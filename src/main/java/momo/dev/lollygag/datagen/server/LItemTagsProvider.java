package momo.dev.lollygag.datagen.server;

import com.farcr.nomansland.common.definitions.BlockDefinition;
import com.teamabnormals.buzzier_bees.common.block.SpecialCandleBlock;
import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import com.teamabnormals.caverns_and_chasms.core.other.tags.CCItemTags;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.block.CoalBlockFixed;
import momo.dev.lollygag.registry.LTags;
import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.hecco.bountifulfares.definition.block.custom.StrippedFruitLogBlock;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.concurrent.CompletableFuture;

import static momo.dev.lollygag.registry.LBlocks.*;
import static momo.dev.lollygag.registry.integration.aether.AetherBase.*;
import static momo.dev.lollygag.registry.integration.aether.AetherCnC.*;
import static momo.dev.lollygag.registry.integration.aether.AetherAutumnity.*;
import static momo.dev.lollygag.registry.integration.aether.AetherBB.*;
import static momo.dev.lollygag.registry.integration.aether.AetherNML.*;
import static momo.dev.lollygag.registry.integration.aether.AetherIncubation.*;
import static momo.dev.lollygag.registry.integration.BFIntegration.*;
import static momo.dev.lollygag.registry.integration.nomansland.NMLIntegration.*;
import static momo.dev.lollygag.registry.integration.OreganizedIntegration.*;

public class LItemTagsProvider extends ItemTagsProvider {
    public LItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Lollygag.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        for (DeferredBlock<?> def : BLOCKS_REGISTERED) {
            Block block = def.get();
            if (block instanceof SaplingBlock) tag(ItemTags.SAPLINGS, def);
            if (block instanceof LeavesBlock) tag(ItemTags.LEAVES, def);
            if (block instanceof FruitLogBlock) tag(ItemTags.LOGS_THAT_BURN, def);
            if (block instanceof StrippedFruitLogBlock) tag(ItemTags.LOGS_THAT_BURN, def);
            if (block instanceof IngotBlock) tag(CCItemTags.PLACEABLE_ITEMS, def);  // TODO: will need to change to datamap when CNC updates
            if (block instanceof CoalBlockFixed) tag(CCItemTags.PLACEABLE_ITEMS, def);  // TODO: will need to change to datamap when CNC updates
            if (block instanceof SpecialCandleBlock) tag(ItemTags.CANDLES, def);
        }

        copy(LTags.BLOCKS.ASPEN_LOGS, LTags.ITEMS.ASPEN_LOGS);
        copy(LTags.BLOCKS.BIRCH_LOGS, LTags.ITEMS.BIRCH_LOGS);
        copy(LTags.BLOCKS.PEAR_LOGS, LTags.ITEMS.PEAR_LOGS);
        copy(LTags.BLOCKS.PEAR_LEAVES, LTags.ITEMS.PEAR_LEAVES);

        // vanilla tags
        tag(ItemTags.FLOWERS, FLOWERING_PEAR_LEAVES);
    }

    @SuppressWarnings("unchecked")
    private void tag(TagKey<Item> tag, Object... values) {
        IntrinsicTagAppender<Item> itemTag = tag(tag);
        for (Object value : values) {
            if (value instanceof DeferredBlock<?> def) itemTag.add(def.get().asItem());
            else if (value instanceof DeferredItem<?> deferredItem) itemTag.add(deferredItem.get());
            else if (value instanceof TagKey<?> key) itemTag.addTag((TagKey<Item>) key);
            else throw new IllegalArgumentException("Unsupported tag entry: " + value);
        }
    }
}
