/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author besem4079
 */
public class MainMenu implements Screen{
    private MyRTSGame manager;
    private Rectangle singlePlayer;
    private Rectangle multiplayer;
    private Rectangle howToPlay;
    private Rectangle difficulty;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 clickPoint;
    
    
    public MainMenu(MyRTSGame manager){
       this.manager = manager;
       singlePlayer = new Rectangle(0,0,64,64);
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.end();
        
        if(Gdx.input.justTouched()){
            camera.unproject(clickPoint);
            
            if(singlePlayer.contains(clickPoint.x,clickPoint.y)){
                manager.changeScreen(new MainGame(manager));
            }
        }
        
    }

    @Override
    public void resize(int width, int height) {
        
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
