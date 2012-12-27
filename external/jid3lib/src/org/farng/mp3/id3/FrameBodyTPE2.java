package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Band/Orchestra/Accompaniment' frame is used for additional<br>
 * <p/>
 * &nbsp;&nbsp; information about the performers in the recording.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTPE2 extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTPE2 object.
     */
    public FrameBodyTPE2() {
        super();
    }

    /**
     * Creates a new FrameBodyTPE2 object.
     */
    public FrameBodyTPE2(final FrameBodyTPE2 body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTPE2 object.
     */
    public FrameBodyTPE2(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTPE2 object.
     */
    public FrameBodyTPE2(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TPE2");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}