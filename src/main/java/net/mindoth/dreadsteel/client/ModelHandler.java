package net.mindoth.dreadsteel.client;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class ModelHandler {

    public static void addCustomItemProperties() {
        makeShield(DreadsteelItems.DREADSTEEL_SHIELD.get());
    }

    public static void makeShield(Item item) {
        addShieldPropertyOverrides(new ResourceLocation(Dreadsteel.MOD_ID, "blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem()
                        && entity.getUseItem() == stack ? 1.0F : 0.0F, DreadsteelItems.DREADSTEEL_SHIELD.get());
    }

    private static void addShieldPropertyOverrides(ResourceLocation override, ClampedItemPropertyFunction propertyGetter, ItemLike... shields) {
        for (ItemLike shield : shields) {
            ItemProperties.register(shield.asItem(), override, propertyGetter);
        }
    }
}
