package net.mindoth.dreadsteel;

import net.mindoth.dreadsteel.client.ModelHandler;
import net.mindoth.dreadsteel.entity.renderer.RenderScytheProjectileDefault;
import net.mindoth.dreadsteel.entity.renderer.RenderScytheProjectileBlack;
import net.mindoth.dreadsteel.entity.renderer.RenderScytheProjectileBronze;
import net.mindoth.dreadsteel.entity.renderer.RenderScytheProjectileWhite;
import net.mindoth.dreadsteel.registries.DreadsteelEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class DreadsteelClient {
    public static void registerHandlers() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(DreadsteelClient::clientSetup);
    }
    private static void clientSetup(final FMLClientSetupEvent event) {
        ModelHandler.addCustomItemProperties();
        EntityRenderers.register(DreadsteelEntities.SCYTHE_PROJECTILE_DEFAULT.get(), RenderScytheProjectileDefault::new);
        EntityRenderers.register(DreadsteelEntities.SCYTHE_PROJECTILE_BLACK.get(), RenderScytheProjectileBlack::new);
        EntityRenderers.register(DreadsteelEntities.SCYTHE_PROJECTILE_BRONZE.get(), RenderScytheProjectileBronze::new);
        EntityRenderers.register(DreadsteelEntities.SCYTHE_PROJECTILE_WHITE.get(), RenderScytheProjectileWhite::new);
    }
}
