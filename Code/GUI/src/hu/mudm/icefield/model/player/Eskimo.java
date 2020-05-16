package hu.mudm.icefield.model.player;

import hu.mudm.icefield.model.action.BuildAction;
import hu.mudm.icefield.model.field.StableIceFloat;

public class Eskimo extends Character {

    public Eskimo(String name, StableIceFloat startPosition) {
        super(name, startPosition);
        addAction(BuildAction.class);
        modifyTemp(1);
    }
}
