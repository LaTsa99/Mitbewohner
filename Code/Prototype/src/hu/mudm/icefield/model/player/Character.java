package hu.mudm.icefield.model.player;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.field.IceFloat;
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

    public Character(IceFloat startPosition){
        position = startPosition;
        items = new ArrayList<>();
    }

    public Boolean canSwim() {
        GUI_skeleton.printlnWithTabs(this.getClass(), "canSwim()");
        for(Item item : items){
            GUI_skeleton.raiseTabCnt();
            if(item.canSwim()) {
                GUI_skeleton.decreaseTabCnt();
                return true;
            }
            GUI_skeleton.raiseTabCnt();
        }
        return false;
    }

    public Boolean canBuildTent(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"canBuildTent()");

        for (Item item: items) {
            if(item.canBuildAsTent()) {
                item.onUse(this);
                return true;
            }
        }
        return false;
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
        GUI_skeleton.printlnWithTabs(this.getClass(),"getIceFloat()");
        return position;
    }

    public Boolean canFastShovel(){
        GUI_skeleton.printlnWithTabs(this.getClass(), "canFastShovel()");
        for(Item item : items){
            GUI_skeleton.raiseTabCnt();
            if(item.canFastShovel()) {
                GUI_skeleton.decreaseTabCnt();
                return true;
            }
            GUI_skeleton.decreaseTabCnt();
        }
        return false;
    }

    public void addItem(Item it){
        GUI_skeleton.printlnWithTabs(this.getClass(), "addItem(Item it)");

        GUI_skeleton.raiseTabCnt();
        items.add(it);
        GUI_skeleton.decreaseTabCnt();
    }

    public void moveTo(IceFloat ice){
        GUI_skeleton.printlnWithTabs(this.getClass(),"moveTo(IceFloat ice)");

        GUI_skeleton.raiseTabCnt();
        position.removeCharacter(this);
        GUI_skeleton.decreaseTabCnt();

        position = ice;

    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
