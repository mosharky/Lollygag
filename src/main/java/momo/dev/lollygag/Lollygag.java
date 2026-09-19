package momo.dev.lollygag;

import momo.dev.lollygag.registry.*;
import momo.dev.lollygag.registry.integration.LAetherIntegration;
import momo.dev.lollygag.registry.integration.Mods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Lollygag.MODID)
public class Lollygag {
    public static final String MODID = "lollygag";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Lollygag(IEventBus bus, ModContainer container) {
        bus.addListener(this::commonSetup);
        bus.addListener(this::addBlockEntityBlocks);

        LParticleTypes.PARTICLE_TYPES.register(bus);

        LBlocks.BLOCKS.register(bus);
        LItems.ITEMS.register(bus);

        if (Mods.AETHER.isLoaded()) {
            LAetherIntegration.Base.register();
            if (Mods.CAVERNS_AND_CHASMS.isLoaded()) LAetherIntegration.CavernsAndChasms.register();
            if (Mods.AUTUMNITY.isLoaded()) LAetherIntegration.Autumnity.register();
            if (Mods.BUZZIER_BEES.isLoaded()) LAetherIntegration.BuzzierBees.register();
            if (Mods.NOMANSLAND.isLoaded()) LAetherIntegration.NoMansLand.register();
            if (Mods.INCUBATION.isLoaded()) LAetherIntegration.Incubation.register();
        }

        LExtinguishables.EXTINGUISHABLES.register(bus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LFlammables.register();
        });
    }

    // probably temporary
    private void addBlockEntityBlocks(final BlockEntityTypeAddBlocksEvent event) {
        if (LAetherIntegration.Base.isLoaded()) {
            event.modify(BlockEntityType.CAMPFIRE, LAetherIntegration.Base.CAELIC_CAMPFIRE.get());
        }
    }

    public static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
