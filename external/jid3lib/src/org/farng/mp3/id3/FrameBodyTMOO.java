package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Mood' frame is intended to reflect the mood of the audio with a<br> &nbsp;&nbsp; few keywords, e.g.
 * &quot;Romantic&quot; or &quot;Sad&quot;.<br>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTMOO extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTMOO object.
     */
    public FrameBodyTMOO() {
        super();
    }

    /**
     * Creates a new FrameBodyTMOO object.
     */
    public FrameBodyTMOO(final FrameBodyTMOO body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTMOO object.
     */
    public FrameBodyTMOO(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTMOO object.
     */
    public FrameBodyTMOO(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TMOO");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}