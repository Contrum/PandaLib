package dev.panda.rank;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface Rank {

    String getName(UUID uuid);
    String getPrefix(UUID uuid);
    String getSuffix(UUID uuid);
    String getColor(UUID uuid);
    String getRealName(Player player);
}
