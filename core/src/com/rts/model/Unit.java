/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

import com.badlogic.gdx.math.Vector2;
import com.rts.game.Player;

/**
 *
 * @author lamonta
 */
public class Unit extends Entity{
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
    
    // states for mario
    public enum State {

        STANDING, MOVING, DAMAGE
    }
    
    // the actual state mario is in
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
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,0);
        stateTime = 0;

        player = p;
        this.cost = cost;
        this.dollarWorth = dollarWorth;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.spawnTime = spawnTime;
        damageTimer = attackSpeed;
    }
    
    
      public void dampen(){
        velocity.x = velocity.x*DAMP;
    }
    
    public void update(float delta){
        
        //STATES
        if (player.getName().equals("p1")) {
            if (state == State.STANDING) {
                this.setVelocityX(0f);
            } else if (state == State.MOVING) {
                this.setVelocityX(1f);
            } else {
                this.setVelocityX(0f);
            }

        } else if (player.getName().equals("p2")) {
            if (state == State.STANDING) {
                this.setVelocityX(0f);
            }else if(state == State.MOVING){
                this.setVelocityX(-1f);
            }else{
                this.setVelocityX(0f);
            }
        }
        
        
        velocity.mulAdd(acceleration, delta);
        
        if(velocity.x < 0.01f && velocity.x > -0.01f){
            velocity.x = 0;
        }
        addToPosition(velocity.x,velocity.y);
        
        
        
        
        stateTime += delta;
    }

    public void attack(Unit u, Base b, String isBase) {
        //attacking a base
        if(isBase.contains("yes")){
            b.removeHealth(attackDamage);
        }
        //attacking another unit
        if (damageTimer >= attackSpeed) {
            u.health = u.health - attackDamage;
            System.out.println("Health is " + u.health);
            damageTimer = 0;
            u.setState(State.DAMAGE);
        }

    }

    public void setVelocityX(float x) {
        velocity.x = x;
    }
    
    public void setVelocityY(float y){
        velocity.y = y;
    }
    
    public void setState(State s){
        if(state != s){
            stateTime = 0;
        }
        state = s;
    }
    
    public float getVelocityX(){
        return velocity.x;
    }
    
    public float getVelocityY(){
        return velocity.y;
    }
    
    public State getState(){
        return state;
    }
    
    public float getStateTime(){
        return stateTime;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public int getHealth(){
        return health;
    }
    
}
