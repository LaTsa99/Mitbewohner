package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

public class Hole extends IceFloat{

    public Hole() {}

    public Hole(Item item){super(item);}

    @Override
    public void stepOn(Character ch) {

        if (ch.canSwim()) return;
        for (IceFloat ice: neighbors){
            if (ice.canRescueFromHere()) {
                ice.stepOn(ch);
                return;
            }
        }
        IceFloat prev = ch.getIceFloat();
        prev.removeCharacter(ch);
        characters.add(ch);
        ch.setPosition(this);
        Controller.Lose();
    }
}
