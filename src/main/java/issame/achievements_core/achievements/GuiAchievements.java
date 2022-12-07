package issame.achievements_core.achievements;

import net.minecraft.src.Block;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Icon;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiAchievements extends GuiScreen {
    private static final int PANE_WIDTH = 252;
    private static final int PANE_HEIGHT = 193;

    private static final int TILE_SIZE = 16;

    private static final int TITLE_COLOR = 4210752;

    private int mapX = 0;
    private int mapY = 0;

    private int prevMouseX = 0;
    private int prevMouseY = 0;

    private int tabIndex = 0;

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        moveAchievementMap(mouseX, mouseY);
        drawAchievementMap();
        // Draw connections
        // Draw Achievements
        // Draw unselected tabs
        drawFrame();
        // Draw selected tab
        drawTitle();
        // Draw page buttons
        // Draw hovered achievement
        // Draw hovered tab
    }

    private void moveAchievementMap(int mouseX, int mouseY) {
        if (Mouse.isButtonDown(0) && isInsideMap(mouseX, mouseY)) {
            // Move the map in the opposite direction to the mouse movement,
            // i.e. moving the mouse to the left should move the map to the right.
            mapX += prevMouseX - mouseX;
            mapY += prevMouseY - mouseY;
        }
        prevMouseX = mouseX;
        prevMouseY = mouseY;
    }

    private boolean isInsideMap(int xPos, int yPos) {
        int mapLeft = getGuiX() + TILE_SIZE;
        int mapRight = getGuiX() + PANE_WIDTH - TILE_SIZE;
        int mapTop = getGuiY() + TILE_SIZE;
        int mapBottom = getGuiY() + PANE_HEIGHT - TILE_SIZE;

        return (xPos >= mapLeft && xPos <= mapRight)
                && (yPos >= mapTop && yPos <= mapBottom);
    }

    private void drawAchievementMap() {
        mc.renderEngine.bindTexture("/terrain.png");

        AchievementTab tab = AchievementTabList.get(tabIndex);

        int offsetX = TILE_SIZE - Math.floorMod(mapX, TILE_SIZE);
        int offsetY = TILE_SIZE - Math.floorMod(mapY, TILE_SIZE);

        int tileWidth = Math.floorDiv(PANE_WIDTH - offsetX, TILE_SIZE);
        int tileHeight = Math.floorDiv(PANE_HEIGHT - offsetY, TILE_SIZE);

        for (int j = 0; j < tileHeight; j++) {
            int posY = j * TILE_SIZE + getGuiY() + offsetY;
            int iconY = j + Math.floorDiv(mapY, TILE_SIZE);

            for (int i = 0; i < tileWidth; i++) {
                int posX = i * TILE_SIZE + getGuiX() + offsetX;
                int iconX = i + Math.floorDiv(mapX, TILE_SIZE);

                Icon icon = tab == null ? Block.dirt.getIcon(0, 0) : tab.genAchievementIcon(iconX, iconY);
                if (iconX == 0 && iconY == 0) {
                    icon = Block.blockDiamond.getIcon(0, 0);
                } else if (iconX * iconX + iconY * iconY < 5 * 5) {
                    icon = Block.oreDiamond.getIcon(0, 0);
                }

                drawTexturedModelRectFromIcon(posX, posY, icon, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawFrame() {
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture("/achievements_core/achievement/frame.png");
        drawTexturedModalRect(getGuiX(), getGuiY(), 0, 0, PANE_WIDTH, PANE_HEIGHT);
    }

    private void drawTitle() {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        int guiX = (width - PANE_WIDTH) / 2;
        int guiY = (height - PANE_HEIGHT) / 2;
        fontRenderer.drawString("Achievements",
                guiX + TILE_SIZE, guiY + TILE_SIZE / 2, TITLE_COLOR);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    private int getGuiX() {
        return (width - PANE_WIDTH) / 2;
    }

    private int getGuiY() {
        return (height - PANE_HEIGHT) / 2;
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        if (keyCode == mc.gameSettings.keyBindInventory.keyCode) {
            mc.displayGuiScreen(null);
            mc.setIngameFocus();
        } else {
            super.keyTyped(keyChar, keyCode);
        }
    }
}
