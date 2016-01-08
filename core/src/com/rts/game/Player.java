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

    private final float COOLDOWN = 2;
    private String name;
    private int coins = 450;
    private int exp = 0;
    private int baseTotalHealth = 5000;
    private int baseRemainingHealth = 5000;
    private int MAX_UNITS = 10;
    private Array<Unit> units;
    private float spawnTime = 2;

    public Player(int round, String name) {
        this.name = name;
        coins = coins + (round * 50);
        units = new Array<Unit>();
    }

    public int getCoins() {
        return coins;
    }

    public void updateCoins(int increase) {
        coins = coins + increase;
    }

    public void updateExp(int increase) {
        exp = exp + increase;
    }

    public void upgradeBaseHealth() {
        baseRemainingHealth = baseRemainingHealth + 1000;
    }

    public void createUnit(String p) {
        if (spawnTime >= COOLDOWN) {
            if (p.contains("p1")) {
                units.add(new Unit(0, 16, 32, 32, "p1"));
            } else if (p.contains("p2")) {
                units.add(new Unit(768, 16, 32, 32, "p2"));
            }
            spawnTime = 0;
        }

    }

    public Array<Unit> getUnits() {
        return units;
    }

    public String getName() {
        return name;
    }
    
    public void addToSpawnTime(float deltaTime){
        spawnTime = spawnTime + deltaTime;
        System.out.println(spawnTime);
    }
}
