package org.kreed.vanilladev;

/** 
 * Describes a gain made to an amplitude value. Can be specified either as a linear scaling or as a
 * gain in decibels.
 */
public class AmplitudeGain implements Comparable<AmplitudeGain> {
	static public final AmplitudeGain ZERO = AmplitudeGain.inDecibels(0);
	
	protected Float mDecibels;
	protected Float mLinearScale;
	protected final float DB_MIN = -144.0f; // Noise floor of 24-bit digital audio. 
	
	// Specify a gain in decibels.
	public static AmplitudeGain inDecibels(float decibels) 
	{
		return new AmplitudeGain(decibels, null);
	}
	
	// Specify a gain as a linear scaling value. Must be greater than or equal to zero.
	public static AmplitudeGain inLinearScale(float scale) 
	{
		return new AmplitudeGain(null, scale);
	}
	
	protected AmplitudeGain(Float decibels, Float linearScale) 
	{
		mDecibels = decibels;
		mLinearScale = linearScale;
		
		if (mLinearScale != null && mLinearScale < 0)
			throw(new IllegalArgumentException("Linear scale must be greater than or equal to zero."));
	}

	@Override
	public int compareTo(AmplitudeGain rhs) {
		if (mDecibels != null)
			return mDecibels.compareTo(rhs.decibels());
		else 
			return mLinearScale.compareTo(rhs.linearScale());
	}
	
	@Override
	public boolean equals(Object rhs) {
	     if (!getClass().isInstance(rhs)) 
	    	 return false;
	     
	     return compareTo((AmplitudeGain)rhs) == 0;
     }

	/** 
	 * The result is the hashCode of either the linear scale or decibel value, whichever was  specified on 
	 * construction of the instance.
	 * Be mindful of this when using these objects in hashes. AmplitudeGain objects specified with different
	 * units won't be equivalent when it comes to hashes. This differs from the "equals" case. I think
	 * it's more important to be stricter in this case, so floating point error doesn't cause unpredictable
	 * results when objects of different types are added to a hash. With this implementation, the result 
	 * may still be unexpected at times, but it's predictable.
	 */
	@Override
	public int hashCode() {
		if (mDecibels != null)
			return mDecibels.hashCode();
		else
			return mLinearScale.hashCode();
	}
	
	// Return the amount of gain in decibels.
	public float decibels() 
	{
		if (mDecibels != null) {
			return mDecibels;
		} else {
			return (float)(20 * Math.log10(mLinearScale)); 
		}
	}
	
	// Return the amount of gain as a linear scaling value.
	public float linearScale() 
	{
		if (mDecibels != null) {
	        if (mDecibels == 0)
	            return 1.0f;
	        else if (mDecibels <= DB_MIN)
	            return 0.0f;
	        else
	            return (float)Math.pow(10.0f, mDecibels / 20.0f);
		} else {
			return mLinearScale;
		}
	}

	public AmplitudeGain increment(AmplitudeGain gain)
	{
		if (mDecibels != null)
			return new AmplitudeGain(mDecibels + gain.decibels(), null);
		else
			return new AmplitudeGain(null, mLinearScale + gain.decibels());
	}
	
	public AmplitudeGain decrement(AmplitudeGain gain)
	{
		if (mDecibels != null)
			return new AmplitudeGain(mDecibels - gain.decibels(), null);
		else
			return new AmplitudeGain(null, mLinearScale - gain.decibels());
	}
	
	public String toString() 
	{
		return String.format("%+.2f db", decibels());
	}
}
