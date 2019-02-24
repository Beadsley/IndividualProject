import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Interaction {

    private ToDoList currentList;
    private Scanner sc;
    private boolean open;
    private boolean skippingPrompt;
    private LocalDate currentDate;
    private DateTimeFormatter dateFormat;
    private boolean listEmpty;

    public Interaction(){
        sc= new Scanner(System.in);
        open=true;
        listEmpty=true;
        currentDate=LocalDate.now();
        dateFormat = DateTimeFormatter.ofPattern("E d/M/u");
    }
    /*
    * Initial interaction
    * User acan create a new list
    * or open an existing
    */
    public void welcome(){
    	printWelcome(); //loop to see if a file has been open
    	while (currentList==null && open){
    		try{
        		int input=sc.nextInt();
        		switch (input){
        			case 1: currentList=new ToDoList();
        					System.out.println("----> List Created :)");
        					break;
        			case 2: sc.nextLine();
        					System.out.println(">> Enter file path e.g. /Users/");
        					String filepath= sc.nextLine();
        					System.out.println(filepath); //ln can be deleted sen
        					ToDoList.importFile(filepath);
        					break;
        					///importFile;
        			case 999: System.out.println("***** Ciao for now *****");
        					  open=false;
        					  break;

        		}    			
    		}
            catch (/*InputMismatchException*/ Exception e) {
            	System.out.println(e);
    			sc.nextLine();
    		}	
    	}
    	if (open){
    		mainMenu();
    	}  
    }
   /*
    *  main menu
    */
    public void mainMenu(){ 
    	printMainMenu();
        while(open) {
        	skippingPrompt =false;
        	try {
        		//check that the integers inputted are just that of the amount of cases
        		int input=sc.nextInt();
        		switch (input){
                	case 1:	sc.nextLine();
                			addTask();
                			listEmpty=false;
                			break;
                	case 2: sc.nextLine();
                			if (!listEmpty){
                				currentList.printList();
                				break;
                			}
                			skippingPrompt=true;
                			break;
                	case 3: sc.nextLine();
                 			if (!listEmpty){
                				findTask();
                				break;
                			}
                			skippingPrompt=true;                			           			
                			break;
                	case 4: sc.nextLine();
                  			if (!listEmpty){
                				taskInfo();
                				break;
                			}           		
                			skippingPrompt=true;                				
                			break;               	
                	case 5: sc.nextLine();                			
                  			if (!listEmpty){
                				taskEditor();
                				break;
                			}           
                			skippingPrompt=true;                						
                			break;
                	case 9: sc.nextLine();
							if (!listEmpty){
        						System.out.println(">> Enter file path e.g. /Users/");								
								String filepath=sc.nextLine();
								System.out.println(filepath);
                				currentList.exportFile(filepath);								
								System.out.println(">> Enter file name e.g. ToDoList 2018");
								String filename=sc.nextLine();
								System.out.println(filename);
                				break;
                			} 
                			skippingPrompt=true;                						
                			break;
        			case 999:System.out.println("***** Ciao for now *****");
        					open=false;
        					skippingPrompt=true;
                			break;                			
        		}
        		if(!skippingPrompt){
        			System.out.println(">> Choose an (option) from Main Menu");
        		}
        		else if(listEmpty && open){ 
        			System.out.println("***** List Empty *****");
        		}
    		}
            catch (InputMismatchException e) {
    			sc.nextLine();
    		}
		}            
    }
    /*
     * method adds a new task, with a completion date
    */
    private void addTask(){
		System.out.println(">> Enter task e.g clean house");
		String taskName=sc.nextLine().trim();
        	if(taskName.equals("")){
				System.out.println("***** WARNING! No task entered *****");
                System.out.println(">> Type <2> to try again"); 
                skippingPrompt=true;                
                }
			else{
				Task t= new Task(taskName);
            	currentList.addToList(t);
				System.out.println("----> Task Added :)");
				System.out.println(">> Enter a completion date e.g. 02/02/2020");
				boolean dueDateAdded=false;
				while(!dueDateAdded){
					try{
						String dueDate=sc.nextLine().trim();
						boolean dateOK=Formatter.checkDateFormat(dueDate);
						if(!dateOK){
							System.out.println("Date in the past, try again");
						}
						else{
							t.setDueDate(dueDate);
							System.out.println("----> Date Added :)");
							dueDateAdded=true;
						}
					}
					catch(DateTimeParseException e){						
						System.out.println("Must be d/MM/yyyy e.g. 12/02/2050");
					}
				}	
             }					
    }
   /*
    * Sees if the task exists in the todo list
    			//maybe switch this method to the ToDoList class
    */
    private void findTask(){
		System.out.println(">> Enter task to find e.g clean house");            	
		String task2Find=sc.nextLine().trim();
        boolean found=currentList.taskExists(task2Find);
        	if(found){
            	System.out.println("----> <"+task2Find+"> Exists :)");
            }
            else{
               	System.out.println("----> <"+task2Find+"> Doesn't exist :(");
            }
	}
    /*
    * User can delete and add notes to 
    * a task
    */
    public void taskInfo(){
    	printTaskInfoWelcome();
    	currentList.printList();
    	System.out.println(">> Enter <Task NUMBER> e.g 1");        
    	boolean leaveTaskInfo=false;
    	while(!leaveTaskInfo){
   			 try{   		
    		
    			Integer input = sc.nextInt();
    			checkIndex(input);
     			if(input.equals(999)){			//add if statement to check if index is within the bounds
    				leaveTaskInfo=true;
    				printMainMenu();
    			} 
    			else{
    				Task t=currentList.getTask(input);
    				System.out.println("--------------------------------------------------");
    				System.out.println(t.getTaskName());
    				System.out.println("--------------------------------------------------");
    				System.out.println("Created:");
    				System.out.println(t.getDateCreated());
    				System.out.println(t.getTimeCreated());
    				System.out.println(t.getTaskLifeTime());
    				System.out.println();
    				System.out.println("Reminder:");
    				System.out.println(t.timeTillDueDate());
    				if(t.notesAvailable()!=0){
    					System.out.println();
    					System.out.println("Notes:");
    					t.printNotes();
    				}
    				System.out.println("--------------------------------------------------");
    				System.out.println(">> Type <999> for Main Menu");
    				System.out.println(">> OR enter a <task number> ");
    			} 
    		}
        	catch (InputMismatchException | IndexOutOfBoundsException e) {    			
    			sc.nextLine();
    		}   			  			
    	}
    }
   /* verifies if the index is
   	* out of bounds
    * @param task index
    */
    public void taskEditor(){
    	printTaskEditorWelcome();
    	currentList.printList();
    	boolean leaveTaskEditor=false;
    	while(!leaveTaskEditor){
    		try{
    			Integer input = sc.nextInt(); //check if index is in bounds 
    			if (input != 1||input !=9||input !=99){
    				System.out.println("****** WARNING! <"+input+"> not recognised *******");
    				System.out.println(">> Try again :)");

    			}
    			switch(input){
    				case 999: leaveTaskEditor=true;
    						  printMainMenu();
    						  break; 
    				case 1: System.out.println(">> Enter <task NUMBER> e.g 1");
    						input=sc.nextInt();
    						checkIndex(input);
    						Task t=currentList.getTask(input);
    						System.out.println(">> Enter note");
    						sc.nextLine();
    						t.addNote(sc.nextLine());
    						System.out.println("----> Note Added :)");
    						System.out.println(">> Type <999> for main menu");
    						System.out.println(">> OR type an <option> from the Task Editor menu");
    						break;
    				case 9: System.out.println(">> Enter <task NUMBER> e.g 1");
    						input=sc.nextInt();
    						checkIndex(input);
    						currentList.removeTask(input);
    						System.out.println("----> Task Deleted <999>");  
							System.out.println(">> Type <999> for main menu");
    						System.out.println(">> OR type an <option> from the Task Editor menu");  							
    			}
    		}
    		catch(InputMismatchException | IndexOutOfBoundsException e){
    			sc.nextLine();
    		}
    	}
    }
    /*
    * Prints out informtaion about a 
    * specified task
    */    
    public void checkIndex(int index){
    	boolean indexExists;
    	indexExists= index<currentList.getListSize() || index==999;
    	if (!indexExists){
    		System.out.println("**** WARNING! <"+index+"> not in list **** ");
    		System.out.println(">> (999) for Main Menu");
    		System.out.println(">> Or Try Again :)");
    	}
    }
    /*
    * method creates a new list and saves it to a file
    */
    private void saveList(){
    	System.out.println(">> Enter file path e.g. /Users/");
        String filePath = sc.nextLine();
        currentList.exportFile(filePath);
    }
    /*
	* prints out welcoming message and
	* options for a new list or open an existing 
	* file  
    */
	private void printWelcome(){
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("**** Bonjour ****");
		System.out.println(">> " + currentDate.format(dateFormat));
		System.out.println(">> Welcome to NAME");
		System.out.println(">> Choose an option:");
		System.out.println(">> (1) Create new To-do list");
		System.out.println(">> (2) Open an existing To-do list");
		System.out.println(">> (999) To Exit");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
   /*
    *  Prints out the opening message alongside intial instructions
    */
    private void printMainMenu(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    	System.out.println(">> **** Main Menu ****");
    	System.out.println(">> " + currentDate.format(dateFormat));
		System.out.println(">> Choose an option:");    	
        System.out.println(">> (1) Add a task");
        System.out.println(">> (2) Print To-do list");
        System.out.println(">> (3) FIND a task");
        System.out.println(">> (4) **** Task Info ****");
		System.out.println(">> (5) **** Task Editor ****");
		System.out.println(">> (9) Save To-do list");    
        System.out.println(">> (999) To Exit");		    
       	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    /*
    *	prints the opening message to the task information
    */
    private void printTaskInfoWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Information ****");
    	System.out.println(">> Type <999> to EXIT back to the main menu");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    }
    /*
    *	prints the opening message to the task editor
    */
    private void printTaskEditorWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Editor ****");
    	System.out.println(">> Type <999> to EXIT back to the main menu");
    	System.out.println(">> Type <1> to ADD a note to a task");
    	System.out.println(">> Type <9> to DELETE a task");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}