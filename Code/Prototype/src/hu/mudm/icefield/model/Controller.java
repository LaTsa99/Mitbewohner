package hu.mudm.icefield.model;

import hu.mudm.icefield.model.action.*;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private PolarBear p;
    private ArrayList<IceFloat> icefloats;
    private ArrayList<Character> characters;
    private Random r;
    private boolean isWon;
    private boolean isLost;
    private static int rocketPartsCnt = 0;

    public Controller(ArrayList<Character> characters, ArrayList<IceFloat> icefloats, PolarBear p) {
        r = new Random();
        isWon = false;
        isLost = false;
        this.p = p;
        this.characters = characters;
        this.icefloats = icefloats;
    }

    public void gameLoop() {
        while(!isWon && !isLost) {
            for (Character ch : characters) {
                for (int i = 0; i < 4; i++) {
                    validateActions(ch);
                    //TODO
                    //action = createAction(ch)
                    //action.performAction()
                }
            }
        }
        p.Wake();
        snowstorm();
        for (IceFloat icefloat : icefloats) {
            icefloat.endTurn();
        }
    }

    public void snowstorm() {
        if(icefloats!=null) {
            for (IceFloat ice: icefloats) {
                if(r.nextFloat() < 0.5f) {
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

    private void validateActions(Character ch) {
        if (ch.getIceFloat().getSnowLevel() > 0) {
            ch.addAction(ShovelAction.class);
        }
        if (ch.getIceFloat().getNeighbors().size() > 0) {
            ch.addAction(MoveAction.class);
        }
        if (checkWinningStatus()) {
            ch.addAction(BuildRocketAction.class);
        }
        if (ch.getIceFloat().hasItem()){
            ch.addAction(PickupAction.class);
        }
    }

    private Action createAction(Character ch) {
            /*TODO*/
    }

    public boolean checkWinningStatus(){
        //the same with the "canWin" method in documentation
        if(rocketPartsCnt < 3) return false;

        Character ch = characters.get(0);
        IceFloat icf = ch.getIceFloat();
        return icf.playersHere() == characters.size();
    }

    public static void rocketPartPickedUp() {
        Controller.rocketPartsCnt++;
    }

    public boolean getisWon() {
        return isWon;
    }

    public boolean getisLost() {
        return isLost;
    }

    public void Win() {
        isWon = true;
        isLost = false;
    }

    public void Loose() {
        isLost = true;
        isWon = false;
    }
}
