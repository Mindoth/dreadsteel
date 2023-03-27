package net.mindoth.dreadsteel.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DreadsteelCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ARMOR_STATS_CHECK;
    public static final ForgeConfigSpec.ConfigValue<Integer> HELMET_ARMOR;
    public static final ForgeConfigSpec.ConfigValue<Integer> CHESTPLATE_ARMOR;
    public static final ForgeConfigSpec.ConfigValue<Integer> LEGGINGS_ARMOR;
    public static final ForgeConfigSpec.ConfigValue<Integer> BOOTS_ARMOR;
    public static final ForgeConfigSpec.ConfigValue<Integer> ARMOR_TOUGHNESS;
    public static final ForgeConfigSpec.ConfigValue<Double> ARMOR_KNOCKBACK_RESISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SCYTHE_STATS_CHECK;
    public static final ForgeConfigSpec.ConfigValue<Integer> SCYTHE_DAMAGE;

    static {
        BUILDER.push("Configs for Dreadsteel");

        ARMOR_STATS_CHECK = BUILDER.comment("Can the Dreadsteel Armor's stats be modified with this config? (Default = true)")
                .define("Armor stats check", true);

        HELMET_ARMOR = BUILDER.comment("Dreadsteel helmet's armor value (Default = 10)")
                .define("Helmet armor", 10);

        CHESTPLATE_ARMOR = BUILDER.comment("Dreadsteel chestplate's armor value (Default = 15)")
                .define("Chestplate armor", 15);

        LEGGINGS_ARMOR = BUILDER.comment("Dreadsteel leggings's armor value (Default = 12)")
                .define("Leggings armor", 12);

        BOOTS_ARMOR = BUILDER.comment("Dreadsteel boots' armor value (Default = 9)")
                .define("Boots armor", 9);

        ARMOR_TOUGHNESS = BUILDER.comment("Dreadsteel armor piece toughness (Default = 8)")
                .define("Armor toughness", 8);

        ARMOR_KNOCKBACK_RESISTANCE = BUILDER.comment("Dreadsteel armor piece knockback resistance (Default = 0.25)")
                .define("Armor knockback resistance", 0.25D);

        SCYTHE_STATS_CHECK = BUILDER.comment("Can the Dreadsteel Scythe's attack damage (and Projectile's damage) be modified with this config? (Default = true)")
                .define("Scythe stats check", true);

        SCYTHE_DAMAGE = BUILDER.comment("Dreadsteel Scythe's attack damage (also Projectile's damage) (Default = 50)")
                .define("Scythe attack damage", 50);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
