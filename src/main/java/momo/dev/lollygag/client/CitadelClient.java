package momo.dev.lollygag.client;

import momo.dev.lollygag.client.particle.BloviatorBreathParticle;
import momo.dev.lollygag.client.particle.StaticLightningParticle;
import momo.dev.lollygag.client.renderer.BloviatorRenderer;
import momo.dev.lollygag.registry.integration.CitadelIntegration;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

// Kept separate from ClientEvents so Citadel classes are only loaded when the Bloviator is enabled
public class CitadelClient {
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CitadelIntegration.BLOVIATOR.get(), BloviatorRenderer::new);
    }

    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(CitadelIntegration.BLOVIATOR_BREATH.get(), BloviatorBreathParticle.Provider::new);
        event.registerSpecial(CitadelIntegration.STATIC_LIGHTNING.get(), new StaticLightningParticle.Provider());
    }
}
