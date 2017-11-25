import java.util.ArrayList;
import java.util.HashMap;

public class Till {
  public static int total(ArrayList<Basketable> items, LoyaltyCard loyaltyCard) {
    int total = 0;
    for (Basketable item : items) {
      total += item.getPrice();
    }
    return applyDiscounts(total, items, loyaltyCard);
  }

  private static int applyDiscounts(int total, ArrayList<Basketable> items, LoyaltyCard loyaltyCard) {
    total = applyBogof(total, items);
    total = apply10PercentOff(total);
    return applyLoyaltyDiscount(total, loyaltyCard);
  }

  private static int applyBogof(int total, ArrayList<Basketable> items) {
    HashMap<Basketable, Integer> itemCount = new HashMap<>();

    for (Basketable item : items) {
      itemCount.putIfAbsent(item, 0);
      itemCount.replace(item, itemCount.get(item) + 1);
      if (itemCount.get(item) == 2 && item.getBogof()) {
        total -= item.getPrice();
        itemCount.replace(item, 0);
      }
    }
    return total;
  }

  private static int apply10PercentOff(int total) {
    if (total > 2000) {
      total *= 0.9;
    }
    return total;
  }

  private static int applyLoyaltyDiscount(int total, LoyaltyCard loyaltyCard) {
    if (loyaltyCard != null && loyaltyCard.isCardValid()) {
      total *= 0.98;
    }
    return total;
  }
}
