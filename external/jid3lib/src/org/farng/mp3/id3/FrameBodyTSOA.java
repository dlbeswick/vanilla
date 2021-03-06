package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.RandomAccessFile;

/**
 * &nbsp;&nbsp; The 'Album sort order' frame defines a string which should be used<br> &nbsp;&nbsp; instead of the album
 * name (TALB) for sorting purposes. E.g. an album<br> &nbsp;&nbsp; named &quot;A Soundtrack&quot; might preferably be
 * sorted as &quot;Soundtrack&quot;.</p>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyTSOA extends AbstractFrameBodyTextInformation {

    /**
     * Creates a new FrameBodyTSOA object.
     */
    public FrameBodyTSOA() {
        super();
    }

    /**
     * Creates a new FrameBodyTSOA object.
     */
    public FrameBodyTSOA(final FrameBodyTSOA body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyTSOA object.
     */
    public FrameBodyTSOA(final byte textEncoding, final String text) {
        super(textEncoding, text);
    }

    /**
     * Creates a new FrameBodyTSOA object.
     */
    public FrameBodyTSOA(final RandomAccessFile file, AbstractID3 parent) throws java.io.IOException, InvalidTagException {
        super(file, parent);
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("TSOA");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }
}