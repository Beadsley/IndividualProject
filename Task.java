import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.Period;
import java.io.Serializable;

/**
 * This class creates a task object
 *
 * @param taskName
 * 
 * a task has a number of attributes.
 * a completion date must be assigned when
 * a task object is created. A task can have notes 
 * assigned and be assigned to a project. The creation date 
 * and time is assigned automatically when a task is 
 * created, alongside its status.
 *
 * @author Daniel Beadleson   
 */

public class Task implements Serializable{ 

    private String taskName;
    private ArrayList<String>notes;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private String completionDate;
    private boolean done;   
	private Project project;
    private String projectName;    

    public Task(String taskName){

        this.taskName=taskName;
        dateCreated=LocalDate.now();        
        notes=new ArrayList<>();
        timeCreated=LocalTime.now();
        completionDate="";
        done=false;
        projectName="Not Assigned";       

    }
   /**
    * Method sets the name of the task
    * @param taskName
    */
    public void setTaskName(String name){
    	taskName=name;
    }
   /**
    * Method returns the name of the task
    * @return taskName
	*/
    public String getTaskName(){
    	return taskName;
    }
   /**
    * Method assigns a completion date
    * @param date in the format dd/mm/yyyy
    */
    public void setDueDate(String date){
    	completionDate=date;
    }
   /**
    * Method returns the date in which the task
    * must be completed
    * @return completion date
    */
    public String getDueDate(){    	
    		return completionDate;    
    }
   /**
    * Method assigns a task to a project
    * @param projectName
    */
    public void setTask2project(String name){
        this.projectName=name;
        Project.addProject2Set(name);
    }
   /**
    * Method returns the project that the task is 
    * assigned to
    * @return projectName
    */
    public String getprojectName(){
        return projectName;
    }
   /**
    * Method returns the status of the task
    * @return task status
    */
    public String getStatus(){
    	if(done){
    		return "Completed";
    	}
    	return "Not Completed";
    }
   /**
    * Method assigns the task to completed
    * or not completed
    * @param task status
    */
    public void set2Completed(boolean completed){
    	if(completed){
    		done=true;
    	}
    	else if (!completed){
    		done=false;
    	}    	
    }
   /**
    * Method returns the amount of time left 
    * to complete the task or if 
    * the task is over due
    * @return time till completion
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
   /**
    * Method adds notes corresponding to the task
    * @param text2add
    */
    public void addNote(String text){
        notes.add(text);
    }
   /**
    * Method returns the size of the notes arraylist
    * @return amount of a tasks notes 
    */
    public int notesAvailable(){
    	return notes.size();
    }
   /**
    * Method prints the contents of the notes
    */
    public void printNotes(){
    	notes.stream().forEach(System.out::println);
    }
   /**
    * Method returns the time of creation
    * @return time created
    */
    public String getTimeCreated()
    {    	
        return Formatter.timeFormatter(timeCreated);
    }
   /**
    * Method returns the date of creation
    * @return date created
    */
    public String getDateCreated(){
    		return Formatter.dateFormatter(dateCreated);
    }
   /**
	* Method returns the amount of time  
	* since the task was created
    * @return tasks age
	*/
    public String getTaskLifeTime(){
    	LocalTime currentTime=LocalTime.now();
    	long elapsedSeconds=Duration.between(timeCreated,currentTime).toSeconds();
    	long elapsedMinutes=Duration.between(timeCreated, currentTime).toMinutes();
    	long elapsedHours=Duration.between(timeCreated, currentTime).toHours();
    		if (elapsedSeconds<60 && elapsedSeconds>0){
    			return elapsedSeconds+ " seconds ago";
    		}
    		else if(elapsedMinutes<60 && elapsedMinutes>0){
    			return elapsedMinutes+ " minute(s) ago";
    		}
    		else if(elapsedHours<24 && elapsedHours>0){
    			return elapsedHours+ " hour(s) ago";
    		}
    		else {
    			LocalDate currentDate=LocalDate.now();
    			return diffDates(currentDate)+" ago";

    		}
    }
   /**
    * Method Returns the difference between the date 
    * created and a specified date
    * @param date to be compared too
    * @return difference between 2 dates
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
