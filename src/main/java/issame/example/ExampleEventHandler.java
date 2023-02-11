package issame.example;

import btw.item.BTWItems;
import issame.achievements_core.AchievementsCore;
import issame.achievements_core.event.EventListener;
import issame.achievements_core.event.EventType;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;

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
            AchievementsCore.triggerAchievement(player, ExampleAchievements.chiselWood);
        }
		else if (itemstack.itemID == BTWItems.sharpStone.itemID) {
            AchievementsCore.triggerAchievement(player, ExampleAchievements.chiselStone);
        }
	}
	/*
	@EventListener(EventType.COOKED)
	public void onCooked(EntityPlayer player, ItemStack itemstack) {
		if (itemstack.itemID == Item.porkCooked.itemID) {
            player.triggerAchievement(ExampleAchievements.cookPork);
        }
		else if (itemstack.itemID == FCBetterThanWolves.fcItemNuggetIron.itemID) {
            player.triggerAchievement(ExampleAchievements.cookIron);
        }
	}
	
	@EventListener(EventType.BREWED)
	public void onBrewed(EntityPlayer player, ItemStack itemstack) {
		if (itemstack.itemID == Item.potion.itemID && itemstack.getItemDamage() == 8195) {
			player.triggerAchievement(ExampleAchievements.firePotion);
        }
	}
	
	@EventListener(EventType.PICKUP)
	public void onPickup(EntityPlayer player, ItemStack itemstack) {
		if (itemstack.itemID == FCBetterThanWolves.fcItemBark.itemID) {
            player.triggerAchievement(ExampleAchievements.mineBark);
        }
		else if (itemstack.itemID == Item.stick.itemID) {
            player.triggerAchievement(ExampleAchievements.mineStick);
        }
	}
	
	@EventListener(EventType.KILLED)
	public void onKilled(EntityPlayer player, EntityLiving entity) {
		if (entity instanceof EntityPig) {
            player.triggerAchievement(ExampleAchievements.killPig);
        }
	}
	
	@EventListener(EventType.PORTAL)
	public void onTravelledDimension(EntityPlayer player, int dimension) {
		if (dimension == -1) {
            player.triggerAchievement(ExampleAchievements.netherPortal);
        }
	}
	
	@EventListener(EventType.ENTITY_INTERACT)
	public void onEntityInteraction(EntityPlayer player, Entity entity) {
		if (entity instanceof EntityCreeper) {
			Item currentItem = player.getCurrentEquippedItem().getItem();
			if (currentItem instanceof ItemShears) {
				player.triggerAchievement(ExampleAchievements.neuterCreeper);
			}
		}
	}
	
	@EventListener(EventType.TRADED)
	public void onTraded(EntityPlayer player, MerchantRecipe recipe) {
		if (recipe.getItemToBuy().itemID == Item.hoeIron.itemID) {
			player.triggerAchievement(ExampleAchievements.levelUpFarmer);
		}
	}
	
	@EventListener(EventType.CURED)
	public void onCured(EntityPlayer player, FCEntityZombie zombieVillager) {
		player.triggerAchievement(ExampleAchievements.cureVillager);
	}
	
	@EventListener(EventType.CONSUMED)
	public void onConsumed(EntityPlayer player, ItemStack foodStack) {
		if (foodStack.getItem().equals(Item.bucketMilk)) {
			player.triggerAchievement(ExampleAchievements.drinkMilk);
		}
	}
	 */
}