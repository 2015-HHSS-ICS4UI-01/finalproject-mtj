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
 * The player; each player has a base, a set of units, coins, and a cooldown
 * associated with spawning each unit.
 * @author donet6376
 */
public class Player {

    private float COOLDOWN = 2;
    private String name;
    private int coins = 500;
    private int baseTotalHealth = 5000;
    private int baseRemainingHealth = 5000;
    private int maxUnits = 5;
    private int currentUnits = 0;
    private int maxTurrets = 1;
    private int currentTurrets = 0;
    private Array<Unit> units;
    private Base base;
    private float unitSpawnTime = 2;

    /**
     * Constructor for the player
     * @param round the current round of the game
     * @param name the player's name; either player 1 or player 2
     */
    public Player(String name) {
        this.name = name;
        units = new Array<Unit>();
        if (name.contains("p1")) {
            base = new Base(0, 16, 80, 80, "p1", 1500);
        } else {
            base = new Base(720, 16, 80, 80, "p2", 1500);
        }
    }

    /**
     * Return the player's current coin amount
     * @return how many coins the player has
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Adds or removes coins from the player's current coins
     * @param increase how many coins to add or remove
     */
    public void updateCoins(int increase) {
        coins = coins + increase;
    }

    /**
     * Creates a new unit that belongs to the player.
     * @param width the width of the unit
     * @param height the height of the unit
     * @param p the player the unit belongs to (player 1 or player 2)
     * @param cost how much it costs to spawn the unit
     * @param dollarWorth how much the enemy player will receive if the unit dies
     * @param health how much health the unit currently has
     * @param attackDamage how much a unit can damage an enemy in one attack
     * @param attackSpeed how quickly a unit can attack an enemy
     * @param spawnTime how long the player must wait before spawning another unit
     */
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

    /**
     * Removes a dead unit belonging to the player
     * @param u the unit to remove
     * @param enemy the player that killed the unit
     */
    public void removeUnit(Unit u, Player enemy) {
        //give the other player coins
        enemy.updateCoins(u.getDollarWorth());
        //remove the unit
        for (int i = 0; i < units.size; i++) {
            if (units.get(i) == u && u.getDamageStateTimer() > 0.2) {
                units.removeIndex(i);
                currentUnits--;
                break;
            }
        }
    }

    /**
     * Returns all of the the player's units
     * @return the player's units
     */
    public Array<Unit> getUnits() {
        return units;
    }

    /**
     * Returns the player's base
     * @return the player's base
     */
    public Base getBase() {
        return base;
    }

    /**
     * Returns the player's name (player 1 or player 2)
     * @return the player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the remaining cooldown on spawning a new unit
     * @return the remaining cooldown
     */
    public float getRemainingCooldown(){
        return unitSpawnTime;
    }

    /**
     * Counts how long a player has waited since spawning a unit
     * @param deltaTime the cooldown
     */
    public void addToSpawnTime(float deltaTime) {
        unitSpawnTime = unitSpawnTime + deltaTime;
    }
    
    public void addToBaseCheck(float deltaTime){
        base.baseCheck(deltaTime);
    }

    /**
     * Returns the current position of a player's unit
     * @param u the unit
     * @return the unit's current position
     */
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
