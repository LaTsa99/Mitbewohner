package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.item.Tent;
import hu.mudm.icefield.model.player.Character;

public class BuildTentAction extends Action {
    public BuildTentAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        IceFloat ice = ch.getIceFloat();
        ice.buildTent();

        Tent t = new Tent();
        ch.removeItem(t);
    }
}
