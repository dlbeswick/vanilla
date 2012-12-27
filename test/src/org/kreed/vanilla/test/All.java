package org.kreed.vanilladev.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class All {
    public static Test suite() {
    	TestSuite suite = new TestSuite("All Vanilla tests");
    	
    	suite.addTestSuite(ReplayGainMetadataTest.class);
    	suite.addTest(org.farng.mp3.test.AllTestCase.suite());
    	
    	return suite;
	}
}
