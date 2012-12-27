package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Original lyricist/text writer' frame is intended for the<br>
 * <p/>
 * &nbsp;&nbsp; text writer of the original recording, if for example the music in<br> &nbsp;&nbsp; the file should be a
 * cover of a previously released song.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTOLY extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTOLY object.
     */
    public FrameBodyTOLY() {
        super();
    }

    /**
     * Creates a new FrameBodyTOLY object.
     */
    public FrameBodyTOLY(final FrameBodyTOLY body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTOLY object.
     */
    public FrameBodyTOLY(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTOLY object.
     */
    public FrameBodyTOLY(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TOLY");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}