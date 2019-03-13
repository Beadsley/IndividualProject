import ToDoList.*;
import junit.framework.*;

public class ToDoListTest extends TestCase{

	protected ToDoList list;
	protected Task task;
	protected Task task2;

	protected void setUp(){
		list= new ToDoList();
		task= new Task("clean house");
		task2= new Task("clean bathroom");
		list.addToList(task);
	}
	public void testGetTask(){
		Task expectedTask=list.getTask(0);
		assertSame(expectedTask, task);
		assertNotSame(expectedTask, task2);
	}
	public void testListSize(){
		int expectedSize=list.getListSize();
		assertEquals(expectedSize, 1);
		assertNotNull(expectedSize);
	}
	public void testFindTask(){
		int expectedIndex=list.findTask("clean house");
		assertEquals(expectedIndex, 0);
		assertNotNull(expectedIndex);	
	}
	public void testNumberOfTasksNotCompleted(){
		long expectedValue=list.numberOfTasksNotCompleted();
		assertTrue(expectedValue== 1);	
		assertNotNull(expectedValue);
	}
	public void testNumberOfTasksCompleted(){
		long expectedValue=list.numberOfTasksCompleted();		
		assertTrue(expectedValue== 0);	
		assertNotNull(expectedValue);
	}
}