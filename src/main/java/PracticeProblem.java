/**
        * File: Culminating
        * Author: Alfred Yang
        * Date Created: May 27, 2026
        * Date Last Modified: June ????????????????????????????????????/, 2026
        */
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
public class PracticeProblem {
	static Scanner input = new Scanner(System.in); 
	static Random random = new Random();
	static ArrayList<String> deck = startingDeck();
	public static void main(String args[]) {
		String userInput = "";
		while (!(userInput.equals("1"))){
		System.out.print("[1] Start Uno.\n[2] Rules.\nPlease enter one of the above: ");
		userInput = input.nextLine();
		if (userInput.equals("1")){
			game(players());
		} 
		else{
			System.out.print("\nInvalid Input, please input an option above\n");
		}
		}
	}

	public static ArrayList<String> startingDeck(){
		String everyCard = "";	
		for (int i = 0; i < 10; i++){
			everyCard += "Red|" + i + " ";
			everyCard += "Blue|" + i + " ";
			everyCard += "Yellow|" + i + " ";
			everyCard += "Green|" + i + " ";
		}
		for (int i = 1; i < 10; i++){
			everyCard += "Red|" + i + " ";
			everyCard += "Blue|" + i + " ";
			everyCard += "Yellow|" + i + " ";
			everyCard += "Green|" + i + " ";
		}
		everyCard += "Red|$ Blue|$ Yellow|$ Green|$ Red|% Blue|% Yellow|% Green|% Red|+2 Blue|+2 Yellow|+2 Green|+2 Wild Wild Wild|+4 Wild|+4 ";
		everyCard += "Red|$ Blue|$ Yellow|$ Green|$ Red|% Blue|% Yellow|% Green|% Red|+2 Blue|+2 Yellow|+2 Green|+2 Wild Wild Wild|+4 Wild|+4 ";
		String[] cardsArray = everyCard.split(" ");
		for (int i = 0; i < cardsArray.length; i++){
			deck.add(cardsArray[i]);
		}
		return deck;
	}

	public static ArrayList<String> players(){
		String playercount = "";
		System.out.print("\nPick a range of players from 2-4: ");
		playercount = input.nextLine();
		while (!(playercount.equals("2") || playercount.equals("3") || playercount.equals("4"))){
			System.out.print("Invalid Input, please put a number from 2-4\n");
			System.out.print("Pick a range of players from 2-4: ");
			playercount = input.nextLine();
		}
		int numberOfPlayers = Integer.parseInt(playercount);
		ArrayList<String> playersNames = new ArrayList<>();
		for (int i = 0; i < numberOfPlayers; i++){
			System.out.print("Select a player name for player " + (i+1) + ": ");
			playersNames.add(input.nextLine());
		}
		return playersNames;
	}

	public static void game(ArrayList<String> players){
		ArrayList<ArrayList<String>> playerCards = new ArrayList<>();
		//players starting hand
		for (int i = 0; i < players.size(); i++){
			playerCards.add(startSevenCards(players));
		}
		System.out.println(playerCards);
	}

	public static ArrayList<String> startSevenCards(ArrayList<String> players){
		ArrayList<String> startHand = new ArrayList<>();
			for (int index = 0; index < 7; index++){
				int randomCard = random.nextInt(deck.size());
				startHand.add(deck.get(randomCard));
				deck.remove(deck.get(randomCard));
			}
		return startHand;
	}
}


