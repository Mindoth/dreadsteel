package net.mindoth.dreadsteel.item.weapon;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBlack;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBronze;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileWhite;
import net.mindoth.dreadsteel.message.MessageSwingArm;
import net.mindoth.dreadsteel.registries.DreadsteelEntities;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileDefault;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Dreadsteel.MOD_ID)
public class DreadsteelScythe extends SwordItem {

    @SubscribeEvent
    public static void dreadsteelScytheAttributeEvent(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if (DreadsteelCommonConfig.SCYTHE_STATS_CHECK.get()) {
            if (item == DreadsteelItems.DREADSTEEL_SCYTHE.get() && event.getSlotType() == EquipmentSlotType.MAINHAND) {
                event.removeAttribute(Attributes.ATTACK_DAMAGE);
                event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", DreadsteelCommonConfig.SCYTHE_DAMAGE.get() - 1, AttributeModifier.Operation.ADDITION));
                event.removeAttribute(Attributes.ATTACK_SPEED);
                event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", 1.6 - 4, AttributeModifier.Operation.ADDITION));
            }
        }
    }

    public DreadsteelScythe(DreadsteelTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    /*private final CustomToolMaterial toolMaterial;

    public DreadsteelScythe(CustomToolMaterial toolmaterial, String name) {
        super(toolmaterial, 3, -2.4F, new Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1).fireResistant());
        this.toolMaterial = toolmaterial;
        this.setRegistryName(Dreadsteel.MOD_ID, name);
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND && this.toolMaterial instanceof DreadsteelMaterial ? this.bakeDreadsteel() : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    private Multimap<Attribute, AttributeModifier> dreadsteelModifiers;

    @Override
    public int getMaxDamage(ItemStack stack) {
        return toolMaterial.getUses();
    }

    private Multimap<Attribute, AttributeModifier> bakeDreadsteel() {
        if (toolMaterial.getAttackDamageBonus() != DreadsteelCommonConfig.SCYTHE_DAMAGE.get() || dreadsteelModifiers == null) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> map = ImmutableMultimap.builder();
            map.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", DreadsteelCommonConfig.SCYTHE_DAMAGE.get() - 1F, AttributeModifier.Operation.ADDITION));
            map.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.4, AttributeModifier.Operation.ADDITION));
            this.dreadsteelModifiers = map.build();
            return this.dreadsteelModifiers;
        }
        else {
            return dreadsteelModifiers;
        }
    }

    public float getAttackDamage() {
        return this.toolMaterial instanceof DreadsteelMaterial ? (float) DreadsteelCommonConfig.SCYTHE_DAMAGE.get() : super.getDamage();
    }*/

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.dreadsteel.dreadsteel_scythe"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        onLeftClick(event.getPlayer(), event.getItemStack());
        if (event.getWorld().isClientSide) {
            Dreadsteel.sendMSGToServer(new MessageSwingArm());
        }
    }

    public static void onLeftClick(final PlayerEntity playerEntity, final ItemStack stack) {
        if ( stack.getItem() == DreadsteelItems.DREADSTEEL_SCYTHE.get() ) {
            DreadsteelScythe.spawnProjectile(stack, playerEntity);
        }
    }

    public static void spawnProjectile(ItemStack stack, PlayerEntity player) {
        CompoundNBT tag = stack.getOrCreateTag();
        if ( player.swingTime > 0.5F ) {
            return;
        }
        if ( player.getItemInHand(Hand.MAIN_HAND) == stack ) {
            final Multimap<Attribute, AttributeModifier> dmg = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
            double totalDmg = 0D;
            for (AttributeModifier modifier : dmg.get(Attributes.ATTACK_DAMAGE)) {
                totalDmg += modifier.getAmount();
            }
            if ( tag.contains("CustomModelData") ) {
                if ( tag.getInt("CustomModelData") == 1 ) {
                    EntityScytheProjectileWhite shot = new EntityScytheProjectileWhite(DreadsteelEntities.SCYTHE_PROJECTILE_WHITE.get(), player.level, player, totalDmg);
                    Vector3d vector3d = player.getLookAngle();
                    Vector3f vector3f = new Vector3f(vector3d);
                    shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 2.0F, 0.5F);
                    player.level.addFreshEntity(shot);
                }
                if ( tag.getInt("CustomModelData") == 2 ) {
                    EntityScytheProjectileBlack shot = new EntityScytheProjectileBlack(DreadsteelEntities.SCYTHE_PROJECTILE_BLACK.get(), player.level, player, totalDmg);
                    Vector3d vector3d = player.getLookAngle();
                    Vector3f vector3f = new Vector3f(vector3d);
                    shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 2.0F, 0.5F);
                    player.level.addFreshEntity(shot);
                }
                if ( tag.getInt("CustomModelData") == 3 ) {
                    EntityScytheProjectileBronze shot = new EntityScytheProjectileBronze(DreadsteelEntities.SCYTHE_PROJECTILE_BRONZE.get(), player.level, player, totalDmg);
                    Vector3d vector3d = player.getLookAngle();
                    Vector3f vector3f = new Vector3f(vector3d);
                    shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 2.0F, 0.5F);
                    player.level.addFreshEntity(shot);
                }
            }
            else {
                EntityScytheProjectileDefault shot = new EntityScytheProjectileDefault(DreadsteelEntities.SCYTHE_PROJECTILE_DEFAULT.get(), player.level, player, totalDmg);
                Vector3d vector3d = player.getLookAngle();
                Vector3f vector3f = new Vector3f(vector3d);
                shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 2.0F, 0.5F);
                player.level.addFreshEntity(shot);
            }
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TRIDENT_THROW, SoundCategory.PLAYERS, 0.75f, 0.75f);
        }
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }
}
