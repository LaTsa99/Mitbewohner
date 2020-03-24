package hu.mudm.icefield.model.player;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.item.Item;

import java.util.ArrayList;

public abstract class Character {

    protected IceFloat position;
    protected ArrayList<Item> items;
    protected ArrayList<Action> actions;

    private int temp;

    public void modifyTemp(int value) {}

    public void addAction(Action action){}

    public void removeAction(Action action){}

    public Character(){}

    public Boolean canSwim() {
        throw new UnsupportedOperationException();
    }

    public Boolean canRescue(){
        throw new UnsupportedOperationException();
    }

    public IceFloat getIceFloat(){
        return position;
    }

    public Boolean canFastShovel(){
        throw new UnsupportedOperationException();
    }

    public void addItem(Item it){}

    public void moveTo(IceFloat ice){}

}
