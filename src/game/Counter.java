/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

/**
 * A simple counter that keeps track of a numerical value.
 */
public class Counter {
    private int counter;

    /**
     * Constructs a counter with an initial value.
     *
     * @param counter The initial value of the counter.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number The number to add to the current count.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number The number to subtract from the current count.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Retrieves the current count value.
     *
     * @return The current count value.
     */
    public int getValue() {
        return this.counter;
    }

    /**
     * Checks if the counter is empty, if the current count is zero.
     *
     * @return {@code true} if the counter is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.counter == 0;
    }
}
