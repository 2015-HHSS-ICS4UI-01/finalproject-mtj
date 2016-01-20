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
        batch.draw(AssetManager.grass, 0, 0, 800, 40);

        //draw player 1 units
        if (p1.getUnits() != null) {
            for (Unit u : p1.getUnits()) {
                if (u.getState() == State.DAMAGE) {
                    batch.draw(AssetManager.boxUnitBlue, u.getX(), u.getY(), u.getWidth(), u.getHeight());
                    batch.draw(AssetManager.unitDamage, u.getX(), u.getY(), u.getWidth(), u.getHeight());
                } else {
                    batch.draw(AssetManager.boxUnitBlue, u.getX(), u.getY(), u.getWidth(), u.getHeight());
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
                    batch.draw(AssetManager.boxUnitRed, u.getX(), u.getY(), u.getWidth(), u.getHeight());
                    batch.draw(AssetManager.unitDamage, u.getX(), u.getY(), u.getWidth(), u.getHeight());
                } else {
                    batch.draw(AssetManager.boxUnitRed, u.getX(), u.getY(), u.getWidth(), u.getHeight());
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
                if(u.getPlayer().getRemainingCooldown() != 0){
                    batch.draw(AssetManager.GUICooldown,606,536,64,64);
                }else{
                    batch.draw(AssetManager.GUI,606,536,64,64);
            }
        }



        Rectangle GUI = new Rectangle(0, 536, 64, 64);

        //GUI
        //team blue
        batch.draw(AssetManager.GUI,0,536,64,64);
        batch.draw(AssetManager.GUI,65,536,64,64);
        batch.draw(AssetManager.GUI,130,536,64,64);

        //team red
        batch.draw(AssetManager.GUI,606,536,64,64);
        batch.draw(AssetManager.GUI,671,536,64,64);
        batch.draw(AssetManager.GUI,736,536,64,64);

        font.setColor(1f, 1f, 1f, 1f);
        font.draw(batch, "Coins: " + p1.getCoins(), 65, 472);
        font.draw(batch, "Coins: " + p2.getCoins(), 671, 472);

//        if(p1.getTurrets() != null){
//            for(Turret t: p1.getTurrets()){
//                batch.draw(AssetManager.boxUnitBlue, t.getX(), t.getY(), t.getWidth(), t.getHeight());
//            }
//        }
//        
//        if(p2.getTurrets() != null){
//            for(Turret t: p2.getTurrets()){
//                batch.draw(AssetManager.boxUnitRed, t.getX(), t.getY(), t.getWidth(), t.getHeight());
//            }
//        }

        // draw the bases
        if (p1.getBase().getState() == Base.baseState.DAMAGE) {
            batch.draw(AssetManager.baseBlue, 0, 16, 80, 80);
            batch.draw(AssetManager.base1Damage, 0, 16, 80, 80);
        } else {
            batch.draw(AssetManager.baseBlue, 0, 16, 80, 80);
        }
        if (p2.getBase().getState() == Base.baseState.DAMAGE) {
            batch.draw(AssetManager.baseRed, 720, 16, 80, 80);
            batch.draw(AssetManager.base2Damage, 720, 16, 80, 80);
        } else {
            batch.draw(AssetManager.baseRed, 720, 16, 80, 80);
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
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
