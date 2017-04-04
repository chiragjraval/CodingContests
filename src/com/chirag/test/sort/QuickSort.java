package com.chirag.test.sort;

public class QuickSort
{
	private static int partition(int[] arr, int start, int end)
	{
		int key = arr[start];
		int i = start+1;
		int j = end;
		
		while(i<=j)
		{
			if(arr[i]>key && arr[j]<key)
			{
				swap(arr, i, j);
				i++; j--;
			}
			else
			{
				if(arr[i]<key) i++;
				if(arr[j]>key) j--;
			}
		}
		
		swap(arr, start, --i);
		return i;
	}
	
	private static void swap(int[] arr, int idx1, int idx2)
	{
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	private static void quickSort(int[] arr, int start, int end)
	{
		if(start<end)
		{
			int partitionIdx = partition(arr, start, end);
			quickSort(arr, start, partitionIdx-1);
			quickSort(arr, partitionIdx+1, end);
		}
	}
	
	private static void quickSort(int[] arr)
	{
		quickSort(arr, 0, arr.length-1);
	}
	
	private static void printArray(int[] arr)
	{
		for(int i=0; i<arr.length; i++)
			System.out.print(arr[i] + ", ");
	}
	
	public static void main(String[] args)
	{
		int arr[] = {80, 11, 15, 5, 20, 40, 33, 65, 90, 70, 100, 1};
		quickSort(arr);
		System.out.print("\nSorted Array :: ");
		printArray(arr);
		
		arr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		quickSort(arr);
		System.out.print("\nSorted Array :: ");
		printArray(arr);
	}
}
