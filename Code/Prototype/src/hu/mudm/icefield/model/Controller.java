package hu.mudm.icefield.model;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.action.BuildAction;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.view.GUI_skeleton;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private PolarBear p;
    private ArrayList<IceFloat> icefloats;
    private ArrayList<Character> characters;
    private Random r;
    private boolean isWon;
    private boolean isLost;

    public Controller(ArrayList<Character> characters, ArrayList<IceFloat> icefloats, PolarBear p) {
        r = new Random();
        isWon = false;
        isLost = false;
        this.p = p;
        this.characters = characters;
        this.icefloats = icefloats;
    }

    public void gameLoop() {
        //foreach character -> input, actions
        p.Wake();
        snowstorm();
        for (IceFloat icefloat : icefloats) {
            icefloat.endTurn();
        }
    }

    public void snowstorm() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"snowstorm()");

        if(icefloats!=null) {
            for (IceFloat ice: icefloats) {
                if(r.nextFloat() < 1.0f) {
                    GUI_skeleton.raiseTabCnt();
                    ice.addSnow();
                    GUI_skeleton.decreaseTabCnt();
                }
            }
        }
    }

    private void validateActions(Character ch) {/*TODO*/}

    //private Action createAction(int index, Object[] parameters) { }

    public boolean checkWinningStatus(){return true; /*TODO*/}

    public static void rocketPartPickedUp() {
        //do
    }
}
