import java.util.Scanner;

class Main {

  static Scanner sc = new Scanner(System.in); // create scanner
  public static void main(String[] args) {
    Deck table = new Deck(); // deck players put cards into
    Deck draw = new Deck();
    draw.makeFullDeck();// deck players draw from

    int players = 0; // declare players
    
    boolean correct = false; // tracks if number of players user entered is within bounds
    while (!correct) {
      System.out.println("How many players? (between 2 and 7)"); // get user input for players
      players = sc.nextInt();
      
      if (players >= 2 && players <= 7)
          correct = true;
      else if (players > 7) System.out.println("That is too many players.");
      else System.out.println("That is not enough players.");
    }
      
    Deck[] playerDecks = new Deck[players]; // create deck for each player
    for (int i = 0; i < playerDecks.length; i++) {
      playerDecks[i] = new Deck();
    }
    

    // deal 7 cards to each player at random
    for (int i = 0; i < players; i++) Deck.addCards(7, playerDecks[i], draw);
    Deck.addCards(1, table, draw);

    int d = 1;

    // loop through every player, display cards
    // allow user to choose which card to play
    // if card is incompatible with last card in table deck, make them play again
    // allow user option to draw card
    while (true) for (int i = 0; i < players; i += d) {
      
      // clear past players cards
      clearScreen();
      
      // player UI
      System.out.println("Table card: |" + table.getTop() + "|");
      System.out.println("Player " + (i + 1) + " - which card will you play?");
      Deck.showUser(playerDecks[i]);
      
      correct = false; // correct tracks whether or not the user chooses a valid move (draw or play)
      while (!correct) {
        
        System.out.println("Would you like to play or draw a card?");
        System.out.println("Type '1' for play and '0' for draw");
        if (playerDecks[i].deck.size() == 1) {
          System.out.println("-----UNO!-----");
          if (!Card.isCompatible(playerDecks[i].deck.get(0), table.getTop()))
            Deck.addCards(1, playerDecks[i], draw);

          else {
            System.out.println("Player " + i + 1 + " wins!");
            break;
          }
        } else if (sc.nextInt() != 0 && sc.nextInt() != 1) {
            System.out.println("You must either draw or play a card. Try again.");

        } else if (sc.nextInt() == 0) {
          // take top card from draw deck and add card to player deck
          Deck.addCards(1, playerDecks[i], draw);
          correct = true;
        } else {
          Deck.moveCard(makeMove(playerDecks, table, i) - 1, playerDecks[i], table);
          if (table.getTop().getClass().getName().equals("PlusTwo")) Deck.addCards(2, playerDecks[i + d], draw);
          else if (table.getTop().getClass().getName().equals("Reverse")) d *= -1;
          else if (table.getTop().getClass().getName().equals("Skip")) i += d;
          else if (table.getTop().getClass().getName().equals("WildCard")) Deck.moveCard(makeWildCardMove(playerDecks, i) - 1, playerDecks[i], table);
          else if (table.getTop().getClass().getName().equals("WildCardPlusFour")){
            Deck.moveCard(makeWildCardMove(playerDecks, i) - 1, playerDecks[i], table);
            Deck.addCards(4, playerDecks[i + d], draw);
          }
          correct = true;
        }
      }
    }
  }
  static int makeMove(Deck[] playerDecks, Deck table, int i) {

    System.out.println("Which card would you like to draw?");
    System.out.println("Enter the integer tag for the card you want to play:");
    int cardIndex = sc.nextInt();

    if (cardIndex > playerDecks.length || cardIndex < playerDecks[i].deck.size()) return makeMove(playerDecks, table, i);
    if (!Card.isCompatible(table.getTop(), playerDecks[i].deck.get(cardIndex))) return makeMove(playerDecks, table, i);
    return cardIndex;
  }

  static int makeWildCardMove(Deck[] playerDecks, int i) {

    System.out.println("Which card would you like to put on the table?");
    System.out.println("Enter the integer tag for the card you want to play:");
    int cardIndex = sc.nextInt();

    if (cardIndex > playerDecks.length || cardIndex < playerDecks[i].deck.size()) return makeWildCardMove(playerDecks, i);
    return cardIndex;
  }
  
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }
}
