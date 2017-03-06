import java.util.*;

/**
 * This is the driver class for the SlideShow
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 3 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class SlideShowManager {

    public static void main(String[] args){
        //Input scanner
        Scanner input = new Scanner(System.in);

        //Variables
        int positionOne, positionTwo;
        String photo, choice;

        //SlideShow object
        ArrayList<String> slideshow = new ArrayList<>();

        //UndoRedoStack objects for an Undo Stack and a Redo Stack
        UndoRedoStack undoStack = new UndoRedoStack();
        UndoRedoStack redoStack = new UndoRedoStack();

        do{
            println("\nA) Add a photo");
            println("R) Remove a photo");
            println("S) Swap Photos");
            println("M) Move Photo");
            println("P) Print");
            println("Z) Undo");
            println("Y) Redo");
            println("Q) Quit");
            println("\n");
            System.out.print("Please select an option:");

            choice = input.nextLine();

            switch (choice.toUpperCase()){
                case "A":
                    //New ActionCommand object of type ADD
                    ActionCommand add  = new ActionCommand(ActionType.ADD);

                    //Enter and add photo name
                    print("Please enter the photo name: ");
                    photo = input.nextLine();
                    add.setPhoto(photo);

                    //Enter and add the position of the photo
                    positionOne = getInputInt("Please enter the position: ", 1, slideshow.size() + 1);
                    add.setPositionOne(positionOne - 1);

                    //Add the photo
                    add.perform(slideshow);

                    //Add action to Undo Stack
                    undoStack.push(add);

                    //Clear Redo stack
                    redoStack.clearStack();
                    break;

                case "R":
                    //Check if the slideshow is empty
                    if(slideshow.size() == 0){
                        println("Empty slideshow. Nothing to remove.");
                        break;
                    }
                    //New ActionCommand object of type REMOVE
                    ActionCommand remove  = new ActionCommand(ActionType.REMOVE);

                    //Enter the remove position of the photo
                    positionOne = getInputInt("Please enter the position of the photo to be removed: ", 1, slideshow.size());
                    remove.setPositionOne(positionOne - 1);

                    //Remove the photo
                    remove.perform(slideshow);

                    //Remove action to Undo Stack
                    undoStack.push(remove);

                    //Clear Redo stack
                    redoStack.clearStack();
                    break;

                case "S":
                    //Check if there's more than one photo
                    if(slideshow.size() <= 1){
                        println("There's less than one photo. Nothing to swap.");
                        break;
                    }
                    //New ActionCommand object of type SWAP
                    ActionCommand swap = new ActionCommand(ActionType.SWAP);

                    //Enter the position of the first photo to be swapped from
                    positionOne = getInputInt("Please enter the first position: ",1, slideshow.size());
                    swap.setPositionOne(positionOne - 1);

                    //Enter the position of the second photo
                    positionTwo = getInputInt("Please enter the second position: ", 1, slideshow.size());
                    swap.setPositionTwo(positionTwo - 1);

                    //Swap the two photos
                    swap.perform(slideshow);

                    //Swap action to Undo Stack
                    undoStack.push(swap);

                    //Clear Redo Stack
                    redoStack.clearStack();
                    break;

                case "M":
                    if(slideshow.size() == 0){
                        println("Empty slideshow. Nothing to move.");
                        break;
                    }
                    //New ActionCommand object of type MOVE
                    ActionCommand move = new ActionCommand(ActionType.MOVE);

                    //Enter the source position
                    positionOne = getInputInt("Please enter source position: ",1, slideshow.size());
                    move.setPositionOne(positionOne - 1);

                    //Enter the destination position
                    positionTwo = getInputInt("Please enter the destination position: ", 1, slideshow.size());
                    move.setPositionTwo(positionTwo - 1);

                    //Move the photo
                    move.perform(slideshow);

                    //Move action to Undo Stack
                    undoStack.push(move);

                    //Clear Redo Stack
                    redoStack.clearStack();
                    break;

                case "P":
                    //Print slideshow
                    println("Slideshow:");
                    println("---------------------------------------------------------------------------");
                    for(int i = 0; i < slideshow.size(); i++){
                        print((i+1) + "." + slideshow.get(i) + " ");
                    }

                    //Redo Undo Stack print
                    println("\n"); //New linw
                    println("Undo Stack:");
                    println(undoStack.toString());
                    println("\nRedo Stack:");
                    println(redoStack.toString());
                    break;

                case "Z":
                    //Be prepared to catch Empty Stack Exception
                    try {
                        //Get the last ActionCommand on UndoStack
                        ActionCommand inverse = undoStack.peek().getInverse();
                        inverse.perform(slideshow); //Perform the inverse

                        //Pop the stack off the UndoStack and add it to Redo Stack
                        redoStack.push(undoStack.pop());
                    }catch (EmptyStackException ex){
                        println("Empty Stack!");
                    }
                    break;

                case "Y":
                    //Be prepared to catch Empty Stack Exception
                    try {
                        //Redo the last ActionCommand that was undone.
                        ActionCommand redo = redoStack.pop();
                        redo.perform(slideshow);

                        //Add the redone ActionCommand to UndoStack
                        undoStack.push(redo);
                    }catch (EmptyStackException ex){
                        println("Empty Stack!");
                    }
                    break;

                case "Q":
                    break;

                default:
                    println("Invalid input. Please try again.");
            }

        }while(!choice.equals("Q"));

        println("Thank you for using my awesome program!");
    }

    /**
     * Prints the message without line skip
     * @param message
     *      Message that needs to be output.
     */
    public static void print(String message){
        System.out.print(message);
    }

    /***
     * Prints the message that is passed with a new line
     * @param message
     *      Message that needs to be output.
     */
    public static void println(String message){
        System.out.println(message);
    }

    /**
     * Get an input value for an int
     * @param message
     *      message that is passed in the method
     * @param lowerBound
     *      minimum value for int
     * @param upperBound
     *      maximum value for int
     * @return
     *      Int value
     */
    public static int getInputInt(String message, int lowerBound, int upperBound){
        int value;
        Scanner in = new Scanner(System.in);

        while(true) {
            try {
                print(message);
                value = in.nextInt();
                in.nextLine();

                if(value < lowerBound || value > upperBound){
                    println("Error: outside range [" + lowerBound + "," + upperBound + "]");
                }else{
                    break;
                }
            } catch (InputMismatchException ex){
                System.out.println("Invalid int. Please try again.");
                in.nextLine();
            }

        }
        return value;
    }


}
