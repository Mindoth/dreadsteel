package net.mindoth.dreadsteel.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class DreadsteelIngot extends Item {
    public DreadsteelIngot(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("tooltip.dreadsteel.dreadsteel_ingot"));
        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}
