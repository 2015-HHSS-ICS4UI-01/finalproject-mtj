/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author lamonta
 */
public class Unit extends Entity{
    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 4.0f;
    private final float DAMP = 0.8f;
    
    private String playerName;
    private String unitName;
    private int cost;
    private int health;
    private int attackSpeed;
    private int attackDamage;
    private int spawnTime;
    private int dollarWorth;
    
    // states for mario
    public enum State{
        STANDING, MOVING, ATTACKING
    }
    
    // the actual state mario is in
    private State state;
    // movement variables
    private Vector2 velocity;
    private Vector2 acceleration;
    
    // animation state counter
    private float stateTime;
    
    public Unit(float x, float y, float width, float height, String playerName){
        super(x,y,width,height);
        state = State.MOVING;
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,0);
        stateTime = 0;
        this.playerName = playerName;
    }
    
    
      public void dampen(){
        velocity.x = velocity.x*DAMP;
    }
    
    public void update(float delta){
        
        //STATES
        if(playerName.contains("p1")){
           if(state == State.STANDING){
            this.setVelocityX(0f);
        }else if(state == State.MOVING){
            this.setVelocityX(1f);
        }else{
            this.setVelocityX(0f);
        } 
           
        }else if(playerName.contains("p2")){
            if(state == State.STANDING){
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
    
    
    public void setVelocityX(float x){
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
    
}
