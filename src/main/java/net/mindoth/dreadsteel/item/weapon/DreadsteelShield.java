package net.mindoth.dreadsteel.item.weapon;

import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
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
        super(new Properties().durability(0).tab(CreativeModeTab.TAB_COMBAT).fireResistant());
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    /*@Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }*/

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.dreadsteel.dreadsteel_shield"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @SubscribeEvent
    public static void onArrowHit(final LivingAttackEvent event) {
        if ( event.getEntityLiving() instanceof Player) {
            Player player = (Player)event.getEntityLiving();
            Level world = player.level;
            //Check if Shield is blocking
            if ( player.isBlocking() && player.getUseItem().getItem() == DreadsteelItems.DREADSTEEL_SHIELD.get() ) {
                //Disintegrate arrows that would hit you
                if ( event.getSource().getDirectEntity() instanceof AbstractArrow) {
                    if ( !world.isClientSide ) {
                        Entity attacker = event.getSource().getDirectEntity();
                        //Sound
                        attacker.level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                                SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1, 1);
                        attacker.level.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                                SoundEvents.IRON_GOLEM_HURT, SoundSource.PLAYERS, 0.5F, 1);
                        //Particles
                        ServerLevel level = (ServerLevel)world;
                        for (int i = 0; i < 8; ++i) {
                            level.sendParticles(ParticleTypes.FLAME, attacker.getX(), attacker.getY(), attacker.getZ(), 1, 0, 0, 0, 0.5f);
                        }
                        //Discard
                        attacker.discard();
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
