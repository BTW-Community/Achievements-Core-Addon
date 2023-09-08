package issame.achievements_core;

import issame.achievements_core.achievements.Achievement;
import net.minecraft.src.EntityPlayer;

public class Colors {
    public static final int TITLE = 4210752;
    public static final int HOVER = -1073741824;
    public static final int NAME = -1;
    public static final int DESCRIPTION = -6250336;
    public static final int TAKEN = -7302913;
    public static final int REQUIRES = -9416624;

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
