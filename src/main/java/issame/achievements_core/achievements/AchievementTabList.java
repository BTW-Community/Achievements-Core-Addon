package issame.achievements_core.achievements;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AchievementTabList {
    // Holds a list of all registered achievement categories.
    private static final List<AchievementTab> tabList = new ArrayList<>();

    public static int add(AchievementTab achievementTab) {
        tabList.add(achievementTab);
        return tabList.size() - 1;
    }

    public static @Nullable AchievementTab get(int index) {
        if (tabList.isEmpty()) {
            return null;
        }
        return tabList.get(index);
    }

    public static int size() {
        return tabList.size();
    }

    public static Iterator<AchievementTab> iterator() {
        return tabList.iterator();
    }

    public static Achievement getAchievement(String name) {
        for (AchievementTab tab : tabList) {
            for (Achievement achievement : tab) {
                if (Objects.equals(achievement.getUnlocalizedName(), name)) {
                    return achievement;
                }
            }
        }
        return null;
    }

    public static List<String> getAllNames() {
        List<String> names = new LinkedList<>();
        for (AchievementTab tab : tabList) {
            for (Achievement achievement : tab) {
                names.add(achievement.getUnlocalizedName());
            }
        }
        return names;
    }
}
