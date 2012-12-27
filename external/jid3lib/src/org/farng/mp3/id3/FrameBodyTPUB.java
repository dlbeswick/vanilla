package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Publisher' frame simply contains the name of the label or<br> &nbsp;&nbsp; publisher.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTPUB extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTPUB object.
     */
    public FrameBodyTPUB() {
        super();
    }

    /**
     * Creates a new FrameBodyTPUB object.
     */
    public FrameBodyTPUB(final FrameBodyTPUB body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTPUB object.
     */
    public FrameBodyTPUB(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTPUB object.
     */
    public FrameBodyTPUB(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TPUB");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}