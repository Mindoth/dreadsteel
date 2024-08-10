package net.mindoth.dreadsteel.item.armor;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.item.CosmeticKit;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
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
    public static void onAnvilDyeEvent(final AnvilUpdateEvent event) {
        ItemStack leftStack = event.getLeft();
        Item rightItem = event.getRight().getItem();
        if ( isDyeableDreadsteelItem(leftStack.getItem()) ) {
            ItemStack result = leftStack.copy();
            CompoundTag newTag = result.getOrCreateTag();
            if ( rightItem instanceof CosmeticKit) {
                if ( rightItem == DreadsteelItems.DEFAULT_KIT.get() ) newTag.remove("CustomModelData");
                else if ( rightItem == DreadsteelItems.WHITE_KIT.get() ) newTag.putInt("CustomModelData", 1);
                else if ( rightItem == DreadsteelItems.BLACK_KIT.get() ) newTag.putInt("CustomModelData", 2);
                else if ( rightItem == DreadsteelItems.BRONZE_KIT.get() ) newTag.putInt("CustomModelData", 3);
                handleCustomAnvil(event, leftStack, result, newTag);
            }
        }
    }

    private static void handleCustomAnvil(AnvilUpdateEvent event, ItemStack leftStack, ItemStack result, CompoundTag tag) {
        result.setTag(tag);
        int xpCost = 1;
        if ( event.getName() != null && !Util.isBlank(event.getName()) ) {
            if ( !event.getName().equals(leftStack.getHoverName().getString()) ) {
                result.setHoverName(Component.literal(event.getName()));
                xpCost += 1;
            }
        }
        else if ( leftStack.hasCustomHoverName() ) result.resetHoverName();
        event.setMaterialCost(1);
        event.setOutput(result);
        event.setCost(xpCost);
    }

    private static boolean isDyeableDreadsteelItem(Item item) {
        return (item instanceof ArmorItem armorItem && armorItem.getMaterial().equals(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL)) || item == DreadsteelItems.DREADSTEEL_SCYTHE.get();
    }
}
