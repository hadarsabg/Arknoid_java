import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.DialogManager;

/**
 * Created by hadar on 03/06/2017.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private HighScoresTable highScoresTable;


    /**
     * constructor.
     *
     * @param ar              animation runner
     * @param ks              KeyboardSensor
     * @param highScoresTable highscore table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable highScoresTable) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.lives = new Counter();
        this.highScoresTable = highScoresTable;
    }

    /**
     * run the wanted levels of the game.
     *
     * @param levels a list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        /*
        HighScoresTable highScoresTable = new HighScoresTable(4);
        File file = new File("highscores");
        try {
            highScoresTable.load(file);
        } catch (IOException e) {
        }*/

        EndScreen endScreen = new EndScreen(this.lives, this.score);
        KeyPressStoppableAnimation keyPressStoppableEndScreen =
                new KeyPressStoppableAnimation(this.keyboardSensor, "space", endScreen);
        int y = this.lives.getValue();
        this.lives.decrease(y);
        this.lives.increase(7);
        int x = this.score.getValue();
        this.score.decrease(x);
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(
                    levelInfo, this.animationRunner, this.keyboardSensor, this.score, this.lives);
            level.initialize();

            //while level has more blocks and player has more lives
            while (level.getRemainingBlocks().getValue() != 0 && this.lives.getValue() != 0) {
                level.playOneTurn();
            }

            if (this.lives.getValue() == 0) {
                break;
            }
        }
        if (highScoresTable.getRank(this.score.getValue()) < highScoresTable.size()) {
            DialogManager dialog = animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(this.score.getValue(), name);
            highScoresTable.add(scoreInfo);
        }
        animationRunner.run(keyPressStoppableEndScreen);
        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highScoresTable);
        KeyPressStoppableAnimation keyPressStoppableHighScore
                = new KeyPressStoppableAnimation(this.keyboardSensor, "space", highScoresAnimation);
        try {
            highScoresTable.save(new File("highscores"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        animationRunner.run(keyPressStoppableHighScore);
    }
}

