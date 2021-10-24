import java.util.Scanner;

class Main {

  static Scanner sc = new Scanner(System.in); // create scanner
  public static void main(String[] args) {
    Deck table = new Deck(); // deck players put cards into
    Deck draw = new Deck();
    draw.makeFullDeck();// deck players draw from

    int players = 0; // declare players
    System.out.println("How many players? (between 2 and 7)"); // get user input for players
    players = sc.nextInt();

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

      // player UI
      System.out.println("Table card: |" + table.getTop() + "|");
      System.out.println("Player " + (i + 1) + " - which card will you play?");
      Deck.showUser(playerDecks[i]);
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
      } else if (sc.nextInt() == 0) {
        // take top card from draw deck and add card to player deck
        Deck.addCards(1, playerDecks[i], draw);
      } else {
        if (makeMove(playerDecks, table, i)) makeMove(playerDecks, table, i);
        if (table.getTop().getClass().getName().equals("PlusTwo")) Deck.addCards(2, playerDecks[i + d], draw);
        else if (table.getTop().getClass().getName().equals("Reverse")) d *= -1;
        else if (table.getTop().getClass().getName().equals("Skip")) i += d;
        else if (table.getTop().getClass().getName().equals("WildCard")) if (makeWildCardMove(playerDecks, table, i)) makeWildCardMove(playerDecks, table, i);
        else if (table.getTop().getClass().getName().equals("WildCardPlusFour")){
          if (makeWildCardMove(playerDecks, table, i)) makeWildCardMove(playerDecks, table, i);
          Deck.addCards(4, playerDecks[i + d], draw);
        }

      }
    }
  }
  static boolean makeMove(Deck[] playerDecks, Deck table, int i) {

    System.out.println("Which card would you like to put on the table?");
    System.out.println("Enter the integer tag for the card you want to play:");
    int cardIndex = sc.nextInt();

    if (cardIndex > playerDecks.length || cardIndex < playerDecks.length) return true;
    if (!Card.isCompatible(table.getTop(), playerDecks[i].deck.get(cardIndex)))
      return true;

    Deck.moveCard(cardIndex-1, playerDecks[i], table);
    return false;
  }

  static boolean makeWildCardMove(Deck[] playerDecks, Deck table, int i) {

    System.out.println("Which card would you like to draw?");
    System.out.println("Enter the integer tag for the card you want to play:");
    int cardIndex = sc.nextInt();

    if (cardIndex > playerDecks.length || cardIndex < playerDecks.length) return true;
    Deck.moveCard(cardIndex-1, playerDecks[i], table);
    return false;
  }
}