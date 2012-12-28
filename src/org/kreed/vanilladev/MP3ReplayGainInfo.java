package org.kreed.vanilladev;

import android.util.Log;

import java.io.IOException;
import java.util.Iterator;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.TagFrameIdentifier;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.FrameBodyTXXX;
import org.farng.mp3.id3.FrameBodyRVA2;
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
			
			Log.i("VanillaMusic", "MP3 replaygain load.");
			
			if (mp3file.getID3v2Tag() != null) {	
				Iterator<AbstractID3v2Frame> iterator = mp3file.getID3v2Tag().getFrameOfType(TagFrameIdentifier.get("TXXX"));
				
				if (iterator != null) {
					Log.i("VanillaMusic", "Parsing TXXX field.");
					
					while (iterator.hasNext()) {
						FrameBodyTXXX txxx = (FrameBodyTXXX)iterator.next().getBody();
						String description = txxx.getDescription();
						
						if (description.equalsIgnoreCase("replaygain_track_gain"))
							mTrackGain = parseReplayGainDbValue(txxx.getObject("Text").toString());
						else if (description.equalsIgnoreCase("replaygain_album_gain"))
							mAlbumGain = parseReplayGainDbValue(txxx.getObject("Text").toString());
					}
				} else {
					Log.i("VanillaMusic", "No TXXX field in tag.");
				}

				if (mAlbumGain != null && mTrackGain != null)
					return;
				
				iterator = mp3file.getID3v2Tag().getFrameOfType(TagFrameIdentifier.get("RVA2"));
				
				if (iterator != null) {
					Log.i("VanillaMusic", "Parsing RVA2 field.");
					
					while (iterator.hasNext()) {
						FrameBodyRVA2 frame = (FrameBodyRVA2)iterator.next().getBody();

						if (frame.channelType() != 1) {
							Log.i("VanillaMusic", "Skipping RVA2 field with channel type " + frame.channelType().toString());
							continue;
						}
						
						Log.i("VanillaMusic", "RVA2 identification: " + frame.identification());
						
						if (frame.identification().toLowerCase().indexOf("album") == -1)
							mTrackGain = AmplitudeGain.inDecibels((float)frame.gain());
						else
							mAlbumGain = AmplitudeGain.inDecibels((float)frame.gain());
					}
				} else {
					Log.i("VanillaMusic", "No RVA2 field in tag.");
				}
			} else {
				Log.i("VanillaMusic", "No ID3 tag in file.");
			}
		} catch (TagException e) {
			throw(new DataExtractException(mMediaSource.sourceInfo(), e));
		} catch (IOException e) {
			throw(new MediaAccessException(mMediaSource.sourceInfo(), e));
		}
	}
}
