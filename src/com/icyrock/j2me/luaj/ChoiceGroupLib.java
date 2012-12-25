package com.icyrock.j2me.luaj;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

public class ChoiceGroupLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable choiceGroupTable = new LuaTable();

    choiceGroupTable.set("create", new Create());

    env.get("lcdui").set("choice_group", choiceGroupTable);
    return choiceGroupTable;
  }

  final class Create extends TwoArgFunction {
    public LuaValue call(LuaValue arg1, LuaValue arg2) {
      String label = arg1.checkjstring();
      int choiceType = arg2.checkint();

      javax.microedition.lcdui.ChoiceGroup j2meChoiceGroup = new javax.microedition.lcdui.ChoiceGroup(
        label, choiceType);

      ChoiceGroup choiceGroup = new ChoiceGroup();
      choiceGroup.setJ2meChoiceGroup(j2meChoiceGroup);
      return choiceGroup;
    }
  }
}
