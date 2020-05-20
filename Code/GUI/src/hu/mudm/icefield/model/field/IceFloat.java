package hu.mudm.icefield.model.field;

import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;

public abstract class IceFloat {
    protected ArrayList<IceFloat> neighbors;
    protected ArrayList<Character> characters;
    protected Item item;
    protected static int idCount = 0;
    protected boolean iglu; //a dokumentációban nem szerepel, hogy milyen típus
    protected int snowLevel;
    protected int capacity;

    protected String type;
    protected boolean tent;
    protected int id;

    public IceFloat() {
        characters = new ArrayList<Character>();
        neighbors = new ArrayList<IceFloat>();
        iglu = false;
        tent = false;
        id = idCount++;
    }

    public IceFloat(Item item){
        this();
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
        if(snowLevel<0) snowLevel= 0;
    }

    public void addSnow() {
        snowLevel++;

        if (iglu || tent) {
            iglu = false;
            tent = false; //habar a sator mindenkepp eltunik a kor vegen
        } else {
            if (characters != null) {
                for (Character ch : characters) {
                    ch.modifyTemp(-1);
                }
            }
        }
    }

    public Boolean isBearProof(){
        return iglu;
    }

    public void endRound() {
        tent = false;
    }

    public int getCapacity(){
        return capacity;
    }

    public void buildIgloo(){
        iglu = true;
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


    public Item getItem(){ return item;}

    public int getSnowLevel() { return snowLevel;}

    public void setItem(Item _item){
        item = _item;
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

    public int getID(){ return id;}

    public void setID(int _id){
        id = _id;
        idCount--;
    }

    public Boolean hasItem(){
        return item != null;
    }
    public boolean hasIglu() { return iglu;}
    public boolean hasTent() { return tent;}
}
