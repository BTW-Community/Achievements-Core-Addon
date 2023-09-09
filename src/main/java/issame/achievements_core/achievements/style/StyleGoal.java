package issame.achievements_core.achievements.style;

import issame.achievements_core.Colors;
import issame.achievements_core.achievements.Achievement;
import net.minecraft.src.StatCollector;

public class StyleGoal extends StyleDefault {
    @Override
    public String getAnnounceMessage() {
        return "achievement.announce.goal";
    }

    @Override
    public int getU() {
        return Achievement.SIZE * 2;
    }

    @Override
    public String getAchievementGetMessage() {
        return StatCollector.translateToLocal( "achievement.get.goal");
    }
}
