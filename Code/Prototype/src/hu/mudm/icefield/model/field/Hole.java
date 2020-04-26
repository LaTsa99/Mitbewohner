package hu.mudm.icefield.model.field;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

import java.io.IOException;

public class Hole extends IceFloat{

    public Hole() { type = "h";}

    public Hole(Item item){
        type = "h";
    }

    @Override
    public void stepOn(Character ch) {
        //Komment, mert ez most nem tűnik hasznosnak ebben a percben...
        /*GUI_skeleton.printlnWithTabs(this.getClass(),"stepOn(Character ch)");

        try {
            GUI_skeleton.raiseTabCnt();
            if (!ch.canSwim()){
                GUI_skeleton.decreaseTabCnt();
                if (neighbors !=null){
                    boolean willBeSaved = false;
                    for (IceFloat ice: neighbors){
                       GUI_skeleton.raiseTabCnt();
                       willBeSaved = ice.canRescueFromHere();
                       GUI_skeleton.decreaseTabCnt();

                       if (willBeSaved) {
                           GUI_skeleton.raiseTabCnt();
                           ice.stepOn(ch);
                           GUI_skeleton.decreaseTabCnt();
                           break;
                       }
                    }
                    if (!willBeSaved){
                        GUI_skeleton.raiseTabCnt();
                        Game.lose();
                        GUI_skeleton.decreaseTabCnt();
                    }
                }
            }else{
                GUI_skeleton.raiseTabCnt();
                ch.getIceFloat().stepOn(ch);
                GUI_skeleton.decreaseTabCnt();
            }
            GUI_skeleton.decreaseTabCnt();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        if (ch.canSwim()) return;
        for (IceFloat ice: neighbors){
            if (ice.canRescueFromHere()) {
                ice.stepOn(ch);
                return;
            }
        }
        IceFloat prev = ch.getIceFloat();
        prev.removeCharacter(ch);
        characters.add(ch);
        ch.setPosition(this);
        Game.lose();
    }
}
