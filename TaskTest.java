import ToDoList.*;
import junit.framework.*;

public class TaskTest extends TestCase {

	protected Task t= new Task("clean house");	
    
  	public void testTaskName() {
  		String name= t.getTaskName();
   		assertEquals(name, "clean house");
  	}
  	public void testDueDate(){
  		t.setDueDate("22/12/2020");
  		String expectedDueDate =t.getDueDate();
  		assertEquals(expectedDueDate, "22/12/2020");
  	}
  	public void testProjectName(){
  		t.setTask2project("SDA");
  		String expectedProjectName=t.getprojectName();
  		assertEquals(expectedProjectName, "SDA");
  	}
  	public void testStatus(){
  		String expectedStatus=t.getStatus();
  		assertFalse(expectedStatus=="Completed");  		
  	}
}