package hu.mudm.icefield.model.field;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;

public class Hole extends IceFloat{

    public Hole() {}

    public Hole(Item item){super(item);}

    @Override
    public void stepOn(Character ch) {

        try {
            if (!ch.canSwim()){
                if (neighbors !=null){
                    boolean willBeSaved = false;
                    for (IceFloat ice: neighbors){
                       willBeSaved = ice.canRescueFromHere();

                       if (willBeSaved) {
                           ice.stepOn(ch);
                           break;
                       }
                    }
                    if (!willBeSaved){
                        Game.lose();
                    }
                }
            }else{
                ch.getIceFloat().stepOn(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
