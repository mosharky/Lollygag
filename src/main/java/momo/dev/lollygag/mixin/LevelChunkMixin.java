package momo.dev.lollygag.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import momo.dev.lollygag.common.block.LeavesConnections;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// Leaves are only post-processed because of BlockStateBaseMixin, so only set their connections here.
// The full vanilla shape update would schedule a leaves tick for nearly every leaf, and ticking a waterlogged leaf's water could make it spill.
@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin {
    @WrapOperation(method = "postProcessGeneration", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;tick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"))
    private void lollygag$skipLeavesFluidTick(FluidState fluid, Level level, BlockPos pos, Operation<Void> original) {
        if (!(level.getBlockState(pos).getBlock() instanceof LeavesBlock)) {
            original.call(fluid, level, pos);
        }
    }

    @WrapOperation(method = "postProcessGeneration", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;updateFromNeighbourShapes(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"))
    private BlockState lollygag$connectLeaves(BlockState state, LevelAccessor level, BlockPos pos, Operation<BlockState> original) {
        if (state.getBlock() instanceof LeavesBlock) {
            return LeavesConnections.withConnections(state, level, pos);
        }
        return original.call(state, level, pos);
    }
}
