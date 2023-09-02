package issame.achievements_core.mixin;

import btw.block.blocks.CampfireBlock;
import btw.block.tileentity.CampfireTileEntity;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin extends BlockContainer {
    protected CampfireBlockMixin(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    @Inject(method = "onBlockActivated(Lnet/minecraft/src/World;IIILnet/minecraft/src/EntityPlayer;IFFF)Z",
            at = @At(value = "INVOKE",
                    target = "Lbtw/block/tileentity/CampfireTileEntity;setCookStack(Lnet/minecraft/src/ItemStack;)V"))
    private void onTakeCampfireCookStack(World world, int i, int j, int k, EntityPlayer player, int iFacing,
                                         float fXClick, float fYClick, float fZClick, CallbackInfoReturnable<Boolean> cir) {
        ItemStack cookStack = ((CampfireTileEntity) world.getBlockTileEntity(i, j, k)).getCookStack();
        if (cookStack != null) {
            EventDispatcher.onCooked(player, cookStack);
        }
    }
}
