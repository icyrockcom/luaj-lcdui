package com.icyrock.j2me.luaj;

public class StringItem extends Item {
  javax.microedition.lcdui.StringItem j2meStringItem;

  public StringItem() {
    super();
  }

  public StringItem(javax.microedition.lcdui.StringItem j2meStringItem) {
    this();
    setJ2meStringItem(j2meStringItem);
  }

  public javax.microedition.lcdui.StringItem getJ2meStringItem() {
    return j2meStringItem;
  }

  public void setJ2meStringItem(javax.microedition.lcdui.StringItem j2meStringItem) {
    setJ2meItem(j2meStringItem);
    this.j2meStringItem = j2meStringItem;
  }
}
