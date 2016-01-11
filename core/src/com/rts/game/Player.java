/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.game;

import com.badlogic.gdx.utils.Array;
import com.rts.model.Entity;
import com.rts.model.Turret;
import com.rts.model.Unit;

/**
 *
 * @author donet6376
 */
public class Player {

    private final float COOLDOWN = 2;
    private String name;
    private int coins = 450;
    private int baseTotalHealth = 5000;
    private int baseRemainingHealth = 5000;
    private int maxUnits = 5;
    private int currentUnits = 0;
    private int maxTurrets = 1;
    private int currentTurrets = 0;
    private Array<Unit> units;
    private Array<Turret> turrets;
    private float spawnTime = 2;

    public Player(int round, String name) {
        this.name = name;
        coins = coins + (round * 50);
        units = new Array<Unit>();
        turrets = new Array<Turret>();
    }

    public int getCoins() {
        return coins;
    }

    public void updateCoins(int increase) {
        coins = coins + increase;
    }

    public void upgradeBaseHealth() {
        baseRemainingHealth = baseRemainingHealth + 1000;
    }

    public void createUnit(String p) {
        if (currentUnits < maxUnits) {
            if (spawnTime >= COOLDOWN) {
                if (p.contains("p1")) {
                    units.add(new Unit(0, 16, 32, 32, "p1"));
                } else if (p.contains("p2")) {
                    units.add(new Unit(768, 16, 32, 32, "p2"));
                }
                spawnTime = 0;
                currentUnits++;
            }
        }
    }
    
    public void createTurret(String p){
        if(currentTurrets < maxTurrets){
            if(p.contains("p1")){
                turrets.add(new Turret(48, 96, 32,32, "p1"));
            }else if(p.contains("p2")){
                turrets.add(new Turret(720,96,32,32, "p1"));
            }
        }
    }

    public Array<Unit> getUnits() {
        return units;
    }
    
    public Array<Turret> getTurrets(){
        return turrets;
    }

    public String getName() {
        return name;
    }

    public void addToSpawnTime(float deltaTime) {
        spawnTime = spawnTime + deltaTime;
    }
}
