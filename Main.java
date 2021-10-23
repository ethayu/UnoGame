import java.util.ArrayList;
import java.util.Scanner;

class Main {

  static Scanner sc = new Scanner(System.in); // create scanner
  public static void main(String[] args) {
    Deck table = new Deck(); // deck players put cards into
    Deck deck = new Deck(); // deck players draw from

    int players = 0; // declare players
    System.out.println("How many players?"); // get user input for players
    players = sc.nextInt();
    Deck[] playerDecks = new Deck[players]; // create deck for each player
    for (int i = 0; i < playerDecks.length; i++) {
      playerDecks[i] = new Deck();
    }
    

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
  static int makeMove(Deck[] playerDecks, Deck table, int i) {

    System.out.println("Which card would you like to draw?");
    Deck.showUser(playerDecks[i]);
    System.out.println("Enter the integer tag for the card you want to play:");
    int cardIndex = sc.nextInt();

    if (cardIndex > playerDecks.length || cardIndex < playerDecks.length) return -1;
    if (!Card.isCompatible(table.deck.get(table.deck.size() - 1), playerDecks[i].deck.get(cardIndex))) return -1;

    Deck.moveCard(sc.nextInt()-1, playerDecks[i], table);
    return cardIndex;
  }
}