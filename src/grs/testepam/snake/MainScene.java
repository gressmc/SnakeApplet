package grs.testepam.snake;

import grs.testepam.snake.controller.GameEngine;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static grs.testepam.snake.Constants.*;

/**
 * Created by gressmc on 17/07/15.
 */
public class MainScene extends Applet implements Runnable, MouseListener, ActionListener {

    private GameEngine engine = new GameEngine();
    Button startBut, pauseBut, stopBut;

    public void init(){

        setLayout(null);

        startBut = new Button("Start");
        pauseBut = new Button("Pause");
        stopBut = new Button("Stop");


        startBut.setBounds(150, WORLD_HEIGHT * CELL_SIZE, 90, 50);
        pauseBut.setBounds(240, WORLD_HEIGHT*CELL_SIZE, 90, 50);
        stopBut.setBounds(330, WORLD_HEIGHT*CELL_SIZE, 90, 50);

        add(startBut);
        add(pauseBut);
        add(stopBut);

        startBut.addActionListener(this);
        pauseBut.addActionListener(this);
        stopBut.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand() ;
        switch (str){
            case "Start" :
                System.out.println("Start");
                engine.start();
                break;
            case "Pause" :
                System.out.println("Pause");
                engine.pause();
                break;
            case "Stop" :
                System.out.println("Stop");
                engine.stop();
                break;
        }
        repaint() ;
    }

    public void start() {
        addMouseListener( this );
        new Thread(this).start();

    }

    public void run() {

        setSize(WORLD_WIDTH*CELL_SIZE, WORLD_HEIGHT*CELL_SIZE + 50);

        BufferedImage screen = new BufferedImage(WORLD_WIDTH*CELL_SIZE, WORLD_HEIGHT*CELL_SIZE + 50, BufferedImage.TYPE_INT_RGB);
        Graphics g = screen.getGraphics();
        Graphics appletGraphics = getGraphics();

        while (true) {


            g.setColor(new Color(232, 232, 232));
            g.fillRect(0, 0, WORLD_WIDTH*CELL_SIZE, WORLD_HEIGHT*CELL_SIZE + 50);

            engine.update();
            engine.render(g);

            appletGraphics.drawImage(screen, 0, 0, null);

            if (!isActive()) {
                return;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        engine.processEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
