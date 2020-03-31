package hu.mudm.icefield;

import hu.mudm.icefield.model.action.Action;
import hu.mudm.icefield.model.action.BuildRocketAction;
import hu.mudm.icefield.model.action.MoveAction;
import hu.mudm.icefield.model.action.ShovelAction;
import hu.mudm.icefield.model.field.Hole;
import hu.mudm.icefield.model.field.InstableIceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.item.Rope;
import hu.mudm.icefield.model.player.Eskimo;
import hu.mudm.icefield.model.player.Researcher;

class Main {
    public static void main(String[] args){

        Test_5_3_1();

        Test_5_3_13();

        Test_5_3_15();

        Test_5_3_7();

        Test_5_3_6();

        //System.out.println("Hello");
    }

    static void Test_5_3_1() { //Test for 5.3.1 - Player steps on stable icefloat
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()), new StableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_13() {  //Test for 5.3.13 - Player gets rescued
        StableIceFloat one=new StableIceFloat();
        Hole two = new Hole();
        StableIceFloat three=new StableIceFloat();

        Researcher r = new Researcher(one);
        Eskimo e = new Eskimo(three);
        e.addItem(new Rope());

        Action ma_13 = new MoveAction(r, two);
        ma_13.performAction();
    }

    static void Test_5_3_15() {  //Test for 5.3.15 - Game won
        Game g = new Game();
        g.win();
    }

    static void Test_5_3_7() { //Test for 5.3.7 - Player builds rocket
        Eskimo e = new Eskimo(new StableIceFloat());
        BuildRocketAction bra = new BuildRocketAction(e);
        bra.performAction();
    }

    static void Test_5_3_6() { //Test for 5.3.6 - Player shovels snow
        Researcher r = new Researcher(new InstableIceFloat());
        ShovelAction s = new ShovelAction(r);
        s.performAction();
    }
}