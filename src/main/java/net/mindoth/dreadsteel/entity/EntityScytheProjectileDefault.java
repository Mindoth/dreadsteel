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
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityScytheProjectileDefault extends AbstractArrowEntity {

    public EntityScytheProjectileDefault(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get());
    }

    public EntityScytheProjectileDefault(EntityType<? extends AbstractArrowEntity> type, World worldIn, double x, double y, double z,
                             float r, float g, float b) {
        this(type, worldIn);
        this.setPos(x, y, z);
        this.setBaseDamage(DreadsteelCommonConfig.SCYTHE_DAMAGE.get());
    }

    public EntityScytheProjectileDefault(EntityType<? extends AbstractArrowEntity> type, World worldIn, LivingEntity shooter, double dmg) {
        super(type, shooter, worldIn);
        this.setBaseDamage(dmg);
    }

    public EntityScytheProjectileDefault(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
        this(DreadsteelEntities.SCYTHE_PROJECTILE_DEFAULT.get(), worldIn);
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
    protected float getWaterInertia() {
        return 0.0F;
    }

    @Override
    public void tick() {
        super.tick();
/*
        double motionX = getDeltaMovement().x();
        double motionY = getDeltaMovement().y();
        double motionZ = getDeltaMovement().z();
*/
        if ( this.tickCount > 5 )
        {
            //Particles
            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY() + 0.5f, this.getZ(), (this.random.nextDouble() - 0.5D) * 1.5D, -this.random.nextDouble() + 1, (this.random.nextDouble() - 0.5D) * 1.5D);
            }
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.ILLUSIONER_MIRROR_MOVE, SoundCategory.PLAYERS, 1, 1);
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
        //Particles
        for (int i = 0; i < 8; ++i) {
            this.level.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY() + 0.5f, this.getZ(), (this.random.nextDouble() - 0.5D) * 1.5D, -this.random.nextDouble() + 1, (this.random.nextDouble() - 0.5D) * 1.5D);
        }
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.ILLUSIONER_MIRROR_MOVE, SoundCategory.PLAYERS, 1, 1);
        this.remove();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
