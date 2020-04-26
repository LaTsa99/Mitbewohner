package hu.mudm.icefield.model.field;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

public class UnstableIceFloat extends IceFloat {

    private int capacity;

    public UnstableIceFloat(Item item) {
        type = "i";
    }

    public UnstableIceFloat(int _id) {
        type = "i";
    }

    @Override
    public void stepOn(Character ch) {
        //ezt kikommentelem, mert különben nem tudom megírni rendesen
        /*boolean retValue = false;//= GUI_skeleton.chooseYesOrNo();
        if (retValue) {
            characters.add(ch);
            ch.setPosition(this);
        } else {
            Game.lose();
        }*/

        IceFloat prev = ch.getIceFloat();
        prev.removeCharacter(ch);
        characters.add(ch);
        ch.setPosition(this);

        if (playersHere() > capacity){
            Game.lose();
        }
    }
}
