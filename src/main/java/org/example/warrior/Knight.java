package org.example.warrior;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Knight extends Warrior implements Serializable {
    private static final int MAX_HEALTH = 50;

    private static final int DAMAGE = 7;

    public Knight() {
        super(MAX_HEALTH);
    }

    @Override
    public int hit() {
        return DAMAGE;
    }

    @Override
    public void receiveDamage(int damage) {
        setHealth(this.health -= damage);
    }

    public static void printInfo() {
        System.out.println(
        """
        ------- Knight ------- 
         /    
        /0  /  - Max Health: 50
        =|=    - Damage    :  7
         |     
        / >       
        """);
    }
}
