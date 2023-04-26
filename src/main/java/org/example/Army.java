package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.warrior.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Getter
@Setter
public class Army {
    private List<Warrior> troops = new ArrayList<>();

    public void addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(factory.get());
        }
    }

    public static Supplier<Warrior> resolveEnumeration(int number) {
        Supplier<Warrior> warrior = null;
        switch (number) {
            case 1 -> warrior = Knight::new;
            case 2 -> warrior = Defender::new;
            case 3 -> warrior = Magician::new;
            case 4 -> warrior = Vampire::new;
            case 5 -> warrior = Berserk::new;
        }
        return warrior;
    }

    public Warrior peekFirst() {
        return troops.isEmpty() ? null : troops.get(0);
    }

    public void removeFirst() {
        troops.remove(0);
    }
}

