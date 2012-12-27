package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Involved people list' is very similar to the musician credits<br> &nbsp;&nbsp; list, but maps
 * between functions, like producer, and names.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTIPL extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTIPL object.
     */
    public FrameBodyTIPL() {
        super();
    }

    /**
     * Creates a new FrameBodyTIPL object.
     */
    public FrameBodyTIPL(final FrameBodyTIPL body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTIPL object.
     */
    public FrameBodyTIPL(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTIPL object.
     */
    public FrameBodyTIPL(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TIPL");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}