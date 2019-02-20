import java.util.ArrayList;
import java.time.LocalDate;
/*
* Class creates a task object
*@param task the name of the task needs to be supplied
*/
public class Task{
	//fields
    private String task;
    private long timestamp;
    private ArrayList<String>notes;
    private LocalDate datestamp;

    public Task(String task){
        this.task=task;
        timestamp = System.currentTimeMillis();
        datestamp=LocalDate.now();        
        notes=new ArrayList<>();
    }

    public void addNote(String text){

        notes.add(text);
    }

    /*
    * method returns the time of creation
    */

    public long getTimeStamp()
    {
        return timestamp;
    }

    /*
    * returns the date of creation
    */

    public LocalDate getDateStamp(){

    	return datestamp;
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
        if(minutes > 0) {
            return minutes + " minutes ago";
        }
        else {
            return seconds + " seconds ago";
        }
    }

    //method of the amount of days gone

}

