package issame.achievements_core.achievements;

import issame.achievements_core.AchievementsCore;
import issame.achievements_core.achievements.style.AchievementStyle;
import issame.achievements_core.achievements.style.StyleDefault;
import net.minecraft.src.*;

public class Achievement {
    public static final int SIZE = 26;
    private final String name;
    private final int column;
    private final int row;
    private final ItemStack icon;
    private final AchievementTab tab;
    private AchievementStyle style;
    private boolean isHidden = false;
    public int threshold = 1;
    private Achievement[] parents;

    public Achievement(String name, int column, int row, ItemStack icon, AchievementTab tab) {
        this.name = name;
        this.column = column;
        this.row = row;
        this.icon = icon;
        this.tab = tab;
        this.style = new StyleDefault();

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

    public Achievement[] getParents() {
        return parents;
    }

    public Achievement setParents(Achievement... parents) {
        this.parents = parents;
        return this;
    }

    public AchievementTab getTab() {
        return tab;
    }

    public String getFrameSheet() {
        return style.getFrameSheet();
    }

    public Achievement setStyle(AchievementStyle style) {
        this.style = style;
        return this;
    }

    public String getFormatString() {
        return style.getFormatString();
    }

    public int getFrameU() {
        return style.getU();
    }

    public int getFrameV(boolean hasUnlocked) {
        return style.getV(hasUnlocked);
    }

    public Achievement setHidden() {
        isHidden = true;
        return this;
    }

    public boolean shouldHide(EntityPlayer player) {
        return isHidden && getStatus(player) != AchievementStatus.UNLOCKED;
    }

    public Achievement setThreshold(int threshold) {
        this.threshold = threshold;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getName() {
        return StatCollector.translateToLocal("achievement." + name);
    }

    public String getUnlocalizedName() {
        return name;
    }

    public String getDescription() {
        return StatCollector.translateToLocal("achievement." + name + ".desc");
    }

    public AchievementStatus getStatus(EntityPlayer player) {
        if (AchievementsCore.hasUnlocked(this, player)) return AchievementStatus.UNLOCKED;
        if (parents == null) return AchievementStatus.CAN_UNLOCK;

        for (Achievement parent : parents) {
            if (parent.getStatus(player) == AchievementStatus.UNLOCKED) return AchievementStatus.CAN_UNLOCK;
        }
        return AchievementStatus.LOCKED;
    }
}
