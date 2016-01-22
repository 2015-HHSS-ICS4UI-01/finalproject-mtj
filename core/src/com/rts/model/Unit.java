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
    private final float DAMP = 0.8f;
    private Player player;
    private int cost;
    private int health;
    private float startingHealth;
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

    /**
     * Constructor for a Unit.
     * @param x the unit's x position
     * @param y the unit's y position
     * @param width the width of the unit
     * @param height the height of the unit
     * @param p the player the unit belongs to
     * @param cost the cost of spawning the unit
     * @param dollarWorth the coin amount received by the enemy when the unit is killed
     * @param health the unit's current health
     * @param attackDamage the amount of health the unit can remove from an enemy in one hit
     * @param attackSpeed how quickly the unit can attack
     * @param spawnTime the amount of time a player must wait before spawning another unit
     */
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
        this.startingHealth = health;
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
                    this.setVelocityX(2f);
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
                    this.setVelocityX(-2f);
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

    /**
     * Attack loop for when unit is attacking another unit.
     * @param u the unit that being attacked.
     */
    public void attack(Unit u) {
        //the unit attacks the enemy unit if the time elapsed since its last attack
        //is greater than its attack speed
        if (attackTimer >= attackSpeed) {
            u.health = u.health - attackDamage;
            System.out.println("Health is " + u.health);
            attackTimer = 0;
            u.state = State.DAMAGE;
            u.damageStateTimer = 0;
        }
    }

    /**
     * Attack loop for when unit is attacking a base.
     * @param b the base that is being attacked.
     */
    public void attackBase(Base b) {
        //the unit attacks the enemy base if the time elapsed since its last attack
        //is greater than its attack speed
        if (attackTimer >= attackSpeed) {
            b.removeHealth(attackDamage);
            attackTimer = 0;
            b.setBaseState(Base.baseState.DAMAGE);
            b.damageStateTimer = 0;
        }
    }
    
    /**
     * Sets the movement speed of the unit.
     * @param x the speed at which the unit is moving
     */
    public void setVelocityX(float x) {
        velocity.x = x;
    }

    /**
     * Sets the current state of the unit
     * @param s the new state
     */
    public void setState(State s) {
        if (state != s) {
            stateTime = 0;
        }
        state = s;

    }

    /**
     * Returns the movement speed of the unit
     * @return the unit's movement speed
     */
    public float getVelocityX() {
        return velocity.x;
    }

    /**
     * Returns the current state of the unit
     * @return the unit's state
     */
    public State getState() {
        return state;
    }

    /**
     * Returns how long a unit has been in a state
     * @return the length of time spent in a state
     */
    public float getStateTime() {
        return stateTime;
    }

    /**
     * Returns the player that owns the unit
     * @return player 1 or player 2
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the current health of the unit
     * @return the current health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Returns the amount of health the unit started with
     * @return the unit's starting health
     */
    public float getStartingHealth(){
        return startingHealth;
    }

    /**
     * Returns how long the unit has been in the damage state
     * @return the length of time spent in the damage state
     */
    public float getDamageStateTimer() {
        return damageStateTimer;
    }
    
    /**
     * Return the number of coins it costs to spawn the unit
     * @return the unit's cost
     */
    public int getCost(){
        return cost;
    }
    
    /**
     * Returns the number of coins the unit grants an enemy when killed
     * @return how much the unit is worth
     */
    public int getDollarWorth(){
        return dollarWorth;
    }
    
    /**
     * Returns the time it takes for the unit to attack
     * @return how long the unit must wait to attack again
     */
    public int getAttackSpeed(){
        return attackSpeed;
    }
    
    /**
     * Returns the cooldown between the unit's attack
     * @return the length of time since an attack
     */
    public float getAttackTimer(){
        return attackTimer;
    }
}
