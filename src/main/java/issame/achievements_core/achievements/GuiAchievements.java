package issame.achievements_core.achievements;

import issame.achievements_core.Colors;
import net.minecraft.src.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiAchievements extends GuiScreen {
    private static final int PANE_WIDTH = 252;
    private static final int PANE_HEIGHT = 193;

    private static final int TAB_WIDTH = 28;
    private static final int TAB_HEIGHT = 32;

    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_HEIGHT = 15;

    private static final int ARROW_OFFSET_X = 1;
    private static final int ARROW_OFFSET_Y = 6;

    private static final int TILE_SIZE = 16;

    private static final int TABS_P_PAGE = 9;
    private static final int TAB_OFFSET = 4;

    private static final int HOVER_OFFSET_X = 12;
    private static final int HOVER_OFFSET_Y = -4;
    private static final int HOVER_PADDING = 3;

    private static final int DESC_PADDING = 3;

    private int mapX = 0;
    private int mapY = 0;

    private int prevMouseX = 0;
    private int prevMouseY = 0;

    private int selectedTabIndex = 0;
    private int page = 0;

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
        GL11.glTranslatef(0, 0, -200);

        drawMapBackground();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.enableGUIStandardItemLighting();

        drawAchievementConnections();
        Achievement hovered = drawAchievements(mouseX, mouseY);

        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);

        drawUnselectedTabs();

        // This stuff makes enchantment glints work properly
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1, 1, 1, 1);

        drawBorder();
        drawTab(selectedTabIndex);
        drawTitle();

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        drawPageButtons(mouseX, mouseY);
        drawAchievementHoverText(mouseX, mouseY, hovered);
        drawTabHoverText(mouseX, mouseY);

        RenderHelper.disableStandardItemLighting();
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

    @Override
    protected void mouseMovedOrUp(int mouseX, int mouseY, int which) {
        AchievementTab tab = getHoveredTab(mouseX, mouseY);
        if (which < 0 || tab == null) {
            super.mouseMovedOrUp(mouseX, mouseY, which);
            return;
        }
        selectedTabIndex = tab.getIndex();
    }

    @Override
    public void handleMouseInput() {
        // Scroll to change pages.
        int direction = -Mouse.getEventDWheel();
        if (direction < 0 && hasPrevPage()) {
            page--;
        } else if (direction > 0 && hasNextPage()) {
            page++;
        }
        super.handleMouseInput();
    }

    private boolean hasNextPage() {
        return AchievementTabList.size() > TABS_P_PAGE * (page + 1);
    }

    private boolean hasPrevPage() {
        return page > 0;
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
            if (!achievement.isHidden) {
                drawConnectionsToParent(achievement);
            }
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

            int x1 = getAchievementPosX(achievement);
            int y1 = getAchievementPosY(achievement);

            int x2 = getAchievementPosX(parent);
            int y2 = getAchievementPosY(parent);

            int color = Colors.getConnectionColor(achievement);
            drawHorizontalLine(x1, x2, y1, color);
            drawVerticalLine(x2, y1, y2, color);
        }
    }

    private Achievement drawAchievements(int mouseX, int mouseY) {
        AchievementTab tab = AchievementTabList.get(selectedTabIndex);
        if (tab == null) {
            return null;
        }

        Achievement hovered = null;
        for (Achievement achievement : tab) {
            if (achievement.isHidden) {
                continue;
            }

            float brightness = 1;
            if (achievement.getStatus() == AchievementStatus.LOCKED) {
                brightness = .3f;
            }
            GL11.glColor4f(brightness, brightness, brightness, 1);

            int offset = Achievement.SIZE / 2;
            int x = getAchievementPosX(achievement) - offset;
            int y = getAchievementPosY(achievement) - offset;

            if (isPosInRect(mouseX, mouseY, x, y, Achievement.SIZE, Achievement.SIZE)) {
                hovered = achievement;
            }

            mc.renderEngine.bindTexture(String.format("/achievements_core/frame_set/%s.png", achievement.getFrameSet()));
            drawTexturedModalRect(x, y, achievement.getFrameU(), achievement.getFrameV(), Achievement.SIZE, Achievement.SIZE);

            if (achievement.getStatus() == AchievementStatus.LOCKED) {
                brightness = .1f;
                GL11.glColor4f(brightness, brightness, brightness, 1);
                renderItem.renderWithColor = false;
            }

            offset = (Achievement.SIZE - TILE_SIZE) / 2;
            renderItem.zLevel = 100;
            renderItem.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, achievement.getIcon(),
                    x + offset, y + offset);
            renderItem.zLevel = 0;
            GL11.glDisable(GL11.GL_LIGHTING);
            renderItem.renderWithColor = true;

            GL11.glColor4f(1, 1, 1, 1);
        }
        return hovered;
    }

    private int getAchievementPosX(Achievement achievement) {
        return (int) (achievement.getColumn() / 2.0 * Achievement.SIZE + getGuiX() - mapX);
    }

    private int getAchievementPosY(Achievement achievement) {
        return (int) (achievement.getRow() / 2.0 * Achievement.SIZE + getGuiY() - mapY);
    }

    private void drawUnselectedTabs() {
        for (int tabIndex = 0; tabIndex < AchievementTabList.size(); tabIndex++) {
            if (tabIndex != selectedTabIndex) {
                drawTab(tabIndex);
            }
        }
    }

    private void drawTab(int tabIndex) {
        AchievementTab tab = AchievementTabList.get(tabIndex);
        if (tab == null || isTabOffPage(tabIndex)) {
            return;
        }

        int x = getGuiX() + TAB_WIDTH * (tabIndex % TABS_P_PAGE);
        int y = getGuiY() - TAB_HEIGHT + TAB_OFFSET;

        int u = 0;
        int v = tabIndex == selectedTabIndex ? TAB_HEIGHT : 0;

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        mc.renderEngine.bindTexture("/gui/allitems.png");
        drawTexturedModalRect(x, y, u, v, TAB_WIDTH, TAB_HEIGHT);

        ItemStack icon = new ItemStack(Item.itemsList[tab.getIconID()]);
        x += (TAB_WIDTH - TILE_SIZE) / 2;
        y += (TAB_HEIGHT - TILE_SIZE) / 4;
        renderItem.zLevel = 100;
        renderItem.renderItemAndEffectIntoGUI(fontRenderer, mc.renderEngine, icon, x, y + TAB_OFFSET);
        renderItem.zLevel = 0;
    }

    private boolean isTabOffPage(int tabIndex) {
        return (tabIndex + 1 <= TABS_P_PAGE * page) || (tabIndex + 1 > TABS_P_PAGE * (page + 1));
    }

    private void drawBorder() {
        mc.renderEngine.bindTexture("/achievements_core/border.png");
        drawTexturedModalRect(getGuiX(), getGuiY(), 0, 0, PANE_WIDTH, PANE_HEIGHT);
    }

    private void drawTitle() {
        int guiX = (width - PANE_WIDTH) / 2;
        int guiY = (height - PANE_HEIGHT) / 2;
        fontRenderer.drawString(StatCollector.translateToLocal("gui.achievements"),
                guiX + TILE_SIZE, guiY + TILE_SIZE / 2, Colors.TITLE_COLOR);
    }

    private void drawPageButtons(int mouseX, int mouseY) {
        this.mc.renderEngine.bindTexture("/btwmodtex/fcguitrading.png");

        int leftX = getGuiX() - ARROW_WIDTH - ARROW_OFFSET_X;
        int rightX = getGuiX() + PANE_WIDTH + ARROW_OFFSET_X;
        int y = getGuiY() - ARROW_HEIGHT - ARROW_OFFSET_Y;

        if (hasPrevPage()) {
            // Show left button.
            int u = isPosInRect(mouseX, mouseY, leftX, y, ARROW_WIDTH, ARROW_HEIGHT) ? 189 : 177;
            drawTexturedModalRect(leftX, y, u, 21, ARROW_WIDTH, ARROW_HEIGHT);
        }
        if (hasNextPage()) {
            // Show right button.
            int u = isPosInRect(mouseX, mouseY, rightX, y, ARROW_WIDTH, ARROW_HEIGHT) ? 189 : 177;
            drawTexturedModalRect(rightX, y, u, 2, ARROW_WIDTH, ARROW_HEIGHT);
        }
    }

    private void drawAchievementHoverText(int mouseX, int mouseY, Achievement hovered) {
        if (hovered == null || !isInsideMap(mouseX, mouseY)) {
            return;
        }

        String name = hovered.getName();
        String description = hovered.getDescription();

        int x = mouseX + HOVER_OFFSET_X;
        int y = mouseY + HOVER_OFFSET_Y;

        int textWidth = Math.max(fontRenderer.getStringWidth(name), 120);
        int textHeight = fontRenderer.splitStringWidth(description, textWidth) + fontRenderer.FONT_HEIGHT;

        drawGradientRect(x - HOVER_PADDING, y - HOVER_PADDING,
                x + textWidth + HOVER_PADDING, y + textHeight + HOVER_PADDING + DESC_PADDING,
                Colors.HOVER_COLOR, Colors.HOVER_COLOR);
        fontRenderer.drawStringWithShadow(name, x, y, Colors.NAME_COLOUR);
        fontRenderer.drawSplitString(description, x, y + fontRenderer.FONT_HEIGHT + DESC_PADDING,
                textWidth, Colors.DESC_COLOUR);
    }

    private void drawTabHoverText(int mouseX, int mouseY) {
        AchievementTab hovered = getHoveredTab(mouseX, mouseY);
        if (hovered == null) {
            return;
        }

        String name = hovered.getName();

        int x = mouseX + HOVER_OFFSET_X;
        int y = mouseY + HOVER_OFFSET_Y;

        int textWidth = Math.max(fontRenderer.getStringWidth(name), 120);
        int textHeight = fontRenderer.FONT_HEIGHT;

        drawGradientRect(x - HOVER_PADDING, y - HOVER_PADDING,
                x + textWidth + HOVER_PADDING, y + textHeight + HOVER_PADDING,
                Colors.HOVER_COLOR, Colors.HOVER_COLOR);

        fontRenderer.drawStringWithShadow(name, x, y, Colors.NAME_COLOUR);
    }

    private AchievementTab getHoveredTab(int mouseX, int mouseY) {
        for (int tabIndex = 0; tabIndex < AchievementTabList.size(); tabIndex++) {
            AchievementTab tab = AchievementTabList.get(tabIndex);
            if (tab == null || isTabOffPage(tabIndex)) {
                continue;
            }

            int x = getGuiX() + TAB_WIDTH * (tabIndex % TABS_P_PAGE);
            int y = getGuiY() - TAB_HEIGHT + TAB_OFFSET;

            if (isPosInRect(mouseX, mouseY, x, y, TAB_WIDTH, TAB_HEIGHT)) {
                return tab;
            }
        }
        return null;
    }

    private boolean isPosInRect(int posX, int posY, int rectX, int rectY, int rectW, int rectH) {
        boolean x = posX >= rectX && posX <= rectX + rectW;
        boolean y = posY >= rectY && posY <= rectY + rectH;
        return x && y;
    }

    private int getGuiX() {
        return (width - PANE_WIDTH) / 2;
    }

    private int getGuiY() {
        return (height - PANE_HEIGHT) / 2;
    }
}
