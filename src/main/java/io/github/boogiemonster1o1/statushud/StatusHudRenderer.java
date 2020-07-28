package io.github.boogiemonster1o1.statushud;

import io.github.boogiemonster1o1.statushud.mixin.InGameHudAccessMixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class StatusHudRenderer {
    public static void renderArmorAtTop(MatrixStack matrixStack, float tickDelta) {
        if (MinecraftClient.getInstance().player != null) {
            int i = MinecraftClient.getInstance().getWindow().getScaledWidth();
            int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
            int p;
            int q;
            int r;
            for(p = 0; p < 4; ++p) {
                q = i - 90 + p * 20 + 2;
                r = j - 16 - 3;
                renderHotbarItem(q, r, tickDelta, MinecraftClient.getInstance().player, MinecraftClient.getInstance().player.inventory.armor.get(p));
            }
        }
    }

    private static void renderHotbarItem(int i, int j, float tickDelta, PlayerEntity playerEntity, ItemStack itemStack) {
        ((InGameHudAccessMixin) StatusHud.hudInstance).invokeRenderHotbarItem(i, j, tickDelta, playerEntity, itemStack);
    }
}
