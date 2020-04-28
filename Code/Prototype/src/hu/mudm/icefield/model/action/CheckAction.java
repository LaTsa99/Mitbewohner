package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public class CheckAction extends Action {
    private IceFloat target;
    private Controller c;

    public CheckAction(Character character, IceFloat ice, Controller c) {
        super(character);
        target = ice;
        this.c = c;
    }

    @Override
    public void performAction() {
        Integer capacity = this.target.getCapacity();

        switch (capacity) {
            case 0:
                c.showMessage("Target iceFloat is a hole");
                break;
            case -1:
                c.showMessage("Target iceFloat is stable");
                break;
            default:
                c.showMessage("The capacity of target icefloat is " + capacity.toString());
                break;
        }
    }

}
