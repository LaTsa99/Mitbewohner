package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

public class Rope extends Item {

    @Override
    public void onPickup(Character ch) {
        ch.addItem(this);
    }

    @Override
    public Boolean canRescue(){
        return true;
    }

    @Override
    public Boolean isStoreable(){
        return true;
    }

}
