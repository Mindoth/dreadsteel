package net.mindoth.dreadsteel.registries;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.item.CosmeticKit;
import net.mindoth.dreadsteel.item.DreadsteelIngot;
import net.mindoth.dreadsteel.item.armor.DreadsteelArmor;
import net.mindoth.dreadsteel.item.weapon.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = Dreadsteel.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
            () -> new CosmeticKit(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> WHITE_KIT = ITEMS.register("kit_white",
            () -> new CosmeticKit(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BLACK_KIT = ITEMS.register("kit_black",
            () -> new CosmeticKit(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BRONZE_KIT = ITEMS.register("kit_bronze",
            () -> new CosmeticKit(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> DREADSTEEL_INGOT = ITEMS.register("dreadsteel_ingot",
            () -> new DreadsteelIngot(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_HELMET = ITEMS.register("dreadsteel_helmet",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlotType.HEAD, itemBuilder().fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_CHESTPLATE = ITEMS.register("dreadsteel_chestplate",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlotType.CHEST, itemBuilder().fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_LEGGINGS = ITEMS.register("dreadsteel_leggings",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlotType.LEGS, itemBuilder().fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_BOOTS = ITEMS.register("dreadsteel_boots",
            () -> new DreadsteelArmor(DreadsteelArmor.MaterialDreadsteel.DREADSTEEL, EquipmentSlotType.FEET, itemBuilder().fireResistant()));

    public static final RegistryObject<Item> DREADSTEEL_SCYTHE = ITEMS.register("dreadsteel_scythe",
            () -> new DreadsteelScythe(DreadsteelTier.DREADSTEEL, -1, 1.6F - 4, new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1).fireResistant()));
    public static final RegistryObject<Item> DREADSTEEL_SHIELD = ITEMS.register("dreadsteel_shield", DreadsteelShield::new);

    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(ItemGroup.TAB_COMBAT);
    }

    /*public static CustomToolMaterial DREADSTEEL_MATERIAL = new DreadsteelMaterial("Dreadsteel", 5, DreadsteelCommonConfig.SCYTHE_DURABILITY.get(),
            (float)DreadsteelCommonConfig.SCYTHE_DAMAGE.get() - 4.0F, 10F, 22);
    public static final Item DREADSTEEL_SCYTHE = new DreadsteelScythe(DREADSTEEL_MATERIAL, "dreadsteel_scythe");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        // Items
        try {
            for (Field f : DreadsteelItems.class.getFields()) {
                Object obj = f.get(null);
                if (obj instanceof Item) {
                    if (((Item) obj).getRegistryName() != null) {
                        event.getRegistry().register((Item) obj);
                    }
                } else if (obj instanceof Item[]) {
                    for (Item item : (Item[]) obj) {
                        if (item.getRegistryName() != null) {
                            event.getRegistry().register(item);
                        }
                    }
                }
            }
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        DreadsteelItems.DREADSTEEL_MATERIAL.setRepairMaterial(Ingredient.of(new ItemStack(DreadsteelItems.DREADSTEEL_INGOT.get())));
    }*/
}
