package org.kreed.vanilla;

// Describes a gain made to an amplitude value. Can be specified either as a linear scaling or as a
// gain in decibels.
public class AmplitudeGain {
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
		return String.format("%+.2f", decibels());
	}
}
