package interfaces;

/**
 * Interface for the task we chose in the main.
 * @param <T>
 */
public interface Task<T> {
    T run();
}
