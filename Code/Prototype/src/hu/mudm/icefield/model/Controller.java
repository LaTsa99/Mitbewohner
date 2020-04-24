package hu.mudm.icefield.model;

import hu.mudm.icefield.model.field.IceFloat;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private PolarBear p;
    private ArrayList<IceFloat> icefloats;
    private ArrayList<Character> characters;
    private Random r;

    public Controller(ArrayList<IceFloat> icefloats) {
        r = new Random();
        this.icefloats = new ArrayList(icefloats); //ez warningot dob
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

    private void checkWinningStatus(){}

    public void snowstorm() {
        if(icefloats!=null) {
            for (IceFloat ice: icefloats) {
                if(r.nextFloat() < 1.0f) {
                    ice.addSnow();
                }
            }
        }
    }
}
