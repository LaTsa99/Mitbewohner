package hu.mudm.icefield.model.item;

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

    public Boolean isStoreable(){
        return false;
    }

    public Boolean canRescue(){
        return false;
    }

}
