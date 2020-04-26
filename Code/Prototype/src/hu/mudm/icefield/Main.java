package hu.mudm.icefield;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.view.GUI_Prototype;

class Main {
    public static void main(String[] args){
        System.out.println("PROTOTYPE");
        GUI_Prototype.setController(new Controller(null));
        try{
            GUI_Prototype.guiLoop();
        }catch (Exception e){
            e.printStackTrace();
        }
        //Code from Skeleton
        /*boolean exit = false;

        GUI_skeleton s = new GUI_skeleton();
        try {
            s.addChoice('A', "Test for 5.3.1 - Player steps on stable icefloat");
            s.addChoice('B', "Test for 5.3.2 - Player steps on unstable icefloat");
            s.addChoice('C', "Test for 5.3.3 - Player steps on hole");
            s.addChoice('D', "Test for 5.3.4 - Player picks up food");
            s.addChoice('E', "Test for 5.3.5 - Player picks up rope(/diving suit/shovel)");
            s.addChoice('F', "Test for 5.3.6 - Player shovels snow");
            s.addChoice('G', "Test for 5.3.7 - Player builds rocket");
            s.addChoice('H', "Test for 5.3.8 - Player examines icefloat");
            s.addChoice('I', "Test for 5.3.9 - Player builds igloo");
            s.addChoice('J', "Test for 5.3.10 - IceFloat topples over");
            s.addChoice('K', "Test for 5.3.11 - Player drowns");
            s.addChoice('L', "Test for 5.3.12 - Player swims back");
            s.addChoice('M', "Test for 5.3.13 - Player gets rescued");
            s.addChoice('N', "Test for 5.3.14 - Game lost");
            s.addChoice('O', "Test for 5.3.15 - Game won");
            s.addChoice('P', "Test for 5.3.16 - Snowstorm appears");
            s.addChoice('Q', "Test for 5.3.17 - Game generates icefloat");
            s.addChoice('X', "Exit");

        } catch (GUI_skeleton.GUI_skeletonException e) {
            e.printStackTrace();
        }

        while(!exit) {
            char c='X';
            try {
                c = s.choose();
            } catch (GUI_skeleton.GUI_skeletonException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch(c) {
                case 'A':
                    Test_5_3_1();
                    break;
                case 'B':
                    Test_5_3_2();
                    break;
                case 'C':
                    Test_5_3_3();
                    break;
                case 'D':
                    Test_5_3_4();
                    break;
                case 'E':
                    Test_5_3_5();
                    break;
                case 'F':
                    Test_5_3_6();
                    break;
                case 'G':
                    Test_5_3_7();
                    break;
                case 'H':
                    Test_5_3_8();
                    break;
                case 'I':
                    Test_5_3_9();
                    break;
                case 'J':
                    Test_5_3_10();
                    break;
                case 'K':
                    Test_5_3_11();
                    break;
                case 'L':
                    Test_5_3_12();
                    break;
                case 'M':
                    Test_5_3_13();
                    break;
                case 'N':
                    Test_5_3_14();
                    break;
                case 'O':
                    Test_5_3_15();
                    break;
                case 'P':
                    Test_5_3_16();
                    break;
                case 'Q':
                    Test_5_3_17();
                    break;
                case 'X':
                    exit = true;
                    break;
                default:
                    System.out.println("Unexpected char");
                    break;
            }
        }*/
    }

    //Tests
    /*static void Test_5_3_1() { //Test for 5.3.1 - Player steps on stable icefloat - Domi
        System.out.println("TEST 5.3.1");
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()), new StableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_2() {  //Test for 5.3.2 - Player steps on unstable icefloat - Domi
        System.out.println("TEST 5.3.2");
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()),new UnstableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_3() {  //Test for 5.3.3 - Player steps on hole - Domi
        System.out.println("TEST 5.3.3");
        IceFloat h = new Hole();
        IceFloat s = new StableIceFloat();
        Researcher r = new Researcher(new StableIceFloat());
        s.setNeighbor(h);
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
        Eskimo e = new Eskimo(new StableIceFloat(new Food()));
        Action ma = new PickupAction(e);
        ma.performAction();
    }

    static void Test_5_3_5() { //Test for 5.3.5 - Player picks up rope(/diving suit/shovel) - Kriszti
        System.out.println("TEST 5.3.5");
        Action ma = new PickupAction(new Eskimo(new StableIceFloat(new Rope())));
        ma.performAction();
    }

    static void Test_5_3_6() { //Test for 5.3.6 - Player shovels snow
        System.out.println("TEST 5.3.6");
        Researcher r = new Researcher(new StableIceFloat());
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

    static void Test_5_3_9() { //Test for 5.3.9 - Player builds igloo - Kriszti
        System.out.println("TEST 5.3.9");
        Action ba = new BuildAction(new Eskimo(new StableIceFloat()));
        ba.performAction();
    }

    static void Test_5_3_10() { //Test for 5.3.10 - IceFloat topples over
        System.out.println("TEST 5.3.10");
        Action ma = new MoveAction(new Eskimo(new StableIceFloat()), new UnstableIceFloat());
        ma.performAction();
    }

    static void Test_5_3_11() {  //Test for 5.3.11 - Player drowns
        System.out.println("TEST 5.3.11");
        Hole one = new Hole();
        StableIceFloat two = new StableIceFloat();
        StableIceFloat a = new StableIceFloat();
        UnstableIceFloat b = new UnstableIceFloat();
        StableIceFloat c = new StableIceFloat();
        one.setNeighbor(two);
        one.setNeighbor(a);
        one.setNeighbor(b);
        one.setNeighbor(c);

        Action ma = new MoveAction(new Researcher(new StableIceFloat()), one);
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
        ArrayList<IceFloat> icefloats = new ArrayList<IceFloat>();
        icefloats.add(new StableIceFloat());
        icefloats.add(new UnstableIceFloat());
        icefloats.add(new Hole());
        icefloats.add(new StableIceFloat());
        icefloats.add(new UnstableIceFloat());
        icefloats.add(new Hole());

        Controller c = new Controller(icefloats);
        c.snowstorm();
    }


    static void Test_5_3_17() { //Test for 5.3.17 - Game generates icefloat
        System.out.println("TEST 5.3.17");
        Game g = new Game();
        g.init();
    }*/
}