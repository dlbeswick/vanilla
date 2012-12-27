package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagFrameIdentifier;
import org.farng.mp3.TagIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Commercial information' frame is a URL pointing at a webpage<br> &nbsp;&nbsp; with information such
 * as where the album can be bought. There may be<br> &nbsp;&nbsp; more than one &quot;WCOM&quot; frame in a tag, but
 * not with the same content.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyWCOM extends AbstractFrameBodyUrlLink {

    /**
     * Creates a new FrameBodyWCOM object.
     */
    public FrameBodyWCOM() {
        super();
    }

    /**
     * Creates a new FrameBodyWCOM object.
     */
    public FrameBodyWCOM(final String urlLink) {
        super(urlLink);
    }

    /**
     * Creates a new FrameBodyWCOM object.
     */
    public FrameBodyWCOM(final FrameBodyWCOM body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyWCOM object.
     */
    public FrameBodyWCOM(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("WCOM");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    public String getClassIdentifier() {
        return IDENTIFIER.toString() + ((char) 0) + getUrlLink();
    }
}