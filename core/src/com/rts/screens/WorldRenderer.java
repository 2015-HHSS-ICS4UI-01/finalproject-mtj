/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.game.Player;
import com.rts.model.Base;
import com.rts.model.Turret;
import com.rts.model.Unit;
import com.rts.model.Unit.State;

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
    BitmapFont font;
    private Player p1;
    private Player p2;

    public WorldRenderer(Player p1, Player p2) {

        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        font = new BitmapFont();

        this.p1 = p1;
        this.p2 = p2;

        // move the x position of the camera
        camera.position.x = V_WIDTH / 2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT / 2f;
        // update the camera
        camera.update();

        // loads in the images
        
    }

    public void render(float delta) {
        // clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);

        // tells the renderer this is the list
        batch.begin();
        // list of things to draw

        // draw the ground
        batch.draw(AssetManager.ground, 0, 0, 800, 16);

        //draw player 1 units
        if (p1.getUnits() != null) {
            for (Unit u : p1.getUnits()) {
                if (u.getState() == State.DAMAGE) {
                    batch.draw(AssetManager.getUnitColor("p1"), u.getX(), u.getY(), u.getWidth(), u.getHeight());
                    batch.draw(AssetManager.unitDamage, u.getX(), u.getY(), u.getWidth(), u.getHeight());
                } else {
                    batch.draw(AssetManager.getUnitColor("p1"), u.getX(), u.getY(), u.getWidth(), u.getHeight());
                }
                if (u.getHealth() > 0) {
                    //drawing health
                    batch.draw(AssetManager.health, u.getX(), u.getY() + u.getHeight() + 5,
                            u.getWidth() * (u.getHealth() / u.getStartingHealth()), 5);
                    //drawing cooldown
                    if (u.getAttackTimer() < u.getAttackSpeed()) {
                        batch.draw(AssetManager.cooldown, u.getX(), u.getY() + u.getHeight() + 10,
                                u.getWidth() * (u.getAttackTimer() / u.getAttackSpeed()), 5);
                    } else {
                        batch.draw(AssetManager.cooldown, u.getX(), u.getY() + u.getHeight() + 10,
                                u.getWidth(), 5);
                    }
                }
            }
        }

        //draw player 2 units
        if (p2.getUnits() != null) {
            for (Unit u : p2.getUnits()) {
                if (u.getState() == State.DAMAGE) {
                    batch.draw(AssetManager.getUnitColor("p2"), u.getX(), u.getY(), u.getWidth(), u.getHeight());
                    batch.draw(AssetManager.unitDamage, u.getX(), u.getY(), u.getWidth(), u.getHeight());
                } else {
                    batch.draw(AssetManager.getUnitColor("p2"), u.getX(), u.getY(), u.getWidth(), u.getHeight());
                }
                if (u.getHealth() > 0) {
                    //drawing health
                    batch.draw(AssetManager.health, u.getX(), u.getY() + u.getHeight() + 5,
                            u.getWidth() * (u.getHealth() / u.getStartingHealth()), 5);
                    //drawing cooldown
                    if (u.getAttackTimer() < u.getAttackSpeed()) {
                        batch.draw(AssetManager.cooldown, u.getX(), u.getY() + u.getHeight() + 10,
                                u.getWidth() * (u.getAttackTimer() / u.getAttackSpeed()), 5);
                    } else {
                        batch.draw(AssetManager.cooldown, u.getX(), u.getY() + u.getHeight() + 10,
                                u.getWidth(), 5);
                    }

                }
            }
        }


        //P1 gui cooldown
        if (p1.getUnits() != null){
            //p1
            batch.draw(AssetManager.smallGUI,0,536,64,64);
            batch.draw(AssetManager.mediumGUI,65,536,64,64);
            batch.draw(AssetManager.largeGUI,130,536,64,64);
            for(Unit u : p1.getUnits()){
                if(u.getDollarWorth() == 100){
                    if(u.getPlayer().getRemainingCooldown() >= 0 && u.getPlayer().getRemainingCooldown() <= 2){
                        batch.draw(AssetManager.GUICooldown,0,536,64,64);
                        batch.draw(AssetManager.GUICooldown,65,536,64,64);
                        batch.draw(AssetManager.GUICooldown,130,536,64,64);                      
                    }else{
                        batch.draw(AssetManager.smallGUI,0,536,64,64);
                        batch.draw(AssetManager.mediumGUI,65,536,64,64);
                        batch.draw(AssetManager.largeGUI,130,536,64,64);
                    }
                }else if(u.getDollarWorth() == 175){
                    if(u.getPlayer().getRemainingCooldown() >=0 && u.getPlayer().getRemainingCooldown() <= 3){
                        batch.draw(AssetManager.GUICooldown,606,536,64,64);
                        batch.draw(AssetManager.GUICooldown,671,536,64,64);
                        batch.draw(AssetManager.GUICooldown,736,536,64,64);
                    }else {
                        batch.draw(AssetManager.smallGUI,0,536,64,64);
                        batch.draw(AssetManager.mediumGUI,65,536,64,64);
                        batch.draw(AssetManager.largeGUI,130,536,64,64);
                    } 
                }else if(u.getDollarWorth() == 350){
                    if(u.getPlayer().getRemainingCooldown() >=0 && u.getPlayer().getRemainingCooldown() <= 5){
                        batch.draw(AssetManager.GUICooldown,606,536,64,64);
                        batch.draw(AssetManager.GUICooldown,671,536,64,64);
                        batch.draw(AssetManager.GUICooldown,736,536,64,64);
                    }else {
                        batch.draw(AssetManager.smallGUI,0,536,64,64);
                        batch.draw(AssetManager.mediumGUI,65,536,64,64);
                        batch.draw(AssetManager.largeGUI,130,536,64,64);
                    } 
                }                   
            }
        }
            
        //P2 gui cooldown
        if (p2.getUnits() != null) {
            //p2
            batch.draw(AssetManager.smallGUI,606,536,64,64);
            batch.draw(AssetManager.mediumGUI,671,536,64,64);
            batch.draw(AssetManager.largeGUI,736,536,64,64);
            for (Unit u : p2.getUnits()) {
                if(u.getDollarWorth() == 100){
                    if(u.getPlayer().getRemainingCooldown() >= 0 && u.getPlayer().getRemainingCooldown() <= 2){    
                        batch.draw(AssetManager.GUICooldown,606,536,64,64);
                        batch.draw(AssetManager.GUICooldown,671,536,64,64);
                        batch.draw(AssetManager.GUICooldown,736,536,64,64);
                    }else {
                        batch.draw(AssetManager.smallGUI,606,536,64,64);
                        batch.draw(AssetManager.mediumGUI,671,536,64,64);
                        batch.draw(AssetManager.largeGUI,736,536,64,64);
                    }   
                }else if(u.getDollarWorth() == 175){
                    if(u.getPlayer().getRemainingCooldown() >=0 && u.getPlayer().getRemainingCooldown() <= 3){
                        batch.draw(AssetManager.GUICooldown,606,536,64,64);
                        batch.draw(AssetManager.GUICooldown,671,536,64,64);
                        batch.draw(AssetManager.GUICooldown,736,536,64,64);
                    }else {
                        batch.draw(AssetManager.smallGUI,606,536,64,64);
                        batch.draw(AssetManager.mediumGUI,671,536,64,64);
                        batch.draw(AssetManager.largeGUI,736,536,64,64);
                    } 
                }else if(u.getDollarWorth() == 350){
                    if(u.getPlayer().getRemainingCooldown() >=0 && u.getPlayer().getRemainingCooldown() <= 5){
                        batch.draw(AssetManager.GUICooldown,606,536,64,64);
                        batch.draw(AssetManager.GUICooldown,671,536,64,64);
                        batch.draw(AssetManager.GUICooldown,736,536,64,64);
                    }else {
                        batch.draw(AssetManager.smallGUI,606,536,64,64);
                        batch.draw(AssetManager.mediumGUI,671,536,64,64);
                        batch.draw(AssetManager.largeGUI,736,536,64,64);
                    } 
                }
            }

            // draw the bases
            if (p1.getBase().getState() == Base.baseState.DAMAGE) {
                batch.draw(AssetManager.getBaseColor("p1"), 0, 16, 80, 80);
                batch.draw(AssetManager.base1Damage, 0, 16, 80, 80);
            } else {
                batch.draw(AssetManager.getBaseColor("p1"), 0, 16, 80, 80);
            }
            if (p2.getBase().getState() == Base.baseState.DAMAGE) {
                batch.draw(AssetManager.getBaseColor("p2"), 720, 16, 80, 80);
                batch.draw(AssetManager.base2Damage, 720, 16, 80, 80);
            } else {
                batch.draw(AssetManager.getBaseColor("p2"), 720, 16, 80, 80);
            }

            // finished listing things to draw
            batch.end();
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
