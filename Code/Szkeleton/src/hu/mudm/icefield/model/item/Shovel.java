package hu.mudm.icefield.model.item;

public class Shovel extends Item {

    @Override
    public void onPickup(Character ch) {}

    @Override
    public Boolean canFastShovel(){
        return true;
    }

    @Override
    public Boolean isStoreable(){
        return true;
    }

}
