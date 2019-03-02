import java.util.Comparator;

/*
 * Class allows the todo list to be sorted
 * by the date of completion  
 */

public class SortByDueDate implements Comparator<Task>{
   /*
	* Method compares the copletion date of
	* 2 task objects
	*/
    public int compare(Task t, Task t2) {
    return	Formatter.duedateSystemFormatter(t.getDueDate())
    		.compareTo(Formatter.duedateSystemFormatter(t2.getDueDate()));
  	}


}