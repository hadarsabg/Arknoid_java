import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadar on 03/06/2017.
 */
public class LevelFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(i * 5, 5 * 60);
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
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.drawText(650, 15, levelName(), 20);
                Color color = new Color(0x07A9AF);
                d.setColor(color);
                d.fillRectangle(30, 45, d.getWidth() - 30, d.getHeight() - 30);
                d.setColor(Color.WHITE);
                for (int i = 0; i < 12; i++) {
                    d.drawLine(95 + i * 10, 400, 80 + i * 10, 600);
                }
                for (int i = 0; i < 12; i++) {
                    d.drawLine(590 + i * 10, 520, 580 + i * 10, 600);
                }
                //first cloud
                d.setColor(new Color(0xCFD5D4));
                d.fillCircle(150, 440, 35);
                d.fillCircle(100, 420, 25);
                d.fillCircle(640, 485, 27);
                d.setColor(Color.LIGHT_GRAY);
                d.fillCircle(120, 390, 30);
                d.fillCircle(150, 390, 40);
                d.fillCircle(180, 430, 40);
                d.fillCircle(160, 420, 25);
                //second cloud
                d.fillCircle(660, 515, 25);
                d.fillCircle(680, 520, 25);
                d.fillCircle(610, 520, 32);
                //  d.fillCircle(640, 485, 27);
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
        int blockWidth = 37;
        int blockHeight = 25;
        java.util.List<Color> colors = new ArrayList<>();
        colors.add(Color.cyan);
        colors.add(Color.yellow);
        colors.add(Color.orange);
        colors.add(Color.MAGENTA);
        colors.add(Color.green);
        colors.add(Color.pink);
        colors.add(Color.blue);
        int hits = 2;
        int row = 20;
        for (int j = 1; j <= 7; j++) {
            Color c = colors.get(j - 1);
            for (int i = 0; i < 20; i++) {
                if (j != 1) {
                    hits = 1;
                }
                Block block = new Block(
                        new Rectangle(new Point(30 + (i * blockWidth), 100 + (j * blockHeight)),
                                blockWidth, blockHeight), hits, c);
                block.setColor(c);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 140;
    }
}
