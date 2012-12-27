package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Payment' frame is a URL pointing at a webpage that will handle<br>
 * <p/>
 * &nbsp;&nbsp; the process of paying for this file.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyWPAY extends AbstractFrameBodyUrlLink {

    /**
     * Creates a new FrameBodyWPAY object.
     */
    public FrameBodyWPAY() {
        super();
    }

    /**
     * Creates a new FrameBodyWPAY object.
     */
    public FrameBodyWPAY(final String urlLink) {
        super(urlLink);
    }

    /**
     * Creates a new FrameBodyWPAY object.
     */
    public FrameBodyWPAY(final FrameBodyWPAY body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyWPAY object.
     */
    public FrameBodyWPAY(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("WPAY");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}