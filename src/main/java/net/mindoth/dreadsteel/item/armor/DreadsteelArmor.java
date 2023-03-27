package net.mindoth.dreadsteel.item.armor;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.client.models.armor.DreadsteelModel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.item.weapon.DreadsteelScythe;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dreadsteel.MOD_ID)
public class DreadsteelArmor extends ArmorItem {

    public static final Map<String, UUID> NAME_UUID_MAP = new HashMap<>();

    public static UUID getUUID(ItemStack stack) {
        return NAME_UUID_MAP.computeIfAbsent(stack.getItem().getRegistryName().toString(), s -> UUID.nameUUIDFromBytes(s.getBytes()));
    }

    @SubscribeEvent
    public static void dreadsteelAttributeEvent(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if (DreadsteelCommonConfig.ARMOR_STATS_CHECK.get()) {
            if (item == DreadsteelItems.DREADSTEEL_HELMET.get() && event.getSlotType() == EquipmentSlotType.HEAD) {
                event.removeAttribute(Attributes.ARMOR);
                event.removeAttribute(Attributes.ARMOR_TOUGHNESS);
                event.removeAttribute(Attributes.KNOCKBACK_RESISTANCE);
                event.addModifier(Attributes.ARMOR, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_armor",
                        DreadsteelCommonConfig.HELMET_ARMOR.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_toughness",
                        DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_knockback_resistance",
                        DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
            }
            if (item == DreadsteelItems.DREADSTEEL_CHESTPLATE.get() && event.getSlotType() == EquipmentSlotType.CHEST) {
                event.removeAttribute(Attributes.ARMOR);
                event.removeAttribute(Attributes.ARMOR_TOUGHNESS);
                event.removeAttribute(Attributes.KNOCKBACK_RESISTANCE);
                event.addModifier(Attributes.ARMOR, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_armor",
                        DreadsteelCommonConfig.CHESTPLATE_ARMOR.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_toughness",
                        DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_knockback_resistance",
                        DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
            }
            if (item == DreadsteelItems.DREADSTEEL_LEGGINGS.get() && event.getSlotType() == EquipmentSlotType.LEGS) {
                event.removeAttribute(Attributes.ARMOR);
                event.removeAttribute(Attributes.ARMOR_TOUGHNESS);
                event.removeAttribute(Attributes.KNOCKBACK_RESISTANCE);
                event.addModifier(Attributes.ARMOR, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_armor",
                        DreadsteelCommonConfig.LEGGINGS_ARMOR.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_toughness",
                        DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_knockback_resistance",
                        DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
            }
            if (item == DreadsteelItems.DREADSTEEL_BOOTS.get() && event.getSlotType() == EquipmentSlotType.FEET) {
                event.removeAttribute(Attributes.ARMOR);
                event.removeAttribute(Attributes.ARMOR_TOUGHNESS);
                event.removeAttribute(Attributes.KNOCKBACK_RESISTANCE);
                event.addModifier(Attributes.ARMOR, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_armor",
                        DreadsteelCommonConfig.BOOTS_ARMOR.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_toughness",
                        DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
                event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(DreadsteelArmor.getUUID(stack), "dreadsteel_knockback_resistance",
                        DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
            }
        }
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }
    private final LazyValue<BipedModel<?>> model;

    public DreadsteelArmor(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
        this.model = DistExecutor.unsafeRunForDist(() -> () -> new LazyValue<>(() -> this.provideArmorModelForSlot(slot)),
                () -> () -> null);
    }

    @SubscribeEvent
    public static void noHat(final RenderPlayerEvent event) {
        PlayerEntity player = event.getPlayer();
        if ( player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == DreadsteelItems.DREADSTEEL_HELMET.get() ) {
            event.getRenderer().getModel().hat.visible = false;
        }
    }

    @SubscribeEvent
    public static void dreadsteelSetDefence(final LivingAttackEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if ( entity.getItemBySlot(EquipmentSlotType.HEAD).getItem() == DreadsteelItems.DREADSTEEL_HELMET.get() &&
                entity.getItemBySlot(EquipmentSlotType.CHEST).getItem() == DreadsteelItems.DREADSTEEL_CHESTPLATE.get() &&
                entity.getItemBySlot(EquipmentSlotType.LEGS).getItem() == DreadsteelItems.DREADSTEEL_LEGGINGS.get() &&
                entity.getItemBySlot(EquipmentSlotType.FEET).getItem() == DreadsteelItems.DREADSTEEL_BOOTS.get() ) {
            if ( event.getSource().getMsgId().equals(DamageSource.LIGHTNING_BOLT.getMsgId()) || event.getSource().getMsgId().equals(DamageSource.IN_FIRE.getMsgId())
                    || event.getSource().getMsgId().equals(DamageSource.ON_FIRE.getMsgId()) || event.getSource().getMsgId().equals(DamageSource.CACTUS.getMsgId()) ) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerUseArmorItem(final PlayerInteractEvent.RightClickItem event) {
        ItemStack headStack = event.getPlayer().getItemBySlot(EquipmentSlotType.HEAD);
        ItemStack chestStack = event.getPlayer().getItemBySlot(EquipmentSlotType.CHEST);
        ItemStack legsStack = event.getPlayer().getItemBySlot(EquipmentSlotType.LEGS);
        ItemStack feetStack = event.getPlayer().getItemBySlot(EquipmentSlotType.FEET);
        ItemStack mainStack = event.getPlayer().getItemBySlot(EquipmentSlotType.MAINHAND);
        ItemStack offStack = event.getPlayer().getItemBySlot(EquipmentSlotType.OFFHAND);
        //White
        if ( event.getItemStack().getItem().equals(DreadsteelItems.WHITE_KIT.get()) ) {
            if ( headStack.getItem().equals(DreadsteelItems.DREADSTEEL_HELMET.get()) ) {
                CompoundNBT tag = headStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                headStack.setTag(tag);
            }
            if ( chestStack.getItem().equals(DreadsteelItems.DREADSTEEL_CHESTPLATE.get()) ) {
                CompoundNBT tag = chestStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                chestStack.setTag(tag);
            }
            if ( legsStack.getItem().equals(DreadsteelItems.DREADSTEEL_LEGGINGS.get()) ) {
                CompoundNBT tag = legsStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                legsStack.setTag(tag);
            }
            if ( feetStack.getItem().equals(DreadsteelItems.DREADSTEEL_BOOTS.get()) ) {
                CompoundNBT tag = feetStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                feetStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundNBT tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundNBT tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                offStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundNBT tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundNBT tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                offStack.setTag(tag);
            }
            if (!event.getPlayer().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getPlayer().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1, 1);
        }
        //Black
        if ( event.getItemStack().getItem().equals(DreadsteelItems.BLACK_KIT.get()) ) {
            if ( headStack.getItem().equals(DreadsteelItems.DREADSTEEL_HELMET.get()) ) {
                CompoundNBT tag = headStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                headStack.setTag(tag);
            }
            if ( chestStack.getItem().equals(DreadsteelItems.DREADSTEEL_CHESTPLATE.get()) ) {
                CompoundNBT tag = chestStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                chestStack.setTag(tag);
            }
            if ( legsStack.getItem().equals(DreadsteelItems.DREADSTEEL_LEGGINGS.get()) ) {
                CompoundNBT tag = legsStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                legsStack.setTag(tag);
            }
            if ( feetStack.getItem().equals(DreadsteelItems.DREADSTEEL_BOOTS.get()) ) {
                CompoundNBT tag = feetStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                feetStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundNBT tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundNBT tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                offStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundNBT tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundNBT tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                offStack.setTag(tag);
            }
            if (!event.getPlayer().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getPlayer().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1, 1);
        }
        //Bronze
        if ( event.getItemStack().getItem().equals(DreadsteelItems.BRONZE_KIT.get()) ) {
            if ( headStack.getItem().equals(DreadsteelItems.DREADSTEEL_HELMET.get()) ) {
                CompoundNBT tag = headStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                headStack.setTag(tag);
            }
            if ( chestStack.getItem().equals(DreadsteelItems.DREADSTEEL_CHESTPLATE.get()) ) {
                CompoundNBT tag = chestStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                chestStack.setTag(tag);
            }
            if ( legsStack.getItem().equals(DreadsteelItems.DREADSTEEL_LEGGINGS.get()) ) {
                CompoundNBT tag = legsStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                legsStack.setTag(tag);
            }
            if ( feetStack.getItem().equals(DreadsteelItems.DREADSTEEL_BOOTS.get()) ) {
                CompoundNBT tag = feetStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                feetStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundNBT tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundNBT tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                offStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundNBT tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundNBT tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                offStack.setTag(tag);
            }
            if (!event.getPlayer().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getPlayer().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1, 1);
        }
        //Default
        if ( event.getItemStack().getItem().equals(DreadsteelItems.DEFAULT_KIT.get()) ) {
            if ( headStack.getItem().equals(DreadsteelItems.DREADSTEEL_HELMET.get()) ) {
                headStack.removeTagKey("CustomModelData");
            }
            if ( chestStack.getItem().equals(DreadsteelItems.DREADSTEEL_CHESTPLATE.get()) ) {
                chestStack.removeTagKey("CustomModelData");
            }
            if ( legsStack.getItem().equals(DreadsteelItems.DREADSTEEL_LEGGINGS.get()) ) {
                legsStack.removeTagKey("CustomModelData");
            }
            if ( feetStack.getItem().equals(DreadsteelItems.DREADSTEEL_BOOTS.get()) ) {
                feetStack.removeTagKey("CustomModelData");
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                mainStack.removeTagKey("CustomModelData");
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                offStack.removeTagKey("CustomModelData");
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                mainStack.removeTagKey("CustomModelData");
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                offStack.removeTagKey("CustomModelData");
            }
            if (!event.getPlayer().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getPlayer().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundCategory.PLAYERS, 1, 1);
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        CompoundNBT tag = stack.getTag();
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
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) model.get();
    }

    @OnlyIn(Dist.CLIENT)
    public BipedModel<?> provideArmorModelForSlot(EquipmentSlotType slot) {
        return new DreadsteelModel(slot);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.dreadsteel.dreadsteel_setbonus"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    public enum MaterialDreadsteel implements IArmorMaterial {

        DREADSTEEL("dreadsteel", 0, new int[] { 9, 12, 15, 10 }, 25, SoundEvents.ARMOR_EQUIP_NETHERITE,
                8, 0.25F, () -> {
            return Ingredient.of(DreadsteelItems.DREADSTEEL_INGOT.get());
        });

        private static final int[] MAX_DAMAGE_ARRAY = new int[] { 9, 12, 15, 10 };
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyValue<Ingredient> repairMaterial;

        MaterialDreadsteel(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getDurabilityForSlot(EquipmentSlotType slotIn) {
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlotType slotIn) {
            return this.damageReductionAmountArray[slotIn.getIndex()];
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
