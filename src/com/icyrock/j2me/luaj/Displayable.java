package com.icyrock.j2me.luaj;

import javax.microedition.lcdui.CommandListener;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class Displayable extends BaseLuaValue {
  javax.microedition.lcdui.Displayable j2meDisplayable;

  public Displayable() {
    super();

    methods.set("add_command", new AddCommand());
    methods.set("remove_command", new RemoveCommand());
    methods.set("set_command_listener", new SetCommandListener());
  }

  public Displayable(javax.microedition.lcdui.Displayable j2meDisplayable2) {
    super();
    setJ2meDisplayable(j2meDisplayable2);
  }

  public javax.microedition.lcdui.Displayable getJ2meDisplayable() {
    return j2meDisplayable;
  }

  public void setJ2meDisplayable(javax.microedition.lcdui.Displayable j2meDisplayable) {
    this.j2meDisplayable = j2meDisplayable;
  }

  final class AddCommand extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      arg.argcheck(arg instanceof Command, 1, "Command required");
      Command command = (Command) arg;
      j2meDisplayable.addCommand(command.getJ2meCommand());
      return this;
    }
  }

  final class RemoveCommand extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      arg.argcheck(arg instanceof Command, 1, "Command required");
      Command command = (Command) arg;
      j2meDisplayable.removeCommand(command.getJ2meCommand());
      return this;
    }
  }

  final class SetCommandListener extends OneArgFunction {
    public LuaValue call(LuaValue arg) {
      arg.argcheck(arg instanceof LuaFunction, 1, "LuaFunction required");

      final LuaFunction callback = (LuaFunction)arg;

      j2meDisplayable.setCommandListener(new CommandListener() {
        public void commandAction(javax.microedition.lcdui.Command j2meCommand,
          javax.microedition.lcdui.Displayable j2meDisplayable) {

          Command command = new Command(j2meCommand);
          Displayable displayable = new Displayable(j2meDisplayable);

          callback.call(command, displayable);
        }
      });

      return this;
    }
  }
}
