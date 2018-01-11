package oneType;

import java.lang.invoke.MethodHandles;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DecForm extends PooledInst {
// pooled Instance
private static final int pooledCache = 2;
private static int instFactId = -1;

private static String decimalSeparator = ".";
private DecimalFormat decimalFormat;

public DecimalFormat getDecimalFormat() {
	return decimalFormat;
}

public void setDecimalFormat(DecimalFormat x) {
	decimalFormat = x;
}

public static String getDecimalSeparator() {
	return decimalSeparator;
}

/**
 * Take care of decimal separator for CeaJournal
 *
 * @param x
 */
public static void setDecimalSeparator(String x) {
	decimalSeparator = x;
}

public static DecForm getInst() {
	if(instFactId < 0) {
		instFactId = addInstFact(MethodHandles.lookup().lookupClass().getName(),pooledCache);
	}
	DecForm df = (DecForm) getPooledInst(instFactId);
	DecimalFormat bdf = df.getDecimalFormat();
	if(bdf == null) {
		bdf = new DecimalFormat();
		df.setDecimalFormat(bdf);
	}
	// Always start as a double
	setDefDecimalFormat(bdf);
	// cleanup
	return df;
}

public static void setDefDecimalFormat(DecimalFormat bdf) {
	DecimalFormatSymbols dfs;
	bdf.setDecimalSeparatorAlwaysShown(true);
	bdf.setGroupingUsed(false);
	bdf.setMinimumFractionDigits(1);
	bdf.setMaximumFractionDigits(3);
	bdf.setMinimumIntegerDigits(1);
	bdf.setMaximumIntegerDigits(OType.edLen(OType.OTDBL) - 3);
	dfs = bdf.getDecimalFormatSymbols();
	dfs.setDecimalSeparator(decimalSeparator.charAt(0));
	bdf.setDecimalFormatSymbols(dfs);
}
}	// End class
