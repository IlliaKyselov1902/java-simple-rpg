package org.example.warrior;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Vampire extends Warrior implements Serializable {
    private static final int MAX_HEALTH = 40;

    private static final int DAMAGE = 4;

    private static int VAMPIRISM = 2;

    public Vampire() {
        super(MAX_HEALTH);
    }

    @Override
    public int hit() {
        vampiring();
        return DAMAGE;
    }

    @Override
    public void receiveDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    private void vampiring() {
        setHealth(Math.min(getHealth() + VAMPIRISM, MAX_HEALTH));
    }

    public static void printInfo() {
        System.out.println(
        """
         ------- Vampire -------
         < 0 / - Max Health: 40 
           |   - Damage    :  4 
           |   - Vampirism :  2 
           / >                      
         
         """);
    }
}
