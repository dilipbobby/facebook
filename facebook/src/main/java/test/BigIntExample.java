package test;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BigIntExample {
	
	private void sum(String newNumber) {
	    // BigInteger is immutable, reassign the variable:
		BigInteger sum = BigInteger.valueOf(0);
		sum = sum.add(new BigInteger(newNumber));
	    //sum = sum.add(BigInteger.valueOf(Long.parseLong(newNumber)));
	}
	public static void main(String args[]) throws ParseException{
	/*	String s = "9876543219876543000000000000";
		BigInteger bi1 =new BigInteger(s),bi2, bi3;
		String text = "2011-10-02 18:48:05.123456";
        Timestamp ts = Timestamp.valueOf(text);
        System.out.println("time stamp formate" +ts);
        String timestamp = "06/09/2006"; 
       
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
    	String dateInString = "06/09/2006";

    	try {

    		Date date = formatter.parse(dateInString);
    		System.out.println(date);
    		//System.out.println(formatter.format(date));

    	} catch (ParseException e) {
    		e.printStackTrace();
    	}*/
        
		//System.out.println("bigint conversion "+bi1);
		//  BigInteger b1 = new BigInteger("987654321987654321000000000");
		 // BigInteger b2 = new BigInteger("987654321987654321000000000");
		//String s="987654321987654321000000000";
		//bi1=BigInteger.valueOf(Long.parseLong(s));
		//System.out.println("coverted "+bi1);
		
		
		//bi1=BigInteger.TEN.;
	   String i="611892872245357";
	   long l = Long.parseLong(i);
	    System.out.println("long l = " + l);
		//long ll=Long.parseLong(s);
		
}
}