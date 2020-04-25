package hu.mudm.icefield.view;

import hu.mudm.icefield.model.Controller;
import hu.mudm.icefield.model.PolarBear;
import hu.mudm.icefield.model.action.*;
import hu.mudm.icefield.model.field.Hole;
import hu.mudm.icefield.model.field.IceFloat;
import hu.mudm.icefield.model.field.StableIceFloat;
import hu.mudm.icefield.model.field.UnstableIceFloat;
import hu.mudm.icefield.model.item.*;
import hu.mudm.icefield.model.player.Character;
import hu.mudm.icefield.model.player.Eskimo;
import hu.mudm.icefield.model.player.Researcher;
import javafx.scene.control.IndexedCell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Data class, that contains the string forms of the possible commands.
 */
final class Commands{
    public final static String loadTest             = "loadTest";
    public final static String generateField        = "generateField";
    public final static String setSnow              = "setSnow";
    public final static String addItemToFloat       = "addItemToFloat";
    public final static String addIgloo             = "addIgloo";
    public final static String addTent              = "addTent";
    public final static String addCharacter         = "addCharacter";
    public final static String addItemToCharacter   = "addItemToCharacter";
    public final static String setPolarBear         = "setPolarBear";
    public final static String setSnowStorm         = "setSnowStorm";
    public final static String startTest            = "startTest";
    public final static String moveAction           = "moveAction";
    public final static String shovelAction         = "shovelAction";
    public final static String buildAction          = "buildAction";
    public final static String buildTentAction      = "buildTentAction";
    public final static String checkAction          = "checkAction";
    public final static String buildRocketAction    = "buildRocketAction";
    public final static String pickupAction         = "pickupAction";
    public final static String snowstorm            = "snowstorm";
    public final static String polarBearMove        = "polarBearMove";
    public final static String saveOutput           = "saveOutput";
    public final static String exit                 = "exit";
}

final class ItemTypes{
    public final static String Shovel           = "shovel";
    public final static String DiverSuit        = "diversuit";
    public final static String Food             = "food";
    public final static String RocketPart       = "rocketpart";
    public final static String Rope             = "rope";
    public final static String Tent             = "tent";
    public final static String BreakableShovel  = "breakable";
}

final class CharacterTypes{
    public final static String researcher   = "researcher";
    public final static String eskimo       = "eskimo";
}

final class IceFloatTypes{
    public final static String stable   = "stable";
    public final static String unstable = "unstable";
    public final static String hole     = "hole";
}

/**
 * Class, that handles the command line input and output of
 * the program. Every field and method is static, so there is
 * no need for instantiating.
 */
public class GUI_Prototype {

    private static Controller c;

    private static boolean snowStormRandom = true;

    private static int state = 0; // 0 = creation, 1 = testing

    /**
     * Basic prompt message before the user input.
     */
    private static final String prompt = ">> ";

    /**
     * Tells if we quit the program.
     */
    private static Boolean exit = false;

    public static void setController(Controller controller){
        c = controller;
    }

    /**
     * The loop of the GUI. Cycles until we exit the program.
     * @throws IOException
     */
    public static void guiLoop() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(!exit){
            System.out.print(prompt);
            String input = br.readLine();

            String[] parsedInput = input.split(" ");

            String cmd = parsedInput[0];

            switch (cmd){
                case Commands.loadTest:
                    loadTest(parsedInput);
                    break;
                case Commands.generateField:
                    generateField(parsedInput);
                    break;
                case Commands.setSnow:
                    setSnow(parsedInput);
                    break;
                case Commands.addItemToFloat:
                    addItemToFloat(parsedInput);
                    break;
                case Commands.addIgloo:
                    addIgloo(parsedInput);
                    break;
                case Commands.addTent:
                    addTent(parsedInput);
                    break;
                case Commands.addCharacter:
                    addCharacter(parsedInput);
                    break;
                case Commands.addItemToCharacter:
                    addItemToCharacter(parsedInput);
                    break;
                case Commands.setPolarBear:
                    setPolarBear(parsedInput);
                    break;
                case Commands.setSnowStorm:
                    setSnowStorm(parsedInput);
                    break;
                case Commands.startTest:
                    startTest(parsedInput);
                    break;
                case Commands.moveAction:
                    moveAction(parsedInput);
                    break;
                case Commands.shovelAction:
                    shovelAction(parsedInput);
                    break;
                case Commands.buildAction:
                    buildAction(parsedInput);
                    break;
                case Commands.buildTentAction:
                    buildTentAction(parsedInput);
                    break;
                case Commands.checkAction:
                    checkAction(parsedInput);
                    break;
                case Commands.buildRocketAction:
                    buildRocketAction(parsedInput);
                    break;
                case Commands.pickupAction:
                    pickupAction(parsedInput);
                    break;
                case Commands.snowstorm:
                    snowstorm(parsedInput);
                    break;
                case Commands.polarBearMove:
                    polarBearMove(parsedInput);
                    break;
                case Commands.saveOutput:
                    saveOutput(parsedInput);
                    break;
                case Commands.exit:
                    exit = true;
                    break;
                default:
                    System.out.println("Error: unknown command!");
            }
        }
    }

    private static void printState(Character character){
        StringBuilder sb = new StringBuilder();
        sb.append(character.getName());
        sb.append(":\n");
        sb.append("Body temperature: ");
        sb.append(character.getTemp());
        sb.append("\n");
        sb.append("Position: ");
        sb.append(character.getPosition());
        sb.append("\n");
        sb.append("Items: ");
        for(Item item : character.getItems()){
            sb.append(item.getName());
            sb.append(" ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void printState(IceFloat iceFloat){
        StringBuilder sb = new StringBuilder();
        sb.append(iceFloat.getId());
        sb.append("\n");
        sb.append("Type: ");
        sb.append(iceFloat.getType());
        sb.append("\n");
        sb.append("Capacity: ");
        sb.append(iceFloat.getCapacity());
        sb.append("\n");
        sb.append("Item: ");
        sb.append(iceFloat.getItem() == null ? " - " : iceFloat.getItem().getName());
        sb.append("\n");
        sb.append("Snow: ");
        sb.append(iceFloat.getSnowLevel());
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void printState(PolarBear bear){
        System.out.println("Polar bear: Position: " + bear.getPosition().getId());
    }

    private static void wrongUsage(String usage){
        System.out.println("Error: wrong usage!");
        System.out.println("Usage:");
        System.out.println(usage);
    }

    private static void loadTest(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 2){
            String usage = "loadTest <input file>";
            wrongUsage(usage);
        }else{
            // TODO
        }
    }

    private static void generateField(String[] params) throws IOException{
        String unstableRegex = "i[0-9][0-9]*";
        ArrayList<String> field = new ArrayList<>();
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length == 1){
            c.setIcefloats(randomizedField(6, 6));
        }if(params.length == 4){
            int n, m;
            try{
                n = Integer.parseInt(params[1]);
                m = Integer.parseInt(params[2]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: first and second arguments should be integers!");
                return;
            }

            if(params[3].equals("y")){
                c.setIcefloats(randomizedField(n, m));
            }else{

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                // Iterating through every row
                for(int i = 0; i < n; i++){
                    boolean valid = false;
                    // While row not valid
                    while(!valid){
                        ArrayList<String> row = new ArrayList<>();
                        System.out.print("\t");
                        String input = br.readLine();
                        // Row cannot be empty
                        if(!input.equals(" ")){
                            boolean rowValid = true;
                            String[] parsed = input.split(" ");
                            // Check if only valid strings
                            for(int j = 0; j < m; j++){
                                if(parsed[j].equals("s") || parsed[j].equals("h") || parsed[j].matches(unstableRegex)){
                                    row.add(parsed[i]);
                                }
                                else{
                                    rowValid = false;
                                }
                            }
                            // If row is valid, add it to the field
                            if(rowValid){
                                valid = true;
                                field.addAll(row);
                            }else{
                                System.out.println("Error: invalid row of icefloats.");
                            }
                        }else{
                            System.out.println("Error: row must contain " + m + " elements.");
                        }
                    }
                }

                // Check, if first icefloat is not stable
                if(!field.get(0).equals("s")){
                    field.set(0, "s");
                    System.out.println("Warning: First float must be stable, so it has been set.");
                }

                ArrayList<IceFloat> iceFloats = new ArrayList<>();

                // Generating icefloats, not implementing neighbourhood yet
                for(String s : field){
                    iceFloats.add(createIceFloat(s));
                }

                // Making neighbours neighbours
                for(int i = 0; i < iceFloats.size(); i++){
                    if((i + 1) % m > (i % m)) iceFloats.get(i).setNeighbor(iceFloats.get(i+1));
                    if((i + m) <= n*m) iceFloats.get(i).setNeighbor(iceFloats.get(i + m));
                }

                c.setIcefloats(iceFloats);
            }
        }else{
            String usage = "generateField ,\n" +
                    "generateField <m> <n> <random (y or n)>";
            wrongUsage(usage);
        }
    } // DONE

    private static void setSnow(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 3){
            String usage = "setSnow <icefloat> <count>";
            wrongUsage(usage);
        }else{
            int floatID, count;
            try{
                floatID = Integer.parseInt(params[1]);
                count = Integer.parseInt(params[2]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: arguments 1 and 2 must be an integer value.");
                return;
            }

            ArrayList<IceFloat> iceFloats = c.getIcefloats();

            // Set snow on icefloat
            for(IceFloat iceFloat : iceFloats){
                if(iceFloat.getId() == floatID){
                    boolean removing = count < 0;
                    count = (count < 0) ? -count : count;
                    for(int i = 0; i < count; i++){
                        if(removing) iceFloat.removeSnow(1);
                        else iceFloat.addSnow();
                    }
                    printState(iceFloat);
                }
            }

            c.setIcefloats(iceFloats);
        }
    } // DONE

    private static void addItemToFloat(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 3){
            String usage = "addItemToFloat <floatID> <item>";
            wrongUsage(usage);
        }else{
            int floatID;

            try{
                floatID = Integer.parseInt(params[1]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: first argument should be an integer value.");
                return;
            }

            String type = params[2];
            Item item = createItem(type);

            if(item != null){
                ArrayList<IceFloat> iceFloats = c.getIcefloats();
                for(IceFloat iceFloat : iceFloats){
                    if(iceFloat.getId() == floatID){
                        if(iceFloat.getType().equals(IceFloatTypes.hole)){
                            System.out.println("Error: You cannot put an item into a hole.");
                            return;
                        }
                        iceFloat.setItem(item);
                        printState(iceFloat);
                    }
                }
                c.setIcefloats(iceFloats);
            }
        }
    } // DONE

    private static void addIgloo(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 2){
            String usage = "addIgloo <floatID>";
            wrongUsage(usage);
        }else{
            int floatID;
            try{
                floatID = Integer.parseInt(params[1]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: first argument should be an integer value.");
                return;
            }

            ArrayList<IceFloat> iceFloats = c.getIcefloats();
            for(IceFloat iceFloat : iceFloats){
                if(iceFloat.getId() == floatID){
                    if(iceFloat.getType().equals(IceFloatTypes.hole)){
                        System.out.println("Error: You cannot build an igloo on a hole.");
                        return;
                    }
                    iceFloat.buildIgloo();
                    printState(iceFloat);
                }
            }
            c.setIcefloats(iceFloats);
        }
    } // DONE

    private static void addTent(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 2){
            String usage = "addTent <floatID>";
            wrongUsage(usage);
        }else{
            int floatID;
            try{
                floatID = Integer.parseInt(params[1]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: first argument should be an integer value.");
                return;
            }

            ArrayList<IceFloat> iceFloats = c.getIcefloats();
            for(IceFloat i : iceFloats){
                if(i.getId() == floatID){
                    if(i.getType().equals(IceFloatTypes.hole)){
                        System.out.println("Error: You cannot build a tent on a hole.");
                        return;
                    }
                    i.buildTent();
                    printState(i);
                }
            }
            c.setIcefloats(iceFloats);
        }
    } // DONE

    private static void addCharacter(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 5){
            String usage = "addCharacter <type> <name> <position> <temp>";
            wrongUsage(usage);
        }else{
            int temp, pos;
            try {
                temp = Integer.parseInt(params[4]);
                pos = Integer.parseInt(params[3]);
            }catch (NumberFormatException nfe) {
                System.out.println("Error: third and fourth arguments should be an integer value.");
                return;
            }

            IceFloat position = getIceFloat(pos);
            if(position == null){
                System.out.println("Error: icefloat doesn't exist.");
                return;
            }
            if(position.getType() != IceFloatTypes.stable){
                System.out.println("Error: selected icefloat isn't stable.");
            }
            String type = params[1];
            String name = params[2];
            Character character = createCharacter(type, name, (StableIceFloat) c.getIcefloats().get(0));
            if(character != null){
                character.setTemp(temp);
                character.setPosition(position);
                ArrayList<Character> chlist = c.getCharacters();
                chlist.add(character);
                c.setCharacters(chlist);
                printState(character);
            }
        }
    } // DONE

    private static void addItemToCharacter(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 3){
            String usage = "addItemToCharacter <item> <character>";
            wrongUsage(usage);
        }else{
            String characterName = params[1];
            String itemType = params[2];

            ArrayList<Character> characters = c.getCharacters();
            boolean exists = false;
            for(Character character : characters){
                if(character.getName().equals(characterName)){
                    exists = true;
                    Item item = createItem(itemType);
                    if(item != null){
                        character.addItem(item);
                    }
                }
            }
            if(exists){
                c.setCharacters(characters);
            }else{
                System.out.println("Error: Character with this name doesn't exist.");
            }
        }
    } // DONE

    private static void setPolarBear(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 3){
            String usage = "setPolarBear <floatID> <randomize>";
            wrongUsage(usage);
        }else{
            int floatID = 0;
            try{
                floatID = Integer.parseInt(params[1]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: first argument should be an integer value.");
                return;
            }

            IceFloat position = getIceFloat(floatID);
            if(position == null){
                System.out.println("Error: Icefloat with this id doesn't exist.");
                return;
            }
            PolarBear bear = new PolarBear(position);
            c.setPolarBear(bear);
            // TODO: RANDOMIZE
        }
    }

    private static void setSnowStorm(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }
        else if(params.length != 2){
            String usage = "setSnowStorm <randomize (y/n)>";
            wrongUsage(usage);
        }else{
            if(params[1].equals("y")){
                snowStormRandom = true;
            }else if(params[1].equals("n")){
                snowStormRandom = false;
            }else{
                System.out.println("Error: Argument must be y (yes) or n (no).");
            }
        }
    } // DONE

    private static void startTest(String[] params){
        if(state == 1){
            System.out.println("Error: You cannot do this in test state.");
        }else{
            System.out.println("Starting test...");
            state = 1;
        }
    } // DONE

    private static void moveAction(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length != 2){
            String usage = "moveAction <character> <icefloat>";
            wrongUsage(usage);
        }else{

            int pos = 0;
            try{
                pos = Integer.parseInt(params[2]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: Argument 2 must be an integer value.");
                return;
            }

            ArrayList<Character> characters = c.getCharacters();
            ArrayList<IceFloat> iceFloats = c.getIcefloats();

            Character character = null;
            IceFloat iceFloat = null;

            for(Character c : characters){
                if(c.getName().equals(params[1])) character = c;
            }

            if(character == null){
                System.out.println("Error: Character with this name doesn't exist!");
                return;
            }

            for(IceFloat i : iceFloats){
                if(i.getId() == pos) iceFloat = i;
            }
            if(iceFloat == null){
                System.out.println("Error: Icefloat with this id doesn't exist!");
                return;
            }
            MoveAction ma = new MoveAction(character, iceFloat);
            ma.performAction();
            printState(character);
            printState(iceFloat);

            c.setCharacters(characters);
            c.setIcefloats(iceFloats);
        }
    } // DONE

    private static void shovelAction(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length != 2){
            String usage = "shovelAction <character>";
            wrongUsage(usage);
        }else{

            ArrayList<Character> characters = c.getCharacters();

            Character character = null;

            for(Character c : characters){
                if(c.getName().equals(params[1])) character = c;
            }

            if(character == null){
                System.out.println("Error: Character with this name doesn't exist!");
                return;
            }

            ShovelAction sa = new ShovelAction(character);
            sa.performAction();
            printState(character);
            printState(character.getPosition());

            c.setCharacters(characters);

            ArrayList<IceFloat> iceFloats = c.getIcefloats();

            for(int i = 0; i < iceFloats.size(); i++){
                if(iceFloats.get(i).getId() == character.getPosition().getId()) {
                    iceFloats.set(i, character.getPosition());
                    c.setIcefloats(iceFloats);
                }
            }
        }
    } // DONE

    private static void buildAction(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length != 2){
            String usage = "buildAction <character>";
            wrongUsage(usage);
        }else{
            ArrayList<Character> characters = c.getCharacters();

            Character character = null;

            for(Character c : characters){
                if(c.getName().equals(params[1])) character = c;
            }

            if(character == null){
                System.out.println("Error: Character with this name doesn't exist!");
                return;
            }

            boolean hasAction = false;
            ArrayList<Class<? extends Action>> actions = character.getActions();
            for(Class<? extends Action> action : actions){
                if(action.getCanonicalName().equals(BuildAction.class.getCanonicalName()))
                    hasAction = true;
            }
            if(!hasAction){
                System.out.println("Error: You cannot build igloo as a researcher.");
                return;
            }


            BuildAction ba = new BuildAction(character);
            ba.performAction();
            printState(character);
            printState(character.getPosition());

            c.setCharacters(characters);

            ArrayList<IceFloat> iceFloats = c.getIcefloats();

            for(int i = 0; i < iceFloats.size(); i++){
                if(iceFloats.get(i).getId() == character.getPosition().getId()) {
                    iceFloats.set(i, character.getPosition());
                    c.setIcefloats(iceFloats);
                }
            }
        }
    } // DONE

    private static void buildTentAction(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length != 2){
            String usage = "buildTentAction <character>";
            wrongUsage(usage);
        }else{

            ArrayList<Character> characters = c.getCharacters();

            Character character = null;

            for(Character c : characters){
                if(c.getName().equals(params[1])) character = c;
            }

            if(character == null){
                System.out.println("Error: Character with this name doesn't exist!");
                return;
            }

            boolean hastent = false;
            ArrayList<Item> items = character.getItems();
            for(Item i: items){
                if(i.getName().equals(ItemTypes.Tent))
                    hastent = true;
            }

            if(!hastent){
                System.out.println("Error: Character doesn't have a tent.");
            }

            BuildTentAction bta = new BuildTentAction(character);
            bta.performAction();
            printState(character);
            printState(character.getPosition());

            c.setCharacters(characters);

            ArrayList<IceFloat> iceFloats = c.getIcefloats();

            for(int i = 0; i < iceFloats.size(); i++){
                if(iceFloats.get(i).getId() == character.getPosition().getId()) {
                    iceFloats.set(i, character.getPosition());
                    c.setIcefloats(iceFloats);
                }
            }
        }
    } // DONE

    private static void checkAction(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length != 3){
            String usage = "checkAction <character> <float>";
            wrongUsage(usage);
        }else{

            int pos = 0;
            try{
                pos = Integer.parseInt(params[2]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: Second argument must be an integer value.");
            }

            ArrayList<Character> characters = c.getCharacters();
            ArrayList<IceFloat> iceFloats = c.getIcefloats();

            Character character = null;

            for(Character c : characters){
                if(c.getName().equals(params[1])) character = c;
            }

            if(character == null){
                System.out.println("Error: Character with this name doesn't exist!");
                return;
            }

            IceFloat iceFloat = null;

            for(IceFloat i : iceFloats){
                if(i.getId() == pos)
                    iceFloat = i;
            }

            if(iceFloat == null){
                System.out.println("Error: Icefloat with this id doesn't exist!");
                return;
            }

            boolean hasAction = false;
            ArrayList<Class<? extends Action>> actions = character.getActions();
            for(Class<? extends Action> action : actions){
                if(action.getCanonicalName().equals(CheckAction.class.getCanonicalName()))
                    hasAction = true;
            }
            if(!hasAction){
                System.out.println("Error: You cannot check capacity as an eskimo.");
                return;
            }


            CheckAction ca = new CheckAction(character, iceFloat);
            ca.performAction();
            printState(character);
            printState(iceFloat);

            c.setCharacters(characters);
            c.setIcefloats(iceFloats);
        }
    } // DONE

    private static void buildRocketAction(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length != 2){
            String usage = "buildRocketAction <character>";
            wrongUsage(usage);
        }else{

            ArrayList<Character> characters = c.getCharacters();

            Character character = null;

            for(Character c : characters){
                if(c.getName().equals(params[1])) character = c;
            }

            if(character == null){
                System.out.println("Error: Character with this name doesn't exist!");
                return;
            }

            BuildRocketAction bra = new BuildRocketAction(character);
            bra.performAction();
            printState(character);

            c.setCharacters(characters);
        }
    } // DONE

    private static void pickupAction(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length != 2){
            String usage = "pickupAction <character>";
            wrongUsage(usage);
        }else{

            ArrayList<Character> characters = c.getCharacters();

            Character character = null;

            for(Character c : characters){
                if(c.getName().equals(params[1])) character = c;
            }

            if(character == null){
                System.out.println("Error: Character with this name doesn't exist!");
                return;
            }

            PickupAction pa = new PickupAction(character);
            pa.performAction();
            printState(character);
            printState(character.getPosition());

            c.setCharacters(characters);

            ArrayList<IceFloat> iceFloats = c.getIcefloats();

            for(int i = 0; i < iceFloats.size(); i++){
                if(iceFloats.get(i).getId() == character.getPosition().getId()) {
                    iceFloats.set(i, character.getPosition());
                    c.setIcefloats(iceFloats);
                }
            }
        }
    } // DONE

    private static void snowstorm(String[] params){

    }

    private static void polarBearMove(String[] params){

    }

    private static void saveOutput(String[] params){

    }

    private static ArrayList<IceFloat> randomizedField(int n, int m){
        return new ArrayList<>();
    }

    private static Character getCharacter(String name){
        return null;
    }

    private static IceFloat getIceFloat(int id){
        IceFloat iceFloat = null;
        for(IceFloat fl : c.getIcefloats()){
            if(fl.getId() == id) iceFloat = fl;
        }
        return iceFloat;
    }


    private static Item createItem(String type){
        switch (type){
            case ItemTypes.BreakableShovel:
                return new BreakableShovel();
            case ItemTypes.DiverSuit:
                return new DiverSuit();
            case ItemTypes.Food:
                return new Food();
            case ItemTypes.RocketPart:
                return new RocketPart();
            case ItemTypes.Rope:
                return new Rope();
            case  ItemTypes.Shovel:
                return new Shovel();
            case ItemTypes.Tent:
                return new Tent();
            default:
                System.out.println("Error: no such item.");
                return null;
        }
    }

    private static Character createCharacter(String type, String name, StableIceFloat startPosition){
        switch (type){
            case CharacterTypes.eskimo:
                return new Eskimo(name, startPosition);
            case CharacterTypes.researcher:
                return new Researcher(name, startPosition);
            default:
                System.out.println("Error: no such character type.");
                return null;
        }
    }

    private static IceFloat createIceFloat(String type){
        String floatType = type;
        int capacity = 6;
        if(type.contains("i")){
            floatType = "i";
            capacity = Integer.parseInt(type.substring(1, 1));
        }
        switch (floatType){
            case "s":
                return new StableIceFloat();
            case "h":
                return new Hole();
            case "i":
                return new UnstableIceFloat(capacity);
        }
    }
}
