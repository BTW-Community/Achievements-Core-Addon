package issame.achievements_core.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiAchievements;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.IPlayerUsage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements Runnable, IPlayerUsage {
    @ModifyVariable(method = "displayGuiScreen(Lnet/minecraft/src/GuiScreen;)V", at = @At("HEAD"))
    private GuiScreen displayGuiScreen(GuiScreen screen) {
        if (screen instanceof GuiAchievements) {
            screen = new issame.achievements_core.achievements.GuiAchievements();
        }
        return screen;
    }
}
