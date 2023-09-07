package issame.achievements_core.achievements;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@Environment(EnvType.CLIENT)
public class AchievementPopup extends Gui
{
    /** Holds the instance of the game (Minecraft) */
    private final Minecraft mc;

    /** Holds the latest width scaled to fit the game window. */
    private int windowWidth;

    /** Holds the latest height scaled to fit the game window. */
    private int windowHeight;
    private String achievementGetText;

    /** Holds the achievement that will be displayed on the GUI. */
    private Achievement achievement;
    private long achievementTime;

    /**
     * Holds a instance of RenderItem, used to draw the achievement icons on screen (is based on ItemStack)
     */
    private final RenderItem renderItem;

    public AchievementPopup(Minecraft par1Minecraft)
    {
        mc = par1Minecraft;
        renderItem = new RenderItem();
    }

    /**
     * Queue a taken achievement to be displayed.
     */
    public void queueTakenAchievement(Achievement achievement)
    {
        achievementGetText = StatCollector.translateToLocal("achievement.get");
        achievementTime = Minecraft.getSystemTime();
        this.achievement = achievement;
    }

    /**
     * Update the display of the achievement window to match the game window.
     */
    private void updateAchievementWindowScale()
    {
        GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        ScaledResolution scaledResolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        windowWidth = scaledResolution.getScaledWidth();
        windowHeight = scaledResolution.getScaledHeight();
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, windowWidth, windowHeight, 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
    }

    /**
     * Updates the small achievement tooltip window, showing a queued achievement if is needed.
     */
    public void updateAchievementWindow()
    {
        if (achievement != null && achievementTime != 0L)
        {
            double var1 = (Minecraft.getSystemTime() - achievementTime) / 3000.0D;

            if (var1 < 0.0D || var1 > 1.0D)
            {
                achievementTime = 0L;
            }
            else
            {
                updateAchievementWindowScale();
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                double var3 = var1 * 2.0D;

                if (var3 > 1.0D)
                {
                    var3 = 2.0D - var3;
                }

                var3 *= 4.0D;
                var3 = 1.0D - var3;

                if (var3 < 0.0D)
                {
                    var3 = 0.0D;
                }

                var3 *= var3;
                var3 *= var3;
                int var5 = windowWidth - 160;
                int var6 = (int) (-var3 * 36);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                mc.renderEngine.bindTexture("/achievements_core/border.png");
                GL11.glDisable(GL11.GL_LIGHTING);
                drawTexturedModalRect(var5, var6, 96, 202, 160, 32);

                mc.fontRenderer.drawString(achievementGetText, var5 + 30, var6 + 7, -256);
                mc.fontRenderer.drawString(achievement.getName(), var5 + 30, var6 + 18, -1);

                RenderHelper.enableGUIStandardItemLighting();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glEnable(GL11.GL_COLOR_MATERIAL);
                GL11.glEnable(GL11.GL_LIGHTING);
                renderItem.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, achievement.getIcon(), var5 + 8, var6 + 8);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }
        }
    }
}
