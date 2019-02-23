import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.Period;
/*
* Class creates a task object
*@param task the name of the task needs to be supplied
* notes:
* serialistaion interfacce is implemented to:
*	To serialize an object means to convert its state to a byte stream 
*	so that the byte stream can be reverted back into a copy of the object
*/

public class Task  { 
	//fields
    private String taskName;
    private long timestamp;
    private ArrayList<String>notes;
    private LocalDate dateCreated;
    private LocalDate dueDate;
    private LocalTime timeCreated;
    private DateTimeFormatter dateFormat;
    private DateTimeFormatter timeFormat;   

    public Task(String taskName){
        this.taskName=taskName;
        timestamp = System.currentTimeMillis();
        dateCreated=LocalDate.now();        
        notes=new ArrayList<>();
        timeCreated=LocalTime.now();
        dueDate=null;
        dateFormat = DateTimeFormatter.ofPattern("E d/M/u");
        timeFormat=DateTimeFormatter.ofPattern("h:mma");
    }
    /*
    * returns the name of the task
	*/
    public String getTaskName(){
    	return taskName;
    }
    /*
    * set due date
    * @param date in the format 12/12/2018
    */
    public void setDueDate(String date){
    	//check if the due date is in the future
    	//isAfter() method
    	DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	dueDate = LocalDate.parse(date, inputFormat);
    	System.out.println(dueDate);
    	//System.out.println(dueDate);

    }

    public String getDueDate(){
    	
    		return dueDate.format(dateFormat);
    }

    public void timeTillDueDate(){

    	    LocalDate currentDate=LocalDate.now();
    	    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy"); 
    		Period period = Period.between(LocalDate.parse("12/10/1992", inputFormat),currentDate);
    		int diff = period.getDays();
    		int diffMonths=period.getMonths();
    		int diffYears=period.getYears();
    		System.out.println(diff);
    		System.out.println(diffMonths);
    		System.out.println(diffYears);
    }
    /*
    * adds notes about the task
    */
    public void addNote(String text){
        notes.add(text);
    }

    /*
    * method returns the time of creation
    */

    public String getTimeCreated()
    {
    	
        	return timeCreated.format(timeFormat);
    }

    /*
    * returns the date of creation
    */

    public String getDateCreated(){

    		return dateCreated.format(dateFormat);
    }

	/*
	 * returns the amount of time the task has been created
	 */
    public String getTaskLifeTime(){
    	LocalTime currentTime=LocalTime.now();
    	long elapsedSeconds=Duration.between(timeCreated,currentTime).toSeconds();
    	long elapsedMinutes=Duration.between(timeCreated, currentTime).toMinutes();
    	long elapsedHours=Duration.between(timeCreated, currentTime).toHours();
    		if (elapsedSeconds<60){
    			return elapsedSeconds+ " seconds ago";
    		}
    		else if(elapsedMinutes<60){
    			return elapsedMinutes+ " minute(s) ago";
    		}
    		else if(elapsedHours<24){
    			return elapsedHours+ " hour(s) ago";
    		}
    		else {
    			LocalDate currentDate=LocalDate.now();
    			Period period = Period.between(dateCreated,currentDate);
    			int diffDays = period.getDays();
    			int diffMonths=period.getMonths();
    			int diffYears=period.getYears();
    			if(diffDays<24){
    				return diffDays+ " day(s) ago";
    			}
    			else if(diffMonths<12 && diffYears<1){
    				return diffYears+ " month(s) ago";
    			}
    			return "Over a year ago mate!";
    		}  	 
    	}
  





}

