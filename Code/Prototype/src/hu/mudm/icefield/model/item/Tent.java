package hu.mudm.icefield.model.item;

import hu.mudm.icefield.model.player.Character;

public class Tent extends Item { @Override
        public void onPickup(Character ch) {
            ch.addItem(this);
        }

        @Override
        public Boolean canBuildAsTent(){ return true; }

        @Override
        public Boolean isStorable(){
            return true;
        }

        @Override
        public void onUse(Character ch) {
            ch.getIceFloat().buildTent();
            ch.removeItem(this);
        }
}
