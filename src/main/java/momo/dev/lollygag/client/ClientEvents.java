package momo.dev.lollygag.client;

import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.integration.LAetherIntegration;
import momo.dev.lollygag.registry.integration.Mods;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;


@EventBusSubscriber(modid = Lollygag.MODID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        if (LAetherIntegration.Base.isLoaded()) {
            event.register((state, tintIndex) -> GrassColor.get(0.5D, 1),
                    LAetherIntegration.Base.TALL_SKYGRASS.get(),
                    LAetherIntegration.Base.SHORT_SKYGRASS.get(),
                    LAetherIntegration.Base.SKYGRASS_SPROUTS.get()
            );
        }

        event.register((stack, index) -> FoliageColor.get(0.5D, 1),
                // Leaves
                LBlocks.ASPEN_LEAVES.get(),
                LBlocks.BIRCH_LEAVES.get(),
                LBlocks.PEAR_LEAVES.get(),
                LBlocks.FLOWERING_PEAR_LEAVES.get()
        );
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        if (LAetherIntegration.Base.isLoaded()) {
            event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.get(0.5D, 1),
                    LAetherIntegration.Base.TALL_SKYGRASS.get(),
                    LAetherIntegration.Base.SHORT_SKYGRASS.get(),
                    LAetherIntegration.Base.SKYGRASS_SPROUTS.get()
            );
        }

        event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : GrassColor.get(0.5D, 1),
                LBlocks.ASPEN_LEAVES.get(),
                LBlocks.BIRCH_LEAVES.get(),
                LBlocks.PEAR_LEAVES.get(),
                LBlocks.FLOWERING_PEAR_LEAVES.get()
        );
    }
}
