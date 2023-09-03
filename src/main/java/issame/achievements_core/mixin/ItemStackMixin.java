package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "onFoodEaten(Lnet/minecraft/src/World;Lnet/minecraft/src/EntityPlayer;)Lnet/minecraft/src/ItemStack;",
            at = @At("HEAD"))
    private void onFoodEaten(World world, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir) {
        EventDispatcher.onEaten(player, (ItemStack) ((Object) this));
    }
}
