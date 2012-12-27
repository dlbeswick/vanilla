package org.farng.mp3.id3;

import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * This frame is used if the frame identifier is not recognized. the contents of the frame are read as a byte stream and
 * kept so they can be saved when the file is written again
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public class FrameBodyUnsupported extends AbstractID3v2FrameBody {

    private TagIdentifier identifier = TagFrameIdentifier.EMPTY;
    private byte[] value;

    /**
     * Creates a new FrameBodyUnsupported object.
     */
    public FrameBodyUnsupported(final byte[] value) {
        this.value = value;
    }

    /**
     * Creates a new FrameBodyUnsupported object.
     */
    public FrameBodyUnsupported(final FrameBodyUnsupported copyObject) {
        super(copyObject);
        this.identifier = copyObject.identifier;
        assert(copyObject == null || this.identifier != null);
        this.value = (byte[]) copyObject.value.clone();
    }

    /**
     * Creates a new FrameBodyUnsupported object.
     */
    public FrameBodyUnsupported(final RandomAccessFile file, AbstractID3 parent) throws IOException, InvalidTagException {
        this.read(file, parent);
    }

    public TagIdentifier getIdentifier() {
        return this.identifier;
    }

    public int getSize() {
        int size = 0;
        if (this.value != null) {
            size += this.value.length;
        }
        return size;
    }

    public boolean isSubsetOf(final Object object) {
        if ((object instanceof FrameBodyUnsupported) == false) {
            return false;
        }
        final FrameBodyUnsupported frameBodyUnsupported = (FrameBodyUnsupported) object;
        final String subset = new String(this.value);
        final String superset = new String(frameBodyUnsupported.value);
        if (superset.indexOf(subset) < 0) {
            return false;
        }
        return super.isSubsetOf(object);
    }

    public boolean equals(final Object obj) {
        if ((obj instanceof FrameBodyUnsupported) == false) {
            return false;
        }
        final FrameBodyUnsupported frameBodyUnsupported = (FrameBodyUnsupported) obj;
        if (this.identifier.equals(frameBodyUnsupported.identifier) == false) {
            return false;
        }
        if (Arrays.equals(this.value, frameBodyUnsupported.value) == false) {
            return false;
        }
        return super.equals(obj);
    }

    protected void setupObjectList() {
//        throw new UnsupportedOperationException();
    }

    public void read(final RandomAccessFile file, AbstractID3 parent) throws IOException, InvalidTagException {
        final int size;
        final byte[] buffer;
        if (has6ByteHeader(parent)) {
            // go back and read the 3 byte unsupported identifier;
            file.seek(file.getFilePointer() - 3);
            buffer = new byte[3];
            file.read(buffer);
            this.identifier = TagFrameIdentifier.get(buffer);
        } else {
            // go back and read the 4 byte unsupported identifier;
            file.seek(file.getFilePointer() - 4);
            buffer = new byte[4];
            file.read(buffer);
            this.identifier = TagFrameIdentifier.get(buffer);
        }
        size = readHeader(file, parent);

        // read the data
        this.value = new byte[size];
        file.read(this.value);
    }

    public String toString() {
        return "??" + getIdentifier() + " : " + (new String(this.value));
    }

    public void write(final RandomAccessFile file, AbstractID3 parent) throws IOException {
        writeHeader(file, this.getSize(), parent);
        file.write(this.value);
    }
}