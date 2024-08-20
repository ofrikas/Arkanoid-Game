/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package geometry;

/**
 * The HitNotifier interface represents an object that can notify hit events to registered listeners.
 * Objects implementing this interface can allow other objects to subscribe as listeners and receive notifications
 * when a hit event occurs.
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to the list of listeners.
     *
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a hit listener from the list of listeners.
     *
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}
