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
    private int exp;
    private int baseHealth;
    private int MAX_UNITS;
    private Array<Entity> entities;
    
    public Player(int round){
           coins = coins + (round * 50);  
    }
    
    public int getCoins(){
        return coins;
    }
    
    
}
