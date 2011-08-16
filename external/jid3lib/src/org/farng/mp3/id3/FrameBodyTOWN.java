package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'File owner/licensee' frame contains the name of the owner or<br>
 * <p/>
 * &nbsp;&nbsp; licensee of the file and it's contents.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTOWN extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTOWN object.
     */
    public FrameBodyTOWN() {
        super();
    }

    /**
     * Creates a new FrameBodyTOWN object.
     */
    public FrameBodyTOWN(final FrameBodyTOWN body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTOWN object.
     */
    public FrameBodyTOWN(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTOWN object.
     */
    public FrameBodyTOWN(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TOWN");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}