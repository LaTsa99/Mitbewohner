package hu.mudm.icefield.model.item;

//Falls ein Charakter diesen Gegenstand enthält, dann kann er einen anderen Character aus
//einem benachbarten Loch retten.
public class Rope extends Item {
    @Override
    public Boolean canRescue() {
        return true;
    }
}
