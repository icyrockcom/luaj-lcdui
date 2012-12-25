package com.icyrock.j2me.luaj;



import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.VarArgFunction;

public class FormLib extends OneArgFunction {
  Globals globals;

  public LuaValue call(LuaValue env) {
    globals = env.checkglobals();
    LuaTable formTable = new LuaTable();

    formTable.set("create", new Create());

    env.get("lcdui").set("form", formTable);
    return formTable;
  }

  final class Create extends VarArgFunction {
    public Varargs invoke(Varargs args) {
      String title = args.arg(1).checkjstring();
      
      javax.microedition.lcdui.Item[] j2meItems = new javax.microedition.lcdui.Item[args.narg() - 1];
      for(int i = 2; i <= args.narg(); i++) {
        args.argcheck(args.arg(i) instanceof Item, i, "Item required");
        
        Item item = (Item) args.arg(i);
        javax.microedition.lcdui.Item j2meItem = item.getJ2meItem();
        j2meItems[i - 2] = j2meItem;
      }
      
      javax.microedition.lcdui.Form j2meForm = new javax.microedition.lcdui.Form(title, j2meItems);
      
      Form form = new Form();
      form.setJ2meForm(j2meForm);
      return form;
    }
  }
}
