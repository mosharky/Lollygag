// The code here is based on Environmental by TeamAbnormals
// com.teamabnormals.environmental.common.block.DwarfSprucePlantBlock

package momo.dev.lollygag.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LDwarfSprucePlantBlock extends LDwarfSpruceBlock {
    private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");

    private final LDwarfSpruceHeadBlock headBlock;

    public LDwarfSprucePlantBlock(Properties properties, LDwarfSpruceHeadBlock headBlock) {
        super(properties);
        this.headBlock = headBlock;
        this.headBlock.setBodyBlock(this);
        this.registerDefaultState(this.stateDefinition.any().setValue(BOTTOM, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
        if (!state.canSurvive(level, pos))
            level.scheduleTick(pos, this, 1);
        return direction == Direction.UP && !isValidAboveBlock(offsetState) ? this.getHeadState(state) : state;
    }

    public BlockState getHeadState(BlockState originalState) {
        return this.headBlock.defaultBlockState().setValue(LDwarfSpruceHeadBlock.TOP, !originalState.getValue(BOTTOM));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        BlockPos headpos = getHeadPos(level, pos);
        return headpos != null && level.getBlockState(headpos.above()).isAir();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos headpos = getHeadPos(level, pos);
        if (headpos != null) {
            BlockState headstate = level.getBlockState(headpos);
            ((LDwarfSpruceHeadBlock) headstate.getBlock()).performBonemeal(level, random, headpos, headstate);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BOTTOM);
    }
}
