package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class BuildAction extends Action {

    public BuildAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");

        GUI_skeleton.raiseTabCnt();
        ch.getIceFloat();
        GUI_skeleton.raiseTabCnt();
        target.buildIgloo();
        GUI_skeleton.decreaseTabCnt();
        GUI_skeleton.decreaseTabCnt();
    }
}
