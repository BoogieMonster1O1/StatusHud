package io.github.boogiemonster1o1.statushud.util;

import java.lang.annotation.Target;

@SuppressWarnings("unused")
@Target({ /* No targets */ })
public @interface Loc {
    enum Armor {
        STATUSHUD_ARMOR_TOP,
        STATUSHUD_ARMOR_LEFT,
        STATUSHUD_ARMOR_RIGHT,
        STATUSHUD_ARMOR_VERTICAL_LEFT,
        STATUSHUD_ARMOR_VERTICAL_RIGHT,
        STATUSHUD_ARMOR_TOP_RIGHT,
        STATUSHUD_ARMOR_TOP_LEFT,
        STATUSHUD_DO_NOT_SHOW,
    }

    enum Effects {
        STATUSHUD_EFFECTS_LEFT,
        STATUSHUD_EFFECTS_RIGHT,
        STATUSHUD_DO_NOT_SHOW,
    }
}
