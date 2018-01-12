package oneType;

import java.lang.invoke.MethodHandles;

/**
 * OTCur thread safe cursor class for navigation of OTypes
 */
public class OTCur extends PooledInst {
// pooled Instance
private static final int pooledCache = 50;
private static int instFactId = -1;

// Collection types
public static final int OPEN = 0;
public static final int HASH = 1;
public static final int TREE = 2;
public static final int SORT = 3;
public static final int ALPH = 4;
private OT cur;
private LListEle po;
private int cIndex = -1;
private int totIndex = -1;
private OT coll;
// Hash elements
private int collType;
private boolean clearHash = true;
private OTCur nchash2;
private OTCur nchashe;
private OTCur nchash3;
private OT he;
private OT h2;
private OT h3;
private boolean inSync;
private boolean rowColl;

public int getCollType() {
	return collType;
}

public void setTotIndex(int x) {
	totIndex = x;
}

public int getIndex() {
	return cIndex;
}

/**
 * get total index
 *
 * @return largest index not array size
 */
public int getTotIndex() {
	if(totIndex == -1) {
		setCur();
		if(coll != null) {
			if(coll.getHead() != null) {
				do {
					totIndex++;
				} while(coll.getNext() != null);
			}
		}
		restore();
	}
	// cleanup
	return totIndex;
}

public void diagnose() {
	if(coll != null) {
		coll.diagnose();
	}
}

public void diagnose(String x) {
	if(coll != null) {
		coll.diagnose(x);
	}
}

static public OTCur getInst() {
	if(instFactId < 0) {
		instFactId = addInstFact(MethodHandles.lookup().lookupClass().getName(),pooledCache);
	}
	return (OTCur) getPooledInst(instFactId);
}

public static OTCur getInst(OT c) {
	OTCur r = getInst();
	r.setColl(c);
	return r;
}

@Override
protected void clear() {
	// This is a total clear and the release
	cur = null;
	po = null;
	cIndex = -1;
	totIndex = -1;
	coll = null;
	rowColl = false;
	if(nchash3 != null) {
		nchash3.delAll();
		nchash3.release();
		nchash3 = null;
	}
	if(nchash2 != null) {
		nchash2.delAll();
		nchash2.release();
		nchash2 = null;
	}
	if(nchashe != null) {
		if(collType == HASH) {
			nchashe.delAll();
		} else if(collType == TREE) {
			releaseTree(nchashe);
		}
		nchashe.release();
		nchashe = null;
	}
	// Default to natural tree of 0
	collType = OPEN;
	clearHash = true;
	he = null;
	h2 = null;
	he = null;
}

public OTCur() {
	setColl(null);
}

public OTCur(OT it) {
	setColl(it);
}

/**
 * Get Current
 *
 * @return
 */
public OT getCur() {
	return cur;
}

public OT getColl() {
	return coll;
}

/**
 * get off the creation trail
 *
 * @param it
 */
public final void setColl(OT it) {
	OT h;
	boolean hashFound = false;
	coll = (it == null) ? new OT() : it;
	clrCur();
	OT attributes;
	// Default to no tree
	collType = OPEN;
	// Find from labels without a OTCur since I am a nug currsor
	if(it != null) {
		// Non recursive check for a hash
		if((attributes = coll.getInstAt()) != null) {
			setSync(true,attributes);
			if((h = attributes.getL(lhash)) != null) {
				collType = h.eI();
				hashFound = true;
			}
			setSync(false,attributes);
		}
		if(!hashFound && coll.getLabel() != null && (attributes = coll.getLabel().getPersAt()) != null) {
			setSync(true,attributes);
			if((h = attributes.getL(lhash)) != null) {
				collType = h.eI();
			}
			if(attributes.getL(lrow) != null) {
				rowColl = true;
			}
			setSync(false,attributes);
		}
	}
}

/**
 * get OType by index
 *
 * @param index
 * @return
 */
public OT getIndex(int index) {
	setCur();
	if(cIndex > index || cIndex < 0) {
		cur = coll.getHead();
		cIndex = 0;
	}
	while(cIndex < index && cur != null) {
		cur = coll.getNext();
		cIndex++;
	}
	// Didn't find it so undo
	if(cur == null) {
		cIndex = -1;
	}
	restore();
	return cur;
}

/**
 * get head
 *
 * @return
 */
public OT getHead() {
	setCur();
	cur = coll.getHead();
	restore();
	return cur;
}

/**
 * get tail
 *
 * @return
 */
public OT getTail() {
	setCur();
	cur = coll.getTail();
	restore();
	return cur;
}

/**
 * get Previous NOTE this is not HASHABLE but should be
 *
 * @return
 */
public OT getPrev() {
	setCur();
	cur = coll.getPrev();
	restore();
	return cur;
}

/**
 * Get Next
 *
 * @return
 */
public OT getNext() {
	setCur();
	OT ret;
	switch(collType) {
		case OPEN:
		case SORT:
		case ALPH:
			ret = coll.getNext();
			break;
		case HASH:
			ret = getNextHash();
			break;
		case TREE:
			// Returns last sort
			if((ret = getNextTree()) != null && rowColl) {
				ret = ret.getHead();
			}
			break;
		default:
			ret = null;
	}
	// cleanup
	restore();
	return ret;
}

public OT getNextIndex() {
	OT r = getNext();
	if(r == null) {
		cIndex = -1;
	} else {
		cIndex++;
	}
	return r;
}

/**
 * Get Next Ignore the hash otherwise a hash cant get anything but a hash
 *
 * @return
 */
public OT getNextIgn() {
	setCur();
	cur = coll.getNext();
	// cleanup
	restore();
	return cur;
}

/**
 * addHead
 *
 * @param it
 * @return
 */
public OT insHead(OT it) {
	setCur();
	cur = coll.insHead(it);
	restore();
	return cur;
}

public OT insTail(OT it) {
	return insTail(it,false);
}

/**
 * insTail
 *
 * @param it
 * @param mr
 * @return
 */
public OT insTail(OT it,boolean mr) {
	setCur();
	cur = coll.insTail(it,mr);
	restore();
	return cur;
}

/**
 * Insert tail keeping track of index
 *
 * @param it
 * @return
 */
public OT insTailIndex(OT it) {
	getTotIndex();
	insTail(it);
	cIndex = ++totIndex;
	return cur;
}

/**
 * Insert after the current position
 *
 * @param it
 * @return
 */
public OT insAfter(OT it) {
	setCur();
	cur = coll.insAfter(it);
	restore();
	return cur;
}

/**
 * ins before
 *
 * @param it
 * @return
 */
public OT insBefore(OT it) {
	setCur();
	cur = coll.insBefore(it);
	restore();
	return cur;
}

public OT insBeforeIndex(OT it) {
	OT r = insBefore(it);
	setCur();
	cIndex = -1;
	OT c = coll.getHead();
	while(c != null) {
		cIndex++;
		if(c == it) {
			break;
		}
		c = coll.getNext();
	}
	restore();
	return cur;
}

/**
 * Replace as a stack (LIFO)
 *
 * @param it
 * @return
 */
public OT replHead(OT it) {
	setCur();
	cur = coll.replHead(it);
	restore();
	return cur;
}

public OT replL(OT it) {
	return replL(it,false);
}

/**
 * Replace
 *
 * @param it
 * @param mr
 * @return
 */
public OT replL(OT it,boolean mr) {
	setCur();
	cur = coll.replL(it,mr);
	restore();
	return cur;
}

/**
 * Replace
 *
 * @param from
 * @param to
 * @return
 */
public OT replObjR(OT from,OT to) {
	setCur();
	cur = coll.replObjR(from,to);
	restore();
	return cur;
}

/**
 * delete all the little buggers
 */
public void delAll() {
	setCur();
	coll.delAll();
	clrCur();
	restore();
}

public OT del() {
	return del(false);
}

/**
 * delete the current
 *
 * @param mr
 * @return
 */
public OT del(boolean mr) {
	OT r;
	setCur();
	r = coll.del(mr);
	restore();
	return r;
}

/**
 * delete head
 *
 * @return
 */
public OT delHead() {
	OT r;
	setCur();
	r = coll.delHead();
	restore();
	return r;
}

public OT delIndex() {
	OT r = del();
	// Index remains same unless empty
	if(totIndex > -1) {
		totIndex--;
	}
	if(cIndex > totIndex) {
		cIndex = totIndex;
	}
	return r;
}

public OT del(OT x) {
	return del(x,false);
}

/**
 * delete the current
 *
 * @param x
 * @param mr
 * @return
 */
public OT del(OT x,boolean mr) {
	setCur();
	OT r = coll.del(x,mr);
	// delete sets to previous
	restore();
	return r;
}

/**
 * delL delete the label
 *
 * @param x
 * @return
 */
public OT delL(OT x) {
	setCur();
	OT r = coll.delL(x);

	restore();
	return r;
}

/**
 * RF - recursive find breath first.
 *
 * @param l
 * @return
 */
public OT getLR(OT l) {
	OT r;
	setCur();
	r = coll.getLR(l);

	restore();
	return r;
}

/**
 * Get Label Recursive No Row Collections
 *
 * @param l
 * @return
 */
public OT getLRNRC(OT l) {
	return getLRNRC(l,null);
}

/**
 * Get Label Recursive No Row Collections
 *
 * @param l
 * @param theBag
 * @return
 */
public OT getLRNRC(OT l,OT theBag) {
	setCur();
	OT r = coll.getLRNRC(l,theBag);

	restore();
	return r;
}

/**
 * get the next given label in the collection
 *
 * @param l	label
 * @return
 */
public OT getNext(OT l) {
	setCur();
	cur = coll.getNext(l);
	restore();
	return cur;
}

public OT getN(OT obj) {
	setCur();
	OT ret = coll.getN(obj);
	// cleanup
	restore();
	return ret;
}

/**
 * Get given object recursive in the collection
 *
 * @param obj
 * @return Found object or null
 */
public OT getOR(OT obj) {
	setCur();
	OT r = null;
	OT row;
	OTCur ncrow;
	OT c;
	// always save the
	// Position before i started if i'm there
	while(r == null && (row = coll.getNext()) != null) {
		if(row == obj) {
			r = row;
		} else {
			ncrow = row.getNC();
			while(r == null && (c = ncrow.getNext()) != null) {
				if(c == obj) {
					r = c;
				}
			}
			ncrow.release();
		}
	}
	restore();
	return r;
}

/**
 * Find the first instance of the given label in the list
 *
 * @param l
 * @return
 */
public OT getL(OT l) {
	setCur();
	cur = coll.getL(l);
	restore();
	return cur;
}

/**
 * get string ignore case by default
 *
 * @param s
 * @return
 */
public OT getStr(String s) {
	return getStr(s,false);
}

/**
 * get string recursively ignore case by default
 *
 * @param s
 * @return
 */
public OT getStrR(String s) {
	return getStrR(s,false);
}

/**
 * Find the given String in the collection
 *
 * @param s
 * @param ignoreCase
 * @return Found object or null
 */
public OT getStr(String s,boolean ignoreCase) {
	setCur();
	cur = coll.getStr(s,ignoreCase);
	// cleanup
	restore();
	return cur;
}

/**
 * Find the given String recursively in the collection
 *
 * @param s
 * @param ignoreCase
 * @return Found object or null
 */
public OT getStrR(String s,boolean ignoreCase) {
	setCur();
	OT r = coll.getStrR(s,ignoreCase);
	// cleanup
	restore();
	return r;
}

/**
 * Find the given String with the given label in the collection Find Collection Label with String
 *
 * @param ns
 * @return Found object or null
 */
public OT getCLS(OT ns) {
	setCur();
	OT rl = null;
	OTCur nccl;
	OT cl;
	OT cLabel;
	clrCur();
	// Loop through current lines
	while(rl == null && (cl = getNextNC()) != null) {
		nccl = cl.getNC();
		// Found the label and data equals data
		if((cLabel = nccl.lFind(ns.getLabel())) != null && cLabel.eS().equalsIgnoreCase(
			ns.eS())) {
			rl = cl;
		}
		nccl.release();
	}
	// cleanup
	restore();
	return rl;
}

/**
 * Find the given label in the bag from current position or the top
 *
 * @param label
 * @param head
 * @return
 */
public OT sFind(OT label,boolean head) {
	return lFind(label,head);
}

/**
 * Add to collection
 *
 * @param it
 * @return
 */
public OT addColl(OT it) {
	switch(collType) {
		case SORT:
			cur = addToSortColl(it);
			break;
		default:
			setCur();
			cur = coll.addColl(it);
			restore();
			break;
	}
	// cleanup
	return cur;
}

protected OT addToSortColl(OT row) {
	int i;
	OT x;
	OT arr[] = new OT[11];
	OTCur ncSort = OTCur.getInst(coll.getPA(lsort));
	for(i = 0;i < 11;i++) {
		arr[i] = null;
	}
	for(i = 0;i < 11 && (x = ncSort.getNext()) != null;i++) {
		arr[i] = x;
	}
	// Look don't save in ins bag quite yet
	coll.addRow(row,(arr[0] == null) ? null : (arr[0]).getLabel(),
		(arr[1] == null) ? null : (arr[1]).getLabel(),
		(arr[2] == null) ? null : (arr[2]).getLabel(),
		(arr[3] == null) ? null : (arr[3]).getLabel(),
		(arr[4] == null) ? null : (arr[4]).getLabel(),
		(arr[5] == null) ? null : (arr[5]).getLabel(),
		(arr[6] == null) ? null : (arr[6]).getLabel(),
		(arr[7] == null) ? null : (arr[7]).getLabel(),
		(arr[8] == null) ? null : (arr[8]).getLabel(),
		(arr[9] == null) ? null : (arr[9]).getLabel(),
		(arr[10] == null) ? null : (arr[10]).getLabel(),
		null);
	// cleanup
	ncSort.release();
	return row;
}

/**
 * Add to collection
 *
 * @param it
 * @return
 */
public OT insAlpha(OT it) {
	return insAlpha(it,false);
}

/**
 *
 * @param it
 * @param mark
 * @return
 */
public OT insAlpha(OT it,boolean mark) {
	// Position before i started
	setCur();
	cur = coll.insAlpha(it,mark);
	restore();
	return cur;
}

public OT insAlpha(OT it,boolean mark,boolean useTags) {
	// Position before i started
	setCur();
	cur = coll.insAlpha(it,mark,useTags);
	restore();
	return cur;
}

/**
 * insert in collection
 *
 * @param it
 * @return
 */
public OT insInColl(OT it) {
	// Position before i started
	setCur();
	// get started
	cur = coll.insInColl(it);
	restore();
	return cur;
}

public OT insInCollIndex(OT it) {
	insInColl(it);
	totIndex++;
	return cur;
}

/**
 * delete from collection
 *
 * @param row
 * @return
 */
public OT delFrColl(OT row) {
	// Position before i started
	setCur();
	OT r = coll.getCollTagRow(row.getTagVal());
	if(r != null) {
		row = coll.del();
	}

	restore();
	return row;
}

public OT delFrCollIndex(OT row) {
//xxx	delFrCollIndex(row);
	// Unless last one its the same
	if(cIndex > -1 && cIndex == totIndex) {
		cIndex--;
	}
	if(totIndex > -1) {
		totIndex--;
	}
	// cleanup
	return row;
}

public OT getCT(long tagVal) {
	OT r = null;
	if(tagVal >= 0) {
		if(coll.isRowColl() != null) {
			r = getCTR(tagVal);
		} else {
			setCur();
			r = coll.getCollTag(tagVal);
			restore();
		}
	}
	// cleanup
	return r;
}

/**
 * Get Coll Tag Row from reference tag
 *
 * @param row
 * @return
 */
public OT getCTR(OT row) {
	if(row == null) {
		cur = null;
	} else {
		cur = getCTR(row.getTgRf());
	}
	return cur;
}

/**
 * Get Coll Tag Row
 *
 * @param tag
 * @return
 */
public OT getCTR(long tag) {
	setCur();
	clrCur();
	OT c = coll.getHead();
	while(c != null) {
		if(c.getTagVal() == tag) {
			break;
		}
		c = coll.getNext();
	}
	restore();
	return c;
}

/**
 *
 * @param tag
 * @return
 */
public OT getCTRIndex(long tag) {
	setCur();
	clrCur();
	OT c = coll.getHead();
	cIndex = -1;
	while(c != null) {
		cIndex++;
		if(c.getTagVal() == tag) {
			break;
		}
		c = coll.getNext();
	}
	restore();
	return c;
}

public OT getNextHash() {
	OT nextHash = null;
	// Prime for first element
	if(clearHash) {
		clearHash = false;
		if((h2 = coll.getHead()) != null) {
			if(nchash2 != null) {
				nchash2.setColl(h2);
			} else {
				nchash2 = h2.getNC();
			}
		}
		h3 = he = null;
	}
	while(nextHash == null && h2 != null) {
		if(he == null || (nextHash = nchashe.getNextIgn()) == null) {
			if(h3 != null && (he = nchash3.getNextIgn()) != null) {
				if(nchashe != null) {
					nchashe.setColl(he);
				} else {
					nchashe = he.getNC();
				}
			} else if(h2 != null && (h3 = nchash2.getNext()) != null) {
				if(nchash3 != null) {
					nchash3.setColl(h3);
				} else {
					nchash3 = h3.getNC();
				}
			} else if((h2 = coll.getNext()) != null) {
				if(nchash2 != null) {
					nchash2.setColl(h2);
				} else {
					nchash2 = h2.getNC();
				}
			}
		}
	}
	// clear on last call	
	if(nextHash == null) {
		if(nchash3 != null) {
			nchash3.release();
			nchash3 = null;
		}
		if(nchash2 != null) {
			nchash2.release();
			nchash2 = null;
		}
		if(nchashe != null) {
			nchashe.release();
			nchashe = null;
		}
	}
	return nextHash;
}

/**
 * protected internal implementation
 */
protected synchronized void setCur() {
	// Wait for sync
	setSync(true);
	// Attempting to do something better have a collection
	if(coll == null) {
		setColl(new OT(ldefault));
	}
	if(coll.getSubOTList() != null) {
		// Position
		coll.getSubOTList().setPo(po);
	} else {
		po = null;
		cur = null;
	}
}

/**
 * saveCur Save the current cursor position
 */
protected void saveCur() {
	if(coll.getSubOTList() != null) {
		po = coll.getSubOTList().getPo();
		cur = coll.getAt();
	} else {
		po = null;
		cur = null;
	}
}

/**
 * Restore native OType position
 */
protected synchronized void restore() {
	saveCur();
	// Return lock
	setSync(false);
}

protected OT getHeadNC() {
	// First OType and get the current
	if((cur = coll.getHead()) != null) {
	} else {
		clrCur();
	}
	return cur;
}

protected OT getNextNC() {
	// Default location
	if(cur == null) {
		getHeadNC();
	} else if((cur = coll.getNext()) != null) {
	} else {
		clrCur();
	}
	return cur;
}

/**
 * Find the given label in the bag from the top
 *
 * @param label
 * @return
 */
protected OT lFind(OT label) {
	return sFind(label,true);
}

/**
 * Find the given label in the bag from current position or the top
 *
 * @param l
 * @param head
 * @return
 */
protected OT lFind(OT l,boolean head) {
	if(head) {
		cur = getHeadNC();
	}
	while(cur != null && cur.getLabel() != l) {
		cur = getNextNC();
	}
	return cur;
}

public void clrCur() {
	cIndex = -1;
	po = null;
	cur = null;
	///ZZZ WHO cares about underlying postion ?????
	// Internally used clear  needs to set
	boolean localSync = inSync;
	// Under lying OType position should be null
	if(!inSync) {
		setSync(true);
	}
	if(coll != null && coll.getSubOTList() != null) {
		coll.getSubOTList().setPo(null);
	}
	if(!localSync) {
		setSync(false);
	}

	// Am I a hash or not
	clearHash = true;
	if(nchashe != null) {
		nchashe.clrCur();
	}
}

/**
 * releaseTree Release the given tree collection
 *
 * @param ncTreeCur
 */
protected void releaseTree(OTCur ncTreeCur) {
	OT x;
	if(ncTreeCur != null) {
		ncTreeCur.clrCur();
		while((x = ncTreeCur.getNext()) != null) {
			((OTCur) x.eO()).release();
		}
	}
}

/**
 * set the synchronization flag for this collection. Wait for an open synchronization
 *
 * @param s
 */
protected void setSync(boolean s) {
	setSync(s,coll);
	inSync = s;
}

/**
 * set the synchronization flag for this collection. Wait for an open synchronization
 *
 * @param s
 * @param x
 */
protected void setSync(boolean s,OT x) {
	// Rare case where not initialized
	if(x != null) {
		// Grab the sync flag
		if(s) {
			while(!x.setSync(true)) {
				try {
					Thread.sleep(1);
				} catch(InterruptedException ie) {
					if(OTNSp.getDebug()) {
						OTNSp.buzz(ie.getMessage());
					}
				}
			}
		} // Return the sync
		else {
			x.setSync(false);
		}
	}
}

public OT popStack(Object x) {
	OT addMsg = null;
	if(coll != null) {
		setCur();
		if((addMsg = coll.getHead()) == null) {
			addMsg = new OT(x);

		} else {
			coll.delHead();
			addMsg.aO(x);
		}
		restore();
	}
	return addMsg;
}

/**
 * Compare 2 collections for identical elecollnts return true on sacoll?
 *
 * @param c co
 * @return true/false
 */
public boolean whoisCollSacoll(OT c) {
	boolean r = true;
	boolean cont = true;
	OTCur ncb = null;
	OT a, b;
	// Garbage in
	if(coll == null && c == null) {
		return true;
	}
	if(coll != null && c != null) {
		ncb = OTCur.getInst(c);
		clrCur();
		while(cont == true) {
			a = getNext();
			b = ncb.getNext();
			if(a != null && b != null) {
				// Don't add collaningless tags if not there
				if(a.getTagVal() != b.getTagVal()) {
					r = false;
					cont = false;
				}
			} else {
				cont = false;
				if(a != null || b != null) {
					r = false;
				}
			}
		}
	} else {
		r = false;
	}
	// cleanup
	if(ncb != null) {
		ncb.release();
	}
	return r;
}

/**
 * getTreeFrRef Get tree from reference row
 *
 * @param ref reference row
 * @return r target row
 */
public OT getTreeFrRef(OT ref) {
	return getTreeFrRef(ref,true);
}

/**
 * getTreeFrRef Get tree from reference row
 *
 * @param ref reference row
 * @param createRow
 * @return r target row
 */
public OT getTreeFrRef(OT ref,boolean createRow) {
	//xxx can't add if not there to this level if current
	//xxx setCur();
	OT r;
	OTCur ncSort = OTCur.getInst(coll.getPA(lsort));
	OTCur ncRef = OTCur.getInst(ref);
	// Always start from scratch
	clrCur();
	// Make if can't find reference
	r = getNextTreeFrRef(ncSort,ncRef,this,coll,createRow);
	// cleanup
	//xxx Don't current so don't restore
	//xxx restore();
	ncSort.release();
	ncRef.release();
	return r;
}

/**
 * getNextTreeLevel
 *
 * @param ncSort list
 * @param ncRef reference
 * @param root
 * @param inLevel
 * @param createRow
 * @return r next level
 */
protected OT getNextTreeFrRef(OTCur ncSort,OTCur ncRef,OTCur inLevel,OT root,
	boolean createRow) {
	OT r = null;
	OT c, cSort;
	OTCur ncLevel = OTCur.getInst();
	OTCur ncRow, ncS;
	if((c = ncSort.getNextIgn()) != null) {
		cSort = ncRef.getL(c.getLabel());
		ncLevel.setColl(getTreeLevel(cSort,inLevel));
		r = getNextTreeFrRef(ncSort,ncRef,ncLevel,root,createRow);
	} else if(inLevel.getHeadIgnInt() == null) {//  done so fill out as a row from his definition
		if(createRow) {
			// Mark as add
			root.setMr(OT.MARKADD);
			// Now really a row
			r = coll.cloneRow();
			if(r == null) {
				coll.getLabel().diagnose();
			}
			ncRow = OTCur.getInst(r);
			inLevel.insTail(ncRow.getColl());
			ncS = OTCur.getInst(ncSort.getColl());
			// Now update the sort values
			while((c = ncS.getNextIgn()) != null) {
				ncRow.getL(c.getLabel()).aCT(ncRef.getL(c.getLabel()));
			}
			ncRow.release();
			ncS.release();
		}
	} else {
		r = inLevel.getHead();
	}
	// cleanup
	ncLevel.release();
	return r;
}

/**
 * getTreeLevel.
 *
 * @param cSort key
 * @param nc current tree level
 * @return r new level
 */
protected OT getTreeLevel(OT cSort,OTCur nc) {
	OT c = null;
	long ind = 1;
	while((c = nc.getNextIgnInt()) != null) {
		if((ind = cSort.compareTo(c)) <= 0) {
			break;
		}
	}
	// zero collans we found
	if(ind != 0) {
		c = cSort.cloneNoKids();
		// Less than zero collans insert before
		if(ind < 0) {
			nc.insBeforeIgnInt(c);
		} else {
			nc.insTailIgnInt(c);
		}
	}
	// cleanup
	return c;
}

protected OT getNextTree() {
	OT r;
	OTCur nc = OTCur.getInst(coll.getPA(lsort));
	if(nchashe == null) {
		nchashe = OTCur.getInst(new OT());
		// Find last label
		h2 = nc.getTail();
		nc.clrCur();
	} else {
		nchashe.clrCur();
	}
	// No sort we are normal
	if(h2 == null) {
		return coll.getNext();
	}
	// Must have at least one sort level
	r = getNextTree(nc,this);
	// cleanup
	nc.release();
	return r;
}

/**
 * getNextTreeLevel
 *
 * @param ncSort sort list
 * @param inLevel
 * @return r next level
 */
protected OT getNextTree(OTCur ncSort,OTCur inLevel) {
	OT r = null;
	OT c;
	OT nextTreeLevel;
	OTCur ncNextTreeLevel;
	OT x;
	// If no sort we are at the bottem
	c = ncSort.getNextIgnInt();
	if(c.getLabel() == h2.getLabel()) {
		r = inLevel.getNextIgnInt();
	} else {
		// We know we are brand new
		if((nextTreeLevel = nchashe.getNextIgnInt()) == null) {
			// Empty list should return this
			if((c = inLevel.getNextIgnInt()) == null) {
				return null;
			}
			ncNextTreeLevel = OTCur.getInst(c);
			x = new OT(ncNextTreeLevel);
			nchashe.insTailIgnInt(x);
			nchashe.setCollCur(x);
		} else {
			ncNextTreeLevel = (OTCur) nextTreeLevel.eO();
			if((c = ncNextTreeLevel.getColl()) == null) {
				if((c = inLevel.getNextIgnInt()) != null) {
					ncNextTreeLevel.setColl(c);
				}
			}
		}
		// Now find result
		if(c != null) {
			while((r = getNextTree(ncSort,ncNextTreeLevel)) == null && (c = inLevel.getNextIgnInt())
				!= null) {
				ncNextTreeLevel.setColl(c);
			}
			// This level is over so back up to next level and reload		
			if(c == null) {
				ncNextTreeLevel.setColl(null);
			}
		}
		// Back up the tree order too
		nchashe.getPrevIgnInt();
	}
	// Back up the ladder
	ncSort.getPrevIgnInt();
	// cleanup
	return r;
}

/**
 * getNextIgnInt Get Next Ignore Internal. Ignore hashing and trees
 *
 * @return
 */
protected OT getNextIgnInt() {
	OT r = coll.getNext();
	// Still do a restore to set up the nug
	//xxx saveCur();
	return r;
}

/**
 * getPrevIgnInt() Get previous ignore internal.
 *
 * @return
 */
protected OT getPrevIgnInt() {
	OT r = coll.getPrev();
	// Still do a restore to save attributes
	//xxx saveCur();
	return r;
}

/**
 * insTailIgnInt
 *
 * @param it
 * @return
 */
protected OT insTailIgnInt(OT it) {
	cur = coll.insTail(it);
	//xxx saveCur(); // Restore before death
	return cur;
}

/**
 * Set Collection Current
 *
 * @param x
 * @return
 */
protected OT setCollCur(OT x) {
	cur = coll.setCollCur(x);
	saveCur(); // Restore before death
	return cur;
}

/**
 * Set Collection Current
 *
 * @return
 */
protected OT getCollCur() {
	cur = coll.getCollCur();
	saveCur(); // Restore before death
	return cur;
}

/**
 * getHeadIgnInt Get head of list ignoring collections
 *
 * @return
 */
protected OT getHeadIgnInt() {
	cur = coll.getHead();
	saveCur();
	return cur;
}

/**
 * insBeforeIgnInt Insert before ignore collections
 *
 * @param it
 * @return
 */
protected OT insBeforeIgnInt(OT it) {
	cur = coll.insBefore(it);
	//xxx saveCur();
	return cur;
}

/**
 * getStrIA
 *
 * @param IALabel
 * @param what
 * @return
 */
public OT getStrIA(OT IALabel,String what) {
	OT r;
	setCur();
	r = coll.getHead();
	while(r != null) {
		if(r.getIA(IALabel,OT.nst).equals(what)) {
			break;
		}
		r = coll.getNext();
	}
	restore();
	return cur;
}

/**
 * get flat sorted collection row equal to given
 *
 * @param ref
 * @return
 */
public OT getSortFrRef(OT ref) {
	OT r;
	OTCur ncRefSort = OTCur.getInst(coll.getIA(lsort));
	OTCur ncR = OTCur.getInst();
	OTCur ncRef = OTCur.getInst(ref);
	OT s, x;
	clrCur();
	while((r = getNext()) != null) {
		ncR.setColl(r);
		ncRefSort.clrCur();
		while((s = ncRefSort.getNext()) != null) {
			if((x = ncR.getL(s.getLabel())) != null && x.compareTo(ncRef.getL(s.getLabel())) != 0) {
				break;
			}
		}
		if(s == null) {
			break;
		}
	}
	// cleanup
	ncRefSort.release();
	ncRef.release();
	ncR.release();
	return r;
}
final public static OT lhash = OTNSp.lhash;
final public static OT lsort = OTNSp.lsort;
final public static OT ldefault = OTNSp.ldefault;
final public static OT lrow = OTNSp.lrow;
}
