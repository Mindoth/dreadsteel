package net.mindoth.dreadsteel.entity;

import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.registries.DreadsteelEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityScytheProjectileWhite extends AbstractArrowEntity {

    public EntityScytheProjectileWhite(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get() + 1);
    }

    public EntityScytheProjectileWhite(EntityType<? extends AbstractArrowEntity> type, World worldIn, double x, double y, double z,
                                         float r, float g, float b) {
        this(type, worldIn);
        this.setPos(x, y, z);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get() + 1);
    }

    public EntityScytheProjectileWhite(EntityType<? extends AbstractArrowEntity> type, World worldIn, LivingEntity shooter, double dmg) {
        super(type, shooter, worldIn);
        this.setBaseDamage(dmg);
    }

    public EntityScytheProjectileWhite(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
        this(DreadsteelEntities.SCYTHE_PROJECTILE_WHITE.get(), worldIn);
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void tick() {
        super.tick();

        if ( this.tickCount == 80 ) {
            spawnParticles();
        }
        else if ( this.tickCount > 80 ) {
            this.playSound(SoundEvents.ILLUSIONER_MIRROR_MOVE, 1, 1);
            this.remove();
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if ( entity instanceof LivingEntity && entity != this.getOwner() ) {
            entity.hurt(DamageSource.indirectMagic(this, this.getOwner()).bypassArmor().bypassMagic(), (float)this.getBaseDamage());
        }
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult result) {
        if ( !this.level.isClientSide ) {
            spawnParticles();
            this.playSound(SoundEvents.ILLUSIONER_MIRROR_MOVE, 1, 1);
            this.remove();
        }
    }

    private void spawnParticles() {
        if ( !this.level.isClientSide ) {
            Vector3d center = this.getBoundingBox().getCenter();
            ServerWorld level = (ServerWorld)this.level;
            for ( int i = 0; i < 8; ++i ) {
                level.sendParticles(ParticleTypes.FIREWORK, center.x, center.y, center.z, 1, 0, 0, 0, 1);
            }
        }
    }

    @Override
    protected float getWaterInertia() {
        return 0.0F;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
