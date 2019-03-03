import java.util.Comparator;

/*
 * This Class allows the todo list to be sorted
 * by the date of completion  
 *
 * @author Daniel Beadleson 
 */

public class SortByDueDate implements Comparator<Task>{
   /*
	* Method compares the completion date of
	* 2 task objects
	*/
    public int compare(Task t, Task t2) {
    return	Formatter.duedateSystemFormatter(t.getDueDate())
    		.compareTo(Formatter.duedateSystemFormatter(t2.getDueDate()));
  	}


}