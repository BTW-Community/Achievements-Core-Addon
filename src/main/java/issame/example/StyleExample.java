package issame.example;

import issame.achievements_core.achievements.style.AchievementStyle;

public class StyleExample extends AchievementStyle {
    @Override
    public String getFormatString() {
        return "§4";
    }

    @Override
    public String getAnnounceMessage() {
        return "achievement.announce.example";
    }

    @Override
    public String getFrameSheet() {
        return "/example/frame_sheet.png";
    }

    @Override
    public int getU() {
        return 0;
    }

    @Override
    protected int getV() {
        return 0;
    }
}
