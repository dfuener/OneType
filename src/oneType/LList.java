package oneType;

/**
 * LList linked list implementation
 *
 */
public class LList {

private LListEle head;
private LListEle tail;
private LListEle po;

/**
 * get current position
 *
 * @return current position
 */
public LListEle getPo() {
	return po;
}

/**
 * set current position
 *
 * @param listElem list element
 */
public void setPo(LListEle listElem) {
	po = listElem;
}

/**
 * clone
 *
 * @return cloned LList
 */
@Override
protected Object clone() {
	LList c = new LList();
	c.head = head;
	c.tail = tail;
	c.po = po;
	return c;
}

/**
 * create LList
 */
LList() {
}

/**
 * Clean every node of the tree by null each object 
 *
 */
public void clean() {
	LListEle x = head;
	LListEle cle;
	while(x != null) {
		cle = x;
		x = cle.next;
		cle.clean();
	}
	zero();
}

private void zero() {
	head = null;
	tail = null;
	po = null;
}

/**
 * delete if object found
 *
 * @param o object object
 * @return found object or null
 */
public Object delThis(Object o) {
	Object it = find(o);
	if(it != null) {
		del();
	}
	return it;
}

/**
 * get the head object.
 *
 * @return head object or null
 */
public Object getHead() {
	if(head != null) {
		po = head;
		return head.stuff;
	}
	return null;
}

/**
 * get the tail object
 *
 * @return tail object or null
 */
public Object getTail() {
	if(tail != null) {
		po = tail;
		return tail.stuff;
	}
	// cleanup
	return null;
}

/**
 * delete the head object
 *
 * @return head object or null
 */
public Object delHead() {
	Object st;
	// get the head for returning
	if((st = getHead()) != null) {
		st = del();
	}
	return st;
}

/**
 * delete the tail object
 *
 * @return tail object or null
 */
public Object delTail() {
	Object st;
	// get the Tail for returning
	if((st = getTail()) != null) {
		st = del();
	}
	return st;
}

/**
 * delete the current object
 *
 * @return deleted object or null
 */
public Object del() {
	Object st = null;
	LListEle tmp;
	// If I have a position, del it
	if(po != null) {
		// By definition set po
		st = po.stuff;
		if(po == head) {
			head = po.next;
		}
		if(po == tail) {
			tail = po.prev;
		}
		if(po.prev != null) {
			tmp = po.prev;
			tmp.next = po.next;
		}
		if(po.next != null) {
			tmp = po.next;
			tmp.prev = po.prev;
		}
		// Reset current position
		po = po.prev;
	}
	// cleanup
	return st;
}

/**
 * insert object before head
 *
 * @param o object
 * @return head object or null
 */
public Object insHead(Object o) {
	// Jump to top of list
	getHead();
	// cleanup
	return insBefore(o);
}

/**
 * Insert object after tail object
 *
 * @param o object
 * @return tail object or null
 */
public Object insTail(Object o) {
	getTail();
	// cleanup
	return insAfter(o);
}

/**
 * insert an object before the current object
 *
 * @param o object
 * @return return inserted object or null
 */
public Object insBefore(Object o) {
	// Always create a link and assign a return Object to it
	LListEle newLink = new LListEle(o);
	LListEle tmp;
	// Just add if empty
	if(head == null) {
		head = newLink;
		tail = newLink;
	} else {
		// No current position go to the head
		if(po == null) {
			getHead();
		}
		if(po == head) {
			head = newLink;
		}
		if(po.prev != null) {
			tmp = po.prev;
			tmp.next = newLink;
			newLink.prev = tmp;
		}
		po.prev = newLink;
		newLink.next = po;
	}
	// cleanup
	// Save current position
	if(po == null) {
		po = newLink;
	}
	return o;
}

/**
 * insert an object after the current object
 *
 * @param o object
 * @return inserted object or null
 */
public Object insAfter(Object o) {
	// Always create a link and assign a return Object to it
	LListEle newLink = new LListEle(o);
	LListEle tmp;
	// Just add if empty
	if(head == null) {
		head = newLink;
		tail = newLink;
		po = head;
	} else {
		// If no po then go to the tail
		if(po == null) {
			getTail();
		}
		if(po == tail) {
			tail = newLink;
		}
		if(po.next != null) {
			tmp = po.next;
			tmp.prev = newLink;
			newLink.next = tmp;
		}
		po.next = newLink;
		newLink.prev = po;
	}
	// cleanup
	// Save the current position for constant checks
	if(po == null) {
		po = newLink;
	}
	return o;
}

// delete all objects
public void delAll() {
	// Why just do one level ???
	// We really are a zero?
	zero();
}

/**
 * get the next object
 *
 * @return next object or null
 */
public Object getNext() {
	Object st = null;
	if(po != null) {
		po = po.next;
		if(po != null) {
			st = po.stuff;
		}
	} else {
	// This is neccessary for using cursors
	// so a getNext starts the list
		st = getHead();
	}
	return st;
}

/**
 * get the previous object
 *
 * @return previous object or null
 */
public Object getPrev() {
	Object st = null;
	if(po != null) {
		po = po.prev;
		if(po != null) {
			st = po.stuff;
		}
	} else {
		st = getTail();
	}
	return st;
}

/**
 * get current positioned object
 *
 * @return current object or null
 */
public Object getAt() {
	Object st = null;
	if(po != null) {
		st = po.stuff;
	}
	return st;
}

/**
 * find the given object
 *
 * @param o object
 * @return found object or null
 */
public Object find(Object o) {
	Object rSt = getHead();
	while(rSt != null && rSt != o) {
		rSt = getNext();
	}
	// cleanup
	return rSt;
}
}
