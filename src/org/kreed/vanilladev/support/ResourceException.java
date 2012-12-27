package org.kreed.vanilladev.support;

/**
 * An exception involving a resource that can be named, such as a file.
 */
public abstract class ResourceException extends Exception {
	private static final long serialVersionUID = 6161388374756385465L;
	
	public Throwable mInstigatorException;
	
	protected static String exceptionInfo(
			String resourceInfo, 
			String exceptionInfo, 
			Throwable instigatorException) 
	{
		String formatString = "%s: There was an exception extracting ReplayGain data for this media.";
		String result;
		
		if (instigatorException != null)
		{
			formatString = formatString + " (%s)";
			result = String.format(formatString, resourceInfo, instigatorException);
		}
		else
		{
			result = String.format(formatString, resourceInfo);
		}
		
		if (exceptionInfo != null) {
			result += " More info: " + exceptionInfo;
		}
		
		return result;
	}
	
	/**
	 * @param instigatorException Any exception that contributed to triggering this exception, such
	 * 	as an ioException.
	 */
	protected ResourceException(String resourceInfo, Throwable instigatorException) {
		super(exceptionInfo(resourceInfo, null, instigatorException));
		
		mInstigatorException = instigatorException;
	}

	/** 
	 * @param exceptionInfo Any extra information available about the exception. 
	 */
	protected ResourceException(String resourceInfo, String exceptionInfo) {
		super(exceptionInfo(resourceInfo, exceptionInfo, null));
	}

	protected ResourceException(String resourceInfo, String exceptionInfo, Throwable instigatorException) {
		super(exceptionInfo(resourceInfo, exceptionInfo, instigatorException));
		
		mInstigatorException = instigatorException;
	}
}