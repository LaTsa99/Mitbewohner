package hu.mudm.icefield;

import hu.mudm.icefield.model.action.*;
import hu.mudm.icefield.model.field.*;
import hu.mudm.icefield.model.item.*;
import hu.mudm.icefield.model.player.*;

class Main {
    public static void main(String[] args){

        Test_5_3_1();

        Test_5_3_2();

        Test_5_3_13();

        Test_5_3_15();

        Test_5_3_7();

        Test_5_3_6();
    }

    static void Test_5_3_1() { //Test for 5.3.1
        System.out.printf("TEST 5.3.1");
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()), new StableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_2() {  //Test for 5.3.13
        System.out.printf("TEST 5.3.2");
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()),new UnstableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_6() { //Test for 5.3.6 - Player shovels snow
        System.out.printf("TEST 5.3.6");
        Researcher r = new Researcher(new UnstableIceFloat());
        ShovelAction s = new ShovelAction(r);
        s.performAction();
    }

    static void Test_5_3_7() { //Test for 5.3.7 - Player builds rocket
        System.out.printf("TEST 5.3.7");
        Eskimo e = new Eskimo(new StableIceFloat());
        BuildRocketAction bra = new BuildRocketAction(e);
        bra.performAction();
    }

    static void Test_5_3_13() {  //Test for 5.3.13
        System.out.printf("TEST 5.3.13");
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
        System.out.printf("TEST 5.3.15");
        Game g = new Game();
        g.win();
    }





    static void Test_5_3_6() { //Test for 5.3.6 - Player shovels snow
        Researcher r = new Researcher(new InstableIceFloat());
        ShovelAction sa = new ShovelAction(r);
        s.performAction();
    }

    static void Test_5_3_8() { //Test for 5.3.8 - Player examines icefloat
        CheckAction ca = new CheckAction(new Researcher(), new Hole()); //We should be able to add Eskimos to a CheckAction as long as they are able to perform one
        ca.performAction();
    }

}