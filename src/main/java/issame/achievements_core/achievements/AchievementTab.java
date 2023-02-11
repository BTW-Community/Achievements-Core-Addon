package issame.achievements_core.achievements;

import net.minecraft.src.Block;
import net.minecraft.src.Icon;
import net.minecraft.src.Item;
import net.minecraft.src.StatCollector;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AchievementTab implements Iterable<Achievement> {
    private final String name;

    private final List<Achievement> achievements;

    private final int index;

    private int iconID = Item.paper.itemID;

    private int minColumn = 0;
    private int maxColumn = 0;
    private int minRow = 0;
    private int maxRow = 0;

    public AchievementTab(String name) {
        this.name = "achievementtab." + name;
        achievements = new ArrayList<>();
        index = AchievementTabList.add(this);
    }

    public AchievementTab setIcon(int id) {
        this.iconID = id;
        return this;
    }

    public AchievementTab setIcon(Item item) {
        return setIcon(item.itemID);
    }

    public AchievementTab setIcon(Block block) {
        return setIcon(block.blockID);
    }

    public String getName() {
        return StatCollector.translateToLocal(name);
    }

    public void add(Achievement achievement) {
        achievements.add(achievement);

        minColumn = Math.min(minColumn, achievement.getColumn());
        maxColumn = Math.max(maxColumn, achievement.getColumn());
        minRow = Math.min(minRow, achievement.getRow());
        maxRow = Math.max(maxRow, achievement.getRow());
    }

    public int getIconID() {
        return iconID;
    }

    public int getIndex() {
        return index;
    }

    public int size() {
        return achievements.size();
    }

    /**
     * Generates the background of a tab.
     * Uses the vanilla background by default.
     * Override this method to create a custom background.
     */
    protected Icon genAchievementIcon(int x, int y) {
        Random random = new Random();

        float brightness = 0.6F - y / 25.0F * 0.3F;
        GL11.glColor4f(brightness, brightness, brightness, 1.0F);

        random.setSeed(1234 + x);
        random.nextInt();
        int y1 = Math.max(0, y);
        y1 = random.nextInt(1 + y1) + y1 / 2;
        Icon icon = Block.sand.getIcon(0, 0);

        if (y1 <= 37 && y != 35) {
            if (y1 == 22) {
                if (random.nextInt(2) == 0) {
                    icon = Block.oreDiamond.getIcon(0, 0);
                } else {
                    icon = Block.oreRedstone.getIcon(0, 0);
                }
            } else if (y1 == 10) {
                icon = Block.oreIron.getIcon(0, 0);
            } else if (y1 == 8) {
                icon = Block.oreCoal.getIcon(0, 0);
            } else if (y1 > 4) {
                icon = Block.stone.getIcon(0, 0);
            } else if (y1 > 0) {
                icon = Block.dirt.getIcon(0, 0);
            }
        } else {
            icon = Block.bedrock.getIcon(0, 0);
        }
        return icon;
    }

    @NotNull
    @Override
    public Iterator<Achievement> iterator() {
        return achievements.iterator();
    }
}
