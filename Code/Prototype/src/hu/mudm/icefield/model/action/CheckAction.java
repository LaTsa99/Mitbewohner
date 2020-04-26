package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public class CheckAction extends Action {

    public CheckAction(Character character, IceFloat ice ) {
        super(character);
        target = ice;
    }

    @Override
    public void performAction() {
        int capacity = this.target.getCapacity();
        System.out.println("Capacity of target ice-float: " + capacity);
    }

}
