package net.mindoth.dreadsteel.item.armor;

import net.mindoth.dreadsteel.DreadsteelClient;
import net.mindoth.dreadsteel.client.models.armor.DreadsteelModel;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class DreadsteelArmor extends ArmorItem {

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tooltip.dreadsteel.dreadsteel_setbonus"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    private final LazyLoadedValue<HumanoidModel<?>> model;

    public DreadsteelArmor(ArmorMaterial material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties.fireResistant());
        this.model = DistExecutor.unsafeRunForDist(() -> () -> new LazyLoadedValue<>(() -> this.provideArmorModelForSlot(type)),
                () -> () -> null);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        CompoundTag tag = stack.getTag();
        String color = null;
        if ( tag != null ) {
            if ( tag.getInt("CustomModelData") == 1 ) {
                color = "dreadsteel:textures/item/dreadsteel_armor_model_white.png";
            }
            if ( tag.getInt("CustomModelData") == 2 ) {
                color = "dreadsteel:textures/item/dreadsteel_armor_model_black.png";
            }
            if ( tag.getInt("CustomModelData") == 3 ) {
                color = "dreadsteel:textures/item/dreadsteel_armor_model_bronze.png";
            }
            if ( tag.getInt("CustomModelData") == 0 ) {
                color = "dreadsteel:textures/item/dreadsteel_armor_model_default.png";
            }
        }
        else color = "dreadsteel:textures/item/dreadsteel_armor_model_default.png";
        return color;
    }

    @OnlyIn(Dist.CLIENT)
    public HumanoidModel<?> provideArmorModelForSlot(ArmorItem.Type type) {
        return new DreadsteelModel(Minecraft.getInstance().getEntityModels().bakeLayer(DreadsteelClient.DREADSTEEL_ARMOR), type);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                return model.get();
            }
        });
    }

    public enum MaterialDreadsteel implements ArmorMaterial {

        DREADSTEEL("dreadsteel", 0, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 0);
            map.put(ArmorItem.Type.LEGGINGS, 0);
            map.put(ArmorItem.Type.CHESTPLATE, 0);
            map.put(ArmorItem.Type.HELMET, 0);
        }), 25, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
            return Ingredient.of(DreadsteelItems.DREADSTEEL_INGOT.get());
        });

        private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 13);
            map.put(ArmorItem.Type.LEGGINGS, 15);
            map.put(ArmorItem.Type.CHESTPLATE, 16);
            map.put(ArmorItem.Type.HELMET, 11);
        });
        private final String name;
        private final int maxDamageFactor;
        private final EnumMap<ArmorItem.Type, Integer> damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final Supplier<Ingredient> repairMaterial;

        MaterialDreadsteel(String name, int maxDamageFactor, EnumMap<ArmorItem.Type, Integer> damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = repairMaterial;
        }

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.maxDamageFactor;
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return this.damageReductionAmountArray.get(type);
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getEquipSound() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairMaterial.get();
        }

        @OnlyIn(Dist.CLIENT)
        public String getName() {
            return this.name;
        }

        public float getToughness() {
            return this.toughness;
        }

        /**
         * Gets the percentage of knockback resistance provided by armor of the material.
         */
        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }
    }
}
