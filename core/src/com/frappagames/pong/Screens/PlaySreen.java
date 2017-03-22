package com.frappagames.pong.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.frappagames.pong.Pong;
import com.frappagames.pong.Tools.GameScreen;
import com.badlogic.gdx.Input.Keys;

/**
 * PlayScreen is the main game screen where player play
 * <p/>
 * Created by Miridan on 09/03/16.
 */
public class PlaySreen extends GameScreen {
    private float pad_speed;
    private float max_speed;
    protected Sprite  ball;
    protected Sprite  playerPad;
    protected Sprite  aiPad;
    protected Sprite  startTxt;
    protected Vector2 ballSpeed;
    private   Label   playerScoreLbl;
    private   Label   aiScoreLbl;
    private boolean isPlaying;
    private float initial_speed;


    public PlaySreen(final Pong game, int difficulty) {
        super(game);
        isPlaying = false;
        game.playerScore = 0;
        game.aiScore = 0;

        switch (difficulty) {
            case 1 :
                pad_speed = 30;
                initial_speed = 50;
                max_speed = 100;
                break;
            case 3 :
                pad_speed = 100;
                initial_speed = 100;
                max_speed = 200;
                break;
            default :
                pad_speed = 60;
                initial_speed = 75;
                max_speed = 150;
                break;
        }

        Image playerImage = new Image(new TextureRegionDrawable(game.atlas.findRegion("lblPlayer")));
        Image pongAIImage = new Image(new TextureRegionDrawable(game.atlas.findRegion("lblPongAI")));
        ball = new Sprite(new TextureRegion(game.atlas.findRegion("ball")));
        playerPad = new Sprite(new TextureRegion(game.atlas.findRegion("paddle")));
        aiPad = new Sprite(new TextureRegion(game.atlas.findRegion("paddle")));
        startTxt = new Sprite(new TextureRegion(game.atlas.findRegion("continue")));

        ball.setPosition(111, 32);
        playerPad.setPosition(121, 28);
        aiPad.setPosition(5, 28);

        ballSpeed = new Vector2(initial_speed, 0);
        ballSpeed.setAngle(180);

        BitmapFont       font       = new BitmapFont(Gdx.files.internal("snaredrum.fnt"), false);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.GRAY);

        playerScoreLbl = new Label("00", labelStyle);
        aiScoreLbl = new Label("00", labelStyle);
        playerScoreLbl.setAlignment(Align.center);
        aiScoreLbl.setAlignment(Align.center);

        ImageButton menuBtn = new ImageButton(
                new TextureRegionDrawable(game.atlas.findRegion("btnMenu")),
                new TextureRegionDrawable(game.atlas.findRegion("btnMenuOver"))
        );

        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pong.playSound(game.padSound);
                game.setScreen(new MenuScreen(game));
            }
        });

        Table menu = new Table();
        menu.setFillParent(true);
        menu.top();
        menu.add(pongAIImage);
        menu.add(aiScoreLbl).width(17);
        menu.add(menuBtn);
        menu.add(playerScoreLbl).width(17);
        menu.add(playerImage);
        stage.addActor(menu);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();

        if (isPlaying) {
            // process user input
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(5, Gdx.input.getY(), 0);
                camera.unproject(touchPos);

                if (touchPos.y < 7) playerPad.setY(2);
                else if (touchPos.y > 59) playerPad.setY(54);
                else playerPad.setY(touchPos.y - 5);
            }

            if (Gdx.input.isKeyPressed(Keys.DOWN) && playerPad.getY() > 2) {
                playerPad.setY(playerPad.getY() - pad_speed * Gdx.graphics.getDeltaTime());
            }

            if (Gdx.input.isKeyPressed(Keys.UP) && playerPad.getY() < 54) {
                playerPad.setY(playerPad.getY() + pad_speed * Gdx.graphics.getDeltaTime());
            }

            moveBall(delta);
            moveAI(delta);
        } else {
            game.batch.draw(startTxt, 22, 22);
            if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
                isPlaying = true;
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Pong.playSound(game.padSound);
            game.setScreen(new MenuScreen(game));
        }

        game.batch.draw(ball, ball.getX(), ball.getY());
        game.batch.draw(playerPad, playerPad.getX(), playerPad.getY());
        game.batch.draw(aiPad, aiPad.getX(), aiPad.getY());
        game.batch.end();
    }

    public void moveBall(float delta) {
        ball.setPosition(ball.getX() + (ballSpeed.x * delta), ball.getY() + (ballSpeed.y * delta));

        // Test Paddles collisions : Left collision
        if (ball.getX() < 7
                && (ball.getY() + 2) >= aiPad.getY()
                && ball.getY() <= (aiPad.getY() + 10)) {
            ball.setX(7);

            float ballCenter = (ball.getY() + (ball.getHeight() / 2));
            float padCenter = (aiPad.getY() + (aiPad.getHeight() / 2));
            float collisionCenter = ballCenter - padCenter;

            ballSpeed.setAngle(collisionCenter * 10);
            if(ballSpeed.len() < max_speed) ballSpeed.scl(1.05f);

            Pong.playSound(game.padSound);

        // Right Collision
        } else if (ball.getX() > 119
                && (ball.getY() + 2) >= playerPad.getY()
                && ball.getY() <= (playerPad.getY() + 10)) {
            ball.setX(119);

            float ballCenter = (ball.getY() + (ball.getHeight() / 2));
            float padCenter = (playerPad.getY() + (playerPad.getHeight() / 2));
            float collisionCenter = ballCenter - padCenter;

            ballSpeed.setAngle(180 - collisionCenter * 10);
            if(ballSpeed.len() < max_speed) ballSpeed.scl(1.05f);

            Pong.playSound(game.padSound);
        }

        // Test top and bottom lines collisions
        if (ball.getY() < 2) {
            ball.setY(2);
            ballSpeed.setAngle(-ballSpeed.angle());
            Pong.playSound(game.borderSound);
        } else if (ball.getY() > 62) {
            ball.setY(62);
            ballSpeed.setAngle(-ballSpeed.angle());
            Pong.playSound(game.borderSound);
        }

        // Test right and left side collisions (Lose)
        if (ball.getX() < 5) {
            ball.setX(5);
            ballSpeed.x = -ballSpeed.x;
            Pong.playSound(game.loseSound);
            game.playerScore++;
            playerScoreLbl.setText(formatScore(game.playerScore));
            setGameOver(false);
        } else if (ball.getX() > 121) {
            ball.setX(121);
            ballSpeed.x = -ballSpeed.x;
            Pong.playSound(game.loseSound);
            game.aiScore++;
            aiScoreLbl.setText(formatScore(game.aiScore));
            setGameOver(true);
        }
    }

    private String formatScore(Integer score) {
        String scoreString = score.toString();
        if (score < 10) {
            scoreString = "0" + scoreString;
        }
        return scoreString;
    }

    private void moveAI(float delta) {

        float aiPadCenter = aiPad.getY() + 5;
        float ballCenter = ball.getY() + 1;
        float difference = ballCenter - aiPadCenter;
        float maxMove = pad_speed * delta;

        if (difference > maxMove) {
            aiPad.setY(aiPad.getY() + maxMove);
        } else if (difference < -maxMove) {
            aiPad.setY(aiPad.getY() - maxMove);
        } else {
            aiPad.setY(aiPad.getY() + difference);
        }

        if (aiPad.getY() > 54)     aiPad.setY(54);
        else if (aiPad.getY() < 2) aiPad.setY(2);
    }

    private void setGameOver(boolean isPlayer) {
        isPlaying = false;
        ballSpeed = new Vector2(initial_speed, 0);
        playerPad.setPosition(121, 28);
        aiPad.setPosition(5, 28);

        if (isPlayer) {
            ball.setPosition(15, 32);
        } else {
            ball.setPosition(111, 32);
            ballSpeed.setAngle(180);
        }

    }

    @Override
    public void pause() {
        Gdx.graphics.setContinuousRendering(false);
        isPlaying = false;

        super.pause();
    }

    @Override
    public void resume() {
        Gdx.graphics.setContinuousRendering(true);

        super.resume();
    }
}
