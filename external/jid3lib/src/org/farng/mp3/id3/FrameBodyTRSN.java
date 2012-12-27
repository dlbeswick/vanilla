package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Internet radio station name' frame contains the name of the<br> &nbsp;&nbsp; internet radio station
 * from which the audio is streamed.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTRSN extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTRSN object.
     */
    public FrameBodyTRSN() {
        super();
    }

    /**
     * Creates a new FrameBodyTRSN object.
     */
    public FrameBodyTRSN(final FrameBodyTRSN body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTRSN object.
     */
    public FrameBodyTRSN(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTRSN object.
     */
    public FrameBodyTRSN(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TRSN");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}