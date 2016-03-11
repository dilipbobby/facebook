package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GettingYear {

public static void main(String args[]) throws ParseException{
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	//Date date = new Date();
	Date date = new Date();
	SimpleDateFormat ddf = new SimpleDateFormat("yyyy");
	 String ccomyear=ddf.format(date);
	System.out.println(ccomyear);
	
	
	String sdate="2016-01-10T14:26:06+0000";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //Date currentDate = format.parse(sdate);
    
    //String currentDateString = "02/27/2012 17:00:00";
    String currentDateString="2014-01-10T14:26:06+0000";
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    Date currentDate = sd.parse(currentDateString);
    SimpleDateFormat df = new SimpleDateFormat("yyyy");
    String comyear=df.format(currentDate);
   
    System.out.println(""+comyear);
   if(ccomyear!=null && ccomyear.equals(comyear)){
	   
	   System.out.println("equal");
	   
   }
    
    /*String yourDateString = "02/28/2012 15:00:00";
    SimpleDateFormat yourDateFormat = new SimpleDateFormat(
            "mm/dd/yyyy HH:mm:ss");

    Date yourDate = yourDateFormat.parse(yourDateString);

    if (yourDate.after(currentDate)) {
        System.out.println("After");
    } else if(yourDate.equals(currentDate))
    		{
        System.out.println("Same");
    }else{
                    System.out.println("Before");
            }
	*/
	
}

}
