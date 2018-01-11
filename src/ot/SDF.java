package oneType;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SDF extends PooledInst {
// pooled Instance

private static int instFactId = -1;
SimpleDateFormat df;

public SDF() {
	df = new SimpleDateFormat();
	df = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT,OTNSp.getLocale());
	df.setLenient(false);
	if(!OTNSp.getDatePattern().equals(OT.nst)) {
		df.applyPattern(OTNSp.getDatePattern());
	}
}

/**
 * set systemwide date pattern by clearing the current SDF list and recreating
 * @param datePattern
 */
static public void setDatePattern(String datePattern) {
	// there is the chance that one is in release
	// does this make sense?????
	if(instFactId > -1) {
		getInstFact(instFactId).clrObjList();
	}
}

static public SDF getInst() {
	if(instFactId < 0) {
		instFactId = addInstFact(MethodHandles.lookup().lookupClass().getName());
	}
	return (SDF) getPooledInst(instFactId);
}

public String getSDF(String pattern,Date dt) {
	SimpleDateFormat fdf = new SimpleDateFormat(pattern);
	fdf.setLenient(false);
	return fdf.format(dt);
}

public String format(Date dt) {
	return df.format(dt);
}

public Date parse(String sDt) {
	Date dt;
	try {
		dt = df.parse(sDt);
	} catch(ParseException pe) {
		if(OTNSp.getDebug()) {
			OTNSp.buzz("SDF.parse " + pe.getMessage());
		}
		dt = null;
	}
	return dt;
}

public String toPattern() {
	return df.toPattern();
}
}
