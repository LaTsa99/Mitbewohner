package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

public abstract class Action {

    private Controller controller;

    protected hu.mudm.icefield.model.player.Character ch;
    protected IceFloat target;

    public Action(Character character){
        this.ch = character;
    }

    public abstract void performAction();
}
