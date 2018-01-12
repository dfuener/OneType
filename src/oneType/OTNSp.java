package oneType;

import java.util.Locale;

public class OTNSp {

public static final String ISO_FULLTIME = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
static private long totallabels;
public static final int BLEN = 90;
private static final boolean log = false;	// Never log
private static boolean development;
/**
 * the mother and the mother of all labels
 */
private static final OT nameSpace = new OT();
public static final OT lLt = new OT("Lt");
public static final OT lcollection = new OT("collection",lLt);
public static final OT lChan = new OT("Chan",lLt);
public static final OT lDate = new OT("Date",lLt);
public static final OT ldefault = new OT("default",lLt);
public static final OT ldefDisp = new OT("defDisp",lLt);
public static final OT ldefType = new OT("defType",lLt);
public static final OT lenabled = new OT("enabled",lLt);
public static final OT lFlip = new OT("flip",lLt);
public static final OT lfraction = new OT("fraction",lLt);
public static final OT lhash = new OT("hash",lLt);
public static final OT lInst = new OT("Inst",lLt);
public static final OT lInstAt = new OT("InstAt",lLt);
public static final OT llength = new OT("length",lLt);
public static final OT lmr = new OT("mr",lLt);
public static final OT lNameSpace = new OT("NameSpace",lLt);
public static final OT lnull = new OT(OT.nst,lLt);
public static final OT lpattern = new OT("pattern",lLt);
public static final OT lPersAt = new OT("PersAt",lLt);
public static final OT lpersistDefault = new OT("persistDefault",lLt);
public static final OT lref = new OT("ref",lLt);
public static final OT lrow = new OT("row",lLt);
public static final OT lrowOrder = new OT("rowOrder",lLt);
public static final OT lsrhColl = new OT("srhColl",lLt);
public static final OT lsort = new OT("sort",lLt);
public static final OT ltg = new OT("tg",lLt);
public static final OT ltgIn = new OT("tgIn",lLt);
public static final OT ltgRf = new OT("tgRf",lLt);
public static final OT lTransactions = new OT("Transactions",lLt);
private static final boolean justCheck = false;
private static boolean init;
private static Locale locale = Locale.getDefault();
static private long collTag;
static private boolean ignoreCase;
static private boolean convertTo;
private static boolean debug;
private static boolean debugConsole;
private static boolean overlayXML;
static private String datePattern = OT.nst;
static private boolean useTags = true;
private static boolean useMr = true;
private static String referenceArea = System.getProperty("user.dir") + "/";
private static String userArea = System.getProperty("user.dir") + "/";
private static final String user = System.getProperty("user.name");
private static String reference = OT.nst;
//sdf static private SimpleDateFormat fdf = null;
//sdf static private SimpleDateFormat df = null;

static private void init() {
	init = true;
	// Assign the original label a label
	lLt.setLabel(lLt);
	nameSpace.crtHash();
	//xxxnameSpace.setCase(ignoreCase);
	// Default nugs
	nameSpace.addHash(lLt);
	nameSpace.addHash(lcollection);
	nameSpace.addHash(lDate);
	nameSpace.addHash(ldefault);
	nameSpace.addHash(ldefDisp);
	nameSpace.addHash(ldefType);
	nameSpace.addHash(lenabled);
	nameSpace.addHash(lfraction);
	nameSpace.addHash(lhash);
	nameSpace.addHash(lInst);
	nameSpace.addHash(lInstAt);
	nameSpace.addHash(llength);
	nameSpace.addHash(lmr);
	nameSpace.addHash(lNameSpace);
	nameSpace.addHash(lnull);
	nameSpace.addHash(lpattern);
	nameSpace.addHash(lPersAt);
	nameSpace.addHash(lpersistDefault);
	nameSpace.addHash(lref);
	nameSpace.addHash(lrow);
	nameSpace.addHash(lrowOrder);
	nameSpace.addHash(lsrhColl);
	nameSpace.addHash(lsort);
	nameSpace.addHash(ltg);
	nameSpace.addHash(ltgIn);
	nameSpace.addHash(ltgRf);
	nameSpace.addHash(lTransactions);
	// Tell me updates
	lhash.getPersAt(true).insTail(new OT(1,lhash));
	lPersAt.getPersAt(true).insTail(new OT("Chars",ldefDisp));
	// Default type
	lChan.getPersAt(true).insTail(new OT(OType.OTINT,ldefType));
	lDate.getPersAt(true).insTail(new OT(OType.OTDAT,ldefType));
	ldefType.getPersAt(true).insTail(new OT(OType.OTINT,ldefType));
	lFlip.getPersAt(true).insTail(new OT(OType.OTINT,ldefType));
	lfraction.getPersAt(true).insTail(new OT(OType.OTINT,ldefType));
	lInst.getPersAt(true).insTail(new OT(OType.OTINT,ldefType));
	llength.getPersAt(true).insTail(new OT(OType.OTINT,ldefType));
	lrow.getPersAt(true).insTail(new OT(true,lcollection));
	ltg.getPersAt(true).insTail(new OT(OType.OTLON,ldefType));
	ltgIn.getPersAt(true).insTail(new OT(OType.OTLON,ldefType));
	ltgRf.getPersAt(true).insTail(new OT(OType.OTLON,ldefType));
	lTransactions.getPersAt(true).insTail(new OT(OType.OTLON,ldefType));
	// hard count of total labels
	totallabels = 30;
}

static public String getUser() {
	return user;
}

static public OT getStats() {
	return new OT(totallabels,lNameSpace);
}

static public String getReferenceArea() {
	return referenceArea;
}

static public void setReferenceArea(String s) {
	referenceArea = getEndSlash((s == null) ? System.getProperty("user.dir") + "/" : s);
}

static public String getUserArea() {
	return userArea;
}

static public void setUserArea(String s) {
	userArea = getEndSlash((s == null) ? System.getProperty("user.dir") + "/" : s);
}

static public String getEndSlash(String s) {
	char lc = s.charAt(s.length() - 1);
	if(lc != '\\' && lc != '/') {
		s += "/";
	}
	// cleanup
	return s;
}

static public String getReference() {
	return reference;
}

static public void setReference(String s) {
	reference = (s == null) ? OT.nst : s;
}

static public OT getNameSpace() {
	return nameSpace;
}

static public Locale getLocale() {
	return locale;
}

static public void setLocale(Locale x) {
	locale = x;
}

static public long getCurCollTag() {
	return collTag;
}

static public void setCollTag(long x) {
	collTag = x;
}

static synchronized public long getCollTag() {
	long r = collTag++;
	if(collTag == Long.MAX_VALUE) {
		collTag = 0;
	}
	return r;
}

//xxxstatic public void setCase(boolean x) {
//xxx	ignoreCase = x;
//xxx	nameSpace.setCase(x);
//xxx}
//xxxstatic public boolean getCase() {
//xxx	return ignoreCase;
//xxx}
static public void setDevelopment(boolean x) {
	development = x;
}

static public boolean getDevelopment() {
	return development;
}

static public void setConvertTo(boolean x) {
	convertTo = x;
}

static public boolean getConvertTo() {
	return convertTo;
}

static public void setDebug(boolean d) {
	debug = d;
}

/**
 * get debug mode
 *
 * @return
 */
static public boolean getDebug() {
	return debug;
}

static public void setDebugConsole(boolean d) {
	debugConsole = d;
}

/**
 * get debug mode
 *
 * @return
 */
static public boolean getDebugConsole() {
	return debugConsole;
}

/**
 * set always replace stored XML
 *
 * @param d
 */
static public void setOverlayXML(boolean d) {
	overlayXML = d;
}

/**
 * always replace stored XML
 *
 * @return
 */
static public boolean getOverlayXML() {
	return overlayXML;
}

/**
 * return a static date pattern
 *
 * @return
 */
static public String getDatePattern() {
	return datePattern;
}

static public void setDatePattern(String x) {
	if(!datePattern.equals(x)) {
		datePattern = x;
		SDF.setDatePattern(datePattern);
		lDate.setIA(lpattern,x);
	}
}

/*sdf
static public void setSDF(SimpleDateFormat x) {
	df = x;
}
 */
 /*sdf
static public SimpleDateFormat getSDF() {
	if(df == null) {
		df = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT,OTNSp.getLocale());
		df.setLenient(false);
		if(pattern != null) {
			df.applyPattern(pattern);
		}
	}
	return df;
}
 */
/**
 * getSDF
 *
 * @param pattern
 * @param dt
 * @return
 */
/*sdf
static synchronized public String getSDF(String pattern,Date dt) {
	if(fdf == null) {
		fdf = new SimpleDateFormat(pattern);
		fdf.setLenient(false);
	} else {
		fdf.applyPattern(pattern);
	}
	return fdf.format(dt);
}
 */
/**
 * setUseTags Use Tags are individual reference tags are not used for formatting BpcsIX messages
 *
 * @param x
 */
public static void setUseTags(boolean x) {
	useTags = x;
}

/**
 * getUseTags Use Tags are individual reference tags are not used for formatting BpcsIX messages
 *
 * @return
 */
public static boolean getUseTags() {
	return useTags;
}

/**
 * set use mark parameter
 *
 * @param x
 */
public static void setUseMr(boolean x) {
	useMr = x;
}

/**
 *
 * @return
 */
public static boolean getUseMr() {
	return useMr;
}

/**
 * just check if a OType is really there or not
 */
public OTNSp() {
	init();
}

static public OT reallyThere(String giv) {
	OT man = null; // The default is this guy if not found
	// Garbage in, garbage out
	if(giv != null && !giv.equals(OT.nst)) {
		man = nameSpace.getHash(giv);
	}
	return man;
}

/**
 * convert this to its real label or message
 *
 * @param gav
 * @return
 */
synchronized static public OT really(String gav) {
	OT n;
	if(!init) {
		init();
	}
	// Garbage in garbage out
	if(gav == null || gav.equals(OT.nst)) {
		return null;
	}
	// if I did not find the string than add to the hash list
	if((n = nameSpace.getHash(gav)) == null) {
		n = new OT(gav,OTNSp.lLt);
		nameSpace.addHash(n);
	} else if(ignoreCase && !convertTo) {
		// Last version is latest version
		// Only convert if different to save on what memory locations get changed
		if(!gav.equals(n.eS())) {
			n.a(gav);
		}
	}
	// cleanup
	return n;
}

synchronized static public OT really(OT giv) {
	OT man = null; // The default is this guy if not found
	String es;
	if(!init) {
		init();
	}
	// Garbage in, garbage out
	if(giv != null && giv.eS() != null && !giv.eS().equals(OT.nst)) {
		if(giv.eS().charAt(0) == ' ') {
			System.out.println("" + giv.eS());
		}

		// Find in extended list
		if((man = nameSpace.getHash(giv)) == null) {
			// If I didn't find than add
			if(!justCheck && man == null) {
				man = giv;
				nameSpace.addHash(giv);
			}
		} // Last version is latest version
		else if(ignoreCase) {
			// Only convert if different to save on what memory locations get changed
			if(!giv.eS().equals(man.eS())) {
				man.a(giv.eS());
			}
		}
	}
	return man;
}

static public void buzz(String s) {
	if(OTNSp.getDebug()) {
		OTNSp.buzz(null,s);
	}
}

static public void buzz(String className,String s) {
	String temp;
	if(debug) {
		synchronized(OTNSp.class) {
			if(s == null) {
				s = OT.nst;
			}
			if(className == null) {
				className = OT.nst;
			}
			int blen = s.length();
			int bi = 0, b2;
			String bs;
			if(blen == 0 && className.length() > 0) {
				temp = getThreadId() + className;
				if(debugConsole) {
					System.out.println(temp);
				}
			}
			//ooo	if(log && Log.getInst().getLogDebug()) {
			//ooo		Log.getInst().addDebugMessage(temp);
			//ooo	}
			//ooo} else {
			//ooo		if(log && Log.getInst().getLogDebug()) {
			//ooo		Log.getInst().addDebugMessage(s);
			//ooo	}
			if(debugConsole) {
				while(bi < blen) {
					b2 = bi + BLEN;
					if(b2 > blen) {
						b2 = blen;
					}
					bs = s.substring(bi,b2);
					temp = getThreadId();
					temp += className + ((className.length() > 0) ? " " : OT.nst) + bs;
					System.out.println(temp);
					bi = b2;
				}
			}
		}
	}
}

static public String getThreadId() {
	String temp = "T" + Thread.currentThread().getId();
	if(temp.length() < 3) {
		temp += "   ";
	} else if(temp.length() < 4) {
		temp += "  ";
	} else {
		temp += " ";
	}
	return temp;
}

static public void buzz(String className,OT x,String y) {
	if(debug) {
		synchronized(OTNSp.class) {
			if(className != null) {
				buzz(className);
			}
			String s = (x == null) ? OT.nst : x.toEntireDXString();
			int i = 0;
			int l = s.length();
			int b = i;
			char c;
			while(i < l) {
				c = s.charAt(i);
				if(c != ' ' && Character.isWhitespace(c)) {
					if(b < l) {
						buzz(s.substring(b,i));
					}
					b = i + 1;
				}
				i++;
			}
			if(b < l) {
				buzz(s.substring(b,i));
			}
			if(y != null) {
				buzz(y);
			}
		}
	}
}

static public void dspLabelList(OT mo) {
	OT c = mo.getHead();
	OT d, e, el;
	String es;
	System.out.println("OTNSp List ");
	while(c != null) {
		System.out.println("" + c.getLabel().eS() + " " + c.eS());
		d = c.getHead();
		while(d != null) {
			System.out.println("   " + d.getLabel().eS() + " " + d.eS());
			e = d.getHead();
			while(e != null) {
				if((el = e.getLabel()) != null) {
					es = el.eS();
				} else {
					es = "";
				}
				System.out.println("      " + es + " " + e.eS());
				e = d.getNext();
			}
			d = c.getNext();
		}
		c = mo.getNext();
	}
}

/**
 * delete List of label
 *
 * @param list
 */
public synchronized static void delList(OT list) {
	OT c = list.getHead();
	while(c != null) {
		nameSpace.del(c);
		c = list.getNext();
	}
}
}	// Class
