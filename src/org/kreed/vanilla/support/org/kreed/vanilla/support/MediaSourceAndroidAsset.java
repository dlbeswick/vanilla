package org.kreed.vanilla.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.kreed.vanilla.ContextApplication;
import android.content.res.AssetManager;

/**
 * Creates a temp file with the contents of the given asset when opened. This is necessary because
 * there's currently no way to perform random access IO on asset files, but it also means this access
 * method should be used sparingly. Currently, it's only used during unit tests.
 */
public class MediaSourceAndroidAsset extends MediaSource {
	protected File mTempFile;
	protected boolean mClosed = false;
	protected AssetManager mAssetManager;
	
	public MediaSourceAndroidAsset(AssetManager assetManager, String streamSourceInfo) {
		super(streamSourceInfo);
		
		mAssetManager = assetManager;
	}
	
	public String assetPath() {
		return sourceInfo();
	}
	
	public String assetTempPath() {
		return sourceInfo().replace(File.separatorChar, '_');
	}
	
	@Override
	public File getFile() throws MediaAccessException {
		if (mClosed)
			throw(new MediaAccessException(sourceInfo(), "Object has been closed."));
			
		if (mTempFile == null) {
			try {
				InputStream assetStream = mAssetManager.open(assetPath());
				
				mTempFile = File.createTempFile(
						getClass().getName(),
						assetTempPath(),
						ContextApplication.getContext().getExternalCacheDir()
					);
				
				byte[] buffer = new byte[4096];
				FileOutputStream tempWriter = new FileOutputStream(mTempFile);
				
				while (true) {
					int result = assetStream.read(buffer);
					if (result == -1)
						break;
					
					tempWriter.write(buffer);
				}
				
				tempWriter.close();
			} catch (IOException e) {
				throw(new MediaAccessException(sourceInfo(), e));
			}
		}
		
		return mTempFile;
	}
	
	public void close() {
		mTempFile.delete();
		mTempFile = null;
		mClosed = true;
	}
}