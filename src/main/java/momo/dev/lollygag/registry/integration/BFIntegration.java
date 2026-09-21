package momo.dev.lollygag.registry.integration;

import momo.dev.lollygag.common.block.HangingPearBlock;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.worldgen.LTreeGrowers;
import net.hecco.bountifulfares.definition.block.custom.FruitLeavesBlock;
import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.hecco.bountifulfares.definition.block.custom.StrippedFruitLogBlock;
import net.hecco.bountifulfares.registry.content.BFBlocks;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class BFIntegration {
    // Dead
    public static final DeferredBlock<Block> DEAD_LEAVES = LBlocks.register("dead_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    // Aspen
    public static final DeferredBlock<Block> ASPEN_LOG = LBlocks.register("aspen_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final DeferredBlock<Block> ASPEN_WOOD = LBlocks.register("aspen_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_LOG = LBlocks.register("stripped_aspen_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_WOOD = LBlocks.register("stripped_aspen_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final DeferredBlock<Block> ASPEN_LEAVES = LBlocks.register("aspen_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    public static final DeferredBlock<Block> ASPEN_SAPLING = LBlocks.register("aspen_sapling", () -> new SaplingBlock(LTreeGrowers.ASPEN, ofFullCopy(Blocks.BIRCH_SAPLING)));
    public static final DeferredBlock<Block> POTTED_ASPEN_SAPLING = LBlocks.registerNoItem("potted_aspen_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ASPEN_SAPLING, ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));
    // Birch
    public static final DeferredBlock<Block> BIRCH_LOG = LBlocks.register("birch_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final DeferredBlock<Block> BIRCH_WOOD = LBlocks.register("birch_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final DeferredBlock<Block> STRIPPED_BIRCH_LOG = LBlocks.register("stripped_birch_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final DeferredBlock<Block> STRIPPED_BIRCH_WOOD = LBlocks.register("stripped_birch_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final DeferredBlock<Block> BIRCH_LEAVES = LBlocks.register("birch_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    public static final DeferredBlock<Block> BIRCH_SAPLING = LBlocks.register("birch_sapling", () -> new SaplingBlock(LTreeGrowers.BIRCH, ofFullCopy(Blocks.BIRCH_SAPLING)));
    public static final DeferredBlock<Block> POTTED_BIRCH_SAPLING = LBlocks.registerNoItem("potted_birch_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BIRCH_SAPLING, ofFullCopy(Blocks.POTTED_BIRCH_SAPLING).noOcclusion()));
    // Pear
    public static final DeferredBlock<Block> PEAR_LOG = LBlocks.register("pear_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final DeferredBlock<Block> PEAR_WOOD = LBlocks.register("pear_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final DeferredBlock<Block> STRIPPED_PEAR_LOG = LBlocks.register("stripped_pear_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final DeferredBlock<Block> STRIPPED_PEAR_WOOD = LBlocks.register("stripped_pear_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final DeferredBlock<Block> HANGING_PEAR = LBlocks.registerNoItem("hanging_pear", () -> new HangingPearBlock(ofFullCopy(BFBlocks.HANGING_APPLE.get())));
    public static final DeferredBlock<Block> PEAR_LEAVES = LBlocks.register("pear_leaves", () -> new FruitLeavesBlock(HANGING_PEAR.get().defaultBlockState(), ofFullCopy(BFBlocks.APPLE_LEAVES.get())));
    public static final DeferredBlock<Block> FLOWERING_PEAR_LEAVES = LBlocks.register("flowering_pear_leaves", () -> new FruitLeavesBlock(HANGING_PEAR.get().defaultBlockState(), ofFullCopy(BFBlocks.FLOWERING_APPLE_LEAVES.get())));
    public static final DeferredBlock<Block> PEAR_SAPLING = LBlocks.register("pear_sapling", () -> new SaplingBlock(LTreeGrowers.PEAR, ofFullCopy(BFBlocks.APPLE_SAPLING.get())));
    public static final DeferredBlock<Block> POTTED_PEAR_SAPLING = LBlocks.registerNoItem("potted_pear_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), PEAR_SAPLING, ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));

    public static void register() {}
}
