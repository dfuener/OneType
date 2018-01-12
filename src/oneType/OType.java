package oneType;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BigD
 */
import java.util.Date;
import java.math.BigDecimal;
import java.nio.ByteBuffer;

public class OType {

final public static String COMMA = ",";
final public static char t = 't';
public static final int OTNON = 0;
public static final int OTINT = 1;
public static final int OTSTR = 2;
public static final int OTOBJ = 3;
public static final int OTLON = 4;
public static final int OTBAR = 5;
public static final int OTDBL = 6;
//	public static final int OTBL=7;	// Special type for files
public static final int OTDAT = 8; // Special type for dates
public static final int OTBIG = 9; // Special type for big nums
public static final String TRUE = "t";
public static final String FALSE = "f";
public static final char PERIOD = '.';
public static final String ZEROPTZERO = "0.0";
public static final String ZERO = "0";
public static final char DASH = '-';
public static final char LPAR = '(';
/* implamented data types */
protected byte nuglType = OTNON;
protected Object no;
protected OT label = OTNSp.lnull;

/**
 * create default OType class
 */
public OType() {
}

/**
 * create integer OType class
 *
 * @param i
 */
public OType(int i) {
	nuglType = OTINT;
	no = ByteBuffer.wrap(new byte[8]);
	((ByteBuffer)no).putInt(0,i);
}

/**
 * create string OType class
 *
 * @param s
 */
public OType(String s) {
	nuglType = OTSTR;
	no = s;
}

/**
 * create boolean OType class
 *
 * @param b
 */
public OType(boolean b) {
	nuglType = OTSTR;
	no = (b) ? OType.TRUE : OType.FALSE;
}

/**
 * create an object OType class
 *
 * @param o
 */
public OType(Object o) {
	nuglType = OTOBJ;
	no = o;
}

/**
 * create a long OType class
 *
 * @param l
 */
public OType(long l) {
	nuglType = OTLON;
	no = ByteBuffer.wrap(new byte[8]);
	((ByteBuffer)no).putLong(0,l);
}

/**
 * create a byte array OType class
 *
 * @param bA
 */
public OType(byte[] bA) {
	nuglType = OTBAR;
	no = bA;
}

/**
 * create a double OType class
 *
 * @param d
 */
public OType(double d) {
	nuglType = OTDBL;
	no = ByteBuffer.wrap(new byte[8]);
	((ByteBuffer)no).putDouble(0,d);
}

/**
 * create a big Decimal class
 *
 * @param bD
 */
public OType(BigDecimal bD) {
	nuglType = OTBIG;
	no = bD;
}

/**
 * create a date OType class
 *
 * @param dt
 */
public OType(Date dt) {
	nuglType = OTDAT;
	no = dt;
}

/**
 * create a OType OType class
 *
 * @param n
 */
public OType(OT n) {
	// Assign and fly
	a(n);
}

/**
 * assign an integer
 *
 * @param i
 */
public void a(int i) {
	if(nuglType != OTINT && nuglType != OTLON && nuglType != OTDBL) {
		no = ByteBuffer.wrap(new byte[8]);
	}
	((ByteBuffer)no).putInt(0,i);
	nuglType = OTINT;
}

public void a(long l) {
	if(nuglType != OTINT && nuglType != OTLON && nuglType != OTDBL) {
		no = ByteBuffer.wrap(new byte[8]);
	}
	((ByteBuffer)no).putLong(0,l);
	nuglType = OTLON;
}

/**
 * assign a string
 *
 * @param s
 */
public void a(String s) {
	nuglType = OTSTR;
	no = s;
}

/**
 * Assign an object
 *
 * @param o
 */
public void aO(Object o) {
	nuglType = OTOBJ;
	no = o;
}

/**
 * Assign a long
 *
 * @param l
 */
public void aL(long l) {
	if(nuglType != OTINT && nuglType != OTLON && nuglType != OTDBL) {
		no = ByteBuffer.wrap(new byte[8]);
	}
	((ByteBuffer)no).putLong(0,l);
	nuglType = OTLON;
}

/**
 * Assign a byte array
 *
 * @param bA
 */
public void a(byte[] bA) {
	nuglType = OTBAR;
	no = bA;
}

/**
 * Assign a double
 *
 * @param d
 */
public void a(double d) {
	if(nuglType != OTINT && nuglType != OTLON && nuglType != OTDBL) {
		no = ByteBuffer.wrap(new byte[8]);
	}
	((ByteBuffer)no).putDouble(0,d);
	nuglType = OTDBL;
}

/**
 * assign a double
 *
 * @param d
 */
public void aD(double d) {
	if(nuglType != OTINT && nuglType != OTLON && nuglType != OTDBL) {
		no = ByteBuffer.wrap(new byte[8]);
	}
	((ByteBuffer)no).putDouble(0,d);
	nuglType = OTDBL;
}

/**
 * Assign a big decimal
 *
 * @param bD
 */
public void a(BigDecimal bD) {
	nuglType = OTBIG;
	no = bD;
}

/**
 * assign boolean
 *
 * @param b
 */
public void a(boolean b) {
	a((b) ? OType.TRUE : OType.FALSE);
}

/**
 * Assign Date to a class
 *
 * @param l
 */
public void a(Date l) {
	nuglType = OTDAT;
	no = l;
}

/**
 * Assign the OTypes type to this type. This is really a clone
 *
 * @param n
 */
public final void a(OT n) {
	switch(n.nuglType) {
		case OTNON:
			clear();
			break;
		case OTINT:
			a(n.eI());
			break;
		case OTSTR:
			a(n.eS());
			break;
		case OTOBJ:
			aO(n.eO());
			break;
		case OTLON:
			aL(n.eL());
			break;
		case OTBAR:
			a(n.eB());
			break;
		case OTDBL:
			a(n.eD());
			break;
		case OTDAT:
			a(n.eDt());
			break;
		case OTBIG:
			a(n.eBD());
			break;
		default:
			break;
	}
}

/**
 * Assign a OType to this OTypes current type
 *
 * @param n
 */
public void aCT(OT n) {
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
			a(n.eI());
			break;
		case OTSTR:
			a(n.eS());
			break;
		case OTOBJ:
			aO(n.eO());
			break;
		case OTLON:
			aL(n.eL());
			break;
		case OTBAR:
			a(n.eB());
			break;
		case OTDBL:
			a(n.eD());
			break;
		case OTDAT:
			a(n.eDt());
			break;
		case OTBIG:
			a(n.eBD());
			break;
		default:
			break;
	}
}

/**
 * assign by current in type
 *
 * @param data
 */
public void aType(String data) {
	aType(data,nuglType);
}

/**
 * assign by type
 *
 * @param data
 * @param type
 */
public void aType(String data,int type) {
	switch(type) {
		case OTNON:
			clear();
			break;
		case OTINT:
			if(data == null) {
				a(0);
			} else {
				try {
					Integer i = new Integer(cleanNum(data,false));
					a(i);
				} catch (NumberFormatException e) {
					a(0);
				}
			}
			break;
		case OTSTR:
			a(data);
			break;
		case OTOBJ:
			aO((Object)data);
			break;
		case OTLON:
			if(data == null) {
				aL(0);
			} else {
				try {
					Long l = new Long(cleanNum(data,false));
					aL(l);
				} catch (NumberFormatException e) {
					aL(0);
				}
			}
			break;
		// assume byte is just another name for string ?
		case OTBAR:
			if(data != null) {
				a(data.getBytes());
			} else {
				no = null;
				nuglType = OTBAR;
			}
			break;
		case OTDBL:
			if(data == null) {
				aD(0.0);
			} else {
				try {
					Double d = new Double(cleanNum(data,true));
					aD(d);
				} catch (NumberFormatException e) {
					aD(0.0);
				}
			}
			break;
		case OTDAT:
			a(getDate(data));
			break;
		case OTBIG:
			data = cleanNum(data,true);
			BigDecimal d = new BigDecimal(data);
			a(d);
			break;
		default:
			break;
	}
}

/**
 * aType	Assign by type
 *
 * @param sb
 * @param xLine
 * @param startPos
 * @param length
 * @param type
 */
public void aType(StringBuilder sb,char[] xLine,int startPos,int length,int type) {
	switch(type) {
		case OTNON:
			clear();
			break;
		case OTINT:
			if(xLine == null) {
				a(0);
			} else {
				try {
					Integer i = new Integer(cleanNum(sb,xLine,startPos,length,false));
					a(i);
				} catch (NumberFormatException e) {
					a(0);
				}
			}
			break;
		case OTSTR:
			a(((xLine == null) ? OT.nst : new String(xLine,startPos,length)));
			break;
		case OTOBJ:
			aO((Object)((xLine == null) ? OT.nst : new String(xLine,startPos,length)));
			break;
		case OTLON:
			if(xLine == null) {
				aL(0);
			} else {
				try {
					Long l = new Long(cleanNum(sb,xLine,startPos,length,false));
					aL(l);
				} catch (NumberFormatException e) {
					aL(0);
				}
			}
			break;
		// assume byte is just another name for string ?
		case OTBAR:
			if(xLine != null) {
				a(new String(xLine,startPos,length).getBytes());
			} else {
				no = null;
			}
			break;
		case OTDBL:
			if(xLine == null) {
				aD(0.0);
			} else {
				try {
					Double d = new Double(cleanNum(sb,xLine,startPos,length,true));
					aD(d);
				} catch (NumberFormatException e) {
					aD(0.0);
				}
			}
			break;
		case OTDAT:
			if(xLine == null) {
				a((Date)null);
			} else {
				a(getDate(new String(xLine,startPos,length)));
			}
			break;
		case OTBIG:
			a(new BigDecimal(cleanNum(sb,xLine,startPos,length,true)));
			break;
		default:
			break;
	}
}

/**
 * assign by type
 *
 * @param data
 * @param type
 */
public void aType(Object data,int type) {
	switch(type) {
		case OTNON:
			clear();
			break;
		case OTINT:
			if(data == null) {
				a(0);
			} else {
				try {
					Integer i = (Integer)data;
					a(i);
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
					a(0);
				}
			}
			break;
		case OTSTR:
			a((String)data);
			break;
		case OTOBJ:
			aO(data);
			break;
		case OTLON:
			if(data == null) {
				aL(0);
			} else {
				try {
					Long l = (Long)data;
					aL(l);
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
					aL(0);
				}
			}
			break;
		// assume byte is just another name for string ?
		case OTBAR:
			if(data != null) {
				a((byte[])data);
			} else {
				no = null;
			}
			break;
		case OTDBL:
			if(data == null) {
				aD(0.0);
			} else {
				try {
					Double d = (Double)data;
					aD(d);
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
					aD(0.0);
				}
			}
			break;
		case OTDAT:
			a((Date)data);
			break;
		case OTBIG:
			if(data != null) {
				BigDecimal d = (BigDecimal)data;
				a(d);
			} else {
				no = null;
			}
			break;
		default:
			break;
	}
}

public int eI() {
	int rs = 0;
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
			rs = ((ByteBuffer)no).getInt(0);
			break;
		case OTSTR:
			if(no != null) {
				try {
					rs = Integer.valueOf(cleanNum((String)no,false));
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
					rs = 0;
				}
			}
			break;
		case OTOBJ:
			if(no.getClass().getName().equals(OT.OTCLASS)) {
				rs = ((OT)no).eI();
			} else {
				rs = 0;
			}
			break;
		case OTLON:
			rs = (int)((ByteBuffer)no).getLong(0);
			break;
		// assume byte is just another name for string ?
		case OTBAR:
			if(no != null) {
				try {
					rs = Integer.valueOf(cleanNum(new String((byte[])no),false));
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
					rs = 0;
				}
			}
			break;
		case OTDBL:
			rs = (int)((ByteBuffer)no).getDouble(0);
			break;
		case OTDAT:
			if(no != null) {
				rs = (int)((Date)no).getTime();
			}
			break;
		case OTBIG:
			if(no != null) {
				rs = ((BigDecimal)no).intValue();
			}
			break;
		default:
			break;
	}
	return rs;
}

public String eS() {
	return eS(null);
}

public String eS(DecForm df) {
	String rs = OT.nst;
	DecForm lf = df;
	SDF sdf;
	switch(nuglType) {
		case OTSTR:
			if(no != null) {
				rs = (String)no;
			}
			break;
		case OTNON:
			break;
		case OTINT:
			rs = Integer.toString(((ByteBuffer)no).getInt(0));
			break;
		case OTOBJ:
			if(no != null) {
				if(no.getClass().getName().equals(OT.OTCLASS)) {
					// Bad news recursion if an objects object is it
					if(no == this) {
						rs = OT.nst;
					} else {
						rs = ((OT)no).eS();
					}
				} else {
					rs = no.toString();
				}
			}
			break;
		case OTLON:
			rs = Long.toString(((ByteBuffer)no).getLong(0));
			break;
		case OTBAR:
			if(no != null) {
				rs = new String((byte[])no);
			}
			break;
		case OTDBL:
			// Got decmal format from anywhere so make a default double
			if(df == null) {
				lf = DecForm.getInst();
			}
			rs = lf.getDecimalFormat().format(((ByteBuffer)no).getDouble(0));
			if(df == null) {
				lf.release();
			}
			break;
		case OTDAT:
			if(no != null) {
				sdf = SDF.getInst();
				rs = sdf.format((Date)no);
				sdf.release();
			}
			break;
		case OTBIG:
			if(no != null) {
				rs = ((BigDecimal)no).toString();
				if(DecForm.getDecimalSeparator().charAt(0) != PERIOD) {
					int ids = rs.indexOf(PERIOD);
					if(ids >= 0) {
						String a = rs.substring(0,ids);
						a += DecForm.getDecimalSeparator();
						if(ids < rs.length()) {
							a += rs.substring(ids + 1,rs.length());
							rs = a;
						}
					}
				}
			}
			break;
		default:
			break;
	}
	return rs;
}

/**
 * class returns its object reference
 *
 * @return
 */
public Object eO() {
	Object rs = null;
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
			rs = ((ByteBuffer)no).getInt(0);
			break;
		case OTSTR:
			rs = no;
			break;
		case OTOBJ:
			rs = no;
			break;
		case OTLON:
			rs = ((ByteBuffer)no).getLong(0);
			break;
		case OTBAR:
			rs = no;
			break;
		case OTDBL:
			rs = ((ByteBuffer)no).getDouble(0);
			break;
		case OTDAT:
			rs = no;
			break;
		case OTBIG:
			rs = no;
			break;
		default:
			break;
	}
	return rs;
}

/**
 * return the long
 *
 * @return
 */
public long eL() {
	long rs = 0;
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
			rs = ((ByteBuffer)no).getInt(0);
			break;
		case OTLON:
			rs = ((ByteBuffer)no).getLong(0);
			break;
		case OTSTR:
			if(no != null) {
				try {
					rs = Long.valueOf(cleanNum((String)no,false));
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
					rs = 0;
				}
			}
			break;
		case OTOBJ:
			if(no.getClass().getName().equals(OT.OTCLASS)) {
				rs = ((OT)no).eL();
			}
			break;
		case OTBAR:
			if(no != null) {
				try {
					rs = Long.valueOf(cleanNum(new String((byte[])no),false));
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
					rs = 0;
				}
			}
			break;
		case OTDBL:
			rs = (long)((ByteBuffer)no).getDouble(0);
			break;
		case OTDAT:
			if(no != null) {
				rs = ((Date)no).getTime();
			}
			break;
		case OTBIG:
			if(no != null) {
				rs = ((BigDecimal)no).longValue();
			}
			break;
		default:
			break;
	}
	return rs;
}

/**
 * return a byte array
 *
 * @return
 */
public byte[] eB() {
	SDF sdf;
	byte[] rs = OT.nst.getBytes();
	switch(nuglType) {
		case OTSTR:
			if(no != null) {
				rs = ((String)no).getBytes();
			}
			break;
		case OTNON:
			break;
		case OTINT:
			rs = Integer.toString(((ByteBuffer)no).getInt(0)).getBytes();
			break;
		case OTOBJ:
			if(no != null) {
				if(no.getClass().getName().equals(OT.OTCLASS)) {
					rs = ((OT)no).eB();
				} else {
					rs = no.toString().getBytes();
				}
			}
			break;
		case OTLON:
			rs = Long.toString(((ByteBuffer)no).getLong(0)).getBytes();
			break;
		case OTBAR:
			if(no != null) {
				rs = (byte[])no;
			}
			break;
		case OTDBL:
			rs = Double.toString(((ByteBuffer)no).getDouble(0)).getBytes();
			break;
		case OTDAT:
			if(no != null) {
				sdf = SDF.getInst();
				rs = sdf.format((Date)no).getBytes();
				sdf.release();
			}
			break;
		case OTBIG:
			if(no != null) {
				rs = ((BigDecimal)no).toString().getBytes();
			}
			break;
		default:
			break;
	}
	return rs;
}

/**
 * return the double
 *
 * @return
 */
public double eD() {
	double rs = 0.0;
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
			rs = ((ByteBuffer)no).getInt(0);
			break;
		case OTSTR:
			if(no != null) {
				try {
					rs = Double.valueOf(cleanNum((String)no,true));
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
				}
			}
			break;
		case OTOBJ:
			if(no != null) {
				if(no.getClass().getName().equals(OT.OTCLASS)) {
					rs = ((OT)no).eD();
				}
			}
			break;
		case OTLON:
			rs = ((ByteBuffer)no).getLong(0);
			break;
		case OTBAR:
			if(no != null) {
				try {
					rs = Double.valueOf(cleanNum(new String((byte[])no),true));
				} catch (NumberFormatException e) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(e.getMessage());
					}
				}
			}
			break;
		case OTDBL:
			rs = ((ByteBuffer)no).getDouble(0);
			break;
		case OTDAT:
			if(no != null) {
				rs = ((Date)no).getTime();
			}
			break;
		case OTBIG:
			if(no != null) {
				rs = ((BigDecimal)no).doubleValue();
			}
			break;
		default:
			break;
	}
	return rs;
}

/**
 * return the Big decimal
 *
 * @return
 */
public BigDecimal eBD() {
	if(nuglType == OTBIG) {
		return (BigDecimal)no;
	}
	BigDecimal rs = null;
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
			rs = new BigDecimal(cleanNum(eS(),false));
			break;
		case OTSTR:
			if(no != null) {
				rs = new BigDecimal(cleanNum((String)no,true));
			}
			break;
		case OTOBJ:
			if(no.getClass().getName().equals(OT.OTCLASS)) {
				rs = ((OT)no).eBD();
			}
			break;
		case OTLON:
			rs = new BigDecimal(cleanNum(eS(),false));
			break;
		case OTBAR:
			if(no != null) {
				try {
					rs = new BigDecimal(cleanNum(new String((byte[])no),true));
				} catch (NumberFormatException e) {
					rs = null;
				}
			}
			break;
		case OTDBL:
			rs = new BigDecimal(cleanNum(eS(),true));
			break;
		case OTDAT:
			if(no != null) {
				rs = new BigDecimal(cleanNum(eS(),false));
			}
			break;
		default:
			break;
	}
	if(rs == null) {
		rs = new BigDecimal(ZEROPTZERO);
	}
	return rs;
}

/**
 * class returns its object reference
 *
 * @return
 */
public Date eDt() {
	Date rs = null;
	long ldt;
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
			ldt = ((ByteBuffer)no).getInt(0);
			rs = new Date(ldt);
			break;
		// Converting a string to a date
		case OTSTR:
			rs = getDate((String)no);
			break;
		case OTOBJ:
			break;
		case OTLON:
			rs = new Date(((ByteBuffer)no).getLong(0));
			break;
		// assume byte is just another name for string ?
		case OTBAR:
			rs = getDate(new String((byte[])no));
			break;
		case OTDBL:
			ldt = (long)((ByteBuffer)no).getDouble(0);
			rs = new Date(ldt);
			break;
		case OTDAT:
			rs = (Date)no;
			break;
		case OTBIG:
			if(no != null) {
				ldt = ((BigDecimal)no).longValue();
				rs = new Date(ldt);
			}
			break;
		default:
			break;
	}
	return rs;
}

/**
 * return a class
 *
 * @return
 */
public OType eNl() {
	return this;
}

/**
 * return class type
 *
 * @return
 */
public int eType() {
	return nuglType;
}

public void clear() {
	no = null;
	nuglType = OTNON;
}

/**
 * edLen return potential maximum digit len
 */
int edLen() {
	return edLen(nuglType);
}

/**
 * edLen return potential maximum digit length
 *
 * @param nType
 * @return
 */
public static int edLen(int nType) {
	int dlen = 0;
	switch(nType) {
		case OTNON:
			break;
		case OTINT:
			dlen = 11;
			break;
		case OTSTR:
			dlen = 20;
			break;
		case OTOBJ:
			break;
		case OTLON:
			dlen = 20;
			break;
		case OTBAR:
			break;
		case OTDBL:
			dlen = 23;
			break;
		// The display length for a date is really 10	
		case OTDAT:
			dlen = 10;
			break;
		case OTBIG:
			dlen = 50;
			break;
		default:
			break;
	}
	return dlen;
}

/**
 * copy the given class
 *
 * @return
 */
@Override
public Object clone() {
	return clone(null);
}

public Object clone(OType r) {
	if(r == null) {
		r = new OType();
	}
	int i;
	long bing;
	if(no != null) {
		switch(nuglType) {
			case OTBAR:
				byte[] nr = new byte[((byte[])no).length];
				r.no = nr;
				for(i = 0;i < nr.length;i++) {
					nr[i] = ((byte[])no)[i];
				}
				break;
			case OTBIG:
				r.no = new BigDecimal(((BigDecimal)no).toString());
				break;
			case OTDAT:
				r.no = ((Date)no).clone();
			case OTINT:
				r.a(eI());
				break;
			case OTLON:
				r.a(eL());
				break;
			case OTDBL:
				r.a(eD());
				break;
			default:
				r.no = no;
				break;
		}
	}
	// Just save code and set the type
	r.nuglType = nuglType;
	// cleanup
	return r;
}

/**
 * Get a date from a string
 *
 * @param data
 * @return
 */
public Date getDate(String data) {
	Date dt = null;
	SDF sdf;
	if(data != null && !data.equals(OT.nst)) {
		sdf = SDF.getInst();
		dt = sdf.parse(data);
		sdf.release();
	}
	return dt;
}
// make sure no garbage when creating numbers

public String cleanNum(String ox,boolean period) {
	String cleanNumb;
	int o = 0;
	char clean[] = null;
	char x;
	char ds;
	int startAt = 0;
	int dash;
	if(ox != null) {
		clean = new char[ox.length()];
		// Am i minus ???
		if((dash = ox.indexOf(DASH)) >= 0 || (dash = ox.indexOf(LPAR)) >= 0) {
			clean[o++] = DASH;
			startAt = dash + 1;
		}
		ds = DecForm.getDecimalSeparator().charAt(0);
		for(int i = startAt;i < ox.length();i++) {
			x = ox.charAt(i);
			if(Character.isDigit(x)) {
				clean[o++] = x;
			} else if(x == ds || x == PERIOD) {
				// Always use this when putting into nugs
				// Because big decimal pukes
				// Converting to integer or long done....or zero will result
				if(!period) {
					break;
				} else {
					clean[o++] = PERIOD;
				}
			}
		}
	}
	// No value, return a value
	if(o == 0) {
		if(period) {
			cleanNumb = ZEROPTZERO;
		} else {
			cleanNumb = ZERO;
		}
	} else {
		cleanNumb = new String(clean,0,o);
	}
	return cleanNumb;
}

/**
 * cleanNumb
 *
 * @param sb
 * @param xLine
 * @param period
 * @param length
 * @param startPos
 * @return
 */
public String cleanNum(StringBuilder sb,char[] xLine,int startPos,int length,boolean period) {
	String cleanNumb;
	char x;
	char ds;
	sb.setLength(0);
	boolean minus = false;
	int endPos = startPos + length;
	if(xLine != null) {
		ds = DecForm.getDecimalSeparator().charAt(0);
		for(int i = startPos;i < endPos;i++) {
			x = xLine[i];
			if(!minus && (x == DASH || x == LPAR)) {
				minus = true;
				sb.insert(0,DASH);
			} else if(Character.isDigit(x)) {
				sb.append(x);
			} else if(x == ds || x == PERIOD) {
				// Always use this when putting into nugs
				// Because big decimal pukes
				// Converting to integer or long done....or zero will result
				if(!period) {
					break;
				} else {
					sb.append(PERIOD);
				}
			}
		}
	}
	// No value, return a value
	if(sb.length() == 0) {
		if(period) {
			cleanNumb = ZEROPTZERO;
		} else {
			cleanNumb = ZERO;
		}
	} else {
		cleanNumb = sb.toString();
	}
	return cleanNumb;
}

/**
 * compareTo Compare to value.
 */
public void zero() {
	switch(nuglType) {
		case OTNON:
			break;
		case OTINT:
		case OTLON:
		case OTDBL:
		case OTDAT:
			((ByteBuffer)no).putLong(0,(long)0);
			break;
		case OTSTR:
			no = OT.nst;
			break;
		case OTOBJ:
			no = null;
			break;
		case OTBAR:
			no = null;
			break;
		case OTBIG:
			no = new BigDecimal(0.0);
			break;
		default:
			break;
	}
}

public long compareTo(OT x) {
	OType n = x.eNl();
	long r = 0;
	double d;
	switch(nuglType) {
		case OTNON:
			if(x.eType() != OTNON) {
				r = -1;
			}
			break;
		case OTINT:
			r = eI() - n.eI();
			break;
		case OTSTR:
			r = eS().compareTo(n.eS());
			break;
		case OTOBJ:
			if(this != n) {
				r = -1;
			}
			break;
		case OTLON:
			r = eL() - n.eL();
			break;
		case OTBAR:
			r = eS().compareTo(n.eS());
			break;
		case OTDBL:
			d = eD() - n.eD();
			if(d == 0.0
				|| (d >= 1.0 || d <= -1.0)) {
				r = (long)d;
			} else {
				r = (d < 0.0) ? -1 : 1;
			}
			break;
		case OTDAT:
			r = eL() - n.eL();
			break;
		case OTBIG:
			r = eBD().compareTo(n.eBD());
			break;
		default:
			r = -1;
			break;
	}
	return r;
}

public OT getLabel() {
	return label;
}

/**
 * set label
 *
 * @param n
 */
public void setLabel(OT n) {
	if((label = n) == null) {
		label = OTNSp.lnull;
	}
}

/**
 * aType Assign OType to this OTypes original type. A string for i/o from binary
 *
 * @param data
 * @return value assigned true
 */
public boolean aaType(OT data) {
	boolean r = false;
	// No worries if they are the same, right ??
	if(data != this) {
		// The current type is by definition this type ?
		aCT(data);
		r = true;
	}
	return r;
}

/**
 * assign OType to the original data type
 *
 * @param data
 * @return value assigned - true
 */
public boolean aaT(OT data) {
	return aaType(data);
}

/**
 * assign an original type for i/o
 *
 * @param data
 * @param type
 */
//xxx public void aOrgType(String data,int type) {
//xxx 	aType(data,type);
//xxx }
/**
 * assign an original type for i/o
 *
 * @param data
 * @param type
 */
//xxx public void aOrgType(Object data,int type) {
//xxx 	aType(data,type);
//xxx }
/**
 * Assign a OType to a OType
 *
 * @param n
 * @return
 */
public boolean aNu(OT n) {
	boolean r = true;
	// Create new one if still the original
	aCT(n);
	return r;
}

/**
 * Assign a boolean to a OType
 *
 * @param boo
 * @return
 */
public boolean aBoo(boolean boo) {
	a((boo) ? OType.TRUE : OType.FALSE);
	return boo;
}

/**
 * return OType
 *
 * @return
 */
public OType eNu() {
	return this;
}

/**
 * Equals a guaranteed numeric string (i.e. 0)
 *
 * @return
 */
public String eNs() {
	String x = eS(null);
	if(x.equals(OT.nst)) {
		x = OType.ZERO;
	}
	if(DecForm.getDecimalSeparator().charAt(0) != OType.PERIOD) {
		int ids = x.indexOf(COMMA);
		if(ids >= 0) {
			String a = x.substring(0,ids);
			a += OType.PERIOD;
			if(ids < x.length()) {
				a += x.substring(ids + 1,x.length());
				x = a;
			}
		}
	}
	return x;
}

public boolean eBoo() {
	String s = eS(null);
	if(s.length() == 0) {
		a(OType.FALSE);
		return false;
	}
	boolean r = false;
	if(s != null) {
		char x = s.charAt(0);
		r = (x == t);
	}
	return r;
}
}
