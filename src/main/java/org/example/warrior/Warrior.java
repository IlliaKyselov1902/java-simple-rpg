package org.example.warrior;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ability.CanAttack;
import org.example.ability.CanReceiveDamage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Warrior implements CanAttack, CanReceiveDamage {
    protected int health;

    public boolean isAlive() {
        return health > 0;
    }

    //Метод битви між двома воїнами, який лежить в основі методі для битви між арміями
    //Приймає посилання на опонента, і повертає bool: true, якщо виграв перший, false, якщо виграв другий
    public boolean fight(Warrior opponent) {
        while (this.isAlive() && opponent.isAlive()) {
            opponent.receiveDamage(this.hit());
            if (opponent.isAlive()) {
                receiveDamage(opponent.hit());
            }
        }
        return this.isAlive();
    }
}
