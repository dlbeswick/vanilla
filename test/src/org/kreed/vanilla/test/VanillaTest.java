package org.kreed.vanilla.test;

import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

public class VanillaTest extends InstrumentationTestCase {
	static protected String audioAssetPath(String assetFileName) {
		return "audio/" + assetFileName;
	}
	
	protected AssetManager getTestAssets() {
		return getInstrumentation().getContext().getAssets();
	}
}
