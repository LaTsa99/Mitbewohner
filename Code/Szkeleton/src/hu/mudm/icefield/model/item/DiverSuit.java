package hu.mudm.icefield.model.item;

public class DiverSuit extends Item {

    @Override
    public void onPickup(Character ch) {}

    @Override
    public Boolean canSwim(){
        return true;
    }

    @Override
    public Boolean isStoreable(){
        return true;
    }

}
