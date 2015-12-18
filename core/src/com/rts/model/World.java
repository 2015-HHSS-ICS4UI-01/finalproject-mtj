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
    
    private Mario player;
    
    public World(){
        
        
        demoLevel();
    }
    
    private void demoLevel(){
        player = new Mario(16,16,16,32);
        // blocks along the floor
        for(int i = 0; i < 50; i++){
            
        }
        
        
    }
    
    public void update(float delta){
        
    }
    
    public Mario getPlayer(){
        return player;
    }
    
    
}
