import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Represents a stack which holds the ActionCommands that the
 * user has entered so far.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 3 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class UndoRedoStack {
    //Data fields
    public final int CAPACITY = 100;
    ArrayList<ActionCommand> action = new ArrayList<>();
    private int top;


    //Constructor

    /**
     * Constructor for an UndoRedoStack object and initializes
     * data field top to -1 and sets action array to 100
     * <dt>Postconditions:</dt>
     * <dd>Data fields top and data are initialized</dd>
     */
    public UndoRedoStack(){
        top = -1;
    }

    //Accessors

    /**
     * Gets the index value of top
     * @return
     *      the index value at top of the stack
     */
    public int getTop(){
        return top;
    }

    //Mutators

    /**
     * Sets the index value of the top
     * @param top
     *      index value at top of stacks
     */
    public void setTop(int top){
        this.top = top;
    }


    //Methods

    /**
     * Pushes a new ActionCommand object on top of the stack
     * <dt><b>Preconditions:</b></dt>
     * <dd>The stack is not full.</dd>
     * @param a
     *      an ActionCommand object that is to be put on a stack
     * <dt>Postconditions:</dt>
     * <dd>Stack is incremented by one, and the ActionCommand object
     * is placed on top of stack</dd>
     */
    public void push(ActionCommand a){
        top++;
        action.add(a);

    }

    /**
     * Pops an ActionCommand object off the stack
     *
     * <dt><b>Preconditions:</b></dt>
     * <dd>The stack is not empty</dd>
     * @return
     *      the ActionCommand object that is popped
     * <dt><b>Postconditions:</b></dt>
     * <dd>Top of the stack containing an ActionCommand object
     * is popped</dd>
     */
    public ActionCommand pop() {
        ActionCommand a;

        //Check if the stack is empty
        if(top == -1)
            throw new EmptyStackException();

        a = action.remove(top);
        top--;
        return a;
    }

    /**
     * Peeks the ActionCommand on top of the stack
     * <dt><b>Preconditions:</b></dt>
     * <dd>The stack is not empty</dd>
     * @return
     *      Returns the value of the ActionCommand object on top
     *      of the stack
     */
    public ActionCommand peek(){
        ActionCommand a;

        //Check if the stack is empty
        if(top == -1)
            throw new EmptyStackException();

        a = action.get(top);
        return a;
    }

    /**
     * Checks whether the stack is empty
     * @return
     *      Returns true if the stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return (top == -1);
    }


    /**
     * Clears a stack completely.
     * <dt><b>Postconditions:</b></dt>
     * <dd>Has removed every item in the Stack and set top to -1</dd>
     */
    public void clearStack(){
        action.clear();
        top = -1;
    }

    /**
     * String representation of the stack for Undo or Redo
     * @return
     *      String representation of the stack which hold all the
     *      actions.
     */
    @Override
    public String toString(){
        String pStack = "";

        //Check if the stack is empty
        if(top == -1)
            return pStack = "[empty]";

        for(int i = top; i >= 0; i--)
            pStack+= action.get(i).toString() + "\n";

        return  pStack;
    }
}
