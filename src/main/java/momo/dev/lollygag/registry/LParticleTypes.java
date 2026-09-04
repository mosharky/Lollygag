package momo.dev.lollygag.registry;

import momo.dev.lollygag.Lollygag;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, Lollygag.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CAELIC_FIRE_FLAME = register(false, "caelic_fire_flame");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMALL_CAELIC_FIRE_FLAME = register(false, "small_caelic_fire_flame");

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(boolean alwaysShow, String name) {
        return PARTICLE_TYPES.register(name, () -> new SimpleParticleType(alwaysShow));
    }


    @EventBusSubscriber(modid = Lollygag.MODID, value = Dist.CLIENT)
    public static class RegisterParticles {
        @SubscribeEvent
        public static void registerParticleTypes(RegisterParticleProvidersEvent e) {
            e.registerSpriteSet(CAELIC_FIRE_FLAME.get(), FlameParticle.Provider::new);
            e.registerSpriteSet(SMALL_CAELIC_FIRE_FLAME.get(), FlameParticle.SmallFlameProvider::new);
        }
    }
}
