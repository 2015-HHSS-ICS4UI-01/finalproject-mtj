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

    private MyRTSGame manager;
    private WorldRenderer renderer;
    private int round;
    private Player p1;
    private Player p2;
    private Base b1;
    private Base b2;
    private Array<Unit> collisionCheck;

    public MainGame(MyRTSGame manager) {
        this.manager = manager;

        round = 1;
        p1 = new Player(round, "p1");
        p2 = new Player(round, "p2");
        b1 = new Base(round);
        b2 = new Base(round);
        renderer = new WorldRenderer(p1, p2);
        collisionCheck = new Array<Unit>();
       
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {

        //small unit
        if (Gdx.input.isKeyJustPressed(Keys.A)) {
            p1.createUnit("p1", 75, 100, 100, 50, 2, 2);
        }
        if (Gdx.input.isKeyJustPressed(Keys.S)) {
            p1.createUnit("p1", 150, 175, 150, 100, 3, 3);
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)) {
            p1.createUnit("p1", 300, 350, 300, 150, 5, 5);
        }

        if (Gdx.input.isKeyJustPressed(Keys.J)) {
            p2.createUnit("p2", 75, 100, 100, 50, 2, 2);
        }
        if (Gdx.input.isKeyJustPressed(Keys.K)) {
            p2.createUnit("p2", 150, 175, 150, 100, 3, 3);
        }
        if (Gdx.input.isKeyJustPressed(Keys.L)) {
            p2.createUnit("p2", 300, 350, 300, 150, 5, 5);
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
        collisionCheck.clear();
        collisionCheck.addAll(p1.getUnits());
        collisionCheck.addAll(p2.getUnits());
        // go through each block
        if (p1.getUnits() != null && p2.getUnits() != null) {
            for (int i = 0; i < collisionCheck.size; i++) {
                Unit u1 = collisionCheck.get(i);
                for (Unit u2 : collisionCheck) {
                    if (u1 != u2 && u1.isColliding(u2)) {
                        float overX = u1.getOverlapX(u2);
                        u1.setState(Unit.State.STANDING);
                        u2.setState(Unit.State.STANDING);
                    }
                }
            }
        }

        
        p1.addToSpawnTime(deltaTime);
        p2.addToSpawnTime(deltaTime);
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
