package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Original filename' frame contains the preferred filename for the<br> &nbsp;&nbsp; file, since some
 * media doesn't allow the desired length of the<br>
 * <p/>
 * &nbsp;&nbsp; filename. The filename is case sensitive and includes its suffix.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTOFN extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTOFN object.
     */
    public FrameBodyTOFN() {
        super();
    }

    /**
     * Creates a new FrameBodyTOFN object.
     */
    public FrameBodyTOFN(final FrameBodyTOFN body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTOFN object.
     */
    public FrameBodyTOFN(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTOFN object.
     */
    public FrameBodyTOFN(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TOFN");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}