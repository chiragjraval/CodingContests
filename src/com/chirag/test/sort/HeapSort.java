package com.chirag.test.sort;

public class HeapSort {

	private static void heapify(int[] arr, int idx, int size)
	{
		int leftChild = (idx<<1) + 1;
		int rightChild = (idx<<1) + 2;
		int largestIdx = idx;
		
		if(leftChild<size && arr[largestIdx]<arr[leftChild])
			largestIdx = leftChild;
		
		if(rightChild<size && arr[largestIdx]<arr[rightChild])
			largestIdx = rightChild;
		
		if(largestIdx!=idx)
		{
			swap(arr, idx, largestIdx);
			heapify(arr, largestIdx, size);
		}
	}
	
	private static void createMaxHeap(int[] arr)
	{
		int arrLength = arr.length;
		int midIdx = (arrLength-2)>>1;
		
		for(int i=midIdx; i>=0; i--)
			heapify(arr, i, arrLength);
	}
	
	private static void heapSort(int[] arr)
	{
		int arrLength = arr.length;
		createMaxHeap(arr);
		System.out.print("Initial Heap :: ");
		printArray(arr);
		
		for(int i=arrLength-1; i>0; i--)
		{
			swap(arr, 0, i);
			heapify(arr, 0, i);
			System.out.print("\nFor Interation-" + i + " :: ");
			printArray(arr);
		}
	}
	
	private static void printArray(int[] arr)
	{
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + ", ");
	}
	
	private static void swap(int[] arr, int idx1, int idx2)
	{
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	public static void main(String[] args)
	{
		int arr[] = {80, 11, 15, 5, 20, 40, 33, 65, 90, 70};
		heapSort(arr);
		System.out.print("\nSorted Array :: ");
		printArray(arr);
	}
	
}
