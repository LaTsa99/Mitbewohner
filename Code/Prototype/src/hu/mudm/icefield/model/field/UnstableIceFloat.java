package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

public class UnstableIceFloat extends IceFloat {

    public UnstableIceFloat(int capacity) {
        this.capacity = capacity;
    }

    public UnstableIceFloat(int capacity, Item item) {
        this(capacity);
        this.item = item;
    }


    @Override
    public void stepOn(Character ch) {
        IceFloat prev = ch.getIceFloat();
        prev.removeCharacter(ch);
        characters.add(ch);
        ch.setPosition(this);

        if (playersHere() > capacity) {
            Controller.Lose();
        }
    }
}
