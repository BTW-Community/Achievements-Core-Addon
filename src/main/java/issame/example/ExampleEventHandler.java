package issame.example;

import btw.entity.mob.ZombieEntity;
import btw.item.BTWItems;
import issame.achievements_core.AchievementsCore;
import issame.achievements_core.event.EventListener;
import issame.achievements_core.event.EventType;
import net.minecraft.src.*;

/**
 * An example event handler.
 * <p>
 * The names of the functions do not matter, all you need are the annotations
 * and the relevant parameters.
 */
public class ExampleEventHandler {
    @EventListener(EventType.CRAFTED)
    public void onCrafted(EntityPlayer player, ItemStack itemstack) {
        if (itemstack.itemID == BTWItems.pointyStick.itemID) {
            AchievementsCore.update(ExampleAchievements.chiselWood, player);
        }
        else if (itemstack.itemID == BTWItems.sharpStone.itemID) {
            AchievementsCore.update(ExampleAchievements.chiselStone, player);
        }
    }
    
    @EventListener(EventType.COOKED)
    public void onCooked(EntityPlayer player, ItemStack itemstack) {
        if (itemstack.itemID == Item.porkCooked.itemID) {
            AchievementsCore.update(ExampleAchievements.cookPork, player);
        }
        else if (itemstack.itemID == BTWItems.ironNugget.itemID) {
            AchievementsCore.update(ExampleAchievements.cookIron, player);
        }
    }
    
    @EventListener(EventType.BREWED)
    public void onBrewed(EntityPlayer player, ItemStack itemstack) {
        if (itemstack.itemID == Item.potion.itemID && itemstack.getItemDamage() == 8195) {
            AchievementsCore.update(ExampleAchievements.firePotion, player);
        }
    }
    
    @EventListener(EventType.PICKUP)
    public void onPickup(EntityPlayer player, ItemStack itemstack) {
        if (itemstack.itemID == BTWItems.bark.itemID) {
            AchievementsCore.update(ExampleAchievements.mineBark, player);
        }
        else if (itemstack.itemID == Item.stick.itemID) {
            AchievementsCore.update(ExampleAchievements.mineStick, player);
        }
    }
    
    @EventListener(EventType.KILLED)
    public void onKilled(EntityPlayer player, EntityLiving entity) {
        if (entity instanceof EntityPig) {
            AchievementsCore.update(ExampleAchievements.killPig, player);
        }
    }
    
    @EventListener(EventType.PORTAL)
    public void onTravelledDimension(EntityPlayer player, int dimension) {
        if (dimension == -1) {
            AchievementsCore.update(ExampleAchievements.netherPortal, player);
        }
    }
    
    @EventListener(EventType.ENTITY_INTERACT)
    public void onEntityInteraction(EntityPlayer player, Entity entity) {
        if (entity instanceof EntityCreeper) {
            Item currentItem = player.getCurrentEquippedItem().getItem();
            if (currentItem instanceof ItemShears) {
                AchievementsCore.update(ExampleAchievements.neuterCreeper, player);
            }
        }
    }
    
    @EventListener(EventType.TRADED)
    public void onTraded(EntityPlayer player, MerchantRecipe recipe) {
        if (recipe.getItemToBuy().itemID == Item.hoeIron.itemID) {
            AchievementsCore.update(ExampleAchievements.levelUpFarmer, player);
        }
    }
    
    @EventListener(EventType.CURED)
    public void onCured(EntityPlayer player, ZombieEntity zombieVillager) {
        AchievementsCore.update(ExampleAchievements.cureVillager, player);
    }
    
    @EventListener(EventType.CONSUMED)
    public void onConsumed(EntityPlayer player, ItemStack foodStack) {
        if (foodStack.getItem().equals(Item.bucketMilk)) {
            AchievementsCore.update(ExampleAchievements.drinkMilk, player);
        }
    }
}