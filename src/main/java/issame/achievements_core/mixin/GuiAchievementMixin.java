package issame.achievements_core.mixin;

import net.minecraft.src.Achievement;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiAchievement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiAchievement.class)
public abstract class GuiAchievementMixin extends Gui {
    @Inject(method = "queueAchievementInformation(Lnet/minecraft/src/Achievement;)V", at = @At("HEAD"), cancellable = true)
    private void noOpenInventory(Achievement achievement, CallbackInfo ci) {
        ci.cancel();
    }
}
