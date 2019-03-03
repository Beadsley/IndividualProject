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

	private String projectName;
    private static HashSet<String> projectNames;	

	public Project(String projectName){
		this.projectName=projectName;
		projectNames= new HashSet<>();  
	}
   /*
	* Method sets the name of the project
	*/
	public void setProjectName(String name){
		projectName=name;
		addProject2Set(name);
	}
   /*
    * Method retrieves the name of a project
    * @return tasks name
    */
	public String getName(){
		return projectName;
	}
   /*
	* Method adds the project name to a set
	* @param name of the project
	*/
	public void addProject2Set(String name){
    	projectNames.add(name);
    } 
   /*
    * Method prints all projectNames
    */
    public static void viewProjects(){
    	for (String s:projectNames){
    		System.out.println(s);
    		
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