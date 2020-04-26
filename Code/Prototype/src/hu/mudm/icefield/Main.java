package hu.mudm.icefield;

import hu.mudm.icefield.view.GUI_Prototype;

class Main {
    public static void main(String[] args){
        System.out.println("PROTOTYPE");
        GUI_Prototype.setGame(new Game());
        try{
            GUI_Prototype.guiLoop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}