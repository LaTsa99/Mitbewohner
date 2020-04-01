package hu.mudm.icefield.model.field;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

import java.io.IOException;

public class Hole extends IceFloat{

    public Hole() {}

    public Hole(Item item){super(item);}

    @Override
    public void stepOn(Character ch) {
        GUI_skeleton.printlnWithTabs(this.getClass(),"stepOn(Character ch)");

        System.out.println("Can this character swim? (Do they have a diving suit?)");
        try {
            if (!GUI_skeleton.chooseYesOrNo()){
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
            }
        } catch (GUI_skeleton.GUI_skeletonException | IOException e) {
            e.printStackTrace();
        }
    }
}
