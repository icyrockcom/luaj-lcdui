package com.icyrock.j2me.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

public class StringItemLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable stringItemTable = new LuaTable();

    stringItemTable.set("create", new Create());

    env.get("lcdui").set("string_item", stringItemTable);
    return stringItemTable;
  }

  final class Create extends TwoArgFunction {
    public LuaValue call(LuaValue arg1, LuaValue arg2) {
      String label = arg1.checkjstring();
      String text = arg2.checkjstring();

      javax.microedition.lcdui.StringItem j2meStringItem = new javax.microedition.lcdui.StringItem(
        label, text);

      StringItem stringItem = new StringItem();
      stringItem.setJ2meStringItem(j2meStringItem);
      return stringItem;
    }
  }
}
