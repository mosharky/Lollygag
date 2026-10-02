package momo.dev.lollygag.common.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Function;

// Based on Bountiful Fares' StrippedFruitLogBlock
public class BranchBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty BRANCHES_ONLY = BooleanProperty.create("branches_only");

    protected static final VoxelShape BASE_SHAPE = Block.box(4.0, 4.0, 4.0, 12.0, 12.0, 12.0);
    protected static final Map<Direction, VoxelShape> ARM_SHAPES = Map.of(
            Direction.NORTH, Block.box(4.0, 4.0, 0.0, 12.0, 12.0, 4.0),
            Direction.EAST, Block.box(12.0, 4.0, 4.0, 16.0, 12.0, 12.0),
            Direction.SOUTH, Block.box(4.0, 4.0, 12.0, 12.0, 12.0, 16.0),
            Direction.WEST, Block.box(0.0, 4.0, 4.0, 4.0, 12.0, 12.0),
            Direction.UP, Block.box(4.0, 12.0, 4.0, 12.0, 16.0, 12.0),
            Direction.DOWN, Block.box(4.0, 0.0, 4.0, 12.0, 4.0, 12.0)
    );

    private final Map<BlockState, VoxelShape> shapes;

    public BranchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(BRANCHES_ONLY, false)
        );
        this.shapes = this.stateDefinition.getPossibleStates().stream()
                .collect(ImmutableMap.toImmutableMap(Function.identity(), BranchBlock::makeShape));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED, NORTH, EAST, SOUTH, WEST, UP, DOWN, BRANCHES_ONLY);
    }

    // The core extends along its axis unless only one end is connected and nothing else is
    private static VoxelShape makeShape(BlockState state) {
        Direction.Axis axis = state.getValue(AXIS);
        boolean positive = isConnected(state, Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE));
        boolean negative = isConnected(state, Direction.fromAxisAndDirection(axis, Direction.AxisDirection.NEGATIVE));
        boolean sidesConnected = false;
        for (Direction direction : Direction.values()) {
            if (direction.getAxis() != axis && isConnected(state, direction)) {
                sidesConnected = true;
            }
        }

        VoxelShape shape = BASE_SHAPE;
        if ((!positive && !negative) || (positive ^ negative && !sidesConnected)) {
            shape = Shapes.or(shape,
                    ARM_SHAPES.get(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE)),
                    ARM_SHAPES.get(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.NEGATIVE)));
        }
        for (Direction direction : Direction.values()) {
            if (isConnected(state, direction)) {
                shape = Shapes.or(shape, ARM_SHAPES.get(direction));
            }
        }
        return shape;
    }

    private static boolean isConnected(BlockState state, Direction direction) {
        return state.getValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction));
    }

    @Override
    @NotNull
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shapes.get(state);
    }

    @Override
    @NotNull
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos pos = ctx.getClickedPos();
        BlockState state = super.getStateForPlacement(ctx)
                .setValue(WATERLOGGED, ctx.getLevel().getFluidState(pos).getType() == Fluids.WATER)
                .setValue(BRANCHES_ONLY, ctx.isSecondaryUseActive());  // if sneaking
        for (Direction direction : Direction.values()) {
            state = state.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), connectsTo(state, ctx.getLevel(), pos.relative(direction), direction));
        }
        return state;
    }

    @Override
    @NotNull
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return state.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), connectsTo(state, level, neighborPos, direction));
    }

    @Override
    @NotNull
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    private static boolean connectsTo(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        BlockState neighbor = level.getBlockState(pos);
        if (neighbor.getBlock() instanceof BranchBlock) return true;
        if (state.getValue(BRANCHES_ONLY)) return false;

        // Leaves have an empty support shape, so they never count as sturdy
        // Otherwise, same check fences and walls use: the neighbor's face toward this block must be full
        return neighbor.is(BlockTags.LEAVES) || neighbor.isFaceSturdy(level, pos, direction.getOpposite());
    }

    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
}
