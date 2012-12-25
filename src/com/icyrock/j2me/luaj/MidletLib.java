package com.icyrock.j2me.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class MidletLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable midletTable = new LuaTable();

    midletTable.set("current", new Midlet());

    env.get("lcdui").set("midlet", midletTable);
    return midletTable;
  }
}
