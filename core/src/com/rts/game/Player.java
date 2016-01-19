/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.game;

import com.badlogic.gdx.utils.Array;
import com.rts.model.Base;
import com.rts.model.Entity;
import com.rts.model.Turret;
import com.rts.model.Unit;

/**
 * The player; each user has a base, a set of units, coins, and a cooldown
 * associated with spawning each unit.
 * @author donet6376
 */
public class Player {

    private float COOLDOWN = 2;
    private String name;
    private int coins = 450;
    private int baseTotalHealth = 5000;
    private int baseRemainingHealth = 5000;
    private int maxUnits = 5;
    private int currentUnits = 0;
    private int maxTurrets = 1;
    private int currentTurrets = 0;
    private Array<Unit> units;
    private Base base;
    private float unitSpawnTime = 2;

    public Player(int round, String name) {
        this.name = name;
        coins = coins + (round * 50);
        units = new Array<Unit>();
        if (name.contains("p1")) {
            base = new Base(0, 16, 80, 80, "p1", 1500);
        } else {
            base = new Base(720, 16, 80, 80, "p2", 1500);
        }
    }

    public int getCoins() {
        return coins;
    }

    public void updateCoins(int increase) {
        coins = coins + increase;
    }

    public void createUnit(int width, int height, Player p, int cost, int dollarWorth, int health,
            int attackDamage, int attackSpeed, int spawnTime) {
        if (currentUnits < maxUnits) {
            if (unitSpawnTime >= COOLDOWN) {
                if (p.getName().equals("p1")) {
                    units.add(new Unit(0, 16, width, height, p, cost, dollarWorth,
                            health, attackDamage, attackSpeed, spawnTime));
                } else if (p.getName().equals("p2")) {
                    units.add(new Unit(768, 16, width, height, p, cost, dollarWorth,
                            health, attackDamage, attackSpeed, spawnTime));
                }
                unitSpawnTime = 0;
                COOLDOWN = spawnTime;
                currentUnits++;
            }
        }
    }

    public void removeUnit(Unit u) {
        for (int i = 0; i < units.size; i++) {
            if (units.get(i) == u && u.getDamageStateTimer() > 0.2) {
                units.removeIndex(i);
                currentUnits--;
                break;
            }
        }
    }

    public Array<Unit> getUnits() {
        return units;
    }

    public Base getBase() {
        return base;
    }

    public String getName() {
        return name;
    }
    
    public float getRemainingCooldown(){
        return unitSpawnTime;
    }

    public void addToSpawnTime(float deltaTime) {
        unitSpawnTime = unitSpawnTime + deltaTime;
    }
    
    public void addToBaseCheck(float deltaTime){
        base.baseCheck(deltaTime);
    }

    public int getUnitPosition(Unit u) {
        for(int i = 0; i < this.getUnits().size; i++){
        if(units.get(i) == u){
            return i;
        }
        }
        //dead return
        return -1;
    }
    
    
}
