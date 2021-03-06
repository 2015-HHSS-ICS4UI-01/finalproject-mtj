package com.rts.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.rts.model.Base;
import com.rts.model.Unit;
import com.rts.screens.WinScreen;
import com.rts.screens.WorldRenderer;

/**
 *
 * @author MTJ
 */
public class MainGame implements Screen {

    private final int smallUnitCost = 75;
    private final int mediumUnitCost = 150;
    private final int largeUnitCost = 300;
    private MyRTSGame manager;
    private WorldRenderer renderer;
    private Player p1;
    private Player p2;
    private Array<Unit> collisionCheck;

    public MainGame(MyRTSGame manager) {
        this.manager = manager;
        p1 = new Player("p1");
        p2 = new Player("p2");
        renderer = new WorldRenderer(p1, p2);
        collisionCheck = new Array<Unit>();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {

        //SPAWNING UNITS
        //player 1 spawns a small unit that costs 75 coins
        if (Gdx.input.isKeyJustPressed(Keys.A) && p1.getCoins() >= smallUnitCost) {
            p1.createUnit(16, 32, p1, 75, 100, 100, 50, 2, 2);
            //when unit is spawned, coins are deducted from player 1
            if (p1.getRemainingCooldown() == 0) {
                p1.updateCoins(-smallUnitCost);
            }
        }
        //player 1 spawns a medium unit that costs 150 coins
        if (Gdx.input.isKeyJustPressed(Keys.S) && p1.getCoins() >= mediumUnitCost) {
            p1.createUnit(32, 48, p1, 150, 175, 150, 100, 3, 3);
            //when unit is spawned, coins are deducted from player 1
            if (p1.getRemainingCooldown() == 0) {
                p1.updateCoins(-mediumUnitCost);
            }
        }
        //player 1 spawns a large unit that costs 300 coins
        if (Gdx.input.isKeyJustPressed(Keys.D) && p1.getCoins() >= largeUnitCost) {
            p1.createUnit(48, 64, p1, 300, 350, 300, 150, 3, 5);
            //when unit is spawned, coins are deducted from player 1
            if (p1.getRemainingCooldown() == 0) {
                p1.updateCoins(-largeUnitCost);
            }
        }
        //player 2 spawns a small unit that costs 75 coins
        if (Gdx.input.isKeyJustPressed(Keys.J) && p2.getCoins() >= smallUnitCost) {
            p2.createUnit(16, 32, p2, 75, 100, 100, 50, 2, 2);
            //when unit is spawned, coins are deducted from player 2
            if (p2.getRemainingCooldown() == 0) {
                p2.updateCoins(-smallUnitCost);
            }
        }
        //player 2 spawns a medium unit that costs 150 coins
        if (Gdx.input.isKeyJustPressed(Keys.K) && p2.getCoins() >= mediumUnitCost) {
            p2.createUnit(32, 48, p2, 150, 175, 150, 100, 3, 3);
            //when unit is spawned, coins are deducted from player 2
            if (p2.getRemainingCooldown() == 0) {
                p2.updateCoins(-mediumUnitCost);
            }
        }
        //player 2 spawns a large unit that costs 300 coins
        if (Gdx.input.isKeyJustPressed(Keys.L) && p2.getCoins() >= largeUnitCost) {
            p2.createUnit(48, 64, p2, 300, 350, 300, 150, 3, 5);
            //when unit is spawned, coins are deducted from player 2
            if (p2.getRemainingCooldown() == 0) {
                p2.updateCoins(-largeUnitCost);
            }
        }

        //update player 1's units
        if (p1.getUnits() != null) {
            for (Unit u : p1.getUnits()) {
                u.update(deltaTime);
            }
        }

        //update player 2's units
        if (p2.getUnits() != null) {
            for (Unit u : p2.getUnits()) {
                u.update(deltaTime);
            }
        }

        //COLLISIONS
        //check that there are units to collide
        if (p1.getUnits() != null && p2.getUnits() != null) {

            //fill the collision array with both player's units
            collisionCheck.clear();
            collisionCheck.addAll(p1.getUnits());
            collisionCheck.addAll(p2.getUnits());

            //check through every unit
            for (int i = 0; i < collisionCheck.size; i++) {
                Unit u1 = collisionCheck.get(i);

                //if the unit is not currently taking damage
                if (u1.getState() != Unit.State.DAMAGE) {

                    //if a unit has died, remove it
                    if (u1.getHealth() <= 0) {
                        //if unit was player 1's, player 2 earns coins
                        if (u1.getPlayer().getName().equals("p1")) {
                            u1.getPlayer().removeUnit(u1, p2);
                        } //if unit was player 2's, player 1 earns coins
                        else if (u1.getPlayer().getName().equals("p2")) {
                            u1.getPlayer().removeUnit(u1, p1);
                        }
                        //move onto the next unit
                        continue;
                    }

                    //Assume the unit's state is moving unless found otherwise
                    u1.setState(Unit.State.MOVING);
                    //check if it is colliding with every other unit
                    for (Unit u2 : collisionCheck) {
                        if (u1 != u2 && u1.isColliding(u2)) {

                            //the unit is the front unit for their team
                            if (p1.getUnitPosition(u1) == 0 || p2.getUnitPosition(u1) == 0) {
                                //the two units are on the opposite team
                                if (!u1.getPlayer().getName().equals(u2.getPlayer().getName())) {
                                    //sets the state of the front unit
                                    u1.setState(Unit.State.STANDING);
                                }
                                //the unit is colliding with a teammate and not the front unit
                            } else if (u1.getPlayer().getName().equals(u2.getPlayer().getName())
                                    && (p1.getUnitPosition(u1) != -1 && p1.getUnitPosition(u1) > p1.getUnitPosition(u2)
                                    || p2.getUnitPosition(u1) != -1 && p2.getUnitPosition(u1) > p2.getUnitPosition(u2))) {
                                //sets the state of the unit behind a teammate
                                u1.setState(Unit.State.WAITING);
                            }

                            //each unit attacks
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
                if (u2.getState() != Unit.State.DAMAGE) {
                    if (u2.isColliding(base1)) {
                        u2.setState(Unit.State.STANDING);
                        u2.attackBase(base1);
                    }
                }

            }

            //if player 1's base is destroyed
            //player 2 is announced the winner
            if (base1.getHealth() <= 0) {
                manager.changeScreen(new WinScreen(manager, "p2"));
            }

            Base base2 = p2.getBase();
            //player 1 units colliding with player 2's base

            for (Unit u1 : p1.getUnits()) {
                if (u1.getState() != Unit.State.DAMAGE) {
                    if (u1.isColliding(base2)) {
                        u1.setState(Unit.State.STANDING);
                        u1.attackBase(base2);
                    }
                }
            }

            //if player 2's base is destroyed
            //player 1 is announced the winner
            if (base2.getHealth() <= 0) {
                manager.changeScreen(new WinScreen(manager, "p1"));
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
