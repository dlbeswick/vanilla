package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * <div class=h5>TYER</div>
 * <p/>
 * <div class=t>The 'Year' frame is a numeric string with a year of the recording. This frames is always four characters
 * long (until the year 10000).</div>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTYER extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTYER object.
     */
    public FrameBodyTYER() {
        super();
    }

    /**
     * Creates a new FrameBodyTYER object.
     */
    public FrameBodyTYER(final FrameBodyTYER body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTYER object.
     */
    public FrameBodyTYER(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTYER object.
     */
    public FrameBodyTYER(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TYER");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}