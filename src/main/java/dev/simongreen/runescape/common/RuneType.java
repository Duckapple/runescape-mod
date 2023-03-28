package dev.simongreen.runescape.common;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public enum RuneType implements StringIdentifiable {

    AIR("air"),
    FIRE("fire"),
    WATER("water"),
    EARTH("earth"),
    MIND("mind"),
    BODY("body"),
    COSMIC("cosmic"),
    CHAOS("chaos"),
    NATURE("nature"),
    LAW("law"),
    DEATH("death"),
    ASTRAL("astral"),
    BLOOD("blood"),
    SOUL("soul"),
    WRATH("wrath"),
    ;

    private final String ident;

    RuneType(String ident) {
        this.ident = ident;
    }

    @Override
    public String asString() {
        return ident;
    }

    public static final EnumProperty<RuneType> RUNE_TYPE = EnumProperty.of("rune_type", RuneType.class);
}
