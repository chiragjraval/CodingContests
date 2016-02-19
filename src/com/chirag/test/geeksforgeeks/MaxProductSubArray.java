package com.chirag.test.geeksforgeeks;

public class MaxProductSubArray {
	
	static class SubArrayProduct
	{
		int startIndex;
		int endIndex;
		int product;
		
		public SubArrayProduct(int startIndex, int endIndex, int product)
		{
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.product = product;
		}
	}
	
	public static void printArray(int[] arr)
	{
		System.out.print("\n\nArray = [ ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("]");
	}
	
	private static void printMaxProductSubArray(SubArrayProduct maxSubArray, int[] arr)
	{
		System.out.print("\nMax Sub Array = [ ");
		for(int i=maxSubArray.startIndex; i<=maxSubArray.endIndex; i++)
			System.out.print(arr[i] + " ");
		System.out.print("]");
		
		System.out.print("\nMax Sub Array Product = " + maxSubArray.product);
	}
	
	private static SubArrayProduct getMaxProductSubArray(int[] arr)
	{
		if(arr.length < 1)
			return null;
		
		SubArrayProduct maxSubArray = new SubArrayProduct(0 , 0, arr[0]);
		
		for(int i=0, j=0, product=1; j<arr.length; j++)
		{
			product *= arr[j]; 
			
			if(maxSubArray.product < product)
			{
				maxSubArray.product = product;
				maxSubArray.startIndex = i;
				maxSubArray.endIndex = j;
			}
			if(product == 0)
			{
				product = 1;
				i = j+1;
			}
		}
		
		return maxSubArray;
	}
	
	
	public static void main(String[] args)
	{
		int[] arr = {6, -3, -10, 0, 2};
		SubArrayProduct maxSubArray = getMaxProductSubArray(arr);
		printArray(arr);
		printMaxProductSubArray(maxSubArray, arr);
		
		arr = new int[]{-1, -3, -10, 0, 60};
		maxSubArray = getMaxProductSubArray(arr);
		printArray(arr);
		printMaxProductSubArray(maxSubArray, arr);
		
		arr = new int[]{-2, -50, 10, -1, -10, 0, -2, -40};
		maxSubArray = getMaxProductSubArray(arr);
		printArray(arr);
		printMaxProductSubArray(maxSubArray, arr);
		
		arr = new int[]{6, -3, 10, 0, 2};
		maxSubArray = getMaxProductSubArray(arr);
		printArray(arr);
		printMaxProductSubArray(maxSubArray, arr);
	}
}
