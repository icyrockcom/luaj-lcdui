package com.icyrock.j2me.luaj;

import javax.microedition.midlet.MIDlet;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class Midlet extends BaseLuaValue {
  private MIDlet j2meMidlet;

  public Midlet() {
    super();
    
    methods.set("notify_destroyed", new NotifyDestroyed());
  }
  
  public MIDlet getJ2meMidlet() {
    return j2meMidlet;
  }

  public void setJ2meMidlet(MIDlet j2meMidlet) {
    this.j2meMidlet = j2meMidlet;
  }
  
  final class NotifyDestroyed extends ZeroArgFunction {
    public LuaValue call() {
      j2meMidlet.notifyDestroyed();
      return this;
    }
    
  }
}
