package com.icyrock.j2me.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class ItemLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable itemTable = new LuaTable();

    env.get("lcdui").set("item", itemTable);
    return itemTable;
  }
}
