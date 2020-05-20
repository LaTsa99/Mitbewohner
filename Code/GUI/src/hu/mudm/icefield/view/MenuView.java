package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;

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
        if (((Controller)model).getIsLost()) {
            JOptionPane.showMessageDialog(mainFrame,"Game is lost. :( Better luck next time.","Game over",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (((Controller)model).getIsWon()){
            JOptionPane.showMessageDialog(mainFrame,"Congratulations on winning the game!","Game over",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void packMainFrame(){
        mainFrame.pack();
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


