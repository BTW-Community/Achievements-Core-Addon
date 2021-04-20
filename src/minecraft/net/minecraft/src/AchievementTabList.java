package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AchievementTabList {
	/** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;

    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;

    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;

    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;
    
	/** Holds a list of all registered achievement categories. */
    public static List tabList = new ArrayList();
    /** Counts the number of id's */
    public static int counter = 0;
}
