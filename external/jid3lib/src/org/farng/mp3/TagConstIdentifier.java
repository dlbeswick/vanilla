package org.farng.mp3;

import java.io.UnsupportedEncodingException;

public class TagConstIdentifier extends TagPooledIdentifier {
	static class Pool extends TagPooledIdentifier.Pool<TagConstIdentifier> {
		TagConstIdentifier construct(byte[] id) { return new TagConstIdentifier(id); }
		TagConstIdentifier construct(String id) throws UnsupportedEncodingException { return new TagConstIdentifier(id); }
	}
	
	static protected Pool pool = new Pool();
	
	static public TagConstIdentifier get(String id) {
		return pool.get(id);
	}
	
	static public TagConstIdentifier get(byte[] id) {
		return pool.get(id);
	}
	
	protected TagConstIdentifier(String _identifier) throws UnsupportedEncodingException {
		super(_identifier);
	}
	
	protected TagConstIdentifier(byte[] _identifier) {
		super(_identifier);
	}
	
	@Override
	public boolean isValidID3v2FrameIdentifier() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

}
