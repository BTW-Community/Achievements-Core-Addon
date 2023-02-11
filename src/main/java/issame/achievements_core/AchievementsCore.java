package issame.achievements_core;

import btw.AddonHandler;
import btw.BTWAddon;
import issame.achievements_core.achievements.Achievement;
import issame.achievements_core.achievements.AchievementTab;
import issame.achievements_core.achievements.AchievementTabList;
import net.minecraft.src.EntityPlayer;

import java.util.Iterator;

public class AchievementsCore extends BTWAddon {
    private static AchievementsCore instance;

    private AchievementsCore() {
        super("Achievements Core", "3.0.0", "AC");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        AddonHandler.logMessage(this.getName() + " Initialization Complete.");
    }

    @Override
    public void postInitialize() {
        super.postInitialize();

        for (Iterator<AchievementTab> it = AchievementTabList.iterator(); it.hasNext(); ) {
            AchievementTab tab = it.next();
            AddonHandler.logMessage(String.format("%s: %d achievements", tab.getName(), tab.size()));
        }
    }

    public static void triggerAchievement(EntityPlayer player, Achievement achievement) {
        AddonHandler.logMessage(String.format("%s triggered %s", player.getEntityName(), achievement.getName()));
    }
}
