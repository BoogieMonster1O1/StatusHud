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

    public static int rainbowColor = 0x000000;
    public static boolean reverse = false;

    public static void renderArmorAtTop(MatrixStack matrices, float tickDelta) {
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
                if (StatusHud.config.renderArmorDurability) {
                    String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(3 - p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(3 - p).getDamage());
                    MinecraftClient.getInstance().textRenderer.draw(matrices, damage, q + 50, topOffset.get() + 20, StatusHud.config.durabilityColor);
                }
            }
        }
        updateColor();
    }

    public static void renderArmorAtTopLeft(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = 2;
            int p;
            int q;
            for(p = 0; p < 4; ++p) {
                q = i - 50 + p * 20 + 2;
                RenderUtils.renderHotbarItem(q + 50, 0, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(3 - p));
                if (StatusHud.config.renderArmorDurability) {
                    String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(3 - p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(3 - p).getDamage());
                    MinecraftClient.getInstance().textRenderer.draw(matrices, damage, q + 50, 20, StatusHud.config.durabilityColor);
                }
            }
        }
        updateColor();
    }

    public static void renderArmorAtTopRight(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth();
            int p;
            int q;
            for(p = 0; p < 4; ++p) {
                q = i - 70 - p * 20 - 2;
                RenderUtils.renderHotbarItem(q + 50, 0, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(p));
                if (StatusHud.config.renderArmorDurability) {
                    String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(p).getDamage());
                    MinecraftClient.getInstance().textRenderer.draw(matrices, damage, q + 50, 20, StatusHud.config.durabilityColor);
                }
            }
        }
        updateColor();
    }

    public static void renderArmorAtBottomRight(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
            int q;
            int r = j - 16 - 3;;
            for(int p = 0; p < 4; ++p) {
                q = i + 145 + p * 20 + 2;
                RenderUtils.renderHotbarItem(q, r, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(p));
                renderDurabilityAtBottom(matrices, q, r, p);
            }
        }
        updateColor();
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
        updateColor();
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
        updateColor();
    }

    public static void renderArmorVerticalAtLeft(MatrixStack matrices, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
            int o = 30;
            for(int p = 0; p < 4; ++p, j -= 20) {
                RenderUtils.renderHotbarItem(0, j + o, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(p));
                if (StatusHud.config.renderArmorDurability) {
                    String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(p).getDamage());
                    MinecraftClient.getInstance().textRenderer.draw(matrices, damage,  22, j + o + 5, StatusHud.config.durabilityColor);
                }
            }
        }
        updateColor();
    }

    private static void renderDurabilityAtBottom(MatrixStack matrices, int q, int r, int p) {
        if (StatusHud.config.renderArmorDurability && MinecraftClient.getInstance().player != null) {
            String damage = String.valueOf(MinecraftClient.getInstance().player.inventory.armor.get(p).getMaxDamage() - MinecraftClient.getInstance().player.inventory.armor.get(p).getDamage());
            MinecraftClient.getInstance().textRenderer.draw(matrices, damage, q, r - 10, StatusHud.config.durabilityColor);
        }
        updateColor();
    }

    private static void updateColor() {
        if(rainbowColor == 0xFFFFFF) {
            reverse = true;
        }
        else if(rainbowColor == 0x000000) {
            reverse = false;
        }

        if (reverse) {
            rainbowColor--;
        }
        else {
            rainbowColor++;
        }
    }
}
