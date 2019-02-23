import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private LocalDate date;
    private LocalDate dueDate;
    private LocalTime time;
    private DateTimeFormatter dateFormat; 

    public Task(String taskName){
        this.taskName=taskName;
        timestamp = System.currentTimeMillis();
        date=LocalDate.now();        
        notes=new ArrayList<>();
        time=LocalTime.now();
        dueDate=null;
        dateFormat = DateTimeFormatter.ofPattern("E d/M/u");
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
    	DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	dueDate = LocalDate.parse(date, inputFormat);
    	System.out.println(dueDate);
    	//System.out.println(dueDate);

    }

    public String getDueDate(){
    	
    		return dueDate.format(dateFormat);
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

    public String getCreationTime()
    {
    	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
        	return time.format(timeFormat);
    }

    /*
    * returns the date of creation
    */

    public String getCreationDate(){

    		return date.format(dateFormat);
    }

	/*
	 * returns the amount of time the task has been created
	 */
    public String lifeTime()
    {
        long current = System.currentTimeMillis();	
        long pastMillis = current - timestamp;      // time passed in milliseconds
        long seconds = pastMillis/1000;
        long minutes = seconds/60;
        long hours = minutes/60;
        long days =hours/24;

        if(minutes > 0) {
            return minutes + " minutes ago";
        }
        else if(hours>0){
        	return hours + " hours ago";
        }
        else if(days>0){
        	return days + " days ago";
        }
        else {
            return seconds + " seconds ago";
        }
    }





}

