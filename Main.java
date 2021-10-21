import java.util.ArrayList;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in); // create scanner
    Deck table = new Deck(); // deck players put cards into
    Deck deck = new Deck(); // deck players draw from

    int players = 0; // declare players
    System.out.println("How many players?"); // get user input for players
    players = sc.nextInt();
    Deck[] playerDecks = new Deck[players]; // create deck for each player
    

    // deal 7 cards to each player at random

    System.out.println("-----UNO!-----");

    boolean uno = false;
    while !(uno) {
      for (int i = 0; i <= players; i++) {
        System.out.println("Player " + (i + 1) + " - which card will you play?");
        System.out.println()
        if (playerDecks[i].size()==0) uno = true;
        System.out.print("\033[H\033[2J");
        System.out.flush();
       }
    }
    
  }
}