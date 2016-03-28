package com.frappagames.pong.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.frappagames.pong.Pong;

/**
 * Default game screen
 * Created by jmoreau on 12/01/16.
 */
public class GameScreen implements Screen {
    protected final OrthographicCamera camera;
    private final Viewport           viewport;
    protected     Pong               game;
    protected     Stage              stage;
    protected     Table              table;

    public GameScreen(Pong game) {
        this.game = game;
        camera    = new OrthographicCamera();
        viewport  = new FitViewport(Pong.WIDTH, Pong.HEIGHT, camera);
        stage     = new Stage(viewport);

        TextureRegionDrawable background = new TextureRegionDrawable(game.atlas.findRegion("background"));

        camera.position.set(Pong.WIDTH / 2, Pong.HEIGHT / 2, 0);

        table = new Table();
        table.setBackground(background);
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
