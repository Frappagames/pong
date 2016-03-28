package com.frappagames.pong.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.frappagames.pong.Pong;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(640, 360);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Pong();
        }
}
