package momo.dev.lollygag.common.block;

import com.teamabnormals.autumnity.common.block.AutumnityJackOLanternBlock;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.integration.LAetherIntegration;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class LJackOLanternBlock extends AutumnityJackOLanternBlock {
    @Nullable private BlockPattern snowGolemFull;
    @Nullable private BlockPattern ironGolemFull;
    private static final Predicate<BlockState> IS_PUMPKIN = (state) -> state != null && (state.is(LAetherIntegration.Autumnity.CAELIC_JACK_O_LANTERN));

    public LJackOLanternBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected BlockPattern getOrCreateSnowGolemFull() {
        if (this.snowGolemFull == null) {
            this.snowGolemFull = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', BlockInWorld.hasState(IS_PUMPKIN)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }

        return this.snowGolemFull;
    }

    @Override
    protected BlockPattern getOrCreateIronGolemFull() {
        if (this.ironGolemFull == null) {
            this.ironGolemFull = BlockPatternBuilder.start().aisle("~^~", "###", "~#~").where('^', BlockInWorld.hasState(IS_PUMPKIN)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', block -> block.getState().isAir()).build();
        }

        return this.ironGolemFull;
    }
}
