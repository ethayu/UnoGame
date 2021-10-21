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
      if (card.getClass().getName().equals("NormalCard"))
        System.out.println("| Card " + i++ + ": " + card.number + " " + card.color);
    }
  }

  public void makeDeck() {
    for(int i = 1; i < 10; i++) {
      deck.add(new NormalCard("red", i));
      deck.add(new NormalCard("red", i));
      deck.add(new NormalCard("yellow", i));
      deck.add(new NormalCard("yellow", i));
      deck.add(new NormalCard("green", i));
      deck.add(new NormalCard("green", i));
      deck.add(new NormalCard("blue", i));
      deck.add(new NormalCard("blue", i));
    }

    deck.add(new NormalCard("red", 0));
    deck.add(new NormalCard("yellow", 0));
    deck.add(new NormalCard("green", 0));
    deck.add(new NormalCard("blue", 0));

    for(int i = 0; i < 2; i++) {
      deck.add(new PlusTwo("red"));
      deck.add(new PlusTwo("yellow"));
      deck.add(new PlusTwo("green"));
      deck.add(new PlusTwo("blue"));
      deck.add(new Reverse("red"));
      deck.add(new Reverse("yellow"));
      deck.add(new Reverse("green"));
      deck.add(new Reverse("blue"));
      deck.add(new Skip("red"));
      deck.add(new Skip("yellow"));
      deck.add(new Skip("green"));
      deck.add(new Skip("blue"));
    }

    for(int i = 0; i < 4; i++) {
      deck.add(new WildCard());
      deck.add(new WildCardPlusFour());
    }
  }
}