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
public class Base{
    
    private int health;
    private Array<Base> base;
    
    private Base(float x, float y, float width, float height) {
    }


    public Base(int round){
        base = new Array<Base>();
    }

    public void createBase() {
        base.add(new Base(0,0,0,0));
    }
    
    public Array<Base> getBase(){
        return base;
    }
    
}
