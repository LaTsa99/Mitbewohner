package hu.mudm.icefield.model;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private PolarBear p;
    private ArrayList<IceFloat> icefloats;
    private ArrayList<Character> characters;
    private Random r;

    public Controller(ArrayList<IceFloat> icefloats) {
        r = new Random();
        //this.icefloats = new ArrayList(icefloats); //ez warningot dob
        this.icefloats = icefloats; //ez warningot dob
    }

    public static void rocketPartPickedUp() {
        //do
    }

    public void gameLoop() {
        //foreach character -> input, actions
        p.Wake();
        snowstorm();
        for (IceFloat icefloat : icefloats) {
            icefloat.endTurn();
        }
    }

    public boolean checkWinningStatus(){return true; /*TODO*/}

    public void snowstorm() {
        if(icefloats!=null) {
            for (IceFloat ice: icefloats) {
                if(r.nextFloat() < 1.0f) {
                    ice.addSnow();
                }
            }
        }
    }

    public ArrayList<IceFloat> getIcefloats() { return icefloats;}
    public ArrayList<Character> getCharacters() { return characters;}
    public PolarBear getPolarBear(){return p;}

    public void setIcefloats(ArrayList<IceFloat> newFloats){
        icefloats = newFloats;
    }

    public void setCharacters(ArrayList<Character> newCharacters){
        characters = newCharacters;
    }

    public void setPolarBear(PolarBear bear){
        p = bear;
    }

    private void validateActions(Character ch) {/*TODO*/}
}
