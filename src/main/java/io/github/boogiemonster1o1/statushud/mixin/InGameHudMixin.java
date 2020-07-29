package io.github.boogiemonster1o1.statushud.mixin;

import io.github.boogiemonster1o1.statushud.StatusHud;
import io.github.boogiemonster1o1.statushud.util.Loc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    public void injectInit(MinecraftClient client, CallbackInfo ci) {
        StatusHud.hudInstance = (InGameHud) (Object) this;
    }

    @Inject(at = @At("HEAD"), method = "renderStatusEffectOverlay", cancellable = true)
    public void injectRenderStatusEffectOverlay(MatrixStack matrixStack, CallbackInfo ci) {
        if(StatusHud.config.effectsLocation != Loc.Effects.STATUSHUD_EFFECTS_RIGHT || !(StatusHud.config.effectsEnabled)) {
            ci.cancel();
        }
    }
}
