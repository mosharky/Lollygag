package momo.dev.lollygag.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Leaves that track whether a sturdy block or other leaves is on each horizontal side and below.
// Worldgen leaves get their connections from LevelChunkMixin once their chunk finishes generating.
public class DroopyLeavesBlock extends LeavesBlock {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final List<Direction> DIRECTIONS = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN);

    public DroopyLeavesBlock(Properties properties) {
        // Marks leaves placed during worldgen for post-processing
        super(properties.hasPostProcess((state, level, pos) -> true));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(DOWN, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NORTH, EAST, SOUTH, WEST, DOWN);
    }

    @Override
    @NotNull
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return withConnections(super.getStateForPlacement(ctx), ctx.getLevel(), ctx.getClickedPos());
    }

    @Override
    @NotNull
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return withConnection(super.updateShape(state, direction, neighborState, level, pos, neighborPos), direction, neighborState, level, neighborPos);
    }

    // Trees grown from saplings place their blocks without shape updates, so tick once the whole tree is down
    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!oldState.is(this)) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Vanilla sets the distance here; its helper for that is private, so connections are applied afterward
        super.tick(state, level, pos, random);
        BlockState current = level.getBlockState(pos);
        if (current.is(this)) {
            level.setBlock(pos, withConnections(current, level, pos), 3);
        }
    }

    public static BlockState withConnections(BlockState state, BlockGetter level, BlockPos pos) {
        for (Direction direction : DIRECTIONS) {
            BlockPos neighborPos = pos.relative(direction);
            state = withConnection(state, direction, level.getBlockState(neighborPos), level, neighborPos);
        }
        return state;
    }

    private static BlockState withConnection(BlockState state, Direction direction, BlockState neighbor, BlockGetter level, BlockPos neighborPos) {
        if (direction == Direction.UP) return state;
        return state.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), connectsTo(neighbor, level, neighborPos, direction));
    }

    // Same rule as BranchBlock: leaves (whose support shape is empty), or a full face toward these leaves
    private static boolean connectsTo(BlockState neighbor, BlockGetter level, BlockPos neighborPos, Direction direction) {
        return neighbor.is(BlockTags.LEAVES) || neighbor.isFaceSturdy(level, neighborPos, direction.getOpposite());
    }
}
