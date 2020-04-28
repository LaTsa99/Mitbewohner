package hu.mudm.icefield.model.item;

//Es soll ermöglichen, dass ein Spieler ein Fall in ein Loch überlebt.
public class DiverSuit extends Item {

    @Override
    public Boolean canSwim(){
        return true;
    }
}
