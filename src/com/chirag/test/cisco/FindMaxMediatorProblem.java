package com.chirag.test.cisco;

public class FindMaxMediatorProblem {

	
	/**
	 * @author Chirag
	 * A class to pass integer as Pass by reference across method calls
	 */
	static class IntegerRef
	{
		public int value;
		
		public IntegerRef(int value)
		{
			this.value = value;
		}
	}
	
	/**
	 * @param tree String Array containing tree structure
	 * @param index index of current node under consideration
	 * @param maxHeight IntegerRef object to hold curretn height node
	 * @param size Size of Array representing tree
	 * @return It return integer value indicating maximum distance between any two nodes of subtree with root as tree[index]
	 */
	private static int maxMediatorsUtil(String[] tree, int index, IntegerRef maxHeight, int size)
    {
		IntegerRef lsMaxHeight = new IntegerRef(0), rsMaxHeight = new IntegerRef(0);
		int lsMaxMediators = 0 , rsMaxMediators = 0;
		
		if(index>=size || tree[index]==null || tree[index].trim().isEmpty())
		{
			maxHeight.value = 0;
			return 0;
		}
		
		int leftChildIndex = (index<<1);
		int rightChildIndex = (index<<1) + 1;
		
		lsMaxMediators = maxMediatorsUtil(tree ,leftChildIndex, lsMaxHeight, size);
		rsMaxMediators = maxMediatorsUtil(tree ,rightChildIndex, rsMaxHeight, size);
		
		maxHeight.value = Math.max(lsMaxHeight.value, rsMaxHeight.value) + 1;
		
		return Math.max(lsMaxHeight.value + rsMaxHeight.value + 1, Math.max(lsMaxMediators, rsMaxMediators));
    }
	
	/**
	 * @param input1 String Array containing tree structure
	 * @param input2 Size of Array representing tree
	 * @return It return integer value indicating maximum mediators between two nodes of subtree with root as input1[1]
	 */
	public static int maxMediators(String[] input1,int input2)
    {
		if(input2<=0)
			return 0;
		
		IntegerRef maxHeight = new IntegerRef(0);
		int maxMediators = maxMediatorsUtil(input1, 1, maxHeight, input2);
		maxMediators = (maxMediators>2) ? maxMediators-2 : 0;
		
		return maxMediators;
    }
	
	
	public static void main(String[] args)
	{
		String[] tree = {"","D1","D2","D3","D4","D5","D6","","C1","","C2","C3","D7","D8","","","","","","","","","","","C4","","C5","C6","","","",""};
		int size = tree.length;
		
		//String[] tree = {"","D1","D2","","D3"};
		//int size = tree.length;
		
		System.out.println(maxMediators(tree, size));
	}
}
