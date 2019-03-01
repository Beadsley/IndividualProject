//import java.util.HashSet;
import java.io.Serializable;
public class Project implements Serializable{
	private String projectName;
	//private HashSet<Project> projects;

	public Project(String projectName){
		this.projectName=projectName;
		//projects=new HashSet<>();
		//addToSet();	
	}
	public void setProjectName(String name){
		projectName=name;
	}

	public String getName(){
		return projectName;
	}

	/*public void addToSet(){
		projects.add(p);
		System.out.println(p);
		

	}*/
}