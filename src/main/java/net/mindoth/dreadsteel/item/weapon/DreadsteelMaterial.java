package net.mindoth.dreadsteel.item.weapon;

import net.mindoth.dreadsteel.config.DreadsteelCommonConfig;

public class DreadsteelMaterial extends CustomToolMaterial {

    public DreadsteelMaterial(String name, int harvestLevel, int durability, float damage, float speed, int enchantability) {
        super(name, harvestLevel, durability, damage, speed, enchantability);
    }

    /*public int getUses() {
        return DreadsteelCommonConfig.SCYTHE_DURABILITY.get();
    }*/

    public float getAttackDamageBonus() {
        return (float) DreadsteelCommonConfig.SCYTHE_DAMAGE.get() - 4.0F;
    }
}
