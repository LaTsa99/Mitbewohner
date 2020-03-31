package hu.mudm.icefield;

import hu.mudm.icefield.model.action.*;
import hu.mudm.icefield.model.field.*;
import hu.mudm.icefield.model.item.*;
import hu.mudm.icefield.model.player.*;
import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.view.GUI_skeleton;

class Main {
    public static void main(String[] args){

        Test_5_3_1();

        Test_5_3_2();

        Test_5_3_3();

        Test_5_3_4();

        Test_5_3_5();

        Test_5_3_6();

        Test_5_3_7();

        Test_5_3_8();

        Test_5_3_11();

        Test_5_3_12();

        Test_5_3_13();

        Test_5_3_14();

        Test_5_3_15();

        Test_5_3_16();
    }

    static void Test_5_3_1() { //Test for 5.3.1 - Player steps on stable icefloat - Domi
        System.out.printf("TEST 5.3.1");
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()), new StableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_2() {  //Test for 5.3.2 - Player steps on unstable icefloat - Domi
        System.out.printf("TEST 5.3.2");
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()),new UnstableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_3() {  //Test for 5.3.3 - Player steps on hole - Domi
        System.out.println("TEST 5.3.3");;
        IceFloat h = new Hole();
        IceFloat s = new StableIceFloat();
        Researcher r = new Researcher(new StableIceFloat());
        s.setNeighbors(h);
        System.out.println("Can this character swim? (Do they have a diving suit?)");
        try{
            if(GUI_skeleton.chooseYesOrNo()){
                r.addItem(new DiverSuit());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Action ma = new MoveAction(r,h);
        ma.performAction();
    }

    static void Test_5_3_4() { //Test for 5.3.4 - Player picks up food
        System.out.println("TEST 5.3.4");
        Eskimo e = new Eskimo(new UnstableIceFloat(new Food()));
        Action ma = new PickupAction(e);
        ma.performAction();
    }

    static void Test_5_3_5() { //Test for 5.3.5 - Player picks up rope(/diving suit/shovel) - Kriszti
        System.out.println("TEST 5.3.5");
        Action ma = new PickupAction(new Eskimo(new StableIceFloat()));
        ma.performAction();
    }

    static void Test_5_3_6() { //Test for 5.3.6 - Player shovels snow
        System.out.println("TEST 5.3.6");
        Researcher r = new Researcher(new UnstableIceFloat());
        r.addItem(new Rope());
        r.addItem(new RocketPart());
        try{
            System.out.println("Does the character have a shovel?");
            if(GUI_skeleton.chooseYesOrNo()){
                r.addItem(new Shovel());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Action sa = new ShovelAction(r);
        sa.performAction();
    }

    static void Test_5_3_7() { //Test for 5.3.7 - Player builds rocket
        System.out.println("TEST 5.3.7");
        Eskimo e = new Eskimo(new StableIceFloat());
        Action bra = new BuildRocketAction(e);
        bra.performAction();
    }

    static void Test_5_3_8() { //Test for 5.3.8 - Player examines icefloat
        System.out.println("TEST 5.3.8");
        Action ca = new CheckAction(new Researcher(new StableIceFloat()), new Hole()); //We should be able to add Eskimos to a CheckAction as long as they are able to perform one
        ca.performAction();
    }

    static void Test_5_3_11() {  //Test for 5.3.11 - Player drowns
        System.out.println("TEST 5.3.11");
        Hole one = new Hole();
        StableIceFloat two = new StableIceFloat();

        one.setNeighbors(two);
        Action ma = new MoveAction(new Researcher(new UnstableIceFloat()), one);
        ma.performAction();
    }

    static void Test_5_3_12() {  //Test for 5.3.12 - Player swims back
        System.out.println("TEST 5.3.12");
        Hole one = new Hole();
        Eskimo eski = new Eskimo(new StableIceFloat());
        System.out.println("Can this character swim? (Do they have a diving suit?)");
        try{
            if(GUI_skeleton.chooseYesOrNo()){
                eski.addItem(new DiverSuit());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Action ma = new MoveAction(eski, one);
        ma.performAction();
    }


    static void Test_5_3_13() {  //Test for 5.3.13 - Player gets rescued
        System.out.println("TEST 5.3.13");
        StableIceFloat one=new StableIceFloat();
        Hole two = new Hole();
        StableIceFloat three=new StableIceFloat();

        Researcher r = new Researcher(one);
        Eskimo e = new Eskimo(three);
        e.addItem(new Rope());

        Action ma = new MoveAction(r, two);
        ma.performAction();
    }

    static void Test_5_3_14() {  //Test for 5.3.14 - Game lost
        System.out.println("TEST 5.3.14");
        Game g = new Game();
        g.lose();
    }

    static void Test_5_3_15() {  //Test for 5.3.15 - Game won
        System.out.println("TEST 5.3.15");
        Game g = new Game();
        g.win();
    }

    static void Test_5_3_16() {  //Test for 5.3.16 - Snowstorm appears
        System.out.println("TEST 5.3.16");
        Controller c = new Controller();
        c.snowstorm();
    }
}