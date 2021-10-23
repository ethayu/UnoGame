class Card {
  String type;
  int number;
  public Card (String type) {
    this.type = type;
  }

  public Card (String type, int number) {
    this.type = type;
    this.number = number;
  }

  /**
   * Checks if card a is compa
   * @param a
   * @param b
   * @return
   */
  static public boolean isCompatible(Card a, Card b) {
    if (!a.type.equals(b.type)) return a.number == b.number;
    else return true;
  }
}