/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */

package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class is responsible for running the game levels and managing the game flow.
 */
public class GameFlow {
    private static final int STARTING_SCORE = 0;
    private final AnimationRunner animationrunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;
    private final Counter score;

    /**
     * Constructs a GameFlow object.
     *
     * @param ar  The animation runner to run the game animations
     * @param ks  The keyboard sensor to handle keyboard input
     * @param gui The graphical user interface
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationrunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = new Counter(STARTING_SCORE);
    }

    /**
     * Runs the specified list of game levels.
     *
     * @param levels The list of game levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationrunner, this.gui, this.score);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.noBallsOnBoard()) {
                //Go to losing screen and end the game
                GameOverAnimation gameOverAnimation = new GameOverAnimation(this.score.getValue());
                this.animationrunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", gameOverAnimation));
                gui.close();
                System.exit(0);
                break;
            }

        }
        //If We got here it's a win go to win screen and end the game.
        YouWinAnimation youWinAnimation = new YouWinAnimation(this.score.getValue());
        this.animationrunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", youWinAnimation));
        gui.close();
        System.exit(0);
    }
}
