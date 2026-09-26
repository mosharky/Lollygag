package momo.dev.lollygag.datagen.client;

import com.farcr.nomansland.common.block.torches.ExtinguishedTorchBlock;
import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.block.CoalBlockFixed;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.LItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallTorchBlock;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static momo.dev.lollygag.registry.integration.aether.AetherAutumnity.*;

public class LLanguageProvider extends LanguageProvider {
    private final HashSet<String> DONE = new HashSet<>();
    private static final Set<Class<?>> BLACKLISTED_CLASSES = Set.of(
            CoalBlockFixed.class,
            IngotBlock.class,
            ExtinguishedTorchBlock.class,
            WallTorchBlock.class
    );

    public LLanguageProvider(PackOutput output) {
        super(output, Lollygag.MODID, "en_us");
    }

    private String getPreLangKey(DeferredHolder<?, ?> def) {
        return def.getRegisteredName().replaceAll(":", ".");
    }
    // from NML
    private String getLangKey(DeferredHolder<?, ?> def) {
        String prepend = def.get() instanceof Block ? "block." : "item.";
        return prepend + getPreLangKey(def);
    }

    // from NML
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
        this.add(CAELIC_JACK_O_LANTERN, "Caelic Jack o'Lantern");
        this.add(LARGE_CAELIC_JACK_O_LANTERN, "Large Caelic Jack o'Lantern");

        for (DeferredBlock<?> def : LBlocks.BLOCKS_REGISTERED) this.add(def);
        for (DeferredItem<?> def : LItems.ITEMS_REGISTERED) this.add(def);
    }

    // Automatically getting the assumed name
    private void add(DeferredHolder<?, ?> def) {
        if (BLACKLISTED_CLASSES.stream().anyMatch(type -> type.isInstance(def.get()))) return;
        String key = getLangKey(def);
        if (key.endsWith("_placed")) return;  // prevent placed ingots
        else if (key.endsWith("_plant")) return;  // prevent 'plant' blocks
        this.add(def, getLangName(def));
    }

    private void add(DeferredHolder<?, ?> def, String name) {
        if (!DONE.contains(getPreLangKey(def))) {
            add(getLangKey(def), name);
            DONE.add(getPreLangKey(def));
        }
    }
}
