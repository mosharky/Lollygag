package momo.dev.lollygag.registry;

import com.farcr.nomansland.common.block.GroundPickupBlock;
import com.farcr.nomansland.common.registry.blocks.NMLBlocks;
import com.teamabnormals.caverns_and_chasms.common.block.CoalBlock;
import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.block.*;
import momo.dev.lollygag.common.block.CoalBlockFixed;
import momo.dev.lollygag.registry.integration.LAetherIntegration;
import momo.dev.lollygag.registry.worldgen.LTreeGrowers;
import net.hecco.bountifulfares.definition.block.custom.FruitLeavesBlock;
import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.hecco.bountifulfares.definition.block.custom.StrippedFruitLogBlock;
import net.hecco.bountifulfares.registry.content.BFBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class LBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lollygag.MODID);
    public static List<DeferredBlock<?>> BLOCKS_REGISTERED = new ArrayList<>();

    public static class LProperties {
        private static BlockBehaviour.Properties placedCoal(int baseLight) {
            return of().mapColor(MapColor.COLOR_BLACK).strength(2.0F, 6.0F).requiresCorrectToolForDrops().lightLevel(placedCoalLight(baseLight)).noOcclusion().pushReaction(PushReaction.DESTROY);
        }

        private static ToIntFunction<BlockState> placedCoalLight(int base) {
            return state -> {
                boolean warm = state.getValue(CoalBlock.WARM);
                boolean lit = state.getValue(CoalBlock.LIT);
                return (warm || lit) ? base + (lit ? 4 : 2) + state.getValue(CoalBlock.COAL) : 0;
            };
        }
    }

    // Placed Ingots
    // TODO: oreganized has a "goopyness" feature, maybe make an IngotBlock class for it?
    public static final DeferredBlock<Block> LEAD_INGOT_PLACED = registerPlacedItem("lead_ingot", () -> new IngotBlock(() -> OItems.LEAD_INGOT.get(), of().strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)));
    public static final DeferredBlock<Block> ELECTRUM_INGOT_PLACED = registerPlacedItem("electrum_ingot", () -> new IngotBlock(() -> OItems.ELECTRUM_INGOT.get(), ofFullCopy(OBlocks.ELECTRUM_BLOCK.get())));
    public static final DeferredBlock<Block> AMBROSIUM_PLACED = registerPlacedItem("ambrosium", () -> new CoalBlockFixed(() -> LAetherIntegration.Base.AMBROSIUM_ITEM.get(), LProperties.placedCoal(6)));

    // Pebbles
    public static final DeferredBlock<Block> FLINT_PEBBLES = register("flint_pebbles", () -> new StrikeablePickupBlock(() -> NMLBlocks.PEBBLES.stack(), ofFullCopy(NMLBlocks.PEBBLES.get())));
    public static final DeferredBlock<Block> HOLYSTONE_PEBBLES = register("holystone_pebbles", () -> new GroundPickupBlock(ofFullCopy(NMLBlocks.PEBBLES.get()).mapColor(MapColor.WOOL)));
    public static final DeferredBlock<Block> BLACKSTONE_PEBBLES = register("blackstone_pebbles", () -> new GroundPickupBlock(ofFullCopy(NMLBlocks.PEBBLES.get()).mapColor(MapColor.COLOR_BLACK)));

    // Dead
    public static final DeferredBlock<Block> DEAD_LEAVES = register("dead_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    // Aspen
    public static final DeferredBlock<Block> ASPEN_LOG = register("aspen_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final DeferredBlock<Block> ASPEN_WOOD = register("aspen_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_LOG = register("stripped_aspen_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_WOOD = register("stripped_aspen_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final DeferredBlock<Block> ASPEN_LEAVES = register("aspen_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    public static final DeferredBlock<Block> ASPEN_SAPLING = register("aspen_sapling", () -> new SaplingBlock(LTreeGrowers.ASPEN, ofFullCopy(Blocks.BIRCH_SAPLING)));
    public static final DeferredBlock<Block> POTTED_ASPEN_SAPLING = registerNoItem("potted_aspen_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ASPEN_SAPLING, ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));
    // Birch
    public static final DeferredBlock<Block> BIRCH_LOG = register("birch_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final DeferredBlock<Block> BIRCH_WOOD = register("birch_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final DeferredBlock<Block> STRIPPED_BIRCH_LOG = register("stripped_birch_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final DeferredBlock<Block> STRIPPED_BIRCH_WOOD = register("stripped_birch_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final DeferredBlock<Block> BIRCH_LEAVES = register("birch_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    public static final DeferredBlock<Block> BIRCH_SAPLING = register("birch_sapling", () -> new SaplingBlock(LTreeGrowers.BIRCH, ofFullCopy(Blocks.BIRCH_SAPLING)));
    public static final DeferredBlock<Block> POTTED_BIRCH_SAPLING = registerNoItem("potted_birch_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BIRCH_SAPLING, ofFullCopy(Blocks.POTTED_BIRCH_SAPLING).noOcclusion()));
    // Pear
    public static final DeferredBlock<Block> PEAR_LOG = register("pear_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final DeferredBlock<Block> PEAR_WOOD = register("pear_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final DeferredBlock<Block> STRIPPED_PEAR_LOG = register("stripped_pear_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final DeferredBlock<Block> STRIPPED_PEAR_WOOD = register("stripped_pear_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final DeferredBlock<Block> HANGING_PEAR = registerNoItem("hanging_pear", () -> new HangingPearBlock(ofFullCopy(BFBlocks.HANGING_APPLE.get())));
    public static final DeferredBlock<Block> PEAR_LEAVES = register("pear_leaves", () -> new FruitLeavesBlock(HANGING_PEAR.get().defaultBlockState(), ofFullCopy(BFBlocks.APPLE_LEAVES.get())));
    public static final DeferredBlock<Block> FLOWERING_PEAR_LEAVES = register("flowering_pear_leaves", () -> new FruitLeavesBlock(HANGING_PEAR.get().defaultBlockState(), ofFullCopy(BFBlocks.FLOWERING_APPLE_LEAVES.get())));
    public static final DeferredBlock<Block> PEAR_SAPLING = register("pear_sapling", () -> new SaplingBlock(LTreeGrowers.PEAR, ofFullCopy(BFBlocks.APPLE_SAPLING.get())));
    public static final DeferredBlock<Block> POTTED_PEAR_SAPLING = registerNoItem("potted_pear_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), PEAR_SAPLING, ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));

    // Helpers (from NMLBlocks)
    public static <T extends Block> DeferredBlock<T> registerNoItem(String name, Supplier<T> block) {
        DeferredBlock<T> deferred = BLOCKS.register(name, block);
        BLOCKS_REGISTERED.add(deferred);
        return deferred;
    }

    public static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> block) {
        DeferredBlock<T> deferred = registerNoItem(name, block);
        LItems.register(name, () -> new BlockItem(deferred.get(), new Item.Properties()));
        return deferred;
    }

    public static <T extends Block> DeferredBlock<T> registerPlacedItem(String name, Supplier<T> block) {
        DeferredBlock<T> deferred = registerNoItem(name, block);
        LItems.register(name + "_placed", () -> new BlockItem(deferred.get(), new Item.Properties()));
        return deferred;
    }
}

