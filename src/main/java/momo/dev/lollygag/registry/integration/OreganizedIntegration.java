package momo.dev.lollygag.registry.integration;

import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import momo.dev.lollygag.registry.LBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class OreganizedIntegration {
    // Placed Ingots
    // TODO: oreganized has a "goopyness" feature, maybe make an IngotBlock class for it?
    public static final DeferredBlock<Block> LEAD_INGOT_PLACED = LBlocks.registerPlacedItem("lead_ingot", () -> new IngotBlock(() -> OItems.LEAD_INGOT.get(), of().strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)));
    public static final DeferredBlock<Block> ELECTRUM_INGOT_PLACED = LBlocks.registerPlacedItem("electrum_ingot", () -> new IngotBlock(() -> OItems.ELECTRUM_INGOT.get(), ofFullCopy(OBlocks.ELECTRUM_BLOCK.get())));

    public static void register() {}
}
