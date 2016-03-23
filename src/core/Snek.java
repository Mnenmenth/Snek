package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created by Mnenmenth Alkaborin
 * Please refer to LICENSE file if included
 * for licensing information
 * https://github.com/Mnenmenth
 */

public class Snek extends JPanel {

    public static void main(String[] args){
        Snake snake = new Snake();
    }

    JFrame frame;

    Random rand;

    private class Obj {
        private int x;
        private int y;
        private int height;
        private int width;
        private Color color;
        Obj(int x_, int y_, int height_, int width_, Color color_){
            x = x_;
            y = y_;
            height = height_;
            width = width_;
            color = color_;
        }

        public int getX() { return x; }

        public void setX(int x) { this.x = x; }

        public int getY() { return y; }

        public void setY(int y) { this.y = y; }

        public int getHeight() { return height; }

        public void setHeight(int height) { this.height = height; }

        public int getWidth() { return width; }

        public void setWidth(int width) { this.width = width; }

        public void draw(Graphics g){
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    Obj snake;
    Obj food;

    public Snake() {
        frame = new JFrame("Matt Sucks Cat");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setResizable(false);
        this.setSize(frame.getSize());

        frame.add(this);
        frame.setVisible(true);

        requestFocus();

        rand = new Random();
        this.setBackground(Color.BLACK);

        snake = new Obj(500, 500, 15, 15, Color.WHITE);
        food = new Obj(randX(), randY(), 10, 10, Color.GRAY);



        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "LeftAction");
        this.getActionMap().put("LeftAction", new LeftAction());

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "RightAction");
        this.getActionMap().put("RightAction", new RightAction());

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "UpAction");
        this.getActionMap().put("UpAction", new UpAction());

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "DownAction");
        this.getActionMap().put("DownAction", new DownAction());

    }

    private int randX() { return rand.nextInt(frame.getWidth()-50)+50; }
    private int randY() { return rand.nextInt(frame.getHeight()+50)-50; }

    int speed = 10;

    int xVel = 0;
    int yVel = 0;

    @Override
    public void paint(Graphics g){
        super.paint(g);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        snake.setX(snake.getX() + xVel);
        snake.setY(snake.getY() + yVel);

        food.draw(g);
        snake.draw(g);

        if(snake.getX() >= food.getX() && snake.getX() <= food.getX()+food.getWidth() && snake.getY() >= food.getY() && snake.getY() <= food.getY()+food.getHeight()){
            food.setX(randX());
            food.setY(randY());
        }else if(snake.getX()+snake.getWidth() >= food.getX() && snake.getX()+snake.getWidth() <= food.getX()+food.getWidth()
                && snake.getY()+snake.getHeight() >= food.getY() && snake.getY()+snake.getHeight() <= food.getY()+food.getHeight()){
            food.setX(randX());
            food.setX(randY());
        }

        if(snake.getX() <= 0) snake.setX(getWidth()-snake.getWidth()-10);
        if(snake.getX()+snake.getHeight() >= getWidth()) snake.setX(10);
        if(snake.getY() <= 0) snake.setY(getHeight()-snake.getHeight()-10);
        if(snake.getY()+snake.getHeight() >= getHeight()) snake.setY(10);

        repaint();
    }

    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(snake.getX() >= 0 && snake.getX()+snake.getWidth() <= getWidth()){
                xVel = -speed;
                yVel = 0;
            }else{
                if(snake.getX() <= 0) snake.setX(getWidth()-snake.getWidth()-10);
                if(snake.getX()+snake.getHeight() >= getWidth()) snake.setX(10);
            }
        }
    }

    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(snake.getX() >= 0 && snake.getX()+snake.getWidth() <= getWidth()){
                xVel = speed;
                yVel = 0;
            }else{
                if(snake.getX() <= 0) snake.setX(getWidth()-snake.getWidth()-10);
                if(snake.getX()+snake.getHeight() >= getWidth()) snake.setX(10);
            }
        }
    }

    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(snake.getY() >= 0 && snake.getY()+snake.getHeight() <= getHeight()){
                yVel = -speed;
                xVel = 0;
            }else{
                if(snake.getY() <= 0) snake.setY(getHeight()-snake.getHeight()-10);
                if(snake.getY()+snake.getHeight() >= getHeight()) snake.setY(10);
            }
        }
    }

    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(snake.getY() >= 0 && snake.getY()+snake.getHeight() <= getHeight()){
                yVel = speed;
                xVel = 0;
            }else{
                if(snake.getY() <= 0) snake.setY(getHeight()-snake.getHeight()-10);
                if(snake.getY()+snake.getHeight() >= getHeight()) snake.setY(10);
            }
        }
    }

}
