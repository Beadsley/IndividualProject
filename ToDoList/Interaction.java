package ToDoList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/** 
 * This Class creates the user interaction.
 *
 * The Interaction enables the user to input
 * commands via the terminal. The user can create 
 * or open an existing todo list. Tasks can be 
 * created and attributes can be assigned
 * to the task using the task editor. 
 * Attributes can be viewed in task information. 
 * 
 *  @author Daniel Beadleson
 */

public class Interaction {

    private ToDoList currentList;
    private Scanner sc;
    private boolean open;
    private boolean skippingPrompt;
    private LocalDate currentDate;
    private DateTimeFormatter dateFormat;
    private boolean listEmpty;
    private boolean sortByDate;
    private boolean saved;

    public Interaction(){

        sc= new Scanner(System.in);
        open=true;
        listEmpty=true;
        currentDate=LocalDate.now();
        dateFormat = DateTimeFormatter.ofPattern("E d/M/u");
        sortByDate = true;
        saved = false;
        welcomeMenu();
        
    }
   /**
    * Method creates a welcome page where 
    * the user can decide to create a new list
    * or open an existing list
    * @throws InputMismatchException 
    */
    private void welcomeMenu(){
    	printWelcomeMenu(); 
    	printMessage("chooseOption");
    	while (currentList==null && open){
    		skippingPrompt =false;
    		try{
        		int input=sc.nextInt();
        		switch (input){
        			case 1: 	createNewList();
        							break;

        			case 2: 	openExistingList();
        							break;

        			case 8: 	printWelcomeMenu();
        							skippingPrompt=true;        					
        							break;

        			case 999: 	exit();
        					  		break;
        		} 
        		if(!skippingPrompt){
        			printMessage("chooseOption");
        			printMessage("viewMenuAgain");
        		}   			
    		}
            catch (InputMismatchException e) {
    			sc.nextLine();
    			printMessage("chooseOption");
        		printMessage("viewMenuAgain");
    		}	
    	}
    	if (open){
    		mainMenu();
    	}  
    }
   /**
    * Method to exit the application
    */
    private void exit(){
        printMessage("goodbuy"); 
        open=false;
        skippingPrompt=true;
    }
   /**
    * Method creates a new todo list
    */
    private void createNewList(){
    	sc.nextLine(); 
		currentList=new ToDoList();
        System.out.println("----> List Created :)");
        skippingPrompt=true;
    }
   /**
    * Method opens an existing .BIN file 
    */
    private void openExistingList(){
		sc.nextLine(); 
        System.out.println(">> Enter file path e.g./Users/ToList.BIN");
        String filepath= sc.nextLine().trim();        					
        Object o=FileReader.importFile(filepath);        					
        if (o instanceof ToDoList){
        	System.out.println("----> File opened :)"); 
        	currentList=(ToDoList) o;
            addExistingProjects();
        	listEmpty=false;
        	skippingPrompt=true;
        }
        else if(o instanceof String) {
        	printMessage("error");
        	System.out.println(o);       						
        }
        else{
        	System.out.println(">> **** Couldn't open file ****");
        	System.out.println(">> File is of type:");
        	System.out.println(o.getClass()); 
        }
    }
   /**
    * Method adds existing projects from the .BIN file
    * to the hashset of project names. 
    */
    private void addExistingProjects(){
        for(int i=0; i<currentList.getListSize(); i++){
            String projectName=currentList.getTask(i).getprojectName();
            Project.addProject2Set(projectName);                
        }        
    }
   /**
    * Method creates a main menu where the user can:
    * 1) create a new task
    * 2) view a list of uncompleted tasks
    * 3) view a list of tasks filtered by project
    * 4) find a task in the list
    * 5) view a list of projects 
    * 6) go to task information 
    * 7) go to task editor
    * 8) view main menu again
    * 9) save the current list
    * 999) Exit application
    * @throws InputMismatchException
    */
    private void mainMenu(){ 
    	printMainMenu();
    	printMessage("chooseOption");
        while(open) {
        	skippingPrompt =false;
        	try {
        		int input=sc.nextInt();
        		sc.nextLine();
        		switch (input){
                	case 1:		createTask();
                					listEmpty=false;
                                    saved =false;
                					break;

                	case 2: 	if (!listEmpty){
                					currentList.printList(sortByDate, "allProjects");
                					break;
                				}
                				skippingPrompt=true; 
                				break;

                	case 3:		if (!listEmpty){
                					System.out.println(">> Enter project name ");
                					currentList.printList(sortByDate, sc.nextLine().trim());
                					break;
                				}
                				skippingPrompt=true;                			           			
                				break;			

                	case 4: 	if (!listEmpty){
                					findTask();
                					break;
                				}
                				skippingPrompt=true;                			           			
                				break;

                    case 5:     Project.viewProjects();
                                break;

                	case 6: 	if (!listEmpty){
                					taskInfoMenu();
                					break;
                				}           		
                				skippingPrompt=true;                				
                				break;  

                	case 7: 	if (!listEmpty){
                					taskEditorMenu();
                                    saved=false;
                					break;
                				}           
                				skippingPrompt=true;                						
                				break;

  					case 8: 	printMainMenu(); 
  								printMessage("chooseOption");
  								skippingPrompt=true;        					        					
        						break;

                	case 9:    if (!listEmpty){
									saveList();
                                    saved = true;
                					break;
                				} 
                				skippingPrompt=true;                						
                				break;

        			case 999: 	if (listEmpty || saved){
                                    exit();
                                    break;
                                }
                                else{
                                    System.out.println(">> Would you like to save before leaving??");
                                    System.out.println(">> (1) Yes");
                                    System.out.println(">> (2) No");
                                    int choice=sc.nextInt();
                                    if (choice==2) {
                                        exit();
                                        break;                                     
                                    }
                                    else if(choice==1) {
                                        sc.nextLine();
                                        saveList();
                                        exit();                                       
                                        break;                                    
                                    }
                                    
                                }
               			
        		}
        		if(!skippingPrompt){
        			printMessage("chooseOption");
        			printMessage("viewMenuAgain");
        		}
        		else if(listEmpty && open){ 
        			System.out.println(">> ***** List Empty *****");
        			printMessage("chooseOption");
        			printMessage("viewMenuAgain");
        		}
    		}
            catch (InputMismatchException e) {
    			sc.nextLine();
    			printMessage("chooseOption");
    			printMessage("viewMenuAgain");

    		}
		}            
    }
   /**
    * Method creates a new task, in which a completion
    * date must be assigned to the task in a specific 
    * format.
    * @throws DateTimeParseException
    */
    private void createTask(){
    	
		System.out.println(">> Enter task e.g clean house");
		String taskName=sc.nextLine().trim();
        	if(taskName.equals("")){
				System.out.println(">> **** WARNING! No task entered ****");                
                }
            else if(taskName.length()>29){
            	System.out.println(">> **** Task name too long ****");
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
   /**
    * Method sees if a task exists in the list
    * If so, task information will be printed 
    */
    private void findTask(){
		System.out.println(">> Enter the task name e.g clean house");            	
		String task2Find=sc.nextLine().trim();
		int index=currentList.findTask(task2Find);
		if (index==-1){
			System.out.println("----> *"+task2Find+"* Doesn't exist :(");
		}
		else{
			printTaskInfo(index);
		}
	}
   /**
	* Method saves the current todo list to a .Bin
	* file. Filepath and filename are specified via
    * the terminal
	*/
	private void saveList(){
     	System.out.println(">> Enter file path e.g. /Users/");								
		String filepath=sc.nextLine();         												
		System.out.println(">> Enter file name e.g. ToDoList2018");
		String filename=sc.nextLine();
		FileReader.exportFile(filepath, filename, currentList);		
	}
   /** 
    * Method creates a task information menu. The user 
    * can see a list of all tasks and corresponding indices.
    * The user is able to enter a task index in order 
    * to view information about the task. 
    * @throws InputMismatchException_IndexOutOfBoundsException
    */
    private void taskInfoMenu(){
    	printTaskInfoWelcome();
    	currentList.printList(!sortByDate,"allProjects");
    	printMessage("enterTaskNumber");        
    	boolean leaveTaskInfo=false;
    	while(!leaveTaskInfo){
   			 try{      		
    			Integer input = sc.nextInt();
     			if(input.equals(999)){			
    				leaveTaskInfo=true;
    				printMainMenu();
    			} 
    			else{
    				printTaskInfo(input);
					printMessage("mainMenuPrompt");
			    	System.out.println(">> Or");
			    	printMessage("enterTaskNumber");
    			} 
    		}
        	catch (InputMismatchException | IndexOutOfBoundsException e) {    			
				printListIndices();
				printMessage("mainMenuPrompt");
    		}   			  			
    	}
    }
   /**
    * Method informs the user on which task indices
    * can be inputed into the terminal
    */
    private void printListIndices(){
    			sc.nextLine();
				int listSize= currentList.getListSize()-1;
				if (listSize==0){
					System.out.println(">> **** Choose task number <"+listSize+"> ****");

				} 
				else{
					System.out.println(">> **** Choose task number from <0 --> "+listSize+"> ****");
				}
    }
   /** 
    * Method creates a task editor menu where the user can:
    * 999) Exit back to main menu
   	* 1) add a note to a task
    * 2) assign a task to completed
    * 3) edit a tasks name
    * 4) assign a task to a project
    * 8) view task editor menu again
    * 9) remove a task from the list
    * @throws InputMismatchException_IndexOutOfBoundsException
    */
    private void taskEditorMenu(){
    	printTaskEditorWelcome();
    	currentList.printList(!sortByDate, "allProjects");
    	printMessage("chooseOption");
    	boolean leaveTaskEditor=false;
    	while(!leaveTaskEditor){
    		skippingPrompt =false;
    		try{
    			Integer input = sc.nextInt();   			
    			switch(input){
    				case 999: 	leaveTaskEditor=true; 
    						  	printMainMenu();
    						  	skippingPrompt=true;
    						  	break; 

    				case 1: 	printMessage("enterTaskNumber");   
    							input=sc.nextInt(); 						
    							try {      							
    								addTaskNote(input);
    								break; 
    							}
    							catch (IndexOutOfBoundsException e) {
    								printListIndices();
    								break;
    							}
    							
    				case 2: 	printMessage("enterTaskNumber"); 
    							input=sc.nextInt();
    							try{
    								setTask2Completed(input);	
    								break;     							
    							}
    							catch (IndexOutOfBoundsException e) {
    								printListIndices();
    								break;
    							}    						

    				case 3: printMessage("enterTaskNumber"); 
    						input=sc.nextInt();
    						try{
    							editTaskName(input);
    							break;
    						}
    						catch (IndexOutOfBoundsException e) {
    							printListIndices();
    							break;
    						}      						

    				case 4: printMessage("enterTaskNumber"); 
    						input=sc.nextInt();
    						try{
    							assignTask2Project(input);
    							break;
    						}
    						catch (IndexOutOfBoundsException e) {
    							printListIndices();
    							break;
    						}    									    				

  					case 8: printTaskEditorWelcome(); 
    						currentList.printList(!sortByDate, "allProjects");  					
  							printMessage("chooseOption");
  							printMessage("mainMenuPrompt");
        					skippingPrompt=true;        					
        					break;   						

    				case 9: printMessage("enterTaskNumber"); // delete a task
    						input=sc.nextInt();
    						try{
    							currentList.removeTask(input);
    							System.out.println("----> Task Deleted :)");
    							break;    							
    						}
    						catch (IndexOutOfBoundsException e) {
    							printListIndices();
    							break;
    						} 
   							
    			}
    		    if(!skippingPrompt){
        			printMessage("chooseOption");
        			printMessage("viewMenuAgain");
        			printMessage("mainMenuPrompt");
        		}	
    		}        	
    		catch(InputMismatchException | IndexOutOfBoundsException e){
    			sc.nextLine();
    			System.out.println(">> **** Input not recognised ****");
    			printMessage("chooseOption");
        		printMessage("viewMenuAgain");
    			printMessage("mainMenuPrompt");

    		}
    	}
    }
   /**
    * Method adds a note to a task
    * @param task index
    */
    private void addTaskNote(int index){
    	Task t=currentList.getTask(index);
    	System.out.println(">> Enter note");
    	sc.nextLine();
    	t.addNote(sc.nextLine());
    	System.out.println("----> Note Added :)");
    }
   /**
    * Method sets a tasks status to completed
    * @param task index 
    */
    private void setTask2Completed(int index){
		Task t=currentList.getTask(index);
    	t.set2Completed(true);
    	System.out.println("----> Task Completed");
    	sc.nextLine();
    }
   /**
    * Method edits the name of a task
    * @param task index
    */
    private void editTaskName(int index){
    	Task t=currentList.getTask(index);
    	System.out.println(">> Enter new Task name");
    	sc.nextLine();
    	t.setTaskName(sc.nextLine());
    	System.out.println("----> Name changed :)");    	
    }
   /**
    * Method assigns a task to a project
    * @param task index
    */
    private void assignTask2Project(int index){
		Task t=currentList.getTask(index);
    	System.out.println(">> Enter project name");
    	sc.nextLine();
        String projectName=sc.nextLine();
        if (projectName.equals("allProjects")){
            System.out.println(">> **** 'allProjects' can't be used as a project name ****");
        }
        else{
            t.setTask2project(projectName); 
            System.out.println("----> Assigned to project :)");  
        }   	  							 
    } 
   /**
    * Method prints out a response message
    * @param String representing the response to be printed    
    */ 
    public static void printMessage(String s){
    	switch (s){
    		case "chooseOption":   	System.out.println(">> Choose an (option) from the Menu");
    							   	break;
			case "goodbuy": 	   	System.out.println("***** Ciao for now *****");
							 	   	break;
			case "mainMenuPrompt": 	System.out.println(">> (999) for Main Menu");
        						   	break;
        	case "error":		   	System.out.println("**** Error message: ****");
        							break; 
  	 		case "viewMenuAgain": 	System.out.println(">> (8) View menu again");
        							break; 
        	case "enterTaskNumber": System.out.println(">> Enter <task NUMBER> e.g 0");
        							break;
        	case "---":				System.out.println("--------------------------------------------------");
        							break;				  							   							 
    	}
    }
   /**
	* Method prints the welcome menu  
    */
	private void printWelcomeMenu(){
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("**** Welcome Menu ****");
		System.out.println(">> " + currentDate.format(dateFormat));
		System.out.println(">> Welcome to Your To-Dos");
		System.out.println(">> Choose an option:");
		System.out.println(">> (1) Create new To-do list");
		System.out.println(">> (2) Open an existing To-do list");
		System.out.println(">> (999) To Exit");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
   /**
    * Method prints the main menu
    */
    private void printMainMenu(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    	System.out.println(">> **** Main Menu ****");
    	System.out.println(">> " + currentDate.format(dateFormat));
        System.out.println(">> You have "+ currentList.numberOfTasksNotCompleted() +
                           " task(s) todo and "+currentList.numberOfTasksCompleted()+" task(s) are done!");
		System.out.println(">> Choose an option:");    	
        System.out.println(">> (1) Add a task");
        System.out.println(">> (2) View uncompleted tasks");
        System.out.println(">> (3) View tasks by project");
        System.out.println(">> (4) Find a task");
        System.out.println(">> (5) View projects");
        System.out.println(">> (6) **** Task Info ****");
		System.out.println(">> (7) **** Task Editor ****");
		System.out.println(">> (9) Save To-do list");    
        System.out.println(">> (999) To Exit");		    
       	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
   /**
    * Method prints the task information menu
    */
    private void printTaskInfoWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Information Menu ****");
    	System.out.println(">> (999) EXIT back to the main menu");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    }
   /**
    * Method prints information about a specified task
    * @param index of the task
    */
    private void printTaskInfo(int index){
    	Task t=currentList.getTask(index);
    	printMessage("---");
    	System.out.println(t.getTaskName());
    	printMessage("---");
    	System.out.println("Status:");
    	System.out.println(t.getStatus());
    	System.out.println();
    	System.out.println("Project:");
    	System.out.println(t.getprojectName());
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
		printMessage("---");
    }
   /**
    * Method prints the task editor menu
    */
    private void printTaskEditorWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Editor Menu ****");
    	System.out.println(">> (999) Exit back to the main menu");
    	System.out.println(">> (1) Add a note to a task");
    	System.out.println(">> (2) Set a task to completed");
    	System.out.println(">> (3) Edit task name");
    	System.out.println(">> (4) Assign task to a project");
    	System.out.println(">> (9) Delete a task");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}