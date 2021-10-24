import java.util.ArrayList;

class Deck {
  ArrayList<Card> deck;
  
  public Deck() {
    deck = new ArrayList<>();
  }

  /**
   * adds x cards from deck2 to deck1
   * @param x
   * @param deck1
   * @param deck2
   */
  static public void addCards(int x, Deck deck1, Deck deck2) {
    for (int i = 0; i < x; i++) {
      int index = (int) (Math.random() * deck2.deck.size());
      deck1.deck.add(deck2.deck.get(index));
      deck2.deck.remove(index);
    }
  }

  /**
   * move card x in deck1 to deck2
   * @param x
   * @param deck1
   * @param deck2
   */
  static public void moveCard(int x, Deck deck1, Deck deck2) {
    deck2.deck.add(deck1.deck.get(x));
    deck1.deck.remove(x);
  }

  static public void showUser(Deck deck) {
    int i = 0;
    for (Card card : deck.deck) {
      System.out.println("| Card " + i++ + ": " + card);
    }
  }

  public Card getTop() {
    return deck.get(deck.size() - 1);
  }

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

    deck.add(new NormalCard("Red", 0));
    deck.add(new NormalCard("Yellow", 0));
    deck.add(new NormalCard("Green", 0));
    deck.add(new NormalCard("Blue", 0));

  }
}