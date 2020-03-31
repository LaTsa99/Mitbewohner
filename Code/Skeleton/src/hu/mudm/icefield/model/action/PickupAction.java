package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.item.Food;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

import hu.mudm.icefield.model.player.Eskimo;

import hu.mudm.icefield.model.player.Eskimo;

public class PickupAction extends Action {


    public PickupAction(Character character) {
        super(character);
    }

    public PickupAction(Eskimo e) {
        super(e);
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");
        Food food = new Food();

        GUI_skeleton.raiseTabCnt();
        ch.getIceFloat();
        GUI_skeleton.raiseTabCnt();
        target.removeItem();
        GUI_skeleton.raiseTabCnt();
        food.onPickup(ch);
        GUI_skeleton.decreaseTabCnt();
        GUI_skeleton.decreaseTabCnt();
        GUI_skeleton.decreaseTabCnt();

    }
}
