package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Title sort order' frame defines a string which should be used<br> &nbsp;&nbsp; instead of the title
 * (TIT2) for sorting purposes.<br>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTSOT extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTSOT object.
     */
    public FrameBodyTSOT() {
        super();
    }

    /**
     * Creates a new FrameBodyTSOT object.
     */
    public FrameBodyTSOT(final FrameBodyTSOT body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTSOT object.
     */
    public FrameBodyTSOT(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTSOT object.
     */
    public FrameBodyTSOT(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TSOT");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}