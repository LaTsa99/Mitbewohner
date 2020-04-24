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

        characters.remove(ch);
    }

    public void setNeighbor(IceFloat ice){
        if (!neighbors.contains(ice)){
            neighbors.add(ice);
            ice.setNeighbor(this);
        }
    }

    public void removeSnow(int layerCount){
        snowLevel = snowLevel - layerCount;
    }

    public void addSnow(){
        snowLevel++;
        
        if(iglu || tent) {
            iglu = false;
            tent = false; //habar a sator mindenkepp eltunik a kor vegen
        }
        else {
            for (Character ch: characters) {
                ch.modifyTemp(-1);
            }
        }
    }

    public Boolean isBearProof(){
        return iglu;
    }

    public void endTurn(){
        tent = false;
    }

    public int getCapacity(){
        return capacity;
    }

    public void buildIgloo(){

    }

    public Item removeItem(){
        Item item_returning = item;
        item = null;
        return item_returning;
    }

    public Boolean canRescueFromHere(){
        boolean foundSavior = false;
        if (characters!=null){
            for (Character ch:characters){
                foundSavior = ch.canRescue();
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
