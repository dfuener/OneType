/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneType;

import java.util.ArrayList;

/**
 *
 */
public class PooledInst {
// Class created via instance factory must have package

private static final ArrayList<InstFact> instFactA = new ArrayList<>();
private static int totInstFact = -1;
// Factory Instance
private long id = -1;
protected int pooledFactId;

static protected int addInstFact(String pooledInstClass) {
	return addInstFact(pooledInstClass,0);
}

static protected int addInstFact(String pooledInstClass,int pooledCache) {
	instFactA.add(++totInstFact,new InstFact(pooledInstClass,pooledCache));
	// cleanup
	return totInstFact;
}

static protected PooledInst getPooledInst(int factId) {
	PooledInst r = (PooledInst) instFactA.get(factId).getInst();
	r.pooledFactId = factId;
	return r;
}

static protected InstFact getInstFact(int factId) {
	return instFactA.get(factId);
}

public long getId() {
	return id;
}

void setId(long x) {
	id = x;
}

/**
 * clear stub
 */
protected void clear() {
}

public void release() {
	clear();
	instFactA.get(pooledFactId).release(this,id);
}

/**
 * get Current usage statistics
 * @return
 */
public static String getStrStats() {
	StringBuilder sb = new StringBuilder();
	instFactA.forEach((instFact) -> {
		sb.append(instFact.getStrStats());
		sb.append("\n");
	});
	return sb.toString();
}
}
