package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Official Internet radio station homepage' contains a URL<br> &nbsp;&nbsp; pointing at the homepage
 * of the internet radio station.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyWORS extends AbstractFrameBodyUrlLink {

    /**
     * Creates a new FrameBodyWORS object.
     */
    public FrameBodyWORS() {
        super();
    }

    /**
     * Creates a new FrameBodyWORS object.
     */
    public FrameBodyWORS(final String urlLink) {
        super(urlLink);
    }

    /**
     * Creates a new FrameBodyWORS object.
     */
    public FrameBodyWORS(final FrameBodyWORS body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyWORS object.
     */
    public FrameBodyWORS(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("WORS");
    public TagIdentifier getIdentifier() {
    	return IDENTIFIER;
    }
}