package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

//Beim Aufnehmen dieses Gegenstandes erhöht sich die Körpertemperatur des Spielers um 1.
public class Food extends Item {

    @Override
    public void onPickup(Character ch) {
        ch.modifyTemp(1);
    }
}
