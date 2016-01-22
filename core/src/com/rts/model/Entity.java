/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.model;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author lamonta
 */
public abstract class Entity {
    private Rectangle bounds;
    
    public Entity(float x, float y, float width, float height){
        bounds = new Rectangle(x,y,width,height);
    }
    
    public void addToPosition(float x, float y){
        bounds.x += x;
        bounds.y += y;
    }
    
    public float getX(){
        return bounds.x;
    }
    
    public float getY(){
        return bounds.y;
    }
    
    public float getWidth(){
        return bounds.width;
    }
    
    public float getHeight(){
        return bounds.height;
    }
    
    public boolean isColliding(Entity other){
        return bounds.overlaps(other.bounds);
    }
    

}
