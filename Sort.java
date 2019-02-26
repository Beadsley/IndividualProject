import java.util.Comparator;
public class Sort implements Comparator<Task>{

    public int compare(Task t, Task t2) {
    return	Formatter.duedateSystemFormatter(t.getDueDate())
    		.compareTo(Formatter.duedateSystemFormatter(t2.getDueDate()));
    //return getDateCreated().compareTo(t.getDateCreated());
  	}
  	/*
  	    	Sort s=new Sort();
    	toDoList.stream()
    			.sorted(s)

    			.forEach(t->System.out.println(t.getTaskName()+"------->"+t.getDueDate()));
  	*/

}