package oneType;

/**
 * the base class for factory classes. Provide foundation support for every Factory class
 */
public class InstFact {

final private OT objList = new OT();
final private OT nugList = new OT();
private String className = OT.nst;
private long totQueIns, totReqIns, totCrt, totRelIns, totContainers;
private int seed;

public InstFact() {
}

public InstFact(String cn,int sSeed) {
	className = cn;
	seed = sSeed;
}

public OT getStats() {
	OT data = new OT(className,OTNSp.lInst);
	data.insTail(new OT("In Queue " + totQueIns + " Total Inst Request " + totReqIns
		+ " Recycled back to Queue " + totRelIns + " Crt " + totCrt + " Containers " + totContainers,
		OTNSp.lTransactions));
	return data;
}

public String getStrStats() {
	return className
		+ "\n In Queue          " + totQueIns
		+ "\n Created           " + totCrt
		+ "\n Containers        " + totContainers
		+ "\n Inst Request      " + totReqIns
		+ "\n Recycled to Queue " + totRelIns;
}

public long getTotCrt() {
	return totCrt;
}

public String getClassName() {
	return className;
}

public void setClassName(String x) {
	className = (x == null) ? OT.nst : x;
}

/**
 * get total instances
 *
 * @return
 */
public long getTotQueIns() {
	return totQueIns;
}

public long getTotRelIns() {
	return totRelIns;
}

public long getTotReqIns() {
	return totReqIns;
}

/**
 * get object list
 *
 * @return
 */
public OT getObjList() {
	return objList;
}

/**
 * Clear available OTypes for saving instances
 */
public synchronized void clrOTList() {
	nugList.delAll();
}

/**
 * Clear available instances
 */
public synchronized void clrObjList() {
	objList.delAll();
	totQueIns = 0;
}

/**
 * Get a object that is currently not being used
 *
 * @return
 */
public synchronized Object getInst() {
	Object fs;
	int i;
	OT c = objList.getHead();
	// Got one that was saved ?
	if(c != null) {
		// Take out of the pool
		objList.del(c);
		fs = c.eO();
		totQueIns--;
		// Save the OT
		c.aO(null);
		nugList.insHead(c);
	} else {
		// The default action is to just creat another on a template factory
		// Seed the factory so not all over the place so why swap pates
		for(i = 0;i < seed;i++) {
			addObjList(getClassInst());
		}
		// the seed is adding to the release list
		totRelIns -= seed;
		fs = getClassInst();
	}
	// Record Instance
	if(++totReqIns == Long.MAX_VALUE) {
		totReqIns = 0;
	}
	return fs;
}

/**
 * Get a OType from saved OType list or return a new OType
 *
 * @return
 */
public synchronized OT getOTList() {
	OT r = nugList.getHead();
	if(r != null) {
		nugList.del(r);
	} else {
		r = new OT();
		if(++totContainers == Long.MAX_VALUE) {
			totContainers = 0;
		}
	}
	return r;
}

/**
 * Add Instance to the list of available Instances
 *
 * @param x
 */
public synchronized void addObjList(Object x) {
	if(!chkObjList(x)) {
		OT c = getOTList();
		c.aO(x);
		objList.insHead(c);
		if(++totQueIns == Long.MAX_VALUE) {
			totQueIns = 0;
		}
		if(++totRelIns == Long.MAX_VALUE) {
			totRelIns = 0;
		}
	}
}

public synchronized boolean chkObjList(Object x) {
	boolean r = false;
	// Just checks for already released
	if(OTNSp.getDebug()) {
		OT c = objList.getHead();
		while(c != null) {
			if(c.eO() == x) {
				trace("Inst Release Failure " + className + ".release");
				r = true;
				break;
			}
			c = objList.getNext();
		}
	}
	return r;
}

/**
 * Get a Class Instance of the given class name for this instance factory by having class name be a
 * null we can override the get new functionality
 *
 * @return
 */
public Object getClassInst() {
	Object bd = null;
	if(className != null) {
		try {
			bd = java.lang.Class.forName(className).newInstance();
			// this is dangerous but don't want to change object structure too much
			((PooledInst) bd).setId(totCrt++);
		} catch(InstantiationException | IllegalAccessException | ClassNotFoundException ie) {
			OTNSp.buzz("InstFact getClassInst " + className + " " + ie.getLocalizedMessage());
			// Catch can't find the class
			trace(ie.getMessage());
		}
	}
	return bd;
}

public void release(Object x,long id) {
	if(id > -1) {
		addObjList(x);
	} else {
		trace("Attempt to release without id " + className);
	}
}

/**
 * set stack trace
 *
 * @param detail
 */
public static void trace(String detail) {
	// Track it right now
	System.out.println(detail);
	Thread.currentThread().dumpStack();
	System.exit(0);
}
}
