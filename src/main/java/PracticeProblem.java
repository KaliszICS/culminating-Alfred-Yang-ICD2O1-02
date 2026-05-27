import java.util.ArrayList;

/**
        * File: Culminating
        * Author: Alfred Yang
        * Date Created: May 27, 2026
        * Date Last Modified: June ????????????????????????????????????/, 2026
        */
import java.util.ArrayList;
public class PracticeProblem {
	public static void main(String args[]) {

		String everyCard = "";	
		for (int i = 0; i < 10; i++){
			everyCard += "Red," + i + " ";
			everyCard += "Blue," + i + " ";
			everyCard += "Yellow," + i + " ";
			everyCard += "Green," + i + " ";
		}
		everyCard += "Red,$ Blue,$ Yellow,$ Green,$ Red,% Blue,% Yellow,% Green,% Red,+2 Blue,+2 Yellow,+2 Green,+2 Wild Wild Wild Wild Wild,+4 Wild,+4 Wild,+4 Wild,+4";
		String[] cards = everyCard.split(" ");
		for (int i = 0; i < 109; i++){
					System.out.println(cards[i]);

		}
	}

}
