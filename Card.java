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
    if (!a.type.equals(b.type)) {
      System.out.println( a.number == b.number);
      return a.number == b.number;
    }
    else return true;
  }

  @Override
  public String toString() {
    return switch (this.getClass().getName()) {
      case "NormalCard" -> this.number + " " + this.type;
      case "PlusTwo" -> this.type + " Plus Two Card";
      case "Reverse" -> this.type + " Reverse Card";
      case "Skip" -> this.type + " Skip Card";
      case "WildCard" -> "Wild Card";
      case "WildCardPlusFour" -> "Wild Card Plus Four Card";
      default -> "";
    };
  }

}