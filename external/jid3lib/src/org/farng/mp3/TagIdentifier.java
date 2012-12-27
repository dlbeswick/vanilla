package org.farng.mp3;

abstract public class TagIdentifier implements Comparable<TagIdentifier> {
	public abstract boolean isValidID3v2FrameIdentifier();
	public abstract int length();

	public int compareTo(TagIdentifier rhs)
	{
		return toString().compareTo(rhs.toString());
	}
}