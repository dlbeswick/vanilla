package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Title/Songname/Content description' frame is the actual name of<br> &nbsp;&nbsp; the piece (e.g.
 * &quot;Adagio&quot;, &quot;Hurricane Donna&quot;).</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTIT2 extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTIT2 object.
     */
    public FrameBodyTIT2() {
        super();
    }

    /**
     * Creates a new FrameBodyTIT2 object.
     * "parent" is the AbstractID3-derived instance making use of this frame body.
     */
    public FrameBodyTIT2(final FrameBodyTIT2 body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTIT2 object.
     */
    public FrameBodyTIT2(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTIT2 object.
     */
    public FrameBodyTIT2(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TIT2");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}