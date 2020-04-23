package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.player.Character;

//Die Bestandteile der Leuchtpistole zu identifizieren und es sagen können, ob die Bestandteile
//aufgenommen wurden.
public class RocketPart extends Item {

    @Override
    public void onPickup(Character ch) {
        Controller.rocketPartPickedUp();
        ch.addItem(this);
    }
}
