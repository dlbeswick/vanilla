package org.farng.mp3.lyrics3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;
import org.farng.mp3.id3.AbstractID3;
import org.farng.mp3.object.ObjectStringSizeTerminated;

import java.io.RandomAccessFile;

/**
 * Lyrics/Music Author name.
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FieldBodyAUT extends AbstractLyrics3v2FieldBody {

    /**
     * Creates a new FieldBodyAUT object.
     */
    public FieldBodyAUT() {
        super();
    }

    /**
     * Creates a new FieldBodyAUT object.
     */
    public FieldBodyAUT(final FieldBodyAUT body) {
        super(body);
    }

    /**
     * Creates a new FieldBodyAUT object.
     */
    public FieldBodyAUT(final String author) {
        setObject("Author", author);
    }

    /**
     * Creates a new FieldBodyAUT object.
     */
    public FieldBodyAUT(final RandomAccessFile file, AbstractID3 parent) throws InvalidTagException, java.io.IOException {
        this.read(file, parent);
    }

    public void setAuthor(final String author) {
        setObject("Author", author);
    }

    public String getAuthor() {
        return (String) getObject("Author");
    }

    static protected final TagFrameIdentifier IDENTIFIER = TagFrameIdentifier.get("AUT");
    public TagIdentifier getIdentifier() {
    	return IDENTIFIER;
    }

    protected void setupObjectList() {
        appendToObjectList(new ObjectStringSizeTerminated("Author"));
    }
}