/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.model.Mario;
import com.mygdx.model.World;
import com.mygdx.screens.WorldRenderer;

/**
 *
 * @author donet6376
 */
public class MainGame implements Screen{
    
    private World theWorld;
    private Mario thePlayer;
    private WorldRenderer renderer;
    
    public MainGame(){
        theWorld = new World();
        thePlayer = theWorld.getPlayer();
        renderer = new WorldRenderer(theWorld);
    }
    
    
    @Override
    public void show() {
        
    }

    //the game loop
    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isTouched()){
            thePlayer.setVelocityX(2f);
        }else if(Gdx.input.isKeyPressed(Keys.A)){
            thePlayer.setVelocityX(-2f);
        }
        
        thePlayer.update(deltaTime);
        
        //draw the screen
        renderer.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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
