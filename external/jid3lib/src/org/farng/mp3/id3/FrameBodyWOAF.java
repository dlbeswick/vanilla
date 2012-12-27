package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Official audio file webpage' frame is a URL pointing at a file<br> &nbsp;&nbsp; specific
 * webpage.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyWOAF extends AbstractFrameBodyUrlLink {

    /**
     * Creates a new FrameBodyWOAF object.
     */
    public FrameBodyWOAF() {
        super();
    }

    /**
     * Creates a new FrameBodyWOAF object.
     */
    public FrameBodyWOAF(final String urlLink) {
        super(urlLink);
    }

    /**
     * Creates a new FrameBodyWOAF object.
     */
    public FrameBodyWOAF(final FrameBodyWOAF body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyWOAF object.
     */
    public FrameBodyWOAF(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("WOAF");
    public TagIdentifier getIdentifier() {
    	return IDENTIFIER;
    }
}