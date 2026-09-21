package momo.dev.lollygag.registry.integration.aether;

import com.teamabnormals.autumnity.common.block.LargeJackOLanternSliceBlock;
import com.teamabnormals.autumnity.core.registry.AutumnityBlocks;
import momo.dev.lollygag.common.block.LJackOLanternBlock;
import momo.dev.lollygag.registry.LBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class AetherAutumnity {
    public static final DeferredBlock<Block> CAELIC_JACK_O_LANTERN = LBlocks.register("caelic_jack_o_lantern", () -> new LJackOLanternBlock(ofFullCopy(Blocks.JACK_O_LANTERN)));
    public static final DeferredBlock<Block> LARGE_CAELIC_JACK_O_LANTERN = LBlocks.register("large_caelic_jack_o_lantern", () -> new LargeJackOLanternSliceBlock(ofFullCopy(AutumnityBlocks.LARGE_JACK_O_LANTERN_SLICE.get())));

    public static void register() {}
}
