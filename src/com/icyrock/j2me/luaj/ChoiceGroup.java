package com.icyrock.j2me.luaj;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

public class ChoiceGroup extends Item {
  javax.microedition.lcdui.ChoiceGroup j2meChoiceGroup;

  public ChoiceGroup() {
    super();
    methods.set("append", new Append());
    methods.set("get_selected_string", new GetSelectedString());
  }

  public ChoiceGroup(javax.microedition.lcdui.ChoiceGroup j2meChoiceGroup) {
    this();
    setJ2meChoiceGroup(j2meChoiceGroup);
  }

  public javax.microedition.lcdui.ChoiceGroup getJ2meChoiceGroup() {
    return j2meChoiceGroup;
  }

  public void setJ2meChoiceGroup(javax.microedition.lcdui.ChoiceGroup j2meChoiceGroup) {
    setJ2meItem(j2meChoiceGroup);
    this.j2meChoiceGroup = j2meChoiceGroup;
  }

  final class Append extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      String label = arg.checkjstring();
      int ret = j2meChoiceGroup.append(label, null);
      return valueOf(ret);
    }
  }

  final class GetSelectedString extends ZeroArgFunction {
    public LuaValue call() {
      int selectedIndex = j2meChoiceGroup.getSelectedIndex();
      if (selectedIndex == -1) {
        return NIL;
      }

      String selectedString = j2meChoiceGroup.getString(selectedIndex);
      return valueOf(selectedString);
    }

  }
}
