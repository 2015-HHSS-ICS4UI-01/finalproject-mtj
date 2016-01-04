/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author lamonta
 */
public class World {
    
    private Unit player;
    
    public World(){
        
        
        demoLevel();
    }
    
    private void demoLevel(){
        player = new Unit(16,16,16,32);
        
    }
    
    public void update(float delta){
        
    }
    
    public Unit getPlayer(){
        return player;
    }
    
    
}
