package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character; //ez kb mindenhova kelleni fog, kulonben azt hiszi hogy char-ra gondolunk :(
import hu.mudm.icefield.view.GUI_skeleton;

import java.util.ArrayList;

public abstract class IceFloat {

    protected ArrayList<IceFloat> neighbour;
    protected ArrayList<Character> characters;
    protected Item item;
    protected Boolean iglu; // a dokumentációban nem szerepel, hogy milyen típus
    protected int snowLevel;
    protected int capacity;

    public IceFloat(){
        characters = new ArrayList<Character>();
    }

    public IceFloat(Item item){
        characters = new ArrayList<Character>();
        this.item = item;
    }

    @Deprecated
    public abstract Boolean stepOn(Character ch, IceFloat prev);

    public abstract void stepOn(Character ch);

    public void removeCharacter(Character ch){
        GUI_skeleton.printlnWithTabs(this.getClass(),"removeCharacter(Character ch)");

        characters.remove(ch);
    }

    public void removeSnow(int layerCount){}

    public void addSnow(){}

    public int getCapacity(){
        return capacity;
    }

    public void buildIgloo(){}

    public Item removeItem(){
        throw new UnsupportedOperationException();
    }

    public Boolean canRescueFromHere(){
        throw new UnsupportedOperationException();
    }

    public int playersHere(){
        return characters.size();
    }

}
