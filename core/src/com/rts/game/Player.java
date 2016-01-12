/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.game;

import com.badlogic.gdx.utils.Array;
import com.rts.model.Entity;
import com.rts.model.Unit;

/**
 *
 * @author donet6376
 */
public class Player {

    private float COOLDOWN = 2;
    private String name;
    private int coins = 450;
    private int maxUnits = 5;
    private int currentUnits = 0;
    private Array<Unit> units;
    private Unit base;
    private float unitSpawnTime = 2;

    public Player(int round, String name) {
        this.name = name;
        coins = coins + (round * 50);
        units = new Array<Unit>();
        if(name.contains("p1")){
            base = new Unit(0,16,80,80,"b1",0,0,1500,0,0,0);
        }else{
            base = new Unit(720,16,80,80,"b2",0,0,1500,0,0,0);
        }
    }
    
    public int getCoins() {
        return coins;
    }

    public void updateCoins(int increase) {
        coins = coins + increase;
    }

    public void createUnit(int width, int height, String p, int cost, int dollarWorth, int health, 
            int attackDamage, int attackSpeed, int spawnTime) {
        if (currentUnits < maxUnits) {
            if (unitSpawnTime >= COOLDOWN) {
                if (p.contains("p1")) {
                    units.add(new Unit(0, 16, width, height, "p1", cost, dollarWorth, 
                            health, attackDamage, attackSpeed, spawnTime));
                } else if (p.contains("p2")) {
                    units.add(new Unit(768, 16, width, height, "p2", cost, dollarWorth, 
                            health, attackDamage, attackSpeed, spawnTime));
                }
                unitSpawnTime = 0;
                COOLDOWN = spawnTime;
                currentUnits++;
            }
        }
    }    
    
//    public void removeUnit(Unit u){
//        for(int i = 0; i < units.size; i++){
//            if(units<i> = u){
//                
//            }
//        }
//    }

    public Array<Unit> getUnits() {
        return units;
    }
    
    public Unit getBase(){
        return base;
    }

    public String getName() {
        return name;
    }

    public void addToSpawnTime(float deltaTime) {
        unitSpawnTime = unitSpawnTime + deltaTime;
    }
    
}
