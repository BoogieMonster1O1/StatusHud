package io.github.boogiemonster1o1.statushud;

import io.github.boogiemonster1o1.statushud.config.StatusHudConfig;
import io.github.boogiemonster1o1.statushud.render.ArmorHudRenderer;
import io.github.boogiemonster1o1.statushud.render.EffectsHudRenderer;
import io.github.boogiemonster1o1.statushud.util.Loc;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;

import net.minecraft.client.gui.hud.InGameHud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

@Environment(EnvType.CLIENT)
public class StatusHud implements ClientModInitializer {
    public static final String MOD_ID = "statushud";

    public static InGameHud hudInstance;
    public static StatusHudConfig config;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(StatusHudConfig.class, JanksonConfigSerializer::new);

        StatusHud.config = AutoConfig.getConfigHolder(StatusHudConfig.class).getConfig();

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            if(config.armorEnabled) {
                if(config.armorLocation == Loc.Armor.TOP) {
                    ArmorHudRenderer.renderArmorAtTop(tickDelta);
                } else if(config.armorLocation == Loc.Armor.RIGHT) {
                    ArmorHudRenderer.renderArmorAtBottomRight(tickDelta);
                } else if(config.armorLocation == Loc.Armor.LEFT) {
                    ArmorHudRenderer.renderArmorAtBottomLeft(tickDelta);
                }
            }
            if(config.effectsEnabled) {
                if(config.effectsLocation == Loc.Effects.LEFT) {
                    EffectsHudRenderer.renderEffects(matrixStack, tickDelta);
                }
            }
        });
    }
}
