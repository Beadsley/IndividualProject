import java.util.LinkedList;

public class ToDoList {

        private LinkedList<String> toDoList;

    public ToDoList(){

        toDoList= new LinkedList<>();

    }
    /*
     * Adds an element to the list
     * @param toDo the element to add to the list
     */

    public void addToList(String toDo){

        toDoList.add(toDo);
    }

    /*
     * prints the contents of the list
     */

    public void printList(){

        toDoList.stream().forEach(System.out::println);
    }

    /*
     * @return true if the element exists in the list
     */

    public boolean getElement(String toDo){
        boolean found =false;
        for(String s: toDoList){

            if (s.equals(toDo)){

                found=true;
            }
        }

        return found;
    }

}
