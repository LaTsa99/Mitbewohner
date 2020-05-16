package hu.mudm.icefield.model.item;

//Falls ein Character diesen Gegenstand besitzt, dann kann er 2 Schnee mit einer Action
//schaufeln.
public class Shovel extends Item {

    @Override
    public Boolean canFastShovel() {
        return true;
    }
}
