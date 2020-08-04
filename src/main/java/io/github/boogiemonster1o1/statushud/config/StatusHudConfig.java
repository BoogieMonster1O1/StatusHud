package io.github.boogiemonster1o1.statushud.config;

import io.github.boogiemonster1o1.statushud.util.Loc;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "statushud")
public class StatusHudConfig implements ConfigData {
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Loc.Armor armorLocation = Loc.Armor.STATUSHUD_ARMOR_TOP;

    public boolean renderArmorDurability = false;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Loc.Effects effectsLocation = Loc.Effects.STATUSHUD_EFFECTS_LEFT;

    @ConfigEntry.ColorPicker
    public int durabilityColor = 0x000000;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.CollapsibleObject
    public Offsets offsets = new Offsets();

    public static class Offsets {
        public int topArmorOffsetX = 0;
        public int bottomArmorOffsetX = 0;
        public int verticalArmorOffsetY = 0;
        public int effectOffsetX = 0;
    }
}
