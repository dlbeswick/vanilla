package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Performer sort order' frame defines a string which should be<br> &nbsp;&nbsp; used instead of the
 * performer (TPE2) for sorting purposes.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTSOP extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTSOP object.
     */
    public FrameBodyTSOP() {
        super();
    }

    /**
     * Creates a new FrameBodyTSOP object.
     */
    public FrameBodyTSOP(final FrameBodyTSOP body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTSOP object.
     */
    public FrameBodyTSOP(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTSOP object.
     */
    public FrameBodyTSOP(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TSOP");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}