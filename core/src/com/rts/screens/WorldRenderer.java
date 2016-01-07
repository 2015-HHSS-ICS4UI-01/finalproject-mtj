/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.game.Player;
import com.rts.model.Unit;

/**
 *
 * @author lamonta
 */
public class WorldRenderer {
    // my games virtual width and height
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player p1;
    private Player p2;
    
    public WorldRenderer(Player p1, Player p2){
        
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        
        this.p1 = p1;
        this.p2 = p2;
        
        // move the x position of the camera
        camera.position.x = V_WIDTH/2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT/2f;
        // update the camera
        camera.update();
        
        // loads in the images
        AssetManager.load();
    }
    
    public void render(float delta){
        // clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        // links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);
        
        // tells the renderer this is the list
        batch.begin();
        // list of things to draw
        // draw the ground
        batch.draw(AssetManager.grass, 0, 0, 800, 40);
        
        //draw the units
        if(p1.getUnits() != null){
        for(Unit u: p1.getUnits()){
            batch.draw(AssetManager.boxUnitBlue, u.getX(), u.getY());
        }
        }
        
        if(p2.getUnits() != null){
        for(Unit u: p2.getUnits()){
            batch.draw(AssetManager.boxUnitRed, u.getX(), u.getY());
        }
        }
        
        
//        // draw mario
//        if(player.getState() == Unit.State.STANDING){
//            if(player.isFacingLeft()){
//                batch.draw(AssetManager.skrillex, player.getX(), player.getY());
//            }else{
//                batch.draw(AssetManager.skrillex, player.getX(), player.getY());
//            }
//        }else if(player.getState() == Unit.State.RUNNING){
//            if(player.isFacingLeft()){
//                batch.draw(AssetManager.marioRunL.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
//            }else{
//                batch.draw(AssetManager.marioRun.getKeyFrame(player.getStateTime(), true), player.getX(), player.getY());
//            }
//        }
        
        // finished listing things to draw
        batch.end();
    }
    
    public void resize(int width, int height){
        viewport.update(width, height);
    }
}
