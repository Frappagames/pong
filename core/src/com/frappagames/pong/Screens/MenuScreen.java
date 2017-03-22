package com.frappagames.pong.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private int currentOption;

    public MenuScreen(final Pong game) {
        super(game);

        currentOption = 1;
        pongTitle  = new Image(new TextureRegionDrawable(game.atlas.findRegion("pong")));
        startBtn   = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnStart")),
                new TextureRegionDrawable(game.atlas.findRegion("btnStartOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnStartOver"))
        );
        optionsBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnOptions")),
                new TextureRegionDrawable(game.atlas.findRegion("btnOptionsOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnOptionsOver"))
        );
        quitBtn    = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnQuit")),
                new TextureRegionDrawable(game.atlas.findRegion("btnQuitOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnQuitOver"))
        );

        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
                game.setScreen(new DifficultyScreen(game));
            }
        });

        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
                game.setScreen(new OptionsScreen(game));
            }
        });

        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Pong.playSound(game.padSound);
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && currentOption > 1) {
            currentOption--;
            Pong.playSound(game.padSound);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && currentOption <= 3) {
            Pong.playSound(game.padSound);
            currentOption++;
        }

        startBtn.setChecked(currentOption == 1);
        optionsBtn.setChecked(currentOption == 2);
        quitBtn.setChecked(currentOption == 3);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Pong.playSound(game.padSound);
            switch (currentOption) {
                case 1:
                    game.setScreen(new DifficultyScreen(game));
                    break;
                case 2:
                    game.setScreen(new OptionsScreen(game));
                    break;
                case 3:
                    Gdx.app.exit();
                    break;
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
