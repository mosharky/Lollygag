package momo.dev.lollygag.registry;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.farcr.nomansland.common.block.GrassSproutsBlock;
import com.farcr.nomansland.common.block.GroundPickupBlock;
import com.farcr.nomansland.common.block.torches.*;
import com.farcr.nomansland.common.definitions.BlockDefinition;
import com.farcr.nomansland.common.definitions.BlockProperties;
import com.farcr.nomansland.common.registry.NMLSounds;
import com.farcr.nomansland.common.registry.blocks.NMLBlocks;
import com.farcr.nomansland.datagen.loot.SelfBlockLootType;
import com.teamabnormals.autumnity.common.block.LargeJackOLanternSliceBlock;
import com.teamabnormals.autumnity.core.registry.AutumnityBlocks;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleBlock;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleCakeBlock;
import com.teamabnormals.caverns_and_chasms.common.block.BrazierBlock;
import com.teamabnormals.caverns_and_chasms.common.block.CoalBlock;
import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import com.teamabnormals.caverns_and_chasms.core.registry.CCBlocks;
import com.teamabnormals.caverns_and_chasms.core.registry.CCSoundEvents;
import com.teamabnormals.incubation.common.block.BirdNestBlock;
import com.teamabnormals.incubation.common.block.EmptyNestBlock;
import com.teamabnormals.incubation.core.registry.IncubationBlocks;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.block.*;
import momo.dev.lollygag.common.block.CoalBlockFixed;
import momo.dev.lollygag.common.block.CaelicFireBlock;
import momo.dev.lollygag.registry.worldgen.LTreeGrowers;
import net.hecco.bountifulfares.definition.block.custom.FruitLeavesBlock;
import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.hecco.bountifulfares.definition.block.custom.StrippedFruitLogBlock;
import net.hecco.bountifulfares.registry.content.BFBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class LBlocks {

    public static class LProperties {
        private static BlockBehaviour.Properties placedCoal(int baseLight) {
            return BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(2.0F, 6.0F).requiresCorrectToolForDrops().lightLevel(placedCoalLight(baseLight)).noOcclusion().pushReaction(PushReaction.DESTROY);
        }

        private static ToIntFunction<BlockState> placedCoalLight(int base) {
            return state -> {
                boolean warm = state.getValue(CoalBlock.WARM);
                boolean lit = state.getValue(CoalBlock.LIT);
                return (warm || lit) ? base + (lit ? 4 : 2) + state.getValue(CoalBlock.COAL) : 0;
            };
        }
    }

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lollygag.MODID);
    public static List<BlockDefinition<?>> BLOCK_DEFINITIONS = new ArrayList<>();

    // Foliage
    public static final BlockDefinition<GrassSproutsBlock> SKYGRASS_SPROUTS = register("skygrass_sprouts", () -> new GrassSproutsBlock(ofFullCopy(Blocks.FERN).offsetType(OffsetType.XZ)));
    public static final BlockDefinition<DoublePlantBlock> TALL_SKYGRASS = register("tall_skygrass", () -> new DoublePlantBlock(ofFullCopy(Blocks.TALL_GRASS)));
    public static final BlockDefinition<TallGrassBlock> SHORT_SKYGRASS = register("short_skygrass", () -> new TallGrassBlock(ofFullCopy(Blocks.SHORT_GRASS)));
    public static final BlockDefinition<LDwarfSpruceHeadBlock> SPIRING_FERN = register("spiring_fern",
            () -> new LDwarfSpruceHeadBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final BlockDefinition<LDwarfSprucePlantBlock> SPIRING_FERN_PLANT = registerNoItem("spiring_fern_plant",
            () -> new LDwarfSprucePlantBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY), SPIRING_FERN.get()));

    // Misc Blocks
    public static final BlockDefinition<Block> COARSE_AETHER_DIRT = register("coarse_aether_dirt", () -> new Block(ofFullCopy(Blocks.COARSE_DIRT)));
    public static final BlockDefinition<Block> ROCKY_AETHER_DIRT = register("rocky_aether_dirt",
            () -> new Block(ofFullCopy(AetherBlocks.AETHER_DIRT.get()).sound(CCSoundEvents.CCSoundTypes.ROCKY_DIRT).requiresCorrectToolForDrops().strength(1.5F)));

    // Caelic blocks
    // TODO: new particles
    public static final BlockDefinition<CaelicFireBlock> CAELIC_FIRE = registerNoItem("caelic_fire", () -> new CaelicFireBlock(ofFullCopy(Blocks.SOUL_FIRE).mapColor(MapColor.COLOR_YELLOW)));
    public static final BlockDefinition<CampfireBlock> CAELIC_CAMPFIRE = register("caelic_campfire", () -> new CampfireBlock(false, 1, ofFullCopy(Blocks.SOUL_CAMPFIRE)));
    public static final BlockDefinition<LanternBlock> CAELIC_LANTERN = register("caelic_lantern", () -> new LanternBlock(ofFullCopy(Blocks.LANTERN)));
    public static final BlockDefinition<BrazierBlock> CAELIC_BRAZIER = register("caelic_brazier", () -> new BrazierBlock(1.0F, CCBlocks.CCProperties.BRAZIER));
    public static final BlockDefinition<LJackOLanternBlock> CAELIC_JACK_O_LANTERN = register("caelic_jack_o_lantern", () -> new LJackOLanternBlock(ofFullCopy(Blocks.JACK_O_LANTERN)), BlockProperties.custom(true));
    public static final BlockDefinition<LargeJackOLanternSliceBlock> LARGE_CAELIC_JACK_O_LANTERN = register("large_caelic_jack_o_lantern", () -> new LargeJackOLanternSliceBlock(ofFullCopy(AutumnityBlocks.LARGE_JACK_O_LANTERN_SLICE.get())),  BlockProperties.custom(true));
    public static final BlockDefinition<SpecialCandleBlock> CAELIC_CANDLE = register("caelic_candle", () -> new SpecialCandleBlock(LParticleTypes.SMALL_CAELIC_FIRE_FLAME.getId(), ofFullCopy(Blocks.CANDLE)));
    public static final BlockDefinition<SpecialCandleCakeBlock> CAELIC_CANDLE_CAKE = registerNoItem("caelic_candle_cake", () -> new SpecialCandleCakeBlock(CAELIC_CANDLE.get(), LParticleTypes.SMALL_CAELIC_FIRE_FLAME.getId(), ofFullCopy(Blocks.CANDLE_CAKE)));
    // TODO: sconce torches need a new class to hold non-vanilla particles
    public static final BlockDefinition<SconceTorchBlock> SCONCE_CAELIC_TORCH = registerNoItem("sconce_caelic_torch", () -> new SconceTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(Blocks.SOUL_TORCH).sound(NMLSounds.SCONCE_TORCH)), new BlockProperties(new SelfBlockLootType(), false));
    public static final BlockDefinition<ExtinguishedSconceTorchBlock> EXTINGUISHED_SCONCE_CAELIC_TORCH = registerNoItem("extinguished_sconce_caelic_torch", () -> new ExtinguishedSconceTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_SCONCE_TORCH.get())), BlockProperties.custom(true));
    public static final BlockDefinition<SconceWallTorchBlock> SCONCE_CAELIC_WALL_TORCH = registerNoItem("sconce_caelic_wall_torch", () -> new SconceWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(NMLBlocks.SCONCE_WALL_TORCH.get()).lootFrom(SCONCE_CAELIC_TORCH)), BlockProperties.custom(true));
    public static final BlockDefinition<ExtinguishedSconceWallTorchBlock> EXTINGUISHED_SCONCE_CAELIC_WALL_TORCH = registerNoItem("extinguished_sconce_caelic_wall_torch", () -> new ExtinguishedSconceWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_SCONCE_WALL_TORCH.get())), BlockProperties.custom(true));
    public static final BlockDefinition<ExtinguishedTorchBlock> EXTINGUISHED_CAELIC_TORCH = registerNoItem("extinguished_caelic_torch", () -> new ExtinguishedTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_TORCH.get())), BlockProperties.custom(true));
    public static final BlockDefinition<ExtinguishedWallTorchBlock> EXTINGUISHED_CAELIC_WALL_TORCH = registerNoItem("extinguished_caelic_wall_torch", () -> new ExtinguishedWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_WALL_TORCH.get())), BlockProperties.custom(true));


    // Fragile Blocks
    public static final BlockDefinition<FragileHolystone> FRAGILE_HOLYSTONE = register("fragile_holystone",
            () -> new FragileHolystone(ofFullCopy(AetherBlocks.HOLYSTONE.get()).sound(CCSoundEvents.CCSoundTypes.FRAGILE_STONE)));
    public static final BlockDefinition<FragileUndershale> FRAGILE_UNDERSHALE = register("fragile_undershale",  // TODO: what is undershale
            () -> new FragileUndershale(ofFullCopy(AetherBlocks.HOLYSTONE.get()).sound(CCSoundEvents.CCSoundTypes.FRAGILE_STONE)));

    // Ores
    public static final BlockDefinition<DropExperienceBlock> HOLYSTONE_SILVER_ORE = register("holystone_silver_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));
    public static final BlockDefinition<DropExperienceBlock> HOLYSTONE_QUARTZ_ORE = register("holystone_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));
    public static final BlockDefinition<DropExperienceBlock> HOLYSTONE_ZIRCONIA_ORE = register("holystone_zirconia_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));

    // Placed Ingots
    // TODO: oreganized has a "goopyness" feature, maybe make an IngotBlock class for it?
    public static final BlockDefinition<IngotBlock> LEAD_INGOT = registerPlacedItem("lead_ingot",
            () -> new IngotBlock(() -> OItems.LEAD_INGOT.get(), BlockBehaviour.Properties.of().strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)));
    public static final BlockDefinition<IngotBlock> ELECTRUM_INGOT = registerPlacedItem("electrum_ingot", () -> new IngotBlock(() -> OItems.ELECTRUM_INGOT.get(), ofFullCopy(OBlocks.ELECTRUM_BLOCK.get())));
    public static final BlockDefinition<CoalBlock> AMBROSIUM = registerPlacedItem("ambrosium", () -> new CoalBlockFixed(() -> LItems.AMBROSIUM.get(), LProperties.placedCoal(6)));

    // Pebbles
    public static final BlockDefinition<StrikeablePickupBlock> FLINT_PEBBLES = register("flint_pebbles", () -> new StrikeablePickupBlock(() -> NMLBlocks.PEBBLES.stack(), ofFullCopy(NMLBlocks.PEBBLES.get())));
    public static final BlockDefinition<GroundPickupBlock> HOLYSTONE_PEBBLES = register("holystone_pebbles", () -> new GroundPickupBlock(ofFullCopy(NMLBlocks.PEBBLES.get()).mapColor(MapColor.WOOL)));
    public static final BlockDefinition<GroundPickupBlock> BLACKSTONE_PEBBLES = register("blackstone_pebbles", () -> new GroundPickupBlock(ofFullCopy(NMLBlocks.PEBBLES.get()).mapColor(MapColor.COLOR_BLACK)));

    // Dead
    public static final BlockDefinition<LeavesBlock> DEAD_LEAVES = register("dead_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    // Aspen
    public static final BlockDefinition<FruitLogBlock> ASPEN_LOG = register("aspen_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final BlockDefinition<FruitLogBlock> ASPEN_WOOD = register("aspen_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final BlockDefinition<StrippedFruitLogBlock> STRIPPED_ASPEN_LOG = register("stripped_aspen_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final BlockDefinition<StrippedFruitLogBlock> STRIPPED_ASPEN_WOOD = register("stripped_aspen_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final BlockDefinition<LeavesBlock> ASPEN_LEAVES = register("aspen_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    public static final BlockDefinition<SaplingBlock> ASPEN_SAPLING = register("aspen_sapling", () -> new SaplingBlock(LTreeGrowers.ASPEN, ofFullCopy(Blocks.BIRCH_SAPLING)), BlockProperties.sapling());
    public static final BlockDefinition<FlowerPotBlock> POTTED_ASPEN_SAPLING = registerNoItem("potted_aspen_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ASPEN_SAPLING,
                    ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()), BlockProperties.flowerPot(ASPEN_SAPLING));
    // Birch
    public static final BlockDefinition<FruitLogBlock> BIRCH_LOG = register("birch_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final BlockDefinition<FruitLogBlock> BIRCH_WOOD = register("birch_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final BlockDefinition<StrippedFruitLogBlock> STRIPPED_BIRCH_LOG = register("stripped_birch_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final BlockDefinition<StrippedFruitLogBlock> STRIPPED_BIRCH_WOOD = register("stripped_birch_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final BlockDefinition<LeavesBlock> BIRCH_LEAVES = register("birch_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.BIRCH_LEAVES).isViewBlocking((s, g, p) -> false).isSuffocating((s, g, p) -> false)));
    public static final BlockDefinition<SaplingBlock> BIRCH_SAPLING = register("birch_sapling", () -> new SaplingBlock(LTreeGrowers.BIRCH, ofFullCopy(Blocks.BIRCH_SAPLING)), BlockProperties.sapling());
    public static final BlockDefinition<FlowerPotBlock> POTTED_BIRCH_SAPLING = registerNoItem("potted_birch_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BIRCH_SAPLING,
                    ofFullCopy(Blocks.POTTED_BIRCH_SAPLING).noOcclusion()), BlockProperties.flowerPot(BIRCH_SAPLING));
    // Pear
    public static final BlockDefinition<FruitLogBlock> PEAR_LOG = register("pear_log", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_LOG.get())));
    public static final BlockDefinition<FruitLogBlock> PEAR_WOOD = register("pear_wood", () -> new FruitLogBlock(ofFullCopy(BFBlocks.APPLE_WOOD.get())));
    public static final BlockDefinition<StrippedFruitLogBlock> STRIPPED_PEAR_LOG = register("stripped_pear_log", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_LOG.get())));
    public static final BlockDefinition<StrippedFruitLogBlock> STRIPPED_PEAR_WOOD = register("stripped_pear_wood", () -> new StrippedFruitLogBlock(ofFullCopy(BFBlocks.STRIPPED_APPLE_WOOD.get())));
    public static final BlockDefinition<HangingPearBlock> HANGING_PEAR = registerNoItem("hanging_pear", () -> new HangingPearBlock(ofFullCopy(BFBlocks.HANGING_APPLE.get())));
    public static final BlockDefinition<LeavesBlock> PEAR_LEAVES = register("pear_leaves", () -> new FruitLeavesBlock(HANGING_PEAR.get().defaultBlockState(), ofFullCopy(BFBlocks.APPLE_LEAVES.get())));
    public static final BlockDefinition<LeavesBlock> FLOWERING_PEAR_LEAVES = register("flowering_pear_leaves", () -> new FruitLeavesBlock(HANGING_PEAR.get().defaultBlockState(), ofFullCopy(BFBlocks.FLOWERING_APPLE_LEAVES.get())));
    public static final BlockDefinition<SaplingBlock> PEAR_SAPLING = register("pear_sapling", () -> new SaplingBlock(LTreeGrowers.PEAR, ofFullCopy(BFBlocks.APPLE_SAPLING.get())), BlockProperties.sapling());
    public static final BlockDefinition<FlowerPotBlock> POTTED_PEAR_SAPLING = registerNoItem("potted_pear_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), PEAR_SAPLING,
                    ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()), BlockProperties.flowerPot(PEAR_SAPLING));


    // Incubation nests
    public static final BlockDefinition<BirdNestBlock> TWIG_BLACK_MOA_NEST = registerNoItem("twig_black_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLACK_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));
    public static final BlockDefinition<BirdNestBlock> TWIG_BLUE_MOA_NEST = registerNoItem("twig_blue_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLUE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));
    public static final BlockDefinition<BirdNestBlock> TWIG_WHITE_MOA_NEST = registerNoItem("twig_white_moa_nest", () -> new BirdNestBlock(() -> AetherItems.WHITE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));

    public static final BlockDefinition<BirdNestBlock> HAY_BLACK_MOA_NEST = registerNoItem("hay_black_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLACK_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));
    public static final BlockDefinition<BirdNestBlock> HAY_BLUE_MOA_NEST = registerNoItem("hay_blue_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLUE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));
    public static final BlockDefinition<BirdNestBlock> HAY_WHITE_MOA_NEST = registerNoItem("hay_white_moa_nest", () -> new BirdNestBlock(() -> AetherItems.WHITE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));





    // Helpers (from NMLBlocks)
    public static <T extends Block> BlockDefinition<T> registerNoItem(String name, Supplier<T> block, BlockProperties properties) {
        DeferredBlock<T> deferred = BLOCKS.register(name, block);
        BlockDefinition<T> definition = BlockDefinition.fromHolder(deferred, properties);
        BLOCK_DEFINITIONS.add(definition);
        return definition;
    }

    public static <T extends Block> BlockDefinition<T> registerNoItem(String name, Supplier<T> block) {
        return registerNoItem(name, block, BlockProperties.custom(false));
    }

    public static <T extends Block> BlockDefinition<T> register(String name, Supplier<T> block, BlockProperties properties) {
        BlockDefinition<T> definition = registerNoItem(name, block, properties);
        LItems.register(name, () -> new BlockItem(definition.get(), new Item.Properties()));
        return definition;
    }

    public static <T extends Block> BlockDefinition<T> registerPlacedItem(String name, Supplier<T> block) {
        BlockDefinition<T> definition = registerNoItem(name, block, BlockProperties.custom(true));
        LItems.register(name + "_placed", () -> new BlockItem(definition.get(), new Item.Properties()), true);
        return definition;
    }

    public static <T extends Block> BlockDefinition<T> register(String name, Supplier<T> block) {
        return register(name, block, BlockProperties.custom(false));
    }
}
