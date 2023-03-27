package net.mindoth.dreadsteel.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface SidedProxy {
    Player getPlayer();
    Level getLevel();
    void init();

    void openCodexGui();
}
