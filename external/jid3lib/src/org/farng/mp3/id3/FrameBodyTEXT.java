package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Lyricist/Text writer' frame is intended for the writer of the<br> &nbsp;&nbsp; text or lyrics in
 * the recording.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTEXT extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTEXT object.
     */
    public FrameBodyTEXT() {
        super();
    }

    /**
     * Creates a new FrameBodyTEXT object.
     */
    public FrameBodyTEXT(final FrameBodyTEXT body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTEXT object.
     */
    public FrameBodyTEXT(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTEXT object.
     */
    public FrameBodyTEXT(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TEXT");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}