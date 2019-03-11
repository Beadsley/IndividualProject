import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

/**
 * This class saves and opens files of type .BIN.
 * 
 * The FileReader saves the current todo list to
 * a .BIN file when a specified filePath and
 * fileName are supplied via input through 
 * the terminal. The .BIN file can then be opened
 * when a filePath is supplied.
 *
 * @author Daniel Beadleson
 */

public class FileReader{

   /**
    * Method imports a .BIN file containing an 
    * existing todo list
    * @param filepath 
    * @throws IOException_ClassNotFoundException
    */
    public static Object importFile(String filepath){
	      try{
   	        FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object importedList= ois.readObject(); 
	          ois.close();
	          return importedList;	  	
	      }
	      catch(IOException | ClassNotFoundException e){
      		  return e.getMessage(); 
	      }
    }
   /**
    * Method creates a .BIN file containing the 
    * current toDo list
    * @param filepath of the output directory
    * @param filename of the file to save
    * @throws IOException 
    */
    public static void exportFile(String filepath, String filename, ToDoList list2save){
   	    try{
		        FileOutputStream fos = new FileOutputStream(filepath+filename+".BIN"); //maybe don't export as a text file
      	    ObjectOutputStream oos = new ObjectOutputStream(fos);
      	    oos.writeObject(list2save);
      	    oos.close();
      	    System.out.println("----> File saved :)"); 	
      	}
      	catch(IOException e){
      		  Interaction.printMessage("error");
      		  System.out.println(e.getMessage());
      	}
	}
}