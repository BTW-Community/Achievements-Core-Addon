package issame.example;

import btw.block.blocks.WorkStumpBlock;
import btw.entity.mob.CreeperEntity;
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
            AchievementsCore.trigger(ExampleAchievements.chiselWood, player);
        }
        else if (itemstack.itemID == BTWItems.sharpStone.itemID) {
            AchievementsCore.trigger(ExampleAchievements.chiselStone, player);
        }
    }
    
    @EventListener(EventType.COOKED)
    public void onCooked(EntityPlayer player, ItemStack itemstack) {
        if (itemstack.itemID == Item.porkCooked.itemID) {
            AchievementsCore.trigger(ExampleAchievements.cookPork, player);
        }
        else if (itemstack.itemID == BTWItems.ironNugget.itemID) {
            AchievementsCore.trigger(ExampleAchievements.cookIron, player);
        }
    }
    
    @EventListener(EventType.BREWED)
    public void onBrewed(EntityPlayer player, ItemStack itemstack) {
        if (itemstack.itemID == Item.potion.itemID && itemstack.getItemDamage() == 8195) {
            AchievementsCore.trigger(ExampleAchievements.firePotion, player);
        }
    }
    
    @EventListener(EventType.PICKUP)
    public void onPickup(EntityPlayer player, ItemStack itemstack) {
        if (itemstack.itemID == BTWItems.bark.itemID) {
            AchievementsCore.trigger(ExampleAchievements.mineBark, player);
        }
        else if (itemstack.itemID == Item.stick.itemID) {
            AchievementsCore.trigger(ExampleAchievements.mineStick, player);
        }
    }
    
    @EventListener(EventType.KILLED)
    public void onKilled(EntityPlayer player, EntityLiving entity) {
        if (entity instanceof EntityPig) {
            AchievementsCore.trigger(ExampleAchievements.killPig, player);
        }
    }
    
    @EventListener(EventType.PORTAL)
    public void onTravelledDimension(EntityPlayer player, int dimension) {
        if (dimension == -1) {
            AchievementsCore.trigger(ExampleAchievements.netherPortal, player);
        }
    }
    
    @EventListener(EventType.ENTITY_INTERACT)
    public void onEntityInteraction(EntityPlayer player, Entity entity, ItemStack heldItemStack) {
        if (entity instanceof CreeperEntity
                && ((CreeperEntity) entity).getNeuteredState() <= 0
                && heldItemStack.getItem() instanceof ItemShears) {
            AchievementsCore.trigger(ExampleAchievements.neuterCreeper, player);
        }
    }

    @EventListener(EventType.CONVERTED_BLOCK)
    public void onBlockConverted(EntityPlayer player, Block block, int metadata) {
        if (block instanceof WorkStumpBlock && (metadata & 8) == 0) {
            AchievementsCore.trigger(ExampleAchievements.workstump, player);
        }
    }

    @EventListener(EventType.DEATH)
    public void onDeath(EntityPlayer player, DamageSource damageSource) {
        if (damageSource.isFireDamage()) {
            AchievementsCore.trigger(ExampleAchievements.burn, player);
        }
    }
    
    @EventListener(EventType.TRADED)
    public void onTraded(EntityPlayer player, MerchantRecipe recipe) {
        if (recipe.getItemToBuy().itemID == Item.hoeIron.itemID) {
            AchievementsCore.trigger(ExampleAchievements.levelUpFarmer, player);
        }
    }
    
    @EventListener(EventType.CURED)
    public void onCured(EntityPlayer player, ZombieEntity zombieVillager) {
        AchievementsCore.trigger(ExampleAchievements.cureVillager, player);
    }
    
    @EventListener(EventType.CONSUMED)
    public void onConsumed(EntityPlayer player, ItemStack foodStack) {
        if (foodStack.getItem().equals(Item.bucketMilk)) {
            AchievementsCore.trigger(ExampleAchievements.drinkMilk, player);
        }
    }
}