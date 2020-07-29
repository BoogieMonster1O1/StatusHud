package io.github.boogiemonster1o1.statushud.config;

import io.github.boogiemonster1o1.statushud.util.Loc;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "statushud")
public class StatusHudConfig implements ConfigData {
    public boolean armorEnabled = true;
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Loc.Armor armorLocation = Loc.Armor.TOP;
}
