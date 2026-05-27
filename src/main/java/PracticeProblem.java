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
		String userInput = "";
		while (!(userInput.equals("1") || (userInput.equals("2")))){
		System.out.print("[1] Start Uno.\n[2] Rules.\nPlease enter one of the above: ");
		userInput = input.nextLine();
		if (userInput.equals("1")){
			players();
		} 
		else{
			System.out.print("Invalid Input, please input an option above\nPress enter to continue: ");
			input.nextLine();
		}
		}
	}

	public static String[] Cards(){
		String everyCard = "";	
		for (int i = 0; i < 10; i++){
			everyCard += "Red," + i + " ";
			everyCard += "Blue," + i + " ";
			everyCard += "Yellow," + i + " ";
			everyCard += "Green," + i + " ";
		}
		everyCard += "Red,$ Blue,$ Yellow,$ Green,$ Red,% Blue,% Yellow,% Green,% Red,+2 Blue,+2 Yellow,+2 Green,+2 Wild Wild Wild Wild Wild,+4 Wild,+4 Wild,+4 Wild,+4";
		String[] cards = everyCard.split(" ");
		return cards;
	}

	public static String[] players(){
		String playercount = "";
		System.out.print("Pick a range of players from 2-4: ");
		playercount = input.nextLine();
		while (!(playercount.equals("2") || playercount.equals("3") || playercount.equals("4"))){
			System.out.print("Invalid Input, please put a number from 2-4\nPress enter to continue: ");
			input.nextLine();
			System.out.print("Pick a range of players from 2-4: ");
			playercount = input.nextLine();
		}
		int numberOfPlayers = Integer.parseInt(playercount);
		String[] playersNames = new String[numberOfPlayers];

		for (int i = 0; i < numberOfPlayers; i++){
			System.out.println("Select a player name for player " + playercount + ": ");
			playersNames [i] = input.nextLine();
		}
		return playersNames;
	}

}
