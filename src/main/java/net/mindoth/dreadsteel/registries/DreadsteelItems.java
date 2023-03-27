package net.mindoth.dreadsteel.registries;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.item.CosmeticKit;
import net.mindoth.dreadsteel.item.DreadsteelIngot;
import net.mindoth.dreadsteel.item.armor.DreadsteelArmor;
import net.mindoth.dreadsteel.item.weapon.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

public class DreadsteelItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dreadsteel.MOD_ID);

    public static final RegistryObject<Item> SCYTHE_PROJECTILE_DEFAULT = ITEMS.register("dreadsteel_scythe_projectile_default",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCYTHE_PROJECTILE_BLACK = ITEMS.register("dreadsteel_scythe_projectile_black",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCYTHE_PROJECTILE_BRONZE = ITEMS.register("dreadsteel_scythe_projectile_bronze",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCYTHE_PROJECTILE_WHITE = ITEMS.register("dreadsteel_scythe_projectile_white",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEFAULT_KIT = ITEMS.register("kit_default",
            () -> new CosmeticKit(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> WHITE_KIT = ITEMS.register("kit_white",
            () -> new CosmeticKit(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> BLACK_KIT = ITEMS.register("kit_black",
            () -> new CosmeticKit(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> BRONZE_KIT = ITEMS.register("kit_bronze",
            () -> new CosmeticKit(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<Item> DREADSTEEL_INGOT = ITEMS.register("dreadsteel_ingot",
            () -> new DreadsteelIngot(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_HELMET = ITEMS.register("dreadsteel_helmet",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlot.HEAD, itemBuilder().fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_CHESTPLATE = ITEMS.register("dreadsteel_chestplate",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlot.CHEST, itemBuilder().fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_LEGGINGS = ITEMS.register("dreadsteel_leggings",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlot.LEGS, itemBuilder().fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_BOOTS = ITEMS.register("dreadsteel_boots",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlot.FEET, itemBuilder().fireResistant()));

    public static final RegistryObject<Item> DREADSTEEL_SCYTHE = ITEMS.register("dreadsteel_scythe",
            () -> new DreadsteelScythe(DreadsteelTier.DREADSTEEL, 0, 0, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_SHIELD = ITEMS.register("dreadsteel_shield", DreadsteelShield::new);

    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(CreativeModeTab.TAB_COMBAT);
    }
}
