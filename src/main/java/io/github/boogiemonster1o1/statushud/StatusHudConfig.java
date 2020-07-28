package io.github.boogiemonster1o1.statushud;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "statushud")
public class StatusHudConfig implements ConfigData {
    public Loc.ArmorLoc armorLocation = Loc.ArmorLoc.TOP;
    public boolean armorEnabled = true;
}
