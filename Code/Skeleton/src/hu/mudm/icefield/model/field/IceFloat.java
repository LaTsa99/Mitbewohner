package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character; //ez kb mindenhova kelleni fog, kulonben azt hiszi hogy char-ra gondolunk :(
import hu.mudm.icefield.view.GUI_skeleton;

import java.util.ArrayList;

public abstract class IceFloat {

    protected ArrayList<IceFloat> neighbors;
    protected ArrayList<Character> characters;
    protected Item item;
    protected Boolean iglu; // a dokumentációban nem szerepel, hogy milyen típus
    protected int snowLevel;
    protected int capacity;

    public IceFloat(){
        characters = new ArrayList<Character>();
        neighbors = new ArrayList<IceFloat>();
    }

    public IceFloat(Item item){
        characters = new ArrayList<Character>();
        neighbors = new ArrayList<IceFloat>();
        this.item = item;
    }

    @Deprecated
    public abstract Boolean stepOn(Character ch, IceFloat prev);

    public abstract void stepOn(Character ch);

    public void removeCharacter(Character ch){
        GUI_skeleton.printlnWithTabs(this.getClass(),"removeCharacter(Character ch)");

        characters.remove(ch);
    }

    public void setNeighbors(IceFloat ice){
        if (!neighbors.contains(ice)){
            neighbors.add(ice);
            ice.setNeighbors(this);
        }
    }

    public void removeSnow(int layerCount){}

    public void addSnow(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"addsSnow()");
        snowLevel++;
        
        if(iglu) {
            iglu = false;            
        }
        else {
            for (Character ch: characters) {
                GUI_skeleton.raiseTabCnt();
                ch.modifyTemp(-1);
                GUI_skeleton.decreaseTabCnt();
            }
        }
    }

    public int getCapacity(){
        return capacity;
    }

    public void buildIgloo(){}

    public Item removeItem(){
        throw new UnsupportedOperationException();
    }

    public Boolean canRescueFromHere(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"canRescueFromHere()");
        boolean foundSavior = false;
        if (characters!=null){
            for (Character ch:characters){
                GUI_skeleton.raiseTabCnt();
                foundSavior = ch.canRescue();
                GUI_skeleton.decreaseTabCnt();

                if (foundSavior) break;
            }
        }
        return foundSavior;
    }

    public int playersHere(){
        return characters.size();
    }

}
