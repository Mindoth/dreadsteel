package net.mindoth.dreadsteel;

import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.message.MessageSwingArm;
import net.mindoth.dreadsteel.registries.DreadsteelEntities;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(Dreadsteel.MOD_ID)
public class Dreadsteel {

    //public static SidedProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static final String MOD_ID = "dreadsteel";
    public static final SimpleChannel NETWORK_WRAPPER;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static int packetsRegistered = 0;

    public Dreadsteel() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            DreadsteelClient.registerHandlers();
        }
        addRegistries(modEventBus);
        modEventBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DreadsteelCommonConfig.SPEC, "dreadsteel-common.toml");
    }

    private void addRegistries(final IEventBus modEventBus) {
        DreadsteelItems.ITEMS.register(modEventBus);
        DreadsteelEntities.ENTITIES.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(DreadsteelItems.DREADSTEEL_HELMET);
            event.accept(DreadsteelItems.DREADSTEEL_CHESTPLATE);
            event.accept(DreadsteelItems.DREADSTEEL_LEGGINGS);
            event.accept(DreadsteelItems.DREADSTEEL_BOOTS);
            event.accept(DreadsteelItems.DREADSTEEL_SCYTHE);
            event.accept(DreadsteelItems.DREADSTEEL_SHIELD);
        }
        if(event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(DreadsteelItems.DREADSTEEL_INGOT);
            event.accept(DreadsteelItems.DEFAULT_KIT);
            event.accept(DreadsteelItems.WHITE_KIT);
            event.accept(DreadsteelItems.BLACK_KIT);
            event.accept(DreadsteelItems.BRONZE_KIT);
        }
    }

    static {
        NetworkRegistry.ChannelBuilder channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("dreadsteel", "main_channel"));
        String version = PROTOCOL_VERSION;
        version.getClass();
        channel = channel.clientAcceptedVersions(version::equals);
        version = PROTOCOL_VERSION;
        version.getClass();
        NETWORK_WRAPPER = channel.serverAcceptedVersions(version::equals).networkProtocolVersion(() -> {
            return PROTOCOL_VERSION;
        }).simpleChannel();
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        NETWORK_WRAPPER.sendToServer(message);
    }

    private void setup(final FMLCommonSetupEvent event) {
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageSwingArm.class, MessageSwingArm::write, MessageSwingArm::read, MessageSwingArm.Handler::handle);
    }
}
