package momo.dev.lollygag.mixin.aether;

import momo.dev.lollygag.common.block.CaelicFireBlock;
import momo.dev.lollygag.registry.integration.aether.AetherBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Places caelic fire on its base blocks, the same way vanilla picks soul fire (flint and steel, fire charges, lava, spreading),
// and in place of regular fire anywhere in the Aether
@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin {
    @Inject(method = "getState", at = @At("HEAD"), cancellable = true)
    private static void lollygag$getCaelicFireState(BlockGetter level, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        BlockState below = level.getBlockState(pos.below());
        CaelicFireBlock caelicFire = (CaelicFireBlock) AetherBase.CAELIC_FIRE.get();
        if (CaelicFireBlock.canSurviveOnBlock(below)) {
            cir.setReturnValue(caelicFire.defaultBlockState());
        } else if (level instanceof LevelReader reader && CaelicFireBlock.isInAether(reader, pos) && !SoulFireBlock.canSurviveOnBlock(below)) {
            cir.setReturnValue(caelicFire.getPlacementState(level, pos));
        }
    }
}
