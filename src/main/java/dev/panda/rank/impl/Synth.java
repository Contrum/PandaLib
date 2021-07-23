package dev.panda.rank.impl;

import club.vaxel.core.SynthAPI;
import dev.panda.rank.Rank;

import java.util.UUID;

public class Synth implements Rank {

    @Override
    public String getName(UUID uuid) {
        return SynthAPI.INSTANCE.getPlayerRank(uuid).getName();
    }

    @Override
    public String getPrefix(UUID uuid) {
        return SynthAPI.INSTANCE.getPlayerRank(uuid).getPrefix();
    }

    @Override
    public String getSuffix(UUID uuid) {
        return SynthAPI.INSTANCE.getPlayerRank(uuid).getSuffix();
    }

    @Override
    public String getColor(UUID uuid) {
        return SynthAPI.INSTANCE.getPlayerRank(uuid).getColor()
                + SynthAPI.INSTANCE.getPlayerRank(uuid).getName();
    }
}
