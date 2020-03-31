package hu.mudm.icefield;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.action.MoveAction;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.player.Eskimo;

class Main {
    public static void main(String[] args){
        //Test for 5.3.1
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()), new StableIceFloat());
        ma.performAction();

        //System.out.println("Hello");
    }
}