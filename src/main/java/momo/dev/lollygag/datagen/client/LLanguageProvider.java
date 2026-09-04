package momo.dev.lollygag.datagen.client;

import com.farcr.nomansland.common.definitions.BlockDefinition;
import com.farcr.nomansland.common.definitions.ItemDefinition;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.LItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static momo.dev.lollygag.registry.LBlocks.*;

public class LLanguageProvider extends LanguageProvider {
    public LLanguageProvider(PackOutput output) {
        super(output, Lollygag.MODID, "en_us");
    }


    @Override
    protected void addTranslations() {
        for (BlockDefinition<?> definition : LBlocks.BLOCK_DEFINITIONS) {
            if (!definition.hasCustomLang()) {
                add(definition.langKey(), definition.langName());
            }
        }

        for (ItemDefinition<?> definition : LItems.ITEM_DEFINITIONS) {
            if (!definition.hasCustomLang() && !definition.isBlockItem()) {
                add(definition.langKey(), definition.langName());
            }
        }

        addBlock(CAELIC_JACK_O_LANTERN, "Caelic Jack o'Lantern");
        addBlock(LARGE_CAELIC_JACK_O_LANTERN, "Large Caelic Jack o'Lantern");
    }
}
