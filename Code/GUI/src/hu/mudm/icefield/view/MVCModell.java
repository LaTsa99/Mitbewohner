package hu.mudm.icefield.view;

import java.util.List;

public abstract class MVCModell {

    protected List<MVCView> views;
    protected GUI gui;

    private MVCController mvcController;

    public MVCModell(GUI gui){
        this.gui = gui;
    }

    public void updateViews(){
        for(MVCView view : views){
            view.update();
        }
    }

    public void addView(MVCView view){
        views.add(view);
    }

    public abstract void createViews(MenuView mv);
}
