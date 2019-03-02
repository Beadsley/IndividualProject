import java.util.ArrayList;
import java.io.Serializable;

/*
 * Class creates a todo list used to
 * store task objects
 */

public class ToDoList implements Serializable{

    private ArrayList<Task> toDoList;

    public ToDoList(){

        toDoList= new ArrayList<>();        

    }
   /*
    * Method adds a task to the list
    * @param task to be added 
    */
    public void addToList(Task t){
        toDoList.add(t);
    }
   /*
    * removes a task from the list
    * @param index of the task
    */
    public void removeTask(int i){
    	toDoList.remove(i);
    }
   /*
    * prints the contents of the list
    * @param to sort this list by duedate or 
    * not
    */
    public void printList(Boolean sorted){
		Interaction.printMessage("---");
		System.out.println(String.format("%1$-30s %2$-10s", " ToDos:", "Complete by:"));
    	Interaction.printMessage("---");
    	if(toDoList.size()>0 && !sorted){
			for (int i=0; i<toDoList.size(); i++){
    			System.out.println("<"+i+"> " + String.format("%1$-30s %2$-10s",
    				               toDoList.get(i).getTaskName(),    								
    								Formatter.duedateFormatter(toDoList.get(i).getDueDate())));
    		}
    	}
    	else if(toDoList.size()>0 && sorted){
			toDoList.stream()
    			    .sorted(new SortByDueDate())
    			    .forEach(t->System.out.println(String.format(
    			    		 "%1$-30s %2$-10s",
    			    	     t.getTaskName(),			
    					     Formatter.duedateFormatter(t.getDueDate()))));
    	}
    	
    	else{
    		System.out.println("***** List Empty *****");
    	}
    	Interaction.printMessage("---");
    }
   /* Method returns a task object
    * @return Task object 
    * @param index of the task to return
    */
    public Task getTask(int i){

    	return toDoList.get(i);
    }

   /*
    * Method returns the size of a list
    * @return true if the element exists in the list
    * @param names of the task to find
    */
    public int getListSize(){

    	return toDoList.size();
    }
   /* 
    * assesses if abtask elememt exists
    * in the list
    * @return 
    * @param name of the task 
    */
    public boolean taskExists(String task2find){
        boolean found =false;
        for(Task t: toDoList){
        	String taskName=t.getTaskName();
            if (taskName.equals(task2find)){ //****need to convert so it retreives task as a string****

                found=true;
            }
        }
        return found;
    }

}
