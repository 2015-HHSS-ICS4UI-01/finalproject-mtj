/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.game.MainGame;
import com.rts.game.MyRTSGame;

/**
 *
 * @author besem4079
 */
public class HowToPlay implements Screen{
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private MyRTSGame manager;
    private Viewport viewport;
    private Rectangle back;
    private Rectangle next;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private float x;
    private float y;  
    private Vector3 clickPoint;
    
    public HowToPlay(MyRTSGame manager){
        this.manager = manager;
        back = new Rectangle(4,0,64,64);
        next = new Rectangle(128,0,64,64);
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
        batch.draw(AssetManager.grass,back.x,back.y,back.width,back.height);
        batch.draw(AssetManager.grass,next.x,next.y,next.width,next.height);
        batch.end();
        
        if(Gdx.input.isTouched()){
            viewport.unproject(clickPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(back.contains(clickPoint.x, clickPoint.y)){
                manager.changeScreen(new MainMenu(manager));
            }
            if(next.contains(clickPoint.x, clickPoint.y)){
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
