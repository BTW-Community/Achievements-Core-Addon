package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AchievementList
{
    /** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;

    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;

    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;

    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;

    /** Holds a list of all registered achievements. */
    public static List achievementList = new ArrayList();

    // Vanilla Achievements stored in a AchievementTab
    // Set all achievements to null so they don't register,
    // apart from openInventory since it needs to be not null in other places.
    public static AchievementTab minecraftTab = new AchievementTab("minecraft").setIcon(Block.grass.blockID);
    
    /** Is the 'open inventory' achievement. */
    public static Achievement openInventory = (new Achievement(0, "openInventory", 0, 0, Item.book, (Achievement)null));
    
    /** Is the 'getting wood' achievement. */
    public static Achievement mineWood = null;
    
    /** Is the 'benchmarking' achievement. */
    public static Achievement buildWorkBench = null;
    
    /** Is the 'time to mine' achievement. */
    public static Achievement buildPickaxe = null;
    
    /** Is the 'hot topic' achievement. */
    public static Achievement buildFurnace = null;
    
    /** Is the 'acquire hardware' achievement. */
    public static Achievement acquireIron = null;
    
    /** Is the 'time to farm' achievement. */
    public static Achievement buildHoe = null;
    
    /** Is the 'bake bread' achievement. */
    public static Achievement makeBread = null;
    
    /** Is the 'the lie' achievement. */
    public static Achievement bakeCake = null;
    
    /** Is the 'getting a upgrade' achievement. */
    public static Achievement buildBetterPickaxe = null;
    
    /** Is the 'delicious fish' achievement. */
    public static Achievement cookFish = null;
    
    /** Is the 'on a rail' achievement */
    public static Achievement onARail = null;
    
    /** Is the 'time to strike' achievement. */
    public static Achievement buildSword = null;
    
    /** Is the 'monster hunter' achievement. */
    public static Achievement killEnemy = null;
    
    /** is the 'cow tipper' achievement. */
    public static Achievement killCow = null;
    
    /** Is the 'when pig fly' achievement. */
    public static Achievement flyPig = null;
    
    /** The achievement for killing a Skeleton from 50 meters aways. */
    public static Achievement snipeSkeleton = null;
    
    /** Is the 'DIAMONDS!' achievement */
    public static Achievement diamonds = null;
    
    /** Is the 'We Need to Go Deeper' achievement */
    public static Achievement portal = null;
    
    /** Is the 'Return to Sender' achievement */
    public static Achievement ghast = null;
    
    /** Is the 'Into Fire' achievement */
    public static Achievement blazeRod = null;
    
    /** Is the 'Local Brewery' achievement */
    public static Achievement potion = null;
    
    /** Is the 'The End?' achievement */
    public static Achievement theEnd = null;
    
    /** Is the 'The End.' achievement */
    public static Achievement theEnd2 = null;
    
    /** Is the 'Enchanter' achievement */
    public static Achievement enchantments = null;
    public static Achievement overkill = null;
    
    /** Is the 'Librarian' achievement */
    public static Achievement bookcase = null;

    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {}

    static
    {
    	EventDispatcher.init();
    	//System.out.println("Minecraft: " + minecraftTab.size() + " achievements");
    }
}