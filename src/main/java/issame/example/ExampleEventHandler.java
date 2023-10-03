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
            AchievementsCore.trigger(ExampleAchievements.killPigs, player);
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

    @EventListener(EventType.MILK_TOSSED)
    public void onMilkTossed(EntityPlayer player) {
        AchievementsCore.trigger(ExampleAchievements.tossMilk, player);
    }

    @EventListener(EventType.CAKE_POWERED)
    public void onCakePowered(EntityPlayer player) {
        AchievementsCore.trigger(ExampleAchievements.powerCake, player);
    }

    @EventListener(EventType.GHAST_RETURNED)
    public void onReturnedToGhast(EntityPlayer player) {
        AchievementsCore.trigger(ExampleAchievements.returnGhast, player);
    }

    @EventListener(EventType.ARMOR_EQUIPPED)
    public void onArmorEquipped(EntityPlayer player, ItemStack helmetStack, ItemStack chestStack, ItemStack legsStack, ItemStack bootsStack) {

        if ((helmetStack != null && helmetStack.itemID == Item.helmetIron.itemID) &&
                (chestStack != null && chestStack.itemID == Item.plateIron.itemID) &&
                (legsStack != null && legsStack.itemID == Item.legsIron.itemID) &&
                (bootsStack != null && bootsStack.itemID == Item.bootsIron.itemID))
        {
            AchievementsCore.trigger(ExampleAchievements.equipIronArmor, player);
        }

        if ((helmetStack != null && helmetStack.itemID == Item.helmetGold.itemID) &&
                (chestStack != null && chestStack.itemID == Item.plateGold.itemID) &&
                (legsStack != null && legsStack.itemID == Item.legsGold.itemID) &&
                (bootsStack != null && bootsStack.itemID == Item.bootsGold.itemID))
        {
            AchievementsCore.trigger(ExampleAchievements.equipGoldArmor, player);
        }

        if ((helmetStack != null && helmetStack.itemID == Item.helmetDiamond.itemID) &&
                (chestStack != null && chestStack.itemID == Item.plateDiamond.itemID) &&
                (legsStack != null && legsStack.itemID == Item.legsDiamond.itemID) &&
                (bootsStack != null && bootsStack.itemID == Item.bootsDiamond.itemID))
        {
            AchievementsCore.trigger(ExampleAchievements.equipDiamondArmor, player);
        }
    }
}