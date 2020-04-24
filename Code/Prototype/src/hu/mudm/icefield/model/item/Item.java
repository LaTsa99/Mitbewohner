package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

//Das soll für einen abstrakten Gegenstand entsprechen, das die verschiedene Eigenschaften der
//einzelne Gegenstände beschreibt.
public abstract class Item {

    protected String name;

    public void onPickup(Character ch) {
        ch.addItem(this);
    }

    public void onUse(Character ch) {
    }

    public Boolean canSwim() {
        return false;
    }

    public Boolean canFastShovel() {
        return false;
    }

    public Boolean canRescue() {
        return false;
    }

    public Boolean canBuildAsTent(){
        return false;
    }

    public String getName(){
        return name;
    }
}
