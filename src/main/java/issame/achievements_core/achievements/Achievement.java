package issame.achievements_core.achievements;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatCollector;

public class Achievement {
    public static final int SIZE = 26;

    public boolean isHidden = false;
    public String frameSet = "default";

    private final String name;
    private final int column;
    private final int row;
    private final ItemStack icon;
    private final AchievementTab tab;

    private Achievement[] parents;

    // uv coordinates from frameSet.
    private int u = 0;
    private int v = 0;

    public Achievement(String name, int column, int row, ItemStack icon, AchievementTab tab) {
        this.name = "achievement." + name;
        this.column = column;
        this.row = row;
        this.icon = icon;
        this.tab = tab;

        tab.add(this);
    }

    public Achievement(String name, int column, int row, Item icon, AchievementTab tab) {
        this(name, column, row, new ItemStack(icon), tab);
    }

    public Achievement(String name, int column, int row, Block icon, AchievementTab tab) {
        this(name, column, row, new ItemStack(icon), tab);
    }

    public ItemStack getIcon() {
        return icon;
    }

    public Achievement setParents(Achievement... parents) {
        this.parents = parents;
        return this;
    }

    public Achievement[] getParents() {
        return parents;
    }

    public AchievementTab getTab() {
        return tab;
    }

    public String getFrameSet() {
        return frameSet;
    }

    public Achievement setFrame(int x, int y) {
        u = SIZE * x;
        v = SIZE * y;
        return this;
    }

    public int getFrameU() {
        return u;
    }

    public int getFrameV() {
        return v;
    }

    public Achievement setHidden() {
        isHidden = true;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getName() {
        return StatCollector.translateToLocal(name);
    }

    public String getDescription() {
        return StatCollector.translateToLocal(name + ".desc");
    }

    public AchievementStatus getStatus() {
        return AchievementStatus.CAN_UNLOCK;
    }
}
