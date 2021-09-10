package com.ocbc.auctionservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private int id;
    private int hp;
    private int damage;
    private int damageDealt;
    private boolean alive;
    private String name;

    public void addDamageDealt() {
        damageDealt += damage;
    }

    public void takenDamage(int damage) {
        if (alive) {
            hp -= damage;
            if (hp < 0) {
                hp = 0;
                alive = false;
            }
        }
    }

    boolean dead() {
        return !alive;
    }

    public String getName(){
        return name + "-" + id;
    }
}
