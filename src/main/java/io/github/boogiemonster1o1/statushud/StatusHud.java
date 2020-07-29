package io.github.boogiemonster1o1.statushud;

import io.github.boogiemonster1o1.statushud.config.StatusHudConfig;
import io.github.boogiemonster1o1.statushud.render.StatusHudRenderer;
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

    @Override
    public void onInitializeClient() {
        AutoConfig.register(StatusHudConfig.class, JanksonConfigSerializer::new);

        StatusHudConfig config = AutoConfig.getConfigHolder(StatusHudConfig.class).getConfig();

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            if(config.armorEnabled) {
                if(config.armorLocation == Loc.Armor.TOP) {
                    StatusHudRenderer.renderArmorAtTop(tickDelta);
                } else if(config.armorLocation == Loc.Armor.RIGHT) {
                    StatusHudRenderer.renderArmorAtBottomRight(tickDelta);
                } else if(config.armorLocation == Loc.Armor.LEFT) {
                    StatusHudRenderer.renderArmorAtBottomLeft(tickDelta);
                }
            }
        });
    }
}
