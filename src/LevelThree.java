import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * Created by hadar on 02/06/2017.
 */
public class LevelThree implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(40 + i * 30, 5 * 60);
            velocities.add(v);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 8 * 60;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.green);
                d.fillRectangle(30, 45, d.getWidth() - 30, d.getHeight() - 30);
                int buildingWidth = 80;
                int buildingHeight = 200;
                int windowWidth = 10;
                int windowHeight = 10;
                int gap = 5;
                d.setColor(Color.black);
                int buildingUpperLeftX = 70;
                int buildingUpperLeftY = d.getHeight() - buildingHeight;
                d.fillRectangle(buildingUpperLeftX, buildingUpperLeftY, buildingWidth, buildingHeight);
                d.setColor(Color.white);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 13; j++) {
                        d.fillRectangle(
                                (buildingUpperLeftX + gap) + i * (windowWidth + gap),
                                (buildingUpperLeftY + gap) + j * (windowHeight + gap), windowWidth, windowHeight);
                    }
                }
                d.setColor(Color.black);
                d.drawText(650, 15, levelName(), 20);
                d.fillRectangle((buildingUpperLeftX + buildingWidth / 2) - 5,
                        d.getHeight() - buildingHeight - 120, 10, 120);
                d.fillCircle(buildingUpperLeftX + buildingWidth / 2, d.getHeight() - buildingHeight - 120, 15);
                d.setColor(Color.blue);
                d.fillCircle(buildingUpperLeftX + buildingWidth / 2, d.getHeight() - buildingHeight - 120, 10);
                d.setColor(Color.pink);
                d.fillCircle(buildingUpperLeftX + buildingWidth / 2, d.getHeight() - buildingHeight - 120, 5);
            }

            @Override
            public void timePassed(double dt) {
                return;

            }
        };
        return background;

    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        int blockWidth = 50;
        int blockHeight = 25;
        java.util.List<Color> colors = new ArrayList<>();
        colors.add(Color.cyan);
        colors.add(Color.yellow);
        colors.add(Color.orange);
        colors.add(Color.MAGENTA);
        int hits = 2;
        int row = 10;
        for (int j = 1; j <= 4; j++) {
            Color c = colors.get(j - 1);
            for (int i = 1; i < row; i++) {
                if (row != 10) {
                    hits = 1;
                }
                Block block = new Block(
                        new Rectangle(new Point(770 - (i * blockWidth), 100 + (j * blockHeight)),
                                blockWidth, blockHeight), hits, c);
                block.setColor(c);
                blockList.add(block);
            }
            row--;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 30;
    }
}
