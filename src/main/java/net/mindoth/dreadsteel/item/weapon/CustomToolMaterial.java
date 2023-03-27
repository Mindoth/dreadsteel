package net.mindoth.dreadsteel.item.weapon;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public class CustomToolMaterial implements IItemTier {
    private String name;
    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    //private final LazyValue<Ingredient> repairIngredient = null;
    private Ingredient ingredient = null;

    CustomToolMaterial(String name, int level, int uses, float speed, float damage, int enchantmentValue/*, Supplier<Ingredient> repairIngredient*/) {
        this.name = name;
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        //this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public String getName() {
        return this.name;
    }

    public int getUses() {
        return uses;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAttackDamageBonus() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        //return repairIngredient.get();
        return this.ingredient == null ? Ingredient.EMPTY : this.ingredient;
    }

    public void setRepairMaterial(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
