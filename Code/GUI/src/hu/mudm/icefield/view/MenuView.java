package hu.mudm.icefield.view;

import javax.swing.*;

public class MenuView extends MVCView {

    private MainFrame mainFrame;

    public MenuView(MVCModell modell) {
        super(modell);
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    @Override
    public void update() {

    }

    public JPanel getDataView(){
        return mainFrame.getDataView();
    }

    public JPanel getField(){
        return mainFrame.getField();
    }

    public JPanel getIceFloat(){
        return mainFrame.getIceFloat();
    }

    public JLabel getMessage(){
        return mainFrame.getMessage();
    }
}


