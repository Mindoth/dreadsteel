package net.mindoth.dreadsteel.item.weapon;

import com.google.common.collect.Multimap;
import com.mojang.math.Vector3f;
import net.mindoth.dreadsteel.Dreadsteel;
import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBlack;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBronze;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileWhite;
import net.mindoth.dreadsteel.message.MessageSwingArm;
import net.mindoth.dreadsteel.registries.DreadsteelEntities;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileDefault;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
    static final Map<Item, Map<Attribute, AttributeModifier>> WEAPON_ATTRIBUTE_MODIFIERS = new HashMap<>();
    private static final String ATTACK_DAMAGE_MODIFIER_NAME = new ResourceLocation(Dreadsteel.MOD_ID, "dreadsteel_attack").toString();
    private static final String ATTACK_SPEED_MODIFIER_NAME = new ResourceLocation(Dreadsteel.MOD_ID, "dreadsteel_speed").toString();

    public DreadsteelScythe(DreadsteelTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @SubscribeEvent
    public static void dreadsteelScytheAttributeEvent(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        if ( item == DreadsteelItems.DREADSTEEL_SCYTHE.get() && event.getSlotType() == EquipmentSlot.MAINHAND ) {
            findAndRemoveVanillaModifier(event, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID);
            event.addModifier(Attributes.ATTACK_DAMAGE, getAttackDamage(item, DreadsteelCommonConfig.SCYTHE_DAMAGE.get()));
            findAndRemoveVanillaModifier(event, Attributes.ATTACK_SPEED, BASE_ATTACK_SPEED_UUID);
            event.addModifier(Attributes.ATTACK_SPEED, getAttackSpeed(item, (float)(DreadsteelCommonConfig.SCYTHE_SPEED.get() - 4)));
        }
    }

    private static void findAndRemoveVanillaModifier(ItemAttributeModifierEvent event, Attribute attribute, UUID baseUUID) {
        event.getOriginalModifiers()
                .get(attribute)
                .stream()
                .filter(modifier -> modifier.getId() == baseUUID) // we don't use "equals" because vanilla enforces a direct memory address comparison
                .findAny()
                .ifPresent(modifier -> event.removeModifier(attribute, modifier));
    }

    private static AttributeModifier getAttackDamage(Item item, double defaultValue) {
        return getModifier(item, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID, ATTACK_DAMAGE_MODIFIER_NAME, defaultValue, AttributeModifier.Operation.ADDITION);
    }

    private static AttributeModifier getAttackSpeed(Item item, double defaultValue) {
        return getModifier(item, Attributes.ATTACK_SPEED, BASE_ATTACK_SPEED_UUID, ATTACK_SPEED_MODIFIER_NAME, defaultValue, AttributeModifier.Operation.ADDITION);
    }

    private static AttributeModifier getModifier(Item item, Attribute attribute, UUID uuid, String modifierName, double defaultValue, AttributeModifier.Operation operation) {
        return WEAPON_ATTRIBUTE_MODIFIERS
                .computeIfAbsent(item, k -> new HashMap<>())
                .computeIfAbsent(attribute, k ->
                        new AttributeModifier(uuid, modifierName, defaultValue, operation)
                );
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.dreadsteel.dreadsteel_scythe"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        onLeftClick(event.getPlayer(), event.getItemStack());
        if (event.getWorld().isClientSide) {
            Dreadsteel.sendMSGToServer(new MessageSwingArm());
        }
    }

    public static void onLeftClick(final Player playerEntity, final ItemStack stack) {
        if ( stack.getItem() == DreadsteelItems.DREADSTEEL_SCYTHE.get() ) {
            DreadsteelScythe.spawnProjectile(stack, playerEntity);
        }
    }

    public static void spawnProjectile(ItemStack stack, Player player) {
        CompoundTag tag = stack.getOrCreateTag();
        if ( player.swingTime > 0.5F ) {
            return;
        }
        if ( player.getItemInHand(InteractionHand.MAIN_HAND) == stack ) {
            final Multimap<Attribute, AttributeModifier> dmg = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);
            double totalDmg = 0D;
            for (AttributeModifier modifier : dmg.get(Attributes.ATTACK_DAMAGE)) {
                totalDmg += modifier.getAmount();
            }
            if ( tag.contains("CustomModelData") ) {
                if ( tag.getInt("CustomModelData") == 1 ) {
                    EntityScytheProjectileWhite shot = new EntityScytheProjectileWhite(DreadsteelEntities.SCYTHE_PROJECTILE_WHITE.get(), player.level, player, totalDmg);
                    Vec3 vector3d = player.getLookAngle();
                    Vector3f vector3f = new Vector3f(vector3d);
                    shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 1.0F, 0.0F);
                    player.level.addFreshEntity(shot);
                }
                if ( tag.getInt("CustomModelData") == 2 ) {
                    EntityScytheProjectileBlack shot = new EntityScytheProjectileBlack(DreadsteelEntities.SCYTHE_PROJECTILE_BLACK.get(), player.level, player, totalDmg);
                    Vec3 vector3d = player.getLookAngle();
                    Vector3f vector3f = new Vector3f(vector3d);
                    shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 1.0F, 0.0F);
                    player.level.addFreshEntity(shot);
                }
                if ( tag.getInt("CustomModelData") == 3 ) {
                    EntityScytheProjectileBronze shot = new EntityScytheProjectileBronze(DreadsteelEntities.SCYTHE_PROJECTILE_BRONZE.get(), player.level, player, totalDmg);
                    Vec3 vector3d = player.getLookAngle();
                    Vector3f vector3f = new Vector3f(vector3d);
                    shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 1.0F, 0.0F);
                    player.level.addFreshEntity(shot);
                }
            }
            else {
                EntityScytheProjectileDefault shot = new EntityScytheProjectileDefault(DreadsteelEntities.SCYTHE_PROJECTILE_DEFAULT.get(), player.level, player, totalDmg);
                Vec3 vector3d = player.getLookAngle();
                Vector3f vector3f = new Vector3f(vector3d);
                shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 1.0F, 0.0F);
                player.level.addFreshEntity(shot);
            }
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 0.75f, 0.75f);
        }
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return true;
    }
}
