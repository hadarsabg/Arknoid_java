import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadar on 02/06/2017.
 */
public class LevelTwo implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(80 + i * 20, 5 * 60);
            velocities.add(v);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5 * 60;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                Color color = new Color(0xF1EE92);
                d.setColor(color);
                for (int i = 0; i < 100; i++) {
                    d.drawLine(200, 200, i * 6, 300);
                }
                for (int i = 0; i < 3; i++) {
                    d.setColor(color);
                    d.fillCircle(200, 170, 90 - i * 10);
                    color = color.darker();
                }
                d.setColor(Color.black);
                d.drawText(650, 15, levelName(), 20);

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
        java.util.List<Color> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.blue);
        int blockWidth = 740 / numberOfBlocksToRemove();
        int blockHeight = 30;
        Color color = colors.get(0);
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            if (i % 4 == 0) {
                color = colors.get(i / 4);
            }
            Block block = new Block(
                    new Rectangle(new Point(30 + (i * blockWidth), 300), blockWidth, blockHeight),
                    1, color);
            block.setColor(color);
            blockList.add(block);

        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 20;
    }
}
