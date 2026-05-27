/**
        * File: Culminating
        * Author: Alfred Yang
        * Date Created: May 27, 2026
        * Date Last Modified: June ????????????????????????????????????/, 2026
        */
import java.util.ArrayList;
import java.util.Scanner;
public class PracticeProblem {
	static Scanner input = new Scanner(System.in); 
	public static void main(String args[]) {
		//Creating Cards
		String everyCard = "";	
		for (int i = 0; i < 10; i++){
			everyCard += "Red," + i + " ";
			everyCard += "Blue," + i + " ";
			everyCard += "Yellow," + i + " ";
			everyCard += "Green," + i + " ";
		}
		everyCard += "Red,$ Blue,$ Yellow,$ Green,$ Red,% Blue,% Yellow,% Green,% Red,+2 Blue,+2 Yellow,+2 Green,+2 Wild Wild Wild Wild Wild,+4 Wild,+4 Wild,+4 Wild,+4";
		String[] cards = everyCard.split(" ");

		System.out.println("[1] Start Uno.\n[2] Rules.\nPlease enter one of the above: ");
		String userInput = input.nextLine();
		if (userInput.equals("1")){
			
		} 
	}
}
