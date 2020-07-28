package io.github.boogiemonster1o1.statushud;

import io.github.boogiemonster1o1.statushud.mixin.BossbarHudMixin;
import io.github.boogiemonster1o1.statushud.mixin.InGameHudAccessMixin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class StatusHudRenderer {
    public static void renderArmorAtTop(MatrixStack matrixStack, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
            int p;
            int q;
            AtomicInteger topOffset = new AtomicInteger();
            getBossbars().forEach((uuid, bossbar) -> {
                if(topOffset.get() >= 80) {
                    return;
                }
                topOffset.set(topOffset.get() + 20);
            });
            for(p = 0; p < 4; ++p) {
                q = i - 90 + p * 20 + 2;
                renderHotbarItem(q + 50, topOffset.get(), tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(3 - p));
            }
        }
    }

    private static void renderHotbarItem(int i, int j, float tickDelta, PlayerEntity playerEntity, ItemStack itemStack) {
        ((InGameHudAccessMixin) StatusHud.hudInstance).invokeRenderHotbarItem(i, j, tickDelta, playerEntity, itemStack);
    }

    private static Map<UUID, ClientBossBar> getBossbars() {
        return ((BossbarHudMixin) ((InGameHudAccessMixin) StatusHud.hudInstance).getBossBarHud()).getBossBars();
    }
}
