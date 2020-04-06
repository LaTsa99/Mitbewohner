package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

public class BreakableShovel extends Shovel {
    protected int uses = 3;
    @Override
    public void onUse(Character ch) {
        uses--;
        if(uses<=0) {
            ch.removeItem(this);
        }
    }
}
