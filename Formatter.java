import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
/*
 * A seperate class to format dates and times
 * has been created due to the fact 
 * DateTimeFormatter is not serialisable
*/
public class Formatter{

    private static DateTimeFormatter dateFormat;
    private static DateTimeFormatter timeFormat; 

/*
    * checks if the date is of the correct format
    * @param date to be analyseed
    */
    public static boolean checkDateFormat(String date){
    	DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	LocalDate date2check = LocalDate.parse(date, inputFormat);
    	LocalDate currentDate=LocalDate.now();
    	if (currentDate.isAfter(date2check)){
    		return false;
    	}
    	else{
    		return true;
    	}	
    }

    public static String duedateFormatter(String date){
    	dateFormat=DateTimeFormatter.ofPattern("E d/M/u");
    	DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	LocalDate dateSystemFormat= LocalDate.parse(date, inputFormat);
    	String datePrintFormat=dateSystemFormat.format(dateFormat);
    	return datePrintFormat;    	
    }

    public static LocalDate duedateSystemFormatter(String date){
    	dateFormat=DateTimeFormatter.ofPattern("E d/M/u");
    	DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	LocalDate dateSystemFormat= LocalDate.parse(date, inputFormat);
    	return dateSystemFormat;    	
    }

    public static String timeFormatter(LocalTime time){
    	timeFormat=DateTimeFormatter.ofPattern("h:mma");
    	String timePrintFormat=time.format(timeFormat);
    	return timePrintFormat;  	
    }
    public static String dateFormatter(LocalDate date){
    	  dateFormat=DateTimeFormatter.ofPattern("E d/M/u");
    	  String datePrintFormat=date.format(dateFormat);

    	  return datePrintFormat;
    }



}