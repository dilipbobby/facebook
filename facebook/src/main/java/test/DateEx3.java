package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateEx3 
{
	
	/*
	 * Calender.before(), Calender.after() and Calender.equals()

Most common way to compare two dates with Calendar object, 
and believe it's most widely used method.
	 * */
     public static void main( String[] args ) 
    {
    	try{
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date date1 = sdf.parse("2009-12-31");
        	Date date2 = sdf.parse("2010-01-31");

        	System.out.println(sdf.format(date1));
        	System.out.println(sdf.format(date2));
        	
        	Calendar cal1 = Calendar.getInstance();
        	Calendar cal2 = Calendar.getInstance();
        	cal1.setTime(date1);
        	cal2.setTime(date2);
        	
        	if(cal1.after(cal2)){
        		System.out.println("Date1 is after Date2");
        	}
        	
        	if(cal1.before(cal2)){
        		System.out.println("Date1 is before Date2");
        	}
        	
        	if(cal1.equals(cal2)){
        		System.out.println("Date1 is equal Date2");
        	}
        	
    	}catch(ParseException ex){
    		ex.printStackTrace();
    	}
    }
}
