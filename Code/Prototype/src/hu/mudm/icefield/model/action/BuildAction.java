package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public class BuildAction extends Action {

    public BuildAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");

        GUI_skeleton.raiseTabCnt();
        IceFloat ice = ch.getIceFloat();
        GUI_skeleton.decreaseTabCnt();

        GUI_skeleton.raiseTabCnt();
        ice.buildIgloo();
        GUI_skeleton.decreaseTabCnt();
    }
}
