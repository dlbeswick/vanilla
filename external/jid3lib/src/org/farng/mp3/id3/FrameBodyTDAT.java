package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * <div class=h5>TDAT</div>
 * <p/>
 * <div class=t>The 'Date' frame is a numeric string in the DDMM format containing the date for the recording. This
 * field is always four characters long.</div>
 * <p/>
 * <p/>
 * <a name="TDLY"> </a>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTDAT extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTDAT object.
     */
    public FrameBodyTDAT() {
        super();
    }

    /**
     * Creates a new FrameBodyTDAT object.
     */
    public FrameBodyTDAT(final FrameBodyTDAT body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTDAT object.
     */
    public FrameBodyTDAT(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTDAT object.
     */
    public FrameBodyTDAT(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TDAT");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}