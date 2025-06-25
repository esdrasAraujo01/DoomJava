
package graphics;

import java.awt.*;

public class RaycastingRenderer {
    private double playerX, playerY;
    private double dirX, dirY;
    private double planeX, planeY;
    private int[][] map;
    private int tileSize;
    private int screenWidth;
    private int screenHeight;

    public RaycastingRenderer(double playerX, double playerY, double dirX, double dirY,
                              double planeX, double planeY, int[][] map, int tileSize,
                              int screenWidth, int screenHeight) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.dirX = dirX;
        this.dirY = dirY;
        this.planeX = planeX;
        this.planeY = planeY;
        this.map = map;
        this.tileSize = tileSize;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void render(Graphics2D g) {
        for (int x = 0; x < screenWidth; x++) {
            double cameraX = 2.0 * x / screenWidth - 1;
            double rayDirX = dirX + planeX * cameraX;
            double rayDirY = dirY + planeY * cameraX;

            int mapX = (int) playerX;
            int mapY = (int) playerY;

            double sideDistX, sideDistY;
            double deltaDistX = Math.abs(1 / rayDirX);
            double deltaDistY = Math.abs(1 / rayDirY);
            double perpWallDist;
            int stepX, stepY;
            boolean hit = false;
            int side = 0;

            if (rayDirX < 0) {
                stepX = -1;
                sideDistX = (playerX - mapX) * deltaDistX;
            } else {
                stepX = 1;
                sideDistX = (mapX + 1.0 - playerX) * deltaDistX;
            }

            if (rayDirY < 0) {
                stepY = -1;
                sideDistY = (playerY - mapY) * deltaDistY;
            } else {
                stepY = 1;
                sideDistY = (mapY + 1.0 - playerY) * deltaDistY;
            }

            while (!hit) {
                if (sideDistX < sideDistY) {
                    sideDistX += deltaDistX;
                    mapX += stepX;
                    side = 0;
                } else {
                    sideDistY += deltaDistY;
                    mapY += stepY;
                    side = 1;
                }

                if (map[mapY][mapX] > 0) hit = true;
            }

            if (side == 0) perpWallDist = (mapX - playerX + (1 - stepX) / 2) / rayDirX;
            else perpWallDist = (mapY - playerY + (1 - stepY) / 2) / rayDirY;

            int lineHeight = (int) (screenHeight / perpWallDist);
            int drawStart = -lineHeight / 2 + screenHeight / 2;
            if (drawStart < 0) drawStart = 0;
            int drawEnd = lineHeight / 2 + screenHeight / 2;
            if (drawEnd >= screenHeight) drawEnd = screenHeight - 1;

            Color wallColor = (side == 0) ? Color.darkGray : Color.GRAY;
            g.setColor(wallColor);
            g.drawLine(x, drawStart, x, drawEnd);
        }
    }

    public void setPlayerX(double playerX) { this.playerX = playerX; }
    public void setPlayerY(double playerY) { this.playerY = playerY; }
    public void setDirX(double dirX) { this.dirX = dirX; }
    public void setDirY(double dirY) { this.dirY = dirY; }
    public void setPlaneX(double planeX) { this.planeX = planeX; }
    public void setPlaneY(double planeY) { this.planeY = planeY; }
}
