package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

public class Food extends Item {

    @Override
    public void onPickup(Character ch) {
        ch.modifyTemp(1);
    }

}
