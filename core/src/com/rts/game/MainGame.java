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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.model.Base;
import com.rts.model.Unit;
import com.rts.screens.WorldRenderer;

/**
 *
 * @author donet6376
 */
public class MainGame implements Screen {

    private final int smallUnitCost = 75;
    private final int mediumUnitCost = 150;
    private final int largeUnitCost = 300;
    private MyRTSGame manager;
    private WorldRenderer renderer;
    private int round;
    private Player p1;
    private Player p2;
    private Array<Unit> collisionCheck;

    public MainGame(MyRTSGame manager) {
        this.manager = manager;

        round = 1;
        p1 = new Player(round, "p1");
        p2 = new Player(round, "p2");
        renderer = new WorldRenderer(p1, p2);
        collisionCheck = new Array<Unit>();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {

        //spawns a small unit that costs 75 coins
        if (Gdx.input.isKeyJustPressed(Keys.A) && p1.getCoins() >= smallUnitCost) {
            p1.createUnit(16, 32, p1, 75, 100, 100, 50, 2, 2);
            //when unit is spawned, coins are deducted from player 1
            if (p1.getRemainingCooldown() == 0) {
                p1.updateCoins(-smallUnitCost);
                System.out.println("p1:" + p1.getCoins());
            }
        }
        //spawns a medium unit that costs 150 coins
        if (Gdx.input.isKeyJustPressed(Keys.S) && p1.getCoins() >= mediumUnitCost) {
            p1.createUnit(32, 48, p1, 150, 175, 150, 100, 3, 3);
            //when unit is spawned, coins are deducted from player 1
            if (p1.getRemainingCooldown() == 0) {
                p1.updateCoins(-mediumUnitCost);
                System.out.println("p1:" + p1.getCoins());
            }
        }
        //spawns a large unit that costs 300 coins
        if (Gdx.input.isKeyJustPressed(Keys.D) && p1.getCoins() >= largeUnitCost) {
            p1.createUnit(48, 64, p1, 300, 350, 300, 150, 5, 5);
            //when unit is spawned, coins are deducted from player 1
            if (p1.getRemainingCooldown() == 0) {
                p1.updateCoins(-largeUnitCost);
                System.out.println("p1:" + p1.getCoins());
            }
        }
        //spawns a small unit that costs 75 coins
        if (Gdx.input.isKeyJustPressed(Keys.J) && p2.getCoins() >= smallUnitCost) {
            p2.createUnit(16, 32, p2, 75, 100, 100, 50, 2, 2);
            //when unit is spawned, coins are deducted from player 2
            if (p2.getRemainingCooldown() == 0) {
                p2.updateCoins(-smallUnitCost);
                System.out.println("p2:" + p2.getCoins());
            }
        }
        //spawns a medium unit that costs 150 coins
        if (Gdx.input.isKeyJustPressed(Keys.K) && p2.getCoins() >= mediumUnitCost) {
            p2.createUnit(32, 48, p2, 150, 175, 150, 100, 3, 3);
            //when unit is spawned, coins are deducted from player 2
            if (p2.getRemainingCooldown() == 0) {
                p2.updateCoins(-mediumUnitCost);
                System.out.println("p2:" + p2.getCoins());
            }
        }
        //spawns a large unit that costs 300 coins
        if (Gdx.input.isKeyJustPressed(Keys.L) && p2.getCoins() >= largeUnitCost) {
            p2.createUnit(48, 64, p2, 300, 350, 300, 150, 5, 5);
            //when unit is spawned, coins are deducted from player 2
            if (p2.getRemainingCooldown() == 0) {
                p2.updateCoins(-largeUnitCost);
                System.out.println("p2:" + p2.getCoins());
            }
        }

        if (p1.getUnits() != null) {
            for (Unit u : p1.getUnits()) {
                u.update(deltaTime);
            }
        }


        if (p2.getUnits() != null) {
            for (Unit u : p2.getUnits()) {
                u.update(deltaTime);
            }
        }
        //collisions
        // go through each unit
        // units colliding with each other
        if (p1.getUnits() != null && p2.getUnits() != null) {

            collisionCheck.clear();
            collisionCheck.addAll(p1.getUnits());
            collisionCheck.addAll(p2.getUnits());
            for (int i = 0; i < collisionCheck.size; i++) {

                Unit u1 = collisionCheck.get(i);
                if (u1.getState() != Unit.State.DAMAGE) {

                    //if a unit has died
                    if (u1.getHealth() <= 0) {
                        //if unit was player 1's, player 2 earns coins
                        if (u1.getPlayer().getName().equals("p1")) {
                            u1.getPlayer().removeUnit(u1, p2);
                        } //if unit was player 2's, player 1 earns coins
                        else if (u1.getPlayer().getName().equals("p2")) {
                            u1.getPlayer().removeUnit(u1, p1);
                        }
                        continue;
                    }

                    u1.setState(Unit.State.MOVING);
                    for (Unit u2 : collisionCheck) {
                        if (u1 != u2 && u1.isColliding(u2)) {


                            if (p1.getUnitPosition(u1) == 0 || p2.getUnitPosition(u1) == 0) {
                                if (!u1.getPlayer().getName().equals(u2.getPlayer().getName())) {
                                    u1.setState(Unit.State.STANDING);
                                }
                            } else if (u1.getPlayer().getName().equals(u2.getPlayer().getName())
                                    && (p1.getUnitPosition(u1) != -1 && p1.getUnitPosition(u1) > p1.getUnitPosition(u2)
                                    || p2.getUnitPosition(u1) != -1 && p2.getUnitPosition(u1) > p2.getUnitPosition(u2))) {
                                u1.setState(Unit.State.WAITING);
                            }


                            //damage
                            if (!u1.getPlayer().getName().equals(u2.getPlayer().getName())) {
                                u1.attack(u2);
                                u2.attack(u1);

                            }

                        }

                    }
                }
            }


            Base base1 = p1.getBase();
            //player 2 units colliding with player 1's base

            for (Unit u2 : p2.getUnits()) {
                if(u2.getState() != Unit.State.DAMAGE){
                   if (u2.isColliding(base1)) {
                    u2.setState(Unit.State.STANDING);
                    u2.attackBase(base1);
                } 
                }
                
            }


            Base base2 = p2.getBase();
            //player 1 units colliding with player 2's base

            for (Unit u1 : p1.getUnits()) {
                if(u1.getState() != Unit.State.DAMAGE){
                if (u1.isColliding(base2)) {
                    u1.setState(Unit.State.STANDING);
                    u1.attackBase(base2);
                }
            }
            }

        }

        p1.addToSpawnTime(deltaTime);
        p2.addToSpawnTime(deltaTime);
        p1.addToBaseCheck(deltaTime);
        p2.addToBaseCheck(deltaTime);
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
