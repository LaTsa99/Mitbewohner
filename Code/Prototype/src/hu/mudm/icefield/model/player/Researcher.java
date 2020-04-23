package hu.mudm.icefield.model.player;

import hu.mudm.icefield.model.action.CheckAction;
import hu.mudm.icefield.model.field.StableIceFloat;

public class Researcher extends Character {

    public Researcher(String name, StableIceFloat startPosition) {
        super(name, startPosition);
        addAction(CheckAction.class);
    }

}
