package hu.mudm.icefield.view;

import java.util.ArrayList;
import java.util.List;

public abstract class MVCModell {

    protected List<MVCView> views = new ArrayList<>();
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

    public MVCController getMvcController(){
        return mvcController;
    }

    public void setMvcController(MVCController controller){
        this.mvcController = controller;
    }
}
