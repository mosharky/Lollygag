package momo.dev.lollygag.registry.worldgen;

import momo.dev.lollygag.Lollygag;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class LConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN = register("aspen");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH = register("birch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR = register("pear");

    public static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Lollygag.loc(name));
    }
}
