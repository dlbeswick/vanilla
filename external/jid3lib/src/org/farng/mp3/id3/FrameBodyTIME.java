package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * <div class=h5>TIME</div>
 * <p/>
 * <div class=t>The 'Time' frame is a numeric string in the HHMM format containing the time for the recording. This
 * field is always four characters long.</div>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTIME extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTIME object.
     */
    public FrameBodyTIME() {
        super();
    }

    /**
     * Creates a new FrameBodyTIME object.
     */
    public FrameBodyTIME(final FrameBodyTIME body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTIME object.
     */
    public FrameBodyTIME(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTIME object.
     */
    public FrameBodyTIME(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TIME");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}