package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'ISRC' frame should contain the International Standard Recording<br> &nbsp;&nbsp; Code [ISRC] (12
 * characters).<br>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTSRC extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTSRC object.
     */
    public FrameBodyTSRC() {
        super();
    }

    /**
     * Creates a new FrameBodyTSRC object.
     */
    public FrameBodyTSRC(final FrameBodyTSRC body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTSRC object.
     */
    public FrameBodyTSRC(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTSRC object.
     */
    public FrameBodyTSRC(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TSRC");
    public TagIdentifier getIdentifier() {
    	return IDENTIFIER;
    }
}