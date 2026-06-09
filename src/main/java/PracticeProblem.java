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
	static ArrayList<String> deck;
	static ArrayList<Integer> score = new ArrayList<>();
	static boolean wildPlusFourSkip = true;
	static boolean plusTwoSkip = true;
	static boolean reverseSkipOption = true;
	static boolean lastActionCard = true;
	static boolean firstCardEffect = true;
	public static void main(String args[]) {
		String userInput = "";
		while (!(userInput.equals("1"))){
			System.out.print("[1] Start Uno.\n[2] Settings.\nPlease enter one of the above: ");
			userInput = input.nextLine();
			if (userInput.trim().equals("1")){
				game(players());
			} 
			if (userInput.trim().equals("2")){
				settings();
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
		//CREATING THE DECK
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
		//CREATING THE AMOUNT OF PLAYERS
		String playercount = "";
		System.out.print("\nPick a range of players from 2-4: ");
		playercount = input.nextLine();
		while (!(playercount.equals("2") || playercount.equals("3") || playercount.equals("4"))){
			System.out.print("Invalid Input, please put a number from 2-4\n");
			System.out.print("Pick a range of players from 2-4: ");
			playercount = input.nextLine();
		}
		//THE PLAYERS NAMES
		int numberOfPlayers = Integer.parseInt(playercount);
		ArrayList<String> playersNames = new ArrayList<>();
		for (int i = 0; i < numberOfPlayers; i++){
			System.out.print("Select a player name for player " + (i+1) + ": ");
			playersNames.add(input.nextLine());
			score.add(0);
		}
		System.out.println("% --> reverse");
		System.out.println("$ --> skip");
		System.out.print("press enter to get into the game: ");
		input.nextLine();
		return playersNames;
	}

	public static void game(ArrayList<String> players){
		deck = startingDeck();
		int currentPlayer = 0;
		String cardInPlay = "";
		ArrayList<String> discardPile = new ArrayList<>();
		ArrayList<ArrayList<String>> playerCards = new ArrayList<>();
		//PLAYERS STARTING HAND
		for (int i = 0; i < players.size(); i++){
			playerCards.add(new ArrayList<>());
		}
		for (int i = 0; i < playerCards.size(); i++) {
			for (int j = 0; j < 7; j++) {
				playerCards = drawCard(i, playerCards, discardPile);
			}
		}
		int randomCard = random.nextInt(deck.size());
		cardInPlay = deck.get(randomCard);
		deck.remove(deck.get(randomCard));
		randomCard = random.nextInt(deck.size());
		//STARTING THE REVERSE
		boolean reverseSkip = false;
		if (players.size() == 2 && reverseSkipOption){
			reverseSkip = true;
		}
		boolean reverse = false;

		if (firstCardEffect){
		reverse = reverse(reverse, cardInPlay, players, reverseSkip);
		currentPlayer = skip(reverse, cardInPlay, currentPlayer, reverseSkip);
		playerCards = drawTwoOrFour((currentPlayer - 1), playerCards, cardInPlay, reverse, players, 2, discardPile);
		}
		System.out.println("\nCARD IN PLAY: " + cardInPlay + "");
		System.out.print("\nIt is " + players.get(currentPlayer) + "'s turn \nPress enter to continue: ");
		input.nextLine();
		while (endGame(playerCards, players) == -1){			
			System.out.print("\nCARD IN PLAY: " + cardInPlay + "\n");
			//ALL OF A SINGLE PLAYERS CARDS
			ArrayList<String> allPlayerCards = playableCards(currentPlayer, playerCards, cardInPlay, true);
			String outputCards = "";
			for (int i = 0; i < allPlayerCards.size(); i++){
				outputCards += allPlayerCards.get(i) + "\n";
			}
			System.out.println(outputCards);

			String playedCard = playOptions(players, currentPlayer, playerCards, cardInPlay, outputCards);

			if (playedCard.equals("0")){
				playerCards = drawCard(currentPlayer, playerCards, discardPile);
				ArrayList<String> currentPlayerCards = playerCards.get(currentPlayer);
				System.out.println("You drew a " + currentPlayerCards.get(currentPlayerCards.size() - 1));
			}
			else {
				if (cardInPlay.endsWith("Wild")){
					discardPile.add("Wild|Card");
				}
				else if (cardInPlay.endsWith("+4")){
					discardPile.add("Wild|+4");
				}
				else{
					discardPile.add(cardInPlay);
				}
			playerCards.get(currentPlayer).remove(playedCard);
			reverse = reverse(reverse, playedCard, players, reverseSkip);
			playerCards = drawTwoOrFour(currentPlayer, playerCards, playedCard, reverse, players, 2, discardPile);
			playedCard = wildCard(playedCard);

			cardInPlay = playedCard;
			currentPlayer = skip(reverse, playedCard, currentPlayer, reverseSkip);

			}
			//SO THE CURRENT PLAYER CAN'T SEE THE NEXT PLAYERS CARDS OR LAST PLAYERS
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Card Amounts: ");

			for (int i = 0; i < players.size(); i++){
				System.out.println(players.get(i) + ": " + playerCards.get(i).size());
			}
			if (endGame(playerCards, players) != -1){
				endMenu(players, endGame(playerCards, players));
			}
			else{
				nextTurnText(reverse, currentPlayer, players);
				if (reverse){
					currentPlayer = Math.abs((currentPlayer - 1) % players.size());;
				}
				else {
					currentPlayer = Math.abs((currentPlayer + 1) % players.size());;
				}
			}
		}
	}

	public static void endMenu(ArrayList<String> players, int victor){
		score.set(victor, score.get(victor) + 1);
		System.out.println("Congratulations " + players.get(victor) + " Won the game");
		for (int i = 0; i < players.size(); i++){
			System.out.println(players.get(i) + ": " + score.get(i));
		}
		while (true){
			System.out.println("[0] End Game?");
			System.out.println("[1] Play again?");
			System.out.println("[2] Settings.");
			System.out.print("Select and option: ");
			String playerInput = input.nextLine();
			if (playerInput.trim().equals("0")){
				return;
			}
			if (playerInput.trim().equals("1")){
				game(players);
				return;
			}
			if (playerInput.trim().equals("2")){
				settings();
			}
			System.out.println("Invalid Input\n");
		}
	}

	public static ArrayList<ArrayList<String>> drawTwoOrFour(int currentPlayer, ArrayList<ArrayList<String>> playerCards, String playedCard, boolean reverse, ArrayList<String> players, int drawAmount, ArrayList<String> discardPile){
		if (!(playedCard.contains("+2") || playedCard.contains("+4"))){
			return playerCards;
		}
		if (reverse){
			if (playedCard.contains("+2")){
				for (int i = 0; i < drawAmount; i++){
				playerCards = drawCard(Math.abs((currentPlayer - 1) % players.size()), playerCards, discardPile);
			}
			}
			else{
				drawAmount = drawAmount + 2;
				for (int i = 0; i < (drawAmount); i++){
				playerCards = drawCard(Math.abs((currentPlayer - 1) % players.size()), playerCards, discardPile);
			}
			}
		}
		else{
			if (playedCard.contains("+2")){
				for (int i = 0; i < drawAmount; i++){
				playerCards = drawCard(Math.abs((currentPlayer + 1) % players.size()), playerCards, discardPile);
			}
			}
			else{
				drawAmount = drawAmount + 2;
				for (int i = 0; i < (drawAmount); i++){
				playerCards = drawCard(Math.abs((currentPlayer + 1) % players.size()), playerCards, discardPile);
			}
		}
	}
	return playerCards;
}

	public static void nextTurnText(boolean reverse, int currentPlayer, ArrayList<String> players){
		if (reverse){
			String nextPlayer = (players.get(Math.abs((currentPlayer - 1) % players.size())));
			System.out.print("\nnext turn: " + nextPlayer + "\ngive the device to " + nextPlayer + ".\nPress enter to continue: ");
			input.nextLine();
		}			
		else {
			String nextPlayer = (players.get(Math.abs((currentPlayer + 1) % players.size())));
			System.out.print("\nnext turn: " + nextPlayer + "\ngive the device to " + nextPlayer + ".\nPress enter to continue: ");
			input.nextLine();
		}
	}

	public static String playOptions(ArrayList<String> players, int currentPlayer, ArrayList<ArrayList<String>> playerCards, String cardInPlay, String outputCards){
		while (true){
			String userInput = "";
			ArrayList<String> playableCards = playableCards(currentPlayer, playerCards, cardInPlay, false);
			System.out.print("Select a playable card: ");
			userInput = input.nextLine();
			if (userInput.equals("0")){
				return "0";
			}
			for (int i = 0; i < (playableCards.size()); i++){
				if (userInput.equals(i+"")){
					return playableCards.get(i).substring(4);
				}
			}
			System.out.println("Invalid Input, Please input a valid option");
			System.out.println(outputCards);
		}
	}

	public static ArrayList<ArrayList<String>> drawCard(int currentPlayer, ArrayList<ArrayList<String>> playerCards, ArrayList<String> discardPile){
		if (deck.size() == 0){
			deck = discardPile;
		}
		int randomCard = random.nextInt(deck.size());
		playerCards.get(currentPlayer).add(deck.get(randomCard));
		deck.remove(deck.get(randomCard));
		return playerCards;
	}

	public static int endGame(ArrayList<ArrayList<String>> playerCards, ArrayList<String> players){
		for (int i = 0; i < players.size(); i++){
			if (playerCards.get(i).size() == 0){
				return i;
			}
		}
		return -1;
	}

	public static ArrayList<String> playableCards(int currentPlayer, ArrayList<ArrayList<String>> playerCards, String cardInPlay, boolean showAllCards){
		ArrayList<String> playerHand = new ArrayList<>();
		playerHand.add("[0] Draw Card");
		String currentCard = "";
		int cardNumberCounter = 1;
		ArrayList<String> currentHand = playerCards.get(currentPlayer);
		for (int i = 0; i < currentHand.size(); i++){
			currentCard = currentHand.get(i);
			if (currentCard.startsWith(inPlayCardSuit(cardInPlay)) || currentCard.substring(currentCard.indexOf("|") + 1).equals(inPlayCardValue(cardInPlay)) || currentCard.startsWith("Wild") || cardInPlay.startsWith("Wild")){
				playerHand.add("[" + cardNumberCounter + "] " + currentCard);
				cardNumberCounter++;
			}
		}
		if (!showAllCards){
			return playerHand;
		}
		for (int i = 0; i < currentHand.size(); i++){
			currentCard = currentHand.get(i);
			if (!(currentCard.startsWith(inPlayCardSuit(cardInPlay)) || currentCard.substring(currentCard.indexOf("|") + 1).equals(inPlayCardValue(cardInPlay)) || currentCard.startsWith("Wild") || cardInPlay.startsWith("Wild"))){
				playerHand.add(currentCard);
			}
		}				
		if (currentHand.size() == 1 && !lastActionCard && !"0123456789".contains(currentCard.substring(currentCard.indexOf("|") + 1))){
			playerHand.clear();
			playerHand.add("[0] Draw Card");
			playerHand.add(currentCard);
		}
		return playerHand;
	}

	public static String inPlayCardSuit(String cardInPlay){
		return cardInPlay.substring(0, cardInPlay.indexOf("|"));
	}

	public static String inPlayCardValue(String cardInPlay){
		return cardInPlay.substring(cardInPlay.indexOf("|") + 1, cardInPlay.length());
	}

	public static String wildCard(String card){
		if (!card.startsWith("Wild")){
			return card;
		}
		String plusFourText = "";
		if (card.contains("+4")){
			plusFourText = "+4";
		}
		while (true){
				System.out.print("Select a colour for the Wild card to be. (R, G, B, Y): ");
				String userInput = input.nextLine();
				if (userInput.toLowerCase().equals("g")){
					return "Green|Wild" + plusFourText;
				}
				if (userInput.toLowerCase().equals("r")){
					return "Red|Wild" + plusFourText;
				}
				if (userInput.toLowerCase().equals("b")){
					return "Blue|Wild" + plusFourText;
				}
				if (userInput.toLowerCase().equals("y")){
					return "Yellow|Wild" + plusFourText;
				}
				System.out.println("Invalid Input, Please select an actual option.");
			}
	}
	public static boolean reverse(boolean reverse, String playedCard, ArrayList<String> players, boolean reverseSkip){
		if (reverseSkip){
			return reverse;
		}
		if (playedCard.contains("%")){
			return !reverse;
		}
		return reverse;
	}

	public static int skip(boolean reverse, String playedCard, int currentPlayer, boolean reverseSkip){
		if (reverseSkip && playedCard.contains("%")){
			return currentPlayer + 1;
		}
		int nextPlayer = currentPlayer;
		if (reverse){
			nextPlayer--;
		}
		else{
			nextPlayer++;
		}
		if (wildPlusFourSkip && playedCard.contains("+4")){
			return (nextPlayer);
		}
		if (plusTwoSkip && playedCard.contains("+2")){
			return (nextPlayer);
		}
		if (!playedCard.contains("$")){
			return currentPlayer;
		}
		else {
			return (nextPlayer);
		}
	}
	public static void settings(){
		while(true){
			System.out.println("\n[0] Exit");
			System.out.println("[1] Plus 4 Wild Skips next players turn: " + wildPlusFourSkip);
			System.out.println("[2] Plus 2 Skips next players turn: " + plusTwoSkip);
			System.out.println("[3] 2 players have the % skip the next players turn: " + reverseSkipOption);
			System.out.println("[4] You can play an action card as your last card: " + lastActionCard);
			System.out.println("[5] If the first card is an action card, do the action: " + firstCardEffect);

			System.out.print("Select and option: ");
			String userInput = input.nextLine();
			if (userInput.trim().equals("0")){
				return;
			}
			else if (userInput.trim().equals("1")){
				wildPlusFourSkip = !wildPlusFourSkip;
			}
			else if (userInput.trim().equals("2")){
				plusTwoSkip = !plusTwoSkip;
			}
			else if (userInput.trim().equals("3")){
				reverseSkipOption = !reverseSkipOption;
			}
			else if (userInput.trim().equals("4")){
				lastActionCard = !lastActionCard;
			}
			else if (userInput.trim().equals("5")){
				firstCardEffect = !firstCardEffect;
			}
			else{
				System.out.println("Invalid Input, input a value below");
			}
		}
	}
}


