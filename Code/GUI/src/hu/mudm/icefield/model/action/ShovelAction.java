package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public class ShovelAction extends Action {

    public ShovelAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        IceFloat iceFloat = this.ch.getIceFloat();
        if(this.ch.canFastShovel()){
            iceFloat.removeSnow(2);
        }else{
            iceFloat.removeSnow(1);
        }
    }

}
