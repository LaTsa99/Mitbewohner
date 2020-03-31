package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.Hole;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class MoveAction extends Action {

    public MoveAction(Character character, IceFloat ice){
        super(character);
        target = ice;
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");

        GUI_skeleton.raiseTabCnt(); //Komolyan minden függvényhívás így fog kinézni?? Nagyon kéne ide egy lambda srácok
        target.stepOn(ch);
        GUI_skeleton.decreaseTabCnt();
    }

}
