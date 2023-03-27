package net.mindoth.dreadsteel.entity;

import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.registries.DreadsteelEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class EntityScytheProjectileBronze extends AbstractArrow {

    public EntityScytheProjectileBronze(EntityType<? extends AbstractArrow> type, Level LevelIn) {
        super(type, LevelIn);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get());
    }

    public EntityScytheProjectileBronze(EntityType<? extends AbstractArrow> type, Level LevelIn, double x, double y, double z,
                                         float r, float g, float b) {
        this(type, LevelIn);
        this.setPos(x, y, z);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get());
    }

    public EntityScytheProjectileBronze(EntityType<? extends AbstractArrow> type, Level LevelIn, LivingEntity shooter, double dmg) {
        super(type, shooter, LevelIn);
        this.setBaseDamage(dmg);
    }

    public EntityScytheProjectileBronze(PlayMessages.SpawnEntity spawnEntity, Level LevelIn) {
        this(DreadsteelEntities.SCYTHE_PROJECTILE_BRONZE.get(), LevelIn);
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

        if ( this.tickCount > 5 ) {
            spawnParticles();
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
    protected void onHit(HitResult result) {
        Entity thrower = getOwner();
        if (level.isClientSide || result.getType() == HitResult.Type.ENTITY && ((EntityHitResult) result).getEntity() == thrower) {
            return;
        }

        if (result.getType() == HitResult.Type.ENTITY && ((EntityHitResult) result).getEntity() instanceof Mob entity) {
            if (entity != this.getOwner()) {
                entity.hurt(DamageSource.indirectMagic(this, this.getOwner()).bypassArmor().bypassMagic(), (float)this.getBaseDamage());
            }
        }

        if (result.getType() == HitResult.Type.BLOCK) {
            spawnParticles();
            this.playSound(SoundEvents.ILLUSIONER_MIRROR_MOVE, 1, 1);
            this.discard();
        }
    }

    private void spawnParticles() {
        //Particles
        for (int i = 0; i < 8; ++i) {
            this.level.addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + 0.5f, this.getZ(), (this.random.nextDouble() - 0.5D) * 1.5D, -this.random.nextDouble() + 1, (this.random.nextDouble() - 0.5D) * 1.5D);
        }
    }

    @Override
    protected float getWaterInertia() {
        return 0.0F;
    }

    @Override
    public Packet getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
