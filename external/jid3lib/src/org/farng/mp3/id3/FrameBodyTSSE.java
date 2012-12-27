package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Software/Hardware and settings used for encoding' frame<br>
 * <p/>
 * &nbsp;&nbsp; includes the used audio encoder and its settings when the file was<br> &nbsp;&nbsp; encoded. Hardware
 * refers to hardware encoders, not the computer on<br> &nbsp;&nbsp; which a program was run.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTSSE extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTSSE object.
     */
    public FrameBodyTSSE() {
        super();
    }

    /**
     * Creates a new FrameBodyTSSE object.
     */
    public FrameBodyTSSE(final FrameBodyTSSE body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTSSE object.
     */
    public FrameBodyTSSE(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTSSE object.
     */
    public FrameBodyTSSE(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TSSE");
    public TagIdentifier getIdentifier() {
    	return IDENTIFIER;
    }
}