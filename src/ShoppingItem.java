public class ShoppingItem implements Basketable {
  private String name;
  private int price;
  private boolean bogof = false;

  public ShoppingItem(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public boolean getBogof() {
    return bogof;
  }

  public void setBogof(boolean bogof) {
    this.bogof = bogof;
  }
}
