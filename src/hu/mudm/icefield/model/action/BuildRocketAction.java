package hu.mudm.icefield.model.action;

public class BuildRocketAction extends Action {

    private static int pickedUp = 0;

    public static int pickedUpParts(){
        return pickedUp;
    }

    @Override
    public void performAction() {}

}
