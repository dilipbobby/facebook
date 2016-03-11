package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEx1 
{
	
	/*A classic method to compare two dates in Java.

	– Return value is 0 if both dates are equal.
	– Return value is greater than 0 , if Date is after the date argument.
	– Return value is less than 0, if Date is before the date argument.*/
	
     public static void main( String[] args ) 
    {
    	try{
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date date1 = sdf.parse("2009-12-31");
        	Date date2 = sdf.parse("2010-01-31");

        	System.out.println(sdf.format(date1));
        	System.out.println(sdf.format(date2));
        	
        	if(date1.compareTo(date2)>0){
        		System.out.println("Date1 is after Date2");
        	}else if(date1.compareTo(date2)<0){
        		System.out.println("Date1 is before Date2");
        	}else if(date1.compareTo(date2)==0){
        		System.out.println("Date1 is equal to Date2");
        	}else{
        		System.out.println("How to get here?");
        	}
        	
    	}catch(ParseException ex){
    		ex.printStackTrace();
    	}
    }
}