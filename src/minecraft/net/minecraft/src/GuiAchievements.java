package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;

public class GuiAchievements extends GuiScreen
{	
	/** The top x coordinate of the achievement map */
    private static final int guiMapTop = AchievementTabList.minDisplayColumn * 24 - 112;

    /** The left y coordinate of the achievement map */
    private static final int guiMapLeft = AchievementTabList.minDisplayRow * 24 - 112;

    /** The bottom x coordinate of the achievement map */
    private static final int guiMapBottom = AchievementTabList.maxDisplayColumn * 24 - 77;

    /** The right y coordinate of the achievement map */
    private static final int guiMapRight = AchievementTabList.maxDisplayRow * 24 - 77;
    protected int achievementsPaneWidth = 252;  // 256 AA
    protected int achievementsPaneHeight = 150;  // 202 AA

    /** The current mouse x coordinate */
    protected int mouseX = 0;

    /** The current mouse y coordinate */
    protected int mouseY = 0;
    protected double field_74117_m;
    protected double field_74115_n;

    /** The x position of the achievement map */
    protected double guiMapX;

    /** The y position of the achievement map */
    protected double guiMapY;
    protected double field_74124_q;
    protected double field_74123_r;

    /** Whether the Mouse Button is down or not */
    private int isMouseButtonDown = 0;
    private StatFileWriter statFileWriter;
    
	 // AA
	List tabList = AchievementTabList.tabList;
	private static int tabIndex = 0;

    public GuiAchievements(StatFileWriter par1StatFileWriter)
    {
        this.statFileWriter = par1StatFileWriter;
        short var2 = 141;
        short var3 = 141;
        this.field_74117_m = this.guiMapX = this.field_74124_q = (double)(AchievementList.openInventory.displayColumn * 24 - var2 / 2 - 12);
        this.field_74115_n = this.guiMapY = this.field_74123_r = (double)(AchievementList.openInventory.displayRow * 24 - var3 / 2);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        //this.buttonList.add(new GuiSmallButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, StatCollector.translateToLocal("gui.done")));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }

        super.actionPerformed(button);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == this.mc.gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
        }
        else
        {
            super.keyTyped(par1, par2);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (Mouse.isButtonDown(0))
        {
            int guiLeft = (this.width - this.achievementsPaneWidth) / 2;
            int guiTop = (this.height - this.achievementsPaneHeight) / 2;
            int mapLeft = guiLeft + 8;
            int mapTop = guiTop + 17;

            if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && mouseX >= mapLeft && mouseX < mapLeft + 234 && mouseY >= mapTop && mouseY < mapTop + 113)  // AA
            {
                if (this.isMouseButtonDown == 0)
                {
                    this.isMouseButtonDown = 1;
                }
                else
                {
                    this.guiMapX -= (double)(mouseX - this.mouseX);
                    this.guiMapY -= (double)(mouseY - this.mouseY);
                    this.field_74124_q = this.field_74117_m = this.guiMapX;
                    this.field_74123_r = this.field_74115_n = this.guiMapY;
                }

                this.mouseX = mouseX;
                this.mouseY = mouseY;
            }

            if (this.field_74124_q < (double)guiMapTop)
            {
                this.field_74124_q = (double)guiMapTop;
            }

            if (this.field_74123_r < (double)guiMapLeft)
            {
                this.field_74123_r = (double)guiMapLeft;
            }

            if (this.field_74124_q >= (double)guiMapBottom)
            {
                this.field_74124_q = (double)(guiMapBottom - 1);
            }

            if (this.field_74123_r >= (double)guiMapRight)
            {
                this.field_74123_r = (double)(guiMapRight - 1);
            }
        }
        else
        {
            this.isMouseButtonDown = 0;
        }

        this.drawDefaultBackground();
        this.genAchievementBackground(mouseX, mouseY, partialTicks);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        this.drawTitle();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.field_74117_m = this.guiMapX;
        this.field_74115_n = this.guiMapY;
        double var1 = this.field_74124_q - this.guiMapX;
        double var3 = this.field_74123_r - this.guiMapY;

        if (var1 * var1 + var3 * var3 < 4.0D)
        {
            this.guiMapX += var1;
            this.guiMapY += var3;
        }
        else
        {
            this.guiMapX += var1 * 0.85D;
            this.guiMapY += var3 * 0.85D;
        }
    }

    /**
     * Draws the "Achievements" title at the top of the GUI.
     */
    protected void drawTitle()
    {
        int guiLeft = (this.width - this.achievementsPaneWidth) / 2;
        int guiTop = (this.height - this.achievementsPaneHeight) / 2;
        this.fontRenderer.drawString("Achievements", guiLeft + 15, guiTop + 5, 4210752);
    }

    protected void genAchievementBackground(int posX, int posY, float par3)
    {
        int windowY = MathHelper.floor_double(this.field_74117_m + (this.guiMapX - this.field_74117_m) * (double)par3);
        int windowX = MathHelper.floor_double(this.field_74115_n + (this.guiMapY - this.field_74115_n) * (double)par3);

        if (windowY < guiMapTop)
        {
            windowY = guiMapTop;
        }

        if (windowX < guiMapLeft)
        {
            windowX = guiMapLeft;
        }

        if (windowY >= guiMapBottom)
        {
            windowY = guiMapBottom - 1;
        }

        if (windowX >= guiMapRight)
        {
            windowX = guiMapRight - 1;
        }

        int guiLeft = (this.width - this.achievementsPaneWidth) / 2;
        int guiTop = (this.height - this.achievementsPaneHeight) / 2;
        int shiftMapY = -32;
        int xShift = guiLeft + 16 +2;  // AA
        int yShift = guiTop + 17 +shiftMapY;
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, -200.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        this.mc.renderEngine.bindTexture("/terrain.png");
        int var10 = windowY + 288 >> 4;
        int var11 = windowX + 288 >> 4;
        int mapWidth = (windowY + 288) % 16 +16;  // AA
        int mapHeight = (windowX + 288) % 16 +shiftMapY;  // AA
        Random random = new Random();
        int i;
        int x1;
        int y1;

        for (i = 0; i * 16 - mapHeight < 155; ++i)
        {
            float brightness = 0.6F - (float)(var11 + i) / 25.0F * 0.3F;
            GL11.glColor4f(brightness, brightness, brightness, 1.0F);

            for (x1 = 0; x1 * 16 - mapWidth < 224; ++x1)
            {
                random.setSeed((long)(1234 + var10 + x1));
                random.nextInt();
                y1 = random.nextInt(1 + var11 + i) + (var11 + i) / 2;
                Icon icon = Block.sand.getIcon(0, 0);

                if (y1 <= 37 && var11 + i != 35)
                {
                    if (y1 == 22)
                    {
                        if (random.nextInt(2) == 0)
                        {
                            icon = Block.oreDiamond.getIcon(0, 0);
                        }
                        else
                        {
                            icon = Block.oreRedstone.getIcon(0, 0);
                        }
                    }
                    else if (y1 == 10)
                    {
                        icon = Block.oreIron.getIcon(0, 0);
                    }
                    else if (y1 == 8)
                    {
                        icon = Block.oreCoal.getIcon(0, 0);
                    }
                    else if (y1 > 4)
                    {
                        icon = Block.stone.getIcon(0, 0);
                    }
                    else if (y1 > 0)
                    {
                        icon = Block.dirt.getIcon(0, 0);
                    }
                }
                else
                {
                    icon = Block.bedrock.getIcon(0, 0);
                }

                this.drawTexturedModelRectFromIcon(xShift + x1 * 16 - mapWidth, yShift + i * 16 - mapHeight, icon, 16, 16);
            }
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        int y2;
        int flash;
        int x2;

        List achievementList = ((AchievementTab) tabList.get(tabIndex)).achievementList;
        for (i = 0; i < achievementList.size(); ++i)
        {
            Achievement achievement = (Achievement) achievementList.get(i);

            if (achievement.parentAchievement != null)
            {
                x1 = achievement.displayColumn * 24 - windowY + 11 + xShift;
                y1 = achievement.displayRow * 24 - windowX + 11 + yShift;
                x2 = achievement.parentAchievement.displayColumn * 24 - windowY + 11 + xShift;
                y2 = achievement.parentAchievement.displayRow * 24 - windowX + 11 + yShift;
                boolean hasUnlocked = this.statFileWriter.hasAchievementUnlocked(achievement);
                boolean canUnlock = this.statFileWriter.canUnlockAchievement(achievement);
                flash = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D ? 255 : 130;
                int color = -16777216;

                if (hasUnlocked)
                {
                    color = -9408400;
                }
                else if (canUnlock)
                {
                    color = 65280 + (flash << 24);
                }

                this.drawHorizontalLine(x1, x2, y1, color);
                this.drawVerticalLine(x2, y1, y2, color);
            }
        }

        Achievement achievementHovered = null;
        RenderItem renderItem = new RenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int stringWidth;
        int tooltipY;

        for (x1 = 0; x1 < achievementList.size(); ++x1)
        {
            Achievement achievement = (Achievement) achievementList.get(x1);
            x2 = achievement.displayColumn * 24 - windowY;
            y2 = achievement.displayRow * 24 - windowX;

            if (x2 >= -24 && y2 >= -24 && x2 <= 224 && y2 <= 155)
            {
                float brightness;

                if (this.statFileWriter.hasAchievementUnlocked(achievement))
                {
                    brightness = 1.0F;
                    GL11.glColor4f(brightness, brightness, brightness, 1.0F);
                }
                else if (this.statFileWriter.canUnlockAchievement(achievement))
                {
                    brightness = Math.sin((double)(Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) < 0.6D ? 0.6F : 0.8F;
                    GL11.glColor4f(brightness, brightness, brightness, 1.0F);
                }
                else
                {
                    brightness = 0.3F;
                    GL11.glColor4f(brightness, brightness, brightness, 1.0F);
                }

                this.mc.renderEngine.bindTexture("/achievement/bg.png");
                stringWidth = xShift + x2;
                tooltipY = yShift + y2;

                if (achievement.getSpecial())
                {
                    this.drawTexturedModalRect(stringWidth - 2, tooltipY - 2, 26, 202, 26, 26);
                }
                else
                {
                    this.drawTexturedModalRect(stringWidth - 2, tooltipY - 2, 0, 202, 26, 26);
                }

                if (!this.statFileWriter.canUnlockAchievement(achievement))
                {
                    float borderBrightness = 0.1F;
                    GL11.glColor4f(borderBrightness, borderBrightness, borderBrightness, 1.0F);
                    renderItem.renderWithColor = false;
                }

                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
                renderItem.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, achievement.theItemStack, stringWidth + 3, tooltipY + 3);
                GL11.glDisable(GL11.GL_LIGHTING);

                if (!this.statFileWriter.canUnlockAchievement(achievement))
                {
                    renderItem.renderWithColor = true;
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                if (posX >= xShift && posY >= yShift && posX < xShift + 224 && posY < yShift + 155 && posX >= stringWidth && posX <= stringWidth + 22 && posY >= tooltipY && posY <= tooltipY + 22)
                {
                    achievementHovered = achievement;
                }
            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/achievement/window.png");  // AA
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.achievementsPaneWidth, this.achievementsPaneHeight);
        GL11.glPopMatrix();
        this.zLevel = 0.0F;
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        super.drawScreen(posX, posY, par3);

        if (achievementHovered != null)
        {
            String name = StatCollector.translateToLocal(achievementHovered.getName());
            String description = achievementHovered.getDescription();
            x2 = posX + 12;
            y2 = posY - 4;

            if (this.statFileWriter.canUnlockAchievement(achievementHovered))
            {
                stringWidth = Math.max(this.fontRenderer.getStringWidth(name), 120);
                tooltipY = this.fontRenderer.splitStringWidth(description, stringWidth);

                if (this.statFileWriter.hasAchievementUnlocked(achievementHovered))
                {
                    tooltipY += 12;
                }

                this.drawGradientRect(x2 - 3, y2 - 3, x2 + stringWidth + 3, y2 + tooltipY + 3 + 12, -1073741824, -1073741824);
                this.fontRenderer.drawSplitString(description, x2, y2 + 12, stringWidth, -6250336);

                if (this.statFileWriter.hasAchievementUnlocked(achievementHovered))
                {
                    this.fontRenderer.drawStringWithShadow(StatCollector.translateToLocal("achievement.taken"), x2, y2 + tooltipY + 4, -7302913);
                }
            }
            else
            {
                stringWidth = Math.max(this.fontRenderer.getStringWidth(name), 120);
                String requiredDesc = StatCollector.translateToLocalFormatted("achievement.requires", new Object[] {StatCollector.translateToLocal(achievementHovered.parentAchievement.getName())});
                flash = this.fontRenderer.splitStringWidth(requiredDesc, stringWidth);
                this.drawGradientRect(x2 - 3, y2 - 3, x2 + stringWidth + 3, y2 + flash + 12 + 3, -1073741824, -1073741824);
                this.fontRenderer.drawSplitString(requiredDesc, x2, y2 + 12, stringWidth, -9416624);
            }

            this.fontRenderer.drawStringWithShadow(name, x2, y2, this.statFileWriter.canUnlockAchievement(achievementHovered) ? (achievementHovered.getSpecial() ? -128 : -1) : (achievementHovered.getSpecial() ? -8355776 : -8355712));
        }
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHelper.enableGUIStandardItemLighting();
        for (i = 0; i < tabList.size(); ++i)
        {
            AchievementTab tab = (AchievementTab) tabList.get(i);
            this.mc.renderEngine.bindTexture("/gui/allitems.png");
            this.renderAchievementTab(tab);
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        RenderHelper.disableStandardItemLighting();
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return true;
    }
    
    // AA
    /**
     * Renders passed creative inventory tab into the screen.
     */
    protected void renderAchievementTab(AchievementTab tab)
    {
    	int guiLeft = (this.width - this.achievementsPaneWidth) / 2;
        int guiTop = (this.height - this.achievementsPaneHeight) / 2;
        
    	boolean tabSelected = tab.getIndex() == tabIndex;
        boolean tabFirstRow = tab.getIndex() < 6;
        int i = tab.getIndex() % 6;
        int j = i * 28;
        int k = 0;
        int l = guiLeft + 28 * i;
        int i1 = guiTop;
        byte j1 = 32;

        if (tabSelected)
        {
            k += 32;
        }

        if (i == 5)
        {
            l = guiLeft + this.achievementsPaneWidth - 28;
        }
        else if (i > 0)
        {
            l += i;
        }

        if (tabFirstRow)
        {
            i1 -= 28;
        }
        else
        {
            k += 64;
            i1 += this.achievementsPaneHeight - 4;
        }
        
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawTexturedModalRect(l, i1, j, k, 28, j1);
        this.zLevel = 100.0F;
        RenderItem renderItem = new RenderItem();
        renderItem.zLevel = 100.0F;
        l += 6;
        i1 += 8 + (tabFirstRow ? 1 : -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        ItemStack itemstack = new ItemStack(tab.getIconItem());
        renderItem.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, itemstack, l, i1);
        renderItem.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, itemstack, l, i1);
        GL11.glDisable(GL11.GL_LIGHTING);
        renderItem.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }
}
