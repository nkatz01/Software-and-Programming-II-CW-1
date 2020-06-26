package knapsack;
/**
 * An Item has a name and a positive weight in grammes. Objects of this class
 * are immutable: after an object of class Item has been created, one cannot
 * change the values of its attributes. Thus, the class Item has no mutators.
 *
 * @author Carsten Fuhs
 */
public class Item implements Comparable<Item> {

  /** The name of the Item. Always non-null after object creation. */
  private final String name;

  /** The weight of an Item in grammes. Must be greater than 0. */
  private final int weightInGrammes;

  /**
   * Constructs a new Item with given name and weightGrammes.
   *
   * @param name            the name of the Item; must not be null
   * @param weightInGrammes the weight of the Item in grammes; must be positive
   * @throws NullPointerException     if name is null
   * @throws IllegalArgumentException if weightInGrammes is not positive
   */
  public Item(String name, int weightInGrammes) {
    if (name == null) {
      throw new NullPointerException("name must not be null!");
    }
    if (weightInGrammes <= 0) {
      throw new IllegalArgumentException("Expected weightGrammes > 0, found " + weightInGrammes);
    }
    this.name = name;
    this.weightInGrammes = weightInGrammes;
  }

  /**
   * Returns the name of this Item.
   *
   * @return the name; always non-null
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the weight in grammes of this Item.
   *
   * @return the weightInGrammes; always greater than 0
   */
  public int getWeightInGrammes() {
    return this.weightInGrammes;
  }

  /**
   * Returns a String representation of this Item with a "(", the name of this
   * Item, ", " (comma and space), the weight in grammes and "g)".
   *
   * For example, if i is an Item with name "Pen" and a weight of 20 grammes the
   * call i.toString() would return "(Pen, 20g)".
   *
   * @return a human-readable String representation of this Item
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "(" + this.name + ", " + this.weightInGrammes + "g)";
  }

  /**
   * Returns the hash code of this Item. The hash code of an item is based on its
   * name and its weight in grammes.
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    // this implementation relies on the name attribute being non-null
    return prime * this.name.hashCode() + this.weightInGrammes;
  }

  /**
   * Returns whether this Item and another object are equal. This is the case if
   * the other object is also an Item object with equal name and the same weight
   * in grammes.
   *
   * @param obj another object to be compared with
   * @return whether this Item and obj are equal
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Item)) {
      return false;
    }
    Item other = (Item) obj;
    // this implementation relies on the name attribute being non-null
    return this.name.equals(other.name) && this.weightInGrammes == other.weightInGrammes;
  }

  /**
   * Compares this Item to o. The comparison considers first the weights (higher
   * is greater) and, in case of a tie, the names wrt their natural ordering.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is
   *         less than, equal to, or greater than the specified object.
   *
   * @throws NullPointerException if the specified object is null
   */
  @Override
  public int compareTo(Item o) {
    if (this.weightInGrammes > o.weightInGrammes) {
      return 1;
    }
    if (this.weightInGrammes < o.weightInGrammes) {
      return -1;
    }
    // this implementation relies on the name attribute being non-null
    return this.name.compareTo(o.name);
  }
}