package knapsack;
/**
 * A Knapsack holds zero or more Items and can provide
 * information about the Items. One can add Items to a Knapsack during
 * its lifetime, reset the Knapsack, create a copy which contains Items
 * only up to a certain weight, and make various queries to the
 * Knapsack. (Thus, the number of Items that will be stored by a
 * Knapsack object is not yet known when the new object is created, and
 * it may grow and shrink over the lifetime of a Knapsack object.)
 *
  @author Nuchem Katz
 * 
 */

import java.util.Arrays;
import java.util.ArrayList;

public class Knapsack {
  /**
   * the variable name for each newly created instacne of Knapsack. An arrayList,
   * named sack that holds a number of Item objects. sack cannot be null after
   * creation but cab be empty, i.e. containng no Items.
   */
  private ArrayList<Item> sack;

  /* Constructors */

  /**
   * Constructs a new Knapsack without any Items. sack cannot be null.
   * 
   */
  public Knapsack() {
    this.sack = new ArrayList<Item>();
  }

  /**
   * Constructs a new Knapsack, by calling the constructer *that takes no
   * parameters, then calls Class method add() *to insert into the Knapsack all
   * non-null Items in items.
   * 
   * @param an array 'items' of Item objects; must not be null; non-null elements
   * are added to the constructed *Knapsack
   * 
   */
  public Knapsack(Item[] items) {
    // sack.addAll(Arrays.asList(items));
    this();
    if (items == null) {
      throw new NullPointerException("item must not be null!");
    }
    for (Item item : items) {
      if (item != null)
        this.sack.add(item);

    }

  }

  
  /* Modifiers */

  /**
   * Adds an Item e to this Knapsack if e is not null; does not modify this
   * Knapsack otherwise. Returns true if e is not null, false otherwise.
   
   **@param e an item to be added to this Knapsack
   * @return true if e is not null, false otherwise
   */
  public boolean add(Item e) {
    if (e != null) {
      this.sack.add(e);
      return true;

    } else
      return false;
  }

  /**
   * Adds all non-null Items in items to this Knapsack.
   *
   * @param items contains the Item objects to be added to this Knapsack; must not
   * be null (but may contain null)
   * @return true if at least one element of items is non-null; false otherwise
   */
  public boolean addAll(Item[] items) {
    boolean found = false;
    if (items == null) {
      throw new NullPointerException("item must not be null!");
    }
    for (Item item : items) {
      if (item != null)
        this.sack.add(item);
      found = true;

    }
    if (found == true)
      return true;
    else
      return false;
  }

  /**
   * Resets this Knapsack to a Knapsack that contains 0 Items.
   */
  public void reset() {
    int counter = (this.sack.size() - 1);
    for (int i = counter; i >= 0; i--) {
      this.sack.remove(i);
    }
  }

  /**
   * Removes certain Items from this Knapsack. Exactly those Items are kept whose
   * weight in grammes is less than or equal to the specified maximum weight in
   * grammes. Checks that by utilizing the getWeightInGRammes()  method provided in Item.java
   *
   * @param maxItemWeightInGrammes the maximum weight in grammes for the Items that are kept
   */
  public void keepOnlyItemsWith(int maxItemWeightInGrammes) {
    int counter = (this.sack.size() - 1);
    for (int i = counter; i >= 0; i--) {
      if (this.sack.get(i).getWeightInGrammes() > maxItemWeightInGrammes)
        this.sack.remove(i);
    }
  }

  /* Accessors */

  /**
   * Returns the number of non-null Items in this Knapsack.
   * * @return the number of non-null Items in this Knapsack
   */
  public int numberOfItems() {
    int n = 0;
    for (int i = 0; i < sack.size(); i++) {
      if (sack.get(i) != null)
        n++;
    }
    return n;
  }

  /**
   * Returns the total weight of the Items in this Knapsack. Utilizes the getWeightInGrammes() method provided in Item.java.
   *
   * @return the total weight of the Items in this Knapsack.
   */
  public int totalWeightInGrammes() {
    int total = 0;
    for (Item item : this.sack) {
      if (item != null)
        total += item.getWeightInGrammes();
    }
    return total;
  }

  /**
   * Returns the average weight in grammes of the (non-null) Items in this
   * Knapsack. In case there is no Item in this Knapsack, -1.0 is returned. Utilizes numberOfItems() totalWeightInGrammes() Class methods
   *
   * For example, if this Knapsack has the contents new Item("Soda", 400) and new
   * Item("Water", 395), the result is: 397.5
   *
   * @return the average length of the Items in this Knapsack, or -1.0 if there is
   * no such Item.
   */
  public double averageWeightInGrammes() {
    double average = 0.0;
    if (this.numberOfItems() > 0) {
      average = ((double) this.totalWeightInGrammes() / this.numberOfItems());
      return average;
    } else
      return -1.0;
  }

  /**
   * Returns the greatest Item in this Knapsack according to the natural ordering
   * of Item given by its compareTo method; null if this Knapsack does not contain
   * any Item objects. Utilizes the compareTo() method provided/overriden in Item.java class. 
   *
   * @return the greatest Item in this Knapsack according to the natural ordering
   *         of Item given by its compareTo method; null if this Knapsack does not
   *         contain any Item objects
   */
  public Item greatestItem() {
    Item greatestItem;
    boolean foundNotNull=false; 
    if (this.sack.size() == 0)
      return null;
    else 
    for (Item item: this.sack){
      if( item!=null)
      foundNotNull=true;
    }
    if (foundNotNull==true)
      {
      greatestItem = this.sack.get(0);
      for (Item item : this.sack) {
        if (greatestItem.compareTo(item) < 1)
          greatestItem = item;}
      
      return greatestItem;}

      else return null; 
    }

  

  /**
   * Returns a new Knapsack with exactly those Items of this Knapsack whose weight
   * is less than or equal to the specified method parameter. Does not modify this
   * Knapsack.
   *
   * @param maxItemWeightInGrammes the maximum weight in grammes for the Items in
   *                               the new Knapsack
   * @return a new Knapsack with exactly those Items of this Knapsack whose weight
   *         is less than or equal to the specified method parameter
   */
  public Knapsack makeNewKnapsackWith(int maxItemWeightInGrammes) {
    Knapsack newSack = new Knapsack();
    for (Item item : this.sack) {
      if (item != null && item.getWeightInGrammes() <= maxItemWeightInGrammes)
        newSack.add(item);
    }
    return newSack;
  }

  /**
   * Returns a string representation of this Knapsack. The string representation
   * consists of a list of the Knapsack's contents, enclosed in square brackets
   * ("[]"). Adjacent Items are separated by the characters ", " (comma and
   * space). Items are converted to strings as by their toString() method. The
   * representation does not mention any null references.
   *
   * So for Item i1 = new Item("Pen", 15); Item i2 = new Item("Letter", 20); Item i3 =
   * null; Item[] items = { i1, i2, i3, i1 }; Knapsack k = new Knapsack(items);
   *
   * the call k.toString() will return one of the three following Strings:
   *
   * "[(Pen, 15), (Pen, 15), (Letter, 20)]" "[(Pen, 15), (Letter, 20), (Pen, 15)]"
   * "[(Letter, 20), (Pen, 15), (Pen, 15)]"
   *
   * @return a String representation of this Knapsack
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {

    return "" + this.sack;
  }

  /* class methods */

  /**
   * Class method to return a Knapsack with the highest total weight from an array
   * of Knapsacks. If we have an array with a Knapsack of 3000 grammes and a
   * Knapsack with 4000 grammes, the Knapsack with 4000 grammes is returned.
   *
   * Entries of the array may be null, and your method should work also in the
   * presence of such entries. So if in the above example we had an additional
   * third array entry null, the result would be exactly the same.
   *
   * If there are several Knapsacks with the same weight, it is up to the method
   * implementation to choose one of them as the result (i.e., the choice is
   * implementation-specific, and method users should not rely on any particular
   * behaviour).
   *
   * @param knapsacks must not be null, but may contain null
   * 
   * @return one of the Knapsacks with the highest total weight among all
   * Knapsacks in the parameter array; must not be null; null if there is no non-null
   * reference in knapsacks
   */
  public static Knapsack heaviestKnapsack(Knapsack[] knapsacks) {
    
    int foundNotNull = 0;
    Knapsack greatestKnapsack;
    if (knapsacks == null) 
      {throw new NullPointerException("item must not be null!");
    }
  else  if (knapsacks.length == 0)
      return null;
    else {
      greatestKnapsack = knapsacks[0];
      for (Knapsack knapsack : knapsacks) {
        if (knapsack != null) {
          foundNotNull++;
          if (knapsack.totalWeightInGrammes() > greatestKnapsack.totalWeightInGrammes())
            greatestKnapsack = knapsack;
        }
      }
      if (foundNotNull > 0)
        return greatestKnapsack;
      else
        return null;
    }
  }
}