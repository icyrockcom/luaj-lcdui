package com.icyrock.j2me.luaj;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

public class Display extends BaseLuaValue {
  javax.microedition.lcdui.Display j2meDisplay;

  public Display() {
    super();
    methods.set("get_current", new GetCurrent());
    methods.set("set_current", new SetCurrent());
  }

  public javax.microedition.lcdui.Display getJ2meDisplay() {
    return j2meDisplay;
  }

  public void setJ2meDisplay(javax.microedition.lcdui.Display j2meDisplay) {
    this.j2meDisplay = j2meDisplay;
  }

  final class GetCurrent extends ZeroArgFunction {
    public LuaValue call() {
      Displayable displayable = new Displayable();
      javax.microedition.lcdui.Displayable j2meDisplayable = j2meDisplay.getCurrent();
      displayable.setJ2meDisplayable(j2meDisplayable);
      return displayable;
    }
  }

  final class SetCurrent extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      arg.argcheck(arg instanceof Displayable, 1, "Displayable required");
      Displayable displayable = (Displayable) arg;
      javax.microedition.lcdui.Displayable j2meDisplayable = displayable.getJ2meDisplayable();
      j2meDisplay.setCurrent(j2meDisplayable);
      System.out.println("j2me_display.setCurrent: j2meDisplayable: " + j2meDisplayable);
      return this;
    }
  }
}
