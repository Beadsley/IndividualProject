import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.Period;
import java.io.Serializable;

/*
 * Class creates a task object
 * @param the name of the task 
 * a task can have a number of attributes.
 * a completion date must be assigned when
 * a task object is created. A task object is 
 * automatically assigned to uncompleted. A task 
 * can have notes assigned to it and the creation 
 * time, alngside timeto completion date may be 
 * calculated  
 */

public class Task implements Serializable{ 

    private String taskName;
    private ArrayList<String>notes;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private String completionDate;
    private boolean done;   
	private Project project;    

    public Task(String taskName){

        this.taskName=taskName;
        dateCreated=LocalDate.now();        
        notes=new ArrayList<>();
        timeCreated=LocalTime.now();
        completionDate="";
        done=false;
        project= new Project("Not Assigned");
        ToDoList.addProject2Set(project);

    }
   /*
    * Method sets the name of the task
    * @param task's name
    */
    public void setTaskName(String name){
    	taskName=name;
    }
   /*
    * returns the name of the task
    * @return tasks name
	*/
    public String getTaskName(){
    	return taskName;
    }
   /*
    * set due date
    * @param date in the format 12/12/2018
    */
    public void setDueDate(String date){
    	completionDate=date;

    }
   /*
    * Returns the date in which the task
    * must be completed
    * @return completion date
    */
    public String getDueDate(){    	
    		return completionDate;    
    }
   /*
    * Returns the project object
    * @return project
    */
    public Project getProject(){
   		return project;
   }
   /* 
    * Returns the status of the task
    * @return task status
    */
    public String getStatus(){
    	if(done){
    		return "Completed";
    	}
    	return "Not Completed";
    }
   /*
    * sets the task to completed
    * or not completed
    * @param tasks status
    */
    public void set2Completed(boolean completed){
    	if(completed){
    		done=true;
    	}
    	else if (!completed){
    		done=false;
    	}    	
    }
   /*
    * Returns the amount of time left 
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
   /*
    * adds notes about the task
    * @oaram notes content
    */
    public void addNote(String text){
        notes.add(text);
    }
   /*
    * returns the size of the notes arraylist
    * @return amount of a tasks notes 
    */
    public int notesAvailable(){
    	return notes.size();
    }
   /*
    * Method prints the contents of the notes
    */
    public void printNotes(){
    	notes.stream().forEach(System.out::println);
    }
   /*
    * method returns the time of creation
    * @return time created
    */
    public String getTimeCreated()
    {    	
        return Formatter.timeFormatter(timeCreated);
    }
   /*
    * returns the date of creation
    * @return date created
    */
    public String getDateCreated(){
    		return Formatter.dateFormatter(dateCreated);
    }
   /*
	* Returns the amount of time  
	* since the task was created
    * @return tasks age
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
    * a date
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
