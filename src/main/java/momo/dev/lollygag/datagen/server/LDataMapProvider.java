package momo.dev.lollygag.datagen.server;

import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import com.teamabnormals.caverns_and_chasms.core.other.CCDataMaps;
import momo.dev.lollygag.common.block.CoalBlockFixed;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.concurrent.CompletableFuture;

import static momo.dev.lollygag.registry.LBlocks.*;

public class LDataMapProvider extends DataMapProvider {
    public LDataMapProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider lookupProvider) {
        Builder<CCDataMaps.PlaceableItem, Item> placeableItems = builder(CCDataMaps.PLACEABLE_ITEMS);

        for (DeferredBlock<?> def : BLOCKS_REGISTERED) {
            Block block = def.get();
            // placed blocks return their source item from asItem(), the placed item is "<block>_placed"
            if (block instanceof IngotBlock || block instanceof CoalBlockFixed)
                placeableItems.add(block.asItem().builtInRegistryHolder(), new CCDataMaps.PlaceableItem(block.builtInRegistryHolder(), lookupProvider), false);
        }
    }
}
