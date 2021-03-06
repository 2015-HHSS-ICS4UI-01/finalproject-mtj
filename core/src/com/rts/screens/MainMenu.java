package com.rts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.game.MainGame;
import com.rts.game.MyRTSGame;

/**
 * The game's Main Menu; allows players to start a 2 player game, change the colours
 * of their units, and learn how to play the game.
 *
 * @author MTJ
 */
public class MainMenu implements Screen {

    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;

    private MyRTSGame manager;
    private MainGame newGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float pressEnterTimer;
    private boolean showEnter = true;

    //difficulty of a single player game
    private String[] coinLevels;
    private String startingCoins;

    public MainMenu(MyRTSGame manager) {
        this.manager = manager;
        coinLevels = new String[]{"Low", "Medium", "High"};
        startingCoins = "Medium";
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        pressEnterTimer = 0;
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
        batch.draw(AssetManager.mainMenu, 0, 0);

        if (pressEnterTimer > 1) {
            showEnter = !showEnter;
            pressEnterTimer = 0;
        }

        if (showEnter) {
            batch.draw(AssetManager.pressEnter, 220, 72);
        }

        batch.draw(AssetManager.getUnitColor("p1"), 96, 64);
        batch.draw(AssetManager.getUnitColor("p2"), 672, 64);
        batch.end();

        //if the enter key is pressed, the game starts
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            manager.changeScreen(new MainGame(manager));
        }

        //unit colour changing for player 1
        if (Gdx.input.isKeyJustPressed(Keys.A)) {
            AssetManager.setColorLeft("p1");
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)) {
            AssetManager.setColorRight("p1");
        }

        //unit colour changing for player 2
        if (Gdx.input.isKeyJustPressed(Keys.J)) {
            AssetManager.setColorLeft("p2");
        }

        if (Gdx.input.isKeyJustPressed(Keys.L)) {
            AssetManager.setColorRight("p2");
        }

        pressEnterTimer += delta;
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
