import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {
  private Shopper shopper;
  private Shopper loyalShopper;
  private ShoppingItem cheapItem;
  private ShoppingItem expensiveItem;
  private ShoppingItem bogofItem;

  @Before
  public void before() {
    ShoppingBasket basket = new ShoppingBasket();
    LoyaltyCard loyaltyCard = new LoyaltyCard();
    shopper = new Shopper(basket);
    loyalShopper = new Shopper(basket, loyaltyCard);
    cheapItem = new ShoppingItem("Cheap thing", 200);
    expensiveItem = new ShoppingItem("Expensive thing", 3000);
    bogofItem = new ShoppingItem("Bogof thing", 500, true);
  }

  @Test
  public void canAddItemsToBasket() {
    shopper.addToBasket(cheapItem);
    shopper.addToBasket(expensiveItem);
    assertEquals(2, shopper.countItems());
  }

  @Test
  public void canRemoveItemsFromBasket() {
    shopper.addToBasket(cheapItem);
    shopper.addToBasket(expensiveItem);
    shopper.removeFromBasket(cheapItem);
    assertEquals(1, shopper.countItems());
  }

  @Test
  public void canRemoveOneOfSameItems() {
    shopper.addToBasket(cheapItem);
    shopper.addToBasket(cheapItem);
    shopper.removeFromBasket(cheapItem);
    assertEquals(1, shopper.countItems());
  }

  @Test
  public void canEmptyBasket() {
    shopper.addToBasket(cheapItem);
    shopper.addToBasket(expensiveItem);
    shopper.emptyBasket();
    assertEquals(0, shopper.countItems());
  }

  @Test
  public void canTotalBasket() {
    shopper.addToBasket(cheapItem);
    assertEquals(200, Till.total(shopper.getItems(), shopper.getLoyaltyCard()));
  }

  @Test
  public void canBogof() {
    shopper.addToBasket(bogofItem);
    shopper.addToBasket(bogofItem);
    assertEquals(500, Till.total(shopper.getItems(), shopper.getLoyaltyCard()));
  }

  @Test
  public void bogofIsRightWithThreeOfItem() {
    shopper.addToBasket(bogofItem);
    shopper.addToBasket(bogofItem);
    shopper.addToBasket(bogofItem);
    assertEquals(1000, Till.total(shopper.getItems(), shopper.getLoyaltyCard()));
  }

  @Test
  public void canGet10PercentOffOver20() {
    shopper.addToBasket(expensiveItem);
    assertEquals(2700, Till.total(shopper.getItems(), shopper.getLoyaltyCard()));
  }

  @Test
  public void canGetLoyaltyDiscount() {
    loyalShopper.addToBasket(bogofItem);
    assertEquals(490, Till.total(loyalShopper.getItems(), loyalShopper.getLoyaltyCard()));
  }

  @Test
  public void canBogofInWrongOrder() {
    loyalShopper.addToBasket(bogofItem);
    loyalShopper.addToBasket(cheapItem);
    loyalShopper.addToBasket(bogofItem);
    assertEquals(686, Till.total(loyalShopper.getItems(), loyalShopper.getLoyaltyCard()));
  }

  @Test
  public void cannotRemoveWhatIsNotThere() {
    shopper.addToBasket(expensiveItem);
    shopper.addToBasket(bogofItem);
    shopper.removeFromBasket(cheapItem);
    assertEquals(2, shopper.countItems());
  }
}
