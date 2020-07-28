package io.github.boogiemonster1o1.statushud.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public interface InGameHudAccessMixin {
    @Invoker
    void invokeRenderHotbarItem(int i, int j, float f, PlayerEntity playerEntity, ItemStack itemStack);

    @Accessor("bossBarHud")
    BossBarHud getBossBarHud();
}
