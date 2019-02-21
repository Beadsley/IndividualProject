public class Main{

	private static ToDoList newList;

	public static void main(String[] args) {
		//**Initialise Interaction class
		//Interaction.getInput(); //methods in the Interaction class have to be static in order to work
		Interaction I1= new Interaction();
		I1.getInput();

		//Task makeBed=new Task("Make bed");
		//System.out.println(makeBed.lifeTime());
		
		//System.out.println(makeBed.getCreationDate());

		/*
		newList=new ToDoList();
		newList.addToList("makeBed");
		newList.addToList("takeSelfie");
		newList.printList();
		createFile("/Users/beadsley/Dropbox/IP/Bin/");
		*/

	}


	public static void createFile(String filePath) {


			newList.outputFile(filePath);

		

	}
}