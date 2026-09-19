// The code here is based on Environmental by TeamAbnormals
// com.teamabnormals.environmental.common.block.DwarfSpruceBlock

package momo.dev.lollygag.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public abstract class LDwarfSpruceBlock extends BushBlock implements BonemealableBlock {
    protected LDwarfSpruceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos))
            level.destroyBlock(pos, true);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowpos = pos.below();
        BlockState belowstate = level.getBlockState(belowpos);
        return belowstate.getBlock() instanceof LDwarfSpruceBlock || canSupportCenter(level, belowpos, Direction.UP) || belowstate.getBlock() instanceof LeavesBlock;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    protected static BlockPos getHeadPos(BlockGetter level, BlockPos pos) {
        BlockPos.MutableBlockPos mutable = pos.mutable();
        while (true) {
            mutable.move(Direction.UP);
            if (level.getBlockState(mutable).getBlock() instanceof LDwarfSpruceHeadBlock)
                return mutable.immutable();
            else if (!(level.getBlockState(mutable).getBlock() instanceof LDwarfSprucePlantBlock))
                return null;
        }
    }

    protected static boolean isValidAboveBlock(BlockState state) {
        return state.getBlock() instanceof LDwarfSpruceHeadBlock && state.getValue(LDwarfSpruceHeadBlock.TOP) || state.getBlock() instanceof LDwarfSprucePlantBlock && !state.getValue(LDwarfSprucePlantBlock.BOTTOM);
    }
}
