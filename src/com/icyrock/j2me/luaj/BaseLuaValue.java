package com.icyrock.j2me.luaj;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public abstract class BaseLuaValue extends LuaValue {
  LuaTable methods;

  public BaseLuaValue() {
    methods = new LuaTable();
    methods.set("tostring", new Tostring());
  }

  public LuaValue get(LuaValue name) {
    return methods.get(name);
  }

  public String tojstring() {
    return this.getClass().getName() + " " + Integer.toHexString(hashCode());
  }

  public int type() {
    return LuaValue.TUSERDATA;
  }

  public String typename() {
    return "userdata";
  }

  final class Tostring extends ZeroArgFunction {
    public LuaValue call() {
      return valueOf(BaseLuaValue.this.tojstring());
    }
  }
}
