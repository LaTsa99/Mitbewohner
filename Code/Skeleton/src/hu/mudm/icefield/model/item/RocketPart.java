package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

public class RocketPart extends Item {

    @Override
    public void onPickup(Character ch) {
        ch.addItem(this);
    }

    @Override
    public Boolean isRocketPart(){
        return true;
    }

    @Override
    public Boolean isStorable(){
        return true;
    }

}
