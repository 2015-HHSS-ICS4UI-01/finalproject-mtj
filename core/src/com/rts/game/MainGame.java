/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.model.Unit;
import com.rts.screens.WorldRenderer;

/**
 *
 * @author donet6376
 */
public class MainGame implements Screen{

    private MyRTSGame manager;
    private Unit units;
    private WorldRenderer renderer;
    private int round;
    private Player user;
    
    public MainGame(MyRTSGame manager){
        this.manager = manager;
        units = new Unit(16,16,16,32);
        renderer = new WorldRenderer(units);
        round = 1;
        user = new Player(round);
        System.out.println(user.getCoins());
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float deltaTime) {
        
        
        if(Gdx.input.isKeyPressed(Keys.D)){
            units.setVelocityX(2f);
        }else if(Gdx.input.isKeyPressed(Keys.A)){
            units.setVelocityX(-2f);
        }else{
            units.dampen();
        }
        
        
        
        units.update(deltaTime);
        
        //collisions
        // go through each block
//        for(Block b: theWorld.getBlocks()){
//            // if player is hitting a block
//            if(player.isColliding(b)){
//                // get overlapping amount
//                float overX = player.getOverlapX(b);
//                float overY = player.getOverlapY(b);
//                
//                //just fixing y if not moving
//                if(player.getVelocityX() == 0){
//                    // player is above the block
//                    if(player.getY() > b.getY()){
//                        player.addToPosition(0, overY);
//                        player.setState(Mario.State.STANDING);
//                    }else{
//                        player.addToPosition(0, -overY);
//                    }
//                    player.setVelocityY(0);
//                }else{
//                    // fix the smallest overlap
//                    if(overX < overY){
//                        // left of the block
//                        if(player.getX() < b.getX()){
//                            player.addToPosition(-overX, 0);
//                        }else{
//                            player.addToPosition(overX, 0);
//                        }
//                    } else {
//                        // above the block
//                        if(player.getY() > b.getY()){
//                            player.addToPosition(0, overY);
//                            if(player.getState() == Mario.State.JUMPING){
//                                player.setState(Mario.State.STANDING);
//                            }
//                        }else{
//                            player.addToPosition(0, -overY);
//                        }
//                        player.setVelocityY(0);
//                    }
//                }
//            }
//        }
        // draw the screen
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
