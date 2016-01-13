/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

/**
 * Creates immobile turrets on top of player bases, that will shoot approaching enemy units. 
 * @author besem4079
 */
public class Turret extends Entity{
    
    private String playerName;
    private String unitName;
    private int cost;
    private int attackSpeed;
    private int attackDamage;
    
    public enum State{
        IDLE, ATTACKING
    }
    
    private State state;
    private float stateTime;
    
    public Turret(float x, float y, float width, float height, String playerName){
        super(x,y,width,height);
        state = state.IDLE;
        this.playerName = playerName;
    }  
       
    public void update(float delta){
        if(playerName.contains("p1")){
            if(state == State.IDLE){
                
            }
        }
    }
    
    public void setState(State s){
        if(state != s){
            stateTime = 0;
        }
        state = s;
    }
    
}
