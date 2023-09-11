package issame.achievements_core.mixin;

import btw.block.blocks.CakeBlock;
import btw.client.fx.BTWEffectManager;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CakeBlock.class)
public abstract class CakeBlockMixin extends BlockCake {
    public CakeBlockMixin(int iBlockID ) {
        super(iBlockID);
    }

    @Inject(method = "onNeighborBlockChange(Lnet/minecraft/src/World;IIII)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;playAuxSFX(IIIII)V"))

    private void onCakePowered(World world, int i, int j, int k, int iNeigborBlockID, CallbackInfo ci){
        EntityPlayer player = world.getClosestPlayer(i,j,k,8);

        if ( player != null ) {
            EventDispatcher.onCakePowered(player);
        }
    }
}