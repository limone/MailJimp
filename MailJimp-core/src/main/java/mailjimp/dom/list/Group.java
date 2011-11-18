package mailjimp.dom.list;

import mailjimp.dom.IParsableProperty;

public class Group implements IParsableProperty {
  private String bit;
  private String name;
  private String displayOrder;
  private int numSubscribers;

  /**
   * Default no-args constructor.
   */
  public Group() {
    // empty
  }

  /**
   * Full args constructor.
   * 
   * @param bit
   * @param name
   * @param displayOrder
   * @param numSubscribers
   */
  public Group(String bit, String name, String displayOrder, int numSubscribers) {
    super();
    this.bit = bit;
    this.name = name;
    this.displayOrder = displayOrder;
    this.numSubscribers = numSubscribers;
  }

  public String getBit() {
    return bit;
  }

  public void setBit(String bit) {
    this.bit = bit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(String displayOrder) {
    this.displayOrder = displayOrder;
  }

  public int getNumSubscribers() {
    return numSubscribers;
  }

  public void setNumSubscribers(int numSubscribers) {
    this.numSubscribers = numSubscribers;
  }
}