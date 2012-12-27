package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Set subtitle' frame is intended for the subtitle of the part of<br> &nbsp;&nbsp; a set this track
 * belongs to.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTSST extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTSST object.
     */
    public FrameBodyTSST() {
        super();
    }

    /**
     * Creates a new FrameBodyTSST object.
     */
    public FrameBodyTSST(final FrameBodyTSST body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTSST object.
     */
    public FrameBodyTSST(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTSST object.
     */
    public FrameBodyTSST(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TSST");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}