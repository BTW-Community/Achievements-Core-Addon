package issame.example;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.block.BTWBlocks;
import btw.item.BTWItems;
import issame.achievements_core.achievements.Achievement;
import issame.achievements_core.achievements.AchievementTab;
import issame.achievements_core.achievements.style.StyleChallenge;
import issame.achievements_core.achievements.style.StyleGoal;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

public class ExampleAchievements extends BTWAddon {

    /* List of custom achievements */
    public static Achievement mineBark;
    public static Achievement mineStick;
    public static Achievement chiselWood;
    public static Achievement chiselStone;
    public static Achievement killPigs;
    public static Achievement cookPork;
    public static Achievement drinkMilk;
    public static Achievement cookIron;
    public static Achievement neuterCreeper;
    public static Achievement netherPortal;
    public static Achievement firePotion;
    public static Achievement cureVillager;
    public static Achievement levelUpFarmer;
    public static Achievement burn;
    public static Achievement workstump;
    public static Achievement tossMilk;

    public static Achievement powerCake;
    public static Achievement returnGhast;
    private static ExampleAchievements instance;

    public ExampleAchievements() {
        super("Example Achievements", "1.0.0", "EA");
    }

    public static ExampleAchievements getInstance() {
        if (instance == null) {
            instance = new ExampleAchievements();
        }
        return instance;
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        AchievementTab tabSimple = new AchievementTab("simple").setIcon(BTWBlocks.companionCube);

        tossMilk = new Achievement("tossMilk", -8, -8, Item.bucketMilk, tabSimple);

        returnGhast = new Achievement("returnGhast", -6, -6, Item.fireballCharge, tabSimple);

        powerCake = new Achievement("powerCake", -4, -4, Item.cake, tabSimple);

        mineBark = new Achievement("mineBark", 0, -1, BTWItems.bark, tabSimple);
        mineStick = new Achievement("mineStick", 0, 1, Item.stick, tabSimple);
        chiselWood = new Achievement("chiselWood", 2, 0, BTWItems.pointyStick, tabSimple)
                .setParents(mineBark, mineStick);
        chiselStone = new Achievement("chiselStone", 4, 0, BTWItems.sharpStone, tabSimple)
                .setParents(chiselWood);
        killPigs = (new Achievement("killPig", 4, -2, Item.porkRaw, tabSimple))
                .setParents(chiselStone).setStyle(new StyleGoal()).setThreshold(5);
        cookPork = (new Achievement("cookPork", 4, -4, Item.porkCooked, tabSimple))
                .setParents(killPigs).setStyle(new StyleChallenge());
        drinkMilk = (new Achievement("drinkMilk", 4, -6, Item.bucketMilk, tabSimple))
                .setParents(cookPork).setStyle(new StyleExample());
        cookIron = (new Achievement("cookIron", 6, 0, BTWBlocks.ironOreChunkStorage, tabSimple))
                .setParents(chiselStone);
        neuterCreeper = (new Achievement("neuterCreeper", 6, 2, BTWItems.creeperOysters, tabSimple))
                .setParents(cookIron).setHidden();
        workstump = (new Achievement("workstump", 6, -2, BTWBlocks.workStump, tabSimple))
                .setParents(cookIron);

        AchievementTab tabCustom = new CustomAchievementTab("custom").setIcon(Item.netherStar);
        netherPortal = (new Achievement("netherPortal", 0, 0, Block.obsidian, tabCustom))
                .setParents(neuterCreeper);
        firePotion = (new Achievement("firePotion", 0, 2, Item.potion, tabCustom))
                .setParents(netherPortal);
        cureVillager = (new Achievement("cureVillager", 2, 0, BTWItems.soulUrn, tabCustom))
                .setParents(netherPortal).setStyle(new StyleGoal());
        levelUpFarmer = (new Achievement("levelUpFarmer", 4, 0, Block.blockEmerald, tabCustom))
                .setParents(cureVillager).setStyle(new StyleChallenge());
        burn = (new Achievement("burn", 4, 2, Block.fire, tabCustom))
                .setParents(firePotion);


        EventDispatcher.register(new ExampleEventHandler());
        AddonHandler.logMessage(this.getName() + " Initialization Complete.");
    }
}
