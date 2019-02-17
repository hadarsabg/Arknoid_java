import biuoop.KeyboardSensor;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by hadar on 21/06/2017.
 */
public class Ass6Game {

    /**
     * end the program.
     */
    public void exit() {
        System.exit(-1);
    }

    /**
     * main method-starts a new game.
     * run the wanted levels of the game,
     * if no levels are entered- run all four levels.
     *
     * @param args - list of numbers of levels to play
     */
    public static void main(String[] args) {
        Ass6Game ass6Game = new Ass6Game();
        //create highScore table
        File file = new File("highscores");
        HighScoresTable highScoresTable = new HighScoresTable(4);

        try {
            highScoresTable.load(file);
        } catch (IOException e) {
            ;
        }
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, highScoresTable);
        InputStream inputStream1;
        if (args.length != 0) {
            inputStream1 = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        } else {
            inputStream1 = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        }
        Reader reader = new InputStreamReader(inputStream1);
        LevelsSetsReader levelsSetsReader = new LevelsSetsReader();
        Map<String, String> levelsSet = levelsSetsReader.levelSetsFromReader(reader);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("choose level", keyboardSensor);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("arkanoid", keyboardSensor);
        for (String key : levelsSet.keySet()) {
            String[] nameSeparate = key.split(":");
            String symbol = nameSeparate[0];
            String setsName = nameSeparate[1];
            String filePath = levelsSet.get(key);
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            Reader r = new InputStreamReader(inputStream);

            LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
            List<LevelInformation> levelsListOfSet = new ArrayList<>();
            levelsListOfSet = levelSpecificationReader.fromReader(r);
            List<LevelInformation> finalLevelsListOfSet = levelsListOfSet;

            Task<Void> runLevelsTask = new Task<Void>() {
                @Override
                public Void run() {

                    gameFlow.runLevels(finalLevelsListOfSet);
                    return null;
                }
            };
            subMenu.addSelection(symbol,
                    "press \"" + symbol + "\" for " + setsName + " level", runLevelsTask);
        }

        Task<Void> subMenuTask = new Task<Void>() {
            @Override
            public Void run() {
                animationRunner.run(subMenu);
                Task<Void> task = subMenu.getStatus();
                task.run();
                return null;
            }
        };
        menu.addSelection("s", "Press \"s\" to start a new game", subMenuTask);

        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highScoresTable);
        KeyPressStoppableAnimation keyPressStoppableHighScore
                = new KeyPressStoppableAnimation(keyboardSensor, "space", highScoresAnimation);

        Task<Void> highScoresTask = new Task<Void>() {
            @Override
            public Void run() {
                animationRunner.run(keyPressStoppableHighScore);
                return null;
            }
        };

        Task<Void> quitTask = new Task<Void>() {
            @Override
            public Void run() {
                ass6Game.exit();
                return null;
            }
        };

        menu.addSelection("h", "Press \"h\" to see the high scores", highScoresTask);
        menu.addSelection("q", "Press \"q\" to quit", quitTask);

        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

}



