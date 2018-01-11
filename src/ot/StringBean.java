package oneType;

import java.lang.invoke.MethodHandles;

public class StringBean extends PooledInst {
// pooled Instance

private final static int pooledCache = 4;
private static int instFactId = -1;

// Builder initial size
protected static int preSize = 10000;
public StringBuilder sb;

static public StringBean getInst() {
	if(instFactId < 0) {
		instFactId = addInstFact(MethodHandles.lookup().lookupClass().getName(),pooledCache);
	}
	// Unlike the others because the presize is overwritten have to add the builder last
	StringBean r = (StringBean) getPooledInst(instFactId);
	if(r.sb == null) {
		r.sb = new StringBuilder(preSize);
	}
	return r;
}

@Override
protected void clear() {
	sb.setLength(0);
}

static public String getStrBeanStats() {
	return getBeanStats(instFactId,"StringBean ",preSize);
}

static protected String getBeanStats(int instId,String name,int size) {
	StringBuilder sCute = new StringBuilder();
	sCute.append(name);
	sCute.append(" ");
	sCute.append(size);
	sCute.append("\n");
	if(instId > -1) {
		OT objList = getInstFact(instId).getObjList();
		OT c = objList.getHead();
		int cnt = 0;
		StringBean sBean;
		// Got one that was saved ?
		while(c != null) {
			// Take out of the pool
			sBean = (StringBean) c.eO();
			if(sBean.sb != null) {
				if(sBean.sb.capacity() > size) {
					sCute.append("Buffer ");
					sCute.append(cnt);
					sCute.append(" ");
					sCute.append(sBean.sb.capacity());
					sCute.append("\n");
				}
			}
			cnt++;
			c = objList.getNext();
		}
	}
	// cleanup
	return sCute.toString();
}
}
