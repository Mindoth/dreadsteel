package net.mindoth.dreadsteel.item.weapon;

import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum DreadsteelTier implements Tier {

    DREADSTEEL(7F, 22, 4, 9.0F, 0,
            () -> Ingredient.of(DreadsteelItems.DREADSTEEL_INGOT.get()));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repaierMaterial;

    DreadsteelTier(float damage, int enchantmentValue, int level, float speed, int uses, Supplier<Ingredient> repaierMaterial) {
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.level = level;
        this.speed = speed;
        this.uses = uses;
        this.repaierMaterial = repaierMaterial;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repaierMaterial.get();
    }
}
