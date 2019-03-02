/*
 * Class opens  and saves a todo 
 * list to a .BIN file
 * 
 */
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class FileReader{

   /*
    * imports a file of an existing todo list
    * @param filepath 
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
    /*
    *creates an output file
    *@param filepath of the output directory
    *@pram filename of the file to save
    */
    public static void saveList(String filepath, String filename, ToDoList list2save){
   	try{
		FileOutputStream fos = new FileOutputStream(filepath+filename+".BIN"); //maybe don't export as a text file
      	ObjectOutputStream oos = new ObjectOutputStream(fos);
      	oos.writeObject(list2save);
      	oos.close();
      	System.out.println("----> File saved :)"); 	
      	}
      	catch(IOException e){
      		Interaction.printResponse(4);
      		System.out.println(e.getMessage());
      	}


	}
}