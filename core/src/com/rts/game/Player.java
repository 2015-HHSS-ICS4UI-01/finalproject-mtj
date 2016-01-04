/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.game;

import com.badlogic.gdx.utils.Array;
import com.rts.model.Entity;

/**
 *
 * @author donet6376
 */
public class Player {
    
    private int coins = 450;
    private int exp = 0;
    private int baseTotalHealth = 5000;
    private int baseRemainingHealth = 5000;
    private int MAX_UNITS = 10;
    private Array<Entity> entities;
    
    public Player(int round){
           coins = coins + (round * 50);  
    }
    
    public int getCoins(){
        return coins;
    }
    
    public void updateCoins(int increase){
        coins = coins + increase;
    }
    
    public void updateExp(int increase){
        exp = exp + increase;
    }
    
    public void upgradeBaseHealth(){
        baseRemainingHealth = baseRemainingHealth + 1000;
    }
    
}
