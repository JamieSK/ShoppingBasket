import java.util.ArrayList;

public class Shopper {
  private ShoppingBasket basket;
  private LoyaltyCard loyaltyCard;

  public Shopper(ShoppingBasket basket) {
    this.basket = basket;
  }

  public Shopper(ShoppingBasket basket, LoyaltyCard loyaltyCard) {
    this.basket = basket;
    this.loyaltyCard = loyaltyCard;
  }

  public ArrayList<Basketable> getItems() {
    return basket.getItems();
  }

  public LoyaltyCard getLoyaltyCard() {
    return loyaltyCard;
  }

  public void addToBasket(Basketable item) {
    basket.add(item);
  }

  public void removeFromBasket(Basketable item) {
    basket.remove(item);
  }

  public void emptyBasket() {
    basket.empty();
  }

  public int countItems() {
    return basket.getItemCount();
  }
}
