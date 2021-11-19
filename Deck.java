import java.util.ArrayList;

class Deck {
  ArrayList<Card> deck;
  
  public Deck() {
    deck = new ArrayList<>();
  }

  /**
   * adds x cards from deck2 to deck1
   * @param x how many cards to add
   * @param deck1 first deck
   * @param deck2 second deck
   */
  static public void addCards(int x, Deck deck1, Deck deck2) {
    for (int i = 0; i < x; i++) {
      int index = (int) (Math.random() * (deck2.deck.size() - 1));
      deck1.deck.add(deck2.deck.get(index));
      deck2.deck.remove(index);
    }
  }

  /**
   * shuffles deck by calling addCards function on the deck
   * @param deck deck to shuffle
   */
  static public void shuffleDeck(Deck deck) {
    addCards(deck.deck.size(), deck, deck);
    int i = -1;
    while (true) {
      if (deck.deck.get(++i).number != 0) {
        moveCard(i, deck, deck);
        break;
      }
    }
  }

  /**
   * move card x in deck1 to deck2
   * @param x card index to move
   * @param deck1 deck card x is in
   * @param deck2 deck card x is moved to
   */
  static public void moveCard(int x, Deck deck1, Deck deck2) {
    deck2.deck.add(deck1.deck.get(x));
    deck1.deck.remove(x);
  }

  static public void showUser(Deck deck) {
    int i = 1;
    for (Card card : deck.deck) {
      System.out.println("| Card " + i++ + ": " + card);
    }
  }

  public Card getTop() {
    return deck.get(deck.size() - 1);
  }

  /**
   * makes a full deck
   */
  public void makeFullDeck() {

    for(int i = 0; i < 2; i++) {
      deck.add(new PlusTwo("Red"));
      deck.add(new PlusTwo("Yellow"));
      deck.add(new PlusTwo("Green"));
      deck.add(new PlusTwo("Blue"));
      deck.add(new Reverse("Red"));
      deck.add(new Reverse("Yellow"));
      deck.add(new Reverse("Green"));
      deck.add(new Reverse("Blue"));
      deck.add(new Skip("Red"));
      deck.add(new Skip("Yellow"));
      deck.add(new Skip("Green"));
      deck.add(new Skip("Blue"));
    }

    for(int i = 0; i < 4; i++) {
      deck.add(new WildCard());
      deck.add(new WildCardPlusFour());
    }
    for(int i = 1; i < 10; i++) {
      deck.add(new NormalCard("Red", i));
      deck.add(new NormalCard("Red", i));
      deck.add(new NormalCard("Yellow", i));
      deck.add(new NormalCard("Yellow", i));
      deck.add(new NormalCard("Green", i));
      deck.add(new NormalCard("Green", i));
      deck.add(new NormalCard("Blue", i));
      deck.add(new NormalCard("Blue", i));
    }

  }
}