package com.frappagames.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.frappagames.pong.Screens.SplashScreen;
import com.frappagames.pong.Tools.Settings;

public class Pong extends Game {
    public static final String TITLE  = "PONG";
    public static final int    WIDTH  = 128;
    public static final int    HEIGHT = 72;
    public SpriteBatch  batch;
    public TextureAtlas atlas;
    public Sound padSound;
    public Sound loseSound;
    public Sound borderSound;
    public Integer playerScore = 0;
    public Integer aiScore = 0;

    public static void playSound (Sound sound) {
        if (Settings.soundEnabled) sound.play(1);
    }

    @Override
    public void create() {
        Settings.load();
        atlas       = new TextureAtlas(Gdx.files.internal("pong.pack"));
        batch       = new SpriteBatch();
        padSound    = Gdx.audio.newSound(Gdx.files.internal("padSound.ogg"));
        loseSound   = Gdx.audio.newSound(Gdx.files.internal("loseSound.ogg"));
        borderSound = Gdx.audio.newSound(Gdx.files.internal("borderSound.ogg"));

        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
        padSound.dispose();
        loseSound.dispose();
        borderSound.dispose();
    }

    @Override
    public void render() {
        super.render();
    }
}
