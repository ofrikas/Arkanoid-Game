/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;
import geometry.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of sprites.
 */
public class SpriteCollection {
    private final List<Sprite> lOS;

    /**
     * Constructor for a new game.SpriteCollection.
     */
    public SpriteCollection() {
        this.lOS = new ArrayList<>();
    }

    /**
     * Get the list of sprites.
     *
     * @return the list of sprites
     */
    public List<Sprite> getLOS() {
        return lOS;
    }

    /**
     * Add a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.lOS.add(s);
    }

    /**
     * Removes the specified sprite from the collection.
     *
     * @param s The sprite to remove.
     * @return {@code true} if the sprite was successfully removed, {@code false} otherwise.
     */
    public boolean removeSprite(Sprite s) {
        if (this.lOS.contains(s)) {
            this.lOS.remove(s);
            return true;
        }
        return false;
    }

    /**
     * Notify all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.lOS.size(); i++) {
            this.lOS.get(i).timePassed();
        }
    }

    /**
     * Draw all sprites on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.lOS) {
            s.drawOn(d);
        }
    }
}