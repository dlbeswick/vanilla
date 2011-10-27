package org.kreed.vanilla.support;

/**
 * An exception occurred while trying to access the media.
 */
public class MediaAccessException extends ResourceException {
	private static final long serialVersionUID = -715904887480199147L;

	public MediaAccessException(String mediaSourceInfo, Exception instigatorException) {
		super(mediaSourceInfo, instigatorException);
	}

	public MediaAccessException(String mediaSourceInfo, String exceptionInfo) {
		super(mediaSourceInfo, exceptionInfo);
	}
}