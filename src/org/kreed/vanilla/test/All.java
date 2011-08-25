package org.kreed.vanilla.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class All {
	static class JID3LibTestAdapter extends TestCase {
		@Override
		public void run(TestResult result) {
			org.farng.mp3.AllTestCase.suite().run(result);
		}
	}
	
    public static Test suite() {
    	TestSuite suite = new TestSuite();
    	suite.addTest(new JID3LibTestAdapter());
    	return suite;
	}
}
