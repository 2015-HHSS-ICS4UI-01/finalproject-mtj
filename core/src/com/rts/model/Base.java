/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

import com.rts.game.Player;

/**
 *
 * @author donet6376
 */
public class Base extends Entity {
    
    private int health;
    private String playerName;
    
    public Base(float x, float y, float width, float height, String playerName, int health) {

        super(x, y, width, height);
        this.health = health;
        this.playerName = playerName;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void removeHealth(int decrease){
        health = health - decrease;
    }
    
}
