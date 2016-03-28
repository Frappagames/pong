package com.frappagames.pong.Screens;

import com.badlogic.gdx.Gdx;
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
public class DifficultyScreen extends GameScreen {
    private final Image       pongTitle;
    private final ImageButton easyBtn;
    private final ImageButton normalBtn;
    private final ImageButton hardBtn;

    public DifficultyScreen(final Pong game) {
        super(game);

        pongTitle = new Image(new TextureRegionDrawable(game.atlas.findRegion("pong")));
        easyBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnEasy")),
                new TextureRegionDrawable(game.atlas.findRegion("btnEasyOver"))
        );
        normalBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnNormal")),
                new TextureRegionDrawable(game.atlas.findRegion("btnNormalOver"))
        );
        hardBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnHard")),
                new TextureRegionDrawable(game.atlas.findRegion("btnHardOver"))
        );
        ImageButton backBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnBack")),
                new TextureRegionDrawable(game.atlas.findRegion("btnBackOver"))
        );

        easyBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Pong.playSound(game.padSound);
                game.setScreen(new PlaySreen(game, 1));
            }
        });

        normalBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Pong.playSound(game.padSound);
                game.setScreen(new PlaySreen(game, 2));
            }
        });

        hardBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Pong.playSound(game.padSound);
                game.setScreen(new PlaySreen(game, 3));
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
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
