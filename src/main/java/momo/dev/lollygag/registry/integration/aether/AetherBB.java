package momo.dev.lollygag.registry.integration.aether;

import com.teamabnormals.buzzier_bees.common.block.SpecialCandleBlock;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleCakeBlock;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.LParticleTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class AetherBB {
    public static final DeferredBlock<Block> CAELIC_CANDLE = LBlocks.register("caelic_candle", () -> new SpecialCandleBlock(LParticleTypes.SMALL_CAELIC_FIRE_FLAME.getId(), ofFullCopy(Blocks.CANDLE)));
    public static final DeferredBlock<Block> CAELIC_CANDLE_CAKE = LBlocks.registerNoItem("caelic_candle_cake", () -> new SpecialCandleCakeBlock(CAELIC_CANDLE.get(), LParticleTypes.SMALL_CAELIC_FIRE_FLAME.getId(), ofFullCopy(Blocks.CANDLE_CAKE)));

    public static void register() {}
}
