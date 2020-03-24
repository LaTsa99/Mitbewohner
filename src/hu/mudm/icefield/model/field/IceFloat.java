package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.Game;

import java.util.ArrayList;

public abstract class IceFloat {

    protected ArrayList<IceFloat> neighbour;
    protected ArrayList<Character> characters;
    protected Item item;
    protected Boolean iglu; // a dokumentációban nem szerepel, hogy milyen típus
    protected int snowLevel;
    protected int capacity;

    public IceFloat(){}

    public abstract Boolean stepOn(Character ch, IceFloat prev);

    public void removeCharacter(Character ch){}

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
