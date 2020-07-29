package io.github.boogiemonster1o1.statushud.util;

import io.github.boogiemonster1o1.statushud.StatusHud;
import io.github.boogiemonster1o1.statushud.mixin.BossbarHudMixin;
import io.github.boogiemonster1o1.statushud.mixin.InGameHudAccessMixin;

import java.util.Map;
import java.util.UUID;

import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class RenderUtils {
    private RenderUtils() {
    }

    public static void renderHotbarItem(int i, int j, float tickDelta, PlayerEntity playerEntity, ItemStack itemStack) {
        ((InGameHudAccessMixin) StatusHud.hudInstance).invokeRenderHotbarItem(i, j, tickDelta, playerEntity, itemStack);
    }

    public static Map<UUID, ClientBossBar> getBossBars() {
        return ((BossbarHudMixin) ((InGameHudAccessMixin) StatusHud.hudInstance).getBossBarHud()).getBossBars();
    }
}
