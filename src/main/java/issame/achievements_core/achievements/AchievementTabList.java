package issame.achievements_core.achievements;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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
}
