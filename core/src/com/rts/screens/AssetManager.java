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
    
    private static Array<Texture> unitTextures;
    private static Array<Texture> baseTextures;
    private static int arrayPosP1 = 0;
    private static int arrayPosP2 = 1;
    
    //the color of the team units
    public static Texture p1UnitColor;
    public static Texture p1BaseColor;
    public static Texture p2UnitColor;
    public static Texture p2BaseColor;
    
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
    public static Texture GUI = new Texture("GUI.png");
    public static Texture GUICooldown = new Texture("GUICooldown.png");
    
    public static void load(){
        unitTextures = new Array<Texture>();
        baseTextures = new Array<Texture>();
        //adding unit colors to unit array
        unitTextures.add(boxUnitBlue);
        unitTextures.add(boxUnitRed);
        unitTextures.add(boxUnitLime);
        //adding base colors to the base array
        baseTextures.add(baseBlue);
        baseTextures.add(baseRed);
        baseTextures.add(baseLime);
        
        p1UnitColor = unitTextures.get(arrayPosP1);
        p1BaseColor = baseTextures.get(arrayPosP1);
        p2UnitColor = unitTextures.get(arrayPosP2);
        p2BaseColor = baseTextures.get(arrayPosP2);
    }
    
    public static void setColorRight(String player){
        if(player.contains("p1")){
            if(arrayPosP1 == unitTextures.size - 1){
                p1UnitColor = unitTextures.get(0);
                p1BaseColor = baseTextures.get(0);
                arrayPosP1 = 0;
            }else {
                p1UnitColor = unitTextures.get(arrayPosP1 + 1);
                p1BaseColor = baseTextures.get(arrayPosP1 + 1);
                arrayPosP1++;
            }
            
        }else if(player.contains("p2")){
            if(arrayPosP2 == unitTextures.size - 1){
                p2UnitColor = unitTextures.get(0);
                p2BaseColor = baseTextures.get(0);
                arrayPosP2 = 0;
            }else {
                p2UnitColor = unitTextures.get(arrayPosP2 + 1);
                p2BaseColor = baseTextures.get(arrayPosP2 + 1);
                arrayPosP2++;
            }
        }
    }
    
    public static void setColorLeft(String player){
        if(player.contains("p1")){
            if(arrayPosP1 == 0){
                p1UnitColor = unitTextures.get(unitTextures.size - 1);
                p1BaseColor = baseTextures.get(baseTextures.size - 1);
                arrayPosP1 = unitTextures.size - 1;
            }else {
                p1UnitColor = unitTextures.get(arrayPosP1 - 1);
                p1BaseColor = baseTextures.get(arrayPosP1 - 1);
                arrayPosP1--;
            }
            
        }else if(player.contains("p2")){
            if(arrayPosP2 == 0){
                p2UnitColor = unitTextures.get(unitTextures.size - 1);
                p2BaseColor = baseTextures.get(baseTextures.size - 1);
                arrayPosP2 = unitTextures.size - 1;
            }else {
                p2UnitColor = unitTextures.get(arrayPosP2 - 1);
                p2BaseColor = baseTextures.get(arrayPosP2 - 1);
                arrayPosP2--;
            }
        }
    }
    
    public static Texture getUnitColor(String player){
        if(player.contains("p1")){
            return p1UnitColor;
        }else{
            return p2UnitColor;
        }
    }
    
    public static Texture getBaseColor(String player){
        if(player.contains("p1")){
            return p1BaseColor;
        }else{
            return p2BaseColor;
        }
    }
    
}
