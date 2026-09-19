// The code here is based on Environmental by TeamAbnormals
// com.teamabnormals.environmental.common.block.DwarfSpruceHeadBlock

package momo.dev.lollygag.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LDwarfSpruceHeadBlock extends LDwarfSpruceBlock {
    private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    public static final BooleanProperty TOP = BooleanProperty.create("top");

    protected LDwarfSprucePlantBlock plantBlock;

    public LDwarfSpruceHeadBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TOP, false));
    }

    public void setPlantBlock(LDwarfSprucePlantBlock block) {
        this.plantBlock = block;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(this.asItem());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState belowstate = level.getBlockState(pos.below());
        boolean flag = belowstate.getBlock() instanceof LDwarfSpruceHeadBlock;

        if (isValidAboveBlock(level.getBlockState(pos.above())))
            return plantBlock.defaultBlockState().setValue(LDwarfSprucePlantBlock.BOTTOM, !flag);
        else if (flag)
            return this.defaultBlockState().setValue(TOP, true);
        else
            return this.defaultBlockState();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
        if (!state.canSurvive(level, pos))
            level.scheduleTick(pos, this, 1);
        return direction == Direction.UP && isValidAboveBlock(offsetState) ? this.getBodyState(state) : state;
    }

    public BlockState getBodyState(BlockState originalState) {
        return this.plantBlock.defaultBlockState().setValue(LDwarfSprucePlantBlock.BOTTOM, !originalState.getValue(TOP));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.above()).isAir();
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos.above(), this.defaultBlockState().setValue(TOP, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOP);
    }
}