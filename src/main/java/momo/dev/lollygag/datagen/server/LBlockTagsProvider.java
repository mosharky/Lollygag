package momo.dev.lollygag.datagen.server;

import com.aetherteam.aether.block.AetherBlocks;
import com.farcr.nomansland.common.definitions.BlockDefinition;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleBlock;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleCakeBlock;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.registry.LTags;
import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.hecco.bountifulfares.definition.block.custom.StrippedFruitLogBlock;
import net.hecco.bountifulfares.registry.tags.BFBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static momo.dev.lollygag.registry.LBlocks.*;

public class LBlockTagsProvider extends BlockTagsProvider {
    public LBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Lollygag.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (BlockDefinition<?> def : BLOCK_DEFINITIONS) {
            Block block = def.get();
            if (block instanceof SaplingBlock) tag(BlockTags.SAPLINGS, def);
            if (block instanceof LeavesBlock) tag(BlockTags.LEAVES, def);
            if (block instanceof FlowerPotBlock) tag(BlockTags.FLOWER_POTS, def);
            if (block instanceof SpecialCandleBlock) tag(BlockTags.CANDLES, def);
            if (block instanceof SpecialCandleCakeBlock) tag(BlockTags.CANDLE_CAKES, def);
            if (block instanceof FruitLogBlock) {
                tag(BFBlockTags.IGNORE_PARTICLE_TINT, def);
                tag(BlockTags.LOGS_THAT_BURN, def);
                tag(BlockTags.MINEABLE_WITH_AXE, def);
            }
            if (block instanceof StrippedFruitLogBlock) {
                tag(BlockTags.LOGS_THAT_BURN, def);
                tag(BlockTags.MINEABLE_WITH_AXE, def);
            }
        }

        tag(LTags.BLOCKS.ASPEN_LOGS, ASPEN_LOG, STRIPPED_ASPEN_LOG, ASPEN_WOOD, STRIPPED_ASPEN_WOOD);
        tag(LTags.BLOCKS.BIRCH_LOGS, BIRCH_LOG, STRIPPED_BIRCH_LOG, BIRCH_WOOD, STRIPPED_BIRCH_WOOD);
        tag(LTags.BLOCKS.PEAR_LOGS, PEAR_LOG, STRIPPED_PEAR_LOG, PEAR_WOOD, STRIPPED_PEAR_WOOD);
        tag(LTags.BLOCKS.PEAR_LEAVES, FLOWERING_PEAR_LEAVES, PEAR_LEAVES);
        tag(LTags.BLOCKS.CAN_HANG_ON.PEAR, LTags.BLOCKS.PEAR_LEAVES);

        tag(LTags.BLOCKS.CAELIC_FIRE_BASE_BLOCKS, AetherBlocks.AETHER_DIRT, AetherBlocks.AETHER_GRASS_BLOCK, AetherBlocks.HOLYSTONE);

        // vanilla tags
        tag(BlockTags.FLOWERS, FLOWERING_PEAR_LEAVES);
    }

    @SuppressWarnings("unchecked")
    private void tag(TagKey<Block> tag, Object... values) {
        IntrinsicTagAppender<Block> blockTag = tag(tag);
        for (Object value : values) {
            if (value instanceof BlockDefinition<?> def) blockTag.add(def.block());
            else if (value instanceof DeferredBlock<?> deferredBlock) blockTag.add(deferredBlock.get());
            else if (value instanceof TagKey<?> key) blockTag.addTag((TagKey<Block>) key);
            else throw new IllegalArgumentException("Unsupported tag entry: " + value);
        }
    }
}
