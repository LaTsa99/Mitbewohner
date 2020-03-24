package hu.mudm.icefield.model.action;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.field.IceFloat;

public abstract class Action {

    private Controller controller;

    protected Character ch;
    protected IceFloat target;

    public abstract void performAction();
}
