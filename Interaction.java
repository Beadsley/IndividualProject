import java.util.Scanner;

public class Interaction {
    //fields
    private ToDoList newList;


    public Interaction(){
        //initialisation
        newList=new ToDoList();
    }

    /*
     *  Creates a scanner to read input from the terminal
     */

    public void getInput(){
           
        Scanner sc= new Scanner(System.in);
        printWelcome();
        boolean open=true;
        while(open && sc.hasNext()) {

            String s = sc.nextLine();
            //exits the scanner
            if(s.trim().equals("buy")) {
                System.out.println("Ciao for now");
                open = false;
            }
            //creates a new list *No new list created yet but needed when saving
            else if(s.trim().equals("new")){

                System.out.println("New List created :)");
            }
            //add to the new list
            else if(s.substring(0,3).equals("add")){

                 String task=s.substring(3,s.length()).trim();
                 if (task.equals("")){

                     System.out.println("no task entered");
                 }
                 else {
                      newList.addToList(task);
                     System.out.println(task+" added to the list");
                 }

            }

            //print List
            else if(s.equals("print")){

                newList.printList();
            }

            //finds given element in list
            else if(s.substring(0,4).equals("find")){
                String task2Find=s.substring(4,s.length()).trim();

                 System.out.println(newList.getElement(task2Find));

            }
        }


    }

    /*
        *  prints out the opening message alongside intial instructions

     */
    private void printWelcome(){

    	System.out.println(">> Bonjour");
        System.out.println(">> Type 'buy' to exit the scanner");
        System.out.println(">> Type 'new'to create a new list");
        System.out.println(">> Type 'add' followed by the todo to add it to the list");
        System.out.println(">> Type 'print' to print the list");
        System.out.println(">> Type 'find' followed  by the todo to find in the list");
        System.out.println(">>");
        //System.out.println("to add to the todo list");
        //System.out.println("write 'todo' ':' followed by a 'description'");
        //System.out.println("e.g. Clean: clean before mum arrives");
        //System.out.println("write 'print' to print the contents of the list");
        //System.out.println("write 'print todos' to print a list of todos");
        //System.out.println("write 'find' followed by to do to find the description");

    }

}

