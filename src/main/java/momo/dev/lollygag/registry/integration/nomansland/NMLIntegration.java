package momo.dev.lollygag.registry.integration.nomansland;

import com.farcr.nomansland.common.block.GroundPickupBlock;
import com.farcr.nomansland.common.registry.blocks.NMLBlocks;
import momo.dev.lollygag.common.block.StrikeablePickupBlock;
import momo.dev.lollygag.registry.LBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class NMLIntegration {
    // Pebbles
    public static final DeferredBlock<Block> FLINT_PEBBLES = LBlocks.register("flint_pebbles", () -> new StrikeablePickupBlock(() -> NMLBlocks.PEBBLES.stack(), ofFullCopy(NMLBlocks.PEBBLES.get())));
    public static final DeferredBlock<Block> HOLYSTONE_PEBBLES = LBlocks.register("holystone_pebbles", () -> new GroundPickupBlock(ofFullCopy(NMLBlocks.PEBBLES.get()).mapColor(MapColor.WOOL)));
    public static final DeferredBlock<Block> BLACKSTONE_PEBBLES = LBlocks.register("blackstone_pebbles", () -> new GroundPickupBlock(ofFullCopy(NMLBlocks.PEBBLES.get()).mapColor(MapColor.COLOR_BLACK)));

    // Branches
    public static final DeferredBlock<Block> PINE_BRANCH = LBlocks.registerBranch("pine", () -> NMLBlocks.PINE.log().get(), () -> NMLBlocks.PINE.strippedLog().get());
    public static final DeferredBlock<Block> MAPLE_BRANCH = LBlocks.registerBranch("maple", () -> NMLBlocks.MAPLE.log().get(), () -> NMLBlocks.MAPLE.strippedLog().get());
    public static final DeferredBlock<Block> WALNUT_BRANCH = LBlocks.registerBranch("walnut", () -> NMLBlocks.WALNUT.log().get(), () -> NMLBlocks.WALNUT.strippedLog().get());
    public static final DeferredBlock<Block> WILLOW_BRANCH = LBlocks.registerBranch("willow", () -> NMLBlocks.WILLOW.log().get(), () -> NMLBlocks.WILLOW.strippedLog().get());

    public static void register() {}
}
