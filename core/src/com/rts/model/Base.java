/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

/**
 * Creates a base that is the spawn point for units; if a player's base is depleted 
 * of its health, the opposing player wins the game.
 * @author donet6376
 */
public class Base extends Entity {
    
    private int health;
    private float startingHealth;
    private String playerName;
    private baseState baseState;
    public float damageStateTimer = 0;
    
    public enum baseState {

        NORMAL, DAMAGE
    }
    
    /**
     * Constructor for the Bases.
     * @param x the base's x position
     * @param y the base's y position
     * @param width the base's width
     * @param height the base's height
     * @param playerName the player the base belongs to
     * @param health the base's current health
     */
    public Base(float x, float y, float width, float height, String playerName, int health) {

        super(x, y, width, height);
        this.health = health;
        this.startingHealth = health;
        this.playerName = playerName;
        baseState = baseState.NORMAL;
    }
    
    /**
     * Returns the current health of a base.
     * @return the base's health
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * Returns the current state of a base.
     * @return the base's state
     */
    public baseState getState(){
        return baseState;
    }
    
    /**
     * Changes the base's current state.
     * @param baseState the new state
     */
    public void setBaseState(baseState baseState){
        this.baseState = baseState;
    }
    
    /**
     * Decreases the current health of the base when attacked.
     * @param decrease the amount to remove from the base's health
     */
    public void removeHealth(int decrease){
        health = health - decrease;
        System.out.println("health is " +health );
    }
    
    /**
     * Checks if the base is currently being attacked.
     * @param deltaTime how long the base has been in the damage state
     */
    public void baseCheck(float deltaTime){
        damageStateTimer = damageStateTimer + deltaTime;
        if(baseState == baseState.DAMAGE){
            
            if(damageStateTimer >= 0.2){
                this.setBaseState(baseState.NORMAL);
                
            }
        }
    }
    
}
