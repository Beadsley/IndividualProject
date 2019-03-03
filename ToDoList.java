import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashSet;

/*
 * Class creates a todo list used to
 * store task objects
 */

public class ToDoList implements Serializable{

    private ArrayList<Task> toDoList;
    private static HashSet<Project> projects;


    public ToDoList(){

        toDoList= new ArrayList<>();
        projects= new HashSet<>();        

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
    public static void addProject2Set(Project p){
    	projects.add(p);
    } 
    public static void viewProjects(){
    	for (Project p:projects){
    		System.out.println(p);
    		System.out.println(p.getName());
    	}
    }

   /*
    * prints the contents of the list
    * @param to sort this list by duedate or 
    * not
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
    	else if (sortByDate && projects.contains(project)){
    		filterByProject(project);
    	}
    	else{
    		System.out.println("***** List Empty *****");
    	}
    	Interaction.printMessage("---");
    }
    private void printByIndex(){
			for (int i=0; i<toDoList.size(); i++){
    			System.out.println("<"+i+"> " + String.format("%1$-30s %2$-10s",
    				               toDoList.get(i).getTaskName(),    								
    								Formatter.duedateFormatter(toDoList.get(i).getDueDate())));
    		}

    }
    private void printByDate(){
			toDoList.stream()
    			    .sorted(new SortByDueDate())
    			    .filter(t->t.getStatus().equals("Not Completed"))
    			    .forEach(t->System.out.println(String.format(
    			    		 "%1$-30s %2$-10s",
    			    	     t.getTaskName(),	 		
    					     Formatter.duedateFormatter(t.getDueDate()))));    	
    }
    private void filterByProject(String projectName){
			Interaction.printMessage("---");
			System.out.println(String.format("%1$-30s %2$-10s", " "+projectName+" ToDos:", "Complete by:"));
    		Interaction.printMessage("---");
    					toDoList.stream()
    				    .sorted(new SortByDueDate())
    			    	.filter(t->t.getProject().getName().equals(projectName))
    				    .forEach(t->System.out.println(String.format(
    			    			 "%1$-30s %2$-10s",
    			    		     t.getTaskName(),			
    					    	 Formatter.duedateFormatter(t.getDueDate()))));
    	
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
    * Method sees if a task exists in the list
    * @return task index 
    * @param name of the task 
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

}
