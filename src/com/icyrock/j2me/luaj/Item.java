package com.icyrock.j2me.luaj;

import javax.microedition.lcdui.ItemCommandListener;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

public class Item extends BaseLuaValue {
  javax.microedition.lcdui.Item j2meItem;

  public Item() {
    super();
    methods.set("set_item_command_listener", new SetItemCommandListener());
    methods.set("get_label", new GetLabel());
    methods.set("set_label", new SetLabel());
  }

  public Item(javax.microedition.lcdui.Item j2meItem) {
    this();
    setJ2meItem(j2meItem);
  }

  public javax.microedition.lcdui.Item getJ2meItem() {
    return j2meItem;
  }

  public void setJ2meItem(javax.microedition.lcdui.Item j2meItem) {
    this.j2meItem = j2meItem;
  }

  final class SetItemCommandListener extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      arg.argcheck(arg instanceof LuaFunction, 1, "LuaFunction required");

      final LuaFunction callback = (LuaFunction) arg;

      j2meItem.setItemCommandListener(new ItemCommandListener() {
        public void commandAction(javax.microedition.lcdui.Command j2meCommand,
          javax.microedition.lcdui.Item j2meItem) {

          Command command = new Command(j2meCommand);
          Item item = new Item(j2meItem);

          callback.call(command, item);
        }
      });

      return this;
    }
  }
  
  final class GetLabel extends ZeroArgFunction {
    public LuaValue call() {
      return valueOf(j2meItem.getLabel());
    }
  }
  
  final class SetLabel extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      String label = arg.checkjstring();
      j2meItem.setLabel(label);
      return this;
    }
  }
}