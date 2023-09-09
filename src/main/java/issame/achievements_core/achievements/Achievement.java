package issame.achievements_core.achievements;

import issame.achievements_core.AchievementsCore;
import issame.achievements_core.achievements.style.AchievementStyle;
import issame.achievements_core.achievements.style.StyleDefault;
import net.minecraft.src.*;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public String getParentNames() {
        return Arrays.stream(parents).map(Achievement::getName).collect(Collectors.joining(", "));
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
        return style.getAnnounceFormatString();
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

    public String getAnnounceName() {
        return getFormatString() + "[" + getName() + "]§r";
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

    public String getAnnounceMessage(EntityPlayer player) {
        return StatCollector.translateToLocalFormatted(style.getAnnounceMessage(),
                player.getEntityName(), getAnnounceName());
    }

    public void recursivelyTrigger(EntityPlayer player, boolean unlock) {
        if (unlock && getStatus(player) == AchievementStatus.UNLOCKED) return;
        if (!unlock && getStatus(player) != AchievementStatus.UNLOCKED) return;

        if (unlock && parents != null) {
            for (Achievement parent : parents) {
                parent.recursivelyTrigger(player, unlock);
            }
        }
        int amount = unlock ? threshold : 0;
        AchievementsCore.trigger(this, player, amount, true);
    }

    public AchievementStyle getStyle() {
        return style;
    }
}
