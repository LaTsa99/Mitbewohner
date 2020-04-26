package hu.mudm.icefield.model;

import hu.mudm.icefield.model.action.*;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI;
import hu.mudm.icefield.view.NoActionException;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private PolarBear p;
    private ArrayList<IceFloat> icefloats;
    private ArrayList<Character> characters;
    private Random r;
    private static boolean isWon;
    private static boolean isLost;
    private static int rocketPartsCnt = 0;

    private GUI gui; //még meg kell kapnia

    public Controller(GUI gui, ArrayList<Character> characters, ArrayList<IceFloat> icefloats, PolarBear p) {
        this.gui= gui;
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
                    Action action = null;
                    try {
                        action = createAction(ch);
                    } catch (NoActionException e) {
                        //e.printStackTrace();
                        i=4; //ending the loop
                    }
                    action.performAction();
                }
            }
            p.Wake();
            snowstorm();
            for (IceFloat icefloat : icefloats) {
                icefloat.endTurn();
            }
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

    private Action createAction(Character ch) throws NoActionException {
        Action action=null;

        int actionIndex = gui.getAction(ch);
        Class<? extends Action> classofaction = ch.getActions().get(actionIndex);

        Constructor<? extends Action>[] constructors = (Constructor<? extends Action>[]) classofaction.getConstructors();
        Constructor<? extends Action> constructor = constructors[0];

        try {
            if(constructors[0].getParameterCount()>1)
            {
                int neighborIndex = gui.getChosenNeighborID(ch.getIceFloat());
                action = constructor.newInstance(icefloats.get(neighborIndex));
            }
            else {
                action = constructor.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return action;
    }

    public boolean checkWinningStatus(){
        //the same with the "canWin" method in documentation
        if(rocketPartsCnt < 3) return false;

        Character ch = characters.get(0);
        IceFloat iceFloat = ch.getIceFloat();
        return iceFloat.playersHere() == characters.size();
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

    public static void Win() {
        isWon = true;
        isLost = false;
    }

    public static void Lose() {
        isLost = true;
        isWon = false;
    }
}
