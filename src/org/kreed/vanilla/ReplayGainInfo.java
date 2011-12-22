package org.kreed.vanilla;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.kreed.vanilla.support.MediaAccessException;
import org.kreed.vanilla.support.MediaSource;
import org.kreed.vanilla.support.MediaSourceAndroidAsset;
import org.kreed.vanilla.support.MediaSourceFile;
import org.kreed.vanilla.support.ResourceException;

import android.content.res.AssetManager;
import android.util.Log;

/**
 * Extracts and allows access to a file's replaygain info.
 * 
 * This class expects to be subclassed to support different kinds of media file.
 * Subclasses should call ReplayGainInfo.addSubclass in their static block, so that the subclass
 * will be recognised as one able to handle a type of media file.
 * 
 * File resources are held only for as long as is needed to cache the necessary data, and then released.
 * 
 */
public abstract class ReplayGainInfo {
	protected AmplitudeGain mAlbumGain;
	protected AmplitudeGain mTrackGain;
	protected MediaSource mMediaSource;
	private boolean mDataLoaded;

	public static class UnsupportedFiletypeException extends Exception {
		private static final long serialVersionUID = 6161388374756385465L;
		
		UnsupportedFiletypeException(String filePath) {
			super(String.format("%s: ReplayGain is not supported for files of this type.", filePath));
		}
	}
	
	/**
	 * An exception occurred while trying to extract data from the media.
	 */
	public static class DataExtractException extends ResourceException {
		private static final long serialVersionUID = 6161388374756385465L;
		
		DataExtractException(String filePath, Throwable exception) {
			super(
				filePath,
				"There was an exception extracting ReplayGain data for this file.",
				exception
			);
		}
	}	
	
	/**
	 * Creates an instance of a ReplayGainInfo subclass that's appropriate for the type of the given
	 * file.
	 */
	public static ReplayGainInfo forFile(String filePath) 
		throws DataExtractException, UnsupportedFiletypeException, FileNotFoundException 
	{
		return forMediaSource(new MediaSourceFile(filePath));
	}

	/**
	 * Creates an instance of a ReplayGainInfo subclass for the given Android asset.
	 */
	public static ReplayGainInfo forAndroidAsset(AssetManager assetManager, String assetPath) 
		throws DataExtractException, UnsupportedFiletypeException 
	{
		return forMediaSource(new MediaSourceAndroidAsset(assetManager, assetPath));
	}
	
	/**
	 * Creates an instance of a ReplayGainInfo subclass from the information in the given MediaSource.
	 * This object will consider itself to own the supplied mediaSource object and will call 'close'
	 * on it at an appropriate time.
	 */
	protected static ReplayGainInfo forMediaSource(MediaSource mediaSource) 
		throws DataExtractException, UnsupportedFiletypeException 
	{
		for (Class<? extends ReplayGainInfo> subclass : ReplayGainInfoClasses.SUBCLASSES) {
			try {
				if (subclassSupportsFile(subclass, mediaSource)) {
					return subclass.getDeclaredConstructor(MediaSource.class).newInstance(mediaSource);
				}
			} catch (IllegalArgumentException e) {
				throw(new DataExtractException(mediaSource.sourceInfo(), e));
			} catch (SecurityException e) {
				throw(new DataExtractException(mediaSource.sourceInfo(), e));
			} catch (InstantiationException e) {
				throw(new DataExtractException(mediaSource.sourceInfo(), e));
			} catch (IllegalAccessException e) {
				throw(new DataExtractException(mediaSource.sourceInfo(), e));
			} catch (InvocationTargetException e) {
				throw(new DataExtractException(mediaSource.sourceInfo(), e.getTargetException()));
			} catch (NoSuchMethodException e) {
				throw(new DataExtractException(mediaSource.sourceInfo(), e));
			}
		}
	
		throw(new UnsupportedFiletypeException(mediaSource.sourceInfo()));
	}
	
	/**
	 * Subclasses should provide an implementation of this static method.
	 * @param fileName The name component only (no path or uri) of the source of the media file.
	 * @param stream A stream holding data for the file being tested.
	 */
	public static boolean supportsFile(MediaSource mediaSource) throws Exception {
		throw new Exception("Please provide an implementation of this static method in your subclass.");
	}

	/**
	 * The object considers itself responsible for calling 'close' on the supplied MediaSource.
	 */
	protected ReplayGainInfo(MediaSource mediaSource)
	{
		mMediaSource = mediaSource;
	}
	
	public boolean isOpen()
	{
		return mMediaSource != null;
	}
	
	public boolean hasAlbumGain() throws DataExtractException, MediaAccessException
	{
		ensureDataLoaded();
		
		return mAlbumGain != null;
	}
	
	public boolean hasTrackGain() throws DataExtractException, MediaAccessException
	{
		ensureDataLoaded();
		
		return mTrackGain != null;
	}
	
	public AmplitudeGain albumGain() throws DataExtractException, MediaAccessException
	{
		ensureDataLoaded();
		
		if (mAlbumGain == null)
			return AmplitudeGain.ZERO;
		else
			return mAlbumGain;
	}
	
	public AmplitudeGain trackGain() throws DataExtractException, MediaAccessException
	{
		ensureDataLoaded();
		
		if (mTrackGain == null)
			return AmplitudeGain.ZERO;
		else
			return mTrackGain;
	}
	
	public String mediaSourceInfo() {
		return mMediaSource.sourceInfo();
	}

	/** 
	 * Derived classes should set mTrackGain and mAlbumGain in this method.
	 */
	abstract protected void loadReplaygainData() throws DataExtractException, MediaAccessException;
	
	protected void ensureDataLoaded() throws DataExtractException, MediaAccessException
	{
		if (mDataLoaded)
			return;
		
		loadReplaygainData();
		
		mDataLoaded = true;
		
		// Media source is not immediately required after this point.
		mMediaSource.close();
	}

	// Returns null if text is an invalid value.
	protected AmplitudeGain parseReplayGainDbValue(String text)
	{
		int dbIndex = text.toLowerCase().indexOf("db");
		if (dbIndex == -1)
			return null;
		try {
			return AmplitudeGain.inDecibels(Float.parseFloat(text.substring(0, dbIndex - 1)));
		} catch (NumberFormatException e) {
			Log.i(this.getClass().getName(), String.format("Failed to parse replaygain db value '%s': %s", text, e.toString()));
			return null;
		}
	}
	
	private static boolean subclassSupportsFile(Class<? extends ReplayGainInfo> subclass, MediaSource mediaSource) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException 
	{
		return (Boolean)subclass.getMethod("supportsFile", MediaSource.class).invoke(null, mediaSource) == true;
	}
}
