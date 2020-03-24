package hu.mudm.icefield.model.item;

public class Rope extends Item {

    @Override
    public void onPickup(Character ch) {}

    @Override
    public Boolean canRescue(){
        return true;
    }

    @Override
    public Boolean isStoreable(){
        return true;
    }

}
