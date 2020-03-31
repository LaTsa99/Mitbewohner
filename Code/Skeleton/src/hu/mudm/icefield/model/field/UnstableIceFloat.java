package hu.mudm.icefield.model.field;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.item.Item;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

import java.io.IOException;

public class UnstableIceFloat extends IceFloat {

    public UnstableIceFloat(Item item) {
        super(item);
    }

    public UnstableIceFloat() {
    }

    @Override
    public Boolean stepOn(Character ch, IceFloat prev) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stepOn(Character ch) {
        GUI_skeleton.printlnWithTabs(this.getClass(),"stepOn(Character ch)");

        System.out.println("Is the ice float able to hold another player?");
        try {
            boolean retValue = GUI_skeleton.chooseYesOrNo();
            if (retValue){
                characters.add(ch);

                GUI_skeleton.raiseTabCnt();
                ch.moveTo(this);
                GUI_skeleton.decreaseTabCnt();
            }
            else {
                GUI_skeleton.raiseTabCnt();
                Game.lose();
                GUI_skeleton.decreaseTabCnt();
            }
        } catch (GUI_skeleton.GUI_skeletonException | IOException e) {
            e.printStackTrace();
        }
    }
}
