package net.mindoth.dreadsteel.item.weapon;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = Dreadsteel.MOD_ID)
public class DreadsteelShield extends ShieldItem {

    public DreadsteelShield() {
        super(new Properties().durability(0).tab(ItemGroup.TAB_COMBAT).fireResistant());
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.dreadsteel.dreadsteel_shield"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @SubscribeEvent
    public static void onArrowHit(final LivingAttackEvent event) {
        if ( event.getEntityLiving() instanceof PlayerEntity ) {
            PlayerEntity player = (PlayerEntity)event.getEntityLiving();
            World world = player.level;
            //Check if Shield is blocking
            if ( player.isBlocking() && player.getUseItem().getItem() == DreadsteelItems.DREADSTEEL_SHIELD.get() ) {
                //Disintegrate arrows that would hit you
                if ( event.getSource().getDirectEntity() instanceof AbstractArrowEntity ) {
                    if ( !world.isClientSide ) {
                        Entity attacker = event.getSource().getDirectEntity();
                        //Sound
                        attacker.level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                                SoundEvents.BLAZE_SHOOT, SoundCategory.PLAYERS, 1, 1);
                        attacker.level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                                SoundEvents.IRON_GOLEM_HURT, SoundCategory.PLAYERS, 0.5F, 1);
                        //Particles
                        ServerWorld level = (ServerWorld)world;
                        for (int i = 0; i < 8; ++i) {
                            level.sendParticles(ParticleTypes.FLAME, attacker.getX(), attacker.getY(), attacker.getZ(), 1, 0, 0, 0, 0.5f);
                        }
                        //Discard
                        attacker.remove();
                        event.setCanceled(true);
                    }
                }
                //Set melee attackers on fire
                if ( event.getSource().getDirectEntity() instanceof LivingEntity) {
                    event.getSource().getDirectEntity().setSecondsOnFire(5);
                }
            }
        }
    }
}
