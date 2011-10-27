package org.kreed.vanilla.support;

import java.io.File;

/**
 * Interface allowing media files to be accessed by different methods.
 * 
 * Ensure that you call 'close' when you no longer need this object. Some derived classes require
 * the call to clean up properly, for example, MediaSourceAndroidAsset may leave temporary files 
 * around otherwise.
 */
public abstract class MediaSource {
	protected String mStreamSourceInfo;

	/**
	 * @param streamSourceInfo Information about the source of the file stream, such as a uri or file path.
	 */
	MediaSource(String streamSourceInfo) {
		mStreamSourceInfo = streamSourceInfo;
	}

	public void close() {}
	
	public String sourceInfo() {
		return new String(mStreamSourceInfo);
	}
	
	public abstract File getFile() throws MediaAccessException;
}