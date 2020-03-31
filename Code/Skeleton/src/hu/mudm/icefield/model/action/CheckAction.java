package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class CheckAction extends Action {

    public CheckAction(Character character, IceFloat ice ) {
        super(character);
        target = ice;
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(), "performAction()");
        GUI_skeleton.raiseTabCnt();
        int capacity = this.target.getCapacity();
        GUI_skeleton.decreaseTabCnt();
    }

}
