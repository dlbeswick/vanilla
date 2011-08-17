package org.kreed.vanilla;

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

	public void increment(AmplitudeGain gain)
	{
		if (mDecibels != null)
			mDecibels += gain.decibels();
		else
			mLinearScale += gain.linearScale();
	}
	
	public String toString() 
	{
		return String.format("%+f.2", decibels());
	}
}
