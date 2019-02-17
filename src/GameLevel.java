import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;


/**
 * @author Hadar Sabag <hadarsbg@gmail.com>
 * @version 1.8
 * @since 2017-05-09
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running = true;
    private KeyboardSensor keyboardSensor;
    private Paddle paddle;
    private LevelInformation levelInformation;


    /**
     * constructor.
     *
     * @param levelInformation -level information
     * @param animationRunner  - animation runner
     * @param keyboardSensor   -keyboard sensor
     * @param score            -score counter
     * @param lives            -lives counter
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner animationRunner,
                     KeyboardSensor keyboardSensor, Counter score, Counter lives) {
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        //this.keyboardSensor = this.runner.getGui().getKeyboardSensor();
        this.keyboardSensor = keyboardSensor;
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = score;
        this.lives = lives;
        this.sprites = sprites;
        this.paddle = new Paddle(new Rectangle(new Point((800 / 2) - 50, 600 - 60),
                levelInformation.paddleWidth(), 30), this,
                800 - 30, 30, this.runner, Color.ORANGE, levelInformation.paddleSpeed());
    }

    /**
     * return remaining blocks counter.
     *
     * @return this. remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * add a collidable object to the game.
     *
     * @param c - a collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a sprite object to the game.
     *
     * @param s - a sprit object
     */
    public void addSprit(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //set the background
        Sprite levelName = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(0xFCEAFF));
                d.fillRectangle(0, 0, d.getWidth(), 22);
                d.setColor(Color.black);
                d.drawText(590, 15, levelInformation.levelName(), 20);
            }
            @Override
            public void timePassed(double dt) {
                return;
            }
        };

        this.addSprit(this.levelInformation.getBackground());
        this.addSprit(levelName);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        this.addSprit(scoreIndicator);
        this.addSprit(livesIndicator);
        this.paddle.addToGame(this);
        int surfaceWidth = this.runner.getGui().getDrawSurface().getWidth();
        int surfaceHeight = this.runner.getGui().getDrawSurface().getHeight();
        //set the blocks of the borders
        Block up = new Block(new Rectangle(new Point(0, 20), surfaceWidth, 30),
                0, Color.gray);
        Block left = new Block(new Rectangle(new Point(0, 20), 30, surfaceHeight),
                0, Color.gray);
        Block right = new Block(new Rectangle(new Point(surfaceWidth - 30, 20),
                30, surfaceHeight), 0, Color.gray);
        up.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        //set death region in the bottom
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        Block deathRegion = new Block(new Rectangle(new Point(0, surfaceHeight), surfaceWidth, 30),
                0, Color.gray);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);
        //set the small blocks in the middle
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
            this.remainingBlocks.increase(1);
        }
    }

    /**
     * return the game sprite collection.
     *
     * @return this.sprites
     */
    public SpriteCollection getSprite() {
        return this.sprites;
    }

    /**
     * move the paddle to the center and create the balls.
     */
    public void createBallsOnTopOfPaddle() {
        this.paddle.moveToCenter(new Point((int) (400 - (this.levelInformation.paddleWidth() / 2)), 540));
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 535, 6, Color.WHITE, this.environment);
            ball.addToGame(this);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            this.remainingBalls.increase(1);
        }
    }

    /**
     * Run one turn of the game.
     * start the animation loop using the runner.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * the animation logic.
     * draw the sprites and notify time passed,
     * check the counters, and change running(stop condition)
     * according to them.
     * also check if game needs to be paused.
     *
     * @param d  draw surface
     * @param dt -double dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
        }

        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new PauseScreen()));
        }
    }


    /**
     * remove a given collidable from the game environment.
     *
     * @param c - a collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a given sprite from the game's sprite collection.
     *
     * @param s -a sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);


    }


    @Override
    public boolean shouldStop() {
        return !this.running;

    }

    /**
     * return the life counter.
     *
     * @return this.lives
     */
    public Counter getLives() {
        return this.lives;
    }
}
