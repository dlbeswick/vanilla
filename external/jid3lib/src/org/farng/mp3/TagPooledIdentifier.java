package org.farng.mp3;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

public abstract class TagPooledIdentifier extends TagIdentifier {
	abstract static class Pool<T extends TagPooledIdentifier> {
		protected HashMap<Integer, T> allTagsByByteArray = new HashMap<Integer, T>();
		protected HashMap<String, T> allTagsByString = new HashMap<String, T>();

		public T get(String id) {
			T tag = allTagsByString.get(id);
			
			if (tag == null) {
				try {
					T result = construct(id);
					
					allTagsByString.put(id, result);
					allTagsByByteArray.put(Arrays.hashCode(id.getBytes()), result);
					
					return result;
				} catch (UnsupportedEncodingException e) {
					assert(false);
					return null;
				}
			} else {
				return tag;
			}
		}
		
		public T get(byte[] id) {
			int hashcode = Arrays.hashCode(id);
			T tag = allTagsByByteArray.get(hashcode);
			
			if (tag == null) {
				T result = construct(id);
				
				allTagsByString.put(id.toString(), result);
				allTagsByByteArray.put(hashcode, result);
				
				return result;
			} else {
				return tag;
			}
		}
		
		abstract T construct(byte[] id);
		abstract T construct(String id) throws UnsupportedEncodingException;
	};
	
	protected byte[] identifier;
	protected String identifierString;
	
	protected TagPooledIdentifier(String _identifier) throws UnsupportedEncodingException {
		identifier = _identifier.getBytes("ASCII");
		identifierString = _identifier;
	}
	
	protected TagPooledIdentifier(byte[] _identifier) {              
		identifier = _identifier;
		identifierString = new String(_identifier);
	}
	
	public boolean equals(Object rhs) {
		if (rhs instanceof TagIdentifier == false)
			return false;
		else if (rhs instanceof TagPooledIdentifier == true)
			return this == rhs;
		else
			return toString() == rhs.toString();
	}
}
