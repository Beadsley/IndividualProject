import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class creates a todo list used to store tasks. 
 *
 * @author Daniel Beadleson 
 */

public class ToDoList implements Serializable{

    private ArrayList<Task> toDoList;
    private long completedTasks;
    private long unCompletedTasks;

    public ToDoList(){

        toDoList= new ArrayList<>(); 
        completedTasks=0;
        unCompletedTasks=0;
        
    }
   /**
    * Method adds a task to the list
    * @param task to be added 
    */
    public void addToList(Task t){
        toDoList.add(t);
    }
   /**
    * Method removes a task from the list
    * @param taskIndex
    */
    public void removeTask(int i){
    	toDoList.remove(i);
    }
   /**
    * Method prints the contents of the to-do list. The list can 
    * be printed with task indices, sorted by completion date
    * or filtered by a specified project
    * @param whether to sort this list by duedate  
    * @param projectName
    */
    public void printList(Boolean sortByDate, String project){
		Interaction.printMessage("---");
		System.out.println(String.format("%1$-30s %2$-10s", " ToDos:", "Complete by:"));
    	Interaction.printMessage("---");
    	if(toDoList.size()>0 && !sortByDate){
    		printByIndex();
    	}
    	else if(toDoList.size()>0 && sortByDate && project.equals("allProjects")){
    		printByDate();
    	}
    	else if (sortByDate && Project.containsProject(project)){
    		filterByProject(project);
    	}
    	else{
    		System.out.println("***** List Empty *****");
    	}
    	Interaction.printMessage("---");
    }
   /**
    * Method prints out all tasks corresponding
    * to their index
    */
    private void printByIndex(){
			for (int i=0; i<toDoList.size(); i++){
    			System.out.println("<"+i+"> " + String.format("%1$-30s %2$-10s",
    				               toDoList.get(i).getTaskName(),    								
    								Formatter.duedateFormatter(toDoList.get(i).getDueDate())));
    		}
    }
   /**
    * Method prints out all non completed tasks
    * in order of when they need to be completed
    */
    private void printByDate(){
			toDoList.stream()
    			    .sorted(new SortByDueDate())
    			    .filter(t->t.getStatus().equals("Not Completed"))
    			    .forEach(t->System.out.println(String.format(
    			    		 "%1$-30s %2$-10s",
    			    	     t.getTaskName(),	 		
    					     Formatter.duedateFormatter(t.getDueDate()))));    	
    }
   /**
    * Method prints out all tasks of a corresponding project
    * @param projectName
    */
    private void filterByProject(String projectName){
    			toDoList.stream()
    				    .sorted(new SortByDueDate())
    			    	.filter(t->t.getprojectName().equals(projectName))
    				    .forEach(t->System.out.println(String.format(
    			    			 "%1$-30s %2$-10s",
    			    		     t.getTaskName(),			
    					    	 Formatter.duedateFormatter(t.getDueDate()))));
    	
    }
   /**
    * Method returns a task object
    * @return Task object 
    * @param taskIndex
    */
    public Task getTask(int i){

    	return toDoList.get(i);
    }
   /**
    * Method returns the size of a list
    * @return size of to-do list
    * @param taskName
    */
    public int getListSize(){

    	return toDoList.size();
    }
   /**
    * Method sees if a task exists in the list
    * @return task index 
    * @param taskName 
    */
    public int findTask(String task2find){
    	int index=-1;
    	for(int i =0; i<toDoList.size(); i++){

    		if(getTask(i).getTaskName().equals(task2find)){
    			return index=i; 
    		}
    	}
    	return index;
    }
   /**
    * Method calculates the amount of tasks
    * not completed
    * @return total tasks not completed
    */
    public long numberOfTasksNotCompleted(){
        return unCompletedTasks=
                                toDoList.stream()
                                        .filter(t->t.getStatus().equals("Not Completed"))
                                        .count();
    }
   /**
    * Method calculates the amount of tasks
    * completed
    * @return total tasks completed
    */    
    public long numberOfTasksCompleted(){
        return completedTasks=
                                toDoList.stream()
                                        .filter(t->t.getStatus().equals("Completed"))
                                        .count();
    }

}
