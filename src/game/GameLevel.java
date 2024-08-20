/**
 * ID: 208708768.
 * Name: Ofri Kastenbaum.
 */
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Ball;
import geometry.Block;
import geometry.Collidable;
import geometry.HitListener;
import geometry.Paddle;
import geometry.Point;
import geometry.Rectangle;
import geometry.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The GameLevel class represents a specific level in the game.
 */
public class GameLevel implements Animation {
    private static final int WIDTH_SCREEN = 800, HEIGHT_SCREEN = 600,

    SIZE_OF_BORDER = 40, BEGINNING_OF_LABOR = 0, LEVEL_BONUS_SCORE = 100,
            X_LOCATION_FOR_SCORE = 150, Y_LOCATION_FOR_SCORE = 30, UPPER_TEXT_SIZE = 30,
            X_LOCATION_FOR_NAME = 400, Y_LOCATION_FOR_NAME = 30,

    COUNT_DOWN_NUM_OF_SECONDS = 2, COUNTER_NUMBER_FROM = 3;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter numOfBlocks;
    private final Counter numOfBalls;
    private final Counter score;
    private final LevelInformation levelInfo;
    private boolean running, outOfBalls;
    private final GUI gui;
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboard;


    /**
     * Creates a new GameLevel object.
     *
     * @param levelInfo The level information for the current level
     * @param ks        The keyboard sensor for handling keyboard input
     * @param ar        The animation runner for running the animations
     * @param gui       The graphical user interface
     * @param score     The counter for tracking the player's score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, GUI gui, Counter score) {
        //Get from GameFlow
        this.levelInfo = levelInfo;
        this.keyboard = ks;
        this.animationRunner = ar;
        this.gui = gui;

        // Lists initialization
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        // Counters initialization
        this.numOfBlocks = new Counter(this.levelInfo.numberOfBlocksToRemove());
        this.numOfBalls = new Counter(this.levelInfo.numberOfBalls());
        this.score = score;
        this.running = false;
        this.outOfBalls = false;

    }

    /**
     * @return the sprite collection of the game
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * @return the game environment of the game
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added to the game environment
     */
    public void addCollidable(Collidable c) {
        this.environment.getListOfCollidable().add(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to be added to the sprite collection
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes the specified collidable from the environment's list of collidables.
     *
     * @param c The collidable to remove.
     * @return {@code true} if the collidable was successfully removed, {@code false} otherwise.
     */
    public boolean removeCollidable(Collidable c) {
        if (this.environment.getListOfCollidable().contains(c)) {
            this.environment.getListOfCollidable().remove(c);
            return true;
        }
        return false;
    }

    /**
     * Removes the specified sprite from the collection.
     *
     * @param s The sprite to remove.
     * @return {@code true} if the sprite was successfully removed, {@code false} otherwise.
     */
    public boolean removeSprite(Sprite s) {
        return (this.sprites.removeSprite(s));
    }


    /**
     * Initializes the game, creating the blocks, ball, paddle, and any other game objects, and adding them to the game.
     */
    public void initialize() {
        //Creat HitListener and add listeners
        List<HitListener> hl = new ArrayList<>();
        hl.add(new PrintingHitListener());
        hl.add(new BlockRemover(this, this.numOfBlocks));
        hl.add(new ScoreTrackingListener(this.score));

        //Creat blocks as borders for the screen
        Block top = new Block(new Rectangle(
                new Point(BEGINNING_OF_LABOR, BEGINNING_OF_LABOR), WIDTH_SCREEN, SIZE_OF_BORDER));
        Block right = new Block(new Rectangle(
                new Point(WIDTH_SCREEN - SIZE_OF_BORDER, SIZE_OF_BORDER), SIZE_OF_BORDER, HEIGHT_SCREEN));
        Block bottom = new Block(new Rectangle(new Point(SIZE_OF_BORDER, HEIGHT_SCREEN - SIZE_OF_BORDER),
                WIDTH_SCREEN - SIZE_OF_BORDER * 2, SIZE_OF_BORDER));
        Block left = new Block(new Rectangle(
                new Point(BEGINNING_OF_LABOR, SIZE_OF_BORDER), SIZE_OF_BORDER, HEIGHT_SCREEN));
        top.setColor(Color.gray);
        right.setColor(Color.gray);
        bottom.setColor(Color.gray);
        left.setColor(Color.gray);
        top.addToGame(this);
        right.addToGame(this);
        bottom.addToGame(this);
        left.addToGame(this);

        //Add the notifier for removing the balls
        bottom.addHitListener(new BallRemover(this, numOfBalls));

        //Creat score sprite
        ScoreIndicator scoreI = new ScoreIndicator(this.score, X_LOCATION_FOR_SCORE,
                Y_LOCATION_FOR_SCORE, UPPER_TEXT_SIZE);
        scoreI.addToGame(this);

        //Creat level name sprite
        LevelNameIndicator levelName = new LevelNameIndicator(
                this.levelInfo.levelName(), X_LOCATION_FOR_NAME, Y_LOCATION_FOR_NAME, UPPER_TEXT_SIZE);
        levelName.addToGame(this);

        //Creat paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), new Rectangle(this.levelInfo.paddleUpperLeftPoint(),
                this.levelInfo.paddleWidth(), this.levelInfo.paddleLength()), this.levelInfo.paddleColor(),
                this.levelInfo.paddleSpeed());
        paddle.addToGame(this);

        //Creat the balls
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(this.levelInfo.ballsStartingPoint(), this.levelInfo.ballRadius(),
                    this.levelInfo.ballColor(), this.environment, paddle);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
        }


        //Creat blocks for the game
        List<Block> blocks = this.levelInfo.blocks();
        for (Block block : blocks) {
            // Add the listeners to ech block
            for (HitListener listener : hl) {
                block.addHitListener(listener);
            }
            block.addToGame(this);
        }

        //Add background elements to the sprites
        if (this.levelInfo.getBackground() != null) {
            for (Sprite s : this.levelInfo.getBackground()) {
                this.sprites.addSprite(s);
            }
        }

    }

    /**
     * Runs the game animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.animationRunner.run(new CountdownAnimation(
                COUNT_DOWN_NUM_OF_SECONDS, COUNTER_NUMBER_FROM, this.sprites, this.levelInfo.backgroundColor()));
        this.animationRunner.run(this);
    }


    /**
     * Performs one frame of the animation on the given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the animation frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //Set the background
        d.setColor(this.levelInfo.backgroundColor());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Add the sprites
        this.sprites.drawAllOn(d);

        this.sprites.notifyAllTimePassed();
        // win case
        if (this.numOfBlocks.isEmpty()) {
            this.score.increase(LEVEL_BONUS_SCORE);
            this.running = true;
        }
        // Lose case
        if (this.numOfBalls.isEmpty()) {
            this.running = true;
            this.outOfBalls = true;
        }
        if (this.keyboard.isPressed("p")) {
            // Create and run the PauseScreen
            PauseScreen pauseScreen = new PauseScreen();
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, "space", pauseScreen));


        }
    }

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.running;
    }

    /**
     * Checks if there are still balls on the game board.
     *
     * @return {@code true} if there are no more balls on the board, {@code false} otherwise
     */
    public boolean noBallsOnBoard() {
        return this.outOfBalls;
    }

}