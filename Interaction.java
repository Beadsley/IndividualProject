/* 
 * Class creates the user interaction.
 * Enabling the user to create or open 
 * an existing todo list. Tasks can be 
 * created and attributes can be assigned
 * to the task
 */
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Interaction {

    private ToDoList currentList;
    private Scanner sc;
    private boolean open;
    private boolean skippingPrompt;
    private LocalDate currentDate;
    private DateTimeFormatter dateFormat;
    private boolean listEmpty;
    private boolean sorted;

    public Interaction(){
        sc= new Scanner(System.in);
        open=true;
        listEmpty=true;
        currentDate=LocalDate.now();
        dateFormat = DateTimeFormatter.ofPattern("E d/M/u");
        sorted = true;
    }
    /*
    * welcome page where 
    * User can create a new list
    * or open an existing list
    */
    public void welcome(){
    	printWelcome(); //loop to see if a file has been open
    	printResponse(1);
    	while (currentList==null && open){
    		skippingPrompt =false;
    		try{
        		int input=sc.nextInt();
        		switch (input){
        			case 1: sc.nextLine(); // 1) create new list
        					currentList=new ToDoList();
        					System.out.println("----> List Created :)");
        					skippingPrompt=true;
        					break;
        			case 2: sc.nextLine(); // 2) open existing list
        					System.out.println(">> Enter file path e.g./Users/ToList.BIN");
        					String filepath= sc.nextLine();        					
        					Object o=FileReader.importFile(filepath);        					
        					if (o instanceof ToDoList){
        						System.out.println("----> File opened :)"); 
        						currentList=(ToDoList) o;
        						listEmpty=false;
        						skippingPrompt=true;
        					}
        					else if(o instanceof String) {
        						printResponse(4);
        						System.out.println(o);       						
        					}
        					else{
        						System.out.println(">> **** Couldn't open file ****");
        						System.out.println(">> File is of type:");
        						System.out.println(o.getClass()); 
        					}
        					break;
        			case 8: printWelcome();
        					skippingPrompt=true;        					
        					break;
        			case 999: printResponse(2); // 999) Exit menu
        					  open=false;
        					  skippingPrompt=true;
        					  break;

        		} 
        		if(!skippingPrompt){
        			printResponse(1);
        			printResponse(5);
        		}   			
    		}
            catch (InputMismatchException e) {
    			sc.nextLine();
    			printResponse(1);
        		printResponse(5);
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
    	printResponse(1);
        while(open) {
        	skippingPrompt =false;
        	try {
        		//check that the integers inputted are just that of the amount of cases
        		int input=sc.nextInt();
        		switch (input){
                	case 1:	sc.nextLine(); // 1) add a task
                			addTask();
                			listEmpty=false;
                			break;
                	case 2: sc.nextLine(); // 2) view list
                			if (!listEmpty){
                				currentList.printList(sorted);
                				break;
                			}
                			skippingPrompt=true; 
                			break;
                	case 3: sc.nextLine(); // 3) find a task
                 			if (!listEmpty){
                				findTask();
                				break;
                			}
                			skippingPrompt=true;                			           			
                			break;
                	case 4: sc.nextLine(); // 4) view task information   
                  			if (!listEmpty){
                				taskInfo();
                				break;
                			}           		
                			skippingPrompt=true;                				
                			break;               	
                	case 5: sc.nextLine();// 5) edit tasks               			
                  			if (!listEmpty){
                				taskEditor();
                				break;
                			}           
                			skippingPrompt=true;                						
                			break;
  					case 8: printMainMenu(); 
  							printResponse(1);
  							skippingPrompt=true;        					        					
        					break;
                	case 68: sc.nextLine();  // 68) save list to file
							if (!listEmpty){
        						System.out.println(">> Enter file path e.g. /Users/");								
								String filepath=sc.nextLine();
								System.out.println(filepath);
                				
                												
								System.out.println(">> Enter file name e.g. ToDoList2018");
								String filename=sc.nextLine();
								System.out.println(filename);
								FileReader.saveList(filepath, filename, currentList);
                				break;
                			} 
                			skippingPrompt=true;                						
                			break;
        			case 999: printResponse(2); // 999/ Exit terminal application
        					  open=false;
        					  skippingPrompt=true;
                			  break;                			
        		}
        		if(!skippingPrompt){
        			printResponse(1);
        			printResponse(5);
        		}
        		else if(listEmpty && open){ 
        			System.out.println(">> ***** List Empty *****");
        			printResponse(1);
        			printResponse(5);
        		}
    		}
            catch (InputMismatchException e) {
    			sc.nextLine();
    			printResponse(1);
    			printResponse(5);

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
							System.out.println(">> Date in the past");
							System.out.println(">> Enter date again");
						}
						else{
							t.setDueDate(dueDate);
							System.out.println("----> Date Added :)");
							dueDateAdded=true;
						}
					}
					catch(DateTimeParseException e){						
						System.out.println(">> Must be of format d/MM/yyyy");
						System.out.println(">> e.g. 12/02/2050");
						System.out.println(">> Enter date again");
					}
				}	
             }					
    }
   /*
    * Sees if the task exists in the todo list
    			//maybe switch this method to the ToDoList class
    */
    private void findTask(){
		System.out.println(">> Enter the task name e.g clean house");            	
		String task2Find=sc.nextLine().trim();
        boolean found=currentList.taskExists(task2Find);
        	if(found){
            	System.out.println("----> *"+task2Find+"* Exists :)");
            }
            else{
               	System.out.println("----> *"+task2Find+"* Doesn't exist :(");
            }
	}
    /* USE SWITCH CASE
    * User can delete and add notes to 
    * a task
    */
    public void taskInfo(){
    	printTaskInfoWelcome();
    	currentList.printList(!sorted);
    	printResponse(6);        
    	boolean leaveTaskInfo=false;
    	while(!leaveTaskInfo){
   			 try{   		
    		
    			Integer input = sc.nextInt();
    			//checkIndex(input);
     			if(input.equals(999)){			//add if statement to check if index is within the bounds
    				leaveTaskInfo=true;
    				printMainMenu();
    			} 
    			else{
    				Task t=currentList.getTask(input);
    				printResponse(7);
    				System.out.println(t.getTaskName());
    				printResponse(7);
    				System.out.println("Status:");
    				System.out.println(t.getStatus());
    				System.out.println();
    				System.out.println("Project:");
    				System.out.println(t.getProject().getName());
    				System.out.println();
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
    				printResponse(7);
    				printResponse(3);
    				System.out.println(">> Or");
    				printResponse(6);
    			} 
    		}
        	catch (InputMismatchException | IndexOutOfBoundsException e) {    			
    			sc.nextLine();
				int i= currentList.getListSize()-1;
				if (i==0){
					System.out.println(">> Choose task number <"+i+">");

				} 
				else{
					System.out.println(">> Choose task number from <0 --> "+i+">");
				}   			
    			
    			printResponse(3);
    		}   			  			
    	}
    }
   /* verifies if the index is
   	* out of bounds
    * @param task index
    */
    public void taskEditor(){
    	printTaskEditorWelcome();
    	currentList.printList(!sorted);
    	printResponse(1);
    	boolean leaveTaskEditor=false;
    	while(!leaveTaskEditor){
    		skippingPrompt =false;
    		try{
    			Integer input = sc.nextInt(); //check if index is in bounds    			
    			switch(input){
    				case 999: leaveTaskEditor=true; // 999) exits back to main menu
    						  printMainMenu();
    						  skippingPrompt=true;
    						  break; 
    				case 1: printResponse(6); // 1) add not to a task
    						input=sc.nextInt();
    						//checkIndex(input);
    						
    						if (input>currentList.getListSize()-1){
    							System.out.println("**** Task number <"+input+"> not recognised ****");
    							sc.nextLine();
    							break;
    						}
    						else{
     							Task t=currentList.getTask(input);
    							System.out.println(">> Enter note");
    							sc.nextLine();
    							t.addNote(sc.nextLine());
    							System.out.println("----> Note Added :)");
    							break;   							
    						}
    				case 2: printResponse(6); // 2) set task to completed
    						input=sc.nextInt();
    						if (input>currentList.getListSize()-1){
    							System.out.println("**** Task number <"+input+"> not recognised ****");
    							sc.nextLine();
    							break;
    						}
    						else{
    							Task t=currentList.getTask(input);
    							t.set2Completed(true);
    							System.out.println("----> Task Completed");
    							sc.nextLine();
    							break;    							
    						}
    				case 3: printResponse(6); // 3) adit a task name
    						input=sc.nextInt();
    						if (input>currentList.getListSize()-1){
    							System.out.println("**** Task number <"+input+"> not recognised ****");
    							sc.nextLine();
    							break;
    						}
    						else{
     							Task t=currentList.getTask(input);
    							System.out.println(">> Enter new Task name");
    							sc.nextLine();
    							t.setTaskName(sc.nextLine());
    							System.out.println("----> Name changed :)");
    							break;   							
    						}
    				case 4: printResponse(6); //Assign task to a project
    						input=sc.nextInt();
    						if (input>currentList.getListSize()-1){
    							System.out.println("**** Task number <"+input+"> not recognised ****");
    							sc.nextLine();
    							break;
    						}
    						else{
     							Task t=currentList.getTask(input);
    							System.out.println(">> Enter project name");
    							sc.nextLine();
    							t.getProject().setProjectName(sc.nextLine());
    							System.out.println("----> Name changed :)");
    							break;   							
    						}    								    				

  					case 8: printTaskEditorWelcome(); 
    						currentList.printList(!sorted);  					
  							printResponse(1);
  							printResponse(3);
        					skippingPrompt=true;        					
        					break;   						

    				case 9: printResponse(6); // delete a task
    						input=sc.nextInt();
    						checkIndex(input);
    						currentList.removeTask(input);
    						System.out.println("----> Task Deleted :)");
    						break;    							
    			}
    		    if(!skippingPrompt){
        			printResponse(1);
        			printResponse(5);
        			printResponse(3);
        		}	
    		}        	
    		catch(InputMismatchException | IndexOutOfBoundsException e){
    			sc.nextLine();
    			//System.out.println("****** WARNING! (option) not recognised *******");
    			printResponse(1);
        		printResponse(5);
    			printResponse(3);

    		}
    	}
    }
    /* !!!might be able to delete method
    * Prints out informtaion about a 
    * specified task
    */    
    public void checkIndex(int index){
    	boolean indexExists;
    	indexExists= index<currentList.getListSize() || index==999;
    	if (!indexExists){
    		System.out.println("**** WARNING! <"+index+"> not in list **** ");
    		printResponse(3);
    		System.out.println(">> Or Try Again :)");
    	}
    }
    /*
    * prints out a response 
    * @param index representing the repsonse to be 
    * printed
    */    
    }
    public static void printResponse(int i){
    	switch(i){
    		case 1: System.out.println(">> Choose an (option) from the Menu");
    				break;
    		case 2: System.out.println("***** Ciao for now *****");
    				break;
        	case 3: System.out.println(">> (999) for Main Menu");
        			break;
        	case 4: System.out.println("**** Error message: ****");
        			break;
        	case 5: System.out.println(">> (8) View menu again");
        			break;
        	case 6: System.out.println(">> Enter <task NUMBER> e.g 0");
        			break;
        	case 7: System.out.println("--------------------------------------------------");
        			break;
    	}
    }
    /*
	* prints out welcoming message and
	* options for a new list or open an existing 
	* file  
    */
	private void printWelcome(){
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("**** Welcome Menu ****");
		System.out.println(">> " + currentDate.format(dateFormat));
		System.out.println(">> Welcome to Intent");
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
		System.out.println(">> (68) Save To-do list");    
        System.out.println(">> (999) To Exit");		    
       	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    /*
    *	prints the opening message to the task information
    */
    private void printTaskInfoWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Information Menu ****");
    	System.out.println(">> (999) EXIT back to the main menu");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    }
    /*
    *	prints the opening message to the task editor
    */
    private void printTaskEditorWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Editor Menu ****");
    	System.out.println(">> (999) EXIT back to the main menu");
    	System.out.println(">> (1) ADD a note to a task");
    	System.out.println(">> (2) Set a task to completed");
    	System.out.println(">> (3) Edit task name");
    	System.out.println(">> (4) Assign task to a project");
    	System.out.println(">> (9) DELETE a task");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}