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

    @Inject(method="onNeighborBlockChange",
            at = @At("TAIL"))

    private void onCakePowered(World world, int i, int j, int k, int iNeigborBlockID, CallbackInfo ci){
        boolean bOn = ( world.getBlockMetadata( i, j, k ) & 8 ) > 0;
        boolean bReceivingRedstone = world.isBlockGettingPowered( i, j, k );
        EntityPlayer player = world.getClosestPlayer(i,j,k,8);

        if ( canBlockStay( world, i, j, k ))
        {
            if (bOn && bReceivingRedstone )
            {
                if ( player != null )
                {
                    EventDispatcher.onCakePowered(player);
                }
            }
        }
    }
}