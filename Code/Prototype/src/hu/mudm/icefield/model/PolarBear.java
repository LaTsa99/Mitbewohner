package hu.mudm.icefield.model;

import hu.mudm.icefield.model.field.IceFloat;

import java.util.Random;

public class PolarBear {
    protected IceFloat position;
    private Random r = new Random();

    public PolarBear(IceFloat startingposition) {
        position = startingposition;
    }

    public void Wake() {
        int neighborcount = position.getNeighbors().size();
        position = position.getNeighbors().get(r.nextInt(neighborcount));
        if(position.playersHere()>0 && position.isBearProof()==false)
            Controller.Lose();
    }

    public IceFloat getPosition(){ return position;}
    public void setPosition(IceFloat _position){
        position = _position;
        if(position.playersHere()>0 && position.isBearProof()==false)
            Controller.Lose();
    }
}
