package momo.dev.lollygag.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.List;

// Connection properties added to every LeavesBlock by LeavesBlockMixin
public final class LeavesConnections {
    public static final List<Direction> DIRECTIONS = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN);

    private LeavesConnections() {
    }

    public static BooleanProperty property(Direction direction) {
        return PipeBlock.PROPERTY_BY_DIRECTION.get(direction);
    }

    public static BlockState withConnections(BlockState state, BlockGetter level, BlockPos pos) {
        for (Direction direction : DIRECTIONS) {
            BlockPos neighborPos = pos.relative(direction);
            state = withConnection(state, direction, level.getBlockState(neighborPos), level, neighborPos);
        }
        return state;
    }

    public static BlockState withConnection(BlockState state, Direction direction, BlockState neighbor, BlockGetter level, BlockPos neighborPos) {
        // The state may not be leaves anymore if a subclass swapped it out
        if (direction == Direction.UP || !state.hasProperty(property(direction))) return state;
        return state.setValue(property(direction), connectsTo(neighbor, level, neighborPos, direction));
    }

    // Same rule as BranchBlock: leaves (whose support shape is empty), or a full face toward these leaves
    private static boolean connectsTo(BlockState neighbor, BlockGetter level, BlockPos neighborPos, Direction direction) {
        return neighbor.is(BlockTags.LEAVES) || neighbor.isFaceSturdy(level, neighborPos, direction.getOpposite());
    }
}
