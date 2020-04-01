package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class PickupAction extends Action {


    public PickupAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");

        GUI_skeleton.raiseTabCnt();
        IceFloat ice = ch.getIceFloat();

        GUI_skeleton.raiseTabCnt();
        Item item = ice.removeItem();
        GUI_skeleton.decreaseTabCnt();

        if(item!=null) {
            GUI_skeleton.raiseTabCnt();
            item.onPickup(ch);
            GUI_skeleton.decreaseTabCnt();
        }
    }
}
