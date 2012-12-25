package com.icyrock.j2me.luaj;

public class Screen extends Displayable {
  javax.microedition.lcdui.Screen j2meScreen;

  public javax.microedition.lcdui.Screen getJ2meScreen() {
    return j2meScreen;
  }

  public void setJ2meScreen(javax.microedition.lcdui.Screen j2meScreen) {
    setJ2meDisplayable(j2meScreen);
    this.j2meScreen = j2meScreen;
  }
}