/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

import com.badlogic.gdx.math.Vector2;
import com.rts.game.Player;

/**
 * Creates units that will advance towards the opposing base, and will attack 
 * enemy units or the enemy base if either are in range.
 * @author lamonta
 */
public class Unit extends Entity {

    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 4.0f;
    private final float DAMP = 0.8f;
    private Player player;
    private int cost;
    private int health;
    private int attackSpeed;
    private int attackDamage;
    private int spawnTime;
    private int dollarWorth;
    private float attackTimer;
    private float damageStateTimer = 0;

    // states for units
    public enum State {

        STANDING, MOVING, DAMAGE, WAITING
    }
    // the actual state the unit is in
    private State state;
    // movement variables
    private Vector2 velocity;
    private Vector2 acceleration;
    // animation state counter
    private float stateTime;

    public Unit(float x, float y, float width, float height, Player p, int cost,
            int dollarWorth, int health, int attackDamage, int attackSpeed, int spawnTime) {

        super(x, y, width, height);
        state = State.MOVING;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        stateTime = 0;

        player = p;
        this.cost = cost;
        this.dollarWorth = dollarWorth;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.spawnTime = spawnTime;
        attackTimer = attackSpeed;
    }

    public void dampen() {
        velocity.x = velocity.x * DAMP;
    }

    public void update(float delta) {

        if (state == State.DAMAGE) {

            if (damageStateTimer >= 0.2) {
                this.setState(State.STANDING);

            }
        }

        //STATES
        if (player.getName().equals("p1")) {
            if (state == State.MOVING) {
                if (cost == 75) {
                    this.setVelocityX(2.5f);
                } else if (cost == 150) {
                    this.setVelocityX(1.5f);
                } else {
                    this.setVelocityX(1f);
                }
            } else {
                this.setVelocityX(0f);
            }

        } else if (player.getName().equals("p2")) {
            if (state == State.MOVING) {
                if (cost == 75) {
                    this.setVelocityX(-2.5f);
                } else if (cost == 150) {
                    this.setVelocityX(-1.5f);
                } else {
                    this.setVelocityX(-1f);
                }
            } else {
                this.setVelocityX(0f);
            }
        }


        velocity.mulAdd(acceleration, delta);

        if (velocity.x < 0.01f && velocity.x > -0.01f) {
            velocity.x = 0;
        }
        addToPosition(velocity.x, velocity.y);





        attackTimer += delta;
        stateTime += delta;
        damageStateTimer += delta;
    }

    public void attack(Unit u) {
        if (attackTimer >= attackSpeed) {
            u.health = u.health - attackDamage;
            System.out.println("Health is " + u.health);
            attackTimer = 0;
            u.state = State.DAMAGE;
            u.damageStateTimer = 0;
        }
    }

    public void attackBase(Base b) {
        if (attackTimer >= attackSpeed) {
            b.removeHealth(attackDamage);
            attackTimer = 0;
            b.setBaseState(Base.baseState.DAMAGE);
            b.damageStateTimer = 0;
        }
    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }

    public void setVelocityY(float y) {
        velocity.y = y;
    }

    public void setState(State s) {
        if (state != s) {
            stateTime = 0;
        }
        state = s;

    }

    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public State getState() {
        return state;
    }

    public float getStateTime() {
        return stateTime;
    }

    public Player getPlayer() {
        return player;
    }

    public int getHealth() {
        return health;
    }

    public float getDamageStateTimer() {
        return damageStateTimer;
    }
    
    public int getDollarWorth(){
        return dollarWorth;
    }
}
