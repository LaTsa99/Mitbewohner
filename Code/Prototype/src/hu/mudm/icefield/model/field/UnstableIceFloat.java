package hu.mudm.icefield.model.field;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

public class UnstableIceFloat extends IceFloat {

    private int capacity;

    public UnstableIceFloat(int _capacity, Item item) {
        super(item);
        capacity = _capacity;
    }

    public UnstableIceFloat(int _capacity) {
        capacity = _capacity;
    }

    @Override
    public void stepOn(Character ch) {
        IceFloat prev = ch.getIceFloat();
        prev.removeCharacter(ch);
        characters.add(ch);
        ch.setPosition(this);

        if (playersHere() > capacity) {
            Game.lose();
        }
    }
}
