package org.farng.mp3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.farng.mp3.id3.AbstractID3;
import org.farng.mp3.id3.FrameBodyAPIC;
import org.farng.mp3.id3.FrameBodyCOMM;
import org.farng.mp3.id3.FrameBodyENCR;
import org.farng.mp3.id3.FrameBodyGEOB;
import org.farng.mp3.id3.FrameBodyGRID;
import org.farng.mp3.id3.FrameBodyMCDI;
import org.farng.mp3.id3.FrameBodyPCNT;
import org.farng.mp3.id3.FrameBodyPOPM;
import org.farng.mp3.id3.FrameBodyPRIV;
import org.farng.mp3.id3.FrameBodyRVAD;
import org.farng.mp3.id3.FrameBodySYLT;
import org.farng.mp3.id3.FrameBodyTALB;
import org.farng.mp3.id3.FrameBodyTBPM;
import org.farng.mp3.id3.FrameBodyTCOM;
import org.farng.mp3.id3.FrameBodyTCON;
import org.farng.mp3.id3.FrameBodyTCOP;
import org.farng.mp3.id3.FrameBodyTDAT;
import org.farng.mp3.id3.FrameBodyTDEN;
import org.farng.mp3.id3.FrameBodyTDLY;
import org.farng.mp3.id3.FrameBodyTDOR;
import org.farng.mp3.id3.FrameBodyTDRC;
import org.farng.mp3.id3.FrameBodyTDRL;
import org.farng.mp3.id3.FrameBodyTDTG;
import org.farng.mp3.id3.FrameBodyTENC;
import org.farng.mp3.id3.FrameBodyTEXT;
import org.farng.mp3.id3.FrameBodyTFLT;
import org.farng.mp3.id3.FrameBodyTIME;
import org.farng.mp3.id3.FrameBodyTIPL;
import org.farng.mp3.id3.FrameBodyTIT1;
import org.farng.mp3.id3.FrameBodyTIT2;
import org.farng.mp3.id3.FrameBodyTIT3;
import org.farng.mp3.id3.FrameBodyTKEY;
import org.farng.mp3.id3.FrameBodyTLAN;
import org.farng.mp3.id3.FrameBodyTLEN;
import org.farng.mp3.id3.FrameBodyTMCL;
import org.farng.mp3.id3.FrameBodyTMED;
import org.farng.mp3.id3.FrameBodyTMOO;
import org.farng.mp3.id3.FrameBodyTOAL;
import org.farng.mp3.id3.FrameBodyTOFN;
import org.farng.mp3.id3.FrameBodyTOLY;
import org.farng.mp3.id3.FrameBodyTOPE;
import org.farng.mp3.id3.FrameBodyTORY;
import org.farng.mp3.id3.FrameBodyTOWN;
import org.farng.mp3.id3.FrameBodyTPE1;
import org.farng.mp3.id3.FrameBodyTPE2;
import org.farng.mp3.id3.FrameBodyTPE3;
import org.farng.mp3.id3.FrameBodyTPE4;
import org.farng.mp3.id3.FrameBodyTPOS;
import org.farng.mp3.id3.FrameBodyTPRO;
import org.farng.mp3.id3.FrameBodyTPUB;
import org.farng.mp3.id3.FrameBodyTRCK;
import org.farng.mp3.id3.FrameBodyTRDA;
import org.farng.mp3.id3.FrameBodyTRSN;
import org.farng.mp3.id3.FrameBodyTRSO;
import org.farng.mp3.id3.FrameBodyTSIZ;
import org.farng.mp3.id3.FrameBodyTSOA;
import org.farng.mp3.id3.FrameBodyTSOP;
import org.farng.mp3.id3.FrameBodyTSOT;
import org.farng.mp3.id3.FrameBodyTSRC;
import org.farng.mp3.id3.FrameBodyTSSE;
import org.farng.mp3.id3.FrameBodyTSST;
import org.farng.mp3.id3.FrameBodyTXXX;
import org.farng.mp3.id3.FrameBodyTYER;
import org.farng.mp3.id3.FrameBodyUFID;
import org.farng.mp3.id3.FrameBodyUSLT;
import org.farng.mp3.id3.FrameBodyUnsupported;
import org.farng.mp3.id3.FrameBodyWCOM;
import org.farng.mp3.id3.FrameBodyWCOP;
import org.farng.mp3.id3.FrameBodyWOAF;
import org.farng.mp3.id3.FrameBodyWOAR;
import org.farng.mp3.id3.FrameBodyWOAS;
import org.farng.mp3.id3.FrameBodyWORS;
import org.farng.mp3.id3.FrameBodyWPAY;
import org.farng.mp3.id3.FrameBodyWPUB;
import org.farng.mp3.id3.FrameBodyWXXX;
import org.farng.mp3.id3.AbstractID3v2FrameBody;

public class TagFrameIdentifier extends TagPooledIdentifier {
	public static final TagFrameIdentifier EMPTY;
	public static final TagFrameIdentifier EMPTY3;
	public static final TagFrameIdentifier EMPTY4;
	
	static {
		try {
			EMPTY = new TagFrameIdentifier("");
			EMPTY3 = new TagFrameIdentifier("   ");
			EMPTY4 = new TagFrameIdentifier("    ");
		} catch (UnsupportedEncodingException e) {
            throw new ExceptionInInitializerError(e);
		}
	}
	
	static class Pool extends TagPooledIdentifier.Pool<TagFrameIdentifier> {
		TagFrameIdentifier construct(byte[] id) { return new TagFrameIdentifier(id); }
		TagFrameIdentifier construct(String id) throws UnsupportedEncodingException { return new TagFrameIdentifier(id); }
	}
	
	static protected Pool pool = new Pool();
	
	static public TagFrameIdentifier get(String id) {
		return pool.get(id);
	}
	
	static public TagFrameIdentifier get(byte[] id) {
		return pool.get(id);
	}
	
	protected TagFrameIdentifier(String _identifier) throws UnsupportedEncodingException {
		super(_identifier);
	}
	
	protected TagFrameIdentifier(byte[] _identifier) {
		super(_identifier);
	}
	
	public AbstractID3v2FrameBody createFrameBody(RandomAccessFile file, AbstractID3 parent) {
        try {
	        if ("APIC".equals(identifierString)) {
	            return new FrameBodyAPIC(file, parent);
	        } else if ("COMM".equals(identifierString)) {
	            return new FrameBodyCOMM(file, parent);
	        } else if ("ENCR".equals(identifierString)) {
	            return new FrameBodyENCR(file, parent);
	        } else if ("GEOB".equals(identifierString)) {
	            return new FrameBodyGEOB(file, parent);
	        } else if ("GRID".equals(identifierString)) {
	            return new FrameBodyGRID(file, parent);
	        } else if ("MCDI".equals(identifierString)) {
	            return new FrameBodyMCDI(file, parent);
	        } else if ("PCNT".equals(identifierString)) {
	            return new FrameBodyPCNT(file, parent);
	        } else if ("POPM".equals(identifierString)) {
	            return new FrameBodyPOPM(file, parent);
	        } else if ("PRIV".equals(identifierString)) {
	            return new FrameBodyPRIV(file, parent);
	        } else if ("RVAD".equals(identifierString)) {
	            return new FrameBodyRVAD(file, parent);
	        } else if ("SYLT".equals(identifierString)) {
	            return new FrameBodySYLT(file, parent);
	        } else if ("TALB".equals(identifierString)) {
	            return new FrameBodyTALB(file, parent);
	        } else if ("TBPM".equals(identifierString)) {
	            return new FrameBodyTBPM(file, parent);
	        } else if ("TCOM".equals(identifierString)) {
	            return new FrameBodyTCOM(file, parent);
	        } else if ("TCON".equals(identifierString)) {
	            return new FrameBodyTCON(file, parent);
	        } else if ("TCOP".equals(identifierString)) {
	            return new FrameBodyTCOP(file, parent);
	        } else if ("TDAT".equals(identifierString)) {
	            return new FrameBodyTDAT(file, parent); // Deprecated
	        } else if ("TDEN".equals(identifierString)) {
	            return new FrameBodyTDEN(file, parent);
	        } else if ("TDLY".equals(identifierString)) {
	            return new FrameBodyTDLY(file, parent);
	        } else if ("TDOR".equals(identifierString)) {
	            return new FrameBodyTDOR(file, parent);
	        } else if ("TDRC".equals(identifierString)) {
	            return new FrameBodyTDRC(file, parent);
	        } else if ("TDRL".equals(identifierString)) {
	            return new FrameBodyTDRL(file, parent);
	        } else if ("TDTG".equals(identifierString)) {
	            return new FrameBodyTDTG(file, parent);
	        } else if ("TENC".equals(identifierString)) {
	            return new FrameBodyTENC(file, parent);
	        } else if ("TEXT".equals(identifierString)) {
	            return new FrameBodyTEXT(file, parent);
	        } else if ("TFLT".equals(identifierString)) {
	            return new FrameBodyTFLT(file, parent);
	        } else if ("TIME".equals(identifierString)) {
	            return new FrameBodyTIME(file, parent); // Deprecated
	        } else if ("TIPL".equals(identifierString)) {
	            return new FrameBodyTIPL(file, parent);
	        } else if ("TIT1".equals(identifierString)) {
	            return new FrameBodyTIT1(file, parent);
	        } else if ("TIT2".equals(identifierString)) {
	            return new FrameBodyTIT2(file, parent);
	        } else if ("TIT3".equals(identifierString)) {
	            return new FrameBodyTIT3(file, parent);
	        } else if ("TKEY".equals(identifierString)) {
	            return new FrameBodyTKEY(file, parent);
	        } else if ("TLAN".equals(identifierString)) {
	            return new FrameBodyTLAN(file, parent);
	        } else if ("TLEN".equals(identifierString)) {
	            return new FrameBodyTLEN(file, parent);
	        } else if ("TMCL".equals(identifierString)) {
	            return new FrameBodyTMCL(file, parent);
	        } else if ("TMED".equals(identifierString)) {
	            return new FrameBodyTMED(file, parent);
	        } else if ("TMOO".equals(identifierString)) {
	            return new FrameBodyTMOO(file, parent);
	        } else if ("TOAL".equals(identifierString)) {
	            return new FrameBodyTOAL(file, parent);
	        } else if ("TOFN".equals(identifierString)) {
	            return new FrameBodyTOFN(file, parent);
	        } else if ("TOLY".equals(identifierString)) {
	            return new FrameBodyTOLY(file, parent);
	        } else if ("TOPE".equals(identifierString)) {
	            return new FrameBodyTOPE(file, parent);
	        } else if ("TORY".equals(identifierString)) {
	            return new FrameBodyTORY(file, parent); // Deprecated
	        } else if ("TOWN".equals(identifierString)) {
	            return new FrameBodyTOWN(file, parent);
	        } else if ("TPE1".equals(identifierString)) {
	            return new FrameBodyTPE1(file, parent);
	        } else if ("TPE2".equals(identifierString)) {
	            return new FrameBodyTPE2(file, parent);
	        } else if ("TPE3".equals(identifierString)) {
	            return new FrameBodyTPE3(file, parent);
	        } else if ("TPE4".equals(identifierString)) {
	            return new FrameBodyTPE4(file, parent);
	        } else if ("TPOS".equals(identifierString)) {
	            return new FrameBodyTPOS(file, parent);
	        } else if ("TPRO".equals(identifierString)) {
	            return new FrameBodyTPRO(file, parent);
	        } else if ("TPUB".equals(identifierString)) {
	            return new FrameBodyTPUB(file, parent);
	        } else if ("TRCK".equals(identifierString)) {
	            return new FrameBodyTRCK(file, parent);
	        } else if ("TRDA".equals(identifierString)) {
	            return new FrameBodyTRDA(file, parent); // Deprecated
	        } else if ("TRSN".equals(identifierString)) {
	            return new FrameBodyTRSN(file, parent);
	        } else if ("TRSO".equals(identifierString)) {
	            return new FrameBodyTRSO(file, parent);
	        } else if ("TSIZ".equals(identifierString)) {
	            return new FrameBodyTSIZ(file, parent); // Deprecated
	        } else if ("TSOA".equals(identifierString)) {
	            return new FrameBodyTSOA(file, parent);
	        } else if ("TSOP".equals(identifierString)) {
	            return new FrameBodyTSOP(file, parent);
	        } else if ("TSOT".equals(identifierString)) {
	            return new FrameBodyTSOT(file, parent);
	        } else if ("TSRC".equals(identifierString)) {
	            return new FrameBodyTSRC(file, parent);
	        } else if ("TSSE".equals(identifierString)) {
	            return new FrameBodyTSSE(file, parent);
	        } else if ("TSST".equals(identifierString)) {
	            return new FrameBodyTSST(file, parent);
	        } else if ("TXXX".equals(identifierString)) {
	            return new FrameBodyTXXX(file, parent);
	        } else if ("TYER".equals(identifierString)) {
	            return new FrameBodyTYER(file, parent); // Deprecated
	        } else if ("UFID".equals(identifierString)) {
	            return new FrameBodyUFID(file, parent);
	        } else if ("USLT".equals(identifierString)) {
	            return new FrameBodyUSLT(file, parent);
	        } else if ("WCOM".equals(identifierString)) {
	            return new FrameBodyWCOM(file, parent);
	        } else if ("WCOP".equals(identifierString)) {
	            return new FrameBodyWCOP(file, parent);
	        } else if ("WOAF".equals(identifierString)) {
	            return new FrameBodyWOAF(file, parent);
	        } else if ("WOAR".equals(identifierString)) {
	            return new FrameBodyWOAR(file, parent);
	        } else if ("WOAS".equals(identifierString)) {
	            return new FrameBodyWOAS(file, parent);
	        } else if ("WORS".equals(identifierString)) {
	            return new FrameBodyWORS(file, parent);
	        } else if ("WPAY".equals(identifierString)) {
	            return new FrameBodyWPAY(file, parent);
	        } else if ("WPUB".equals(identifierString)) {
	            return new FrameBodyWPUB(file, parent);
	        } else if ("WXXX".equals(identifierString)) {
	            return new FrameBodyWXXX(file, parent);
	        } else {
	            return new FrameBodyUnsupported(file, parent);
	        }
        } catch (InvalidTagException e) {
        	return null;
        } catch (IOException e) {
        	return null;
        }
	}

	public String toString() {
		return identifierString;
	}
	
	public int length() {
		return identifierString.length();
	}
	
	static public boolean isValidID3v2FrameIdentifier(byte[] buf) {
        byte character;

        for (int i = 0; i < buf.length; i++) {
            character = buf[i];
            if (character >= 'A' && character <= 'Z' || (character >= '0' && character <= '9')) {
                // nothing
            } else {
                return false;
            }
        }
        
        return true;
	}

	static public boolean isValidID3v2FrameIdentifier(String buf) {
        char character;

        for (int i = 0; i < buf.length(); i++) {
            character = buf.charAt(i);
            if (character >= 'A' && character <= 'Z' || (character >= '0' && character <= '9')) {
                // nothing
            } else {
                return false;
            }
        }
        
        return true;
	}
	
	public boolean isValidID3v2FrameIdentifier() {
		return isValidID3v2FrameIdentifier(identifier);
	}
}