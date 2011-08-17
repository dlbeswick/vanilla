package org.kreed.vanilla;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.TagFrameIdentifier;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.FrameBodyTXXX;

import android.util.Log;

public class ReplaygainInfo {
	private AmplitudeGain mAlbumGain = null;
	private AmplitudeGain mTrackGain = null;
	private String mFilePath;
	private boolean mDataLoaded;
	
	ReplaygainInfo(String filePath) {
		mFilePath = filePath;
	}
	
	public boolean hasAlbumGain()
	{
		ensureDataLoaded();
		
		return mAlbumGain != null;
	}
	
	public boolean hasTrackGain()
	{
		ensureDataLoaded();
		
		return mAlbumGain != null;
	}
	
	public AmplitudeGain albumGain()
	{
		ensureDataLoaded();
		
		if (mAlbumGain == null)
			return AmplitudeGain.ZERO;
		else
			return mAlbumGain;
	}
	
	public AmplitudeGain trackGain()
	{
		ensureDataLoaded();
		
		if (mTrackGain == null)
			return AmplitudeGain.ZERO;
		else
			return mTrackGain;
	}

	protected void ensureDataLoaded()
	{
		if (mDataLoaded)
			return;
		
		try {
			MP3File mp3file = new MP3File(new File(mFilePath), false);
			
			if (mp3file.getID3v2Tag() != null) {				
				Iterator<AbstractID3v2Frame> iterator = mp3file.getID3v2Tag().getFrameOfType(TagFrameIdentifier.get("TXXX"));
				
				if (iterator != null) {
					while (iterator.hasNext()) {
						FrameBodyTXXX txxx = (FrameBodyTXXX)iterator.next().getBody();
						String description = txxx.getDescription();
						
						if (description.equalsIgnoreCase("replaygain_track_gain"))
							mTrackGain = parseReplaygainDbValue(txxx.getObject("Text").toString());
						else if (description.equalsIgnoreCase("replaygain_album_gain"))
							mAlbumGain = parseReplaygainDbValue(txxx.getObject("Text").toString());
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mDataLoaded = true;
	}

	// Returns null if text is an invalid value.
	protected AmplitudeGain parseReplaygainDbValue(String text)
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
}
