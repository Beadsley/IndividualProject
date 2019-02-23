import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Interaction{

    private ToDoList newList;
    private Scanner sc;
    private boolean open;
    private boolean skippingPrompt;
    private LocalDate currentDate;
    private DateTimeFormatter dateFormat;
    private boolean listEmpty;

    public Interaction(){
        newList=new ToDoList();
        sc= new Scanner(System.in);
        open=true;
        listEmpty=true;
        currentDate=LocalDate.now();
        dateFormat = DateTimeFormatter.ofPattern("E d/M/u");
    }

    /*
     *  Creates a scanner to read input from the terminal
     */

    public void getInput(){ 
    	printWelcome();
        while(open) {
        	skippingPrompt =false;
        	try {
        		//check that the integers inputted are just that of the amount of cases
        		int input=sc.nextInt();
        		switch (input){
        			case 999:System.out.println("***** Ciao for now *****");
        					open=false;
        					skippingPrompt=true;
                			break;
                	case 1:	sc.nextLine();
                			addTask();
                			listEmpty=false;
                			break;
                	case 2: sc.nextLine();
                			if (!listEmpty){
                				newList.printList();
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
                				getTaskInfo();
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
        		}
        		if(!skippingPrompt){
        			System.out.println(">> Anything else i can help you with Madam?");
        		}
        		else if(listEmpty){
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
            	newList.addToList(t);
				System.out.println("----> Task Added :)");
				System.out.println(">> Enter a completion date e.g. 02/02/2020");

				boolean dueDateAdded=false;
				while(!dueDateAdded){
					try{
						String dueDate=sc.nextLine().trim();
						boolean dateOK=Task.dateChecker(dueDate);
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
    * Method sees if the task exists in the todo list
    //maybe switch this method to the ToDoList class
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
    	System.out.println(">> " + currentDate.format(dateFormat));
        System.out.println(">> Type <999> to EXIT the scanner");
        System.out.println(">> Type <1> to ADD a task");
        System.out.println(">> Type <2> to PRINT the list");
        System.out.println(">> Type <3> to FIND a task");
        System.out.println(">> Type <4> for TASK INFORMATION");
		System.out.println(">> Type <5> for TASK EDITOR");        
        System.out.println("--------------------------------------------------");
    }
    /*
    *	method that prints the opening message to the task information
    */

    private void printTaskInfoWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Information ****");
    	System.out.println(">> Type <999> to EXIT back to the main menu");
    	System.out.println(">> Enter task NUMBER e.g 1");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    }

    private void printTaskEditorWelcome(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");    	
    	System.out.println(">> **** Task Editor ****");
    	System.out.println(">> Type <999> to EXIT back to the main menu");
    	System.out.println(">> Type <1> to ADD a note to a task");
    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void taskEditor(){
    	printTaskEditorWelcome();
    	newList.printList();
    	boolean leaveTaskEditor=false;
    	while(!leaveTaskEditor){
    		try{
    			Integer input = sc.nextInt(); //check if index is in bounds 

    			switch(input){
    				case 999: leaveTaskEditor=true;
    						  printWelcome();
    						  break; 
    				case 1: System.out.println(">> Enter task NUMBER e.g 1");
    						input=sc.nextInt();
    						checkIndex(input);
    						Task t=newList.getTask(input);
    						System.out.println(">> Enter note");
    						sc.nextLine();
    						t.addNote(sc.nextLine());
    						System.out.println("----> Note Added :)");
    						break;

    				
    			}
   			
    		}
    		catch(InputMismatchException | IndexOutOfBoundsException e){
    			sc.nextLine();
    		}
    	}
    }
    /*
    * Method prints out informtaion about a task
    */

    public void getTaskInfo(){
    	printTaskInfoWelcome();
    	newList.printList();        
    	
    	boolean leaveTaskInfo=false;
    	while(!leaveTaskInfo){

   			 try{   		
    		
    			Integer input = sc.nextInt();
    			checkIndex(input);
     			if(input.equals(999)){			//add if statement to check if index is within the bounds
    				leaveTaskInfo=true;
    				printWelcome();
    			} 
    			
    			else{
    				Task t=newList.getTask(input);
    				System.out.println("--------------------------------------------------");
    				System.out.println(t.getTaskName());
    				System.out.println("--------------------------------------------------");
    				System.out.println("Created:");
    				System.out.println(t.getDateCreated());
    				System.out.println(t.getTimeCreated());
    				System.out.println(t.getTaskLifeTime());
    				System.out.println("Reminder:");
    				System.out.println(t.timeTillDueDate());
    				System.out.println("--------------------------------------------------");
    				System.out.println("Notes:");
    				t.printNotes();
    				//retreive information about the first element
    			} 
    		}
        	catch (InputMismatchException | IndexOutOfBoundsException e) {    			
    			sc.nextLine();
    		}   			  			
    		
    	}
    }

    public void checkIndex(int index){
    	boolean indexExists;
    	indexExists= index<newList.getListSize() || index==999;
    	if (!indexExists){
    		System.out.println("****** WARNING! '"+index+"' not in list *******");
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
 }

