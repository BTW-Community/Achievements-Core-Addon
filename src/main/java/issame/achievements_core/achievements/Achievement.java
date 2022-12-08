package issame.achievements_core.achievements;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatCollector;

public class Achievement {
    private final String name;
    private final int column;
    private final int row;
    private final ItemStack icon;
    private final AchievementTab tab;

    private final String description;

    private Achievement[] parents = null;
    private boolean isHidden = false;

    public Achievement(String name, int column, int row, ItemStack icon, AchievementTab tab) {
        this.name = "achievement." + name;
        this.column = column;
        this.row = row;
        this.icon = icon;
        this.tab = tab;

        description = this.name + ".desc";
        tab.add(this);
    }

    public Achievement(String name, int column, int row, Item icon, AchievementTab tab) {
        this(name, column, row, new ItemStack(icon), tab);
    }

    public Achievement(String name, int column, int row, Block icon, AchievementTab tab) {
        this(name, column, row, new ItemStack(icon), tab);
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

    public Achievement setFrame(int x, int y) {
        return this;
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

    @Override
    public String toString() {
        return StatCollector.translateToLocal(name);
    }
}
