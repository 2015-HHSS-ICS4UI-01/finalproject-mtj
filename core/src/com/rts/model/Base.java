/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

/**
 *
 * @author donet6376
 */
public class Base extends Entity {
    
    private int health;
    private String playerName;
    private baseState baseState;
    public float damageStateTimer = 0;
    
    public enum baseState {

        NORMAL, DAMAGE
    }
    
    
    public Base(float x, float y, float width, float height, String playerName, int health) {

        super(x, y, width, height);
        this.health = health;
        this.playerName = playerName;
        baseState = baseState.NORMAL;
    }
    
    public int getHealth(){
        return health;
    }
    
    public baseState getState(){
        return baseState;
    }
    
    public void setBaseState(baseState baseState){
        this.baseState = baseState;
    }
    
    public void removeHealth(int decrease){
        health = health - decrease;
        System.out.println("health is " +health );
    }
    
    public void baseCheck(float deltaTime){
        damageStateTimer = damageStateTimer + deltaTime;
        if(baseState == baseState.DAMAGE){
            
            if(damageStateTimer >= 0.2){
                this.setBaseState(baseState.NORMAL);
                
            }
        }
    }
    
}
