/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.game.MainGame;
import com.rts.game.MyRTSGame;
import com.rts.screens.AssetManager;

/**
 * The game's Main Menu; allows user to select a game mode, change the game's 
 * difficulty, or learn how to play the game.
 * @author besem4079
 */
public class MainMenu implements Screen{
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private MyRTSGame manager;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    
    //menu button collisions
    private Rectangle singlePlayer;
    private Rectangle multiplayer;
    private Rectangle howToPlay;
    private Rectangle difficulty;
    
    //vector that tracks the coordinates of user's click
    private Vector3 clickPoint;
    
    public MainMenu(MyRTSGame manager){
        this.manager = manager;
        singlePlayer = new Rectangle(0,0,64,64);
        howToPlay = new Rectangle(128,0,64,64);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        clickPoint = new Vector3(); 
        camera.position.x = V_WIDTH/2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT/2f;
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
        
        batch.begin();
        batch.draw(AssetManager.grass,singlePlayer.x,singlePlayer.y,singlePlayer.width,singlePlayer.height);
        batch.draw(AssetManager.grass,howToPlay.x,howToPlay.y,howToPlay.width,howToPlay.height);
        batch.end();
        
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
            manager.changeScreen(new MainGame(manager));
        }
        
        if(Gdx.input.isTouched()){
            //converts screen touch coordinates to ingame coordinates
            viewport.unproject(clickPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            
            //starts single player mode
            if(singlePlayer.contains(clickPoint.x, clickPoint.y)){
                manager.changeScreen(new MainGame(manager));
            }
            
            //shows instructions on how to play
            if(howToPlay.contains(clickPoint.x, clickPoint.y)){
                manager.changeScreen(new HowToPlay(manager));
            }
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
