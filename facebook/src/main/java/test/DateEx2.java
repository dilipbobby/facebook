package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEx2 
{
	
	
	/*
	 * 
	 * 
	 * Date.before(), Date.after() and Date.equals()

A more user friendly method to compare two dates. Java developers seldom use this method, 
not really sure what may causing it, may be they just didn't aware of it?
	 * 
	 * 
	 * 
	 * */
    public static void main( String[] args ) 
    {
    	try{
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date date1 = sdf.parse("2009-12-31");
        	Date date2 = sdf.parse("2010-01-31");

        	System.out.println(sdf.format(date1));
        	System.out.println(sdf.format(date2));
        	
        	if(date1.after(date2)){
        		System.out.println("Date1 is after Date2");
        	}
        	
        	if(date1.before(date2)){
        		System.out.println("Date1 is before Date2");
        	}
        	
        	if(date1.equals(date2)){
        		System.out.println("Date1 is equal Date2");
        	}
        	
    	}catch(ParseException ex){
    		ex.printStackTrace();
    	}
    }
}
