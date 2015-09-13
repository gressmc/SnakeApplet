package grs.testepam.snake.view;

import grs.testepam.snake.model.Fog;
import grs.testepam.snake.model.GameObject;
import grs.testepam.snake.model.Snake;

import static grs.testepam.snake.Constants.*;

import java.awt.*;

/**
 * Created by gressmc on 17/07/15.
 */
public class SceneRenderer {

    private Snake snake;
    private Fog fog;

    public SceneRenderer(Snake snake){
        this.snake = snake;
    }

    public void setFog(Fog fog) {
        this.fog = fog;
    }

    public void render(Graphics g) {
        drawField(g);
        drawSnake(g);
        drawFog(g);

        g.setColor(Color.black);
        g.setFont(new Font("Default", Font.BOLD, 30));
        String count = String.valueOf(snake.count);
        g.drawString(count, 30,  WORLD_HEIGHT*CELL_SIZE + 35);
    }

    private void drawField(Graphics g){
        g.setColor(new Color(55, 105, 180));
        g.fillRect(0, 0, WORLD_WIDTH*CELL_SIZE, WORLD_HEIGHT*CELL_SIZE);

        g.setColor(new Color(255, 195, 0));

        for (int column = CELL_SIZE; column < WORLD_WIDTH*CELL_SIZE; column += CELL_SIZE){
            g.drawLine(column, 0, column, WORLD_HEIGHT*CELL_SIZE);
        }

        for (int row = CELL_SIZE; row < WORLD_HEIGHT*CELL_SIZE; row += CELL_SIZE){
            g.drawLine(0, row, WORLD_WIDTH*CELL_SIZE, row);
        }
    }

    private void drawSnake(Graphics g) {

        for (GameObject.BodyPoint bodyPart: snake.getBody()){
            g.setColor(Color.YELLOW);

            if (bodyPart == snake.head()) {
                g.fillOval(bodyPart.getX() * CELL_SIZE + 3, bodyPart.getY() * CELL_SIZE + 3, CELL_SIZE - 6, CELL_SIZE - 6);
            } else if (bodyPart == snake.tail()){
                g.fillOval(bodyPart.getX() * CELL_SIZE + CELL_SIZE / 3, bodyPart.getY() * CELL_SIZE + CELL_SIZE / 3, CELL_SIZE - CELL_SIZE * 2 / 3, CELL_SIZE - CELL_SIZE * 2 / 3);
            } else {
                g.fillOval(bodyPart.getX() * CELL_SIZE + CELL_SIZE / 4, bodyPart.getY() * CELL_SIZE + CELL_SIZE / 4, CELL_SIZE - CELL_SIZE / 2, CELL_SIZE - CELL_SIZE / 2);
            }
        }
    }

    private void drawFog(Graphics g) {
        g.setColor(fog.getColorFog().getColor());

        g.fillOval(fog.getBodyFog().getX() * CELL_SIZE + 3, fog.getBodyFog().getY() * CELL_SIZE + 3, CELL_SIZE - 6, CELL_SIZE - 6);
    }
}
