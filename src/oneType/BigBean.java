package oneType;

import java.lang.invoke.MethodHandles;

/**
 *
 */
public class BigBean extends StringBean {
// pooled Instance

private static int instFactIdBB = -1;
private static final int preSizeBB = 200000;

static public BigBean getInst() {
	if(instFactIdBB < 0) {
		instFactIdBB = addInstFact(MethodHandles.lookup().lookupClass().getName());
	}
	// Unlike the others because the presize is overwritten have to add the builder last
	BigBean r = (BigBean) getPooledInst(instFactIdBB);
	if(r.sb == null) {
		r.sb = new StringBuilder(preSizeBB);
	}
	return r;
}

public static String getStrBeanStats() {
	return getBeanStats(instFactIdBB,"BigBean",preSizeBB);
}
}
