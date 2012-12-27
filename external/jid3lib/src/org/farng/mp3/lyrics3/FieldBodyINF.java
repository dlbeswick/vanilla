package org.farng.mp3.lyrics3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;
import org.farng.mp3.id3.AbstractID3;
import org.farng.mp3.object.ObjectStringSizeTerminated;

import java.io.RandomAccessFile;

/**
 * Additional information multi line text.
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FieldBodyINF extends AbstractLyrics3v2FieldBody {

    /**
     * Creates a new FieldBodyINF object.
     */
    public FieldBodyINF() {
        super();
    }

    /**
     * Creates a new FieldBodyINF object.
     */
    public FieldBodyINF(final FieldBodyINF body) {
        super(body);
    }

    /**
     * Creates a new FieldBodyINF object.
     */
    public FieldBodyINF(final String additionalInformation) {
        setObject("Additional Information", additionalInformation);
    }

    /**
     * Creates a new FieldBodyINF object.
     */
    public FieldBodyINF(final RandomAccessFile file, AbstractID3 parent) throws InvalidTagException, java.io.IOException {
        this.read(file, parent);
    }

    public void setAdditionalInformation(final String additionalInformation) {
        setObject("Additional Information", additionalInformation);
    }

    public String getAdditionalInformation() {
        return (String) getObject("Additional Information");
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("INF");
    public TagIdentifier getIdentifier() {
        return IDENTIFIER;
    }

    protected void setupObjectList() {
        appendToObjectList(new ObjectStringSizeTerminated("Additional Information"));
    }
}