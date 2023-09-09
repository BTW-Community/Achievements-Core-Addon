package issame.achievements_core.achievements.style;

import issame.achievements_core.Colors;
import issame.achievements_core.achievements.Achievement;
import net.minecraft.src.StatCollector;

public class StyleChallenge extends StyleDefault {
    @Override
    public String getAnnounceFormatString() {
        return "§5";
    }

    @Override
    public String getAnnounceMessage() {
        return "achievement.announce.challenge";
    }

    @Override
    public String getAchievementGetMessage() {
        return StatCollector.translateToLocal("achievement.get.challenge");
    }

    @Override
    public int getAchievementGetColor() {
        return Colors.CHALLENGE_GET;
    }

    @Override
    public int getU() {
        return Achievement.SIZE;
    }
}
