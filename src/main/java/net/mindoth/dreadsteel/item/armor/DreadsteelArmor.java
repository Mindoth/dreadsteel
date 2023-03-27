package net.mindoth.dreadsteel.item.armor;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.client.models.armor.DreadsteelModel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.mindoth.dreadsteel.util.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dreadsteel.MOD_ID)
public class DreadsteelArmor extends ArmorItem {

    public static final Map<String, UUID> NAME_UUID_MAP = new HashMap<>();

    public static UUID getUUID(ItemStack stack) {
        return NAME_UUID_MAP.computeIfAbsent(ForgeRegistries.ITEMS.getKey(stack.getItem()).toString(), s -> UUID.nameUUIDFromBytes(s.getBytes()));
    }

    @SubscribeEvent
    public static void dreadsteelAttributeEvent(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if ( item == DreadsteelItems.DREADSTEEL_HELMET.get() && event.getSlotType() == EquipmentSlot.HEAD ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.HELMET_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
        }
        if ( item == DreadsteelItems.DREADSTEEL_CHESTPLATE.get() && event.getSlotType() == EquipmentSlot.CHEST ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.CHESTPLATE_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
        }
        if ( item == DreadsteelItems.DREADSTEEL_LEGGINGS.get() && event.getSlotType() == EquipmentSlot.LEGS ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.LEGGINGS_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
        }
        if ( item == DreadsteelItems.DREADSTEEL_BOOTS.get() && event.getSlotType() == EquipmentSlot.FEET ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.BOOTS_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    DreadsteelArmor.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tooltip.dreadsteel.dreadsteel_setbonus"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {

        consumer.accept(new IClientItemExtensions() {
            static DreadsteelModel model;

            @Override
            public DreadsteelModel getHumanoidArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel _default) {
                if (model == null) model = new DreadsteelModel(Minecraft.getInstance().getEntityModels().bakeLayer(ClientProxy.DREADSTEEL_ARMOR_LAYER));
                float pticks = Minecraft.getInstance().getFrameTime();
                float f = Mth.rotLerp(pticks, entity.yBodyRotO, entity.yBodyRot);
                float f1 = Mth.rotLerp(pticks, entity.yHeadRotO, entity.yHeadRot);
                float netHeadYaw = f1 - f;
                float netHeadPitch = Mth.lerp(pticks, entity.xRotO, entity.getXRot());
                model.slot = slot;
                model.copyFromDefault(_default);
                model.setupAnim(entity, entity.animationPosition, entity.animationSpeed, entity.tickCount + pticks, netHeadYaw, netHeadPitch);
                return model;
            }
        });
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

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    public DreadsteelArmor(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    @SubscribeEvent
    public static void noHat(final RenderPlayerEvent event) {
        Player player = event.getEntity();
        if ( player.getItemBySlot(EquipmentSlot.HEAD).getItem() == DreadsteelItems.DREADSTEEL_HELMET.get() ) {
            event.getRenderer().getModel().hat.visible = false;
        }
    }

    @SubscribeEvent
    public static void dreadsteelSetDefence(final LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        if ( entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == DreadsteelItems.DREADSTEEL_HELMET.get() &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == DreadsteelItems.DREADSTEEL_CHESTPLATE.get() &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == DreadsteelItems.DREADSTEEL_LEGGINGS.get() &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() == DreadsteelItems.DREADSTEEL_BOOTS.get() ) {
            if ( event.getSource().getMsgId().equals(DamageSource.LIGHTNING_BOLT.getMsgId()) || event.getSource().getMsgId().equals(DamageSource.IN_FIRE.getMsgId())
                    || event.getSource().getMsgId().equals(DamageSource.ON_FIRE.getMsgId()) || event.getSource().getMsgId().equals(DamageSource.CACTUS.getMsgId()) ) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerUseArmorItem(final PlayerInteractEvent.RightClickItem event) {
        ItemStack headStack = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestStack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legsStack = event.getEntity().getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feetStack = event.getEntity().getItemBySlot(EquipmentSlot.FEET);
        ItemStack mainStack = event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack offStack = event.getEntity().getItemBySlot(EquipmentSlot.OFFHAND);
        //White
        if ( event.getItemStack().getItem().equals(DreadsteelItems.WHITE_KIT.get()) ) {
            if ( headStack.getItem().equals(DreadsteelItems.DREADSTEEL_HELMET.get()) ) {
                CompoundTag tag = headStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                headStack.setTag(tag);
            }
            if ( chestStack.getItem().equals(DreadsteelItems.DREADSTEEL_CHESTPLATE.get()) ) {
                CompoundTag tag = chestStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                chestStack.setTag(tag);
            }
            if ( legsStack.getItem().equals(DreadsteelItems.DREADSTEEL_LEGGINGS.get()) ) {
                CompoundTag tag = legsStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                legsStack.setTag(tag);
            }
            if ( feetStack.getItem().equals(DreadsteelItems.DREADSTEEL_BOOTS.get()) ) {
                CompoundTag tag = feetStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                feetStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundTag tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundTag tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                offStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundTag tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundTag tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 1);
                offStack.setTag(tag);
            }
            if (!event.getEntity().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getEntity().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1, 1);
        }
        //Black
        if ( event.getItemStack().getItem().equals(DreadsteelItems.BLACK_KIT.get()) ) {
            if ( headStack.getItem().equals(DreadsteelItems.DREADSTEEL_HELMET.get()) ) {
                CompoundTag tag = headStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                headStack.setTag(tag);
            }
            if ( chestStack.getItem().equals(DreadsteelItems.DREADSTEEL_CHESTPLATE.get()) ) {
                CompoundTag tag = chestStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                chestStack.setTag(tag);
            }
            if ( legsStack.getItem().equals(DreadsteelItems.DREADSTEEL_LEGGINGS.get()) ) {
                CompoundTag tag = legsStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                legsStack.setTag(tag);
            }
            if ( feetStack.getItem().equals(DreadsteelItems.DREADSTEEL_BOOTS.get()) ) {
                CompoundTag tag = feetStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                feetStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundTag tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundTag tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                offStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundTag tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundTag tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 2);
                offStack.setTag(tag);
            }
            if (!event.getEntity().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getEntity().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1, 1);
        }
        //Bronze
        if ( event.getItemStack().getItem().equals(DreadsteelItems.BRONZE_KIT.get()) ) {
            if ( headStack.getItem().equals(DreadsteelItems.DREADSTEEL_HELMET.get()) ) {
                CompoundTag tag = headStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                headStack.setTag(tag);
            }
            if ( chestStack.getItem().equals(DreadsteelItems.DREADSTEEL_CHESTPLATE.get()) ) {
                CompoundTag tag = chestStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                chestStack.setTag(tag);
            }
            if ( legsStack.getItem().equals(DreadsteelItems.DREADSTEEL_LEGGINGS.get()) ) {
                CompoundTag tag = legsStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                legsStack.setTag(tag);
            }
            if ( feetStack.getItem().equals(DreadsteelItems.DREADSTEEL_BOOTS.get()) ) {
                CompoundTag tag = feetStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                feetStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundTag tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SCYTHE.get()) ) {
                CompoundTag tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                offStack.setTag(tag);
            }
            if ( mainStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundTag tag = mainStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                mainStack.setTag(tag);
            }
            if ( offStack.getItem().equals(DreadsteelItems.DREADSTEEL_SHIELD.get()) ) {
                CompoundTag tag = offStack.getOrCreateTag();
                tag.putInt("CustomModelData", 3);
                offStack.setTag(tag);
            }
            if (!event.getEntity().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getEntity().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1, 1);
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
            if (!event.getEntity().isCreative()) {
                event.getItemStack().shrink(1);
            }
            event.getEntity().playNotifySound(SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1, 1);
        }
    }
    public enum MaterialDreadsteel implements ArmorMaterial {

        DREADSTEEL("dreadsteel", 0, new int[] { 0, 0, 0, 0 }, 25, SoundEvents.ARMOR_EQUIP_NETHERITE,
                0, 0, () -> {
            return Ingredient.of(DreadsteelItems.DREADSTEEL_INGOT.get());
        });

        private static final int[] MAX_DAMAGE_ARRAY = new int[] { 0, 0, 0, 0 };
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final float knockbackResistance;
        private final Supplier<Ingredient> repairMaterial;

        MaterialDreadsteel(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
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
        public int getDurabilityForSlot(EquipmentSlot slotIn) {
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slotIn) {
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
