package oneType;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Semantic layer of a OType includes label attributes and child OTypes
 */
public class OT extends OType {
	
/**
 * Factory Class name
 */
public final static String OTCLASS = "oneType.OT";
/**
 * default null string reference
 */
public static final String nst = "";
/**
 * Water mark none
 */
public static final int MARKNON = 0;
/**
 * Water mark add
 */
public static final int MARKADD = 1;
/**
 * Water mark delete
 */
public static final int MARKDEL = 2;
/**
 * Water mark insert
 */
public static final int MARKINS = 3;
/**
 * Water mark clear
 */
public static final int MARKCLR = 4;
/**
 * Water mark update
 */
public static final int MARKUPD = 5;
private LList subOTL = null;
private OT instAt = null;
protected boolean sync;

/**
 * Get the child list
 *
 * @return list or null
 */
public LList getSubOTList() {
	return subOTL;
}

/**
 * Set the underlying child list
 *
 * @param lList
 */
public void setSubOTList(LList lList) {
	subOTL = lList;
}

/**
 * Get current attributes list
 *
 * @return list or null
 */
public OT getInstAt() {
	return instAt;
}

/**
 * Is this OType a label
 *
 * @return true for a label
 */
public boolean isLabel() {
	return (getLabel() == OTNSp.lLt);
}

/**
 * Set instance attribute
 *
 * @param att
 */
public void setInstAt(OT att) {
	// No instaTells for lbs
	if(!isLabel()) {
		instAt = att;
	}
}

/**
 * OType default
 */
public OT() {
	super();
}

/**
 * OType as a boolean
 *
 * @param b
 */
public OT(boolean b) {
	super(b);
}

/**
 * OType as an integer
 *
 * @param i
 */
public OT(int i) {
	super(i);
}

/**
 * OType as a string
 *
 * @param s
 */
public OT(String s) {
	super(s);
}

/**
 * OType as an object unless the object is a label and then its short hand for creating a String
 * OType with a label
 *
 * @param o
 */
public OT(Object o) {
	if(o != null && o.getClass().getName().equals(OT.OTCLASS) && ((OT) o).getLabel() == OTNSp.lLt) {
		nuglType = OTSTR;
		setLabel((OT) o);
	} else {
		nuglType = OTOBJ;
		no = o;
	}
}

/**
 * OType as a long
 *
 * @param l
 */
public OT(long l) {
	super(l);
}

/**
 * OType as a byte array
 *
 * @param bA
 */
public OT(byte[] bA) {
	super(bA);
}

/**
 * OType as an double
 *
 * @param d
 */
public OT(double d) {
	super(d);

}

/**
 * OType as a big decimal
 *
 * @param bD
 */
public OT(BigDecimal bD) {
	super(bD);
}

/**
 * OType as an Date
 *
 * @param dt
 */
public OT(Date dt) {
	super(dt);
}

/**
 * OType labeled as a integer
 *
 * @param i
 * @param lb
 */
public OT(int i,OT lb) {
	super(i);
	setLabel(lb);
}

/**
 * OType labeled as a string
 *
 * @param s
 * @param lb
 */
public OT(String s,OT lb) {
	super(s);
	setLabel(lb);
}

/**
 * OType labeled as an object
 *
 * @param o
 * @param lb
 */
public OT(Object o,OT lb) {
	super(o);
	setLabel(lb);
}

/**
 * OType labeled as a long
 *
 * @param l
 * @param lb
 */
public OT(long l,OT lb) {
	super(l);
	setLabel(lb);
}

/**
 * OType labeled as a byte array
 *
 * @param bA
 * @param lb
 */
public OT(byte[] bA,OT lb) {
	super(bA);
	setLabel(lb);
}

/**
 * OType labeled as a double
 *
 * @param d
 * @param lb
 */
public OT(double d,OT lb) {
	super(d);
	setLabel(lb);
}

/**
 * OType labeled as a big decimal
 *
 * @param bD
 * @param lb
 */
public OT(BigDecimal bD,OT lb) {
	super(bD);
	setLabel(lb);
}

/**
 * OType labeled as a date
 *
 * @param dt
 * @param lb
 */
public OT(Date dt,OT lb) {
	super(dt);
	setLabel(lb);
}

/**
 * OType labeled as a boolean
 *
 * @param b
 * @param lb
 */
public OT(boolean b,OT lb) {
	super(b);
	setLabel(lb);
}

/**
 * Find the child OType with the given label. This is a non recursive search.
 *
 * @param lb
 * @return child or null
 */
public OT getL(OT lb) {
	OT r = null;
	OT po;
	// Garbage in garbage out
	if(subOTL != null && lb != null) {
		if((po = (OT) subOTL.getAt()) != null && po.getLabel() == lb) {
			r = po;
		} else {
			r = getHead();
			while(r != null && r.getLabel() != lb) {
				r = getNext();
			}
		}
	}
	// cleanup
	return r;
}

/**
 * Get the current child position
 *
 * @return OType or null
 */
public OT getCur() {
	if(subOTL != null) {
		return (OT) subOTL.getAt();
	}
	// cleanup
	return null;
}

/**
 * Get the child with the given label and attribute
 *
 * @param lb
 * @param att
 * @return child or null
 */
public OT getL(OT lb,OT att) {
	OT x;
	OT c;
	OT r = null;
	OT po;
	// Garbage in garbage out
	if(lb == null || att == null || subOTL == null) {
		return null;
	}
	if((po = (OT) subOTL.getAt()) != null && po.getLabel() == lb && po.getIA(
		att.getLabel()) != null) {
		return r;
	}
	c = getHead();
	while(r == null && c != null) {
		if(c.getLabel() == lb && (x = c.getIA(att.getLabel())) != null && x.eS().equals(att.eS())) {
			r = c;
		} else {
			c = getNext();
		}
	}
	// cleanup
	return r;
}

/**
 * Find the next child with the given label
 *
 * @param lb
 * @return child or null
 */
public OT getNext(OT lb) {
	OT r = getNext();
	while(r != null && r.getLabel() != lb) {
		r = getNext();
	}
	// cleanup
	return r;
}

/**
 * Recursive Find the OType with the given label. First searches all child OTypes that is breadth
 * wise first then deepens into each child OType in order.
 *
 * @param lb
 * @return OType or null
 */
public OT getLR(OT lb) {
	OT r = null;
	OTCur ncX = OTCur.getInst();
	OT x;
	// Get Children
	x = getHead();
	// Wide first
	while(r == null && x != null) {
		if(x.getLabel() == lb) {
			r = x;
		} else {
			x = getNext();
		}
	}
	// And deep
	if(r == null) {
		x = getHead();
		while(r == null && x != null) {
			ncX.setColl(x);
			if((r = ncX.getLR(lb)) == null) {
				x = getNext();
			}
		}
	}
	// cleanup
	ncX.release();
	return r;
}

/**
 * get recursive, find the OType with given label. Do not search row collections.
 *
 * @param lb
 * @return OType or null
 */
public OT getLRNRC(OT lb) {
	return getLRNRC(lb,null);
}

/**
 * get recursive, find the OType with the given label do not search row collections. If found,
 * assign as an object to the given parent reference the level where the object was found
 *
 * @param lb
 * @param parentRef
 * @return OType or null
 */
public OT getLRNRC(OT lb,OT parentRef) {
	OT r = null;
	OTCur ncX = OTCur.getInst();
	OT x;
	// Check child OTypes
	x = getHead();
	// Wide first
	while(r == null && x != null) {
		if(x.getLabel() == lb) {
			r = x;
			if(parentRef != null) {
				parentRef.aO(this);
			}
		} else {
			x = getNext();
		}
	}
	// And deep
	if(r == null) {
		x = getHead();
		while(r == null && x != null) {
			ncX.setColl(x);
			if(!x.isSrhColl() || (r = ncX.getLRNRC(lb,parentRef)) == null) {
				x = getNext();
			}
		}
	}
	// cleanup
	ncX.release();
	return r;
}

/**
 * Get the given OType as an object.
 *
 * @param o
 * @return OType or null
 */
public OT getN(OT o) {
	OT x;
	if(o != null) {
		if(o == this) {
			x = this;
		} else {
			x = getHead();
			while(x != null) {
				if(x == o) {
					break;
				}
				x = getNext();
			}
		}
	} else {
		x = null;
	}
	return x;
}

/**
 * get recursive, find the given OType as an object
 *
 * @param o
 * @return OType or null
 */
public OT getNR(OT o) {
	OT r = null;
	OT x;
	if(o == this) {
		r = o;
	} else {
		// Wide
		x = getHead();
		while(r == null && x != null) {
			if(o == x) {
				r = x;
			} else {
				x = getNext();
			}
		}
		// Deep
		if(r == null) {
			x = getHead();
			if((r = x.getNR(o)) == null) {
				getNext();
			}
		}
	}
	return r;
}

/**
 * Replace object
 *
 * @param objToReplace
 * @param replacementObj
 * @return replacementObject
 */
public OT replObj(OT objToReplace,OT replacementObj) {
	OT x;
	// Both the same ??? GARBAGE
	if(objToReplace != replacementObj) {
		// Object equal null insert tail
		if(objToReplace == null) {
			insTail(replacementObj);
		} else {
			// Now loop continuing to replace till none
			x = getHead();
			while(x != null) {
				// Old object
				if(x == objToReplace) {
					insBefore(replacementObj);
					del();
					x = getHead();
				} else {
					x = getNext();
				}
			}
		}
	}
	return replacementObj;
}

/**
 * replace recursive Object replace Deep then wide because doesn't matter
 *
 * @param objToReplace
 * @param replacementObj
 * @return replacement Object
 */
public OT replObjR(OT objToReplace,OT replacementObj) {
	OT x;
	// Both the same ??? GARBAGE
	if(objToReplace == replacementObj) {
		return replacementObj;
	}
	// What if it is null?
	if(objToReplace == null) {
		insTail(replacementObj);
		return replacementObj;
	}
	// Loop through all telling all child OTypes to
	x = getHead();
	while(x != null) {
		// don't bother with the one were replacing
		if(x != objToReplace && x.isSrhColl()) {
			x.replObjR(objToReplace,replacementObj);
		}
		x = getNext();
	}
	// Now loop continuing to replace till none
	replObj(objToReplace,replacementObj);
	return replacementObj;
}

/**
 * replace OType with the same label
 *
 * @param replacementObj
 * @return OType or null
 */
public OT replL(OT replacementObj) {
	return replL(replacementObj,false);
}

/**
 * replace the with the same lb
 *
 * @param replacementObj
 * @param mr
 * @return replacmentObj
 */
public OT replL(OT replacementObj,boolean mr) {
	if(replacementObj != null) {
		if(getL(replacementObj.getLabel()) != null) {
			insBefore(replacementObj);
			del();
		} else {
			insTail(replacementObj);
		}
		if(mr) {
			setMr(MARKUPD);
		}
	}
	// cleanup
	return replacementObj;
}

/**
 * replace OType with the same label as a stack (LIFO)
 *
 * @param replacmentObj
 * @return
 */
public OT replHead(OT replacmentObj) {
	OT r;
	if((r = getL(replacmentObj.getLabel())) != null) {
		insBefore(replacmentObj);
		del();
	} else {
		insHead(replacmentObj);
	}
	// cleanup
	return replacmentObj;
}

/**
 * get head child OType
 *
 * @return OType or null
 */
public OT getHead() {
	return (subOTL == null) ? null : (OT) subOTL.getHead();
}

/**
 * get tail child OType
 *
 * @return OType or null
 */
public OT getTail() {
	OT r = null;
	if(subOTL != null) {
		r = (OT) subOTL.getTail();
	}
	// cleanup
	return r;
}

/**
 * delete head child OType
 *
 * @return deleted OType or null
 */
public OT delHead() {
	OT r = null;
	if(subOTL != null) {
		r = (OT) subOTL.delHead();
	}
	// cleanup
	return r;
}

/**
 * delete tail child OType
 *
 * @return return deleted child or null
 */
public OT delTail() {
	OT r = null;
	if(subOTL != null) {
		r = (OT) subOTL.delTail();
	}
	// cleanup
	return r;
}

/**
 * delete the given OType as object
 *
 * @param obj
 * @return deleted OType or null
 */
public OT del(OT obj) {
	return del(obj,false);
}

/**
 * delete the given OType as object
 *
 * @param obj
 * @param mr
 * @return deleted OType or null
 */
public OT del(OT obj,boolean mr) {
	OT r;
	if((r = getN(obj)) != null) {
		del(mr);
	}
	// cleanup
	return r;
}

/**
 * delete current child position
 *
 * @return deleted OType or null
 */
public OT del() {
	return del(false);
}

/**
 * delete the current child OType position
 *
 * @param mr
 * @return deleted OType or null
 */
public OT del(boolean mr) {
	if(mr) {
		setMr(MARKDEL);
	}
	// cleanup
	return (subOTL == null) ? null : (OT) subOTL.del();
}

/**
 * Delete the given label
 *
 * @param lb
 * @return deleted or null
 */
public OT delL(OT lb) {
	OT r;
	if(lb != null) {
		if((r = getL(lb)) != null) {
			del();
		}
	} else {
		r = null;
	}
	// cleanup
	return r;
}

/**
 * Insert OType at head of child list
 *
 * @param n
 * @return OType or null
 */
public OT insHead(OT n) {
	if(n == null) {
		return null;
	}
	if(subOTL == null) {
		subOTL = new LList();
	}
	n = (OT) subOTL.insHead((Object) n);
	// cleanup
	return n;
}

/**
 * Insert alpha on data as string
 *
 * @param n
 * @return inserted or null
 */
public OT insAlpha(OT n) {
	return insAlpha(n,false);
}

/**
 * Insert alpha on data as string
 *
 * @param n
 * @param mr
 * @return inserted or null
 */
public OT insAlpha(OT n,boolean mr) {
	return insAlpha(n,mr,OTNSp.getUseTags());
}

public OT insAlphaRev(OT n,boolean mr) {
	return insAlphaRev(n,mr,OTNSp.getUseTags());
}

/**
 * Insert alpha on data as string
 *
 * @param n
 * @param mr
 * @param useTags
 * @return inserted or null
 */
public OT insAlpha(OT n,boolean mr,boolean useTags) {
	OT c;
	if(n != null) {
		c = getHead();
		while(c != null) {
			if(n.compareTo(c) <= 0) {
				if(useTags) {
					n.getTag();
					setMr(mr,MARKINS,n);
					n.setIA(OTNSp.ltgIn,c.getTag());
				}
				insBefore(n);
				if(useTags) {
					n.delIA(OTNSp.ltgIn);
				}
				break;
			} else {
				c = getNext();
			}
		}
		if(c == null) {
			insTail(n);
			if(useTags) {
				getTag();
			}
			setMr(mr,MARKADD,n);
		}
	}
	// cleanup
	return n;
}

/**
 *
 * @param n collection of entries
 * @param mr
 * @param useTags
 * @return
 */
public OT insAlphaRev(OT n,boolean mr,boolean useTags) {
	OT c;
	if(n != null) {
		c = getHead();
		while(c != null) {
			if(n.compareTo(c) > 0) {
				if(useTags) {
					n.getTag();
					setMr(mr,MARKINS,n);
					n.setIA(OTNSp.ltgIn,c.getTag());
				}
				insBefore(n);
				if(useTags) {
					n.delIA(OTNSp.ltgIn);
				}
				break;
			} else {
				c = getNext();
			}
		}
		if(c == null) {
			insTail(n);
			if(useTags) {
				getTag();
			}
			setMr(mr,MARKADD,n);
		}
	}
	// cleanup
	return n;
}

/**
 * insert OType at tail of child list
 *
 * @param n
 * @return inserted or null
 */
public OT insTail(OT n) {
	return insTail(n,false);
}

/**
 * insert OType at tail of child list
 *
 * @param n
 * @param mr
 * @return inserted or null
 */
public OT insTail(OT n,boolean mr) {
	if(n == null) {
		return null;
	}
	if(subOTL == null) {
		subOTL = new LList();
	}
	n = (OT) subOTL.insTail((Object) n);
	if(mr) {
		setMr();
	}
	// cleanup
	return n;
}

/**
 * insert OType before current position in child list
 *
 * @param n
 * @return
 */
public OT insBefore(OT n) {
	if(n == null) {
		return null;
	}
	if(subOTL == null) {
		subOTL = new LList();
	}
	n = (OT) subOTL.insBefore((Object) n);
	// Are we the 1st link or a new list ??
	return n;
}

/**
 * insert OType after current position in child list
 *
 * @param n
 * @return
 */
public OT insAfter(OT n) {
	if(n == null) {
		return null;
	}
	if(subOTL == null) {
		subOTL = new LList();
	}
	n = (OT) subOTL.insAfter((Object) n);
	return n;
}

/**
 * delete all OTypes from child list
 */
public void delAll() {
	// Why not quickly just wipe the planet?
	subOTL = null;
}

/**
 * get next child OType from child list
 *
 * @return OType or null
 */
public OT getNext() {
	return (subOTL == null) ? null : (OT) subOTL.getNext();
}

/**
 * get previous OType from child list
 *
 * @return OType or null
 */
public OT getPrev() {
	return (subOTL == null) ? null : (OT) subOTL.getPrev();
}

/**
 * get the current child list position position
 *
 * @return OType or null
 */
public OT getAt() {
	OT r;
	if(subOTL != null) {
		r = (OT) subOTL.getAt();
	} else {
		r = null;
	}
	return r;
}

/**
 * read from a file
 *
 * @param sXMLFileName
 * @return OType representation of file or null
 */
public OT readOT(String sXMLFileName) {
	FormatXML pu = FormatXML.getInst();
	OT nu = pu.xRead(sXMLFileName);
	pu.release();
	return nu;
}

/**
 * persist OType to a file. Default is do not use types
 *
 * @return OType or null
 */
public OT persist() {
	return persist(null,true,false);
}

/**
 * persist OType to a file Default is do not use types
 *
 * @param sXMLFileName
 * @return OType or null
 */
public OT persist(String sXMLFileName) {
	return persist(sXMLFileName,true,false);
}

/**
 * writeOT persist OType to a file
 *
 * @param sXMLFileName
 * @param pretty
 * @param useTypes
 * @return OType or null
 */
public OT persist(String sXMLFileName,boolean pretty,boolean useTypes) {
	if(sXMLFileName == null) {
		if(getLabel() == null) {
			sXMLFileName = "null";
		} else {
			sXMLFileName = getLabel().eS();
		}
	}
	// clean persist by default no tags
	FormatXML pu = FormatXML.getInst(false,true,true,false,false);
	pu.setUseTypes(useTypes);
	pu.xWrite(sXMLFileName,this,pretty);
	pu.release();
	return this;
}

/**
 * Assign label
 *
 * @param lb
 */
@Override
public final void setLabel(OT lb) {
	super.setLabel(lb);
}

/**
 * get label as a string
 *
 * @return label or null string
 */
public String eSLabel() {
	if(getLabel() == null) {
		return OT.nst;
	} else {
		return getLabel().eS();
	}
}

/**
 * Clean OType remove child OTypes and attribute lists
 */
public void clean() {
	OT child;
	if(subOTL != null) {
		child = getHead();
		while(child != null) {
			child.clean();
			child = getNext();
		}
		subOTL.clean();
		// These
		subOTL = null;
	}
	// Gotta keep instants ? this was greyed out before
	if(instAt != null) {
		instAt.clean();
		instAt = null;
	}
	super.clear();
}

/**
 * Is this a OType with persistent attributes. This is an indication that it is name space lb.
 *
 * @return true for a persistent attribute
 */
public boolean isPersAt() {
	boolean r = false;
	OT persAt = getC(OTNSp.lPersAt);
	if(persAt != null) {
		OTCur nc = OTCur.getInst(persAt);
		if(nc.getHead() != null) {
			r = true;
		}
		nc.release();
	}
	return r;
}

/**
 * Get persistent attribute list
 *
 * @return	persistent attribute or null
 */
public OT getPersAt() {
	return getPersAt(false);
}

/**
 * get persistent attribute list
 *
 * @param create attribute list if not present
 * @return
 */
public OT getPersAt(boolean create) {
	OT persAt;
	// Add what exactly i am
	// Always comes from the lb right
	if(getLabel() == OTNSp.lLt) {
		// OT Cursor would synchronize anyway
		synchronized (this) {
			if((persAt = getL(OTNSp.lPersAt)) == null && create) {
				if((persAt = getL(OTNSp.lPersAt)) == null) {
					persAt = insHead(new OT(OTNSp.lPersAt));
				}
			}
		}
	} else if(getLabel() != null) {
		// Turn into Label
		persAt = getLabel().getPersAt(create);
	} else {
		// Don't set it just default it
		persAt = null;
	}
	return persAt;
}

/**
 * Get instance Attribute list recursive. If Instance attribute list is null return persistent
 * attribute list
 *
 * @return instance attribute list, persistent attribute list or null
 */
public OT getInstAtR() {
	// death by no speed
	if(instAt == null) {
		return getPersAt();
	}
	return instAt;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param b
 * @return attribute
 */
public OT setIA(OT lb,boolean b) {
	OT c = getIALevel(lb);
	if(c == null) {
		c = new OT(b,lb);
		setIA(c);
	} else {
		c.a(b);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param s
 * @return attribute
 */
public OT setIA(OT lb,String s) {
	OT c = getIALevel(lb);
	if(c == null) {
		c = new OT(s,lb);
		setIA(c);
	} else {
		c.a(s);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param l
 * @return attribute
 */
public OT setIA(OT lb,long l) {
	OT c = getIALevel(lb);
	if(c == null) {
		c = new OT(l,lb);
		setIA(c);
	} else {
		c.aL(l);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param d
 * @return attribute
 */
public OT setIA(OT lb,double d) {
	OT c = getIALevel(lb);
	if(c == null) {
		c = new OT(d,lb);
		setIA(c);
	} else {
		c.a(d);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param i
 * @return
 */
public OT setIA(OT lb,int i) {
	OT c = getIALevel(lb);
	if(c == null) {
		c = new OT(i,lb);
		setIA(c);
	} else {
		c.a(i);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param o
 * @return attribute
 */
public OT setIA(OT lb,Object o) {
	OT c = getIALevel(lb);
	if(c == null) {
		c = new OT(o,lb);
		setIA(c);
	} else {
		c.aO(o);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param att
 * @return attribute
 */
public OT setIA(OT att) {
	OTCur nc = OTCur.getInst(reqInstAt());
	nc.replL(att);
	nc.release();
	// cleanup
	return att;
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param bDef
 * @return attribute or default value
 */
public boolean getIA(OT lb,boolean bDef) {
	boolean r = bDef;
	OT c = getIA(lb);
	if(c != null) {
		if(c.eS() != null && c.eS().length() > 0) {
			char x = c.eS().charAt(0);
			r = (x == 'T' || x == 't');
		}
	}
	return r;
}

/**
 * Get IA default to empty string
 *
 * @param lb
 * @return attribute or default value of null string
 */
public String getI(OT lb) {
	return getIA(lb,nst);
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param sDef
 * @return attribute or default value
 */
public String getIA(OT lb,String sDef) {
	String r = sDef;
	OT c = getIA(lb);
	if(c != null) {
		r = c.eS();
	}
	return r;
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param iDef
 * @return attribute or default value
 */
public int getIA(OT lb,int iDef) {
	int r = iDef;
	OT c = getIA(lb);
	if(c != null) {
		r = c.eI();
	}
	return r;
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param lDef
 * @return attribute or default value
 */
public long getIA(OT lb,long lDef) {
	long r = lDef;
	OT c = getIA(lb);
	if(c != null) {
		r = c.eL();
	}
	return r;
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param dDef
 * @return attribute or default value
 */
public double getIA(OT lb,double dDef) {
	double r = dDef;
	OT c = getIA(lb);
	if(c != null) {
		r = c.eD();
	}
	return r;
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param oDef
 * @return attribute or default value
 */
public Object getIA(OT lb,Object oDef) {
	Object r = oDef;
	OT c = getIA(lb);
	if(c != null) {
		r = c.eO();
	}
	return r;
}

/**
 * get instance attribute only at the instance attribute level
 *
 * @param lb
 * @return attribute or default value
 */
public OT getIALevel(OT lb) {
	OT r = null;
	if(instAt != null) {
		OTCur nc = OTCur.getInst(instAt);
		r = nc.getL(lb);
		nc.release();
	}
	// cleanup
	return r;
}

/**
 * delete from instance attributes not from persistent lb
 *
 * @param lb
 */
public void delIA(OT lb) {
	OTCur nc;
	if(instAt != null) {
		nc = OTCur.getInst(instAt);
		nc.delL(lb);
		nc.release();
	}
}

/**
 * get the instance attribute given by search class instance the label and than persistent
 * attributes from the label object
 *
 * @param lb
 * @return attribute
 */
public OT getIA(OT lb) {
	OT c = null;
	if(getInstAt() != null) {
		OTCur nc = OTCur.getInst(getInstAt());
		c = nc.getL(lb);
		nc.release();
	}
	// Because it will default to tell me
	if(c == null) {
		c = getPA(lb);
	}
	// cleanup
	return c;
}

/**
 * Request guarantee existence of descriptive information for instance of the OType
 *
 * @return instance attribute list
 */
public OT reqInstAt() {
	// No insta tellme for lb	
	if(getLabel() == OTNSp.lLt) {
		return getPersAt(true);
	} else if(instAt == null) {
		// death by no speed
		instAt = new OT((Object) null,OTNSp.lInstAt);
	}
	return instAt;
}

/**
 * Set persistent descriptive information about the OType delete any descriptive information from
 * the OType since its label tells all
 *
 * @param att
 * @return attribute
 */
public OT setPersAt(OT att) {
	OTCur nc, ncTellMe;
	OT c;
	// Assign lb for the overall collection
	OT cTellMe = null;
	// I am really assigning to a lb
	if(getLabel() == OTNSp.lLt) {
		// replacing so replace everything
		if((cTellMe = getL(OTNSp.lPersAt)) == null && att.getLabel() == OTNSp.lPersAt) {
			cTellMe = att;
			insHead(cTellMe);
		} else {
			// Add a tell me to the lb
			if(cTellMe == null) {
				cTellMe = new OT(OTNSp.lPersAt);
				insHead(cTellMe);
			}
			// already there so just replace
			if(att != cTellMe) {
				nc = att.getNC();
				ncTellMe = OTCur.getInst(cTellMe);
				ncTellMe.delAll();
				while((c = nc.getNext()) != null) {
					ncTellMe.insTail(c);
				}
				ncTellMe.release();
				nc.release();
			}
		}
	}
	// cleanup
	return cTellMe;
}

/**
 * get a string value among the child OTypes. Defaults to case not ignored
 *
 * @param s
 * @return OType or null
 */
public OT getStr(String s) {
	return getStr(s,false);
}

/**
 * get a string value among the child OTypes
 *
 * @param s
 * @param ignCase
 * @return OType or null
 */
public OT getStr(String s,boolean ignCase) {
	OT r = null;
	OT c;
	c = getHead();
	while(r == null && c != null) {
		if(ignCase) {
			if(s.equalsIgnoreCase(c.eS())) {
				r = c;
			}
		} else if(s.equals(c.eS())) {
			r = c;
		}
		if(r == null) {
			c = getNext();
		}
	}
	return r;
}

/**
 * get a string value among the child OTypes Recursive
 *
 * @param s
 * @param ignCase
 * @return OType or null
 */
public OT getStrR(String s,boolean ignCase) {
	OT r = null;
	OT c = getHead();
	// Wide
	while(r == null && c != null) {
		if(ignCase) {
			if(s.equalsIgnoreCase(c.eS())) {
				r = c;
			}
		} else if(s.equals(c.eS())) {
			r = c;
		}
		if(r == null) {
			c = getNext();
		}
	}
	// Recurse deep
	if(r == null) {
		c = getHead();
		while(r == null && c != null) {
			if((r = c.getStrR(s,ignCase)) == null) // Next line only if don't find
			{
				c = getNext();
			}
		}
	}
	return r;
}

/**
 * get a string value among the child OTypes that also has the given label
 *
 * @param s
 * @param ignCase
 * @param lb
 * @return
 */
public OT getStrLab(String s,boolean ignCase,OT lb) {
	OT r = getHead();
	if(ignCase) {
		while(r != null && (r.getLabel() != lb || !s.equalsIgnoreCase(r.eS()))) {
			r = getNext();
		}
	} else {
		while(r != null && (r.getLabel() != lb || !s.equals(r.eS()))) {
			r = getNext();
		}
	}
	return r;
}

/**
 * display default diagnostic definition
 */
public void diagnose() {
	diagnose(null);
}

/**
 * display default diagnostic definition with string definition
 *
 * @param sDef
 */
public void diagnose(String sDef) {
	if(sDef != null) {
		System.out.print(sDef);
	}
	System.out.println(toEntireDXString());
}

/**
 * to string current level of OType as an XML string
 *
 * @return XML representation of the OType
 */
@Override
public String toString() {
	String s = "<";
	OT l = getLabel();
	String ls;
	if(l == null) {
		ls = nst;
	} else {
		ls = l.eS();
	}
	s += ls + ">" + eS();
	return s;
}

/**
 * To string all levels of the the OType in an XML representation
 *
 * @return XML representation of the entire OType with pretty
 */
public String toEntireDXString() {
	FormatXML fx = FormatXML.getInst(false,true,true,false,false);
	String r = fx.toEntireXString(this,true,false);
	// cleanup
	fx.release();
	return r;
}

/**
 * to string all levels of the the OType in an XML representation
 *
 * @return XML representation of the entire OType with out pretty
 */
public String toXString() {
	FormatXML dump = FormatXML.getInst(true,true,true,false,true);
	String r = dump.toEntireXString(this,false,false);
	dump.release();
	return r;
}

/**
 * clone the class
 *
 * @return cloned OType
 */
@Override
public Object clone() {
	return clone(false);
}

/**
 * clone the class
 *
 * @param setTag
 * @return cloned OType
 */
public Object clone(boolean setTag) {
	long defaultTag = getIA(OTNSp.ldefault,-1);
	OT r = cloneNoKids(setTag);
	OT c;
	OT x;
	OTCur nc = OTCur.getInst(this);
	boolean updateDefault = false;
	// Assign sub OTypes
	while((c = nc.getNextIgn()) != null) {
		if(c.getLabel() != OTNSp.lPersAt) {
			// When cloneing default gets updated so change default
			if(defaultTag > -1 && c.getIA(OTNSp.ltg,(long) -1) == defaultTag) {
				updateDefault = true;
			}
			// Set tags through the tree for sync
			x = (OT) c.clone(setTag);
			// Update the default selected tag
			if(updateDefault) {
				r.setIA(OTNSp.ldefault,x.getTagVal());
				updateDefault = false;
			}
			r.insTail(x);
		}
	}
	// cleanup
	nc.release();
	return r;
}

/**
 * clone new row for the current OType collection
 *
 * @return cloned row
 */
public OT cloneRow() {
	OT row = isRowColl();
	if(row != null) {
		row = (OT) row.clone((getIA(OTNSp.lenabled,false)));
		row.setMr(MARKADD);
		// Should a row be tagged?
		row.getTag();
	}
	// cleanup
	return row;
}

/**
 * cloneClean clone no kids, no attributes
 *
 * @return cloned OType
 */
public OT cloneClean() {
	OT r = (OT) clone(new OT());
	r.setLabel(getLabel());
	// cleanup
	return r;
}

/**
 * cloneNoKids this one just this level, no child OTypes, No data
 *
 * @return
 */
public OT cloneNoKids() {
	return cloneNoKids(false);
}

/**
 * clone just this level, no child OTypes, No data
 *
 * @param setTag
 * @return cloned OType
 */
public OT cloneNoKids(boolean setTag) {
	OT r, tag;
	if(getLabel() == OTNSp.lLt) {
		r = new OT(this);
		if(setTag) {
			r.getTag();
		}
	} else {
		r = new OT();
		r.setLabel(getLabel());
		clone(r);
		//
		if(instAt != null) {
			r.instAt = (OT) instAt.clone();
			tag = r.instAt.getL(OTNSp.ltg);
			// Tags are unique even on a clone
			if(tag != null) {
				tag.aL(OTNSp.getCollTag());
			} else if(setTag) {
				r.getTag();
			}
		} else if(setTag) {
			r.getTag();
		}
	}
	return r;
}

/**
 * equal potential digit length
 *
 * @return length
 */
@Override
public int edLen() {
	int n;
	OT c;
	if((c = getIA(OTNSp.llength)) != null) {
		n = c.eI();
	} else {
		n = super.edLen();
	}
	//	The length includes the fraction so don't add it
	// else n += getIA(lfraction,0);
	if(n > 0) {
		n += ePotFixLen();
	}
	// cleanup
	return n;
}

/**
 * equal potential suffix prefix and junk length
 *
 * @return length
 */
public int ePotFixLen() {
	int n = 0;
	// This could be a date or anything else so
	// for now let's just add one for the minus
	switch(eType()) {
		case OType.OTDBL:
		case OType.OTBIG:
			n += 2;
			break;
		case OType.OTINT:
		case OType.OTLON:
			n++;
			break;
		default:
			break;
	}
	// cleanup
	return n;
}

/**
 * Set the default row for a collection returns the current row because that is what you are going
 * to tell the world
 *
 * @return OType or null
 */
public OT setDefColl() {
	OT cr = null;
	OT def = getIA(OTNSp.ldefault);
	long tag;
	if(def != null) {
		tag = getIA(OTNSp.ldefault,-1);
		if(tag > -1) {
			cr = getCollTagRow(tag);
		}
		if(cr == null && (cr = getHead()) != null) {
			setIA(OTNSp.ldefault,cr.getTagVal());
		} else {
			setIA(OTNSp.ldefault,-1);
		}
	}
	// cleanup
	return cr;
}

/**
 * set the current collection default attribute to the current collection OType element
 *
 * @return tag value
 */
public long setDefColltg() {
	OT orb;
	long cTag = -1;
	long orgTag;
	if(OTNSp.getUseTags()) {
		orgTag = getIA(OTNSp.ldefault,-1);
		if((orb = getCollCur()) != null) {
			cTag = orb.getTagVal();
		}
		// Only change if not the same
		if(cTag != orgTag) {
			setIA(OTNSp.ldefault,cTag);
			// Implys must have changed something
			setMr(getIA(OTNSp.lpersistDefault,true),MARKADD);
		}
	}
	return cTag;
}

/**
 * update and handle that an incomplete row may be updated or added
 *
 * @param updToRow
 * @param updFrRow
 * @return updated to row
 */
public boolean updCollRowFrRow(OT updToRow,OT updFrRow) {
	OTCur ncTo = OTCur.getInst(updToRow);
	OTCur ncFr = OTCur.getInst(updFrRow);
	OT cf, ct;
	boolean r = false;
	while((cf = ncFr.getNext()) != null) {
		// Found lb and tags by default the same ????
		if((ct = ncTo.getL(cf.getLabel())) != null) {
			// only update if not equal
			if(ct.compareTo(cf) != 0) {
				ct.aCT(cf);
				r = true;
			}
			if(ct.isSrhColl()) {
				r = updCollRowFrRow(ct,cf);
			}
		}
	}
	// cleanup
	ncTo.release();
	ncFr.release();
	return r;
}

/**
 * Add add item to a collection bag. This assumes that a given row is a complete row.
 *
 * @param nRow
 * @return OType row or null
 */
public OT addColl(OT nRow) {
	OT rosy;
	// Garbage in, garbage out
	if(nRow == null) {
		if(((rosy = getPA(OTNSp.lrow)) == null || (nRow = (OT) rosy.clone(true)) == null)) {
			return nRow;
		}
	}
	// Add the mark
	setMr(OTNSp.getUseMr(),MARKADD,nRow);
	// Add a tag and mark only once
	if(OTNSp.getUseTags()) {
		nRow.getTag();
	}
	// Insert tail
	insTail(nRow);
	// Make sureCreate on first try and make current
	setCollCur(nRow);
	// cleanup
	return nRow;
}

/**
 * Add before the current cursor position is a complete row.
 *
 * @param nRow
 * @return OType row or null
 */
public OT insInColl(OT nRow) {
	OT rosy = getPersAt().getL(OTNSp.lrow);
	// Garbage in, garbage out
	if(nRow == null) {
		if((rosy == null || (nRow = (OT) rosy.clone(true)) == null)) {
			return nRow;
		}
	}
	// Add the mark
	setMr(OTNSp.getUseMr(),MARKADD,nRow);
	// Add a tag and mark only once
	if(OTNSp.getUseTags()) {
		nRow.getTag();
	}
	// Insert before current
	insBefore(nRow);
	// make current
	setCollCur(nRow);
	return nRow;
}

/**
 * Update the tag row from the given reference row
 *
 * @param refRow
 * @return reference row
 */
public int updCollTagRow(OT refRow) {
	int rowMr = MARKNON;
	long tg = refRow.getTagVal();
	long tgRf = refRow.getIA(OTNSp.ltgRf,tg);
	long cTg;
	OTCur nc = this.getNC();
	OT cRow;
	boolean didTree = false;
	// theoretically makes trees faster
	if(nc.getCollType() == OTCur.TREE) {
		// if sort is not available then should bomp right away?
		if((cRow = nc.getTreeFrRef(refRow)) != null) {
			didTree = true;
			if(updCollRowFrRow(cRow,refRow)) {
				rowMr = MARKUPD;
			}
		}
	}
	if(!didTree) {
		while((cRow = nc.getNext()) != null) {
			cTg = cRow.getTagVal();
			// ref equal to 
			if(cTg == tgRf) {
				// the tags the same, same row
				if(tg != cTg) {
					if(updCollRowFrRow(cRow,refRow)) {
						rowMr = MARKUPD;
					}
				}
				break;
			}
		}
		// didn't find it, add it
		if(cRow == null) {
			addColl(refRow);
			rowMr = MARKADD;
		}
	}
	// cleanup
	nc.release();
	return rowMr;
}

/**
 * Replace via attempt to add the given value to the collection if the row doesn't exist, create the
 * row return the row of the collection change the mark to add or change or delete ONLY return newly
 * created rows
 *
 * @param data
 * @param addColl
 * @return
 */
public OT replCollTagCol(OT data,OT addColl) {
	OT cRow = getCollTagRowCol(data.getIA(OTNSp.ltg,-1));
	OT cCol;
	// If not there, add row and replace this tag
	if(cRow == null) {
		cRow = addColl(null);
		if(addColl != null) {
			addColl.a(true);
		}
		// Always will have a
		cRow.replL(data);
	} else {
		if(addColl != null) {
			addColl.a(false);
		}
		cCol = cRow.getL(data.getLabel());
		cCol.aCT(data);
		setMr(OTNSp.getUseMr(),MARKUPD,cRow);
	}
	// cleanup
	return cRow;
}

/**
 * Get the given OType with the label value and given string data value
 *
 * @param lb
 * @param nDat
 * @return
 */
public OT getData(OT lb,OT nDat) {
	String s = nDat.eS();
	OT r = getHead();
	while(r != null && (r.getLabel() != lb || !s.equalsIgnoreCase(r.eS()))) {
		r = getNext();
	}
	return r;
}

/**
 * Is the collection empty
 *
 * @return collection empty or not
 */
public boolean isEmptyColl() {
	boolean r = true;
	OTCur nc = getNC();
	if(nc.getHead() != null) {
		r = false;
	}
	// cleanup
	nc.release();
	return r;
}

/**
 * Get the default in collection the collection and set the collection to it
 *
 * @return default OType or null
 */
public OT getDefaultColl() {
	OT r = null;
	OT c;
	OTCur nc = null;
	long tag = getIA(OTNSp.ldefault,-1);
	if(tag >= 0) {
		nc = getNC();
		while(r == null && (c = nc.getNext()) != null) {
			if(c.getTag().eL() == tag) {
				r = c;
			}
		}
		if(r != null) {
			setCollCur(r);
		}
	}
	// cleanup
	if(nc != null) {
		nc.release();
	}
	return r;
}

public OT setColl(OT data) {
	return setColl(data,true);
}

/**
 * set this class into a collection looping the loop is far better then setting pointers under the
 * covers which could end up in death for one loop but not another
 *
 * @param data
 * @param setAtt
 * @return
 */
public OT setColl(OT data,boolean setAtt) {
	OTCur nc = OTCur.getInst(data);
	OTCur ncThis = OTCur.getInst(this);
	OT c;
	// For better all worse del all
	ncThis.delAll();
	// No cursor cause nobody better be using while we blow smoke
	while((c = nc.getNextIgn()) != null) {
		ncThis.insTail(c);
	}
	// finally make sure the current selected is set
	if(setAtt) {
		if(data != null && (c = data.getIA(OTNSp.ldefault)) != null) {
			setIA(OTNSp.ldefault,c.eL());
			getDefaultColl();
		} else {
			setIA(OTNSp.ldefault,-1);
		}
		setMr(true,MARKADD);
	}
	// cleanup
	nc.release();
	ncThis.release();
	return this;
}

/**
 * Add column to row based table
 *
 * @param coldefault
 * @param afterLb
 */
public void addColumn(OT coldefault,OT afterLb) {
	OT tellrow = getPersAt().getL(OTNSp.lrow);
	OT nunk;
	OTCur nc = null;
	// Reformat order no matter what
	delIA(OTNSp.lrowOrder);
	if(tellrow != null) {
		if(afterLb != null && tellrow.getL(afterLb) != null) {
			nunk = (OT) coldefault.clone();
			tellrow.insAfter(nunk);
		}
		nc = getNC();
		OT cRow;
		while((cRow = nc.getNext()) != null) {
			if(afterLb == null) {
				cRow.insTail((OT) coldefault.clone());
			} else if(cRow.getL(afterLb) != null) {
				nunk = (OT) coldefault.clone();
				cRow.insAfter(nunk);
			}
		}
	}
	// cleanup
	if(nc != null) {
		nc.release();
	}
}

/**
 * Delete Column from row based table
 *
 * @param lbDelCol
 */
public void delColumn(OT lbDelCol) {
	OTCur nc = getNC();
	OT tellrow = getPersAt().getL(OTNSp.lrow);
	OT cRow;
	if(tellrow != null) {
		tellrow.delL(lbDelCol);
		delIA(OTNSp.lrowOrder);
	}
	while((cRow = nc.getNext()) != null) {
		cRow.delL(lbDelCol);
	}
	// cleanup
	nc.release();
}

/**
 * Is this OType a collection Do I have data or am i a row ??? than I must be a collection
 *
 * @return true if a collection
 */
public boolean isColl() {
	boolean r = false;
	if(getLabel() != null && getLabel() != OTNSp.lLt) {
		// the attribute a collection has been set
		if(getPA(OTNSp.lcollection,false)) {
			r = true;
		} else if(getPA(OTNSp.lrow) != null) {// A row in the tellme proves a collection
			r = true;
		} else if(getPA(OTNSp.ldefDisp,OT.nst).contains("Coll") || eSLabel().contains("Coll")) {
			// Now try by lb name
			r = true;
		}
	}
	return r;
}

/**
 * Is this a search collection thus a NoRowColl
 *
 * @return true for collections that are not row collection
 */
public boolean isSrhColl() {
	boolean r = isColl();
	if(r) {
		if(!getIA(OTNSp.lsrhColl,true) || getPA(OTNSp.lrow) != null) {
			r = false;
		}
	}
	return r;
}

/**
 * IsRowColl Return collections that are row collection
 *
 * @return true if a row collection
 */
public OT isRowColl() {
	OT r = null;
	if(isColl()) {
		r = getPA(OTNSp.lrow);
	}
	return r;
}

/**
 * is this a no row collection.
 *
 * @return a collection that has no row definition
 */
public boolean isNoRowColl() {
	boolean r = false;
	if(isColl() && getPA(OTNSp.lrow) == null) {
		r = true;
	}
	// cleanup
	return r;
}

/**
 * Get given label safely from child list with cursor
 *
 * @param lb
 * @return
 */
public OT getC(OT lb) {
	OTCur nc = getNC();
	OT r = nc.getL(lb);
	// cleanup
	nc.release();
	return r;
}

/**
 * Insert tail of child list safely through cursor
 *
 * @param n
 * @return inserted OType or null
 */
public OT insTailC(OT n) {
	OTCur nc = getNC();
	OT r = nc.insTail(n);
	// cleanup
	nc.release();
	return r;
}

/**
 * Delete label from child list safely with a cursor
 *
 * @param lb
 * @return deleted OType or null
 */
public OT delLC(OT lb) {
	OTCur nc = getNC();
	OT r = nc.delL(lb);
	// cleanup
	nc.release();
	return r;
}

/**
 * Delete OType from child list safely with a cursor
 *
 * @param n the OType to delete
 * @return deleted OType or null
 */
public OT delC(OT n) {
	OTCur nc = getNC();
	OT r = nc.del(n);
	// cleanup
	nc.release();
	return r;
}

/**
 * Get given string from child list safely using cursor
 *
 * @param s
 * @return found OType or null
 */
public OT getC(String s) {
	OTCur nc = getNC();
	OT r = nc.getStr(s);
	// cleanup
	nc.release();
	return r;
}

/**
 * Get cursor for OTypes child list
 *
 * @return cursor for OType
 */
public OTCur getNC() {
	return OTCur.getInst(this);
}

/**
 * get current child list OType
 *
 * @return current child OType or null
 */
public OT getCollCur() {
	OT r = getAt();
	return r;
}

/**
 * Set current child list position
 *
 * @param n
 * @return OType for position
 */
public OT setCollCur(OT n) {
	// Don't even try a null
	if(n != null) {
		// already the value
		if(getAt() != n) {
			// We got to search the list because have to set the pointers underneath
			n = getN(n);
		}
	}
	// Assign the object
	//xxx aO(r);
	// Byte the bullet and keep the current position always
	setDefColltg();
	return n;
}

/**
 * Create a hash table with every child currently this bag. This just sets the hash flag for OType
 */
public void crtHash() {
	// You can remake a hash after removing all the the label should be left
	setIA(OTNSp.lhash,1);
}

/**
 * Remove hash turn the OType back into a collection
 */
public void remHash() {
	OT instAtR;
	if(getIA(OTNSp.lhash,0) == 1) {
		OT hash = new OT();
		hash.setCollCur(getCollCur());
		setCollCur(null);
		OTCur nc = getNC();
		OTCur nchash = hash.getNC();
		OTCur nchashElement = OTCur.getInst();
		OTCur nchashElement2 = OTCur.getInst();
		OTCur nchashElement3 = OTCur.getInst();
		OT nche2, nche3;
		OT nch, nche;
		while((nch = nchash.getNextIgn()) != null) {
			nchashElement.setColl(nch);
			while((nche = nchashElement.getNextIgn()) != null) {
				nchashElement2.setColl(nche);
				while((nche2 = nchashElement2.getNextIgn()) != null) {
					nchashElement3.setColl(nche2);
					while((nche3 = nchashElement3.getNextIgn()) != null) {
						nc.insTail(nche3);
					}
				}
			}
		}
		if(instAt != null) {
			instAt.delL(OTNSp.lhash);
		}
		if((instAtR = getInstAtR()) != null) {
			instAtR.delL(OTNSp.lref);
		}
		// cleanup
		nc.release();
		nchash.release();
		nchashElement.release();
		nchashElement2.release();
		nchashElement3.release();
	}
}

/**
 * Add OType has a hashed element
 *
 * @param n
 * @return hashed OType
 */
public OT addHash(OT n) {
	return addHash(n,false);
}

/**
 * add the given element to the hash table
 *
 * @param n
 * @param mk
 * @return hashed element
 */
public OT addHash(OT n,boolean mk) {
	OTCur ncgotHashElement;
	OT r = null;
	// Don't really look for duplicates right now
	ncgotHashElement = getHashElement(n.eS());
	ncgotHashElement.insAlpha(n,mk,false);
	ncgotHashElement.release();
	setMr(mk,MARKADD);
	// cleanup
	return r;
}

/**
 * get the given element from the hash table
 *
 * @param it String to find
 * @return
 */
public OT getHash(OT it) {
	return getHash(it.eS());
}

/**
 * get the given element in the hash table
 *
 * @param sit String to find in table
 * @return
 */
public OT getHash(String sit) {
	OT found;
	OTCur nc;
	nc = getHashElement(sit);
	found = nc.getStr(sit);
	nc.release();
	return found;
}

/**
 * Find the hash element for the given string
 *
 * @param sit string key for hash
 * @return hash element cursor
 */
public OTCur getHashElement(String sit) {
	return getHashElement(sit,null,null,null);
}

/**
 * Find the hash element for the given string
 *
 * @param sit string key for hash
 * @param nchash
 * @param nchash2
 * @param nchash3
 * @return hash element cursor
 */
public OTCur getHashElement(String sit,OTCur nchash,OTCur nchash2,
	OTCur nchash3) {
	OT gotHashElement = null;
	char c;
	char c2;
	char c3;
	OT cHashElement = null;
	OT cHashElement2 = null;
	OT cHashElement3 = null;
	boolean locnch = false;
	// Garbage protector
	if(sit.length() <= 0) {
		c = '_';
	} else {
		c = sit.charAt(0);
		c = Character.toUpperCase(c);
		if(c <= ' ') {
			c = '_';
		}
		//ign		}
	}
	if(sit.length() < 2) {
		c2 = '_';
	} else {
		c2 = sit.charAt(1);
		c2 = (c2 <= ' ') ? '_' : Character.toLowerCase(c2);
	}
	if(sit.length() < 3) {
		c3 = '_';
	} else {
		c3 = sit.charAt(2);
		c3 = (c3 <= ' ') ? '_' : Character.toLowerCase(c3);
	}
	if(nchash == null) {
		nchash = OTCur.getInst();
		locnch = true;
		nchash.setColl(this);
		nchash2 = OTCur.getInst();
		nchash3 = OTCur.getInst();
	}
	// Find in hash table
	while(gotHashElement == null && (cHashElement = nchash.getNextIgn()) != null) {
		// Ignore case ??
		if(c == cHashElement.eS().charAt(0)) {
			nchash2.setColl(cHashElement);
			while(gotHashElement == null && (cHashElement2 = nchash2.getNextIgn()) != null) {
				if(c2 == cHashElement2.eS().charAt(0)) {
					nchash3.setColl(cHashElement2);
					while(gotHashElement == null && (cHashElement3 = nchash3.getNextIgn()) != null) {
						if(c3 == cHashElement3.eS().charAt(0)) {
							// Found hash element
							gotHashElement = cHashElement3;
						}
					}
					// Third Level add
					if(gotHashElement == null) {
						gotHashElement = new OT(OT.nst + c3);
						nchash3.insAlpha(gotHashElement,false,false);
					}
				}
			}
			// secondary add
			if(gotHashElement == null) {
				cHashElement2 = new OT(OT.nst + c2);
				nchash2.insAlpha(cHashElement2,false,false);
				nchash3.setColl(cHashElement2);
				gotHashElement = new OT(OT.nst + c3);
				nchash3.insAlpha(gotHashElement,false,false);
			}
		}
	}
	// No has element add
	if(gotHashElement == null) {
		// Layer one and layer two
		cHashElement = new OT(OT.nst + c);
		nchash.insAlpha(cHashElement,false,false);
		nchash2.setColl(cHashElement);
		cHashElement2 = new OT(OT.nst + c2);
		nchash2.insAlpha(cHashElement2,false,false);
		nchash3.setColl(cHashElement2);
		gotHashElement = new OT(OT.nst + c3);
		nchash3.insAlpha(gotHashElement,false,false);
	}
	// cleanup
	if(locnch) {
		nchash.release();
		nchash2.release();
		nchash3.release();
	}
	return gotHashElement.getNC();
}

/**
 * get the given element in the hash table
 *
 * @param sit,
 * @param refLabel String to find in table
 * @return
 */
public OT getHash(String sit,OT refLabel) {
	OT found = null;
	OTCur nchashElement = null;
	// Check for asking hash
	if(refLabel != null) {
		nchashElement = getHashElement(sit);
		OT nce;
		OT cre;
		// Find the row element with value equal to given
		while(found == null && (nce = nchashElement.getNextIgn()) != null) {
			//xxx		if((cre = nce.getL(refLabel)) != null && ((ignoreCase && cre.eS().equalsIgnoreCase(
			//xxx			sit)) || cre.eS().equals(sit))) {
			if((cre = nce.getL(refLabel)) != null && cre.eS().equals(sit)) {
				found = cre;
			}
		}
	}
	// cleanup
	if(nchashElement != null) {
		nchashElement.release();
	}
	return found;
}

/**
 * Create a new hash table on the given reference
 *
 * @param refLabel
 * @param tableLabel reference lb for created hash table
 * @return created hash table
 */
public OT crtHash(OT refLabel,OT tableLabel) {
	OT hashTable = new OT(refLabel,tableLabel);
	OTCur ncit = getNC();
	OT it;
	// loop through every string currently in this array
	while((it = ncit.getNextIgn()) != null) {
		hashTable.addHash(it);
	}
	// cleanup
	ncit.release();
	return hashTable;
}

/**
 * add the given element to the hash table with the reference attribute lb
 *
 * @param it row to add
 * @param refLabel reference lb to sequence through
 * @param refLabel2 secondary reference for sorting
 * @return reference to current Hash element
 */
public OT addHash(OT it,OT refLabel,OT refLabel2) {
	OT cgh;
	boolean found = false;
	int bit;
	String s, sref;
	OT h2;
	OT he;
	OTCur nchashe = null;
	OTCur nchash = OTCur.getInst();
	OTCur nchash2 = OTCur.getInst();
	OTCur nchash3 = OTCur.getInst();
	OTCur ncgotHashElement = null;
	OTCur ncRow = it.getNC();
	//No matter what I am going to mark my table as an add
	setMr(OTNSp.getUseMr(),MARKADD);
	// Salt the return
	OT nextHash = null;
	OT cre2;
	String sref2 = null;
	OT cre = ncRow.getL(refLabel);
	if(cre != null) {
		nextHash = new OT(getLabel());
		nextHash.setIA(OTNSp.ltgIn,-1);
		nextHash.insTail(it);
		sref = cre.eS();
		if(sref.equals("")) {
			return null;
		}
		if(refLabel2 != null) {
			if((cre2 = ncRow.getL(refLabel2)) != null) {
				sref2 = cre2.eS();
			}
		}
		ncgotHashElement = getHashElement(sref,nchash,nchash2,nchash3);
		ncgotHashElement.clear();
		while(!found && (cgh = ncgotHashElement.getNext()) != null) {
			ncRow.setColl(cgh);
			if((cre = ncRow.getL(refLabel)) != null) {
				s = cre.eS();
				if((bit = sref.compareTo(s)) <= 0) {
					// Less than 1st reference or no 2nd reference
					if(bit < 0 || refLabel2 == null) {
						ncgotHashElement.insBefore(it);
						it = null;
						nextHash.setIA(OTNSp.ltgIn,cre.getIA(OTNSp.ltg,-1));
						found = true;
					} // nowcheck 2nd lb of this one for being less than the current
					else if(bit == 0) {
						if((cre = ncRow.getL(refLabel2)) != null) {
							s = cre.eS();
							if((bit = sref2.compareTo(s)) < 0) {
								ncgotHashElement.insBefore(it);
								it = null;
								nextHash.setIA(OTNSp.ltgIn,cre.getIA(OTNSp.ltg,-1));
								found = true;
							} else if(bit == 0) {
								found = true;
								// don't add duplicates
								if(it.getIA(OTNSp.ltg,-1) == cgh.getIA(OTNSp.ltg,-1)) {
									it = null;
									nextHash = null;
								}
							}
						}
					} else {
						while(!found && (cgh = ncgotHashElement.getNextIgn()) != null) {
							ncRow.setColl(cgh);
							if((cre = ncRow.getL(refLabel)) != null) {
								s = cre.eS();
								// found 1st level reference greater than
								bit = sref.compareTo(s);
								if(bit < 0) {
									ncgotHashElement.insBefore(it);
									it = null;
									nextHash.setIA(OTNSp.ltgIn,cre.getIA(OTNSp.ltg,-1));
									found = true;
								} else if((cre = ncRow.getL(refLabel2)) != null) {
									s = cre.eS();
									bit = sref2.compareTo(s);
									if(bit < 0) {
										ncgotHashElement.insBefore(it);
										it = null;
										nextHash.setIA(OTNSp.ltgIn,cre.getIA(OTNSp.ltg,-1));
										found = true;
									} else if(bit == 0) {
										found = true;
										// don't add duplicates
										if(it.getIA(OTNSp.ltg,-1) == cgh.getIA(OTNSp.ltg,-1)) {
											it = null;
											nextHash = null;
										} else {
											ncgotHashElement.insBefore(it);
											it = null;
											nextHash.setIA(OTNSp.ltgIn,cre.getIA(OTNSp.ltg,-1));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		// Search for next record for insert before
		if(it != null) {
			ncgotHashElement.insTail(it);
			cre = ncgotHashElement.getNextIgn();
			while(cre == null && nchash2 != null) {
				if(nchashe == null || (cre = nchashe.getNextIgn()) == null) {
					if((he = nchash2.getNextIgn()) != null) {
						nchashe = he.getNC();
					} else if((h2 = nchash.getNextIgn()) != null) {
						nchash2 = h2.getNC();
						if((he = nchash2.getNextIgn()) != null) {
							nchashe = he.getNC();
						} else {
							nchashe = null;
						}
					} else {
						nchash2 = null;
					}
				}
			}
			// Never found nothing so just insert
			// Alpha search through rows baby
			if(cre != null) {
				nextHash.setIA(OTNSp.ltgIn,cre.getIA(OTNSp.ltg,-1));
			}
		}
	}
	// cleanup
	if(nchashe != null) {
		nchashe.release();
	}
	nchash.release();
	nchash2.release();
	nchash3.release();
	if(ncgotHashElement != null) {
		ncgotHashElement.release();
	}
	ncRow.release();
	return nextHash;
}

/**
 * add the given element to the hash table with the reference attribute lb
 *
 * @param refElement row to add
 * @param refElement2 lb to sequence through
 * @return reference to current Hash element
 */
public OT getHash(OT refElement,OT refElement2) {
	OT it = null;
	OTCur ncgotHashElement;
	OTCur ncRow = OTCur.getInst();
	OT cre, cgh;
	String s;
	String sref2 = null;
	boolean found = false;
	if(refElement != null) {
		String sref = refElement.eS();
		if(refElement2 != null) {
			sref2 = refElement2.eS();
		}
		// Fall down the hash tree
		ncgotHashElement = getHashElement(sref);
		// Alpha search through rows baby
		while(!found && (cgh = ncgotHashElement.getNextIgn()) != null) {
			ncRow.setColl(cgh);
			if((cre = ncRow.getL(refElement.getLabel())) != null) {
				s = cre.eS();
//xxx				if((ignoreCase && sref.equalsIgnoreCase(s)) || sref.equals(s)) {
				if(sref.equals(s)) {
					if(refElement2 == null) {
						it = cgh;
						found = true;
					} else if((cre = ncRow.getL(refElement2.getLabel())) != null) {
						s = cre.eS();
//xxx						if((ignoreCase && sref2.equalsIgnoreCase(s)) || sref2.equals(s)) {
						if(sref2.equals(s)) {
							it = cgh;
							found = true;
						}
					}
				}
			}
		}
		// cleanup
		if(ncgotHashElement != null) {
			ncgotHashElement.release();
		}
	}
// cleanup
	ncRow.release();
	return it;
}

/**
 * Get a child with an instance attribute that equals given. Search is cursor protected
 *
 * @param rl Reference instance attribute lb
 * @param rs reference string value of attribute to equate
 * @return found or null
 */
public OT getIARef(OT rl,String rs) {
	OTCur nc = OTCur.getInst(this);
	OT c, ia;
	while((c = nc.getNext()) != null) {
		if((ia = c.getIA(rl)) != null && ia.eS().equals(rs)) {
			break;
		}
	}
	// cleanup
	nc.release();
	return c;
}

/**
 * Recursive Find with a reference equaling a string
 *
 * @param l to equal
 * @param rl attibute lb
 * @param sr tring value of attribute to equate
 * @return found or null
 */
public OT getRRef(OT l,
	OT rl,
	String sr) {
	OTCur nc = OTCur.getInst(this);
	OT x;
	OT r = null;
	while(r == null && (x = nc.getNext()) != null) {
		if(x.getLabel() == l && (sr.equals(x.getIA(rl,"")))) {
			r = x;
		} else {
			r = x.getRRef(l,rl,sr);
		}
	}
	// cleanup
	nc.release();
	return r;
}

/**
 * Insert collection into current collection
 *
 * @param collToInsert
 */
public void insColl(OT collToInsert) {
	if(collToInsert != null) {
		OTCur ncCur = getNC();
		OTCur nctoIns = collToInsert.getNC();
		OT c;
		while((c = nctoIns.getNext()) != null) {
			ncCur.insTail(c);
		}
		// cleanup
		ncCur.release();
		nctoIns.release();
	}
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param def
 * @return
 */
public double getPA(OT lb,double def) {
	double r = def;
	OT c = getPA(lb);
	if(c != null) {
		r = c.eI();
	}
	return r;
}

/**
 * delete the given lb from the current level of definition
 *
 * @param lb
 * @return deleted element
 */
public OT delPA(OT lb) {
	OT r = null;
	OT persAt = getPersAt();
	if(persAt != null) {
		OTCur nc = OTCur.getInst(persAt);
		r = nc.delL(lb);
		nc.release();
	}
	// cleanup
	return r;
}

/**
 * Return the label tell me attribute period for the given lb
 *
 * @param lb
 * @return
 */
public OT getPA(OT lb) {
	OTCur nc = null;
	OT persAt = getPersAt();
	OT c = null;
	String sDefDisp;
	OT defDisp, defDispPersAt;
	if(persAt != null) {
		nc = OTCur.getInst(persAt);
		c = nc.getL(lb);
	}
	// Now look in the Object type types attributes
	// Really this is sending OTs into display types
	if(c == null) {
		if(OTNSp.ldefDisp != this && nc != null && (defDisp = nc.getL(OTNSp.ldefDisp)) != null
			&& (sDefDisp = defDisp.eS()) != null && !sDefDisp.equals(OT.nst)
			&& (defDispPersAt = OTNSp.really(sDefDisp).getPersAt()) != null) {
			c = defDispPersAt.getC(lb);
		}
	}
	// cleanup
	if(nc != null) {
		nc.release();
	}
	return c;
}

/**
 * Return the lb tell me attribute given
 *
 * @param lb
 * @param def
 * @return
 */
public int getPA(OT lb,int def) {
	int r = def;
	OT c = getPA(lb);
	if(c != null) {
		r = c.eI();
	}
	return r;
}

/**
 * Return the lb tell me attribute given
 *
 * @param lb
 * @param def
 * @return
 */
public long getPA(OT lb,long def) {
	long r = def;
	OT c = getPA(lb);
	if(c != null) {
		r = c.eL();
	}
	return r;
}

/**
 * Default return as a string or empty string
 *
 * @param lb
 * @return
 */
public String getP(OT lb) {
	return getPA(lb,nst);
}

/**
 * Return the lb tell me attribute given
 *
 * @param lb
 * @param def
 * @return
 */
public String getPA(OT lb,String def) {
	String r = def;
	OT c = getPA(lb);
	if(c != null) {
		r = c.eS();
	}
	return r;
}

/**
 * Return the instance attribute given
 *
 * @param lb
 * @param def
 * @return
 */
public boolean getPA(OT lb,boolean def) {
	boolean r = def;
	OT c = getPA(lb);
	if(c != null) {
		r = c.eBoo();
	}
	return r;
}

/**
 * Set the given instance attribute
 *
 * @param value
 * @return
 */
public OT setPA(OT value) {
	OTCur nc = OTCur.getInst(getPersAt(true));
	nc.replL(value);
	// cleanup
	nc.release();
	return value;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param def
 * @return
 */
public OT setPA(OT lb,boolean def) {
	OT c = getPA(lb);
	if(c == null) {
		c = new OT(def,lb);
		setPA(c);
	} else {
		c.a(def);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param def
 * @return
 */
public OT setPA(OT lb,String def) {
	OT c = getPA(lb);
	if(c == null) {
		c = new OT(def,lb);
		setPA(c);
	} else {
		c.a(def);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param def
 * @return
 */
public OT setPA(OT lb,int def) {
	OT c = getPA(lb);
	if(c == null) {
		c = new OT(def,lb);
		setPA(c);
	} else {
		c.a(def);
	}
	return c;
}

/**
 * Set the given instance attribute
 *
 * @param lb
 * @param def
 * @return
 */
public OT setPA(OT lb,double def) {
	OT c = getPA(lb);
	if(c == null) {
		c = new OT(def,lb);
		setPA(c);
	} else {
		c.a(def);
	}
	return c;
}

/**
 * aD assign a double to OType making sure nasty fraction additions are eliminated
 *
 * @param d
 */
@Override
public void a(double d) {
	String s;
	// Double has to eliminate garbage part
	if(eType() == OType.OTDBL && getIA(OTNSp.lfraction,-1) > 0) {
		super.a(d);
		s = eS();
		aType(s);
	} else {
		super.a(d);
	}
}

/**
 * return the formated output
 *
 * @return
 */
@Override
public String eS() {
	DecForm df = null;
	String s;
	OT pattern;
	int len, fra;
	SDF sdf = null;
	if(eType() == OType.OTDBL) {
		df = DecForm.getInst();
		// Decimal format always starts as a double
		DecimalFormat decForm = df.getDecimalFormat();
		len = getIA(OTNSp.llength,-1);
		fra = getIA(OTNSp.lfraction,-1);
		if(len > -1 && fra > -1) {
			decForm.setMaximumIntegerDigits(len - fra);
		}
		if(fra > -1) {
			decForm.setMaximumFractionDigits(fra);
		}
		if(fra > -1) {
			decForm.setMinimumFractionDigits(fra);
		}
		decForm.setDecimalSeparatorAlwaysShown(true);
		s = super.eS(df);
	} else if(eType() == OType.OTDAT && (pattern = getIA(OTNSp.lpattern)) != null) {
		if(eDt() == null) {
			s = OT.nst;
		} else {
			sdf = SDF.getInst();
			s = sdf.getSDF(pattern.eS(),eDt());
			sdf.release();
		}
	} else {
		// Default do decimal format
		s = super.eS((DecForm) null);
	}
	// cleanup
	if(df != null) {
		df.release();
	}
	// cleanup
	if(sdf != null) {
		sdf.release();
	}
	return s;
}
// Set the tag for this or remove.....

public void setTag(boolean mustHave) {
	if(mustHave) {
		getTag();
	} else if(instAt != null) {
		instAt.delL(OTNSp.ltg);
	}
}

/**
 * Set the tag reference value for the given reference
 *
 * @param rf
 * @return
 */
public long setTgRf(OT rf) {
	long tgRfVal = rf.getTgRf();
	setIA(OTNSp.ltgRf,tgRfVal);
// cleanup
	return tgRfVal;
}

/**
 * get collection tag row
 *
 * @param tag
 * @return
 */
public OT getCollTagRow(OT tag) {
	return getCollTagRow(tag.eL());
}

/**
 * get collection tag row
 *
 * @param tagVal
 * @return
 */
public OT getCollTagRow(long tagVal) {
	OT r = null;
	OT c;
	long ctag;
	if(tagVal >= 0) {
		// Find tag via a brute
		c = getHead();
		while(r == null && c != null) {
			ctag = c.getIA(OTNSp.ltg,-1);
			if(ctag == tagVal) {
				r = c;
			} else {
				c = getNext();
			}
		}
	}
	// cleanup
	return r;
}

/**
 * get matching tag column in collection
 *
 * @param tag
 * @return
 */
public OT getCollTag(OT tag) {
	return getCollTag(tag.eL());
}

/**
 * get matching tag column in any bag
 *
 * @param tagVal
 * @return
 */
public OT getCollTag(long tagVal) {
	OT r = null;
	OT c;
	OTCur ncc = OTCur.getInst();
	if(tagVal >= 0) {
		// Check my self first
		if(tagVal == getTagVal()) {
			r = this;
		} else {
			// Find tag via a brute Check Children second
			c = getHead();
			while(r == null && c != null) {
				if(c.getTagVal() == tagVal) {
					r = c;
				} else {
					c = getNext();
				}
			}
			// check levels
			c = getHead();
			while(r == null && c != null) {
				ncc.setColl(c);
				if((r = ncc.getCT(tagVal)) == null) {
					c = getNext();
				}
			}
		}
	}
	// cleanup
	ncc.release();
	return r;
}

/**
 * get matching tag Row that has this column in collection
 *
 *
 * @param tagVal
 * @return
 */
public OT getCollTagRowCol(long tagVal) {
	OT r = null;
	OT c;
	OT row;
	if(tagVal >= 0) {
		// Find tag via a brute
		row = getHead();
		while(r == null && row != null) {
			// Check Children first
			c = row.getHead();
			while(r == null && c != null) {
				if(c.getTag().eL() == tagVal) {
					r = row;
				} else {
					c = row.getNext();
				}
			}
			if(r == null) {
				row = getNext();
			}
		}
	}
	// cleanup
	return r;
}

/**
 * the tag value that makes this OType unique
 *
 * @return
 */
public OT getTag() {
	OT theTag;
	// We never tag lbs
	if(getLabel() == OTNSp.lLt) {
		theTag = null;
	} else if((theTag = getIA(OTNSp.ltg)) == null) {
		// Add a tag and mark only once
		theTag = setIA(OTNSp.ltg,OTNSp.getCollTag());
	}
	return theTag;
}

/**
 * get the tag value that makes this OType unique
 *
 * @return tag value
 */
public long getTagVal() {
	// Tags on lbs for view
	return (getLabel() == OTNSp.lLt) ? -1 : getTag().eL();
}

/**
 * get tag reference
 *
 * @return tag reference value
 */
public long getTgRf() {
	return getIA(OTNSp.ltgRf,getTagVal());
}

/**
 * add the given element to the tree table with the reference attribute lb
 *
 * @param row row to add
 * @param refLabel0
 * @param refLabel1
 * @param refLabel2
 * @param refLabel3
 * @param refLabel4
 * @param refLabel5
 * @param refLabel6
 * @param refLabel7
 * @param refLabel8
 * @param refLabel9
 * @param refLabel10
 * @param refMr
 * @return reference to current element
 */
public OT addRow(OT row,OT refLabel0,OT refLabel1,OT refLabel2,
	OT refLabel3,OT refLabel4,OT refLabel5,OT refLabel6,
	OT refLabel7,OT refLabel8,OT refLabel9,OT refLabel10,OT refMr) {
	OTCur ncRow = row.getNC();
	OT cgh = null;
	boolean found = false;
	long bit;
	OTCur nccoll = getNC();
	// Salt the return
	OT cre;

	// Add a tag and mark only once
	if(OTNSp.getUseTags()) {
		row.getTag();
	}
	OT re0 = (refLabel0 != null) ? ncRow.getL(refLabel0) : null;
	OT re1 = (refLabel1 != null) ? ncRow.getL(refLabel1) : null;
	OT re2 = (refLabel2 != null) ? ncRow.getL(refLabel2) : null;
	OT re3 = (refLabel3 != null) ? ncRow.getL(refLabel3) : null;
	OT re4 = (refLabel4 != null) ? ncRow.getL(refLabel4) : null;
	OT re5 = (refLabel5 != null) ? ncRow.getL(refLabel5) : null;
	OT re6 = (refLabel6 != null) ? ncRow.getL(refLabel6) : null;
	OT re7 = (refLabel7 != null) ? ncRow.getL(refLabel7) : null;
	OT re8 = (refLabel8 != null) ? ncRow.getL(refLabel8) : null;
	OT re9 = (refLabel9 != null) ? ncRow.getL(refLabel9) : null;
	OT re10 = (refLabel10 != null) ? ncRow.getL(refLabel10) : null;

	// Alpha search through rows baby
	while(!found && (cgh = nccoll.getNext()) != null) {
		ncRow.setColl(cgh);
		if((cre = ncRow.getL(refLabel0)) != null) {
			if((bit = re0.compareTo(cre)) < 0
				|| (bit == 0 && refLabel1 == null)) {
				setRefMr(refMr,nccoll);
				nccoll.insBefore(row);
				row = null;
				found = true;
				cgh = null;
			} else if(bit == 0) {
				if((cre = ncRow.getL(refLabel1)) != null) {
					if((bit = re1.compareTo(cre)) < 0
						|| (bit == 0 && refLabel2 == null)) {
						setRefMr(refMr,nccoll);
						nccoll.insBefore(row);
						row = null;
						found = true;
					} else if(bit == 0) {
						if((cre = ncRow.getL(refLabel2)) != null) {
							if((bit = re2.compareTo(cre)) < 0
								|| (bit == 0 && refLabel3 == null)) {
								setRefMr(refMr,nccoll);
								nccoll.insBefore(row);
								row = null;
								found = true;
							} else if(bit == 0) {
								if((cre = ncRow.getL(refLabel3)) != null) {
									if((bit = re3.compareTo(cre)) < 0
										|| (bit == 0 && refLabel4 == null)) {
										setRefMr(refMr,nccoll);
										nccoll.insBefore(row);
										row = null;
										found = true;
									} else if(bit == 0) {
										if((cre = ncRow.getL(refLabel4)) != null) {
											if((bit = re4.compareTo(cre)) < 0
												|| (bit == 0 && refLabel5 == null)) {
												setRefMr(refMr,nccoll);
												nccoll.insBefore(row);
												row = null;
												found = true;
											} else if(bit == 0) {
												if((cre = ncRow.getL(refLabel5)) != null) {
													if((bit = re5.compareTo(cre)) < 0
														|| (bit == 0 && refLabel6 == null)) {
														setRefMr(refMr,nccoll);
														nccoll.insBefore(row);
														row = null;
														found = true;
													} else if(bit == 0) {
														if((cre = ncRow.getL(refLabel6)) != null) {
															if((bit = re6.compareTo(cre)) < 0
																|| (bit == 0 && refLabel7 == null)) {
																setRefMr(refMr,nccoll);
																nccoll.insBefore(row);
																row = null;
																found = true;
															} else if(bit == 0) {
																if((cre = ncRow.getL(refLabel7)) != null) {
																	if((bit = re7.compareTo(cre)) < 0
																		|| (bit == 0 && refLabel8 == null)) {
																		setRefMr(refMr,nccoll);
																		nccoll.insBefore(row);
																		row = null;
																		found = true;
																	} else if(bit == 0) {
																		if((cre = ncRow.getL(refLabel8)) != null) {
																			if((bit = re8.compareTo(cre)) < 0
																				|| (bit == 0 && refLabel9 == null)) {
																				setRefMr(refMr,nccoll);
																				nccoll.insBefore(row);
																				row = null;
																				found = true;
																			} else if(bit == 0) {
																				if((cre = ncRow.getL(refLabel9)) != null) {
																					if((bit = re9.compareTo(cre)) < 0
																						|| (bit == 0 && refLabel10 == null)) {
																						setRefMr(refMr,nccoll);
																						nccoll.insBefore(row);
																						row = null;
																						found = true;
																					} else if(bit == 0) {
																						if((cre = ncRow.getL(refLabel10)) != null) {
																							if((bit = re10.compareTo(cre)) < 0) {
																								setRefMr(refMr,nccoll);
																								nccoll.insBefore(row);
																								row = null;
																								found = true;
																							} else if(bit == 0) {
																								// don't add duplicates
																								if(row.getIA(OTNSp.ltg,-1) == cgh.getIA(
																									OTNSp.ltg,-1)) {
																									row = null;
																									found = true;
																									cgh = null;
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Never found nothing so just insert
	if(row != null) {
		nccoll.insTail(row);
		// Add to reference Mr to send to others
		if(refMr != null) {
			refMr.insTail(row);
		}
		setMr(OTNSp.getUseMr(),MARKADD,row);
	}
	// cleanup
	nccoll.release();
	ncRow.release();
	return row;
}

/**
 * add the given element to the tree table with the reference attribute lb
 *
 * @param row row to add
 * @param refLabel0
 * @param refLabel1
 * @param refLabel2
 * @param refLabel3
 * @param refLabel4
 * @param refLabel5
 * @param refLabel6
 * @param refLabel7
 * @param refLabel8
 * @param refLabel9
 * @param refLabel10
 * @param refMr
 * @return reference to current element
 */
public OT addRowRev(OT row,OT refLabel0,OT refLabel1,OT refLabel2,
	OT refLabel3,OT refLabel4,OT refLabel5,OT refLabel6,
	OT refLabel7,OT refLabel8,OT refLabel9,OT refLabel10,OT refMr) {
	OTCur ncRow = row.getNC();
	OT cgh = null;
	boolean found = false;
	long bit;
	OTCur nccoll = getNC();
	// Salt the return
	OT cre;

	// Add a tag and mark only once
	if(OTNSp.getUseTags()) {
		row.getTag();
	}
	OT re0 = (refLabel0 != null) ? ncRow.getL(refLabel0) : null;
	OT re1 = (refLabel1 != null) ? ncRow.getL(refLabel1) : null;
	OT re2 = (refLabel2 != null) ? ncRow.getL(refLabel2) : null;
	OT re3 = (refLabel3 != null) ? ncRow.getL(refLabel3) : null;
	OT re4 = (refLabel4 != null) ? ncRow.getL(refLabel4) : null;
	OT re5 = (refLabel5 != null) ? ncRow.getL(refLabel5) : null;
	OT re6 = (refLabel6 != null) ? ncRow.getL(refLabel6) : null;
	OT re7 = (refLabel7 != null) ? ncRow.getL(refLabel7) : null;
	OT re8 = (refLabel8 != null) ? ncRow.getL(refLabel8) : null;
	OT re9 = (refLabel9 != null) ? ncRow.getL(refLabel9) : null;
	OT re10 = (refLabel10 != null) ? ncRow.getL(refLabel10) : null;

	// Alpha search through rows baby
	while(!found && (cgh = nccoll.getNext()) != null) {
		ncRow.setColl(cgh);
		if((cre = ncRow.getL(refLabel0)) != null) {
			if((bit = re0.compareTo(cre)) >= 0
				|| (bit == 0 && refLabel1 == null)) {
				setRefMr(refMr,nccoll);
				nccoll.insBefore(row);
				row = null;
				found = true;
				cgh = null;
			} else if(bit == 0) {
				if((cre = ncRow.getL(refLabel1)) != null) {
					if((bit = re1.compareTo(cre)) >= 0
						|| (bit == 0 && refLabel2 == null)) {
						setRefMr(refMr,nccoll);
						nccoll.insBefore(row);
						row = null;
						found = true;
					} else if(bit == 0) {
						if((cre = ncRow.getL(refLabel2)) != null) {
							if((bit = re2.compareTo(cre)) >= 0
								|| (bit == 0 && refLabel3 == null)) {
								setRefMr(refMr,nccoll);
								nccoll.insBefore(row);
								row = null;
								found = true;
							} else if(bit == 0) {
								if((cre = ncRow.getL(refLabel3)) != null) {
									if((bit = re3.compareTo(cre)) >= 0
										|| (bit == 0 && refLabel4 == null)) {
										setRefMr(refMr,nccoll);
										nccoll.insBefore(row);
										row = null;
										found = true;
									} else if(bit == 0) {
										if((cre = ncRow.getL(refLabel4)) != null) {
											if((bit = re4.compareTo(cre)) >= 0
												|| (bit == 0 && refLabel5 == null)) {
												setRefMr(refMr,nccoll);
												nccoll.insBefore(row);
												row = null;
												found = true;
											} else if(bit == 0) {
												if((cre = ncRow.getL(refLabel5)) != null) {
													if((bit = re5.compareTo(cre)) >= 0
														|| (bit == 0 && refLabel6 == null)) {
														setRefMr(refMr,nccoll);
														nccoll.insBefore(row);
														row = null;
														found = true;
													} else if(bit == 0) {
														if((cre = ncRow.getL(refLabel6)) != null) {
															if((bit = re6.compareTo(cre)) >= 0
																|| (bit == 0 && refLabel7 == null)) {
																setRefMr(refMr,nccoll);
																nccoll.insBefore(row);
																row = null;
																found = true;
															} else if(bit == 0) {
																if((cre = ncRow.getL(refLabel7)) != null) {
																	if((bit = re7.compareTo(cre)) >= 0
																		|| (bit == 0 && refLabel8 == null)) {
																		setRefMr(refMr,nccoll);
																		nccoll.insBefore(row);
																		row = null;
																		found = true;
																	} else if(bit == 0) {
																		if((cre = ncRow.getL(refLabel8)) != null) {
																			if((bit = re8.compareTo(cre)) >= 0
																				|| (bit == 0 && refLabel9 == null)) {
																				setRefMr(refMr,nccoll);
																				nccoll.insBefore(row);
																				row = null;
																				found = true;
																			} else if(bit == 0) {
																				if((cre = ncRow.getL(refLabel9)) != null) {
																					if((bit = re9.compareTo(cre)) >= 0
																						|| (bit == 0 && refLabel10 == null)) {
																						setRefMr(refMr,nccoll);
																						nccoll.insBefore(row);
																						row = null;
																						found = true;
																					} else if(bit == 0) {
																						if((cre = ncRow.getL(refLabel10)) != null) {
																							if((bit = re10.compareTo(cre)) >= 0) {
																								setRefMr(refMr,nccoll);
																								nccoll.insBefore(row);
																								row = null;
																								found = true;
																							} else if(bit == 0) {
																								// don't add duplicates
																								if(row.getIA(OTNSp.ltg,-1) == cgh.getIA(
																									OTNSp.ltg,-1)) {
																									row = null;
																									found = true;
																									cgh = null;
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Never found nothing so just insert
	if(row != null) {
		nccoll.insTail(row);
		// Add to reference Mr to send to others
		if(refMr != null) {
			refMr.insTail(row);
		}
		setMr(OTNSp.getUseMr(),MARKADD,row);
	}
	// cleanup
	nccoll.release();
	ncRow.release();
	return row;
}
/**
 * 
 * @param refMr
 * @param nccoll 
 */
public void setRefMr(OT refMr,OTCur nccoll) {
	if(refMr != null) {
		refMr.setIA(OTNSp.ltgIn,nccoll.getCur().getTag());
		refMr.setIA(OTNSp.lmr,MARKINS);
	}
}

/**
 * add the given element to the tree table with the reference attribute lb
 *
 * @param row row to add
 * @param refLabel0 reference lb to sequence through
 * @param refLabel1
 * @param refLabel2 secondary reference for sorting
 * @param refLabel3
 * @param refLabel4
 * @param refLabel5
 * @param refLabel6
 * @return refLabel3 reference to current element
 */
public OT getRowFrRow(OT row,OT refLabel0,OT refLabel1,OT refLabel2,
	OT refLabel3,OT refLabel4,OT refLabel5,
	OT refLabel6) {
	OT re0, re1, re2, re3, re4, re5, re6;
	re0 = re1 = re2 = re3 = re4 = re5 = re6 = null;
	// Salt the return
	if(refLabel0 != null) {
		re0 = row.getL(refLabel0);
	}
	if(refLabel1 != null) {
		re1 = row.getL(refLabel1);
	}
	if(refLabel2 != null) {
		re2 = row.getL(refLabel2);
	}
	if(refLabel3 != null) {
		re3 = row.getL(refLabel3);
	}
	if(refLabel4 != null) {
		re4 = row.getL(refLabel4);
	}
	if(refLabel5 != null) {
		re5 = row.getL(refLabel5);
	}
	if(refLabel6 != null) {
		re6 = row.getL(refLabel6);
	}
	return getRow(re0,re1,re2,re3,re4,re5,re6);
}
/**
 * 
 * @param re0
 * @param re1
 * @param re2
 * @param re3
 * @param re4
 * @param re5
 * @param re6
 * @return 
 */
public OT getRow(OT re0,OT re1,OT re2,OT re3,OT re4,OT re5,OT re6) {
	boolean found = false;
	OT cgh = null;
	long bit;
	OTCur nccoll = getNC();
	OTCur ncRow = OTCur.getInst();
	OT cre;
	// Alpha search through rows baby
	while(!found && (cgh = nccoll.getNext()) != null) {
		ncRow.setColl(cgh);
		if(re0 == null) {
			found = true;
		} else if((cre = ncRow.getL(re0.getLabel())) != null) {
			if((bit = re0.compareTo(cre)) < 0) {
				// Less than 1st reference or no 2nd reference
				cgh = null;
				found = true;
			} // nowcheck 2nd lb of this one for being less than the current
			else if(bit == 0) {
				if(re1 == null) {
					found = true;
				} else if((cre = ncRow.getL(re1.getLabel())) != null) {
					if((bit = re1.compareTo(cre)) < 0) {
						cgh = null;
						found = true;
					} else if(bit == 0) {
						if(re2 == null) {
							found = true;
						} else if((cre = ncRow.getL(re2.getLabel())) != null) {
							if((bit = re2.compareTo(cre)) < 0) {
								cgh = null;
								found = true;
							} else if(bit == 0) {
								if(re3 == null) {
									found = true;
								} else if((cre = ncRow.getL(re3.getLabel())) != null) {
									if((bit = re3.compareTo(cre)) < 0) {
										cgh = null;
										found = true;
									} else if(bit == 0) {
										if(re4 == null) {
											found = true;
										} else if((cre = ncRow.getL(re4.getLabel())) != null) {
											if((bit = re4.compareTo(cre)) < 0) {
												cgh = null;
												found = true;
											} else if(bit == 0) {
												if(re5 == null) {
													found = true;
												} else if((cre = ncRow.getL(re5.getLabel())) != null) {
													if((bit = re5.compareTo(cre)) < 0) {
														cgh = null;
														found = true;
													} else if(bit == 0) {
														if(re6 == null) {
															found = true;
														} else if((cre = ncRow.getL(re6.getLabel())) != null) {
															if((bit = re6.compareTo(cre)) < 0) {
																cgh = null;
																found = true;
															} else if(bit == 0) {
																found = true;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	// cleanup
	nccoll.release();
	ncRow.release();
	return cgh;
}
/**
 * 
 * @param re0
 * @param re1
 * @param re2
 * @param re3
 * @param re4
 * @param re5
 * @param re6
 * @return 
 */
public OT getRowRev(OT re0,OT re1,OT re2,OT re3,OT re4,OT re5,OT re6) {
	boolean found = false;
	OT cgh = null;
	long bit;
	OTCur nccoll = getNC();
	OTCur ncRow = OTCur.getInst();
	OT cre;
	// Alpha search through rows baby
	while(!found && (cgh = nccoll.getNext()) != null) {
		ncRow.setColl(cgh);
		if(re0 == null) {
			found = true;
		} else if((cre = ncRow.getL(re0.getLabel())) != null) {
			if((bit = re0.compareTo(cre)) > 0) {
				// Less than 1st reference or no 2nd reference
				cgh = null;
				found = true;
			} // nowcheck 2nd lb of this one for being less than the current
			else if(bit == 0) {
				if(re1 == null) {
					found = true;
				} else if((cre = ncRow.getL(re1.getLabel())) != null) {
					if((bit = re1.compareTo(cre)) > 0) {
						cgh = null;
						found = true;
					} else if(bit == 0) {
						if(re2 == null) {
							found = true;
						} else if((cre = ncRow.getL(re2.getLabel())) != null) {
							if((bit = re2.compareTo(cre)) > 0) {
								cgh = null;
								found = true;
							} else if(bit == 0) {
								if(re3 == null) {
									found = true;
								} else if((cre = ncRow.getL(re3.getLabel())) != null) {
									if((bit = re3.compareTo(cre)) > 0) {
										cgh = null;
										found = true;
									} else if(bit == 0) {
										if(re4 == null) {
											found = true;
										} else if((cre = ncRow.getL(re4.getLabel())) != null) {
											if((bit = re4.compareTo(cre)) > 0) {
												cgh = null;
												found = true;
											} else if(bit == 0) {
												if(re5 == null) {
													found = true;
												} else if((cre = ncRow.getL(re5.getLabel())) != null) {
													if((bit = re5.compareTo(cre)) > 0) {
														cgh = null;
														found = true;
													} else if(bit == 0) {
														if(re6 == null) {
															found = true;
														} else if((cre = ncRow.getL(re6.getLabel())) != null) {
															if((bit = re6.compareTo(cre)) > 0) {
																cgh = null;
																found = true;
															} else if(bit == 0) {
																found = true;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	// cleanup
	nccoll.release();
	ncRow.release();
	return cgh;
}

/**
 * Compare to OType
 *
 * @param a
 * @param to
 * @return
 */
public long compareTo(OT a,OT to) {
	return a.compareTo(to);
}
/**
 * discard all references making garbage collection easier
 */
public void discard() {
	OT x = getHead();
	while(x != null) {
		x.discard();
		x = getNext();
	}
	subOTL = null;
	if(instAt != null) {
		x = instAt.getHead();
		while(x != null) {
			x.discard();
			x = getNext();
		}
		instAt = null;
	}
}

/**
 * set Synchronization flag if the flag Return true if sync is false and can set to true other
 * return false
 *
 * @param s
 * @return
 */
public synchronized boolean setSync(boolean s) {
	boolean r = false;
	if(s) {
		if(!sync) {
			sync = true;
			r = true;
		}
	} else {
		sync = false;
	}
	return r;
}

/**
 * Delete from a hash
 *
 * @param x
 */
public void delFrHash(OT x) {
	OTCur ns = OTCur.getInst(this);
	OTCur l1 = OTCur.getInst();
	OTCur l3 = OTCur.getInst();
	boolean done = false;
	OT h1, h2, h3;
	while(!done && (h1 = ns.getNextIgn()) != null) {
		l1.setColl(h1);
		while(!done && (h2 = l1.getNextIgn()) != null) {
			l3.setColl(h2);
			while(!done && (h3 = l3.getNextIgn()) != null) {
				if(h3.del(x) != null) {
					done = true;
				}
			}
		}
	}
	// cleanup
	ns.release();
	l1.release();
	l3.release();
}

/**
 * Get the first instance of this lb. If it does not exist, create in order
 *
 * @param lb
 * @param val
 * @return
 */
public OT getIns(OT lb,int val) {
	OTCur nc = OTCur.getInst(this);
	OT collVal;
	while((collVal = nc.getNext()) != null) {
		if(val == collVal.eI()) {
			return collVal;
		} else if(collVal.eI() > val) {
			break;
		}
	}
	// Didn't find or over rated
	if(collVal == null) {
		collVal = insTail(new OT(val,lb));
	} else {
		collVal = nc.insBefore(new OT(val,lb));
	}
	// cleanup
	nc.release();
	return collVal;
}

/**
 * Get the first instance of this lb. If it does not exist, create in order
 *
 * @param lb
 * @param val
 * @return
 */
public OT getIns(OT lb,double val) {
	OTCur nc = OTCur.getInst(this);
	OT collVal;
	while((collVal = nc.getNext()) != null) {
		if(val == collVal.eD()) {
			nc.release();
			return collVal;
		} else if(collVal.eD() > val) {
			break;
		}
	}
	// Didn't find or over rated
	if(collVal == null) {
		collVal = insTail(new OT(val,lb));
	} else {
		collVal = nc.insBefore(new OT(val,lb));
	}
	// cleanup
	nc.release();
	return collVal;
}

/**
 * Get the first instance of this lb. If it does not exist, create in order
 *
 * @param lb
 * @param val
 * @return
 */
public OT getIns(OT lb,long val) {
	OTCur nc = OTCur.getInst(this);
	OT collVal;
	while((collVal = nc.getNext()) != null) {
		if(val == collVal.eL()) {
			nc.release();
			return collVal;
		} else if(collVal.eL() > val) {
			break;
		}
	}
	// Didn't find or over rated
	if(collVal == null) {
		collVal = insTail(new OT(val,lb));
	} else {
		collVal = nc.insBefore(new OT(val,lb));
	}
	// cleanup
	nc.release();
	return collVal;
}

/**
 * Get the first instance of this lb. If it does not exist, create in order
 *
 * @param lb
 * @param val
 * @return
 */
public OT getIns(OT lb,String val) {
	int bit;
	OT collVal;
	OTCur nc = OTCur.getInst(this);
	while((collVal = nc.getNext()) != null) {
		if((bit = val.compareTo(collVal.eS())) == 0) {
			nc.release();
			return collVal;
		} else if(bit < 0) {
			break;
		}
	}
	// One or the other
	if(collVal == null) {
		collVal = insTail(new OT(val,lb));
	} else {
		collVal = nc.insBefore(new OT(val,lb));
	}
	// cleanup
	nc.release();
	return collVal;
}

/**
 * get Tree().
 *
 * @param l0
 * @param l1
 * @param l2
 * @param l3
 * @param l4
 * @param l5
 * @param target
 * @return
 *
 */
public OT getTree(OT l0,OT l1,OT l2,OT l3,OT l4,OT l5,OT target) {
	OT r, cl0, cl1, cl2, cl3, cl4, cl5;
	cl0 = cl1 = cl2 = cl3 = cl4 = cl5 = null;
	OTCur nc0 = OTCur.getInst();
	OTCur nc1 = OTCur.getInst();
	OTCur nc2 = OTCur.getInst();
	OTCur nc3 = OTCur.getInst();
	OTCur nc4 = OTCur.getInst();
	OTCur nc5 = OTCur.getInst();
	OTCur nct = OTCur.getInst();
	OT ll = null;
	if(l0 != null) {
		nc0.setColl(this);
		if((cl0 = nc0.getStr(l0.eS(),false)) != null) {
			nc1.setColl(cl0);
			if(l1 != null) {
				if((cl1 = nc1.getStr(l1.eS(),false)) != null) {
					nc2.setColl(cl1);
					if(l2 != null) {
						if((cl2 = nc2.getStr(l2.eS(),false)) != null) {
							nc3.setColl(cl2);
							if(l3 != null) {
								if((cl3 = nc3.getStr(l3.eS(),false)) != null) {
									nc4.setColl(cl3);
									if(l4 != null) {
										if((cl4 = nc4.getStr(l4.eS(),false)) != null) {
											nc5.setColl(cl4);
											if(l5 != null) {
												if((cl5 = nc5.getStr(l5.eS(),false)) != null) {
													ll = cl5;
												}
											} else {
												ll = cl4;
											}
										}
									} else {
										ll = cl3;
									}
								}
							} else {
								ll = cl2;
							}
						}
					} else {
						ll = cl1;
					}
				}
			} else {
				ll = cl0;
			}
		}
	}
	// Could find at some level so create tree
	if(l0 != null) {
		if(cl0 == null) {
			cl0 = nc0.insAlpha(l0.cloneNoKids());
			nc1.setColl(cl0);
		}
		if(l1 != null) {
			if(cl1 == null) {
				cl1 = nc1.insAlpha(l1.cloneNoKids());
				nc2.setColl(cl1);
			}
			if(l2 != null) {
				if(cl2 == null) {
					cl2 = nc2.insAlpha(l2.cloneNoKids());
					nc3.setColl(cl2);
				}
				if(l3 != null) {
					if(cl3 == null) {
						cl3 = nc3.insAlpha(l3.cloneNoKids());
						nc4.setColl(cl3);
					}
					if(l4 != null) {
						if(cl4 == null) {
							cl4 = nc4.insAlpha(l4.cloneNoKids());
							nc5.setColl(cl4);
						}
						if(l5 != null) {
							if(cl5 == null) {
								cl5 = nc5.insAlpha(l5.cloneNoKids());
								ll = cl5;
							}
						} else {
							ll = cl4;
						}
					} else {
						ll = cl3;
					}
				} else {
					ll = cl2;
				}
			} else {
				ll = cl1;
			}
		} else {
			ll = cl0;
		}
	} else {
		ll = this;
	}
	// search last level for target
	nct.setColl(ll);
	if((r = nct.getStr(target.eS(),false)) == null) {
		r = (OT) getPA(OTNSp.lrow).clone();
		r.aCT(target);
		r.setLabel(target.getLabel());
		nct.insAlpha(r);
	}
	// cleanup
	nc0.release();
	nc1.release();
	nc2.release();
	nc3.release();
	nc4.release();
	nc5.release();
	nct.release();
	return r;
}

/**
 * set Mr default the mark to use marks MARK add additional row to null
 */
public void setMr() {
	setMr(true,MARKADD,null);
}

/**
 * Set watermark
 *
 * @param row
 */
public void setMr(OT row) {
	setMr(true,MARKADD,row);
}

/**
 * Set watermark
 *
 * @param mr
 */
public void setMr(int mr) {
	setMr(true,mr,null);
}

/**
 * Set watermark
 *
 * @param useMr
 * @param mr
 */
public void setMr(boolean useMr,int mr) {
	setMr(useMr,mr,null);
}

/**
 * Set water mark
 *
 * @parm useMr
 * @param useMr
 * @param mark
 * @param r
 */
public void setMr(boolean useMr,int mark,OT r) {
	if(useMr) {
		if(r != null) {
			r.setIA(OTNSp.lmr,mark);
		}
		this.setIA(OTNSp.lmr,mark);
	}
}

/**
 * Is the OType marked with a watermark ? Generally used for persistence and updates between
 * objects
 *
 * @return true for watermark
 */
public boolean isMr() {
	return (getIA(OTNSp.lmr,MARKNON) != MARKNON);
}

/**
 * Get a current watermark
 *
 * @return watermark or a default of add
 */
public int getMr() {
	return getIA(OTNSp.lmr,OT.MARKADD);
}

/**
 * Create a row. Create a row from the name space row definition
 *
 * @return row or null
 */
public OT crtRow() {
	OT r = getPA(OTNSp.lrow);
	if(r != null) {
		r = (OT) r.clone();
	}
	// cleanup
	return r;
}
}
