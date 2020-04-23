package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.action.BuildTentAction;
import hu.mudm.icefield.model.player.Character;

//Dieser Gegenstand kann benutzt werden, um ein Zelt auf einer Eisscholle zu bauen.
public class Tent extends Item {

    @Override
    public Boolean canBuildAsTent() {
        return true;
    }


    @Override
    public void onPickup(Character ch) {
        ch.addAction(BuildTentAction.class);
        super.onPickup(ch);
    }

    @Override
    public void onUse(Character ch) {
        ch.getIceFloat().buildTent();
        ch.removeItem(this);
    }
}
