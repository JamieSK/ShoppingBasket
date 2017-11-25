public class LoyaltyCard {
  private static int topCardNumber = 0;
  private int cardNumber;

  public LoyaltyCard() {
    this.cardNumber = topCardNumber;
    topCardNumber++;
  }

  public boolean isCardValid() {
    return cardNumber < topCardNumber;
  }
}
