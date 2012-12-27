package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Conductor' frame is used for the name of the conductor.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTPE3 extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTPE3 object.
     */
    public FrameBodyTPE3() {
        super();
    }

    /**
     * Creates a new FrameBodyTPE3 object.
     */
    public FrameBodyTPE3(final FrameBodyTPE3 body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTPE3 object.
     */
    public FrameBodyTPE3(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTPE3 object.
     */
    public FrameBodyTPE3(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TPE3");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}