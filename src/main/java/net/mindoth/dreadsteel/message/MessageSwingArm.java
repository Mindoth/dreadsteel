package net.mindoth.dreadsteel.message;

import net.mindoth.dreadsteel.item.weapon.DreadsteelScythe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageSwingArm {

    public MessageSwingArm() {
    }

    public static class Handler {
        public Handler() {
        }

        public static void handle(MessageSwingArm message, Supplier<NetworkEvent.Context> context) {
            context.get().setPacketHandled(true);
            Player player = context.get().getSender();
            if (player != null) {
                DreadsteelScythe.onLeftClick(player, player.getItemInHand(InteractionHand.MAIN_HAND));
            }
        }
    }


    public static MessageSwingArm decode(FriendlyByteBuf buf) {
        return new MessageSwingArm();
    }

    public static void encode(MessageSwingArm message, FriendlyByteBuf buf) {
    }
}
