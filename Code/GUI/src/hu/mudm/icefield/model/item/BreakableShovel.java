package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

//Falls ein Character diesen Gegenstand enthält, dann kann er 2 Schnee mit einem Action
//schaufeln. Dieser Gegenstand geht nach 3 Benutzung kaputt.
public class BreakableShovel extends Shovel {
    private int uses = 3;

    @Override
    public void onUse(Character ch) {
        uses--;
        if (uses <= 0) {
            ch.removeItem(this);
        }
    }
}
