
package game;

import graphics.RaycastingRenderer;
import maps.MapLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameLoop extends JPanel implements KeyListener, Runnable {
    private boolean running = true;
    private Thread gameThread;

    private final int screenWidth = 1600;
    private final int screenHeight = 900;

    private int[][] map;

    private double playerX = 3.5, playerY = 3.5;
    private double dirX = 1, dirY = 0;
    private double planeX = 0, planeY = 0.66;

    private final double moveSpeed = 0.1;
    private final double rotSpeed = 0.05;

    private RaycastingRenderer renderer;

    public GameLoop() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setFocusable(true);
        addKeyListener(this);

        try {
            map = MapLoader.loadMap("src/maps/Map1.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        renderer = new RaycastingRenderer(playerX, playerY, dirX, dirY, planeX, planeY, map, 64, screenWidth, screenHeight);

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        renderer.setPlayerX(playerX);
        renderer.setPlayerY(playerY);
        renderer.setDirX(dirX);
        renderer.setDirY(dirY);
        renderer.setPlaneX(planeX);
        renderer.setPlaneY(planeY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render((Graphics2D) g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            if (map[(int) (playerY)][(int) (playerX + dirX * moveSpeed)] == 0) playerX += dirX * moveSpeed;
            if (map[(int) (playerY + dirY * moveSpeed)][(int) (playerX)] == 0) playerY += dirY * moveSpeed;
        }
        if (key == KeyEvent.VK_S) {
            if (map[(int) (playerY)][(int) (playerX - dirX * moveSpeed)] == 0) playerX -= dirX * moveSpeed;
            if (map[(int) (playerY - dirY * moveSpeed)][(int) (playerX)] == 0) playerY -= dirY * moveSpeed;
        }
        if (key == KeyEvent.VK_A) {
            double oldDirX = dirX;
            dirX = dirX * Math.cos(-rotSpeed) - dirY * Math.sin(-rotSpeed);
            dirY = oldDirX * Math.sin(-rotSpeed) + dirY * Math.cos(-rotSpeed);

            double oldPlaneX = planeX;
            planeX = planeX * Math.cos(-rotSpeed) - planeY * Math.sin(-rotSpeed);
            planeY = oldPlaneX * Math.sin(-rotSpeed) + planeY * Math.cos(-rotSpeed);
        }
        if (key == KeyEvent.VK_D) {
            double oldDirX = dirX;
            dirX = dirX * Math.cos(rotSpeed) - dirY * Math.sin(rotSpeed);
            dirY = oldDirX * Math.sin(rotSpeed) + dirY * Math.cos(rotSpeed);

            double oldPlaneX = planeX;
            planeX = planeX * Math.cos(rotSpeed) - planeY * Math.sin(rotSpeed);
            planeY = oldPlaneX * Math.sin(rotSpeed) + planeY * Math.cos(rotSpeed);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Doom Clone");
        GameLoop game = new GameLoop();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
