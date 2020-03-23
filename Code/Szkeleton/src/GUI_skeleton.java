//List of used imports
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.ArrayList;

//This class is to get one character from console between given choices.
//There is two static methods in this class, which are frequent cases of usage of this class.
//Current version of this class is case sensitive.
public class GUI_skeleton {
    //List of choices and their descriptions
    private ArrayList<Character> choices;
    private ArrayList<String> descriptions;

    //Constructor
    public GUI_skeleton(){
        choices = new ArrayList<>();
        descriptions = new ArrayList<>();
    }

    //With this method can a new choice be added to the list with its description.
    //It is possible to give a choice without description with an another method.
    //The same choice cannot be added twice, no matter what the description is.
    //There can be two or more choices with the same description
    public void addChoice(char newChoice, @NotNull String description) throws GUI_skeletonException {
        if (choices.contains(newChoice)) throw new GUI_skeletonException("The same choice can not be added twice");
        choices.add(newChoice);
        descriptions.add(description);
    }

    //With this method can a new choice be added to the list without description.
    public void addChoice(char newChoice) throws GUI_skeletonException {
        addChoice(newChoice, "");
    }

    //This is the most important method of the class
    //With this method can a character be chosen from the given choices from the console.
    public char choose() throws GUI_skeletonException, IOException {
        //If there is no choices given beforehand an exception will be thrown.
        if(choices.size() == 0) throw new GUI_skeletonException("You haven't given any choices to choose from");

        //Listing the choices
        System.out.println("Please choose from the following list:");
        for(int i = 0; i < choices.size(); i++){
            System.out.format("\t" + choices.get(i));
            if (!descriptions.get(i).equals(""))
                System.out.format(": " + descriptions.get(i));
            System.out.println();
        }

        //Reading a characters from console, until the character given is between the choices.
        boolean endOfLoop = false;
        char out = ' ';
        while(!endOfLoop){
            out = (char) System.in.read();
            if(choices.contains(out)) endOfLoop = true;
            else if (out != '\n') System.out.println("Given character is not allowed.\nPlease choose from the allowed characters!");
        }

        //Giving one of the choices back
        return out;
    }

    //With this method will a character from console be chosen.
    //This character needs to be the part of the string
    //This method gives back the chosen character
    static public char chooseCharFromString(@NotNull String choices) throws GUI_skeletonException, IOException {
        GUI_skeleton helpVariable = new GUI_skeleton();
        for(int i = 0; i < choices.length(); i++) helpVariable.addChoice(choices.charAt(i), "");
        return helpVariable.choose();
    }

    //With this method can a Yes-or-No question be stated.
    //If Yes is chosen, than the method gives true back.
    //If No is chosen, than the method gives false back.
    static public boolean chooseYesOrNo() throws IOException, GUI_skeletonException {
        GUI_skeleton yesOrNo = new GUI_skeleton();
        yesOrNo.addChoice('Y', "yes");
        yesOrNo.addChoice('N',"no");
        return yesOrNo.choose() == 'Y';
    }

    //This class holds the unique exceptions of this class in order to differentiate from simple exceptions.
    public class GUI_skeletonException extends Exception{
        public GUI_skeletonException(String message){ super(message); }
    }
}
