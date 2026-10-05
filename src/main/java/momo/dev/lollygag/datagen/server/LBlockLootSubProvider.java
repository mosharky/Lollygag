package momo.dev.lollygag.datagen.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.LinkedHashSet;
import java.util.Set;

import static momo.dev.lollygag.registry.LBlocks.*;

public class LBlockLootSubProvider extends BlockLootSubProvider {
    // Only blocks given a table here are validated, so blocks without one yet don't fail datagen
    private final Set<Block> knownBlocks = new LinkedHashSet<>();

    public LBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        for (Branch branch : BRANCHES) {
            for (DeferredBlock<Block> block : branch.blocks()) {
                dropSelf(block.get());
            }
        }
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        knownBlocks.add(block);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
}
