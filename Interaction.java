import java.util.Scanner;
import java.util.InputMismatchException;

public class Interaction {

    private ToDoList newList;
    private Scanner sc;
    private boolean skip;
    private boolean curious;

    public Interaction(){
        newList=new ToDoList();
        sc= new Scanner(System.in);
    }

    /*
     *  Creates a scanner to read input from the terminal
     */

    public void getInput(){          
        printWelcome();
        boolean open=true;        
        while(open && sc.hasNext()) {
        	curious=true;
        	skip=false;
        	//sc.reset();
        	//sc.skip("");
            String s = sc.nextLine();
           	
            
            if(s.trim().equals("buy")) {
                System.out.println("***** Ciao for now *****");
                break;
            }
            else if(s.trim().equals("new")){
            	newList();
            }
            else if(s.trim().equals("add")){
            	addTask();
            }
            else if(s.trim().equals("print")){
				newList.printList();
			}
            else if(s.trim().equals("find")){
          		findTask();
            }
            else if(s.trim().equals("info")){
            	getTaskInfo();
            }
            else{
            	curious=false;
            	//sc.reset();            	
        	}
        	if(curious && !skip){
            	System.out.println(">> Anything else i can help you with Madam?");
            	}
            else if (!curious && !skip && !s.isEmpty()){
            	System.out.println("****** WARNING! '"+s+"' not recognised *******");
            }
            
        }
    }
    
    /*
    * method creates a new list and saves it to a file
    */
    private void newList(){
    	System.out.println(">> Enter file path e.g. /Users/");
        String filePath = sc.nextLine();
        newList.exportFile(filePath);
    }
    /*
     * method adds a new task
    */
    private void addTask(){
		System.out.println(">> Enter task e.g clean house");
		String taskName=sc.nextLine().trim();
        	if(taskName.equals("")){
				System.out.println("***** WARNING! No task entered *****");
                System.out.println(">> Type 'add' to try again");
                skip=true; 
                }
			else{
            	newList.addToList(taskName);
				System.out.println("----> '"+taskName+"' Added :)");
                }
    }
    /*
    * Method sees if the task exists in the todo list
    */
    private void findTask(){
		System.out.println(">> Enter task to find e.g clean house");            	
		String task2Find=sc.nextLine().trim();
        boolean found=newList.taskExists(task2Find);
        	if(found){
            	System.out.println("----> '"+task2Find+"' Exists :)");
            }
            else{
               	System.out.println("----> '"+task2Find+"' Doesn't exist :(");
            }
	}

    /*
        *  prints out the opening message alongside intial instructions

     */
    private void printWelcome(){
        System.out.println("--------------------------------------------------");
    	System.out.println(">> **** Main Menu ****");
        System.out.println(">> Type 'buy' to exit the scanner");
        System.out.println(">> Type 'new'to save file");
        System.out.println(">> Type 'add' followed by the todo to add it to the list");
        System.out.println(">> Type 'print' to print the list");
        System.out.println(">> Type 'find' followed  by the todo to find in the list");
        System.out.println(">> Type 'info' to get info about the task");
        System.out.println("--------------------------------------------------");
    }
    /*
    * Method prints out informtaion about a task
    */

    public void getTaskInfo(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Information ****");
    	System.out.println(">> Type '999' to exit back to the main menu");
    	System.out.println(">> Enter task number e.g 1");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    	newList.printList();
        
    	
    	boolean leaveTaskInfo=false;
    	while(!leaveTaskInfo){
    		if (!sc.hasNextInt() && sc.next()!=""){	//perhaps a seperate method that checks scanner input
    			System.out.println("****** Enter a number ******");
    			sc.nextLine();    			
    		}
    		else{
    			Integer input = sc.nextInt();
     			if(input.equals(999)){			//add if statement to check if index is within the bounds
    				leaveTaskInfo=true;
    				sc.reset();
    				printWelcome();
    			} 
    			else if (input>=newList.getListSize()){
    				System.out.println("****** WARNING! '"+input+"' not in list *******");
    		
    			}
    			else{
    				Task t=newList.getTask(input);
    				System.out.println("--------------------------------------------------");
    				System.out.println(t.getTaskName());
    				System.out.println("--------------------------------------------------");
    				System.out.println("Task created on:");
    				System.out.println(t.getCreationDate());
    				System.out.println(t.getCreationTime());
    				System.out.println(t.lifeTime());
    				System.out.println("--------------------------------------------------");
    				//retreive information about the first element

    			}    			  			
    		}
    	}
    }
}

