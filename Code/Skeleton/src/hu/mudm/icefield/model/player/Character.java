package hu.mudm.icefield.model.player;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.view.GUI_skeleton;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Character {

    protected IceFloat position;
    protected ArrayList<Item> items;
    protected ArrayList<Action> actions;

    private int temp;

    public void modifyTemp(int value) {
        GUI_skeleton.printlnWithTabs(this.getClass(), "modifyTemp(int value)");
        temp+=value;
    }

    public void addAction(Action action){}

    public void removeAction(Action action){}

    public Character(StableIceFloat startPosition){position = startPosition;}

    public Boolean canSwim() {
        throw new UnsupportedOperationException();
    }

    public Boolean canRescue(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"canRescue()");
        System.out.println("Can this character save someone fallen into a hole? (Do they have a rope?)");
        try {
            return GUI_skeleton.chooseYesOrNo();
        } catch (GUI_skeleton.GUI_skeletonException | IOException e) {
            e.printStackTrace();
        }
        return false;   //Exception was thrown
    }

    public IceFloat getIceFloat(){
        return position;
    }

    public Boolean canFastShovel(){
        throw new UnsupportedOperationException();
    }

    public void addItem(Item it){}

    public void moveTo(IceFloat ice){
        GUI_skeleton.printlnWithTabs(this.getClass(),"moveTo(IceFloat ice)");

        GUI_skeleton.raiseTabCnt();
        position.removeCharacter(this);
        GUI_skeleton.decreaseTabCnt();

        position = ice;

    }

}
