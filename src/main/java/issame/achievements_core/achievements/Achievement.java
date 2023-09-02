package issame.achievements_core.achievements;

import btw.AddonHandler;
import issame.achievements_core.AchievementsCore;
import net.minecraft.src.*;

public class Achievement {
    public static final int SIZE = 26;
    private final String name;
    private final int column;
    private final int row;
    private final ItemStack icon;
    private final AchievementTab tab;
    private final AchievementFrame frame = new AchievementFrame(this);
    public boolean isHidden = false;
    public int threshold = 1;
    public String formatCode = "§a";
    private Achievement[] parents;

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

    public String getFrameSet() {
        return frame.frameSet;
    }

    public Achievement setFrameSet(String frameSet) {
        frame.frameSet = frameSet;
        return this;
    }

    public Achievement setFrame(int index) {
        frame.setFrame(index);
        return this;
    }

    public int getFrameU() {
        return frame.getU();
    }

    public int getFrameV(EntityPlayer player) {
        return frame.getV(player);
    }

    public Achievement setHidden() {
        isHidden = true;
        return this;
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
        return StatCollector.translateToLocal(name);
    }

    public String getUnlocalizedName() {
        return name;
    }

    public String getDescription() {
        return StatCollector.translateToLocal(name + ".desc");
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
