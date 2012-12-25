package com.icyrock.j2me.luaj;

import javax.microedition.lcdui.ItemStateListener;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class Form extends Screen {
  javax.microedition.lcdui.Form j2meForm;

  public Form() {
    super();
    methods.set("append", new Append());
    methods.set("set_item_state_listener", new SetItemStateListener());
  }

  public javax.microedition.lcdui.Form getJ2meForm() {
    return j2meForm;
  }

  public void setJ2meForm(javax.microedition.lcdui.Form j2meForm) {
    setJ2meScreen(j2meForm);
    this.j2meForm = j2meForm;
  }

  final class Append extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      argcheck(arg instanceof Item, 1, "Item required");
      Item item = (Item) arg;
      javax.microedition.lcdui.Item j2meItem = item.getJ2meItem();
      int ret = j2meForm.append(j2meItem);
      return valueOf(ret);
    }
  }

  final class SetItemStateListener extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      arg.argcheck(arg instanceof LuaFunction, 1, "LuaFunction required");

      final LuaFunction callback = (LuaFunction) arg;

      j2meForm.setItemStateListener(new ItemStateListener() {
        public void itemStateChanged(javax.microedition.lcdui.Item j2meItem) {
          Item item;
          if (j2meItem instanceof javax.microedition.lcdui.ChoiceGroup) {
            item = new ChoiceGroup((javax.microedition.lcdui.ChoiceGroup) j2meItem);
          } else if (j2meItem instanceof javax.microedition.lcdui.StringItem) {
            item = new StringItem((javax.microedition.lcdui.StringItem) j2meItem);
          } else {
            item = new Item(j2meItem);
          }
          callback.call(item);
        }
      });

      return this;
    }
  }
}
