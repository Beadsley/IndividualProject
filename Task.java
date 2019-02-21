import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
/*
* Class creates a task object
*@param task the name of the task needs to be supplied
* notes:
* serialistaion interfacce is implemented to:
*	To serialize an object means to convert its state to a byte stream 
*	so that the byte stream can be reverted back into a copy of the object
*/

public class Task implements Serializable { 
	//fields
    private String task;
    private long timestamp;
    private ArrayList<String>notes;
    private LocalDate date;
    private LocalTime time;

    public Task(String task){
        this.task=task;
        timestamp = System.currentTimeMillis();
        date=LocalDate.now();        
        notes=new ArrayList<>();
        time=LocalTime.now();
    }

    public void addNote(String text){

        notes.add(text);
    }

    /*
    * method returns the time of creation
    */

    public String getCreationTime()
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
        return time.format(formatter);
    }

    /*
    * returns the date of creation
    */

    public String getCreationDate(){
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E d/M/u");
    	return date.format(formatter);
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

    public void outputFile() throws Exception {

    	//Task cleanHouse= new Task("Clean House");	

		FileOutputStream fos = new FileOutputStream("/Users/beadsley/Dropbox/IP/Bin/Test.txt");
      	ObjectOutputStream oos = new ObjectOutputStream(fos);
      	oos.writeObject("What UP");
      	oos.close();
    	
    }



}

