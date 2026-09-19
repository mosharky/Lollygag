package momo.dev.lollygag.datagen.client;

import com.farcr.nomansland.common.definitions.ItemDefinition;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.LItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;

import static momo.dev.lollygag.registry.integration.LAetherIntegration.Autumnity.*;

public class LLanguageProvider extends LanguageProvider {
    public LLanguageProvider(PackOutput output) {
        super(output, Lollygag.MODID, "en_us");
    }

    // from NML
    private String getLangKey(DeferredHolder<?, ?> def) {
        return def.getRegisteredName().replaceAll(":", ".");
    }

    private String getLangName(DeferredHolder<?, ?> def) {
        String processed = def.getRegisteredName().split(":")[1].replace("_", " ");

        List<String> nonCapital = List.of("of", "and", "with");

        String[] words = processed.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                if (!nonCapital.contains(word)) result.append(Character.toUpperCase(word.charAt(0)));
                else result.append(word.charAt(0));
                result.append(word.substring(1)).append(" ");
            }
        }

        return result.toString().trim();
    }

    @Override
    protected void addTranslations() {
        for (DeferredBlock<?> def : LBlocks.BLOCKS_REGISTERED) {
            // if (!def.hasCustomLang()) {
            //     add(def.langKey(), def.langName());
            // }
        }

        for (DeferredItem<?> def : LItems.ITEMS_REGISTERED) {
//            if (!def.hasCustomLang() && !def.isBlockItem()) {
//                add(def.langKey(), def.langName());
//            }
        }

        addBlock(CAELIC_JACK_O_LANTERN, "Caelic Jack o'Lantern");
        addBlock(LARGE_CAELIC_JACK_O_LANTERN, "Large Caelic Jack o'Lantern");
    }
}
