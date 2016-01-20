/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author lamonta
 */
public class AssetManager {
    
    private Array<Texture> textures;
    
    //the color of the team units
    public static Texture p1color;
    public static Texture p2color;
    
    public static Texture grass = new Texture("grass.png");
    public static Texture boxUnitBlue = new Texture("BoxUnitBlue.png");
    public static Texture boxUnitRed = new Texture("BoxUnitRed.png");
    public static Texture boxUnitLime = new Texture("BoxUnitLime.png");
    public static Texture baseBlue = new Texture("BaseBlue.png");
    public static Texture baseRed = new Texture("BaseRed.png");
    public static Texture baseLime = new Texture("BaseLime.png");
    public static Texture unitDamage = new Texture("UnitDamage.png");
    public static Texture base1Damage = new Texture("Base1Damage.png");
    public static Texture base2Damage = new Texture("Base2Damage.png");
    public static Texture health = new Texture("Health.png");
    public static Texture cooldown = new Texture("Cooldown.png");
    //public static Texture GUI = new Texture("GUI.png");
    //public static Texture GUICoolDown = new Texture("GUICoolDown.png");
    
    public void setColor(String player){
        
    }
}
