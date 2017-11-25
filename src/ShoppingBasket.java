import java.util.ArrayList;

public class ShoppingBasket {
  private ArrayList<Basketable> basket = new ArrayList<>();
  private int itemCount = 0;

  public void add(Basketable item) {
    basket.add(item);
    itemCount++;
  }

  public void remove(Basketable item) {
    if (basket.remove(item)) {
      itemCount--;
    }
  }

  public void empty() {
    basket.clear();
    itemCount = 0;
  }

  public int getItemCount() {
    return itemCount;
  }

  public ArrayList<Basketable> getItems() {
    return basket;
  }
}
