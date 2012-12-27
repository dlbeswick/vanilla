package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Composer' frame is intended for the name of the composer.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTCOM extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTCOM object.
     */
    public FrameBodyTCOM() {
        super();
    }

    /**
     * Creates a new FrameBodyTCOM object.
     */
    public FrameBodyTCOM(final FrameBodyTCOM body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTCOM object.
     */
    public FrameBodyTCOM(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTCOM object.
     */
    public FrameBodyTCOM(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TCOM");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }

}