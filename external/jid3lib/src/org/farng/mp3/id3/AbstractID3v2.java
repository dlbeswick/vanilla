package org.farng.mp3.id3;

import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagException;
import org.farng.mp3.TagNotFoundException;
import org.farng.mp3.TagUtility;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Superclass for all ID3v2 tags
 *
 * @author Eric Farng
 * @version $Revision: 1.5 $
 */
public abstract class AbstractID3v2 extends AbstractID3 {

    /**
     * just used to add up the padding. the size written to the file is determined by file pointer
     */
    private static int paddingCounter = 0;
    private Map<TagIdentifier, ArrayList<AbstractID3v2Frame>> frameMap = null;
    private String duplicateFrameId = null;
    private byte majorVersion = (byte) 0;
    private byte revision = (byte) 0;
    private int duplicateBytes = 0;
    private int emptyFrameBytes = 0;
    private int fileReadSize = 0;
    private int invalidFrameBytes = 0;
    private int padding = 0;

    /**
     * Creates a new AbstractID3v2 object.
     */
    protected AbstractID3v2() {
        super();
        frameMap = new HashMap<TagIdentifier, ArrayList<AbstractID3v2Frame>>();
    }

    /**
     * Creates a new AbstractID3v2 object.
     */
    protected AbstractID3v2(final AbstractID3v2 copyObject) {
        super(copyObject);
        frameMap = new HashMap<TagIdentifier, ArrayList<AbstractID3v2Frame>>();
        duplicateFrameId = copyObject.duplicateFrameId;
        majorVersion = copyObject.majorVersion;
        revision = copyObject.revision;
        duplicateBytes = copyObject.duplicateBytes;
        emptyFrameBytes = copyObject.emptyFrameBytes;
        fileReadSize = copyObject.fileReadSize;
        invalidFrameBytes = copyObject.invalidFrameBytes;
        padding = copyObject.padding;
        final Iterator<TagIdentifier> iterator = copyObject.frameMap.keySet().iterator();
        TagIdentifier identifier;
        AbstractID3v2Frame newFrame;
        while (iterator.hasNext()) {
            identifier = iterator.next();
            newFrame = (AbstractID3v2Frame) TagUtility.copyObject(copyObject.frameMap.get(identifier));
            setFrame(newFrame);
        }
    }

    public boolean tit2FrameHas6ByteHeader() {
    	return true;
    }
    
    protected ArrayList<AbstractID3v2Frame> frameListFor(TagIdentifier key, boolean allowCreate) {
    	ArrayList<AbstractID3v2Frame> result = frameMap.get(key);
    	
    	if (result == null) {
    		if (allowCreate) {
    			result = new ArrayList<AbstractID3v2Frame>();
    			frameMap.put(key, result);
    		}
    	}
    	
    	return result;
    }
    
    public void setFrame(final AbstractID3v2Frame frame) {
        if (frame.getBody() != null) {
            frameListFor(frame.getIdentifier(), true).add(frame);
        }
    }

    public AbstractID3v2Frame getFrame(final TagIdentifier identifier) {
    	ArrayList<AbstractID3v2Frame> result = frameMap.get(identifier);
    	if (result != null)
    		return result.get(0);
    	else
    		return null;
    }

    public int getFrameCount() {
        int size = 0;
        if (frameMap != null) {
            size = frameMap.size();
        }
        return size;
    }

    public void clearFrameMap() {
        this.frameMap.clear();
    }

    public Iterator<ArrayList<AbstractID3v2Frame>> getFrameIterator() {
        return this.frameMap.values().iterator();
    }

    public Iterator<AbstractID3v2Frame> getFrameOfType(final TagIdentifier identifier) {
    	ArrayList<AbstractID3v2Frame> result = frameListFor(identifier, false);
    	
    	if (result != null)
    		return result.iterator();
    	else
    		return null;
    }

    public boolean TagIdentifier(final TagIdentifier identifier) {
        return frameMap.containsKey(identifier);
    }

    public boolean hasFrame(final TagIdentifier identifier) {
    	return hasFrameOfType(identifier);
    }
    
    public boolean hasFrameOfType(final TagIdentifier identifier) {
        return frameMap.containsKey(identifier);
    }

    public Iterator<ArrayList<AbstractID3v2Frame>> iterator() {
        return frameMap.values().iterator();
    }

    public void removeFrame(final String identifier) {
        frameMap.remove(identifier);
    }

    public void removeFrameOfType(final TagIdentifier identifier) {
        final Iterator<AbstractID3v2Frame> iterator = getFrameOfType(identifier);
        while (iterator.hasNext()) {
            final AbstractID3v2Frame frame = (AbstractID3v2Frame) iterator.next();
            frameMap.remove(frame.getIdentifier());
        }
    }

    public Collection<ArrayList<AbstractID3v2Frame>> values() {
        return frameMap.values();
    }

    public void append(final AbstractMP3Tag abstractMP3Tag) {
        final AbstractID3v2 oldTag = this;
        final AbstractID3v2 newTag;
        if (abstractMP3Tag != null) {
            if (abstractMP3Tag instanceof AbstractID3v2) {
                newTag = (AbstractID3v2) abstractMP3Tag;
            } else {
                newTag = new ID3v2_4(abstractMP3Tag);
            }
            final Iterator<ArrayList<AbstractID3v2Frame>> iterator = newTag.getFrameIterator();

            while (iterator.hasNext()) {
                for (AbstractID3v2Frame frame : iterator.next()) {
	                if (!oldTag.hasFrame(frame.getIdentifier())) {
	                    oldTag.setFrame(frame);
	                }
                }
            }
        }
    }

    public void append(final RandomAccessFile file) throws IOException, TagException {
        AbstractID3v2 oldTag;
        try {
            oldTag = new ID3v2_4(file);
            oldTag.append(this);
            oldTag.write(file);
        } catch (TagNotFoundException ex) {
            try {
                oldTag = new ID3v2_3(file);
                oldTag.append(this);
                oldTag.write(file);
            } catch (TagNotFoundException ex2) {
                try {
                    oldTag = new ID3v2_2(file);
                    oldTag.append(this);
                    oldTag.write(file);
                } catch (TagNotFoundException ex3) {
                    write(file);
                }
            }
        }
    }

    public void delete(final RandomAccessFile file) throws IOException {
        // this works by just erasing the "TAG" tag at the beginning
        // of the file
        final byte[] buffer = new byte[3];
        if (seek(file)) {
            file.seek(0);
            file.write(buffer);
        }
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof AbstractID3v2)) {
            return false;
        }
        final AbstractID3v2 abstractID3v2 = (AbstractID3v2) obj;
        if (!frameMap.equals(abstractID3v2.frameMap)) {
            return false;
        }
        return super.equals(obj);
    }

    public void overwrite(final AbstractMP3Tag abstractMP3Tag) {
        final AbstractID3v2 oldTag = this;
        final AbstractID3v2 newTag;
        if (abstractMP3Tag != null) {
            if (abstractMP3Tag instanceof AbstractID3v2) {
                newTag = (AbstractID3v2) abstractMP3Tag;
            } else {
                newTag = new ID3v2_4(abstractMP3Tag);
            }
            final Iterator<ArrayList<AbstractID3v2Frame>> iterator = newTag.getFrameIterator();

            while (iterator.hasNext()) {
            	for (AbstractID3v2Frame frame : iterator.next()) {
            		oldTag.setFrame(frame);
            	}
            }
        }
    }

    public void overwrite(final RandomAccessFile file) throws IOException, TagException {
        AbstractID3v2 oldTag;
        try {
            oldTag = new ID3v2_4(file);
            oldTag.overwrite(this);
            oldTag.write(file);
        } catch (TagNotFoundException ex) {
            try {
                oldTag = new ID3v2_3(file);
                oldTag.overwrite(this);
                oldTag.write(file);
            } catch (TagNotFoundException ex2) {
                try {
                    oldTag = new ID3v2_2(file);
                    oldTag.overwrite(this);
                    oldTag.write(file);
                } catch (TagNotFoundException ex3) {
                    write(file);
                }
            }
        }
    }

    public void write(final RandomAccessFile file) throws IOException, TagException {
    	write(file, this);
    }
    
    public void write(final AbstractMP3Tag abstractMP3Tag) {
        final AbstractID3v2 oldTag = this;
        final AbstractID3v2 newTag;
        if (abstractMP3Tag != null) {
            if (abstractMP3Tag instanceof AbstractID3v2) {
                newTag = (AbstractID3v2) abstractMP3Tag;
            } else {
                newTag = new ID3v2_4(abstractMP3Tag);
            }
            final Iterator<ArrayList<AbstractID3v2Frame>> iterator = newTag.getFrameIterator();
            oldTag.frameMap.clear();

            while (iterator.hasNext()) {
                for (AbstractID3v2Frame eachFrame : iterator.next())
                	oldTag.setFrame(eachFrame);
            }
        }
    }

    protected static int byteArrayToSize(final byte[] buffer) {
        /**
         * the decided not to use the top bit of the 4 bytes so we need to
         * convert the size back and forth
         */
        return (buffer[0] << 21) + (buffer[1] << 14) + (buffer[2] << 7) + buffer[3];
    }

    protected static byte[] sizeToByteArray(final int size) {
        final byte[] buffer = new byte[4];
        buffer[0] = (byte) ((size & 0x0FE00000) >> 21);
        buffer[1] = (byte) ((size & 0x001FC000) >> 14);
        buffer[2] = (byte) ((size & 0x00003F80) >> 7);
        buffer[3] = (byte) (size & 0x0000007F);
        return buffer;
    }

    protected static void resetPaddingCounter() {
        paddingCounter = 0;
    }

    protected static void incrementPaddingCounter() {
        paddingCounter++;
    }

    protected static void decrementPaddingCounter() {
        paddingCounter--;
    }

    protected static int getPaddingCounter() {
        return paddingCounter;
    }

    public byte getMajorVersion() {
        return majorVersion;
    }

    protected void setMajorVersion(final byte majorVersion) {
        this.majorVersion = majorVersion;
    }

    public byte getRevision() {
        return revision;
    }

    protected void setRevision(final byte revision) {
        this.revision = revision;
    }

    public int getDuplicateBytes() {
        return duplicateBytes;
    }

    public String getDuplicateFrameId() {
        return duplicateFrameId;
    }

    public int getEmptyFrameBytes() {
        return emptyFrameBytes;
    }

    public int getFileReadBytes() {
        return fileReadSize;
    }

    protected void setFileReadBytes(final int fileReadSize) {
        this.fileReadSize = fileReadSize;
    }

    protected void setPaddingSize(final int paddingSize) {
        this.padding = paddingSize;
    }

    protected void incrementDuplicateBytes(final int duplicateBytes) {
        this.duplicateBytes += duplicateBytes;
    }

    protected void incrementEmptyFrameBytes(final int emptyFrameBytes) {
        this.emptyFrameBytes += emptyFrameBytes;
    }

    protected void incrementInvalidFrameBytes() {
        this.invalidFrameBytes++;
    }

    protected void appendDuplicateFrameId(final String duplicateFrameId) {
        this.duplicateFrameId += duplicateFrameId;
    }

    public int getInvalidFrameBytes() {
        return invalidFrameBytes;
    }

    public int getPaddingSize() {
        return padding;
    }
}