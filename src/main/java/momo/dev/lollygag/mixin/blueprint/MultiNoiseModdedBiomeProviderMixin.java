package momo.dev.lollygag.mixin.blueprint;

import com.farcr.nomansland.NMLConfig;
import com.farcr.nomansland.common.registry.worldgen.NMLBiomes;
import com.teamabnormals.blueprint.core.registry.BlueprintBiomes;
import com.teamabnormals.blueprint.core.util.BiomeUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Blueprint's multi-noise slices look biomes up in their own parameter list, skipping Biolith,
// so NML's cave biomes (added through Biolith's replacement hook) never generate under them.
// Defer to the original source wherever it would have placed one of NML's cave biomes.
@Mixin(BiomeUtil.MultiNoiseModdedBiomeProvider.class)
public class MultiNoiseModdedBiomeProviderMixin {
    // ModdedBiomeSource calls the ScopedDensityFunctionContext overload; the Climate.Sampler one is unused.
    @Inject(method = "getNoiseBiome(IIILcom/teamabnormals/blueprint/core/util/BiomeUtil$ScopedDensityFunctionContext;Lnet/minecraft/world/level/biome/BiomeSource;Lnet/minecraft/core/Registry;)Lnet/minecraft/core/Holder;", at = @At("RETURN"), cancellable = true)
    private void lollygag$placeNMLCaves(int x, int y, int z, BiomeUtil.ScopedDensityFunctionContext context, BiomeSource originalSource, Registry<Biome> registry, CallbackInfoReturnable<Holder<Biome>> cir) {
        if (!NMLConfig.CAVES_BIOMES.get()) return;

        Holder<Biome> biome = cir.getReturnValue();
        if (biome.is(BlueprintBiomes.ORIGINAL_SOURCE_MARKER) || biome.is(Tags.Biomes.IS_UNDERGROUND)) return;

        Holder<Biome> original = context.getOriginalBiome(x, y, z, originalSource);
        if (original.is(NMLBiomes.CAVES) || original.is(NMLBiomes.CAVE_DEPTHS)) cir.setReturnValue(original);
    }
}
