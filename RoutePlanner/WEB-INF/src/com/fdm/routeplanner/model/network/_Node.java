package com.fdm.routeplanner.model.network;

/**
 * A node is identified by its name, a single uppercase character.
 * Conversions to/from characters are handled by 
 * {@link #getName()} and {@link #valueOf(char)}, respectively.
 * <p>
 * Package members are also given access to an identity relationship between
 * cities and numbers: they can converts between <code>Node</code>
 * instances and numbers using {@link #valueOf(int)} and {@link #getIndex()}.
 * This special relationship is used by the
 * {@link com.waldura.tw.DenseRoutesMap DensesRoutesMap} 
 * to store nodes in an array.
 * 
 * @author Renaud Waldura &lt;renaud+tw@waldura.com&gt;
 * @version $Id: City.java 2367 2007-08-20 21:47:25Z renaud $
 */
public interface _Node extends Comparable<_Node> {
	
	/**
	 * @return Node name
	 */
	public abstract String getName();
	
	public abstract int getZone();
	
	/**
	 * @return Node hashcode
	 */
	public abstract int hashCode();
	
	/**
	 * Tests if node is in route network
	 * 
	 * @param obj
	 * @return
	 */
	public abstract boolean equals(Object obj);
	
	/**
	 * Compares node names
	 */
	public abstract int compareTo(_Node node);
}
