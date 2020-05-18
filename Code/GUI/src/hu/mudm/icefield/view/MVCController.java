package hu.mudm.icefield.view;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.player.Character;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MVCController implements GUI {

    private MenuView menuView;
    private FieldView fieldView;
    private MessageView messageView;

    private WelcomeFrame welcomeFrame;

    public MVCController(){
    }

    @Override
    public void showMessage(String s) {
        messageView.setText(s);
    }

    @Override
    public int getAction(Character character) throws NoActionException {
        return 0;
    }

    @Override
    public int getChosenNeighborID(IceFloat icefloat) throws NoNeighborException {
        return 0;
    }

    @Override
    public ArrayList<Character> getCharacters(StableIceFloat startingIceFloat) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //TODO welcomeFrame pops up


        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    welcomeFrame = new WelcomeFrame();
                    welcomeFrame.setVisible(true);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        synchronized (welcomeFrame.lock){
            while (welcomeFrame.isVisible()){
                try {
                    welcomeFrame.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //TODO list data
        return null;
    }

    @Override
    public void see(Character ch) {

    }

    @Override
    public void endRound() {

    }

    @Override
    public void startTurn() {

    }
}
