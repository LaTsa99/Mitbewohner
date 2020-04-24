package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public class MoveAction extends Action {

    public MoveAction(Character character, IceFloat ice){
        super(character);
        target = ice;
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");

        GUI_skeleton.raiseTabCnt(); //Komolyan minden fuggvenyhivas igy fog kinezni?? Nagyon kene ide egy lambda sracok
        target.stepOn(ch);
        GUI_skeleton.decreaseTabCnt();
    }

}
