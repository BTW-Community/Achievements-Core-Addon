package issame.achievements_core.achievements;

import net.minecraft.src.*;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;

public class GuiAchievements extends GuiScreen {
    private static final int PANE_WIDTH = 252;
    private static final int PANE_HEIGHT = 193;

    private static final int TAB_WIDTH = 28;
    private static final int TAB_HEIGHT = 32;

    private static final int TILE_SIZE = 16;
    private static final int TITLE_COLOR = 4210752;
    private static final int TABS_P_PAGE = 9;

    private int mapX = 0;
    private int mapY = 0;

    private int prevMouseX = 0;
    private int prevMouseY = 0;

    private int selectedTabIndex = 0;
    private int page = 1;

    private final RenderItem renderItem = new RenderItem();

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
        GL11.glPushMatrix();
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glTranslatef(0, 0, -1);

        drawMapBackground();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);

        drawAchievementConnections();
        drawAchievements();

        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1, 1, 1, 1);

        drawUnselectedTabs();
        drawFrame();
        drawTab(selectedTabIndex);
        drawTitle();
        // Draw page buttons
        // Draw hovered achievement
        // Draw hovered tab

        GL11.glDisable(GL11.GL_DEPTH_TEST);
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

        AchievementTab tab = AchievementTabList.get(selectedTabIndex);

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

    private void drawAchievementConnections() {
        AchievementTab tab = AchievementTabList.get(selectedTabIndex);
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

            int x1 = achievement.getColumn() * Achievement.SIZE + getGuiX() - mapX;
            int y1 = achievement.getRow() * Achievement.SIZE + getGuiY() - mapY;

            int x2 = parent.getColumn() * Achievement.SIZE + getGuiX() - mapX;
            int y2 = parent.getRow() * Achievement.SIZE + getGuiY() - mapY;

            // TODO: Change color based on unlock status
            int color = -16777216;
            drawHorizontalLine(x1, x2, y1, color);
            drawVerticalLine(x2, y1, y2, color);
        }
    }

    private void drawAchievements() {
        AchievementTab tab = AchievementTabList.get(selectedTabIndex);
        if (tab == null) {
            return;
        }

        for (Achievement achievement : tab) {
            // TODO: skip hidden achievements
            // TODO: Change color based on unlock status
            float brightness = 1;
            GL11.glColor4f(brightness, brightness, brightness, 1);

            int offset = Achievement.SIZE / 2;
            int x = achievement.getColumn() * Achievement.SIZE + getGuiX() - mapX - offset;
            int y = achievement.getRow() * Achievement.SIZE + getGuiY() - mapY - offset;

            mc.renderEngine.bindTexture(String.format("/achievements_core/shape/%s.png", achievement.getFrameSet()));
            drawTexturedModalRect(x, y, achievement.getFrameU(), achievement.getFrameV(), Achievement.SIZE, Achievement.SIZE);

            offset = (Achievement.SIZE - TILE_SIZE) / 2;
            renderItem.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, achievement.getIcon(), x + offset, y + offset);
        }
    }

    private void drawUnselectedTabs() {
        for (int tabIndex = 0; tabIndex < AchievementTabList.size(); tabIndex++) {
            if (tabIndex == selectedTabIndex) {
                continue;
            }
            drawTab(tabIndex);
        }
    }

    private void drawTab(int tabIndex) {
        AchievementTab tab = AchievementTabList.get(tabIndex);
        if (tab == null || !isTabOnPage(tabIndex)) {
            return;
        }

        int x = getGuiX() + TAB_WIDTH * (tabIndex % TABS_P_PAGE);
        int y = getGuiY() - TAB_HEIGHT;

        int u = 0;
        int v = tabIndex == selectedTabIndex ? TAB_HEIGHT : 0;

        mc.renderEngine.bindTexture("/gui/allitems.png");
        drawTexturedModalRect(x, y, u, v, TAB_WIDTH, TAB_HEIGHT);

        ItemStack icon = new ItemStack(Item.itemsList[tab.getIconID()]);
        renderItem.renderItemAndEffectIntoGUI(fontRenderer, mc.renderEngine, icon, x, y);
    }

    private boolean isTabOnPage(int tabIndex) {
        return (tabIndex + 1 <= TABS_P_PAGE * page) && (tabIndex + 1 > TABS_P_PAGE * (page - 1));
    }

    private void drawFrame() {
        mc.renderEngine.bindTexture("/achievements_core/frame.png");
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
