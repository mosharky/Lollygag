package momo.dev.lollygag.client;

import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.integration.BFIntegration;
import momo.dev.lollygag.registry.integration.Mods;
import momo.dev.lollygag.registry.integration.aether.AetherBase;
import momo.dev.lollygag.registry.integration.aether.AetherNML;
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
        if (Mods.AETHER.isLoaded()) {
            event.register((state, tintIndex) -> GrassColor.get(0.5D, 1),
                    AetherBase.TALL_SKYGRASS.get(),
                    AetherBase.SHORT_SKYGRASS.get()
            );

            if (Mods.NOMANSLAND.isLoaded()) {
                event.register((state, tintIndex) -> GrassColor.get(0.5D, 1),
                        AetherNML.SKYGRASS_SPROUTS.get()
                );
            }
        }

        event.register((stack, index) -> FoliageColor.get(0.5D, 1),
                // Leaves
                BFIntegration.ASPEN_LEAVES.get(),
                BFIntegration.BIRCH_LEAVES.get(),
                BFIntegration.PEAR_LEAVES.get(),
                BFIntegration.FLOWERING_PEAR_LEAVES.get()
        );
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        if (Mods.AETHER.isLoaded()) {
            event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.get(0.5D, 1),
                    AetherBase.TALL_SKYGRASS.get(),
                    AetherBase.SHORT_SKYGRASS.get()
            );
            if (Mods.NOMANSLAND.isLoaded()) {
                event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.get(0.5D, 1),
                        AetherNML.SKYGRASS_SPROUTS.get()
                );
            }
        }

        event.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : GrassColor.get(0.5D, 1),
                BFIntegration.ASPEN_LEAVES.get(),
                BFIntegration.BIRCH_LEAVES.get(),
                BFIntegration.PEAR_LEAVES.get(),
                BFIntegration.FLOWERING_PEAR_LEAVES.get()
        );
    }
}
