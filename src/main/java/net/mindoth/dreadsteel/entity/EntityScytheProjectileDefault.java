package net.mindoth.dreadsteel.entity;

import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.registries.DreadsteelEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class EntityScytheProjectileDefault extends AbstractArrow {

    public EntityScytheProjectileDefault(EntityType<? extends AbstractArrow> type, Level LevelIn) {
        super(type, LevelIn);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get() + 1);
    }

    public EntityScytheProjectileDefault(EntityType<? extends AbstractArrow> type, Level LevelIn, double x, double y, double z,
                             float r, float g, float b) {
        this(type, LevelIn);
        this.setPos(x, y, z);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get() + 1);
    }

    public EntityScytheProjectileDefault(EntityType<? extends AbstractArrow> type, Level LevelIn, LivingEntity shooter, double dmg) {
        super(type, shooter, LevelIn);
        this.setBaseDamage(dmg);
    }

    public EntityScytheProjectileDefault(PlayMessages.SpawnEntity spawnEntity, Level LevelIn) {
        this(DreadsteelEntities.SCYTHE_PROJECTILE_DEFAULT.get(), LevelIn);
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
            this.discard();
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
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if ( entity instanceof LivingEntity && entity != this.getOwner() ) {
            entity.hurt(DamageSource.indirectMagic(this, this.getOwner()).bypassArmor().bypassMagic(), (float)this.getBaseDamage());
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if ( !this.level.isClientSide ) {
            spawnParticles();
            this.playSound(SoundEvents.ILLUSIONER_MIRROR_MOVE, 1, 1);
            this.discard();
        }
    }

    private void spawnParticles() {
        if ( !this.level.isClientSide ) {
            Vec3 center = this.getBoundingBox().getCenter();
            ServerLevel level = (ServerLevel)this.level;
            for ( int i = 0; i < 8; ++i ) {
                level.sendParticles(ParticleTypes.DRAGON_BREATH, center.x, center.y, center.z, 1, 0, 0, 0, 1);
            }
        }
    }

    @Override
    protected float getWaterInertia() {
        return 0.0F;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
