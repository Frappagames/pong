package com.frappagames.pong.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Settings class provide an interface for manage preferences
 *
 * Created by jmoreau on 19/08/15.
 */
public class Settings {
    private static Preferences settings;

    public static boolean soundEnabled;

    public static void load () {
        settings 	 = Gdx.app.getPreferences("com.frappagames.pong.settings");
        soundEnabled = settings.getBoolean("sound", true);
    }

    public static void setSound(Boolean active) {
        soundEnabled = active;
        settings.putBoolean("sound", active);
        settings.flush();
    }
}
