package com.icyrock.j2me.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ThreeArgFunction;

public class CommandLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable commandTable = new LuaTable();

    commandTable.set("create", new Create());

    env.get("lcdui").set("command", commandTable);
    return commandTable;
  }

  final class Create extends ThreeArgFunction {
    public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
      String label = arg1.checkjstring();
      int commandType = arg2.checkint();
      int priority = arg3.checkint();
      
      javax.microedition.lcdui.Command j2meCommand = new javax.microedition.lcdui.Command(label, commandType, priority);
      Command command = new Command(j2meCommand);
      return command;
    }
  }
}
