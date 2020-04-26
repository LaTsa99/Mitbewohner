package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

public class StableIceFloat extends IceFloat {

    public StableIceFloat() {type = "s";}
    public StableIceFloat(Item item) {type = "s"; }

    @Override
    public void stepOn(Character ch) {
        characters.add(ch);         //Character got accepted on icefloat
        ch.setPosition(this);
    }
}
