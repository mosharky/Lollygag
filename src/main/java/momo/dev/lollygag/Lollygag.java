package momo.dev.lollygag;

import momo.dev.lollygag.registry.*;
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
        LExtinguishables.EXTINGUISHABLES.register(bus);
        LItems.ITEMS.register(bus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LFlammables.register();
        });
    }

    // probably temporary
    private void addBlockEntityBlocks(final BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.CAMPFIRE, LBlocks.CAELIC_CAMPFIRE.get());
    }

    public static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
