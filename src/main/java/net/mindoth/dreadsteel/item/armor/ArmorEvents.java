package net.mindoth.dreadsteel.item.armor;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Dreadsteel.MOD_ID)
public class ArmorEvents {

    public static final Map<String, UUID> NAME_UUID_MAP = new HashMap<>();

    public static UUID getUUID(ItemStack stack) {
        return NAME_UUID_MAP.computeIfAbsent(ForgeRegistries.ITEMS.getKey(stack.getItem()).toString(), s -> UUID.nameUUIDFromBytes(s.getBytes()));
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
            if ( event.getSource().is(DamageTypes.LIGHTNING_BOLT) || event.getSource().is(DamageTypes.IN_FIRE)
                    || event.getSource().is(DamageTypes.ON_FIRE) || event.getSource().is(DamageTypes.CACTUS) ) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void dreadsteelAttributeEvent(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if ( item == DreadsteelItems.DREADSTEEL_HELMET.get() && event.getSlotType() == EquipmentSlot.HEAD ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.HELMET_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
        }
        if ( item == DreadsteelItems.DREADSTEEL_CHESTPLATE.get() && event.getSlotType() == EquipmentSlot.CHEST ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.CHESTPLATE_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
        }
        if ( item == DreadsteelItems.DREADSTEEL_LEGGINGS.get() && event.getSlotType() == EquipmentSlot.LEGS ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.LEGGINGS_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
        }
        if ( item == DreadsteelItems.DREADSTEEL_BOOTS.get() && event.getSlotType() == EquipmentSlot.FEET ) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_armor", DreadsteelCommonConfig.BOOTS_ARMOR.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_toughness", DreadsteelCommonConfig.ARMOR_TOUGHNESS.get(), AttributeModifier.Operation.ADDITION));
            event.addModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    ArmorEvents.getUUID(event.getItemStack()), "dreadsteel_knockback_resistance", DreadsteelCommonConfig.ARMOR_KNOCKBACK_RESISTANCE.get(), AttributeModifier.Operation.ADDITION));
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
}
