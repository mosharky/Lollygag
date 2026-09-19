package momo.dev.lollygag.registry.integration;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import com.farcr.nomansland.common.block.GrassSproutsBlock;
import com.farcr.nomansland.common.block.torches.*;
import com.farcr.nomansland.common.registry.NMLSounds;
import com.farcr.nomansland.common.registry.blocks.NMLBlocks;
import com.teamabnormals.autumnity.common.block.LargeJackOLanternSliceBlock;
import com.teamabnormals.autumnity.core.registry.AutumnityBlocks;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleBlock;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleCakeBlock;
import com.teamabnormals.caverns_and_chasms.common.block.BrazierBlock;
import com.teamabnormals.caverns_and_chasms.core.registry.CCBlocks;
import com.teamabnormals.caverns_and_chasms.core.registry.CCSoundEvents;
import com.teamabnormals.incubation.common.block.BirdNestBlock;
import com.teamabnormals.incubation.common.block.EmptyNestBlock;
import com.teamabnormals.incubation.core.registry.IncubationBlocks;
import momo.dev.lollygag.common.block.*;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.LItems;
import momo.dev.lollygag.registry.LParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

@SuppressWarnings("unused")
public class LAetherIntegration {

    public static class Base extends ModIntegration {
        public static final DeferredBlock<Block> COARSE_AETHER_DIRT = LBlocks.register("coarse_aether_dirt", () -> new Block(ofFullCopy(Blocks.COARSE_DIRT)));

        public static final DeferredBlock<Block> SKYGRASS_SPROUTS = LBlocks.register("skygrass_sprouts", () -> new GrassSproutsBlock(ofFullCopy(Blocks.FERN).offsetType(BlockBehaviour.OffsetType.XZ)));
        public static final DeferredBlock<Block> TALL_SKYGRASS = LBlocks.register("tall_skygrass", () -> new DoublePlantBlock(ofFullCopy(Blocks.TALL_GRASS)));
        public static final DeferredBlock<Block> SHORT_SKYGRASS = LBlocks.register("short_skygrass", () -> new TallGrassBlock(ofFullCopy(Blocks.SHORT_GRASS)));
        public static final DeferredBlock<Block> SPIRING_FERN = LBlocks.register("spiring_fern", () -> new LDwarfSpruceHeadBlock(of().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
        public static final DeferredBlock<Block> SPIRING_FERN_PLANT = LBlocks.registerNoItem("spiring_fern_plant", () -> new LDwarfSprucePlantBlock(of().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY), (LDwarfSpruceHeadBlock) SPIRING_FERN.get()));

        public static final DeferredBlock<Block> CAELIC_FIRE = LBlocks.registerNoItem("caelic_fire", () -> new CaelicFireBlock(ofFullCopy(Blocks.SOUL_FIRE).mapColor(MapColor.COLOR_YELLOW)));
        public static final DeferredBlock<Block> CAELIC_CAMPFIRE = LBlocks.register("caelic_campfire", () -> new CampfireBlock(false, 1, ofFullCopy(Blocks.SOUL_CAMPFIRE)));
        public static final DeferredBlock<Block> CAELIC_LANTERN = LBlocks.register("caelic_lantern", () -> new LanternBlock(ofFullCopy(Blocks.LANTERN)));

        public static final DeferredBlock<Block> FRAGILE_HOLYSTONE = LBlocks.register("fragile_holystone", () -> new FragileHolystone(ofFullCopy(AetherBlocks.HOLYSTONE.get()).sound(CCSoundEvents.CCSoundTypes.FRAGILE_STONE)));
        public static final DeferredBlock<Block> FRAGILE_UNDERSHALE = LBlocks.register("fragile_undershale", () -> new FragileUndershale(ofFullCopy(AetherBlocks.HOLYSTONE.get()).sound(CCSoundEvents.CCSoundTypes.FRAGILE_STONE)));
        public static final DeferredBlock<Block> HOLYSTONE_QUARTZ_ORE = LBlocks.register("holystone_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));

        public static final DeferredItem<Item> AMBROSIUM_ITEM = LItems.register("ambrosium", () -> new Item(new Item.Properties()));
        public static final DeferredItem<Item> SCONCE_CAELIC_TORCH_ITEM = LItems.register("sconce_caelic_torch",() -> new StandingAndWallBlockItem(LAetherIntegration.NoMansLand.SCONCE_CAELIC_TORCH.get(), LAetherIntegration.NoMansLand.SCONCE_CAELIC_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
    }

    public static class CavernsAndChasms extends ModIntegration {
        public static final DeferredBlock<Block> ROCKY_AETHER_DIRT = LBlocks.register("rocky_aether_dirt", () -> new Block(ofFullCopy(AetherBlocks.AETHER_DIRT.get()).sound(CCSoundEvents.CCSoundTypes.ROCKY_DIRT).requiresCorrectToolForDrops().strength(1.5F)));
        public static final DeferredBlock<Block> CAELIC_BRAZIER = LBlocks.register("caelic_brazier", () -> new BrazierBlock(1.0F, CCBlocks.CCProperties.BRAZIER));
        public static final DeferredBlock<Block> HOLYSTONE_SILVER_ORE = LBlocks.register("holystone_silver_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));
        public static final DeferredBlock<Block> HOLYSTONE_ZIRCONIA_ORE = LBlocks.register("holystone_zirconia_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));
    }

    public static class Autumnity extends ModIntegration {
        public static final DeferredBlock<Block> CAELIC_JACK_O_LANTERN = LBlocks.register("caelic_jack_o_lantern", () -> new LJackOLanternBlock(ofFullCopy(Blocks.JACK_O_LANTERN)));
        public static final DeferredBlock<Block> LARGE_CAELIC_JACK_O_LANTERN = LBlocks.register("large_caelic_jack_o_lantern", () -> new LargeJackOLanternSliceBlock(ofFullCopy(AutumnityBlocks.LARGE_JACK_O_LANTERN_SLICE.get())));
    }

    public static class BuzzierBees extends ModIntegration {
        public static final DeferredBlock<Block> CAELIC_CANDLE = LBlocks.register("caelic_candle", () -> new SpecialCandleBlock(LParticleTypes.SMALL_CAELIC_FIRE_FLAME.getId(), ofFullCopy(Blocks.CANDLE)));
        public static final DeferredBlock<Block> CAELIC_CANDLE_CAKE = LBlocks.registerNoItem("caelic_candle_cake", () -> new SpecialCandleCakeBlock(CAELIC_CANDLE.get(), LParticleTypes.SMALL_CAELIC_FIRE_FLAME.getId(), ofFullCopy(Blocks.CANDLE_CAKE)));
    }

    public static class NoMansLand extends ModIntegration {
        // TODO: sconce torches need a new class to hold non-vanilla particles
        public static final DeferredBlock<Block> SCONCE_CAELIC_TORCH = LBlocks.registerNoItem("sconce_caelic_torch", () -> new SconceTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(Blocks.SOUL_TORCH).sound(NMLSounds.SCONCE_TORCH)));
        public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_CAELIC_TORCH = LBlocks.registerNoItem("extinguished_sconce_caelic_torch", () -> new ExtinguishedSconceTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_SCONCE_TORCH.get())));
        public static final DeferredBlock<Block> SCONCE_CAELIC_WALL_TORCH = LBlocks.registerNoItem("sconce_caelic_wall_torch", () -> new SconceWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(NMLBlocks.SCONCE_WALL_TORCH.get()).lootFrom(SCONCE_CAELIC_TORCH)));
        public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_CAELIC_WALL_TORCH = LBlocks.registerNoItem("extinguished_sconce_caelic_wall_torch", () -> new ExtinguishedSconceWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_SCONCE_WALL_TORCH.get())));
        public static final DeferredBlock<Block> EXTINGUISHED_CAELIC_TORCH = LBlocks.registerNoItem("extinguished_caelic_torch", () -> new ExtinguishedTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_TORCH.get())));
        public static final DeferredBlock<Block> EXTINGUISHED_CAELIC_WALL_TORCH = LBlocks.registerNoItem("extinguished_caelic_wall_torch", () -> new ExtinguishedWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME,ofFullCopy(NMLBlocks.EXTINGUISHED_WALL_TORCH.get())));
    }

    public static class Incubation extends ModIntegration {
        public static final DeferredBlock<Block> TWIG_BLACK_MOA_NEST = LBlocks.registerNoItem("twig_black_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLACK_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));
        public static final DeferredBlock<Block> TWIG_BLUE_MOA_NEST = LBlocks.registerNoItem("twig_blue_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLUE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));
        public static final DeferredBlock<Block> TWIG_WHITE_MOA_NEST = LBlocks.registerNoItem("twig_white_moa_nest", () -> new BirdNestBlock(() -> AetherItems.WHITE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));

        public static final DeferredBlock<Block> HAY_BLACK_MOA_NEST = LBlocks.registerNoItem("hay_black_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLACK_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));
        public static final DeferredBlock<Block> HAY_BLUE_MOA_NEST = LBlocks.registerNoItem("hay_blue_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLUE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));
        public static final DeferredBlock<Block> HAY_WHITE_MOA_NEST = LBlocks.registerNoItem("hay_white_moa_nest", () -> new BirdNestBlock(() -> AetherItems.WHITE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));
    }
}
