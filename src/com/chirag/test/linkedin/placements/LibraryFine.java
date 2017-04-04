package com.chirag.test.linkedin.placements;

import java.util.Scanner;

public class LibraryFine
{
	public static void main(String[] args)
	{
        Scanner in = new Scanner(System.in);
        int dayActual = in.nextInt();
        int monthActual = in.nextInt();
        int yearActual = in.nextInt();
        int dayDue = in.nextInt();
        int monthDue = in.nextInt();
        int yearDue = in.nextInt();
        in.close();
        
        int fine = 0;
        
        if(yearActual<yearDue
        		|| (yearActual==yearDue && monthActual<monthDue)
        		|| (yearActual==yearDue && monthActual==monthDue && dayActual<=dayDue))
        	fine = 0;
        else if(yearActual>yearDue)
        	fine = 10000;
        else if(monthActual>monthDue)
        	fine = 500 * (monthActual-monthDue);
        else if(dayActual>dayDue)
        	fine = 15 * (dayActual-dayDue);
        else
        	fine = 0;
        
        System.out.println(fine);
	}
}
