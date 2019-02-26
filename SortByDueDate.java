import java.util.Comparator;
public class SortByDueDate implements Comparator<Task>{

    public int compare(Task t, Task t2) {
    return	Formatter.duedateSystemFormatter(t.getDueDate())
    		.compareTo(Formatter.duedateSystemFormatter(t2.getDueDate()));
  	}


}