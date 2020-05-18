package hu.mudm.icefield.view;

import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.model.player.Eskimo;
import hu.mudm.icefield.model.player.Researcher;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MVCController implements GUI {

    private MenuView menuView;
    private FieldView fieldView;
    private MessageView messageView;

    private WelcomeFrame welcomeFrame;

    public MVCController(){
    }

    public void setViews(MenuView menuView, FieldView fieldView, MessageView messageView){
        this.menuView = menuView;
        this.fieldView = fieldView;
        this.messageView = messageView;
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

        ArrayList<String> nameList = (ArrayList<String>) welcomeFrame.getNickNames();
        ArrayList<String> typeList = (ArrayList<String>) welcomeFrame.getCharacterTypes();

        ArrayList<Character> characters = new ArrayList<>();

        for(int i = 0; i < nameList.size(); i++){
            if(typeList.get(i).equals("Researcher"))
                characters.add(new Researcher(nameList.get(i), startingIceFloat));
            else if(typeList.get(i).equals("Eskimo"))
                characters.add(new Eskimo(nameList.get(i), startingIceFloat));
        }
        return characters;
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
