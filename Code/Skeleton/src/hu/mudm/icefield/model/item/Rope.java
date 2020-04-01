package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class Rope extends Item {

    @Override
    public void onPickup(Character ch) {
        GUI_skeleton.printlnWithTabs(this.getClass(),"onPickup(Character ch)");

        GUI_skeleton.raiseTabCnt();
        ch.addItem(this);
        GUI_skeleton.decreaseTabCnt();
    }

    @Override
    public Boolean canRescue(){
        return true;
    }

    @Override
    public Boolean isStorable(){
        return true;
    }

}
