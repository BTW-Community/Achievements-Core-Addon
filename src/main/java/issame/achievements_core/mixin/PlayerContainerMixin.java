package issame.achievements_core.mixin;

import btw.inventory.container.PlayerContainer;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.ContainerPlayer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerContainer.class)
    public abstract class PlayerContainerMixin extends ContainerPlayer {
    public PlayerContainerMixin(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player) {
        super(inventoryPlayer, isLocalWorld, player);
    }

    @Inject(method = "transferStackInSlot",
            at = @At(value = "INVOKE",
            target = "Lbtw/inventory/container/PlayerContainer;mergeItemStack(Lnet/minecraft/src/ItemStack;IIZ)Z",
            ordinal = 3,
            shift = At.Shift.AFTER,
            by = 1))
    private void onArmorShiftEquipped(EntityPlayer player, int slotClicked, CallbackInfoReturnable<ItemStack> cir){
        //System.out.println("ARMOR SHIFTED: CHECKING ACHIEVEMENTS");

        EventDispatcher.onArmorEquipped(player,
                player.inventory.armorInventory[3],
                player.inventory.armorInventory[2],
                player.inventory.armorInventory[1],
                player.inventory.armorInventory[0]);
    }
}
