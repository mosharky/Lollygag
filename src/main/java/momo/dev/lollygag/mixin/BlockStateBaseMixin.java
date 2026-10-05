package momo.dev.lollygag.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Leaves placed during worldgen get marked for post-processing so LevelChunkMixin can set their connections
// once the chunk and its neighbors are done generating
@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "hasPostProcess", at = @At("HEAD"), cancellable = true)
    private void lollygag$postProcessLeaves(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (this.getBlock() instanceof LeavesBlock) {
            cir.setReturnValue(true);
        }
    }
}
