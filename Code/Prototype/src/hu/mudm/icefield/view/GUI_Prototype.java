package hu.mudm.icefield.view;

import hu.mudm.icefield.Game;
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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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
    public final static String startGame            = "startGame";
    public final static String exit                 = "exit";
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
public class GUI_Prototype implements GUI{

    private static Controller c;
    private static Game game;

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

    private static void p(String s) { System.out.println(s); }

    @Override
    public int getAction(Character character) throws NoActionException {
        //Create List of Actions
        ArrayList<String> actions = new ArrayList<>();
        for (Class<? extends Action> a: character.getActions()) {
            actions.add(a.toString());
        }

        if(actions.size()<1) throw new NoActionException();

        //List the actions
        p("Please choose one of the following Actions: (INPUT: name of action)");
        for (String a:actions) {
            p("\t"+a);
        }

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            while(!actions.contains(input)) {                
                p(prompt);
                input = reader.readLine();
                if(!actions.contains(input)) {
                    p("Please enter the name of one of the Actions above");
                }
            }
        } catch (IOException e) { //exception while reading input
            p("IOEXCEPTION WHILE READING INPUT");
            e.printStackTrace();
            p("ASSUMING INPUT IS "+actions.get(0));
            input = actions.get(0); //assume input is a 0
        }
        return actions.indexOf(input);
    }

    @Override
    public int getChosenNeighborID(IceFloat icefloat) throws NoNeighborException {
        //Create List of IDs
        ArrayList<Integer> ids = new ArrayList<>();
        for (IceFloat neighbor: icefloat.getNeighbors()) {
            ids.add(neighbor.getID());
        }

        if(ids.size()<1) throw new NoNeighborException();

        //List the neighbors
        p("Please choose a neighbor of the IceFloat as a target! (INPUT: number)");
        p("The neighbors:");
        for (Integer id:ids) {
            p("\t"+id.toString());
        }

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String input;
        int i=-1;
        try {
            while(!ids.contains(i)) {
                if(i!=-1)
                    p("Please enter a valid number");
                p(prompt);
                input = reader.readLine();
                try {
                    i = Integer.parseInt(input);
                } catch (NumberFormatException e) { //input is NaN
                    p("Please enter a number");
                    i=-1;
                }
            }
        } catch (IOException e) { //exception while reading input
            p("IOEXCEPTION WHILE READING INPUT");
            e.printStackTrace();
            p("ASSUMING INPUT IS "+ids.get(0).toString());
            i=ids.get(0); //assume input is a 0
        }
        return i;
    }

    @Override
    public ArrayList<Character> getCharacters(){
        ArrayList<Character> characters = new ArrayList<>();
        Random random = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashSet<String> names = new HashSet<>();

        try {
            int count = 0;
            boolean justright = false;
            while(!justright){
                System.out.print("How many characters do you want to add? ");
                count = Integer.parseInt(br.readLine());
                if(count >= 3 && count <= 6)
                    justright = true;
                else{
                    System.out.println("Player count must be between 3 and 6.");
                }
            }


            while (characters.size() < count) {

                int rand = random.nextInt(2);
                String type = rand==0 ? "Researcher" : "Eskimo";
                System.out.println("New " + type);
                System.out.print("Your name: ");
                String name = br.readLine();
                if(!names.contains(name)) {
                    names.add(name);
                    Character character = null;
                    if (rand == 0) {
                        character = new Researcher(name, (StableIceFloat) c.getIcefloats().get(0));
                    } else {
                        character = new Eskimo(name, (StableIceFloat) c.getIcefloats().get(0));
                    }
                    characters.add(character);
                }else{
                    System.out.println("Error: this name is taken.");
                }
            }
        }catch (Exception e){
            System.out.println("An exception occured");
            return null;
        }

        return characters;
    }

    public static void setGame(Game _game){
        game = _game;
        c = game.GetController();
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
                case Commands.startGame:
                    game.init();
                    game.start();
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
        sb.append(iceFloat.getID());
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
        System.out.println("Polar bear: Position: " + bear.getPosition().getID());
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
            ArrayList<IceFloat> iceFloats = new ArrayList<>();
            ArrayList<Character> charactersList = new ArrayList<>();
            try {
                File file = new File(params[1]);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("icefield");
                NodeList config = (NodeList) list.item(0);
                NodeList field = (NodeList) config.item(0);
                NodeList characters = (NodeList) config.item(1);
                Element polarBear = null;
                if(config.getLength() >= 3)
                    polarBear = (Element)config.item(2);

                // parse icefloats
                for(int i = 0; i < field.getLength(); i++){
                    Element iceFloatElement = (Element)field.item(i);
                    String type = iceFloatElement.getAttribute("type");
                    if(i == 0 && !type.equals(IceFloatTypes.stable)){
                        System.out.println("Error: First icefloat must be stable.");
                        return;
                    }

                    int capacity = Integer.parseInt(iceFloatElement.getAttribute("capacity"));
                    String item = iceFloatElement.getAttribute("item");
                    int snowCount = Integer.parseInt(iceFloatElement.getAttribute("snowcount"));
                    String igloo = iceFloatElement.getAttribute("igloo");
                    String tent = iceFloatElement.getAttribute("tent");

                    IceFloat iceFloat = null;
                    switch (type){
                        case IceFloatTypes.stable:
                            iceFloat = new StableIceFloat();
                            break;
                        case IceFloatTypes.unstable:
                            iceFloat = new UnstableIceFloat(capacity);
                            break;
                        case IceFloatTypes.hole:
                            iceFloat = new Hole();
                            break;
                        default:
                            System.out.println("Error: non-existing icefloat type.");
                            return;
                    }

                    iceFloat.setID(i);

                    for(int j = 0; j < snowCount; j++){
                        iceFloat.addSnow();
                    }

                    if(!type.equals(IceFloatTypes.hole)){
                        Item newItem = createItem(item);
                        if(newItem != null)
                            iceFloat.setItem(newItem);

                        if(tent.equals("yes") && igloo.equals("yes"))
                        {
                            System.out.println("Error: an icefloat cannot have both tent an igloo on it.");
                            return;
                        }

                        if(tent.equals("yes") && igloo.equals("no")){
                            iceFloat.buildTent();
                        }

                        if(igloo.equals("yes") && tent.equals("no")){
                            iceFloat.buildIgloo();
                        }
                    }

                    iceFloats.add(iceFloat);
                }

                for(int i = 0; i < iceFloats.size() - 1; i++){
                    iceFloats.get(i).setNeighbor(iceFloats.get(i+1));
                }

                for(IceFloat iceFloat : iceFloats){
                    printState(iceFloat);
                }
                c.setIcefloats(iceFloats);

                // parse characters
                for(int i = 0; i < characters.getLength(); i++){
                    Element characterElement = (Element) characters.item(i);
                    String type = characterElement.getAttribute("type");
                    String name = characterElement.getAttribute("name");
                    int position = Integer.parseInt(characterElement.getAttribute("position"));
                    if(position >= iceFloats.size()){
                        System.out.println("Error: icefloat doesn't exist!");
                        break;
                    }
                    int temp = Integer.parseInt(characterElement.getAttribute("bodytemp"));

                    Character newCharacter = createCharacter(type, name, (StableIceFloat) iceFloats.get(0));

                    NodeList characterItems = (NodeList)((NodeList)characterElement).item(4);
                    for(int j = 0; j < characterItems.getLength(); j++){
                        Element characterItem = (Element)characterItems.item(j);
                        String itemType = characterItem.getAttribute("type");
                        Item newItem = createItem(itemType);
                        if(newItem == null){
                            System.out.println("Error: This kind of item doesn't exist.");
                            return;
                        }
                        newCharacter.addItem(newItem);
                    }
                    newCharacter.setTemp(temp);

                    for(IceFloat ice : iceFloats) {
                        if (ice.getID() == position){
                            if(ice.getType().equals(IceFloatTypes.hole)){
                                System.out.println("Error: you cannot put character in a hole!");
                                return;
                            }
                            newCharacter.setPosition(ice);
                            charactersList.add(newCharacter);
                            ice.stepOn(newCharacter);
                        }
                    }
                }

                for(Character character : charactersList){
                    printState(character);
                }
                c.setCharacters(charactersList);

                // parse polarbear
                if(polarBear != null){
                    int position = Integer.parseInt(polarBear.getAttribute("position"));
                    for(IceFloat i: iceFloats){
                        if(i.getID() == position){
                            PolarBear bear = new PolarBear(i);
                            c.setPolarBear(bear);
                            printState(bear);
                        }
                    }
                }

                NodeList test = (NodeList) list.item(1);
                if(test.getLength() > 0)
                    state = 1;

                String name, pos;
                // XML commands
                for(int i = 0; i < test.getLength(); i++){
                    String actionType = test.item(i).getNodeName();
                    switch (actionType){
                        case "moveaction":
                            name = ((Element)test.item(i)).getAttribute("character");
                            pos = ((Element)test.item(i)).getAttribute("icefloat");
                            String[] parameters1 = {actionType, name, pos};
                            moveAction(parameters1);
                            break;
                        case "shovelaction":
                            name = ((Element)test.item(i)).getAttribute("character");
                            String[] parameters2 = {actionType, name};
                            shovelAction(parameters2);
                            break;
                        case "buildaction":
                            name = ((Element)test.item(i)).getAttribute("character");
                            String[] parameters3 = {actionType, name};
                            buildAction(parameters3);
                            break;
                        case "buildtentaction":
                            name = ((Element)test.item(i)).getAttribute("character");
                            String[] parameters4 = {actionType, name};
                            buildTentAction(parameters4);
                            break;
                        case "checkaction":
                            name = ((Element)test.item(i)).getAttribute("character");
                            pos = ((Element)test.item(i)).getAttribute("icefloat");
                            String[] parameters5 = {actionType, name, pos};
                            checkAction(parameters5);
                            break;
                        case "buildrocketaction":
                            name = ((Element)test.item(i)).getAttribute("character");
                            String[] parameters6 = {actionType, name};
                            buildRocketAction(parameters6);
                            break;
                        case "pickupaction":
                            name = ((Element)test.item(i)).getAttribute("character");
                            String[] parameters7 = {actionType, name};
                            pickupAction(parameters7);
                            break;
                        case "polarmove":
                            pos = ((Element)test.item(i)).getAttribute("icefloat");
                            String[] parameters8 = {actionType, pos};
                            polarBearMove(parameters8);
                            break;
                        case "saveoutput":
                            String location = ((Element)test.item(i)).getAttribute("location");
                            String[] parameters9 = {actionType, location};
                            saveOutput(parameters9);
                            break;
                        case "snowstorm":
                            NodeList floats = (NodeList) test.item(i);
                            if(floats.getLength() == 0){
                                String[] parameters10 = {actionType};
                            }else{
                                int n = floats.getLength();
                                String[] parameters10 = new String[n + 2];
                                parameters10[0] = actionType;
                                parameters10[1] = "" + n;
                                for(int j = 0; j < floats.getLength(); j++){
                                    parameters10[j + 2] = ((Element)floats.item(j)).getAttribute("id");
                                }
                                snowstorm(parameters10);
                            }
                            break;
                        default:
                            System.out.println("Error: unknown command!");
                            return;
                    }
                }



            }catch (Exception e){
                System.out.println("An exception occured!");
                return;
            }
        }
    } // DONE

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

                int id = 0;
                // Generating icefloats, not implementing neighbourhood yet
                for(String s : field){
                    IceFloat iceFloat = createIceFloat(s);
                    iceFloat.setID(id);
                    iceFloats.add(createIceFloat(s));
                    id++;
                }

                // Making neighbours neighbours
                for(int i = 0; i < iceFloats.size(); i++){
                    if((i + 1) % m > (i % m)) iceFloats.get(i).setNeighbor(iceFloats.get(i+1));
                    if((i + m) < n*m) iceFloats.get(i).setNeighbor(iceFloats.get(i + m));
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
                if(iceFloat.getID() == floatID){
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
                    if(iceFloat.getID() == floatID){
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
                if(iceFloat.getID() == floatID){
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
                if(i.getID() == floatID){
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
        }
    } // DONE

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
                if(i.getID() == pos) iceFloat = i;
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
                if(iceFloats.get(i).getID() == character.getPosition().getID()) {
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
                if(iceFloats.get(i).getID() == character.getPosition().getID()) {
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
                if(iceFloats.get(i).getID() == character.getPosition().getID()) {
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
                if(i.getID() == pos)
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
                if(iceFloats.get(i).getID() == character.getPosition().getID()) {
                    iceFloats.set(i, character.getPosition());
                    c.setIcefloats(iceFloats);
                }
            }
        }
    } // DONE

    private static void snowstorm(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length == 1){
            c.snowstorm();
            for(Character character : c.getCharacters()){
                printState(character);
            }
            for(IceFloat iceFloat : c.getIcefloats()){
                printState(iceFloat);
            }
        }else if(params.length > 2){
            int count = 0;
            try{
                count = Integer.parseInt(params[1]);
            }catch (NumberFormatException nfe){
                System.out.println("Error: Second argument must be and integer value.");
                return;
            }

            if(count != params.length - 2){
                System.out.println("Error: The number of floats must be equal to the second argument.");
                return;
            }

            ArrayList<IceFloat> iceFloats = c.getIcefloats();
            HashSet<Integer> floats = new HashSet<>();

            for(int i = 2; i < params.length; i++){
                int floatID = 0;
                try{
                    floatID = Integer.parseInt(params[i]);
                }catch (NumberFormatException nfe){
                    System.out.println("Error: Argument " + (i - 1) + " must be and integer value.");
                    return;
                }
                floats.add(floatID);
            }

            for(IceFloat i : iceFloats){
                if(floats.contains(i.getID())){
                    i.addSnow();
                    ArrayList<Character> characters = i.getCharacters();
                    for(Character c : characters){
                        printState(c);
                    }
                    printState(i);
                }
            }
            c.setIcefloats(iceFloats);
        } else{
            String usage = "snowstorm <n> <f1> <f2> ... <fn>";
            wrongUsage(usage);
        }
    } // DONE

    private static void polarBearMove(String[] params){
        if(state == 0){
            System.out.println("Error: You cannot do this in creation state.");
        }else if(params.length == 1){
            c.getPolarBear().Wake();
            printState(c.getPolarBear());
        }else if(params.length == 2){
            int nextPosID = Integer.parseInt(params[1]);
            ArrayList<IceFloat> neighbours = c.getPolarBear().getPosition().getNeighbors();
            IceFloat target = null;
            for(IceFloat iceFloat : neighbours){
                if(iceFloat.getID() == nextPosID)
                    target = iceFloat;
            }
            if(target == null){
                System.out.println("Error: the bear cannot reach that icefloat.");
                return;
            }
            c.getPolarBear().setPosition(target);
            printState(c.getPolarBear());
        }else{
            String usage = "polarBearMove <direction>";
            wrongUsage(usage);
        }
    } // DONE

    private static void saveOutput(String[] params){
        if(params.length != 2){
            String usage = "saveOutput <location>";
            wrongUsage(usage);
            return;
        }

        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // <icefield>
            Element root = document.createElement("icefield");
            Attr attr = document.createAttribute("state");
            String state;
            if(c.getisWon()) state = "won";
            else if(c.getisLost()) state = "lost";
            else state = "ongoing";
            attr.setValue(state);
            root.setAttributeNode(attr);
            document.appendChild(root);

            Element config = document.createElement("config");
            root.appendChild(config);

            Element floats = document.createElement("icefloats");
            config.appendChild(floats);

            // serializing icefloats
            for(IceFloat i : c.getIcefloats()){
                Element iceFloat = document.createElement("icefloat");
                //---------
                Element type = document.createElement("type");
                String types = i.getType();
                type.appendChild(document.createTextNode(types));
                iceFloat.appendChild(type);
                //---------
                Element capacity = document.createElement("capacity");
                String capacitys;
                if(type.equals(IceFloatTypes.stable) && type.equals(IceFloatTypes.hole)){
                    capacitys = "";
                }else{
                    capacitys = "" + i.getCapacity();
                }
                capacity.appendChild(document.createTextNode(capacitys));
                iceFloat.appendChild(capacity);
                //---------
                Element item = document.createElement("item");
                Item floatsItem = i.getItem();
                String items;
                if(floatsItem == null) items = "";
                else items = floatsItem.getName();
                item.appendChild(document.createTextNode(items));
                iceFloat.appendChild(item);
                //---------
                Element snowCount = document.createElement("snowcount");
                snowCount.appendChild(document.createTextNode("" + i.getSnowLevel()));
                iceFloat.appendChild(snowCount);
                //---------
                Element igloo = document.createElement("igloo");
                String hasIgloo = (i.hasIglu()) ? "true" : "false";
                igloo.appendChild(document.createTextNode(hasIgloo));
                iceFloat.appendChild(igloo);
                //---------
                Element tent = document.createElement("tent");
                String hasTent = (i.hasTent()) ? "true" : "false";
                tent.appendChild(document.createTextNode(hasTent));
                iceFloat.appendChild(tent);
                //---------
                floats.appendChild(iceFloat);
            }

            Element characters = document.createElement("characters");
            config.appendChild(characters);

            // serializing characters
            for(Character character : c.getCharacters()){
                Element characterNode = document.createElement("character");
                //----------
                Element type = document.createElement("type");
                ArrayList<Class<? extends Action>> actions = character.getActions();
                boolean researcher = false;
                for(Class<? extends Action> action : actions){
                    if(action.getCanonicalName().equals(CheckAction.class.getCanonicalName()))
                        researcher = true;
                }
                String types = researcher ? "Researcher" : "Eskimo";
                type.appendChild(document.createTextNode(types));
                characterNode.appendChild(type);
                //---------
                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(character.getName()));
                characterNode.appendChild(name);
                //---------
                Element position = document.createElement("position");
                position.appendChild(document.createTextNode("" + character.getPosition().getID()));
                characterNode.appendChild(position);
                //---------
                Element temp = document.createElement("bodytemp");
                temp.appendChild(document.createTextNode("" + character.getTemp()));
                characterNode.appendChild(temp);
                //---------
                characters.appendChild(characterNode);
            }

            //transform DOM to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(params[1]));

            transformer.transform(domSource, streamResult);

        }catch (Exception e){
            System.out.println("Exception while parsing output.");
            e.printStackTrace();
            return;
        }

    } // DONE


    private static ArrayList<IceFloat> randomizedField(int n, int m){
        Random rand = new Random();
        ArrayList<IceFloat> field = new ArrayList<>();
        IceFloat iceFloat;
        for(int i = 0; i < n * m; i++){
            int random = rand.nextInt(3);
            switch (random){
                case 0:
                    iceFloat = new StableIceFloat();
                    iceFloat.setID(i);
                    field.add(iceFloat);
                    break;
                case 1:
                    iceFloat = new Hole();
                    iceFloat.setID(i);
                    field.add(iceFloat);
                    break;
                case 2:
                    int capacity = rand.nextInt(6) + 1;
                    iceFloat = new UnstableIceFloat(capacity);
                    iceFloat.setID(i);
                    field.add(iceFloat);
                    break;
            }
            if(!field.get(0).getType().equals(IceFloatTypes.stable)){
                IceFloat stable = new StableIceFloat();
                stable.setID(0);
                field.set(0, stable);
            }
        }

        for(int i = 0; i < field.size(); i++){
            if((i + 1) % m > (i % m)) field.get(i).setNeighbor(field.get(i+1));
            if((i + m) < n*m) field.get(i).setNeighbor(field.get(i + m));
        }

        return field;
    } // DONE

    private static IceFloat getIceFloat(int id){
        IceFloat iceFloat = null;
        for(IceFloat fl : c.getIcefloats()){
            if(fl.getID() == id) iceFloat = fl;
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
            capacity = java.lang.Character.getNumericValue(type.charAt(1));
        }
        switch (floatType){
            case "s":
                return new StableIceFloat();
            case "h":
                return new Hole();
            case "i":
                return new UnstableIceFloat(capacity);
            default:
                return null;
        }
    }
}