package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class StableIceFloat extends IceFloat {

    @Override
    public void stepOn(Character ch) {
        GUI_skeleton.printlnWithTabs(this.getClass(),"stepOn(Character ch)");

        characters.add(ch);         //Character got accepted on icefloat

        GUI_skeleton.raiseTabCnt();
        ch.moveTo(this);
        GUI_skeleton.decreaseTabCnt();
    }

    @Override
    public Boolean stepOn(Character ch, IceFloat prev) {
        throw new UnsupportedOperationException();
    }
}
