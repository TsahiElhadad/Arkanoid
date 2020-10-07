package interfaces;

import animation.Animation;

public interface Menu<T> extends Animation {
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the status.
     */
    T getStatus();
}
