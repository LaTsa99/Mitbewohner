package hu.mudm.icefield.view;

import javax.swing.*;

public class MessageView extends MVCView {

    private JLabel label;
    private Boolean locktext = false;

    public MessageView(MVCModell modell, MenuView mv) {
        super(modell);
        label = mv.getMessage();
    }

    @Override
    public void update() {

        if((Controller)modell.getIsWon())
        {
            label.setText("You won!");
            locktext = true;
        }
        if((Controller)modell.getIsLost())
        {
            label.setText("You lost!");
            locktext = true;
        }
    }

    public void setText(String s){
        if(!locktext) label.setText(s);
    }
}
