import java.util.ArrayList;
import java.util.Collections;

/**
 * The ActionCommand class represents a fully desbribed
 * command that the has completed for the slideshow. This
 * class adds, removes, swaps, and moves
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 3 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class ActionCommand {
    //Data fields
    private int positionOne;
    private int positionTwo;
    private String photo;
    final ActionType type;


    //Constructor

    /**
     * Constructor for a new ActionCommand object and
     * initializes ActionType to the parameter passed
     * @param type
     *      Type of Action needed to perform.
     * <dt>Postconditions:</dt>
     *      <dd>The ActionCommand has been initialized
     *      with type of Action set.</dd>
     */
    public ActionCommand(ActionType type){
        this.type = type;
    }


    //Accessors

    /**
     * Receives the value of positionOne
     * @return
     *      the value of postionOne
     */
    public int getPositionOne(){
        return positionOne;
    }

    /**
     * Receives the value of positionTwo
     * @return
     *      the value of positionTwo
     */
    public int getPositionTwo(){
        return positionTwo;
    }

    /**
     * Receives the name of the photo
     * @return
     *      the name of the photo
     */
    public String getPhoto(){
        return photo;
    }

    /**
     * Returns the actionType of the object
     * @return
     *      the ActionType of the object
     */
    public ActionType getActionType(){
        return type;
    }

    //Mutators

    /**
     * Sets the value for positionOne
     * @param positionOne
     *      value of positionOne
     */
    public void setPositionOne(int positionOne){
        this.positionOne = positionOne;
    }

    /**
     * Sets the value for positionTwo
     * @param positionTwo
     *      value of positionTwo
     */
    public void setPositionTwo(int positionTwo){
        this.positionTwo = positionTwo;
    }

    /**
     * Sets the name of the photo.
     * @param photo
     *      name of the photo
     */
    public void setPhoto(String photo){
        this.photo = photo;
    }


    //Methods

    /**
     * Performs the action  of ActionType requested by the user.
     *
     * @param slideshow
     *      slideshow of photos passed
     *
     * <dt>Postconditions:</dt>
     * <dd>Slideshow is appended.</dd>
     */
    public void perform(ArrayList<String> slideshow){
        switch (type){
            case ADD:
                slideshow.add(positionOne, photo);
                break;

            case MOVE:
                photo = slideshow.get(positionOne);
                String photo2 = slideshow.get(positionTwo);
                if(positionOne > positionTwo){
                    int temp = positionOne;
                    positionOne = positionTwo;
                    positionTwo = temp;
                }
                //slideshow.add(positionTwo, photo);
                //slideshow.remove(positionOne);
                Collections.rotate(slideshow.subList(positionOne, positionTwo+1), -1);
                break;

            case REMOVE:
                photo = slideshow.get(positionOne);
                slideshow.remove(positionOne);
                break;

            case SWAP:
                Collections.swap(slideshow,positionOne,positionTwo);
                break;
        }
    }

    /**
     * Generates a new action command that would undo undo the most recent
     * action command.
     * @return
     *      The opposite action of the most recent undo action.
     */
    public ActionCommand getInverse(){
        ActionCommand action = null; //= new ActionCommand(null);

        switch (type){
            case ADD:
                action = new ActionCommand(ActionType.REMOVE);
                //action.setPhoto(photo);
                action.setPositionOne(positionOne);
                break;

            case REMOVE:
                action = new ActionCommand(ActionType.ADD);
                action.setPhoto(photo);
                action.setPositionOne(positionOne);
                break;

            case SWAP:
                action = new ActionCommand(ActionType.SWAP);
                action.setPositionOne(positionOne);
                action.setPositionTwo(positionTwo);
                break;

            case MOVE:
                action = new ActionCommand(ActionType.MOVE);
                //action.setPhoto(photo);
                action.setPositionOne(positionOne);
                action.setPositionTwo(positionTwo);
        }
        return action;
    }

    /**
     * String representation of the action done to slideshow.
     * @return
     *      String representation of the action done to slideshow.
     */
    @Override
    public String toString(){
        if(type == ActionType.ADD)
            return type + " " + photo + " in position " + (positionOne + 1);
        else if(type == ActionType.REMOVE)
            return type + " " + photo + " in position " + (positionOne + 1);
        else if(type == ActionType.SWAP)
            return type + " position " + (positionOne + 1) + " and position " + (positionTwo + 1);
        else
            return type + " position " + (positionOne + 1) + " to position " + (positionTwo + 1);
    }
}
