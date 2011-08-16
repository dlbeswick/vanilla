package org.farng.mp3.id3;

import org.farng.mp3.AbstractMP3Fragment;
import org.farng.mp3.InvalidTagException;
import org.farng.mp3.TagIdentifier;
import org.farng.mp3.TagFrameIdentifier;
import org.farng.mp3.TagUtility;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * This abstract class is each frame header inside a ID3v2 tag <P>
 *
 * @author Eric Farng
 * @version $Revision: 1.4 $
 */
public abstract class AbstractID3v2Frame extends AbstractMP3Fragment {

    /**
     * Creates a new AbstractID3v2Frame object.
     */
    protected AbstractID3v2Frame() {
        super();
    }

    /**
     * Creates a new AbstractID3v2Frame object.
     */
    protected AbstractID3v2Frame(final AbstractID3v2FrameBody body) {
        super(body);
    }

    /**
     * Creates a new AbstractID3v2Frame object.
     */
    protected AbstractID3v2Frame(final AbstractID3v2Frame frame) {
        super(frame);
    }

    public TagIdentifier getIdentifier() {
    	if (getBody() != null) {
            return getBody().getIdentifier();
        } else {
        	return TagFrameIdentifier.EMPTY4;
        }
    }

    public static boolean isValidID3v2FrameIdentifier(final TagIdentifier identifier) {
        return identifier.isValidID3v2FrameIdentifier();
    }

    public String toString() {
        String string = "";
        if (getBody() != null) {
            string = getBody().toString();
        }
        return string;
    }

    // "parent" is the AbstractID3-derived instance making use of this frame body.
    protected  AbstractID3v2FrameBody readBody(final TagFrameIdentifier identifier, final RandomAccessFile file, AbstractID3 parent)
            throws IOException, InvalidTagException {
    	final TagFrameIdentifier frameIdentifier;
        
        if (TagUtility.isID3v2_2FrameIdentifier(identifier)) {
            frameIdentifier = (TagFrameIdentifier)TagUtility.convertFrameID2_2to2_4(identifier);
            assert(frameIdentifier != null);
        } else {
            frameIdentifier = identifier;
        }
        
        return frameIdentifier.createFrameBody(file, parent);
    }
}