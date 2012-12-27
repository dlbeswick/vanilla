package org.farng.mp3;

import org.farng.mp3.id3.AbstractID3;
import org.farng.mp3.object.AbstractMP3Object;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is contained in the <code>AbstractMP3Fragment</code> and represents the actual data of tags. It contains
 * default get/set methods for all data objects. The data is represented as an ArrayList of <code>MP3Object</code>.
 * ID3v2 tags have frames. Lyrics3 tags have fields. ID3v1 tags do not have fragments.
 *
 * @author Eric Farng
 * @version $Revision: 1.6 $
 */
public abstract class AbstractMP3FragmentBody extends AbstractMP3FileItem {

    private static final int SIZE_OBJECT_LIST = 16;
    private static final int SIZE_BRIEF_DESCRIPTION = 64;
    private static final int SIZE_DESCRIPTION = 256;
    /**
     * list of <code>MP3Object</code>
     */
    private List<AbstractMP3Object> objectList = new ArrayList<AbstractMP3Object>(AbstractMP3FragmentBody.SIZE_OBJECT_LIST);

    /**
     * Creates a new MP3FragmentBody object.
     */
    protected AbstractMP3FragmentBody() {
        super();
        setupObjectList();
    }

    /**
     * Creates a new AbstractMP3FragmentBody object.
     */
    protected AbstractMP3FragmentBody(final AbstractMP3FragmentBody copyObject) {
        super(copyObject);
        final Iterator<AbstractMP3Object> iterator = copyObject.iterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object oldObject = (AbstractMP3Object) iterator.next();
            final AbstractMP3Object newObject = (AbstractMP3Object) TagUtility.copyObject(oldObject);
            objectList.add(newObject);
        }
    }

    /**
     * This method calls <code>toString</code> for all it's objects and appends them without any newline characters.
     *
     * @return brief description string
     */
    public String getBriefDescription() {
        final StringBuffer stringBuffer = new StringBuffer(AbstractMP3FragmentBody.SIZE_BRIEF_DESCRIPTION);
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object object = (AbstractMP3Object) iterator.next();
            final String objectToString = object.toString();
            if (objectToString != null && objectToString.length() > 0) {
                final String identifier = object.getIdentifier();
                stringBuffer.append(identifier);
                stringBuffer.append("=\"");
                stringBuffer.append(objectToString);
                stringBuffer.append("\"; ");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * This method calls <code>toString</code> for all it's objects and appends them. It contains new line characters
     * and is more suited for display purposes
     *
     * @return formatted description string
     */
    public String getDescription() {
        final StringBuffer stringBuffer = new StringBuffer(AbstractMP3FragmentBody.SIZE_DESCRIPTION);
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object object = (AbstractMP3Object) iterator.next();
            final String identifier = object.getIdentifier();
            stringBuffer.append(identifier);
            stringBuffer.append(" = ");
            final String string = object.toString();
            stringBuffer.append(string);
            stringBuffer.append(TagConstant.SEPERATOR_LINE);
        }
        final String toString = stringBuffer.toString();
        return toString.trim();
    }

    public Iterator<AbstractMP3Object> getObjectListIterator() {
        return objectList.listIterator();
    }

    /**
     * Sets the all objects of identifier type to <code>object</code> argument.
     *
     * @param identifier <code>MP3Object</code> identifier
     * @param object     new object value
     */
    public void setObject(final String identifier, final Object object) {
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object abstractMP3Object = iterator.next();
            final String currentIdentifier = abstractMP3Object.getIdentifier();
            if (currentIdentifier.equals(identifier)) {
                abstractMP3Object.setValue(object);
            }
        }
    }

    /**
     * Removes all objects of the identifier type.
     *
     * @param identifier <code>MP3Object</code> identifier
     */
    public void removeObjects(final String identifier) {
    	ArrayList<AbstractMP3Object> toRemove = new ArrayList<AbstractMP3Object>();
    	
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object abstractMP3Object = (AbstractMP3Object) iterator.next();
            final String currentIdentifier = abstractMP3Object.getIdentifier();
            if (currentIdentifier.equals(identifier)) {
            	toRemove.add(abstractMP3Object);
            }
        }
        
        objectList.removeAll(toRemove);
    }
    
    /**
     * Returns the object of the <code>MP3Object</code> with the specified <code>identifier</code>
     *
     * @param identifier <code>MP3Object</code> identifier
     *
     * @return the object of the <code>MP3Object</code> with the specified <code>identifier</code>
     */
    public Object getObject(final String identifier) {
        Object object = null;
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object abstractMP3Object = iterator.next();
            final String currentIdentifier = abstractMP3Object.getIdentifier();
            if (currentIdentifier.equals(identifier)) {
                object = abstractMP3Object.getValue();
            }
        }
        return object;
    }

    /**
     * Returns the estimated size in bytes of this object if it was to be written to file. This is not guaranteed to be
     * accurate 100% of the time.
     *
     * @return estimated size in bytes of this object
     */
    public int getSize() {
        //todo get this working 100% of the time
        int size = 0;
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object object = (AbstractMP3Object) iterator.next();
            size += object.getSize();
        }
        return size;
    }

    /**
     * Returns true if this instance and its entire <code>MP3Object</code> array list is a subset of the argument. This
     * class is a subset if it is the same class as the argument.
     *
     * @param object object to determine subset of
     *
     * @return true if this instance and its entire object array list is a subset of the argument.
     */
    public boolean isSubsetOf(final Object object) {
        if (!super.isSubsetOf(object)) {
            return false;
        }
        if (!(object instanceof AbstractMP3FragmentBody)) {
            return false;
        }
        final List<AbstractMP3Object> superset = ((AbstractMP3FragmentBody) object).objectList;
        final int objectListSize = objectList.size();
        for (int i = 0; i < objectListSize; i++) {
            final AbstractMP3Object abstractMP3Object = (AbstractMP3Object) objectList.get(i);
            if (abstractMP3Object.getValue() != null && !superset.contains(abstractMP3Object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this object and its entire <code>MP3Object</code> array list equals the argument. This object is
     * equal to the argument only if they are the same class.
     *
     * @param obj object to determine equality of
     *
     * @return true if this object and its entire <code>MP3Object</code> array list equals the argument.
     */
    public boolean equals(final Object obj) {
        if (!(obj instanceof AbstractMP3FragmentBody)) {
            return false;
        }
        final AbstractMP3FragmentBody abstractMP3FragmentBody = (AbstractMP3FragmentBody) obj;
        if (!objectList.equals(abstractMP3FragmentBody.objectList)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns an iterator of the <code>MP3Object</code> object list.
     *
     * @return iterator of the <code>MP3Object</code> object list.
     */
    public Iterator<AbstractMP3Object> iterator() {
        return objectList.iterator();
    }

    protected void appendToObjectList(final AbstractMP3Object object) {
        objectList.add(object);
    }

    /**
     * Read the data from the given file into this object. The file needs to have its file pointer in the correct
     * location.
     *
     * @param file file to read from
     *
     * @throws IOException         on any I/O error
     * @throws InvalidTagException if there is any error in the data format.
     */
    public void read(final RandomAccessFile file, AbstractID3 parent) throws IOException, InvalidTagException {
        final int size = readHeader(file, parent);
        final byte[] buffer = new byte[size];
        file.read(buffer);
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        int offset = 0;
        while (iterator.hasNext()) {
            // sanjay@revasoft.com start bug fix
            if (offset > size - 1) {
                throw new InvalidTagException("Invalid size for Frame Body");
            }

            // sanjay@revasoft.com end bug fix
            final AbstractMP3Object object = (AbstractMP3Object) iterator.next();
            object.readByteArray(buffer, offset);
            offset += object.getSize();
        }
    }

    /**
     * Calls <code>toString</code> for all <code>MP3Object</code> objects and creates a string with a new line
     * character.
     *
     * @return description string
     */
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer(AbstractMP3FragmentBody.SIZE_DESCRIPTION);
        final TagIdentifier thisIdentifier = getIdentifier();
        stringBuffer.append(thisIdentifier);
        stringBuffer.append(TagConstant.SEPERATOR_LINE);
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object object = (AbstractMP3Object) iterator.next();
            final String objectIdentifier = object.getIdentifier();
            stringBuffer.append(objectIdentifier);
            stringBuffer.append(" = ");
            final String string = object.toString();
            stringBuffer.append(string);
            stringBuffer.append(TagConstant.SEPERATOR_LINE);
        }
        return stringBuffer.toString();
    }

    /**
     * Write the contents of this object to the file at the position it is currently at.
     *
     * @param file destination file
     *
     * @throws IOException on any I/O error
     */
    public void write(final RandomAccessFile file, AbstractID3 parent) throws IOException {
        final int size = getSize();
        writeHeader(file, size, parent);
        final Iterator<AbstractMP3Object> iterator = objectList.listIterator();
        while (iterator.hasNext()) {
            final AbstractMP3Object object = (AbstractMP3Object) iterator.next();
            final byte[] buffer = object.writeByteArray();
            file.write(buffer);
        }
    }

    /**
     * Reads the header for the fragment body. The header contains things such as the length.
     *
     * @param file file to read the header from.
     * @param parent the AbstractID3-derived instance making use of this frame body.
     *
     * @return size of body in bytes as stated in the header.
     *
     * @throws IOException         on any I/O error
     * @throws InvalidTagException if there is any error in the data format.
     */
    protected abstract int readHeader(RandomAccessFile file, AbstractID3 parent) throws IOException, InvalidTagException;

    /**
     * Create the order of <code>MP3Object</code> objects that this body expects. This method needs to be overwritten.
     */
    protected abstract void setupObjectList();

    /**
     * Write the body header to the file at the current file position.
     *
     * @param file file to write to
     * @param size number of bytes the body contains.
     *
     * @throws IOException on any I/O error
     */
    protected abstract void writeHeader(RandomAccessFile file, int size, AbstractID3 parent) throws IOException;

    // "parent" should be the AbstractID3-derived instance 
    protected static boolean has6ByteHeader(AbstractID3 parent) {
    	// dbeswick: original code was expensive on android.
        /*final Exception exception = new Exception();
        final StackTraceElement[] stackArray = exception.getStackTrace();
        final String id3v2_2name = ID3v2_2.class.getName();
        final String id3v2_3name = ID3v2_3.class.getName();
        final String id3v2_4name = ID3v2_4.class.getName();
        boolean has6ByteHeader = false;
        boolean withinTag = false;
        for (int i = stackArray.length - 1; i >= 0; i--) {
            final String className = stackArray[i].getClassName();
            if (id3v2_2name.equals(className)) {
                has6ByteHeader = true;
                withinTag = true;
            } else if (id3v2_3name.equals(className)) {
                has6ByteHeader = false;
                withinTag = true;
            } else if (id3v2_4name.equals(className)) {
                has6ByteHeader = false;
                withinTag = true;
            }
        }
        if (!withinTag) {
            throw new UnsupportedOperationException("FragmentBody not called within ID3v2 tag.");
        }
        return has6ByteHeader;*/
    	
    	return parent.tit2FrameHas6ByteHeader();
    }
}