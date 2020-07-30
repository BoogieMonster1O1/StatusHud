package io.github.boogiemonster1o1.statushud.render;

import io.github.boogiemonster1o1.statushud.StatusHud;
import io.github.boogiemonster1o1.statushud.util.RenderUtils;

import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

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

    public static void renderArmorAtBottomRight(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
            int q;
            int r = j - 16 - 3;;
            for(int p = 0; p < 4; ++p) {
                q = i + 145 + p * 20 + 2;
                RenderUtils.renderHotbarItem(q, r, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(3 - p));
                renderDurabilityAtBottom(matrices, q, r, p);
            }
        }
    }

    public static void renderArmorAtBottomLeft(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
            int q;
            int r = j - 16 - 3;
            for(int p = 0; p < 4; ++p) {
                q = 15 + p * 20 + 2;
                RenderUtils.renderHotbarItem(q, r, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(p));
                renderDurabilityAtBottom(matrices, q, r, p);
            }
        }
    }

    public static void renderArmorVerticalAtRight(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth() - 20;
            int o = 30;
            for(int p = 0; p < 4; ++p, j -= 20) {
                RenderUtils.renderHotbarItem(i, j + o, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(p));
                if (StatusHud.config.renderArmorDurability) {
                    String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(p).getDamage());
                    MinecraftClient.getInstance().textRenderer.draw(matrices, damage, MinecraftClient.getInstance().getWindow().getScaledWidth() - (damage.length() * 5) - 25, j + o + 5, StatusHud.config.durabilityColor);
                }
            }
        }
    }

    public static void renderArmorVerticalAtLeft(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
            int o = 30;
            for(int p = 0; p < 4; ++p, j -= 20) {
                RenderUtils.renderHotbarItem(0, j + o, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(p));
                if (StatusHud.config.renderArmorDurability) {
                    String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(p).getDamage());
                    MinecraftClient.getInstance().textRenderer.draw(matrices, damage,  10, j + o + 5, StatusHud.config.durabilityColor);
                }
            }
        }
    }

    private static void renderDurabilityAtBottom(MatrixStack matrices, int q, int r, int p) {
        if (StatusHud.config.renderArmorDurability && MinecraftClient.getInstance().player != null) {
            String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(p).getDamage());
            MinecraftClient.getInstance().textRenderer.draw(matrices, damage, q, r - 10, StatusHud.config.durabilityColor);
        }
    }
}
