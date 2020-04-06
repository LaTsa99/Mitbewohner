package hu.mudm.icefield.model;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.field.IceFloat;

import java.util.Random;

public class PolarBear {
    protected IceFloat position;
    private Random r = new Random();

    PolarBear(IceFloat startingposition) {
        position = startingposition;
    }

    void Wake() {
        int neighborcount = position.getNeighbors().size();
        position = position.getNeighbors().get(r.nextInt(neighborcount));
        if(position.playersHere()>0)
            Game.lose();
    }
}
