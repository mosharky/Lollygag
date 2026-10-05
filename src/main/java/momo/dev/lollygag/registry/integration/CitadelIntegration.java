package momo.dev.lollygag.registry.integration;

import momo.dev.lollygag.common.entity.BloviatorEntity;
import momo.dev.lollygag.registry.LEntityTypes;
import momo.dev.lollygag.registry.LItems;
import momo.dev.lollygag.registry.LParticleTypes;
import momo.dev.lollygag.registry.LSoundEvents;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

// Bloviator, ported from Cloud Storage. Only registered when Citadel is loaded and it's enabled in the config
public class CitadelIntegration {
    public static final DeferredHolder<EntityType<?>, EntityType<BloviatorEntity>> BLOVIATOR = LEntityTypes.register("bloviator",
            EntityType.Builder.of(BloviatorEntity::new, MobCategory.MONSTER).sized(2F, 1.3F));

    public static final DeferredItem<Item> BLOVIATOR_SPAWN_EGG = LItems.register("bloviator_spawn_egg",
            () -> new DeferredSpawnEggItem(BLOVIATOR, 0xDFF3F7, 0x24AFFF, new Item.Properties()));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOVIATOR_BREATH = LParticleTypes.register(false, "bloviator_breath");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> STATIC_LIGHTNING = LParticleTypes.register(false, "static_lightning");

    public static final DeferredHolder<SoundEvent, SoundEvent> BLOVIATOR_IDLE = LSoundEvents.register("entity.bloviator.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLOVIATOR_HURT = LSoundEvents.register("entity.bloviator.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLOVIATOR_BLOW = LSoundEvents.register("entity.bloviator.blow");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLOVIATOR_LIGHTNING = LSoundEvents.register("entity.bloviator.lightning");

    public static class Events {
        @SubscribeEvent
        public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            event.put(BLOVIATOR.get(), BloviatorEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
            if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
                event.accept(BLOVIATOR_SPAWN_EGG);
            }
        }
    }

    public static void register() {}
}
