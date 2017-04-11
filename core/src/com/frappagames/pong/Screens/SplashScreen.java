package com.frappagames.pong.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.frappagames.pong.Pong;

/**
 * Splash screen for Frappagames
 *
 * Created by Jérémy MOREAU on 14/08/15.
 */
public class SplashScreen implements Screen {
    private static final int SPLASHSCREEN_DURATION = 2000;
    private final Pong game;
    private OrthographicCamera camera;
    private Texture splashTexture;
    private Stage stage;
    private long startTime;

    public SplashScreen(Pong gameObject) {
        this.game = gameObject;
        stage = new Stage();
        splashTexture = new Texture(Gdx.files.internal("SplashScreen.png"));
        startTime = TimeUtils.millis();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);

        Image splashImage = new Image(splashTexture);
        stage.addActor(splashImage);
        splashImage.setX((Pong.WIDTH / 2) - (splashImage.getWidth() / 2));
        splashImage.setY((Pong.HEIGHT / 2) - (splashImage.getHeight() / 2));

        splashImage.addAction(Actions.sequence(
                Actions.alpha(0),
                Actions.fadeIn(0.5f),
                Actions.delay(1),
                Actions.fadeOut(0.5f)
        ));
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

        stage.act();
        stage.draw();

        if (TimeUtils.millis() > (startTime + SPLASHSCREEN_DURATION)) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().setCamera(camera);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        splashTexture.dispose();
        stage.dispose();
    }
}
