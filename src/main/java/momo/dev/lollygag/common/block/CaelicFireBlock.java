package momo.dev.lollygag.common.block;

import com.mojang.serialization.MapCodec;
import momo.dev.lollygag.registry.LTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CaelicFireBlock extends BaseFireBlock {
    public CaelicFireBlock(Properties builder) {
        super(builder, 1F);
    }

    public MapCodec<SoulFireBlock> codec() {
        return null;
    }

    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, level, currentPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below()));
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(LTags.BLOCKS.CAELIC_FIRE_BASE_BLOCKS);
    }

    protected boolean canBurn(BlockState state) {
        return true;
    }
}
