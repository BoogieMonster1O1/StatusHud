package io.github.boogiemonster1o1.statushud.mixin;

import io.github.boogiemonster1o1.statushud.StatusHud;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At("RETURN"), method = "<init>")
    public void injectInit(MinecraftClient client, CallbackInfo ci) {
        StatusHud.hudInstance = (InGameHud) (Object) this;
    }
}
