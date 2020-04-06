package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

public abstract class Item {

    public abstract void onPickup(Character ch);

    public Boolean canSwim(){
        return false;
    }

    public Boolean canFastShovel(){
        return false;
    }

    public Boolean isRocketPart(){
        return false;
    }

    public Boolean isStorable(){
        return false;
    }

    public Boolean canRescue(){
        return false;
    }

}
