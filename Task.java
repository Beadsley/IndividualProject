import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.Period;
import java.io.Serializable;

/*
* Class creates a task object
*@param task the name of the task needs to be supplied
* notes:
* serialistaion interfacce is implemented to:
*	To serialize an object means to convert its state to a byte stream 
*	so that the byte stream can be reverted back into a copy of the object
*/
public class Task implements Serializable{ 

    private String taskName;
    private ArrayList<String>notes;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private String completionDate;   

    public Task(String taskName){

        this.taskName=taskName;
        dateCreated=LocalDate.now();        
        notes=new ArrayList<>();
        timeCreated=LocalTime.now();
        completionDate="";

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

    	//DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	//dueDate = LocalDate.parse(date, inputFormat);

    	completionDate=date;

 
    }
   /*
    * Returns the date in which the task
    * must be completed
    */
    public String getDueDate(){
    	
    		return completionDate;
    }
   /*
    * Returns the amount of time left 
    * to complete the task or if 
    * the task is over due
    */
    public String timeTillDueDate(){
    		LocalDate dueDate=Formatter.duedateSystemFormatter(completionDate);
	  		String dateDiff=diffDates(dueDate);
	  		LocalDate currentDate=LocalDate.now();
    		if(dueDate.isAfter(currentDate)){
    			return dateDiff+" to complete task";
    		}
    		else if(dueDate.equals(currentDate)){
    			return "Task must be completed today";
    		}
    		else{
    			return dateDiff+ " over due";
    		}
    }
   /*
    * adds notes about the task
    */
    public void addNote(String text){
        notes.add(text);
    }
   /*
    * returns the size of the notes arraylist
    */
    public int notesAvailable(){
    	return notes.size();
    }
    public void printNotes(){
    	notes.stream().forEach(System.out::println);
    }
   /*
    * method returns the time of creation
    */
    public String getTimeCreated()
    {    	
        return Formatter.timeFormatter(timeCreated);
    }
   /*
    * returns the date of creation
    */
    public String getDateCreated(){
    		return Formatter.dateFormatter(dateCreated);
    }
   /*
	* Returns the amount of time the 
	* since the task was created
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
    			return diffDates(currentDate)+" ago";

    		}
    }
   /*
    * Returns the difference between the date created and
    * @param a given date
    */
    public String diffDates(LocalDate date2Compare){

    		    Period period = Period.between(dateCreated, date2Compare);
    			int diffDays = Math.abs(period.getDays());
    			int diffMonths=Math.abs(period.getMonths());
    			int diffYears=Math.abs(period.getYears());
    			if(diffDays<24 && diffMonths<1){
    				return diffDays+ " day(s)";
    			}
    			else if(diffMonths<12 && diffYears<1){
    				return diffMonths+ " month(s)";
    			}
    			return "Over a year!";
    }  	 
}
