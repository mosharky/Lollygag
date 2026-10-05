package momo.dev.lollygag.mixin;

import momo.dev.lollygag.common.block.LeavesConnections;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Gives every leaves block north/east/south/west/down properties for whether a sturdy block or other leaves is on that side.
// Worldgen leaves are handled separately in BlockStateBaseMixin and LevelChunkMixin.
@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block {
    public LeavesBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void lollygag$defaultConnections(BlockBehaviour.Properties properties, CallbackInfo ci) {
        BlockState state = this.defaultBlockState();
        for (Direction direction : LeavesConnections.DIRECTIONS) {
            state = state.setValue(LeavesConnections.property(direction), false);
        }
        this.registerDefaultState(state);
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    private void lollygag$addConnections(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        for (Direction direction : LeavesConnections.DIRECTIONS) {
            builder.add(LeavesConnections.property(direction));
        }
    }

    @Inject(method = "updateShape", at = @At("RETURN"), cancellable = true)
    private void lollygag$updateConnection(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(LeavesConnections.withConnection(cir.getReturnValue(), direction, neighborState, level, neighborPos));
    }

    // Runs on placement and on every leaves tick
    @Inject(method = "updateDistance", at = @At("RETURN"), cancellable = true)
    private static void lollygag$updateConnections(BlockState state, LevelAccessor level, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(LeavesConnections.withConnections(cir.getReturnValue(), level, pos));
    }

    // Trees grown from saplings place their blocks without shape updates, so tick once the whole tree is down
    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!oldState.is(this)) {
            level.scheduleTick(pos, this, 1);
        }
    }
}
