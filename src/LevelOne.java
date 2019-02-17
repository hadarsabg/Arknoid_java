import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * Created by hadar on 01/06/2017.
 */
public class LevelOne implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v = Velocity.fromAngleAndSpeed(0, 4 * 60);
        velocities.add(v);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5 * 60;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite sprite = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(30, 45, d.getWidth() - 30, d.getHeight() - 30);
                d.setColor(Color.blue);
                for (int i = 0; i < 3; i++) {
                    d.drawCircle(d.getWidth() / 2, 200, 60 + i * 30);
                }
                d.setColor(Color.blue);
                int lineLength = 300;
                int centerx = 400;
                int centery = 200;
                d.drawLine(centerx, centery - lineLength / 2, centerx, centery + lineLength / 2);
                d.drawLine(centerx - lineLength / 2, centery, centerx + lineLength / 2, centery);
                d.setColor(Color.black);
                d.drawText(650, 15, levelName(), 20);

            }

            @Override
            public void timePassed(double dt) {
                return;
            }
        };
        return sprite;
    }

    @Override
    public List<Block> blocks() {

        List<Block> blockList = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(375, 175), 50, 50),
                1, Color.red);
        block.setColor(Color.red);
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
