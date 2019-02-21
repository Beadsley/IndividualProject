public class Main{

	public static void main(String[] args) throws Exception{

		//Interaction.getInput(); //methods in the Interaction class have to be static in order to work
		//Interaction I1= new Interaction();
		//I1.getInput();

		Task makeBed=new Task("Make bed");
		System.out.println(makeBed.lifeTime());
		makeBed.outputFile();
		//System.out.println(makeBed.getCreationDate());
	}
}