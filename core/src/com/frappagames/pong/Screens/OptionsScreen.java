package com.frappagames.pong.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.pong.Pong;
import com.frappagames.pong.Tools.GameScreen;
import com.frappagames.pong.Tools.Settings;

/**
 * Option Screen let the player activate or deactivate sounds
 *
 * Created by Miridan on 09/03/16.
 */
public class OptionsScreen extends GameScreen {
    private final ImageButton onBtn;
    private final ImageButton offBtn;

    public OptionsScreen(final Pong game) {
        super(game);

        Image pongTitle  = new Image(new TextureRegionDrawable(game.atlas.findRegion("pong")));
        Image soundTitle = new Image(new TextureRegionDrawable(game.atlas.findRegion("lblSound")));

        onBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnOn")),
                new TextureRegionDrawable(game.atlas.findRegion("btnOnOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnOnOver"))
        );
        offBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnOff")),
                new TextureRegionDrawable(game.atlas.findRegion("btnOffOver")),
                new TextureRegionDrawable(game.atlas.findRegion("btnOffOver"))
        );
        ImageButton backBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnBack")),
                new TextureRegionDrawable(game.atlas.findRegion("btnBackOver"))
        );
        if (Settings.soundEnabled) {
            onBtn.setChecked(true);
            offBtn.setChecked(false);
        } else {
            onBtn.setChecked(false);
            offBtn.setChecked(true);
        }

        onBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!Settings.soundEnabled) {
                    Settings.setSound(true);
                    Pong.playSound(game.padSound);
                }
                onBtn.setChecked(true);
                offBtn.setChecked(false);
            }
        });

        offBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Settings.soundEnabled) {
                    Settings.setSound(false);
                }
                onBtn.setChecked(false);
                offBtn.setChecked(true);
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
        menu.add(soundTitle).colspan(2).pad(4, 0, 4, 0).row();
        menu.add(onBtn).pad(4);
        menu.add(offBtn).pad(4).row();
        menu.add(backBtn).colspan(2).pad(6, 4, 4, 4).row();
        table.add(pongTitle).center().expandY().width(55).padRight(4);
        table.add(menu).expandY().width(55).padLeft(4);
    }
}
