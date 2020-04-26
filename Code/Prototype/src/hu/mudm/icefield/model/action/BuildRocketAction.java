package hu.mudm.icefield.model.action;

import hu.mudm.icefield.Game;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.view.GUI_skeleton;

public class BuildRocketAction extends Action {
    public BuildRocketAction(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        GUI_skeleton.printlnWithTabs(this.getClass(),"performAction()");
        Game g = Game.getInstance();
        if (g.GetController().checkWinningStatus()) {
            g.win();
        }
    }
}
