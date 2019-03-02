import java.io.Serializable;

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

	public Project(String projectName){
		this.projectName=projectName;
	}
   /*
	* Method sets the name of the project
	*/
	public void setProjectName(String name){
		projectName=name;
	}
   /*
    * Method retrieves the name of a project
    * @return tasks name
    */
	public String getName(){
		return projectName;
	}

}