import java.util.Scanner;

/**
 * @author Ethan Yu
 * @author Ram Gorthi
 */
class Main {

    static Scanner sc = new Scanner(System.in); // create scanner
    static int direction = 1; // the direction
    static int playerNumber = 0;
    static int next; // for processMove function

    public static void main(String[] args) throws InterruptedException {
        Deck draw = new Deck(); // deck players put cards into and from
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
        for (playerNumber = 0; playerNumber < players; playerNumber++) Deck.addCards(7, playerDecks[playerNumber], draw);
        //static int d = 1; //the direction
        Deck.shuffleDeck(draw); //shuffle draw deck

        // loop through every player, display cards
        // allow user to choose which card to play
        // if card is incompatible with last card in table deck, make them play again
        // allow user option to draw card
        playerNumber = 0;
        while (true) {
            // clear past players cards
            clearScreen();

            // player UI
            System.out.println("Table card: |" + draw.getTop() + "|");
            System.out.println("Player " + (playerNumber + 1) + " - which card will you play?");
            Deck.showUser(playerDecks[playerNumber]);

            if (playerDecks[playerNumber].deck.size() == 1) {
                System.out.println("-----UNO!-----");
                if (!Card.isCompatible(playerDecks[playerNumber].deck.get(0), draw.getTop())) {
                    Deck.addCards(1, playerDecks[playerNumber], draw);
                    System.out.println("Remaining card doesn't match top card, unlucky! Drawing a card.");
                    Thread.sleep(1000);
                } else {
                    System.out.println("Remaining card can be played: ");
                    System.out.println("Player " + playerNumber + 1 + " wins!");
                    System.exit(0);
                }
            } else {
                next = makeMove(playerDecks, draw, playerNumber);
                if (next != -1) processMove(playerDecks, draw);
            }
            playerNumber = (playerNumber + players + direction) % players;
        }
    }

    /**
     * Handles player moves and cases where special cards are chained by calling makeMove()
     * @param playerDecks array list of all the decks corresponding to number of players
     * @param draw deck that holds cards players draw from and the "table card"
     */
    static void processMove(Deck[] playerDecks, Deck draw) throws InterruptedException {
        if (playerDecks[playerNumber].deck.size() == 1) {
            System.out.println("-----UNO!-----");
            if (!Card.isCompatible(playerDecks[playerNumber].deck.get(0), draw.getTop())) {
                Deck.addCards(1, playerDecks[playerNumber], draw);
                System.out.println("Remaining card doesn't match top card, unlucky! Drawing a card.");
                Thread.sleep(1000);
            } else {
                System.out.println("Remaining card can be played: ");
                System.out.println("Player " + playerNumber + 1 + " wins!");
                System.exit(0);
            }
        }
        Deck.moveCard(next, playerDecks[playerNumber], draw);
        if (draw.getTop().getClass().getName().equals("PlusTwo"))
            Deck.addCards(2, playerDecks[(playerNumber + direction) % playerDecks.length], draw);
        else if (draw.getTop().getClass().getName().equals("Reverse")) direction *= -1;
        else if (draw.getTop().getClass().getName().equals("Skip")) playerNumber += direction;
        else if (draw.getTop().getClass().getName().equals("WildCard")) {
            next = makeWildCardMove(playerDecks, playerNumber);
            processMove(playerDecks, draw);
        } else if (draw.getTop().getClass().getName().equals("WildCardPlusFour")) {
            next = makeWildCardMove(playerDecks, playerNumber);
            Deck.addCards(4, playerDecks[(playerNumber + direction + playerDecks.length) % playerDecks.length], draw);
            processMove(playerDecks, draw);
        }
    }

    /***
     * UI that prompts player for card they want to play, handles player order reversals and makes sure cards
     * played are compatible with the table card.
     * @param playerDecks array list of all the decks corresponding to number of player
     * @param draw deck that holds cards players draw from and the "table card"
     * @param i which number player is making the move
     * @return index of played card
     */
    static int makeMove(Deck[] playerDecks, Deck draw, int i) {

        System.out.println("Which card would you like to play?");
        System.out.println("Enter the integer tag for the card you want to play, or 0 if you want to draw: ");
        int cardIndex = sc.nextInt() - 1;

        if (cardIndex == -1) Deck.addCards(1, playerDecks[i], draw);
        else if (!(cardIndex >= 0 && cardIndex < playerDecks[i].deck.size())) {
            System.out.println("Given number is not within size of deck. Try again.");
            return makeMove(playerDecks, draw, i);
        } else if (!Card.isCompatible(draw.getTop(), playerDecks[i].deck.get(cardIndex))) {
            System.out.println("Card chosen isn't compatible with table card. Try again.");
            return makeMove(playerDecks, draw, i);
        }
        return cardIndex;
    }

    /***
     * Handles wild cards when they are played
     * @param playerDecks array list of all the decks corresponding to number of player
     * @param i which number player is making the move
     * @return index of chosen card
     */
    static int makeWildCardMove(Deck[] playerDecks, int i) {

        System.out.println("You used a wild card. Here is your deck:");
        Deck.showUser(playerDecks[i]);
        System.out.println("Enter the integer tag for the card you want to play: ");
        int cardIndex = sc.nextInt() - 1;

        if (!(cardIndex >= 0 && cardIndex < playerDecks[i].deck.size())) {
            System.out.println("Given number is not within size of deck. Try again.");
            return makeWildCardMove(playerDecks, i);
        }
        return cardIndex;
    }

    /***
     * Clears the screen by printing 50 blank lines so the next player can't see the previous player's cards
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println();
    }
}
