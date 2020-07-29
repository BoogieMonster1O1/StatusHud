package io.github.boogiemonster1o1.statushud.render;

import io.github.boogiemonster1o1.statushud.StatusHud;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.MathHelper;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class EffectsHudRenderer {
    public static void renderEffects(MatrixStack stack) {
        if(MinecraftClient.getInstance().player != null) {
            Collection<StatusEffectInstance> collection = MinecraftClient.getInstance().player.getStatusEffects();
            if (!collection.isEmpty()) {
                RenderSystem.enableBlend();
                int i = 0;
                int j = 0;
                StatusEffectSpriteManager statusEffectSpriteManager = MinecraftClient.getInstance().getStatusEffectSpriteManager();
                List<Runnable> list = Lists.newArrayListWithExpectedSize(collection.size());
                MinecraftClient.getInstance().getTextureManager().bindTexture(HandledScreen.BACKGROUND_TEXTURE);

                for (StatusEffectInstance statusEffectInstance : Ordering.natural().reverse().sortedCopy(collection)) {
                    StatusEffect statusEffect = statusEffectInstance.getEffectType();
                    if (statusEffectInstance.shouldShowIcon()) {
                        int k = 5;
                        int l = 1;
                        if (MinecraftClient.getInstance().isDemo()) {
                            l += 15;
                        }

                        if (statusEffect.isBeneficial()) {
                            ++i;
                            k += 25 * i;
                        } else {
                            ++j;
                            k += 25 * j;
                            l += 26;
                        }

                        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                        float f = 1.0F;
                        if (statusEffectInstance.isAmbient()) {
                            StatusHud.hudInstance.drawTexture(stack, k - 28, l, 165, 166, 24, 24);
                        } else {
                            StatusHud.hudInstance.drawTexture(stack, k - 28, l, 141, 166, 24, 24);
                            if (statusEffectInstance.getDuration() <= 200) {
                                int m = 10 - statusEffectInstance.getDuration() / 20;
                                f = MathHelper.clamp((float) statusEffectInstance.getDuration() / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + MathHelper.cos((float) statusEffectInstance.getDuration() * 3.1415927F / 5.0F) * MathHelper.clamp((float) m / 10.0F * 0.25F, 0.0F, 0.25F);
                            }
                        }

                        Sprite sprite = statusEffectSpriteManager.getSprite(statusEffect);
                        int finalK = k;
                        float finalF = f;
                        int finalL = l;
                        list.add(() -> {
                            MinecraftClient.getInstance().getTextureManager().bindTexture(sprite.getAtlas().getId());
                            RenderSystem.color4f(1.0F, 1.0F, 1.0F, finalF);
                            DrawableHelper.drawSprite(stack, finalK + 3 - 28, finalL + 3, StatusHud.hudInstance.getZOffset(), 18, 18, sprite);
                        });
                    }
                }

                list.forEach(Runnable::run);
            }
        }
    }
}
