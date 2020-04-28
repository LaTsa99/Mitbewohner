package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

public class PickupAction extends Action {


    public PickupAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        IceFloat ice = ch.getIceFloat();
        Item item = ice.removeItem();

        if(item!=null) {
            item.onPickup(ch);
        }
    }
}
