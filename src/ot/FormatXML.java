package oneType;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.jar.*;

public class FormatXML extends PooledInst {
// pooled Instance
private static  int pooledCache = 3;
private static int instFactId = -1;

final protected static int XPREINIT = -2;
final protected static int XFAILURE = -1;
final protected static int XENDLABELCONSUMED = -2;
final protected static int XQUESTIONMARK = 3;
final protected static int XEXCLAMATION = 4;
final protected static int XDATALESSLABEL = 5;
final protected static int XMINUSSIX = -6;
final protected static int XINITIALIZE = -7;
final protected static int XSPACE = 3;
static final protected char labelBeg = '<';
static final protected char labelEnd = '>';
static final protected char labelSig = '/';
static final protected char labelQue = '?';
static final protected char labelCom = '!';
static final protected char labelEqu = '=';
static final protected char labelDQt = '"';
static final protected char labelQt = '\'';
static final protected char labelSpc = ' ';
static long totaldefault = 9999;
// Data areas
protected InputStream is;
protected StringReader strReader;
protected StringBuilder strBuilder;
protected BigBean sBigKB;
protected StringBean sBean;
protected URL myUrl;
protected Reader xinRead;
protected FileInputStream file;
protected JarFile jarF;
protected JarEntry jent;
protected byte byteA[];
protected char[] xLine;
protected char miniBuf[] = new char[20];
protected Encryption encryption;
protected String fName;
protected boolean outboundEncoding;
protected boolean useTypes;
protected boolean useAttributes;
protected boolean useTags;
protected boolean useCollTags;
protected boolean trackLabels;
protected boolean allowEncryption = true;
protected boolean noAmpData;
protected String encodeStr;
protected boolean persistDefault = true;
protected OT nukePass;
protected OT listLabels;
protected OT nukeComments = new OT();
protected int nukeCode;
protected int nukeEType;
protected int pos;
protected long depth;
protected long xinCnt;
protected int xSize; // X buffer Size
protected int xPos; // X current pos
protected int xChrI; // X current char
protected char xChr;
protected int xPrevChrI; // X previous char
protected char xPrevChr;
protected int xPeekChrI = XPREINIT; // X current char
protected char xPeekChr;
protected int xPeekPeekChrI = XPREINIT; // X current char
protected char xPeekPeekChr;
protected boolean eTypeFound;
// Water mark
protected long refDefault;
protected long curDefault;
protected String charSet;
protected boolean useJson;
protected boolean extReference;
protected OT docType;
protected OT externalComments = new OT();
protected boolean hasComments;

public OT getDocType() {
	return docType;
}

static synchronized long getDefaultTag() {
	return totaldefault--;
}

/**
 * Get use JSON instead of XML
 *
 * @return
 */
public boolean getUseJson() {
	return useJson;
}

/**
 * Set use JSON instead of XML
 *
 * @param rUseJson
 */
public void setUseJson(boolean rUseJson) {
	useJson = rUseJson;
}

public void setCharSet(String cs) {
	charSet = cs;
}

public String getCharSet() {
	return charSet;
}

public void setURL(URL url) {
	myUrl = url;
}

public URL getURL() {
	return myUrl;
}

public void setInputStream(InputStream x) {
	is = x;
}

public InputStream getInputStream() {
	return is;
}

public void setEncodeStr(String x) {
	encodeStr = x;
}

public String getEncodeStr() {
	return encodeStr;
}

public void setExtReference(boolean extReference) {
	this.extReference = extReference;
}

public boolean getExtReference() {
	return extReference;
}

public void setOutboundEncoding(boolean x) {
	outboundEncoding = x;
}

public boolean getOutboundEncoding() {
	return outboundEncoding;
}

public void setStringReader(StringReader x) {
	strReader = x;
}

public StringReader getStringReader() {
	return strReader;
}

public void setStringBuilder(StringBuilder x) {
	strBuilder = x;
}

public StringBuilder getStringBuilder() {
	return strBuilder;
}

/**
 * Set use tags for OType identification. When parsing add a tag to every OType. On explosion add
 * the tag to the output. The default is false.
 *
 * @param useTags
 */
public void setUseTags(boolean useTags) {
	this.useTags = useTags;
}

/**
 * Get use tags for OType identification. When parsing add a tag to every OType. On explosion add
 * the tag to the output. The default is false.
 *
 * @return use tags
 */
public boolean getUseTags() {
	return useTags;
}

/**
 * set use collection tags. When parsing a tag is always added if the value is or is within a
 * collection. The default is false
 *
 * @param useCollTags
 */
public void setUseCollTags(boolean useCollTags) {
	this.useCollTags = useCollTags;
}

/**
 * Get use collection tags. When parsing a tag is always added if the value is or is within a
 * collection
 *
 * @return true if using collection tags
 */
public boolean getUseCollTags() {
	return useCollTags;
}

public boolean getTrackLabels() {
	return trackLabels;
}

public void setTrackLabels(boolean x) {
	trackLabels = x;
}

public OT getListLabels() {
	return listLabels;
}

public void setListLabels(OT x) {
	listLabels = x;
}

/**
 * Set use types. When parsing add a type if the type is not string. Default is false
 * @param useTypes
 */
public void setUseTypes(boolean useTypes) {
	this.useTypes = useTypes;
}

/**
 * Get use types. When parsing add a type if the type type is not the default string. Default is
 * false.
 * @return use types
 */
public boolean getUseTypes() {
	return useTypes;
}

/**
 * Set use attributes Override and display all attributes good for diagnostic display. Default is
 * false
 * @param useAttributes
 */
public void setUseAttributes(boolean useAttributes) {
	this.useAttributes = useAttributes;
}

/**
 * Get use attributes Override and display all attributes good for diagnostic display. Default is
 * false
 *
 * @return use attributes
 */
public boolean getUseAttributes() {
	return useAttributes;
}

public void setNoAmpData(boolean x) {
	noAmpData = x;
}

public boolean getNoAmpsData() {
	return noAmpData;
}

/**
 * Set persist default. On conversion set up default tags. Default is true
 *
 * @param persistDefault
 */
public void setPersistDefault(boolean persistDefault) {
	this.persistDefault = persistDefault;
}

/**
 * Set persist default. On conversion set up default tags. Default is true.
 * @return persist default
 */
public boolean getPeristDefault() {
	return persistDefault;
}

/**
 * Get file name
 * @return
 */
public String getFile() {
	return fName;
}

/**
 * Set file name
 * @param fileName
 */
public void setFile(String fileName) {
	fName = fileName;
}

public void setAllowEncryption(boolean x) {
	allowEncryption = x;
}

public boolean getAllowEncryption() {
	return allowEncryption;
}

public FormatXML() {
}

/* a noname translate */
public FormatXML(boolean useTag) {
	useTags = useTag;
}

public FormatXML(boolean useTag,boolean useTypes) {
	useTags = useTag;
	this.useTypes = useTypes;
}

/**
 *
 * @param useTag - add tag to OType
 * @param useTypes - add type to output useful when a label has not been assigned that has the type
 * @param useAttrib - override and display all attributes good for diagnostic display
 * @param persistDefault - add selected default marker
 * @param useCollTags - add tags to collections
 */
final public void setUsage(boolean useTag,boolean useTypes,boolean useAttrib,boolean persistDefault,
	boolean useCollTags) {
	useTags = useTag;
	this.useTypes = useTypes;
	useAttributes = useAttrib;
	this.persistDefault = persistDefault;
	this.useCollTags = useCollTags;
}

/**
 *
 * @param useTag
 * @param useTypes
 * @param useAttrib
 * @param saveDefault
 * @param useCollTags
 */
public FormatXML(boolean useTag,boolean useTypes,boolean useAttrib,boolean saveDefault,
	boolean useCollTags) {
	setUsage(useTag,useTypes,useAttrib,saveDefault,useCollTags);
}

public FormatXML(StringReader str) {
	strReader = str;
}

public FormatXML(StringBuilder str) {
	strBuilder = str;
}

public FormatXML(String nugName) {
	fName = nugName;
}

public FormatXML(URL base,String nugName) {
	fName = nugName;
	try {
		myUrl = new URL(base,nugName);
	} catch(MalformedURLException e) {
		myUrl = null;
	}
}

public String toEntireXString(OT aOT,boolean pretty) {
	return toEntireXString(aOT,pretty,false);
}

public String toEntireXString(OT aOT,boolean pretty,boolean strip) {
	return toEntireXKB(aOT,pretty,strip).toString();
}

public StringBuilder toEntireXKB(OT aOT,boolean pretty) {
	return toEntireXKB(aOT,pretty,false);
}

public StringBuilder toEntireXKB(OT aOT,boolean pretty,boolean strip) {
	if(aOT != null) {
		xHead(aOT,pretty,null);
		xSplatKB(aOT,0,pretty,strip);
	}
	clear();
	return sBigKB.sb;
}

public int xWrite(String fn,OT wme) {
	return xWrite(fn,wme,false,null);
}

public int xWrite(String fn,OT wme,boolean pretty) {
	return xWrite(fn,wme,pretty,null);
}

/**
 * Write to a new file by default
 *
 * @param fn
 * @param wme
 * @param pretty
 * @param comment
 * @return
 */
public int xWrite(String fn,OT wme,boolean pretty,String comment) {
	return xWrite(fn,wme,pretty,false,comment);
}

public int xWrite(String fn,OT wme,boolean pretty,boolean append,String comment) {
	int r = 0;
	int level = 0;

	// Default xml
	fName = fn;
	if(!fName.contains(".")) {
		fName += ".xml";
	}
	OT xml, encode = null;
	int index0, index1;
	File pFile;
	FileOutputStream f = null;
	OutputStreamWriter hosw;
	BufferedWriter osw;
	String s, path;
	BigBean cSBean;
	// Garbage in
	if(fn == null || wme == null) {
		return r;
	}
	if(append) {
		try {
			f = new FileOutputStream(fName,append);
		} catch(FileNotFoundException fnfe) {
			if(OTNSp.getDebug()) {
				OTNSp.buzz("FormatXML","Attempt to Create new File: " + fName);
				OTNSp.buzz(fnfe.getMessage());
			}
		}
	}
	try {
		// We may be appending
		if(f == null) {
			f = new FileOutputStream(fName);
		}
		if((xml = wme.getIA(lxml)) != null) {
			encode = xml.getIA(lencoding);
		}
		if(encode != null) {
			hosw = new OutputStreamWriter(f,encode.eS());
		} else if(charSet != null) {
			hosw = new OutputStreamWriter(f,charSet);
			if(comment == null) {
				comment = "<?xml version=1.0 encoding=\"" + charSet + "\"?>";
			}
		} else {
			hosw = new OutputStreamWriter(f);
		}
		osw = new BufferedWriter(hosw);
		if(!useJson) {
			pos = 0;
			// Write a comment
			if(comment != null) {
				osw.write(comment,0,comment.length());
				if(pretty) {
					s = "\n";
					osw.write(s,0,s.length());
				}
			}
			xHead(wme,pretty,osw);
			// Bing
			xSplatOSW(wme,level,pretty,false,osw);
			if(pretty) {
				s = "\n";
				osw.write(s,0,s.length());
			}
		} else {
			// Cheap and dirty json for now parse if really use later
			cSBean = BigBean.getInst();
			toJsonStr(wme,cSBean.sb);
			osw.write(cSBean.sb.toString());
			cSBean.release();
		}
		osw.close();
	} catch(IOException e) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz(e.getMessage());
		}
		r = XFAILURE;
		// Create directory if does not exist
		index0 = fName.lastIndexOf("\\");
		index1 = fName.lastIndexOf("/");
		if(index1 > index0) {
			index0 = index1;
		}
		if(index0 > -1) {
			path = fName.substring(0,index0);
			pFile = new File(path);
			if(!pFile.exists()) {
				if(pFile.mkdirs()) {
					xWrite(fn,wme,pretty,append,comment);
				}
			}
		}
	}
	// cleanup
	clear();
	fName = null;
	return r;
}

public int writeString(String fn,String wme) {
	int r = 0;
	fName = fn;
	FileOutputStream f = null;
	try {
		f = new FileOutputStream(fName);
	} catch(IOException e) {
		r = XFAILURE;
	}

	if(r == 0) {
		DataOutputStream out = new DataOutputStream(f);
		try {

			out.writeChars(wme);
		} catch(IOException e) {
			r = XFAILURE;
		}

	}

	// cleanup
	try {
		if(f != null) {
			f.close();
		}
	} catch(IOException e) {
		r = XENDLABELCONSUMED;
	}
	return r;
}

/**
 * parse the current position of the current line
 *
 * @param inColl
 * @param parent
 * @return
 */
public OT xParse(boolean inColl,OT parent) {
	OT nukeIn;
	OT nukeJr;
	int r;
	int sPos;
	OT el;
	String ns;
	OT cTellMe;
	OTCur ncNukeIn;
	boolean dash1, dash2, done, docT;
	boolean nukeInColl;
	// clear passable parameters
	nukeCode = 0;
	depth++;
	nukeEType = 0;
	eTypeFound = false;
	// Put any exclaimations you find into a bag	
	nukeComments.delAll();
	while((r = xFindLabel()) == XEXCLAMATION) {
	}
	// Find the default type
	if(nukePass != null && nukePass.getLabel() != null) {
		nukeEType = nukePass.getPA(ldefType,OType.OTSTR);
	}
	switch(r) {
		// Failure
		case XFAILURE:
			nukeCode = r;
			depth--;
			nukeIn = nukePass;
			nukePass = null;
			return nukeIn;
		// Ending label is consumed
		case XENDLABELCONSUMED:
			nukeCode = r;
			depth--;
			nukeIn = nukePass;
			nukePass = null;
			if(hasComments) {
				xAddExclamations(nukeIn);
			}
			return nukeIn;
		// Question mark
		case XQUESTIONMARK:
			nukeCode = XQUESTIONMARK;
			break;
		// Exclamation
		case XEXCLAMATION:
			xPos = 0;
			dash1 = dash2 = done = docT = false;
			clrXLine();
			putXLine('<');
			putXLine('!');
			// Could have multiple multiple stuff here
			while(!done) {
				if((xChrI = xGet()) == XFAILURE) {
					depth--;
					nukePass = null;
					return null;
				} else {
					switch(xChr) {
						case '-':
							if(!dash1) {
								dash1 = true;
							} else if(dash1 && !dash2) {
								dash2 = true;
							} else {
								dash1 = true;
								dash2 = false;
							}
							break;
						case labelEnd:
							// Check for Document type in HTML
							if((xLine[0] == 'D' && xLine[1] == 'O' && xLine[2] == 'C'
								&& xLine[3] == 'T')) {
								docT = true;
								done = true;
							} else if(dash1 && dash2) {
								done = true;
							}
							break;
						// This destroys dash count
						default:
							dash1 = dash2 = false;
							break;
					}
					putXLine(xChr);
				}
			}
			// Return a comment
			nukePass = new OT(new String(xLine,0,xPos),((docT) ? lDOCTYPE : lComment));
			nukeCode = XEXCLAMATION;
			// Jump start to next letter?
			xChrI = xGet();
			depth--;
			nukeIn = nukePass;
			nukePass = null;
			return nukeIn;
		// dataless label
		case XDATALESSLABEL:
			nukePass.aType(null,nukeEType);
			depth--;
			nukeIn = nukePass;
			nukePass = null;
			// Everybody gets retagged UGGG except rows......
			if(useTags || (useCollTags && (inColl || nukeIn.isColl()))) {
				nukeIn.getTag();
			}
			return nukeIn;
	}
	// At this point we can assume we have a OT to work with,
	// Labeled or not labeled
	nukeIn = (nukePass == null) ? new OT() : nukePass;
	nukePass = null;
	// Can't do comments to here because could belong in this if found end label
	if(hasComments) {
		xAddExclamations(parent);
	}
	while((xChrI = xFindAttrib(nukeIn)) == 0) {
	}
	// Everybody gets retagged UGGG except rows...... and specifically not collections
	nukeInColl = nukeIn.isColl();
	if(useTags || (useCollTags && (inColl || nukeInColl))) {
		nukeIn.getTag();
	}
	// dataless label
	if(xChrI == XDATALESSLABEL) {
		nukeIn.aType(null,nukeEType);
		xGet();
		depth--;
		return nukeIn;
	} else if(xChrI != XMINUSSIX) {
		nukeCode = xChrI;
		depth--;
		return nukeIn;
	}
	// find the data
	xPos = 0;
	sPos = 0;
	// take care of xmlisms
	xDPack(labelBeg);
	// Please no pretty
	while(xPos > 0 && (Character.isWhitespace(xLine[xPos - 1]) || Character.isSpaceChar(
		xLine[xPos - 1]))) {
		xPos--;
	}
	// Slame off the line
	if(xPos > 0 && sPos < xPos) {
		// Update the etype which is troubling but joining name spaces makes it cool
		if((el = nukeIn.getLabel()) != null) {
			// Up top we already the type so check on attribute 
			if(eTypeFound && nukeEType != OType.OTSTR && el.getPA(ldefType) == null) {
				// Seed the label for look aheads
				el.setPA(new OT(nukeEType,ldefType));
			}
		}
		// Am I a namespace ? then I am defining labels
		if(nukeIn.getLabel() == lLt) {
			// Ha Ha we always blow you away
			nukeIn = OTNSp.really(new String(xLine,sPos,xPos));
			// Once a tell me always a tell me
			ncNukeIn = OTCur.getInst(nukeIn);
			while((cTellMe = nukeIn.getNext()) != null) {
				if(cTellMe.getLabel() != lPersAt) {
					nukeIn.del();
				}
			}
			ncNukeIn.release();
		} else if(nukeEType != OType.OTOBJ) {
			nukeIn.aType(sBean.sb,xLine,sPos,xPos,nukeEType);
			//SOA Encryption
			if(allowEncryption && nukeEType == OType.OTSTR
				&& nukeIn.getIA(lencryption,false)) {
				if(encryption == null) {
					encryption = new Encryption();
				}
				ns = nukeIn.eS();
				if(ns.length() > 0) {
					nukeIn.a(encryption.decrypt(ns));
				}
			}
		} else {
			// only objects we handle now are labels
			nukeIn.aType((Object) OTNSp.really(new String(xLine,sPos,xPos)),OType.OTOBJ);
		}
	} else {
		// blank lines or null data
		// No more chars by default
		nukeIn.aType(null,nukeEType);
	}
	while(nukeCode >= 0) {
		nukeJr = xParse(nukeInColl,nukeIn);
		// Cannot have multiple attributes the same
		if(nukeCode >= 0) {
			// Cannot have multiple attributes the same
			if(nukeCode > 0) {
				nukeIn.setIA(nukeJr);
			} else if(nukeJr.getLabel() == lPersAt) {
				// Tell me is really pointing into the label
				nukeIn.setPersAt(nukeJr);
			} else {
				nukeIn.insTail(nukeJr);
			}
		} // if not a tellme
	} // while
	// Must be back to zero
	nukeCode = 0;
	depth--;
	nukePass = null;
	return nukeIn;
}

public int xFindLabel() {
	String label = null;
	int rCode = 0;
	OT llabel;
	if(xChr != labelBeg && (xChrI = xGet(labelBeg)) == XFAILURE) {
		return XFAILURE;
	}
	// Setup as first character checking for label end
	xPos = 0;
	if((xChrI = xGet()) == XFAILURE) {
		return XFAILURE;
	}
	if(xChr == labelSig) {
		rCode = XENDLABELCONSUMED;
		if((xChrI = xGet()) == XFAILURE) {
			return XFAILURE;
		}
	}
	// Question label ?
	if(xChr == labelQue) {
		// dump the ?
		if((xChrI = xGet()) == XFAILURE) {
			return XFAILURE;
		}
		rCode = XQUESTIONMARK;
	}
	// Comment label !
	if(xChr == labelCom) {
		return (xHdlExclamations() == null) ? XFAILURE : XEXCLAMATION;
	}
	// First check for empty labels
	if(xChr != labelEnd) {
		do {
			putXLine(xChr);
			if(xChr != labelEnd) {
				if((xChrI = xGet()) == XFAILURE) {
					return XFAILURE;
				}
			}
		} while(xChr != labelEnd && !Character.isWhitespace(xChr) && xChr != labelSig && xChr
			!= labelQue);
	}
	// find begining of attribute
	if(Character.isSpaceChar(xChr)) {
		while(xChrI != XFAILURE && (Character.isSpaceChar(xChr) || Character.isWhitespace(xChr))) {
			xChrI = xGet();
		}
	}
	// Found a labelSignal ... than I am just a label
	if(xChr == labelSig) {
		rCode = XDATALESSLABEL;
		xGet();
	}
	// ending labels are simply consumed
	if(rCode != XENDLABELCONSUMED) {
		if(xPos >= 1) {
			// Assuming that line is updated past label
			try {
				label = new String(xLine,0,xPos);
			} catch(StringIndexOutOfBoundsException sioobe) {
				System.out.println(sioobe.getLocalizedMessage());
				return XFAILURE;
			}
		} else if(nukePass == null) {
			nukePass = new OT(OT.nst);
		} else {
			nukePass.setLabel(null);
		}
		if(label != null) {
			//SOA label reference
			llabel = OTNSp.really(label);
			if(nukePass == null) {
				nukePass = new OT(llabel);
			} else {
				nukePass.setLabel(llabel);
			}
			if(trackLabels) {
				if(listLabels == null) {
					listLabels = new OT(llabels);
				}
				if(listLabels.getL(nukePass.getLabel()) == null) {
					listLabels.insTail(nukePass.cloneNoKids());
				}
			}
		}
	}
	return rCode;
}

/**
 * of current position of current line
 *
 * @param nuke
 * @return
 */
public int xFindAttrib(OT nuke) {
	String label;
	int rCode = 0;
	String theData;
	OT myLabel;
	OT att;
	// find begining of attribute
	while(xChrI != XFAILURE && (Character.isSpaceChar(xChr) || Character.isWhitespace(xChr))) {
		xChrI = xGet();
	}
	// Question mark is attempting to close the opening statement
	if(xChrI == XFAILURE) {
		return XFAILURE;
	} else if(xChr == labelQue) {
		return XQUESTIONMARK;
	} else if(xChr == labelSig) {
		return XDATALESSLABEL;
	} else if(xChr == labelEnd) {
		return XMINUSSIX;
	}
	// First postion of label
	xPos = 0;
	putXLine(xChr);
	if((xChrI = xPack(labelEqu)) == XFAILURE) {
		return XFAILURE;
	}
	// Back off any white space
	int tic = xPos - 1;
	while(xPos > 0 && (Character.isSpaceChar(xLine[tic])
		|| Character.isWhitespace(xLine[tic]))) {
		xPos--;
		tic--;
	}
	// Failed label validation
	if(xPos <= 0) {
		return XFAILURE;
	}
	// Assuming that line is updated past label
	try {
		label = new String(xLine,0,xPos);
	} catch(StringIndexOutOfBoundsException sioobe) {
		return XFAILURE;
	}
	// find first no what space
	while((xChrI = xGet()) != XFAILURE && (Character.isSpaceChar(xChr)
		|| Character.isWhitespace(xChr))) {
	}
	if(xChrI != XFAILURE) {
		if(xChr == labelDQt || xChr == labelQt) {
			xPos = 0;
			if((xChrI = xDPack(xChr)) == XFAILURE) {
				return XFAILURE;
			}
			// March past double quote
			xGet();
		} else {
			// End with a space
			xPos = 0;
			putXLine(xChr);
			if((xChrI = xPack(labelSpc,labelEnd)) == XFAILURE) {
				return XFAILURE;
			}
		}
		try {
			theData = new String(xLine,0,xPos);
		} catch(StringIndexOutOfBoundsException sioobe) {
			System.out.println(sioobe.getMessage());
			return XFAILURE;
		}
		// Finally make the attribute
		myLabel = OTNSp.really(label);
		if(myLabel != lnt) {
			att = new OT(theData,myLabel);
			// Since could possible be overwriting but by definition labels are unique
			nuke.setIA(att);
			// Reading in default or tag
			if(persistDefault) {
				if(myLabel == ldefault) {
					refDefault = att.eL();
					curDefault = OTNSp.getCollTag();
					att.aL(curDefault);
				} else if(myLabel == ltg) {
					if(att.eL() == refDefault) {
						att.aL(curDefault);
						curDefault = refDefault = -1;
					}
				}
			}
		} else {
			nukeEType = Integer.parseInt(theData);
			eTypeFound = true;
		}
	} else {
		rCode = XFAILURE;
	}
	//cleanup
	return rCode;
}

public int xPack(char what) {
	while((xChrI = xGet()) != XFAILURE && xChr != what) {
		putXLine(xChr);
	}
	// cleanup
	return xChrI;
}

public int xPack(char what,char what2) {
	while((xChrI = xGet()) != XFAILURE && xChr != what && xChr != what2) {
		putXLine(xChr);
	}
	// cleanup
	return xChrI;
}

public boolean scriptEnd() {
	boolean r = false;
	if(xChrI == '>' && xPos > 7) {
		if(xLine[xPos - 1] == 't' && xLine[xPos - 2] == 'p' && xLine[xPos - 3] == 'i' && xLine[xPos - 4]
			== 'r' && xLine[xPos - 5] == 'c' && xLine[xPos - 6] == 's' && xLine[xPos - 7] == '/'
			&& xLine[xPos - 8] == '<') {
			r = true;
			xPos -= 8;
			xLine[xPos] = 0;
		} else if(xLine[xPos - 1] == 'e' && xLine[xPos - 2] == 'l' && xLine[xPos - 3] == 'y'
			&& xLine[xPos - 4] == 't' && xLine[xPos - 5] == 's' && xLine[xPos - 6] == '/' && xLine[xPos
			- 7] == '<') {
			r = true;
			xPos -= 7;
			xLine[xPos] = 0;
		}
	}
	return r;
}

/**
 * Turn a string from including xml special characters to a string without special characters
 *
 * @param what
 * @return
 */
public int xDPack(char what) {
	int d;
	String bip;
	int codepoint;
	char c;
	while((xChrI = xGet()) != XFAILURE && xChr != what) {
		if(xChr == '&') {
			d = 0;
			while(d < 15 && (xChrI = xGet()) != XFAILURE) {
				if(xChr == ';') {
					bip = new String(miniBuf,0,d);
					if(bip.equalsIgnoreCase("lt")) {
						putXLine('<');
					} else if(bip.equalsIgnoreCase("gt")) {
						putXLine('>');
					} else if(bip.equalsIgnoreCase("amp")) {
						putXLine('&');
					} else if(bip.equalsIgnoreCase("quot")) {
						putXLine('"');
					} else if(bip.equalsIgnoreCase("apos")) {
						putXLine('\'');
					} // a nonbreaking space means they must stay together
					else if(bip.equalsIgnoreCase("nbsp")) {
						putXLine(' ');
					} // Trade mark skip it
					else if(bip.equals("#8482")) {
					} else // Check for decimal
					{
						if(bip.length() > 1 && bip.charAt(0) == '#') {
							try {
								// Hex
								if(bip.charAt(1) == 'x' || bip.charAt(1) == 'X') {
									codepoint = Integer.decode("0X" + bip.substring(2));
								} else {// Decimal
									codepoint = Integer.decode(bip.substring(1));
								}
								c = (char) codepoint;
								putXLine(c);
							} catch(NumberFormatException nfe) {
								if(OTNSp.getDebug()) {
									OTNSp.buzz(nfe.getMessage());
								}
							}
						} else {
							if(OTNSp.getDebug()) {
								OTNSp.buzz("Unknown &Combination " + bip);
							}
						}
					}
					d = 16;
				} else {
					miniBuf[d++] = xChr;
				}
			}
		} else {
			putXLine(xChr);
		}
	}

	return xChrI;
}

public int xGet(char what) {
	while((xChrI = xGet()) != XFAILURE && xChr != what) {
	}
	return xChrI;
}

public int xGet() {
	try {
		// Must prime with at least two chars
		if(xPeekPeekChrI == XPREINIT) {
			xPeekChrI = xinRead.read();
			xPeekChr = (char) xPeekChrI;
			xPeekPeekChrI = xinRead.read();
			xPeekPeekChr = (char) xPeekPeekChrI;
		}
		// Save for reference
		xPrevChrI = xChrI;
		xPrevChr = xChr;
		// Get from peek
		xChrI = xPeekChrI;
		xChr = xPeekChr;
		xinCnt++;
		xPeekChrI = xPeekPeekChrI;
		xPeekChr = xPeekPeekChr;
		// Get next characte
		if(xPeekPeekChrI != XFAILURE) {
			xPeekPeekChrI = xinRead.read();
			xPeekPeekChr = (char) xPeekPeekChrI;
		}
	} catch(IOException e) {
		xPeekPeekChrI = XFAILURE;
		xPeekPeekChr = 0;
	}
	return xChrI;
}

/**
 * read given from a jar file
 *
 * @param jer
 * @param je
 * @return
 */
public OT xRead(JarFile jer,JarEntry je) {
	clear();
	jarF = jer;
	jent = je;
	OT rCode = xRead();
	jarF = null;
	jent = null;
	return rCode;
}

/**
 * XML from a byte array
 *
 * @param byteArray
 * @param encodeFrom
 * @return
 */
public OT xRead(byte[] byteArray,String encodeFrom) {
	encodeStr = encodeFrom;
	OT rcode = xRead(byteArray);
	encodeStr = null;
	return rcode;
}

/**
 * XML from a byte array
 *
 * @param byteArray
 * @return
 */
public OT xRead(byte[] byteArray) {
	clear();
	byteA = byteArray;
	OT rcode = xRead();
	byteA = null;
	return rcode;
}

/**
 * Read XML from a file name
 *
 * @param s
 * @return
 */
public OT xRead(String s) {
	// Default to adding xml in no extension given
	if(!s.contains(".")) {
		s = s + ".xml";
	}
	return xRead(s,null);
}

/**
 * XML from a file name
 *
 * @param s
 * @param head
 * @return
 */
public OT xRead(String s,OT head) {
	clear();
	fName = s;
	OT rcode = xRead(head);
	fName = null;
	return rcode;
}

/**
 * Get the URL from a String
 *
 * @param u
 * @return
 */
public URL getURL(String u) {
	URL url = null;
	try {
		url = new URL(u);
	} catch(MalformedURLException mu) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz("URL Exception " + u + " " + mu.getMessage());
		}
	}
	return url;
}

/**
 * XML from a URL name
 *
 * @param url
 * @return
 */
public OT xRead(URL url) {
	if(url == null) {
		return new OT(-1);
	}
	myUrl = url;
	OT rcode = xRead();
	myUrl = null;
	return rcode;
}

/**
 * Reusing class implies clearing before reuse
 */
public void clear() {
	fName = null;
	myUrl = null;
	jarF = null;
	byteA = null;
	strReader = null;
	strBuilder = null;
	is = null;
	xinRead = null;
	depth = 0; //-1;
	refDefault = -1;
	curDefault = -1;
	charSet = null;
	// Any chance of clearing
	nukePass = null;
	listLabels = null;
	nukeComments.delAll();
	docType = null;
	externalComments.delAll();
	hasComments = false;
	xPeekPeekChrI = xPeekChrI = XPREINIT;
}

/**
 * XML from a file name
 *
 * @return
 */
public InputStream xSetStream() {
	try {
		if(fName != null) {
			file = new FileInputStream(fName);
			is = file;
		} else if(myUrl != null) {
			is = myUrl.openStream();
		} else if(jarF != null) {
			is = jarF.getInputStream(jent);
		} else if(byteA != null) {
			is = new ByteArrayInputStream(byteA);
		} else if(strReader != null) {
			xinRead = strReader;
		} else if(strBuilder != null) {
			xinRead = new StringReader(strBuilder.toString());
		}
	} // End of try
	catch(FileNotFoundException e) {
		is = null;
	} catch(IOException ie) {
		is = null;
	}
	return is;
}

public OT xRead(InputStream inputStream) {
	OT rCode;
	setInputStream(inputStream);
	rCode = xRead();
	setInputStream(null);
	return rCode;
}

public OT xRead(StringReader inputStream) {
	OT rCode;
	setStringReader(inputStream);
	rCode = xRead();
	setInputStream(null);
	return rCode;
}

public OT xRead(StringBuilder inputStream) {
	OT rCode;
	setStringBuilder(inputStream);
	rCode = xRead();
	setInputStream(null);
	return rCode;
}

public OT xRead() {
	return xRead((OT) null);
}

public OT xRead(OT head) {
	OT nuke = null;
	OT nukeJr;
	OT ecode;
	InputStreamReader isr = null;
	OT holdCom = null;
	//OT myHoldCom = 
	OT it;
	// Because of Null values force head tag
	if((useTags || useCollTags) && head != null) {
		head.getTag();
	}
	xSetStream();
	if(is != null || xinRead != null) {
		try {
			if(xinRead == null) {
				// set up reader each time
				if(encodeStr == null) {
					isr = new InputStreamReader(is);
				} else {
					try {
						isr = new InputStreamReader(is,encodeStr);
					} catch(UnsupportedEncodingException uee) {
						if(OTNSp.getDebug()) {
							OTNSp.buzz(uee.getMessage() + encodeStr);
						}
						return null;
					}
				}
				xinRead = new BufferedReader(isr);
			}
			if(!useJson) {
				xinCnt = 0;
				clrXLine();
				// passing a head in means using the head to fill out so really just
				// pass the head and it will know to create or not
				nukePass = head;
				nukeJr = xParse((nukePass == null) ? false : nukePass.isColl(),head);
				if(nukeCode >= 0) {
					do {
						if(nukeCode == 0) {
							// Finally found the header how could this not me null?
							if(nuke == null) {
								nuke = nukeJr;
							}
							// Add comments and other puke
							if(holdCom != null) {
								// You cant just replace because there are attributes in this guy
								it = holdCom.getInstAt().getHead();
								while(it != null) {
									nuke.setIA(it);
									it = holdCom.getInstAt().getNext();
								}
								holdCom = null;
							}
						} else {// We're adding a comment or ?xml
							if(nukeJr.getLabel() == lxml) {
								if((ecode = nukeJr.getIA(lencoding)) != null
									&& !ecode.eS().equalsIgnoreCase("UTF-8")) {
									//							if(OTNSp.getDebug())OTNSp.buzz("Attempt encoding:" + ecode.eS());
									// This is the stuff that dreams are made of
									try {
										xinRead.close();
										if(isr != null) {
											isr.close();
										}
										is.close();
										is = xSetStream();
										isr = new InputStreamReader(is,ecode.eS());
										xinRead = new BufferedReader(isr);
										xinRead.skip(xinCnt);
									} catch(IOException iooe) {
										if(OTNSp.getDebug()) {
											OTNSp.buzz("Encoding exception " + iooe.getMessage());
										}
										return null;
									} // Catch
								}
							} // if xml
							if(holdCom == null) {
								holdCom = new OT("envelope",lEnvelope);
							}
							holdCom.setIA(nukeJr);
						} // else
						nukeJr = xParse((nukePass == null) ? false : nukePass.isColl(),head);
					} while(nukeCode >= 0);
				} else {
					nuke = nukeJr;
				}
			} else {
				nuke = parseJson();
			}
			xinRead.close();
			if(isr != null) {
				isr.close();
			}
			if(is != null) {
				is.close();
			}
		} catch(IOException ioe) {
			OTNSp.buzz(ioe.getMessage());
			nuke = null;
		}
	}
	// cleanup
	clear();
	//	if(OTNSp.getDebug())OTNSp.buzz("FormatXML.xRead Time " + (System.currentTimeMillis() - startTime));
	return nuke;
}

public void clrXLine() {
	xPos = 0;
}

public void putXLine(char x) {
	chkXSize();
	xLine[xPos++] = x;
}
// Make sure the xline never runs dry

public void chkXSize() {
	if(xSize == 0) {
		xSize = 10000;
		xLine = new char[xSize];
	} else if(xPos >= (xSize - 2)) {
		xSize += 10000;
		char nxLine[] = new char[xSize];
		System.arraycopy(xLine,0,nxLine,0,xPos);
		xLine = nxLine;
	}
}

public void xAppend(StringBuilder sB,String gorp,BufferedWriter osw) {
	int l = gorp.length();
	char x;
	try {
		for(int i = 0;i < l;i++) {
			x = gorp.charAt(i);
			// clean little write for dipslay purposes
			if(noAmpData) {
				if(osw == null) {
					sB.append(x);
				} else {
					osw.write(x);
				}
			} else // Append
			{
				if(outboundEncoding && (int) x > 255) {
					if(osw == null) {
						sB.append("&#");
						sB.append((int) x);
						sB.append(";");
					} else {
						osw.write("&#" + (int) x + ";");
					}

				} else if(x == '&') {
					if(osw == null) {
						sB.append("&amp;");
					} else {
						osw.write("&amp;");
					}
				} else if(x == '\'') {
					if(osw == null) {
						sB.append("&apos;");
					} else {
						osw.write("&apos;");
					}
				} else if(x == '"') {
					if(osw == null) {
						sB.append("&quot;");
					} else {
						osw.write("&quot;");
					}
				} else if(x == '<') {
					if(osw == null) {
						sB.append("&lt;");
					} else {
						osw.write("&lt;");
					}
				} else if(x == '>') {
					if(osw == null) {
						sB.append("&gt;");
					} else {
						osw.write("&gt;");
					}
				} else if(osw == null) {
					sB.append(x);
				} else {
					osw.write(x);
				}
			}
		}
	} catch(java.io.IOException joi) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz(joi.getMessage());
		}
	}
}

public void xHead(OT aOT,boolean pretty,BufferedWriter osw) {
	OT x = aOT.getIA(lxml);
	OT c;
	OTCur ncx = OTCur.getInst();
	try {
		if(x != null) {
			// Bing by default reset him for now
			if(osw == null) {
				sBigKB.sb.setLength(0);
				sBigKB.sb.append("<?xml ");
			} else {
				osw.write("<?xml ");
			}
			ncx.setColl(x.getInstAt());
			while((c = ncx.getNextIgn()) != null) {
				if(osw == null) {
					sBigKB.sb.append(c.getLabel().eS());
					sBigKB.sb.append(" = ");
					sBigKB.sb.append("\"");
					sBigKB.sb.append(c.eS());
					sBigKB.sb.append("\" ");
				} else {
					osw.write(c.getLabel().eS() + " = " + "\"" + c.eS() + "\" ");
				}
			}
			if(osw == null) {
				sBigKB.sb.append("?>");
			} else {
				osw.write("?>");
			}
			if(pretty) {
				if(osw == null) {
					sBigKB.sb.append("\n");
				} else {
					osw.write("\n");
				}
			}
		}
	} catch(java.io.IOException jie) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz(jie.getMessage());
		}
	}

	// cleanup
	ncx.release();
}

// Test if encoding will work
public String tryEncodeing(String enc) {
	String x = null;
	ByteArrayInputStream dum;
	InputStreamReader disr;
	try {
		byte a[] = new byte[2];
		dum = new ByteArrayInputStream(a);
		disr = new InputStreamReader(dum,enc);
		disr.close();
		dum.close();
	} catch(UnsupportedEncodingException uee) {
		x = "Unsupported Host Code Page: " + uee.getMessage();
	} catch(IOException ie) {
		boolean deb = false;
		if(deb) {
			if(OTNSp.getDebug()) {
				OTNSp.buzz("FormatXML","Close failure");
			}
		}
	}
	// cleanup
	return x;
}

/**
 * parse for 1 XML label and associated value &param initial OType &param updated return code &param
 * parse String &return created OType
 *
 * @param nuke
 * @param rCode
 * @param y
 * @return
 */
public OT getXLevel(OT nuke,OT rCode,String y) {
	byteA = y.getBytes();
	InputStreamReader isr;
	OT ttt;
	if((is = xSetStream()) == null) {
		rCode.a(XINITIALIZE);
		return nuke;
	}
	try {
		// set up reader each time
		if(encodeStr == null) {
			isr = new InputStreamReader(is);
		} else {
			try {
				isr = new InputStreamReader(is,encodeStr);
			} catch(UnsupportedEncodingException uee) {
				rCode.a(XINITIALIZE);
				return nuke;
			}
		}
		xinRead = new BufferedReader(isr);
		xinCnt = 0;
		clrXLine();
		// Setup to run
		String data;
		rCode.a(0);
		long cnt = 0;
		int iEType = OT.OTSTR;
		int r;
		// Failure
		nukePass = nuke;
		if((r = xFindLabel()) == XFAILURE) {
			rCode.a(r);
			return nuke;
		} // Ending label is consumed
		else if(r == XENDLABELCONSUMED) {
			rCode.a(r);
			return nuke;
		} // Question mark
		else if(r == XQUESTIONMARK) {
			rCode.a(XQUESTIONMARK);
		} // Exclamation
		else if(r == XEXCLAMATION) {
			xPos = 0;
			xPack(labelEnd);
			data = new String(xLine,0,xPos);
			nuke.setLabel(lComment);
			nuke.a(data);
			rCode.a(XEXCLAMATION);
			return nuke;
		} // dataless label
		else if(r == XDATALESSLABEL) {
			nuke.a(OT.nst);
			return nuke;
		}

		// all possible attributes
		while((xChrI = xFindAttrib(nuke)) == 0) {
			cnt++;
		}
		// Add tag for persistent defaults only
		if(persistDefault) {
			nuke.getTag();
		}

		if(xChrI != XMINUSSIX) {
			rCode.a(xChrI);
			return nuke;
		} // dataless label
		else if(xChrI == XDATALESSLABEL) {
			nuke.a(OT.nst);
			xGet();
			return nuke;
		}

		// find the data
		xPos = 0;

		// take care of xmlisms
		xDPack(labelBeg);

		// Please no pretty
		while(xPos > 0 && (Character.isWhitespace(xLine[xPos - 1]) || Character.isSpaceChar(xLine[xPos
			- 1]))) {
			xPos--;
		}

		// Slame off the line
		if(xPos > 0) {
			data = new String(xLine,0,xPos);

			// Am I a namespace ? then I am defining labels
			if(nuke.getLabel() == lLt) {

				// Figure out the persistence
				nuke = OTNSp.really(data);
				//I might be redefining so delete anything that existed
				if((ttt = nuke.getL(OTNSp.lPersAt)) != null) {
					// Save the persistence
					ttt.delAll();
				}
				nuke.delAll();
				if(ttt != null) {
					nuke.insTail(ttt);
				}
			} else if(iEType != OType.OTOBJ) {
				nuke.aType(data,iEType);
			} else {
				// only objects we handle now are labels
				nuke.aO(OTNSp.really(data));
			}
		} // blank lines or null data
		else if(nuke.getLabel() == lPersAt) {
			nuke.aType(lChars,OType.OTOBJ);
		} else {
			nuke.aType(null,iEType);
		}
		xinRead.close();
		isr.close();
		is.close();
	} catch(IOException ioe) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz(ioe.getMessage());
		}
		rCode.a(XINITIALIZE);
	}
	// Revert to clean
	byteA = null;
	// cleanup
	return nuke;
}

/**
 * Write a string to the given file
 *
 * @param fn
 * @param s
 * @param append
 * @return
 */
public int xWrite(String fn,String s,boolean append) {
	int r = 0;
	fName = fn;
	if(OTNSp.getDebug()) {
		OTNSp.buzz("Writing " + fn);
	}
	FileOutputStream f = null;
	OutputStreamWriter hosw;
	BufferedWriter osw;
	// Garbage in garbage out
	if(fName == null || fName.equals(OT.nst) || s == null) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz("FormatXML","File Name or data is Null");
		}
		return r;
	}
	if(append) {
		try {
			f = new FileOutputStream(fName,append);
		} catch(FileNotFoundException fnfe) {
			if(OTNSp.getDebug()) {
				OTNSp.buzz("FormatXML " + fnfe.getLocalizedMessage() + " " + fName);
			}
		}
	}
	try {
		// We may be appending
		if(f == null) {
			f = new FileOutputStream(fName);
		}
		hosw = new OutputStreamWriter(f);
		osw = new BufferedWriter(hosw);
		// cleanup
		osw.write(s,0,s.length());
		osw.close();
	} catch(IOException e) {
		r = XFAILURE;
	}
	fName = null;
	return r;
}

/**
 * Write out XML
 *
 * @param nuke
 * @param level
 * @param pretty
 * @param strip
 */
public void xSplatKB(OT nuke,int level,boolean pretty,boolean strip) {
	OT nukeJr;
	int i;
	OTCur nukeJrc = OTCur.getInst();
	OTCur ncapp = null;
	OT nugData;
	int eType;
	OT lit, attr;
	String ns;
	Object obj;
	// We might recurse our way in which would be
	// a bad thing to overwrite
	if(level == 0) {
		sBigKB.sb.setLength(0);
	}
	// Indent at least this much
	if(pretty) {
		if(level > 0) {
			sBigKB.sb.append("\n");
		}
		for(i = 0;i < level * XSPACE;i++) {
			sBigKB.sb.append(" ");
		}
	}
	// Label
	sBigKB.sb.append("<");
	sBigKB.sb.append(nuke.eSLabel());
	// Convert back to labels
	eType = getEType(nuke);
	//icky icky icky add stuff for
	if((attr = nuke.getInstAt()) != null) {
		ncapp = OTCur.getInst(attr);
		while((lit = ncapp.getNextIgn()) != null) {
			if(useAttributes || (lit.getLabel() != lmr
				&& (useTags || (lit.getLabel() != ltg && lit.getLabel() != ldefault
				&& lit.getLabel() != ltgIn)))) {
				// Persist default tag only
				if(persistDefault) {
					if(lit.getLabel() == ldefault) {
						refDefault = lit.eL();
						curDefault = getDefaultTag();
						lit.aL(curDefault);
					} else if(lit.getLabel() == ltg) {
						if(lit.eL() == refDefault) {
							lit.aL(curDefault);
							refDefault = curDefault = -1;
						} else {
							lit = null;
						}
					} else if(lit.getLabel() == ltgIn || lit.getLabel() == ltgRf) {
						lit = null;
					}
				}
				if(lit != null) {
					sBigKB.sb.append(" ");
					sBigKB.sb.append(lit.getLabel().eS());
					sBigKB.sb.append("=\"");
					xAppend(sBigKB.sb,lit.eS(),null);
					sBigKB.sb.append("\"");
				}
			} // Good attribute
		} // while attributes
	}
	// add type for non strings
	if(useTypes && eType != OType.OTSTR && eType != OType.OTNON) {
		if(eType != OType.OTOBJ || ((obj = nuke.eO()) != null && obj.getClass().getName().equals(
			OT.OTCLASS) && ((OT) obj).getLabel() == OTNSp.lLt)) {
			sBigKB.sb.append(" ");
			sBigKB.sb.append(lnt.eS());
			sBigKB.sb.append("=\"");
			sBigKB.sb.append(eType);
			sBigKB.sb.append("\"");
		}
	}
	sBigKB.sb.append(">");
	// Data
	if(eType != OType.OTOBJ) {
		//SOA Encryption
		if(allowEncryption && nuke.getLabel() != lLt && nuke.getIA(lencryption,false)) {
			if(encryption == null) {
				encryption = new Encryption();
			}
			// Only encrypt if have a length
			ns = nuke.eS();
			if(ns.length() > 0) {
				ns = encryption.encrypt(ns);
			}
		} // Strip out unwanted characters
		else // Buffers are non displayable in the pretty case
		{
			if(pretty && eType == OType.OTBAR) {
				ns = OT.nst;
			} else {
				ns = nuke.eS();
				if(strip) {
					ns = ns.replace('\'',' ');
					ns = ns.replace('"',' ');
					ns = ns.replace(',',' ');
				}
			} // if pretty than that means no non displayable
		}
		xAppend(sBigKB.sb,ns,null);
	} // It is an object
	else if((obj = nuke.eO()) != null && obj.getClass().getName().equals(OT.OTCLASS)) {
		nugData = (OT) obj;
		if(nugData.getLabel() == OTNSp.lLt) {
			xAppend(sBigKB.sb,nugData.eS(),null);
		}
	}
	// Add children if necessary
	nukeJrc.setColl(nuke);
	nukeJr = nukeJrc.getNextIgn();
	int jrCnt = 0;
	// Children
	if(nukeJr != null) {
		do {
			jrCnt++;
			xSplatKB(nukeJr,level + 1,pretty,strip);
		} while((nukeJr = nukeJrc.getNextIgn()) != null);
	}
	// A new ending for XML
	if(jrCnt > 0 && (pretty)) {
		sBigKB.sb.append("\n");
		for(i = 0;i < level * XSPACE;i++) {
			sBigKB.sb.append(" ");
		}
	}
	sBigKB.sb.append("</");
	sBigKB.sb.append(nuke.eSLabel());
	sBigKB.sb.append(">");
	// cleanup
	nukeJrc.release();
	if(ncapp != null) {
		ncapp.release();
	}
}

/**
 * Write out XML to an output string writer
 *
 * @param nuke
 * @param level
 * @param pretty
 * @param strip
 * @param osw
 */
public void xSplatOSW(OT nuke,int level,boolean pretty,boolean strip,BufferedWriter osw) {
	OT nukeJr;
	int i;
	OTCur nukeJrc = OTCur.getInst();
	OT nugData;
	int eType;
	OT lit, attr;
	String ns;
	//	String theClass;
	OTCur ncapp = null;
	Object obj;

	try {
		// Indent at least this much
		if(pretty) {
			if(level > 0) {
				osw.write("\n");
			}
			for(i = 0;i < level * XSPACE;i++) {
				osw.write(" ");
			}
		}
		// Label
		osw.write("<");
		osw.write(nuke.eSLabel());
		// Convert back to labels
		eType = getEType(nuke);

		//icky icky icky add stuff for
		if((attr = nuke.getInstAt()) != null) {
			ncapp = OTCur.getInst(attr);
			while((lit = ncapp.getNextIgn()) != null) {
				if(useAttributes || (lit.getLabel() != lmr
					&& (useTags || (lit.getLabel() != ltg && lit.getLabel() != ldefault
					&& lit.getLabel() != ltgIn)))) {
					// Persist default tag only
					if(persistDefault) {
						if(lit.getLabel() == ldefault) {
							refDefault = lit.eL();
							curDefault = getDefaultTag();
							lit.aL(curDefault);
						} else if(lit.getLabel() == ltg) {
							if(lit.eL() == refDefault) {
								lit.aL(curDefault);
								refDefault = curDefault = -1;
							} else {
								lit = null;
							}
						} else if(lit.getLabel() == ltgIn) {
							lit = null;
						}
					}
					if(lit != null) {
						osw.write(" ");
						osw.write(lit.getLabel().eS());
						osw.write("=\"");
						xAppend(sBigKB.sb,lit.eS(),osw);
						osw.write("\"");
					}
				} // Good attribute
			} // while attributes
		}

		// add type for non strings
		if(useTypes && eType != OType.OTSTR && eType != OType.OTNON) {
			if(eType != OType.OTOBJ || ((obj = nuke.eO()) != null && obj.getClass().getName().equals(
				OT.OTCLASS) && ((OT) obj).getLabel() == OTNSp.lLt)) {
				osw.write(" ");
				osw.write(lnt.eS());
				osw.write("=\"");
				osw.write(OT.nst + eType);
				osw.write("\"");
			}
		}
		osw.write(">");

		// Data
		if(eType != OType.OTOBJ) {
			//SOA Encryption
			if(allowEncryption && nuke.getLabel() != lLt && nuke.getIA(lencryption,false)) {
				if(encryption == null) {
					encryption = new Encryption();
				}
				ns = nuke.eS();
				if(ns.length() > 0) {
					ns = encryption.encrypt(ns);
				}
			} // Strip out unwanted characters
			else // Buffers are non displayable in the pretty case
			{
				if(pretty && eType == OType.OTBAR) {
					ns = OT.nst;
				} else {
					ns = nuke.eS();
					if(strip) {
						ns = ns.replace('\'',' ');
						ns = ns.replace('"',' ');
						ns = ns.replace(',',' ');
					}
				}
			}
			xAppend(sBigKB.sb,ns,osw);
		} // It is an object
		else if((obj = nuke.eO()) != null && obj.getClass().getName().equals(OT.OTCLASS)) {
			nugData = (OT) obj;
			if(nugData.getLabel() == OTNSp.lLt) {
				xAppend(sBigKB.sb,nugData.eS(),osw);
			}
		}

		// Add children if necessary
		nukeJrc.setColl(nuke);
		nukeJr = nukeJrc.getNextIgn();

		int jrCnt = 0;

		// Children
		if(nukeJr != null) {
			do {
				jrCnt++;
				xSplatOSW(nukeJr,level + 1,pretty,strip,osw);
			} while((nukeJr = nukeJrc.getNextIgn()) != null);
		}

		// A new ending for XML
		if(jrCnt > 0 && (pretty)) {
			osw.write("\n");
			for(i = 0;i < level * XSPACE;i++) {
				osw.write(" ");
			}
		}

		osw.write("</");
		osw.write(nuke.eSLabel());
		osw.write(">");

	} catch(java.io.IOException ioe) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz("IO execption " + ioe.getMessage());
		}
	}

	// cleanup
	nukeJrc.release();
	if(ncapp != null) {
		ncapp.release();
	}
}

public void xAddExclamations(OT spot) {
	OT n;
	OTCur nc = nukeComments.getNC();
	OTCur ncSpot = (spot == null) ? externalComments.getNC() : spot.getNC();
	while((n = nc.getNext()) != null) {
		ncSpot.insTail(n);
	}
	hasComments = false;
	nc.delAll();
	// clean up
	ncSpot.release();
	nc.release();
}

// Exclamation
public OT xHdlExclamations() {
	OT comment;
	xPos = 0;
	boolean dash1, dash2, done;
	dash1 = dash2 = done = false;
	boolean docT = false;
	clrXLine();
	putXLine('<');
	putXLine('!');
	// Could have multiple multiple stuff here
	while(!done) {
		if((xChrI = xGet()) == XFAILURE) {
			depth--;
			return null;
		} else {
			switch(xChr) {
				case '-':
					if(!dash1) {
						dash1 = true;
					} else if(dash1 && !dash2) {
						dash2 = true;
					} else {
						dash1 = true;
						dash2 = false;
					}
					break;
				case labelEnd:
					// Check for Document type in HTML
					if((xLine[2] == 'D' && xLine[3] == 'O' && xLine[4] == 'C' && xLine[5] == 'T')) {
						done = true;
						docT = true;
					} else if(dash1 && dash2) {
						done = true;
					}
					break;
				// This destroys dash count
				default:
					dash1 = dash2 = false;
					break;
			}
			putXLine(xChr);
		}
	}
	// Return a comment
	comment = new OT(new String(xLine,0,xPos),((docT) ? lDOCTYPE : lComment));
	// Jump start to next letter?
	xChrI = xGet();
	if(docT) {
		docType = comment;
	} else {
		nukeComments.insTail(comment);
		hasComments = true;
	}
	return comment;
}

static public FormatXML getInst() {
	if(instFactId < 0) {
		instFactId = addInstFact(MethodHandles.lookup().lookupClass().getName(),pooledCache);
	}
	FormatXML r = (FormatXML) getPooledInst(instFactId);
	// assigning instances to instances;
	r.sBigKB = BigBean.getInst();
	r.sBean = StringBean.getInst();
	// cleanup
	return r;
}

static public FormatXML getInst(boolean useTag,boolean useTypes,boolean useAttrib,
	boolean saveDefault,boolean useCollTag) {
	FormatXML r = getInst();
	r.setUsage(useTag,useTypes,useAttrib,saveDefault,useCollTag);
	return r;
}

@Override
public void release() {
	// total clear before release
	useTypes = useAttributes = useTags = useCollTags = noAmpData = useJson = false;
	extReference = outboundEncoding = trackLabels = false;
	persistDefault = allowEncryption = true;
	encodeStr = null;
	sBigKB.release();
	sBigKB = null;
	sBean.release();
	sBean = null;
	super.release();
}

public int getEType(OT nuke) {
	int r;
	if(nuke.getLabel() == lLt) {
		r = OType.OTSTR;
	} else if(nuke.getLabel() == OTNSp.lnull) {
		r = nuke.eType();
	} else {
		r = nuke.getPA(ldefType,nuke.eType());
	}
	return r;
}

/**
 * Parse JSON stub
 *
 * @return
 */
public OT parseJson() {
	return null;
}

/**
 * To JSON String stub. Parse OType to JSON string.
 *
 * @param n
 * @param sb string builder for output
 */
public void toJsonStr(OT n,StringBuilder sb) {
}

/** to JSON String stub. Parse OType to JSON string.
 *
 * @param n
 * @return parsed string
 */
public String toJsonStr(OT n) {
	BigBean cSBean = BigBean.getInst();
	toJsonStr(n,cSBean.sb);
	String s = cSBean.sb.toString();
	// cleanup
	cSBean.release();
	return s;
}
final public static OT lnt = OTNSp.really("nt");
final public static OT lEnvelope = OTNSp.really("Envelope");
final public static OT lxml = OTNSp.really("xml");
final public static OT lencoding = OTNSp.really("encoding");
final public static OT lPersAt = OTNSp.really("PersAt");
final public static OT lmr = OTNSp.really("mr");
final public static OT lLt = OTNSp.really("Lt");
final public static OT llabels = OTNSp.really("Labels");
final public static OT ltg = OTNSp.really("tg");
final public static OT lComment = OTNSp.really("Comment");
final public static OT lencryption = OTNSp.really("encryption");
final public static OT lChars = OTNSp.really("Chars");
//xxx final public static OT lprs = OTNSp.really("prs");
final public static OT ldefType = OTNSp.really("defType");
final public static OT lrmSel = OTNSp.really("rmSel");
final public static OT ldefault = OTNSp.really("default");
final public static OT ltgIn = OTNSp.really("tgIn");
final public static OT ltgRf = OTNSp.really("tgRf");
final public static OT lrow = OTNSp.really("row");
public static final OT lDOCTYPE = OTNSp.really("DOCTYPE");
}
