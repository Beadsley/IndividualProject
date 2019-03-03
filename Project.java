import java.io.Serializable;
import java.util.HashSet;

/*
 * Class creates a project object
 * @param name of the project
 * a task may be assigned to a project
 * when a task object is created it is
 * automatically assigned to a 'Not Assigned'
 * project object
 * 
 * 
 */

public class Project implements Serializable{

    private static HashSet<String> projectNames= new HashSet<>(); 	

   /*
	* Method adds the project name to a set
	* @param name of the project
	*/
	public static void addProject2Set(String name){
    	projectNames.add(name);
    } 
   /*
    * Method prints all projectNames
    */
    public static void viewProjects(){
    	
    	if (projectNames.size()!=0){
    		Interaction.printMessage("---");
    		System.out.println("Projects:");
    		Interaction.printMessage("---");
    		for (String s:projectNames){
    			System.out.println(s);    		
    		}
    		Interaction.printMessage("---");
    	}
    	else{
    		System.out.println(">> **** No tasks have been assigned to a project ****");
    	}  		
    }
   /*
	* Method sees if a project name exists
	* @param name of the project
	*/
    public static Boolean containsProject(String name){
    	if (projectNames.contains(name)){
    		return true;
    	}
    	return false;
    }
}