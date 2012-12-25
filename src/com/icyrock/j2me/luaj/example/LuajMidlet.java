package com.icyrock.j2me.luaj.example;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.jme.JmePlatform;

import com.icyrock.j2me.luaj.LcduiLib;
import com.icyrock.j2me.luaj.Midlet;

public class LuajMidlet extends MIDlet {
  protected void startApp() throws MIDletStateChangeException {
    System.out.println("< startApp");

    final String script = "phi";

    LuaValue _G = JmePlatform.standardGlobals();
    _G.load(new LcduiLib());

    Midlet midlet = (Midlet) _G.get("lcdui").get("midlet").get("current");
    midlet.setJ2meMidlet(this);

    LuaValue luaLoadFunc = new OneArgFunction() {
      boolean loaded = false;

      public LuaValue call(LuaValue arg) {
        if (!loaded) {
          String luaScript = readFile("file:///SDCard/icyrock/lua/" + script + ".lua");
          loaded = true;
          return valueOf(luaScript);
        }
        return NIL;
      }
    };
    _G.get("load").call(luaLoadFunc).call();

    System.out.println("< startApp");
  }

  protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
  }

  protected void pauseApp() {
  }

  private String readFile(String fileName) {
    String ret = "";

    FileConnection fc = null;
    try {
      fc = (FileConnection) Connector.open(fileName);
      InputStream is = fc.openInputStream();

      StringBuffer sb = new StringBuffer();

      int cap = 2000;
      byte[] bytes = new byte[cap];

      while (true) {
        int available = is.available();
        if (available == 0) {
          break;
        }

        int len = available > cap ? cap : available;
        is.read(bytes, 0, len);

        sb.append(new String(bytes, 0, len, "UTF-8"));
      }

      ret = sb.toString();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fc != null) {
        try {
          fc.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return ret;
  }
}
