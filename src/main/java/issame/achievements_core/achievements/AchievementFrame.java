package issame.achievements_core.achievements;

import net.minecraft.src.EntityPlayer;

import static issame.achievements_core.achievements.Achievement.SIZE;

public class AchievementFrame {
    private final Achievement achievement;
    public String frameSet;
    private int u;
    private int v;

    public AchievementFrame(Achievement achievement, String frameSet) {
        this.achievement = achievement;
        this.frameSet = frameSet;
        setFrame(0);
    }

    public AchievementFrame(Achievement achievement) {
        this(achievement, "default");
    }

    public void setFrame(int index) {
        u = SIZE * (index % SIZE);
        v = SIZE * (index / SIZE) * 2;
    }

    public int getU() {
        return u;
    }

    public int getV(EntityPlayer player) {
        if (achievement.getStatus(player) == AchievementStatus.UNLOCKED) {
            return v + SIZE;
        }
        return v;
    }
}
