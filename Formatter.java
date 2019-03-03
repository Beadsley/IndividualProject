import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * This class formats dates and times
 *
 * A seperate class to format these types has 
 * been created beacuse DateTimeFormatter
 * is non serialisable
 *
 * @author Daniel Beadleson
 */

public class Formatter{

    private static DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("E d/M/u");;
    private static DateTimeFormatter timeFormat=DateTimeFormatter.ofPattern("h:mma");;
    private static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");

   /*
    * Method checks if the date inputed through the
    * terminal is of the correct format
    * @param date to be analyseed
    * @return correct format
    */
    public static boolean checkDateFormat(String date){
    	LocalDate date2check = LocalDate.parse(date, inputFormat);
    	LocalDate currentDate=LocalDate.now();
    	if (currentDate.isAfter(date2check)){
    		return false;
    	}
    	else{
    		return true;
    	}	
    }
   /* 
	* Method converts a completion date into
	* a fancy format used to be printed back to the user  
	* @param completion date to be converted
	* @return fancy completion date
    */
    public static String duedateFormatter(String date){
    	LocalDate dateSystemFormat= LocalDate.parse(date, inputFormat);
    	String datePrintFormat=dateSystemFormat.format(dateFormat);
    	return datePrintFormat;    	
    }
   /*
    * Method converts the completion date into a format the
    * system can read and analyse
    * @param completion date to be converted
    * @return completion date in a system format
	*/
    public static LocalDate duedateSystemFormatter(String date){
    	LocalDate dateSystemFormat= LocalDate.parse(date, inputFormat);
    	return dateSystemFormat;    	
    }
   /*
    * Method converts a time into a fancy format
    * used to be printed back to the user 
    * @param time to be converted
    * @return converted time
    */
    public static String timeFormatter(LocalTime time){
    	String timePrintFormat=time.format(timeFormat);
    	return timePrintFormat;  	
    }
   /*
    * Method converts a date into a fancy format
    * used to be printed back to the user 
    * @param date to be converted
    * @return converted date
    */
    public static String dateFormatter(LocalDate date){
    	  String datePrintFormat=date.format(dateFormat);
    	  return datePrintFormat;
    }

}