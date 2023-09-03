package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.Block;
import net.minecraft.src.ItemInWorldManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemInWorldManager.class)
public abstract class ItemInWorldManagerMixin {
    @Inject(method = "survivalTryHarvestBlock(IIII)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/src/Block;convertBlock(Lnet/minecraft/src/ItemStack;Lnet/minecraft/src/World;IIII)Z",
                    shift = At.Shift.AFTER))
    private void survivalTryHarvestBlock(int i, int j, int k, int fromSide, CallbackInfoReturnable<Boolean> cir) {
        ItemInWorldManager iiwm = (ItemInWorldManager) ((Object) this);
        Block block = Block.blocksList[iiwm.theWorld.getBlockId(i, j, k)];
        int metadata = iiwm.theWorld.getBlockMetadata(i, j, k);
        EventDispatcher.onBlockConverted(iiwm.thisPlayerMP, block, metadata);
    }
}
