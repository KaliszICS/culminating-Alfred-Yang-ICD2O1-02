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
		ArrayList<String> startingdeck = new ArrayList<>();
		for (int i = 0; i < cardsArray.length; i++){
			startingdeck.add(cardsArray[i]);
		}
		return startingdeck;
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
		int counter = -1;
		int randomCard = random.nextInt(deck.size());
		ArrayList<ArrayList<String>> playerCards = new ArrayList<>();
		String cardInPlay = "";
		ArrayList<String> discardPile = new ArrayList<>();
		//players starting hand
		for (int i = 0; i < players.size(); i++){
			playerCards.add(startSevenCards(players));
		}
		cardInPlay = deck.get(randomCard);
		deck.remove(deck.get(randomCard));
		randomCard = random.nextInt(deck.size());
		while (!(endGame(playerCards, players))){
			int currentPlayer = (counter + 1) % players.size();
			counter++;
			System.out.print("\nCARD IN PLAY: " + cardInPlay + "\n");
			playableCards(currentPlayer, playerCards, cardInPlay);
		}
	}

	public static ArrayList<String> startSevenCards(ArrayList<String> players){
		ArrayList<String> startHand = new ArrayList<>();
			for (int index = 0; index < 7; index++){
				int randomCard = random.nextInt(deck.size());
				startHand.add(deck.get(randomCard));
				deck.remove(deck.get(randomCard));
				randomCard = random.nextInt(deck.size());
			}
		return startHand;
	}

	public static Boolean endGame(ArrayList<ArrayList<String>> playerCards, ArrayList<String> players){
		for (int i = 0; i < players.size(); i++){
			if (playerCards.get(i).size() == 0){
				return true;
			}
		}
		return false;
	}

	public static String playableCards(int currentPlayer, ArrayList<ArrayList<String>> playerCards, String cardInPlay){
		String playerHand = "[0] Draw Card\n";
		String currentCard = "";
		int cardNumberCounter = 1;
		ArrayList<String> currentHand = playerCards.get(currentPlayer);
		for (int i = 0; i < currentHand.size(); i++){
			currentCard = currentHand.get(i);
			if (currentCard.startsWith(inPlayCardSuit(cardInPlay)) || currentCard.substring(currentCard.indexOf("|") + 1, currentCard.length()).equals(inPlayCardValue(cardInPlay)) || currentCard.startsWith("Wild")){
				playerHand += "[" + cardNumberCounter + "] " + currentCard + "\n";
				cardNumberCounter++;
			}
		}
		for (int i = 0; i < currentHand.size(); i++){
			currentCard = currentHand.get(i);
			if (!(currentCard.startsWith(inPlayCardSuit(cardInPlay)) || currentCard.substring(currentCard.indexOf("|") + 1, currentCard.length()).equals(inPlayCardValue(cardInPlay)) || currentCard.startsWith("Wild"))){
				playerHand += currentCard + "\n";
			}
		}
		System.out.println(playerHand);
		input.nextLine();
		return playerHand;
	}

	public static String inPlayCardSuit(String cardInPlay){
		return cardInPlay.substring(0, cardInPlay.indexOf("|"));
	}

	public static String inPlayCardValue(String cardInPlay){
		return cardInPlay.substring(cardInPlay.indexOf("|"), cardInPlay.length());
	}
}


