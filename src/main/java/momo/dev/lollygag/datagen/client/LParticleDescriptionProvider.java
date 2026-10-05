package momo.dev.lollygag.datagen.client;

import momo.dev.lollygag.LConfig;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.registry.LParticleTypes;
import momo.dev.lollygag.registry.integration.CitadelIntegration;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

public class LParticleDescriptionProvider extends ParticleDescriptionProvider {
    public LParticleDescriptionProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void addDescriptions() {
        sprite(LParticleTypes.CAELIC_FIRE_FLAME.get(), Lollygag.loc("caelic_fire_flame"));
        sprite(LParticleTypes.SMALL_CAELIC_FIRE_FLAME.get(), Lollygag.loc("caelic_fire_flame"));

        // Static lightning renders its own bolts, so only the breath needs sprites
        if (LConfig.bloviatorEnabled()) {
            spriteSet(CitadelIntegration.BLOVIATOR_BREATH.get(), ResourceLocation.withDefaultNamespace("generic"), 8, true);
        }
    }
}
