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
    private static final int FRAME_SIZE = 26;

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
        updateMapPosition(mouseX, mouseY);

        // I have no idea how graphics work.
        // Most of the GL11 stuff here was achieved through trial and error.
        // Apologies in advance.
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glTranslatef(0, 0, -1);

        drawMapBackground();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);

        drawMapConnections();
        // Draw Achievements
        // Draw unselected tabs

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        drawFrame();
        // Draw selected tab
        drawTitle();
        // Draw page buttons
        // Draw hovered achievement
        // Draw hovered tab
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

    private void updateMapPosition(int mouseX, int mouseY) {
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

    private void drawMapBackground() {
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
                drawTexturedModelRectFromIcon(posX, posY, icon, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawMapConnections() {
        AchievementTab tab = AchievementTabList.get(tabIndex);
        if (tab == null) {
            return;
        }

        for (Achievement achievement : tab) {
            // TODO: skip hidden achievements
            drawConnectionsToParent(achievement);
        }
    }

    private void drawConnectionsToParent(Achievement achievement) {
        if (achievement.getParents() == null) {
            return;
        }
        for (Achievement parent : achievement.getParents()) {
            if (parent == null || achievement.getTab() != parent.getTab()) {
                continue;
            }

            int x1 = achievement.getColumn() * FRAME_SIZE + getGuiX() - mapX;
            int y1 = achievement.getRow() * FRAME_SIZE + getGuiY() - mapY;

            int x2 = parent.getColumn() * FRAME_SIZE + getGuiX() - mapX;
            int y2 = parent.getRow() * FRAME_SIZE + getGuiY() - mapY;

            int color = -16777216;
            drawHorizontalLine(x1, x2, y1, color);
            drawVerticalLine(x2, y1, y2, color);
        }
    }

    private void drawFrame() {
        mc.renderEngine.bindTexture("/achievements_core/achievement/frame.png");
        drawTexturedModalRect(getGuiX(), getGuiY(), 0, 0, PANE_WIDTH, PANE_HEIGHT);
    }

    private void drawTitle() {
        int guiX = (width - PANE_WIDTH) / 2;
        int guiY = (height - PANE_HEIGHT) / 2;
        fontRenderer.drawString("Achievements",
                guiX + TILE_SIZE, guiY + TILE_SIZE / 2, TITLE_COLOR);
    }

    private int getGuiX() {
        return (width - PANE_WIDTH) / 2;
    }

    private int getGuiY() {
        return (height - PANE_HEIGHT) / 2;
    }
}
