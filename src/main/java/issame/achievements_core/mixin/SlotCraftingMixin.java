package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlotCrafting.class)
public abstract class SlotCraftingMixin extends Slot {
    @Shadow
    private EntityPlayer thePlayer;

    public SlotCraftingMixin(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Inject(method = "onCrafting(Lnet/minecraft/src/ItemStack;)V", at = @At("TAIL"))
    private void onCrafting(ItemStack itemStack, CallbackInfo ci) {
        EventDispatcher.onCrafted(thePlayer, itemStack);
        System.out.println(itemStack.toString());
    }
}
