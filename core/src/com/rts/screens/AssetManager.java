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
    
    public Array<Texture> unitTextures;
    public Array<Texture> baseTextures;
    private int arrayPosP1 = 0;
    private int arrayPosP2 = 1;
    
    //the color of the team units
    private Texture p1UnitColor;
    private Texture p1BaseColor;
    private Texture p2UnitColor;
    private Texture p2BaseColor;
    
    public Texture grass = new Texture("grass.png");
    public Texture boxUnitBlue = new Texture("BoxUnitBlue.png");
    public Texture boxUnitRed = new Texture("BoxUnitRed.png");
    public Texture boxUnitLime = new Texture("BoxUnitLime.png");
    public Texture baseBlue = new Texture("BaseBlue.png");
    public Texture baseRed = new Texture("BaseRed.png");
    public Texture baseLime = new Texture("BaseLime.png");
    public Texture unitDamage = new Texture("UnitDamage.png");
    public Texture base1Damage = new Texture("Base1Damage.png");
    public Texture base2Damage = new Texture("Base2Damage.png");
    public Texture health = new Texture("Health.png");
    public Texture cooldown = new Texture("Cooldown.png");
    public Texture GUI = new Texture("GUI.png");
    public Texture GUICooldown = new Texture("GUICooldown.png");
    
    public AssetManager(){
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
    
    public void setColorRight(String player){
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
    
    public void setColorLeft(String player){
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
    
    public Texture getUnitColor(String player){
        if(player.contains("p1")){
            return p1UnitColor;
        }else{
            return p2UnitColor;
        }
    }
    
    public Texture getBaseColor(String player){
        if(player.contains("p1")){
            return p1BaseColor;
        }else{
            return p2BaseColor;
        }
    }
    
}
