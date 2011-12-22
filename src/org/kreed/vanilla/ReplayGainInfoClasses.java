package org.kreed.vanilla;

import java.util.ArrayList;

public class ReplayGainInfoClasses {
	final static public ArrayList<Class<? extends ReplayGainInfo>> SUBCLASSES;
	
	static {
		SUBCLASSES = new ArrayList<Class<? extends ReplayGainInfo>>();
		
		SUBCLASSES.add(MP3ReplayGainInfo.class);
		SUBCLASSES.add(OggReplayGainInfo.class);
	}
}
