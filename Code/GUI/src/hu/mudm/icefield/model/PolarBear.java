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
        setPosition(position.getNeighbors().get(r.nextInt(neighborcount)));
    }

    public IceFloat getPosition(){ return position;}
    public void setPosition(IceFloat _position){
        position = _position;
        //GUI_Prototype.printMessage("Maci moved to " + position.getID());
        if(position.playersHere()>0 && position.isBearProof()==false)
            Controller.Lose("You met Maci Laci");
    }
}
