package momo.dev.lollygag.datagen.client;

import momo.dev.lollygag.Lollygag;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import static momo.dev.lollygag.registry.integration.aether.AetherBase.*;


public class LItemModelProvider extends ItemModelProvider {
    public LItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Lollygag.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basic(AMBROSIUM_ITEM);
    }

    @SafeVarargs
    private void basic(DeferredItem<? extends ItemLike>... items) {
        for (DeferredItem<? extends ItemLike> item : items) {
            basicItem(item.get());
        }
    }
}
