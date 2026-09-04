package momo.dev.lollygag.registry.worldgen;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class LTreeGrowers {
    public static final TreeGrower ASPEN = new TreeGrower("aspen", 0f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(LConfiguredFeatures.ASPEN),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    public static final TreeGrower BIRCH = new TreeGrower("birch", 0f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(LConfiguredFeatures.BIRCH),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    public static final TreeGrower PEAR = new TreeGrower("pear", 0f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(LConfiguredFeatures.PEAR),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
}
