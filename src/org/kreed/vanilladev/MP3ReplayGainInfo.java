package org.kreed.vanilladev;

import java.io.IOException;
import java.util.Iterator;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.TagFrameIdentifier;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.FrameBodyTXXX;
import org.kreed.vanilladev.support.MediaAccessException;
import org.kreed.vanilladev.support.MediaSource;

public class MP3ReplayGainInfo extends ReplayGainInfo {
	protected MP3ReplayGainInfo(MediaSource mediaSource) {
		super(mediaSource);
	}
	
	public static boolean supportsFile(MediaSource mediaSource) {
		return mediaSource.sourceInfo().toLowerCase().endsWith(".mp3");
	}
	
	@Override
	protected void loadReplaygainData() throws DataExtractException, MediaAccessException {
		try {
			MP3File mp3file = new MP3File(mMediaSource.getFile(), false);
			
			if (mp3file.getID3v2Tag() != null) {				
				Iterator<AbstractID3v2Frame> iterator = mp3file.getID3v2Tag().getFrameOfType(TagFrameIdentifier.get("TXXX"));
				
				if (iterator != null) {
					while (iterator.hasNext()) {
						FrameBodyTXXX txxx = (FrameBodyTXXX)iterator.next().getBody();
						String description = txxx.getDescription();
						
						if (description.equalsIgnoreCase("replaygain_track_gain"))
							mTrackGain = parseReplayGainDbValue(txxx.getObject("Text").toString());
						else if (description.equalsIgnoreCase("replaygain_album_gain"))
							mAlbumGain = parseReplayGainDbValue(txxx.getObject("Text").toString());
					}
				}
			}
		} catch (TagException e) {
			throw(new DataExtractException(mMediaSource.sourceInfo(), e));
		} catch (IOException e) {
			throw(new MediaAccessException(mMediaSource.sourceInfo(), e));
		}
	}
}
