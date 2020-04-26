package hu.mudm.icefield.model;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.action.BuildAction;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.player.Character;
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
                if(r.nextFloat() < 0.5f) {
                    GUI_skeleton.raiseTabCnt();
                    ice.addSnow();
                    GUI_skeleton.decreaseTabCnt();
                }
            }
        }
    }

    private void validateActions(Character ch) {/*TODO*/}

    //private Action createAction(int index, Object[] parameters) { }

    public boolean checkWinningStatus(){
        //the same with the "canWin" method in documentation
        if(rocketPartsCnt < 3) return false;

        Character ch = characters.get(0);
        IceFloat icf = ch.getIceFloat();
        if(icf.playersHere() != characters.size()) return false;

        return true;
    }

    public static void rocketPartPickedUp() {
        Controller.rocketPartsCnt++;
    }
}
