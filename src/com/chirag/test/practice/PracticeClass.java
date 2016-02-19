package com.chirag.test.practice;

import java.util.ArrayList;
import java.util.List;

public class PracticeClass {

	public class Solution {
		// DO NOT MODIFY THE LIST
		public ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> a)
		{
			 ArrayList<Integer> result = new ArrayList<Integer>();
			 
			 int m = a.size(), n =a.get(0).size();
			 int i, k = 0, l = 0;
	 
	        /*  k - starting row index
	            m - ending row index
	            l - starting column index
	            n - ending column index
	            i - iterator
	        */
	 
	        while (k < m && l < n)
	        {
	            /* Print the first row from the remaining rows */
	            for (i = l; i < n; ++i)
	            {
	                result.add(a.get(k).get(i));
	            }
	            k++;
	 
	            /* Print the last column from the remaining columns */
	            for (i = k; i < m; ++i)
	            {
	            	result.add(a.get(i).get(n-1));
	            }
	            n--;
	 
	            /* Print the last row from the remaining rows */
	            if ( k < m)
	            {
	                for (i = n-1; i >= l; --i)
	                {
	                	result.add(a.get(m-1).get(i));
	                }
	                m--;
	            }
	     
	            /* Print the first column from the remaining columns */
	            if (l < n)
	            {
	                for (i = m-1; i >= k; --i)
	                {
	                	result.add(a.get(i).get(l));
	                }
	                l++;    
	            }        
	        }
			 
			return result;
		}
	}

	
}
