/**
 * @author Tzahi elhadad
 * ID 206214165
 */
package game;

/**
 * Class for count things.
 */
public class Counter {
    // counter value.
    private int counter;

    /**.
     * Constructor for the game.Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**.
     * Another constructor for Counter.
     * @param num - get number for initiate.
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**.
     * Function that add number to current count.
     * @param number - number to add.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Function that subtract number from current count.
     * @param number - get number to subtract.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get current count.
     * @return - this count.
     */
    int getValue() {
        return this.counter;
    }
}
