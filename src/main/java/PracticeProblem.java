/**
        * File: Culminating
        * Author: Alfred Yang
        * Date Created: May 27, 2026
        * Date Last Modified: June ????????????????????????????????????/, 2026
        */

public class PracticeProblem {
	public static void main(String args[]) {

		String everyCard = "";	
		for (int i = 0; i < 10; i++){
			everyCard += "Red," + i + "|";
			everyCard += "Blue," + i + "|";
			everyCard += "Yellow," + i + "|";
			everyCard += "Green," + i + "|";
		}
		everyCard += "Red, x|Blue, x|Yellow, x|Green, x";
		System.out.println(everyCard);
	}

}
