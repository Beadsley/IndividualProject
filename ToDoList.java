import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;

public class ToDoList implements Serializable{

        private ArrayList<Task> toDoList;

    public ToDoList(){

        toDoList= new ArrayList<>();

    }
   /*
    * Adds an element to the list
    * @param toDo the element to add to the list
    */
    public void addToList(Task t){
    	//Task taskName = new Task("taskName");
        //toDoList.add(new Task(taskName));
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
    */
    public void printList(){
		System.out.println("--------------------------------------------------");
		System.out.println("ToDos:				Complete by:");
    	System.out.println("--------------------------------------------------");
    	if(toDoList.size()>0){
			for (int i=0; i<toDoList.size(); i++){
    			System.out.println("<"+i+"> "+toDoList.get(i).getTaskName()
    								+"			"+toDoList.get(i).getDueDate());
    		}
    	}
    	else{
    		System.out.println("***** List Empty *****");
    	}
    	System.out.println("--------------------------------------------------");
    }
   /*
    *	@return Task object 
    *	@param index of the task to return
    */
    public Task getTask(int i){

    	return toDoList.get(i);
    }

   /*
    * @return true if the element exists in the list
    * @param names of the task to find
    */
    public int getListSize(){

    	return toDoList.size();
    }
   /* 
    * assesses if task elememt exists
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
   /*
    *creates an output file
    *@param filepath of the output directory
    */
    public void exportFile(String filepath) {
    	//Task cleanHouse= new Task("Clean House");	
    	try{
		FileOutputStream fos = new FileOutputStream(filepath+"ToList.txt"); //maybe don't export as a text file
      	ObjectOutputStream oos = new ObjectOutputStream(fos);
      	oos.writeObject(toDoList);
      	oos.close();
      	System.out.println("----> File saved :)"); 	
      	}
      	catch(IOException e){
      		System.out.println(e); // ln can be deleted
      		System.out.println("**** Error message: ****");
      		System.out.println(e.getMessage());
      	}    	
    }
   /*
    * imports a file
    * @param filepath 
    */
    public static void importFile(String filepath){
	  try{
   	  FileInputStream fis = new FileInputStream(filepath);
      ObjectInputStream ois = new ObjectInputStream(fis);
      Object importedList= ois.readObject(); //ToDoList can also be of type ToDoList
	  ois.close();
	  System.out.println("----> File opened :)");	  	
	  }
	  catch(IOException | ClassNotFoundException e){
			System.err.println(e); // ln can be deleted
     		System.out.println("**** Error message: ****");
      		System.out.println(e.getMessage());
	  }
    }

}
