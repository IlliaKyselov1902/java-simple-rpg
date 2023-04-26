package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.warrior.*;

@Getter
@Setter
public class Game {
    private Army army1 = new Army();
    private Army army2 = new Army();

     public boolean Battle() {
         while (!army1.getTroops().isEmpty() && !army2.getTroops().isEmpty()) {
             Warrior w1 = army1.peekFirst();
             Warrior w2 = army2.peekFirst();
             if (w1.fight(w2))
                  army2.removeFirst();
             else
                  army1.removeFirst();
         }
         return !army1.getTroops().isEmpty();
     }

    void displayStats(Army army) {
        System.out.println("Knights: " + army.getTroops().stream().filter(w -> w instanceof Knight).count());
        System.out.println("Defenders: " + army.getTroops().stream().filter(w -> w instanceof Defender).count());
        System.out.println("Magician: " + army.getTroops().stream().filter(w -> w instanceof Magician).count());
        System.out.println("Vampire: " + army.getTroops().stream().filter(w -> w instanceof Vampire).count());
        System.out.println("Berserk: " + army.getTroops().stream().filter(w -> w instanceof Berserk).count());
    }

    void restart() {//Очищуємо армії
        army1.getTroops().clear();
        army2.getTroops().clear();
        System.out.println("Game has been restarted!");
    }
}
