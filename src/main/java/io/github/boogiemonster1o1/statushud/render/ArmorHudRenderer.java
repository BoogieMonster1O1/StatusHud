package io.github.boogiemonster1o1.statushud.render;

import io.github.boogiemonster1o1.statushud.util.RenderUtils;

import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.client.MinecraftClient;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class ArmorHudRenderer {
    public static void renderArmorAtTop(float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
            int p;
            int q;
            AtomicInteger topOffset = new AtomicInteger();
            RenderUtils.getBossBars().forEach((uuid, bossbar) -> {
                if(topOffset.get() >= 80) {
                    return;
                }
                topOffset.set(topOffset.get() + 20);
            });
            for(p = 0; p < 4; ++p) {
                q = i - 90 + p * 20 + 2;
                RenderUtils.renderHotbarItem(q + 50, topOffset.get(), tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(3 - p));
            }
        }
    }

    public static void renderArmorAtBottomRight(float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
            int q;
            int r = j - 16 - 3;;
            for(int p = 0; p < 4; ++p) {
                q = i + 145 + p * 20 + 2;
                RenderUtils.renderHotbarItem(q, r, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(3 - p));
            }
        }
    }

    public static void renderArmorAtBottomLeft(float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
            int q;
            int r = j - 16 - 3;
            for(int p = 0; p < 4; ++p) {
                q = 15 + p * 20 + 2;
                RenderUtils.renderHotbarItem(q, r, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(3 - p));
            }
        }
    }
}
