package issame.achievements_core.achievements.style;

import issame.achievements_core.Colors;
import net.minecraft.src.StatCollector;

import static issame.achievements_core.achievements.Achievement.SIZE;

public abstract class AchievementStyle {

    public abstract String getFrameSheet();

    /**
     * <a href="https://minecraft.fandom.com/wiki/Formatting_codes">Formatting codes (Minecraft Wiki)</a>
     * @return
     */
    public String getAnnounceFormatString() {
        return "§a";
    }

    public String getAnnounceMessage() {
        return "achievement.announce.default";
    }

    public String getAchievementGetMessage() {
        return StatCollector.translateToLocal("achievement.get");
    }

    public int getAchievementGetColor() {
        return Colors.ACHIEVEMENT_GET;
    }

    public int getU() {
        return 0;
    };

    protected int getV() {
        return 0;
    };

    public int getV(boolean hasUnlocked) {
        return hasUnlocked ? getV() + SIZE : getV();
    }
}
