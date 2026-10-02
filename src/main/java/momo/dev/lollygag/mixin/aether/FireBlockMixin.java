package momo.dev.lollygag.mixin.aether;

import momo.dev.lollygag.registry.integration.aether.AetherBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Vanilla only carries the age over when spreading/updating into regular fire, so caelic fire would never burn out
@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
    @Inject(method = "getStateWithAge", at = @At("RETURN"), cancellable = true)
    private void lollygag$ageCaelicFire(LevelAccessor level, BlockPos pos, int age, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = cir.getReturnValue();
        if (state.is(AetherBase.CAELIC_FIRE.get())) {
            cir.setReturnValue(state.setValue(FireBlock.AGE, age));
        }
    }
}
