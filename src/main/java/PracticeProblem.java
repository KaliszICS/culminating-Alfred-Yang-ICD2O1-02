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
		everyCard += "Red|$ Blue|$ Yellow|$ Green|$ Red|% Blue|% Yellow|% Green|% Red|+2 Blue|+2 Yellow|+2 Green|+2 Wild|Card Wild|Card Wild|+4 Wild|+4 ";
		everyCard += "Red|$ Blue|$ Yellow|$ Green|$ Red|% Blue|% Yellow|% Green|% Red|+2 Blue|+2 Yellow|+2 Green|+2 Wild|Card Wild|Card Wild|+4 Wild|+4 ";
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
		String cardInPlay = "";
		ArrayList<String> discardPile = new ArrayList<>();
		ArrayList<ArrayList<String>> playerCards = new ArrayList<>();
		//players starting hand
		for (int i = 0; i < players.size(); i++){
			playerCards.add(startSevenCards(players));
		}
		int randomCard = random.nextInt(deck.size());
		cardInPlay = deck.get(randomCard);
		deck.remove(deck.get(randomCard));
		randomCard = random.nextInt(deck.size());

		System.out.print("\nit's " + players.get(0) + "'s turn \nPress enter to continue: ");
		input.nextLine();

		int reverse = 1;
		while (!(endGame(playerCards, players))){
			int currentPlayer = (counter + 1) % players.size();

			if (reverse % 2 == 0){
				counter--;
			}
			else {
				counter++;
			}
			
			System.out.print("\nCARD IN PLAY: " + cardInPlay + "\n");
			ArrayList<String> allPlayerCards = playableCards(currentPlayer, playerCards, cardInPlay, true);
			String outputCards = "";
			for (int i = 0; i < allPlayerCards.size(); i++){
				outputCards += allPlayerCards.get(i) + "\n";
			}
			System.out.println(outputCards);

			String playedCard = playOptions(players, currentPlayer, playerCards, cardInPlay, outputCards);

			if (playedCard.equals("0")){
				playerCards = drawCard(currentPlayer, playerCards);
			}
			else {
			reverse = reverse(reverse, playedCard);
			currentPlayer = skip(reverse, playedCard, currentPlayer);
			playedCard = wildCard(playedCard);
			playerCards = drawTwoOrFour(currentPlayer, playerCards, playedCard);

			discardPile.add(cardInPlay);
			cardInPlay = playedCard;
			playerCards.get(currentPlayer).remove(playedCard);
			}
			nextTurnText(reverse, currentPlayer, players);
		
		}
	}

	public static ArrayList<ArrayList<String>> drawTwoOrFour(int currentPlayer, ArrayList<ArrayList<String>> playerCards, String playedCard){
		if (!playedCard.substring(playedCard.indexOf("|")).equals("+2")){
			return playerCards;
		}
	}

	public static void nextTurnText(int reverse, int currentPlayer, ArrayList<String> players){
		if (reverse % 2 == 0){
			System.out.print("\nnext turn: " + (players.get((currentPlayer - 1) % players.size())) + "\ngive the device to " + (players.get((currentPlayer - 1) % players.size())) + ".\nPress enter to continue: ");
			input.nextLine();
		}			
		else {
			System.out.print("\nnext turn: " + (players.get((currentPlayer + 1) % players.size())) + "\ngive the device to " + (players.get((currentPlayer + 1) % players.size())) + ".\nPress enter to continue: ");
			input.nextLine();
		}
	}

	public static String playOptions(ArrayList<String> players, int currentPlayer, ArrayList<ArrayList<String>> playerCards, String cardInPlay, String outputCards){
		while (true){
			String userInput = "";
			int i = 0;
			ArrayList<String> playableCards = playableCards(currentPlayer, playerCards, cardInPlay, false);
			System.out.print("Select a playable card: ");
			userInput = input.nextLine();
			if (userInput.equals("0")){
				return "0";
			}
			for (i = 0; i < (playableCards.size()); i++){
				if (userInput.equals(i+1+"")){
					return playableCards.get(i + 1).substring(4);
				}
			}
			System.out.println("Invalid Input, Please input a valid option");
			System.out.println(outputCards);
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

	public static ArrayList<ArrayList<String>> drawCard(int currentPlayer, ArrayList<ArrayList<String>> playerCards){
		int randomCard = random.nextInt(deck.size());
		playerCards.get(currentPlayer).add(deck.get(randomCard));
		deck.remove(deck.get(randomCard));
		return playerCards;
	}

	public static Boolean endGame(ArrayList<ArrayList<String>> playerCards, ArrayList<String> players){
		for (int i = 0; i < players.size(); i++){
			if (playerCards.get(i).size() == 0){
				return true;
			}
		}
		return false;
	}

	public static ArrayList<String> playableCards(int currentPlayer, ArrayList<ArrayList<String>> playerCards, String cardInPlay, boolean showAllCards){
		ArrayList<String> playerHand = new ArrayList<>();
		playerHand.add("[0] Draw Card");
		String currentCard = "";
		int cardNumberCounter = 1;
		ArrayList<String> currentHand = playerCards.get(currentPlayer);
		for (int i = 0; i < currentHand.size(); i++){
			currentCard = currentHand.get(i);
			if (currentCard.startsWith(inPlayCardSuit(cardInPlay)) || currentCard.substring(currentCard.indexOf("|") + 1).equals(inPlayCardValue(cardInPlay)) || currentCard.startsWith("Wild")){
				playerHand.add("[" + cardNumberCounter + "] " + currentCard);
				cardNumberCounter++;
			}
		}
		if (!showAllCards){
			return playerHand;
		}
		for (int i = 0; i < currentHand.size(); i++){
			currentCard = currentHand.get(i);
			if (!(currentCard.startsWith(inPlayCardSuit(cardInPlay)) || currentCard.substring(currentCard.indexOf("|") + 1).equals(inPlayCardValue(cardInPlay)) || currentCard.startsWith("Wild"))){
				playerHand.add(currentCard);
			}
		}
		return playerHand;
	}

	public static String inPlayCardSuit(String cardInPlay){
		//if (cardInPlay.startsWith("Wild")){
		//	return wildCard(cardInPlay);
		//}
		return cardInPlay.substring(0, cardInPlay.indexOf("|"));
	}

	public static String inPlayCardValue(String cardInPlay){
		return cardInPlay.substring(cardInPlay.indexOf("|") + 1, cardInPlay.length());
	}

	public static String wildCard(String card){
		if (!card.startsWith("Wild")){
			return card;
		}
		while (true){
				System.out.print("Select a colour for the Wild card to be. (R, G, B, Y): ");
				String userInput = input.nextLine();
				if (userInput.toLowerCase().equals("g")){
					return "Green|Wild";
				}
				if (userInput.toLowerCase().equals("r")){
					return "Red|Wild";
				}
				if (userInput.toLowerCase().equals("b")){
					return "Blue|Wild";
				}
				if (userInput.toLowerCase().equals("y")){
					return "Yellow|Wild";
				}
				System.out.println("Invalid Input, Please select an actual option.");
			}
	}
	public static int reverse(int reverse, String playedCard){
		if (playedCard.contains("%")){
			return reverse + 1;
		}
		return reverse;
	}

	public static int skip(int reverse, String playedCard, int currentPlayer){
		if (!playedCard.contains("$")){
			return currentPlayer;
		}
		if (reverse % 2 == 0){
			return (currentPlayer - 1);
		}
		else {
			return (currentPlayer + 1);
		}
	}
}


