package net.mindoth.dreadsteel.registries;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileDefault;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBlack;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBronze;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileWhite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DreadsteelEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Dreadsteel.MOD_ID);

    public static final RegistryObject<EntityType<EntityScytheProjectileDefault>> SCYTHE_PROJECTILE_DEFAULT
            = registerEntity(EntityType.Builder.<EntityScytheProjectileDefault>of(EntityScytheProjectileDefault::new,
            EntityClassification.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileDefault::new), "scythe_projectile_default");

    public static final RegistryObject<EntityType<EntityScytheProjectileBlack>> SCYTHE_PROJECTILE_BLACK
            = registerEntity(EntityType.Builder.<EntityScytheProjectileBlack>of(EntityScytheProjectileBlack::new,
            EntityClassification.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileBlack::new), "scythe_projectile_black");

    public static final RegistryObject<EntityType<EntityScytheProjectileBronze>> SCYTHE_PROJECTILE_BRONZE
            = registerEntity(EntityType.Builder.<EntityScytheProjectileBronze>of(EntityScytheProjectileBronze::new,
            EntityClassification.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileBronze::new), "scythe_projectile_bronze");

    public static final RegistryObject<EntityType<EntityScytheProjectileWhite>> SCYTHE_PROJECTILE_WHITE
            = registerEntity(EntityType.Builder.<EntityScytheProjectileWhite>of(EntityScytheProjectileWhite::new,
            EntityClassification.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileWhite::new), "scythe_projectile_white");

    private static final <T extends Entity> RegistryObject<EntityType<T>> registerEntity(EntityType.Builder<T> builder, String entityName) {
        return ENTITIES.register(entityName, () -> builder.build(entityName));
    }
}
