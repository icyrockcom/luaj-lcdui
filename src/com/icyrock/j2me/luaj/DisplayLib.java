package com.icyrock.j2me.luaj;

import javax.microedition.midlet.MIDlet;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class DisplayLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable displayTable = new LuaTable();

    displayTable.set("get_display", new GetDisplay());

    env.get("lcdui").set("display", displayTable);
    return displayTable;
  }

  final class GetDisplay extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      arg.argcheck(arg instanceof Midlet, 1, "Midlet required");
      LuaValue midletValue = arg;

      Midlet midlet = (Midlet) midletValue;
      MIDlet j2meMidlet = midlet.getJ2meMidlet();
      javax.microedition.lcdui.Display j2meDisplay = javax.microedition.lcdui.Display
        .getDisplay(j2meMidlet);

      Display display = new Display();
      display.setJ2meDisplay(j2meDisplay);
      return display;
    }
  }
}
