package issame.achievements_core.achievements.style;

import issame.achievements_core.achievements.Achievement;

public class StyleGoal extends StyleDefault {
    @Override
    public String getAnnounceMessage() {
        return "achievement.announce.goal";
    }

    @Override
    public int getU() {
        return Achievement.SIZE * 2;
    }
}
