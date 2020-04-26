package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.item.Tent;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class BuildTentAction extends Action {
    public BuildTentAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");

        GUI_skeleton.raiseTabCnt();
        IceFloat ice = ch.getIceFloat();
        GUI_skeleton.decreaseTabCnt();

        GUI_skeleton.raiseTabCnt();
        ice.buildTent();
        GUI_skeleton.decreaseTabCnt();

        Tent t = new Tent();
        ch.removeItem(t);
    }
}