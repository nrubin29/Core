package me.nrubin29.rpg.launcher;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Main {

    public static void main(String[] args) {
        NativeInterface.open();

        new Launcher();

        NativeInterface.runEventPump();
    }
}