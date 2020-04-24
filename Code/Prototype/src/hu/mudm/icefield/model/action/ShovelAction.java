package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public class ShovelAction extends Action {

    public ShovelAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(), "performAction()");

        GUI_skeleton.raiseTabCnt();
        IceFloat iceFloat = this.ch.getIceFloat();
        if(this.ch.canFastShovel()){
            iceFloat.removeSnow(2);
        }else{
            iceFloat.removeSnow(1);
        }
        GUI_skeleton.decreaseTabCnt();
    }

}
