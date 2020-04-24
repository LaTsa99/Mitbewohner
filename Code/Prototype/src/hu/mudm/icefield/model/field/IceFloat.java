package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character; //ez kb mindenhova kelleni fog, kulonben azt hiszi hogy char-ra gondolunk :(

import java.util.ArrayList;

public abstract class IceFloat {

    protected ArrayList<IceFloat> neighbors;
    protected ArrayList<Character> characters;
    protected Item item;
    protected Boolean iglu; // a dokumentációban nem szerepel, hogy milyen típus
    protected Boolean tent;
    protected int snowLevel;
    protected int capacity;

    public IceFloat(){
        characters = new ArrayList<Character>();
        neighbors = new ArrayList<IceFloat>();
        iglu = false;
    }

    public IceFloat(Item item){
        characters = new ArrayList<Character>();
        neighbors = new ArrayList<IceFloat>();
        iglu = false;
        this.item = item;
    }

    public abstract void stepOn(Character ch);

    public void removeCharacter(Character ch){
        GUI_skeleton.printlnWithTabs(this.getClass(),"removeCharacter(Character ch)");

        characters.remove(ch);
    }

    public void setNeighbor(IceFloat ice){
        GUI_skeleton.printlnWithTabs(this.getClass(),"setNeighbors(IceFloat ice)");
        if (!neighbors.contains(ice)){
            neighbors.add(ice);
            GUI_skeleton.raiseTabCnt();
            ice.setNeighbor(this);
            GUI_skeleton.decreaseTabCnt();
        }
    }

    public void removeSnow(int layerCount){
        GUI_skeleton.printlnWithTabs(this.getClass(), String.format("removeSnow(%d)",layerCount));
        snowLevel = snowLevel - layerCount;
    }

    public void addSnow(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"addsSnow()");
        snowLevel++;
        
        if(iglu || tent) {
            iglu = false;
            tent = false; //habar a sator mindenkepp eltunik a kor vegen
        }
        else {
            for (Character ch: characters) {
                GUI_skeleton.raiseTabCnt();
                ch.modifyTemp(-1);
                GUI_skeleton.decreaseTabCnt();
            }
        }
    }

    public Boolean isBearProof(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"isBearProof()");
        return iglu;
    }

    public void endTurn(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"endOfTurn()");

        tent = false;
    }

    public int getCapacity(){
        GUI_skeleton.printlnWithTabs(this.getClass(), "getCapacity()");
        return capacity;
    }

    public void buildIgloo(){
        GUI_skeleton.printlnWithTabs(this.getClass(), "buildIgloo()");
    }

    public Item removeItem(){
        GUI_skeleton.printlnWithTabs(this.getClass(),"removeItem()");
        Item item_returning = item;
        item = null;
        return item_returning;
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

    public ArrayList<IceFloat> getNeighbors() {
        return new ArrayList<>(neighbors);
    }

    public void buildTent() {
        tent = true;
    }
}
