package oneType;
/**
 * Maintain previous and next as well as data object
 */
public class LListEle {
/**
 * Create LListEle
 */
public LListEle() {
}

/**
 * Create LListEle
 * @param o object
 */
public LListEle(Object o) {
	stuff = o;
}
/**
 * clean
 */
public void clean() {
	prev = null;
	next = null;
	stuff = null;
}

protected LListEle prev;
protected LListEle next;
protected Object stuff;
}
