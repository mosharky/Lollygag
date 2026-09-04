package momo.dev.lollygag.datagen.client;

import com.farcr.nomansland.common.definitions.ItemDefinition;
import momo.dev.lollygag.Lollygag;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static momo.dev.lollygag.registry.LItems.*;

public class LItemModelProvider extends ItemModelProvider {
    public LItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Lollygag.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basic(AMBROSIUM);
    }

    @SafeVarargs
    private void basic(ItemDefinition<? extends ItemLike>... items) {
        for (ItemDefinition<? extends ItemLike> item : items) {
            basicItem(item.get());
        }
    }
}
