package hu.mudm.icefield.model.player;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.action.BuildTentAction;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.item.Item;

import java.util.ArrayList;

//Repräsentiert den Spieler im Spiel. Enthaltet die Arbeiten, die der Charakter leisten kann.
//Diese Klasse ermöglicht auch die Nachverfolgung und Änderung der Körpertemperatur des
//Charakters. Diese Klasse enthält auch die Gegenstände, die der Spieler aufgenommen hat, und
//hat einige Funktionen um zu bestimmen, ob der Charakter dazu fähig ist, bestimmte
//Tätigkeiten durchzuführen. Die Klasse ist abstrakt, es macht nur Sinn, eine konkrete
//Charakterentität, z.B. ein Eskimo zu instanzieren.
public abstract class Character {

    static int id = 0;

    protected IceFloat position;
    protected ArrayList<Item> items;
    protected ArrayList<Class<? extends Action>> actions;

    protected int temp;
    private String name;
    protected int actionsLeft;

    private int charId;

    public Character(String name, StableIceFloat startingposition) {
        this.name = name;
        startingposition.stepOn(this);
        items = new ArrayList<>();
        actions = new ArrayList<>();
        temp = 4;
        charId = id++;
    }

    public int getId(){
        return charId;
    }

    public void modifyTemp(int value) {
        temp += value;
    }

    public void addAction(Class<? extends Action> action) {
        if (!actions.contains(action)
                || action.equals(BuildTentAction.class)) {
            actions.add(action);
        }
    }

    public void removeAction(Class<? extends Action> action) {
        actions.remove(action);
    }

    public Boolean canSwim() {
        for (Item item : items) {
            if (item.canSwim()) {
                return true;
            }
        }
        return false;
    }

    public Boolean canRescue() {
        for (Item item : items) {
            if (item.canRescue()) {
                return true;
            }
        }
        return false;
    }

    public IceFloat getIceFloat() {
        return position;
    }

    public Boolean canFastShovel(){
        for(Item item : items){
            if(item.canFastShovel()) {
                item.onUse(this);
                return true;
            }
        }
        return false;
    }

    public void addItem(Item it) {
        items.add(it);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void setPosition(IceFloat ice) {
        if(position!=null) position.removeCharacter(this);
        position = ice;
    }

    public Boolean BuildTent() {
        for (Item item : getItems()) {
            if (item.canBuildAsTent()) {
                item.onUse(this);
                return true;
            }
        }
        return false;
    }

    public String getName(){
        return name;
    }

    public int getTemp(){
        return temp;
    }

    public IceFloat getPosition(){
        return position;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public void setTemp(int newtemp){
        if(temp > 0) temp = newtemp;
    }

    public ArrayList<Class<? extends Action>> getActions(){ return actions;}

    public void setActionsLeft (int i){actionsLeft = i;}

    public int getActionsLeft(){return actionsLeft;}
}
