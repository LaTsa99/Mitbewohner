package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public class BuildAction extends Action {

    public BuildAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        IceFloat ice = ch.getIceFloat();
        ice.buildIgloo();
    }
}
