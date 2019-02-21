import java.util.LinkedList;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ToDoList implements Serializable{

        private LinkedList<String> toDoList;

    public ToDoList(){

        toDoList= new LinkedList<>();

    }
    /*
     * Adds an element to the list
     * @param toDo the element to add to the list
     */

    public void addToList(String toDo){

        toDoList.add(toDo);
    }

    /*
     * prints the contents of the list
     */

    public void printList(){

        toDoList.stream().forEach(System.out::println);
    }

    /*
     * @return true if the element exists in the list
     */

    public boolean getElement(String toDo){
        boolean found =false;
        for(String s: toDoList){

            if (s.equals(toDo)){

                found=true;
            }
        }

        return found;
    }
    /*
     *creates an output file
     *@param filepath of the output directory
     */

    public void outputFile(String filepath) {

    	//Task cleanHouse= new Task("Clean House");	
    	try{
		FileOutputStream fos = new FileOutputStream(filepath+"ToList.txt");
      	ObjectOutputStream oos = new ObjectOutputStream(fos);
      	oos.writeObject(toDoList);
      	oos.close();
      	System.out.println(":) File successfully exported to the directory");
      	System.out.println(filepath);
      	}
      	catch(IOException e){
      		System.out.println(e);
      	}
    	
    }

}
