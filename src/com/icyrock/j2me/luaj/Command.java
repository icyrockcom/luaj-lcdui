package com.icyrock.j2me.luaj;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class Command extends BaseLuaValue {
  javax.microedition.lcdui.Command j2meCommand;

  public Command() {
    super();
    
    methods.set("get_label", new GetLabel());
    methods.set("get_command_type", new GetCommandType());
    methods.set("get_priority", new GetPriority());
  }

  public Command(javax.microedition.lcdui.Command j2meCommand) {
    this();
    
    this.j2meCommand = j2meCommand;
  }

  public javax.microedition.lcdui.Command getJ2meCommand() {
    return j2meCommand;
  }

  final class GetLabel extends ZeroArgFunction {
    public LuaValue call() {
      return valueOf(j2meCommand.getLabel());
    }
  }
  
  final class GetCommandType extends ZeroArgFunction {
    public LuaValue call() {
      return valueOf(j2meCommand.getCommandType());
    }
  }
  
  final class GetPriority extends ZeroArgFunction {
    public LuaValue call() {
      return valueOf(j2meCommand.getPriority());
    }
  }
  
}