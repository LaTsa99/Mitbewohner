package hu.mudm.icefield;

import hu.mudm.icefield.model.action.*;
import hu.mudm.icefield.model.player.*;
import hu.mudm.icefield.model.field.*;
import hu.mudm.icefield.model.item.*;

class Main {
    public static void main(String[] args){

        //Test_5_3_1();

        //Test_5_3_2();

       // Test_5_3_13();
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
}