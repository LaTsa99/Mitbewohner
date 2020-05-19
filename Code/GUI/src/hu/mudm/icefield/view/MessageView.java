package hu.mudm.icefield.view;
import hu.mudm.icefield.model.Controller;
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

        if(((Controller)model).getisWon())
        {
            label.setText("You won!");
            locktext = true;
        }
        if(((Controller)model).getisLost())
        {
            label.setText("You lost!");
            locktext = true;
        }
    }

    public void setText(String s){
        if(!locktext) label.setText(s);
    }
}
