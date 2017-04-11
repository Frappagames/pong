package com.frappagames.pong.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pong.Pong;
import com.frappagames.pong.Tools.GameScreen;

/**
 * Created by Miridan on 09/03/16.
 */
public class DifficultyScreen extends GameScreen {
    private final Image       pongTitle;
    private final ImageButton easyBtn;
    private final ImageButton normalBtn;
    private final ImageButton hardBtn;
    private final ImageButton backBtn;
    private int currentOption;

    public DifficultyScreen(final Pong game) {
        super(game);

        currentOption = 1;
        pongTitle = new Image(new TextureRegionDrawable(game.atlas.findRegion("pong")));
        easyBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnEasy")),
                new TextureRegionDrawable(game.atlas.findRegion("btnEasyOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnEasyOver"))
        );
        normalBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnNormal")),
                new TextureRegionDrawable(game.atlas.findRegion("btnNormalOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnNormalOver"))
        );
        hardBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnHard")),
                new TextureRegionDrawable(game.atlas.findRegion("btnHardOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnHardOver"))
        );
        backBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnBack")),
                new TextureRegionDrawable(game.atlas.findRegion("btnBackOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnBackOver"))
        );

        easyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
                game.setScreen(new PlayScreen(game, 1));
            }
        });

        normalBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
                game.setScreen(new PlayScreen(game, 2));
            }
        });

        hardBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
                game.setScreen(new PlayScreen(game, 3));
            }
        });

        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
                game.setScreen(new MenuScreen(game));
            }
        });

        Table menu = new Table();
        menu.add(easyBtn).pad(6, 0, 2, 0).row();
        menu.add(normalBtn).pad(2, 0, 2, 0).row();
        menu.add(hardBtn).pad(2, 0, 2, 0).row();
        menu.add(backBtn).pad(6, 0, 0, 0).row();

        table.add(pongTitle).center().expandY().width(55).padRight(4);
        table.add(menu).expandY().width(55).padLeft(4);
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Pong.playSound(game.padSound);
            game.setScreen(new MenuScreen(game));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && currentOption > 1) {
            currentOption--;
            Pong.playSound(game.padSound);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && currentOption < 4) {
            Pong.playSound(game.padSound);
            currentOption++;
        }

        easyBtn.setChecked(currentOption == 1);
        normalBtn.setChecked(currentOption == 2);
        hardBtn.setChecked(currentOption == 3);
        backBtn.setChecked(currentOption == 4);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Pong.playSound(game.padSound);
            switch (currentOption) {
                case 1:
                case 2:
                case 3:
                    game.setScreen(new PlayScreen(game, currentOption));
                    break;
                case 4:
                    game.setScreen(new MenuScreen(game));
                    break;
            }
        }

        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
