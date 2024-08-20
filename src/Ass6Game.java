/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.GameFlow;
import game.GameLevelDirectHit1;
import game.GameLevelGreen3;
import game.GameLevelWideEasy2;
import game.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class for running the Ass6Game.
 * This class sets up the game environment, initializes the levels to be played, and runs the game flow.
 */
public class Ass6Game {
    private static final int WIDTH_SCREEN = 800, HEIGHT_SCREEN = 600, FRAMES_PER_SECOND = 60;

    /**
     * The main method that starts the game.
     *
     * @param args command-line arguments (optional level numbers to play)
     */
    public static void main(String[] args) {
        //Get the list of levels that we should include
        List<Integer> levelsThatWeShouldInclude = transformToNumbers(args);


        List<LevelInformation> levels = new ArrayList<>();
        //If the list is empty we run all the levels
        if (levelsThatWeShouldInclude.isEmpty()) {
            levels.add(new GameLevelDirectHit1());
            levels.add(new GameLevelWideEasy2());
            levels.add(new GameLevelGreen3());
        } else {
            //Creat the list with the levels that the user asked for
            for (int num : levelsThatWeShouldInclude) {
                if (num == 1) {
                    levels.add(new GameLevelDirectHit1());
                } else if (num == 2) {
                    levels.add(new GameLevelWideEasy2());
                } else if (num == 3) {
                    levels.add(new GameLevelGreen3());
                }
            }
        }

        GUI gui = new GUI("Game", WIDTH_SCREEN, HEIGHT_SCREEN);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui, FRAMES_PER_SECOND);
        GameFlow gf = new GameFlow(animationRunner, ks, gui);
        gf.runLevels(levels);

    }

    /**
     * Transforms the command-line arguments to a list of level numbers.
     *
     * @param args the command-line arguments
     * @return a list of level numbers
     */
    public static List<Integer> transformToNumbers(String[] args) {
        List<Integer> levelsThatWeShouldInclude = new ArrayList<>();
        for (String s : args) {
            switch (s) {
                case "1" -> levelsThatWeShouldInclude.add(1);
                case "2" -> levelsThatWeShouldInclude.add(2);
                case "3" -> levelsThatWeShouldInclude.add(3);
                // Handle unexpected or unmatched cases here
                default ->  {
                }
            }
        }
        return levelsThatWeShouldInclude;
    }
}
