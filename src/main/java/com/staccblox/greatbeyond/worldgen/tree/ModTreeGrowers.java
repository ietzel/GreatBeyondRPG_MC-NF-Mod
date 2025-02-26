package com.staccblox.greatbeyond.worldgen.tree;

import com.staccblox.greatbeyond.GreatBeyond;
import com.staccblox.greatbeyond.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD = new TreeGrower(GreatBeyond.MOD_ID + ":bloodwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLOODWOOD_KEY), Optional.empty());

}
