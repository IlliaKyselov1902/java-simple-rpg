package org.example.warrior;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Defender extends Warrior implements Serializable {
    private static final int MAX_HEALTH = 60;

    private static final int DAMAGE = 3;

    private static int DEFENCE = 2;

    public Defender() {
        super(MAX_HEALTH);
    }
    @Override
    public int hit() {
        return DAMAGE;
    }

    @Override
    public void receiveDamage(int damage) {
        setHealth(this.health -= Math.max(damage - DEFENCE, 0));
    }

    public static void printInfo() {
        System.out.println(
        """
         ------- Defender -------
         /0  #  - Max Health: 60;
         =|= ## - Damage    :  3;
          |  #  - Defence   :  2;
         / >
        """);
    }
}
