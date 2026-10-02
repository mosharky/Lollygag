package momo.dev.lollygag.common.block;

import com.aetherteam.aether.AetherTags;
import com.mojang.serialization.MapCodec;
import momo.dev.lollygag.registry.LTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;

// Behaves like regular fire in Aether biomes, and like soul fire everywhere else
public class CaelicFireBlock extends FireBlock {
    public static final MapCodec<CaelicFireBlock> CODEC = simpleCodec(CaelicFireBlock::new);

    public CaelicFireBlock(Properties builder) {
        super(builder);
    }

    @SuppressWarnings("unchecked")
    public MapCodec<FireBlock> codec() {
        return (MapCodec<FireBlock>) (MapCodec<?>) CODEC;
    }

    public static boolean isInAether(LevelReader level, BlockPos pos) {
        return level.getBiome(pos).is(AetherTags.Biomes.IS_AETHER);
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(LTags.BLOCKS.CAELIC_FIRE_BASE_BLOCKS);
    }

    public BlockState getPlacementState(BlockGetter level, BlockPos pos) {
        return this.getStateForPlacement(level, pos);
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below())) || (level.getBiome(pos).is(AetherTags.Biomes.IS_AETHER) && super.canSurvive(state, level, pos));
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Outside the Aether it neither spreads nor burns out, like soul fire
        if (level.getBiome(pos).is(AetherTags.Biomes.IS_AETHER)) {
            super.tick(state, level, pos, random);
        }
    }

    // Flammability is registered on vanilla fire, so read it from there
    @SuppressWarnings("deprecation")
    protected boolean canBurn(BlockState state) {
        return ((FireBlock) Blocks.FIRE).getIgniteOdds(state) > 0;
    }
}
