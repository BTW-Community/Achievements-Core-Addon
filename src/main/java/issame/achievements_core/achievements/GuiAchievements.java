package issame.achievements_core.achievements;

import net.minecraft.src.Block;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Icon;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiAchievements extends GuiScreen {
    private static final int PANE_WIDTH = 252;
    private static final int PANE_HEIGHT = 192;

    private static final int TILE_SIZE = 16;

    private static final int TITLE_COLOR = 4210752;

    private int mapX = 0;
    private int mapY = 0;

    private int prevMouseX;
    private int prevMouseY;

    private int tabIndex = 0;

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        moveAchievementMap(mouseX, mouseY, partialTicks);
        drawAchievementTabs(mouseX, mouseY, partialTicks);
        drawTitle();
    }

    private void moveAchievementMap(int mouseX, int mouseY, float partialTicks) {
        if (Mouse.isButtonDown(0) && isInsideMap(mouseX, mouseY)) {
            mapX -= mouseX - prevMouseX;
            mapY -= mouseY - prevMouseY;
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

    private void drawAchievementTabs(int mouseX, int mouseY, float partialTicks) {
        drawAchievementMap(mouseX, mouseY, partialTicks);
        // Draw connections
        // Draw Achievements

        // Draw unselected tabs
        drawFrame();
        // Draw selected tab

        // Draw page buttons
        // Draw hovered achievement
        // Draw hovered tab
    }

    private void drawAchievementMap(int mouseX, int mouseY, float partialTicks) {
        mc.renderEngine.bindTexture("/terrain.png");

        /*
        int mapLeft = guiX + TILE_SIZE;
        int mapRight = guiX + PANE_WIDTH - TILE_SIZE;
        int mapTop = guiY + TILE_SIZE;
        int mapBottom = guiY + PANE_HEIGHT - TILE_SIZE;
         */

//        AchievementTab tab = AchievementTabList.get(tabIndex);
        int tileWidth = Math.floorDiv(PANE_WIDTH, TILE_SIZE);
        int tileHeight = Math.floorDiv(PANE_HEIGHT, TILE_SIZE);

        int offsetX = getGuiX() - mapX % TILE_SIZE;
        int offsetY = getGuiY() - mapY % TILE_SIZE;

        for (int j = 0; j < tileHeight; j++) {
            int posY = j * TILE_SIZE + offsetY;
            int iconY = j + mapY / TILE_SIZE;

            for (int i = 0; i < tileWidth; i++) {
                int posX = i * TILE_SIZE + offsetX;
                int iconX = i + mapX / TILE_SIZE;

                Icon icon = Block.dirt.getIcon(0, 0);
                if (iconX == 0 && iconY == 0) {
                    icon = Block.stone.getIcon(0, 0);
                } else if ((iconX % 2 == 0 && iconY % 2 == 0) || (iconX % 2 != 0 && iconY % 2 != 0)) {
                    icon = Block.sand.getIcon(0, 0);
                }
                //Icon icon = tab == null ? Block.dirt.getIcon(0, 0) : tab.genAchievementIcon(i, j, i, i);

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
        fontRenderer.drawString("BetterThenAchievements",
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
