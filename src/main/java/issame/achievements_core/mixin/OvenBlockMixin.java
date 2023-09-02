package issame.achievements_core.mixin;

import btw.block.blocks.FurnaceBlock;
import btw.block.blocks.OvenBlock;
import btw.block.tileentity.OvenTileEntity;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OvenBlock.class)
public abstract class OvenBlockMixin extends FurnaceBlock {
    public OvenBlockMixin(int iBlockID, boolean bIsLit) {
        super(iBlockID, bIsLit);
    }

    @Inject(method = "onBlockActivated(Lnet/minecraft/src/World;IIILnet/minecraft/src/EntityPlayer;IFFF)Z",
            at = @At(value = "INVOKE",
                    target = "Lbtw/block/tileentity/OvenTileEntity;givePlayerCookStack(Lnet/minecraft/src/EntityPlayer;I)V"))
    private void onTakeOvenCookStack(World world, int i, int j, int k, EntityPlayer player, int iFacing,
                                         float fXClick, float fYClick, float fZClick, CallbackInfoReturnable<Boolean> cir) {
        ItemStack cookStack = ((OvenTileEntity) world.getBlockTileEntity(i, j, k)).getCookStack();
        if (cookStack != null) {
            EventDispatcher.onCooked(player, cookStack);
        }
    }
}
