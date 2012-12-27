package org.kreed.vanilladev.support;

import java.io.File;

public class MediaSourceFile extends MediaSource {
	public MediaSourceFile(String filePath) {
		super(filePath);
	}
	
	public String filePath() {
		return sourceInfo();
	}
	
	@Override
	public File getFile() throws MediaAccessException {
		File file = new File(filePath());
		
		if (!file.exists())
			throw(new MediaAccessException(filePath(), "File doesn't exist."));
		
		return file;
	}
}