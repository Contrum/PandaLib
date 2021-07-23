package dev.panda.rank.impl;

import dev.panda.rank.Rank;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.UUID;

public class LuckPerms implements Rank {

    private final net.luckperms.api.LuckPerms luckPerms;

    public LuckPerms() {
        this.luckPerms = Bukkit.getServicesManager().load(net.luckperms.api.LuckPerms.class);
    }

    @Override
    public String getName(UUID uuid) {
        return getMetaData(uuid).getPrimaryGroup();
    }

    @Override
    public String getPrefix(UUID uuid) {
        return getMetaData(uuid).getPrefix() == null ? "" : getMetaData(uuid).getPrefix();
    }

    @Override
    public String getSuffix(UUID uuid) {
        return getMetaData(uuid).getSuffix() == null ? "" : getMetaData(uuid).getSuffix();
    }

    @Override
    public String getColor(UUID uuid) {
        return getMetaData(uuid).getPrimaryGroup();
    }

    private CachedMetaData getMetaData(UUID uuid) {
        User user = this.luckPerms.getUserManager().getUser(uuid);

        if (user == null) {
            throw new IllegalArgumentException("LuckPerms user could not be found");
        }

        Optional<QueryOptions> queryOptions = this.luckPerms.getContextManager().getQueryOptions(user);

        if (!queryOptions.isPresent()) {
            throw new IllegalArgumentException("LuckPerms context could not be loaded");
        }

        return user.getCachedData().getMetaData(queryOptions.get());
    }
}
