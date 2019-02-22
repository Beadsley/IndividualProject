import java.util.Scanner;

public class Interaction {
    //fields
    private ToDoList newList;
    private Scanner sc;

    public Interaction(){
        //initialisation
        newList=new ToDoList();
        sc= new Scanner(System.in);
    }

    /*
     *  Creates a scanner to read input from the terminal
     */

    public void getInput(){          
        //calls method to print welcome messages
        printWelcome();
        boolean open=true;        
        while(open && sc.hasNext()) {
        	boolean curious=true;
        	boolean skip=false;
            String s = sc.nextLine();
            //exits the scanner
            if(s.trim().equals("buy")) {
                System.out.println("***** Ciao for now *****");
                break;
            }
            
            //creates a new list *No new list created yet but needed when saving
            else if(s.trim().equals("new")){

                System.out.println(">> Enter file path e.g. /Users/");
                String filePath = sc.nextLine();
                newList.exportFile(filePath);
            }
            //adds a task to the toDO list
            else if(s.trim().equals("add")){
            	System.out.println(">> Enter task e.g clean house");

                 String taskName=sc.nextLine().trim();
                 if (taskName.equals("")){

                     System.out.println("***** WARNING! No task entered *****");
                     System.out.println(">> Type 'add' to try again");
                     skip=true; 
                 }
                 else {
                      newList.addToList(taskName);
                     System.out.println("----> '"+taskName+"' Added :)");
                 }

            }

            //prints List
            else if(s.trim().equals("print")){
            	System.out.println("--------------------------------------------------");
                newList.printList();
                System.out.println("--------------------------------------------------");
            }

            //finds given element in list
            else if(s.trim().equals("find")){
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
            //retreive info on tasks
            else if(s.trim().equals("info")){
            	getTaskInfo();
            }
            else{
            	curious=false;            	
        	}

        	if (curious && !skip){
            		System.out.println(">> Anything else i can help you with Madam?");
            	}
            else if (!curious){
            	System.out.println("****** WARNING! '"+s+"' not recognised *******");
            }

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
        System.out.println(">>");
        //System.out.println("to add to the todo list");
        //System.out.println("write 'todo' ':' followed by a 'description'");
        //System.out.println("e.g. Clean: clean before mum arrives");
        //System.out.println("write 'print' to print the contents of the list");
        //System.out.println("write 'print todos' to print a list of todos");
        //System.out.println("write 'find' followed by to do to find the description");

    }

    public void getTaskInfo(){
        System.out.println("--------------------------------------------------");    	
    	System.out.println(">> **** Task Information ****");
    	System.out.println(">> Type '999' to exit back to the main menu");
    	System.out.println(">> Enter task number e.g 1");
    	System.out.println("--------------------------------------------------");
    	System.out.println("ToDos");
        System.out.println("--------------------------------------------------");
        newList.printList(); //prints current list of tasks
        System.out.println("--------------------------------------------------");
        
    	
    	boolean leaveTaskInfo=false;
    	while(!leaveTaskInfo){
    		Integer input = sc.nextInt();
    		if(input.equals(999)){			//add if statement to check if index is within the bounds
    			printWelcome();
    			leaveTaskInfo=true;
    		}
    		else{
    			//retreive information about the first element

    		}


    	}
    }

}

