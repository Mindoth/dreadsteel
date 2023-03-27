package net.mindoth.dreadsteel.client;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class ModelHandler {

    public static void addCustomItemProperties() {
        makeShield(DreadsteelItems.DREADSTEEL_SHIELD.get());
    }

    public static void makeShield(Item item) {
        addShieldPropertyOverrides(new ResourceLocation(Dreadsteel.MOD_ID, "blocking"),
                (stack, world, entity) -> entity != null && entity.isUsingItem()
                        && entity.getUseItem() == stack ? 1.0F : 0.0F, DreadsteelItems.DREADSTEEL_SHIELD.get());
    }

    private static void addShieldPropertyOverrides(ResourceLocation override, IItemPropertyGetter propertyGetter, IItemProvider... shields) {
        for (IItemProvider shield : shields) {
            ItemModelsProperties.register(shield.asItem(), override, propertyGetter);
        }
    }
}
