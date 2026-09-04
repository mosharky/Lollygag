package momo.dev.lollygag.datagen;

import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.datagen.client.LBlockStateProvider;
import momo.dev.lollygag.datagen.client.LItemModelProvider;
import momo.dev.lollygag.datagen.client.LLanguageProvider;
import momo.dev.lollygag.datagen.client.LParticleDescriptionProvider;
import momo.dev.lollygag.datagen.server.LBlockTagsProvider;
import momo.dev.lollygag.datagen.server.LItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Lollygag.MODID)
public class DataGenEvents {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        boolean server = event.includeServer();
        boolean client = event.includeClient();

        // models
        generator.addProvider(client, new LBlockStateProvider(output, existingFileHelper));
        generator.addProvider(client, new LItemModelProvider(output, existingFileHelper));
        // lang
        generator.addProvider(client, new LLanguageProvider(output));
        // particles
        generator.addProvider(client, new LParticleDescriptionProvider(output, existingFileHelper));
        // tags
        BlockTagsProvider blockTagsProvider = generator.addProvider(server, new LBlockTagsProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(server, new LItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
    }
}
