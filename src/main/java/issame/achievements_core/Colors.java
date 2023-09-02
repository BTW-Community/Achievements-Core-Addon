package issame.achievements_core;

import issame.achievements_core.achievements.Achievement;
import net.minecraft.src.EntityPlayer;

public class Colors {
    public static final int TITLE_COLOR = 4210752;
    public static final int HOVER_COLOR = -1073741824;
    public static final int NAME_COLOUR = -1;
    public static final int DESC_COLOUR = -6250336;

    public static final int CONNECTION_LOCKED = -16777216;
    public static final int CONNECTION_UNLOCKED = -9408400;
    public static final int CONNECTION_CAN_UNLOCK = -10513578;

    public static int getConnectionColor(Achievement achievement, EntityPlayer player) {
        switch (achievement.getStatus(player)) {
            case LOCKED:
                return CONNECTION_LOCKED;
            case CAN_UNLOCK:
                return CONNECTION_CAN_UNLOCK;
            case UNLOCKED:
                return CONNECTION_UNLOCKED;
        }
        return CONNECTION_LOCKED;
    }
}
