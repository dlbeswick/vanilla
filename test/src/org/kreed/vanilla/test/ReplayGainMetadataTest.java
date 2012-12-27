package org.kreed.vanilladev.test;

import org.kreed.vanilladev.AmplitudeGain;
import org.kreed.vanilladev.ReplayGainInfo;
import org.kreed.vanilladev.ReplayGainInfo.DataExtractException;
import org.kreed.vanilladev.ReplayGainInfo.UnsupportedFiletypeException;
import org.kreed.vanilladev.support.MediaAccessException;

public class ReplayGainMetadataTest extends VanillaTest {
	public void testMP3() throws DataExtractException, MediaAccessException, UnsupportedFiletypeException {
		assertGainValues(
				audioAssetPath("metadata test.mp3"), 
				AmplitudeGain.inDecibels(27.9f), 
				AmplitudeGain.inDecibels(27.9f)
		);
	}
	
	public void testOgg() throws DataExtractException, MediaAccessException, UnsupportedFiletypeException {
		assertGainValues(
				audioAssetPath("metadata test.ogg"),
				AmplitudeGain.inDecibels(27.82f), 
				AmplitudeGain.inDecibels(27.82f)
		);
	}

	protected void assertGainValues(
			String assetFileName, 
			AmplitudeGain trackGain, 
			AmplitudeGain albumGain) throws DataExtractException, MediaAccessException, UnsupportedFiletypeException
	{
		ReplayGainInfo info = ReplayGainInfo.forAndroidAsset(
				getTestAssets(),
				assetFileName
			);
		assertEquals(trackGain, info.trackGain());
		assertEquals(albumGain, info.albumGain());
	}
}
