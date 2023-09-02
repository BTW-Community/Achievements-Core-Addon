package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.src.SlotBrewingStandPotion")
public abstract class SlotBrewingStandPotionMixin {
    @Inject(method = "onPickupFromSlot(Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/ItemStack;)V", at = @At("HEAD"))
    private void onPickupFromSlot(EntityPlayer player, ItemStack itemStack, CallbackInfo ci) {
        EventDispatcher.onBrewed(player, itemStack);
    }
}
