package hu.mudm.icefield.model.item;

public class RocketPart extends Item {

    @Override
    public void onPickup(Character ch) {}

    @Override
    public Boolean isRocketPart(){
        return true;
    }

    @Override
    public Boolean isStoreable(){
        return true;
    }

}
