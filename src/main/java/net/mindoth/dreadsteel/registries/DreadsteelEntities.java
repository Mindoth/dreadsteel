package net.mindoth.dreadsteel.registries;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileDefault;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBlack;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBronze;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileWhite;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DreadsteelEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Dreadsteel.MOD_ID);

    public static final RegistryObject<EntityType<EntityScytheProjectileDefault>> SCYTHE_PROJECTILE_DEFAULT
            = registerEntity(EntityType.Builder.<EntityScytheProjectileDefault>of(EntityScytheProjectileDefault::new,
            MobCategory.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileDefault::new), "scythe_projectile_default");

    public static final RegistryObject<EntityType<EntityScytheProjectileBlack>> SCYTHE_PROJECTILE_BLACK
            = registerEntity(EntityType.Builder.<EntityScytheProjectileBlack>of(EntityScytheProjectileBlack::new,
            MobCategory.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileBlack::new), "scythe_projectile_black");

    public static final RegistryObject<EntityType<EntityScytheProjectileBronze>> SCYTHE_PROJECTILE_BRONZE
            = registerEntity(EntityType.Builder.<EntityScytheProjectileBronze>of(EntityScytheProjectileBronze::new,
            MobCategory.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileBronze::new), "scythe_projectile_bronze");

    public static final RegistryObject<EntityType<EntityScytheProjectileWhite>> SCYTHE_PROJECTILE_WHITE
            = registerEntity(EntityType.Builder.<EntityScytheProjectileWhite>of(EntityScytheProjectileWhite::new,
            MobCategory.MISC).sized(0.75F, 1.0F).setCustomClientFactory(EntityScytheProjectileWhite::new), "scythe_projectile_white");

    private static final <T extends Entity> RegistryObject<EntityType<T>> registerEntity(EntityType.Builder<T> builder, String entityName) {
        return ENTITIES.register(entityName, () -> builder.build(entityName));
    }
}
