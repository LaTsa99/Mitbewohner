package hu.mudm.icefield.model;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.view.GUI_skeleton;

import java.util.ArrayList;

import java.util.Random;

public class Controller {

    private ArrayList<IceFloat> icefloats;
    private ArrayList<Character> characters;
    private Random r;

    public Controller() {
        r= new Random();
    }

    public void gameLoop(){}

    private void checkWinningStatus(){}

    public void snowstorm() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"snowstorm()");

        for (IceFloat ice: icefloats) {
            if(r.nextFloat() < 0.1f) {
                GUI_skeleton.raiseTabCnt();
                ice.addSnow();
                GUI_skeleton.decreaseTabCnt();
            }
        }
    }
}
