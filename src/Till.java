import java.util.ArrayList;
import java.util.HashMap;

public class Till {
  private static int total;
  private static ArrayList<Basketable> items;
  private static LoyaltyCard loyaltyCard;

  public static int totalAfterDiscounts(ArrayList<Basketable> basket, LoyaltyCard card) {
    total = 0;
    items = basket;
    loyaltyCard = card;

    for (Basketable item : items) {
      total += item.getPrice();
    }

    applyDiscounts();
    return total;
  }

  private static void applyDiscounts() {
    applyBogof();
    apply10PercentOff();
    applyLoyaltyDiscount();
  }

  private static void applyBogof() {
    HashMap<Basketable, Integer> itemCount = new HashMap<>();

    for (Basketable item : items) {
      itemCount.putIfAbsent(item, 0);
      itemCount.replace(item, itemCount.get(item) + 1);
      if (itemCount.get(item) == 2 && item.getBogof()) {
        total -= item.getPrice();
        itemCount.replace(item, 0);
      }
    }
  }

  private static void apply10PercentOff() {
    if (total > 2000) {
      total *= 0.9;
    }
  }

  private static void applyLoyaltyDiscount() {
    if (loyaltyCard != null && loyaltyCard.isCardValid()) {
      total *= 0.98;
    }
  }
}
