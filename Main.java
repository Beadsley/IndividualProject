public class Main{

	public static void main(String[] args) {

		//Interaction.getInput(); //methods in the Interaction class have to be static in order to work
		//Interaction I1= new Interaction();
		//I1.getInput();

		//Task makeBed=new Task("Make bed");
		//System.out.println(makeBed.lifeTime());
		
		//System.out.println(makeBed.getCreationDate());

		createFile("/Users/beadsley/Dropbox/IP/Bin/sddsd/");

	}

	public static void createFile(String filePath) {


			ToDoList.outputFile(filePath);

		

	}
}