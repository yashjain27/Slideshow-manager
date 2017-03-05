/**
 * The ActionType class lists the actions and commands the user
 * might execute. The four actions are ADD, REMOVE, SWAP, MOVE
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 3 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public enum ActionType {
    /**
     * ADD - add a photo to a slideshow
     */
    ADD,

    /**
     * REMOVE - remove a photo from a slideshow
     */
    REMOVE,

    /**
     * MOVE - move a photo's position in the slideshow.
     */
    MOVE,

    /**
     * SWAP - swap the positions of two photos.
     */
    SWAP
}
