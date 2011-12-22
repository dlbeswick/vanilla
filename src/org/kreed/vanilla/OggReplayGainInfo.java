package org.kreed.vanilla;

import java.io.IOException;

import org.kreed.vanilla.support.MediaAccessException;
import org.kreed.vanilla.support.MediaSource;

import adamb.vorbis.CommentField;
import adamb.vorbis.VorbisCommentHeader;
import adamb.vorbis.VorbisIO;

public class OggReplayGainInfo extends ReplayGainInfo {
	protected OggReplayGainInfo(MediaSource mediaSource) {
		super(mediaSource);
	}
	
	public static boolean supportsFile(MediaSource mediaSource) {
		return mediaSource.sourceInfo().toLowerCase().endsWith(".ogg");
	}
	
	@Override
	protected void loadReplaygainData() throws DataExtractException, MediaAccessException {
		try {
			VorbisCommentHeader comments = VorbisIO.readComments(mMediaSource.getFile());
			
			for (CommentField field : comments.fields) {
				if (field.name.equalsIgnoreCase("replaygain_track_gain")) {
					mTrackGain = parseReplayGainDbValue(field.value);
					break;
				}
			}
			
			for (CommentField field : comments.fields) {
				if (field.name.equalsIgnoreCase("replaygain_album_gain")) {
					mAlbumGain = parseReplayGainDbValue(field.value);
					break;
				}
			}
		} catch (IOException e) {
			throw(new DataExtractException(mMediaSource.sourceInfo(), e));
		}
	}
}
