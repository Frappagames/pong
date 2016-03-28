package com.frappagames.pong.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pong.Pong;
import com.frappagames.pong.Tools.GameScreen;

/**
 * Created by Miridan on 09/03/16.
 */
public class MenuScreen extends GameScreen {
    private final Image       pongTitle;
    private final ImageButton startBtn;
    private final ImageButton optionsBtn;
    private final ImageButton quitBtn;

    public MenuScreen(final Pong game) {
        super(game);

        pongTitle  = new Image(new TextureRegionDrawable(game.atlas.findRegion("pong")));
        startBtn   = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnStart")),
                new TextureRegionDrawable(game.atlas.findRegion("btnStartOver"))
        );
        optionsBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnOptions")),
                new TextureRegionDrawable(game.atlas.findRegion("btnOptionsOver"))
        );
        quitBtn    = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnQuit")),
                new TextureRegionDrawable(game.atlas.findRegion("btnQuitOver"))
        );

        startBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Pong.playSound(game.padSound);
                game.setScreen(new DifficultyScreen(game));
            }
        });

        optionsBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Pong.playSound(game.padSound);
                game.setScreen(new OptionsScreen(game));
            }
        });

        quitBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        Table menu = new Table();
        menu.add(startBtn).pad(4, 0, 4, 0).row();
        menu.add(optionsBtn).pad(4, 0, 4, 0).row();
        menu.add(quitBtn).pad(4, 0, 4, 0).row();

        table.add(pongTitle).center().expandY().width(55).padRight(4);
        table.add(menu).expandY().width(55).padLeft(4);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
