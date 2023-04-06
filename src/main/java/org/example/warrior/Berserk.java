package org.example.warrior;

import lombok.Getter;
import lombok.Setter;
import org.example.ability.CanRage;

import java.io.Serializable;

@Getter
@Setter
public class Berserk extends Warrior implements Serializable, CanRage {
    private boolean rageFlag = false;

    private static final int MAX_HEALTH = 44;

    private int damage = 5;

    //Increase the damage
    private static final int RAGE_EFFECT = 3;

    public Berserk() {
        super(MAX_HEALTH);
    }
    @Override
    public int hit() {
        return damage;
    }

    @Override
    public void rage() {
        setDamage(damage + RAGE_EFFECT);
    }

    @Override
    public void receiveDamage(int damage) {
        if (getHealth() <= MAX_HEALTH / 2 && !rageFlag) {
            rage();
            rageFlag = true;
        }
        setHealth(getHealth() - damage);
    }

    public static void printInfo() {
        System.out.println("""
         -------  Berserk  -------
         /0 /#    - Max Health : 44;
          |/      - Damage     :  5;
          |       - Rage Effect:  3;
         / >
        """);
    }
}
