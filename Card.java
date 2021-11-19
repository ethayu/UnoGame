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
   * Checks if card a is compatible with card b
   * @param a card a
   * @param b card b
   * @return true if is compatible; false if not.
   */
  static public boolean isCompatible(Card a, Card b) {
    if (a.type.equals("wild") || a.type.equals("wildfour") || b.type.equals("wild") || b.type.equals("wildfour")) return true;
    if (!a.type.equals(b.type)) {
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