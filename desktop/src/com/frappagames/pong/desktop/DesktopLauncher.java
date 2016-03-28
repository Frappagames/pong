package com.frappagames.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frappagames.pong.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Pong.HEIGHT * 5;
		config.width = Pong.WIDTH * 5;
		config.title = Pong.TITLE;
		new LwjglApplication(new Pong(), config);
	}
}
