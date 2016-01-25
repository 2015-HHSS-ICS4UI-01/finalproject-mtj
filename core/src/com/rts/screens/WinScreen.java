package com.rts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.game.MyRTSGame;

/**
 * The screen that appears when a base has died, announcing the player that
 * destroyed the base as the winner.
 * @author MTJ
 */
public class WinScreen implements Screen {

    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;

    private MyRTSGame manager;
    private String winner;
    private Viewport viewport;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    public WinScreen(MyRTSGame manager, String winner) {
        this.manager = manager;
        this.winner = winner;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        camera.position.x = V_WIDTH / 2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT / 2f;
        // update the camera
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        //displays the player who won the game
        if (winner.contains("p1")) {
            batch.draw(AssetManager.p1win, 0, 0);
        } else {
            batch.draw(AssetManager.p2win, 0, 0);
        }
        batch.end();

        //pressing the space bar returns players to the main menu
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            manager.changeScreen(new MainMenu(manager));
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    }
}
