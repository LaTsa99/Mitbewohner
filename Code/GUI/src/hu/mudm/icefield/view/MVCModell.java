package hu.mudm.icefield.view;

import java.util.List;

public abstract class MVCModell {

    private List<MVCView> views;

    private MVCController mvcController;

    public void updateViews(){
        for(MVCView view : views){
            view.update();
        }
    }

    public abstract void createViews();
}
