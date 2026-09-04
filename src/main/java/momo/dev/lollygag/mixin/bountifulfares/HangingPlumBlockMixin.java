package momo.dev.lollygag.mixin.bountifulfares;

import momo.dev.lollygag.registry.LTags;
import net.hecco.bountifulfares.definition.block.custom.HangingFruitBlock;
import net.hecco.bountifulfares.definition.block.custom.HangingPlumBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HangingPlumBlock.class)
public class HangingPlumBlockMixin extends HangingFruitBlock {
    public HangingPlumBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    public void lollygag$canSurvive(BlockState state, LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(
                world.getBlockState(pos.above()).is(LTags.BLOCKS.CAN_HANG_ON.PLUM) && !world.isWaterAt(pos)
        );
    }
}
