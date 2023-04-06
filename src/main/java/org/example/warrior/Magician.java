package org.example.warrior;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Magician extends Warrior implements Serializable {
    private static final int MAX_HEALTH = 32;

    private static final int MAX_DAMAGE = 12;

    public Magician() {
        super(MAX_HEALTH);
    }
    @Override
    public int hit() {
        return MAX_DAMAGE - ((int)(Math.random() * 10) % 13);
    }

    @Override
    public void receiveDamage(int damage) {
        setHealth(this.health -= damage);
    }

    public static void printInfo() {
        System.out.println(
        """
        ------- Magician -------
         0    - Max Health:  32
        5|9   - Damage    :0-12 
         |    
        < > 
        """);
    }
}
