package com.icyrock.j2me.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class LcduiLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable lcduiTable = new LuaTable();

    env.set("lcdui", lcduiTable);
    env.get("package").get("loaded").set("lcdui", lcduiTable);

    globals.load(new ChoiceGroupLib());
    globals.load(new CommandLib());
    globals.load(new DisplayLib());
    globals.load(new FormLib());
    globals.load(new MidletLib());
    globals.load(new StringItemLib());

    return lcduiTable;
  }
}
