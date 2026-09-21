package momo.dev.lollygag.registry.integration.aether;

import com.aetherteam.aether.item.AetherItems;
import com.teamabnormals.incubation.common.block.BirdNestBlock;
import com.teamabnormals.incubation.common.block.EmptyNestBlock;
import com.teamabnormals.incubation.core.registry.IncubationBlocks;
import momo.dev.lollygag.registry.LBlocks;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class AetherIncubation {
    public static final DeferredBlock<Block> TWIG_BLACK_MOA_NEST = LBlocks.registerNoItem("twig_black_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLACK_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));
    public static final DeferredBlock<Block> TWIG_BLUE_MOA_NEST = LBlocks.registerNoItem("twig_blue_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLUE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));
    public static final DeferredBlock<Block> TWIG_WHITE_MOA_NEST = LBlocks.registerNoItem("twig_white_moa_nest", () -> new BirdNestBlock(() -> AetherItems.WHITE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.TWIG_NEST.get(), IncubationBlocks.IncubationProperties.TWIG_NEST));

    public static final DeferredBlock<Block> HAY_BLACK_MOA_NEST = LBlocks.registerNoItem("hay_black_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLACK_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));
    public static final DeferredBlock<Block> HAY_BLUE_MOA_NEST = LBlocks.registerNoItem("hay_blue_moa_nest", () -> new BirdNestBlock(() -> AetherItems.BLUE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));
    public static final DeferredBlock<Block> HAY_WHITE_MOA_NEST = LBlocks.registerNoItem("hay_white_moa_nest", () -> new BirdNestBlock(() -> AetherItems.WHITE_MOA_EGG.get(), (EmptyNestBlock) IncubationBlocks.HAY_NEST.get(), IncubationBlocks.IncubationProperties.HAY_NEST));

    public static void register() {}
}
