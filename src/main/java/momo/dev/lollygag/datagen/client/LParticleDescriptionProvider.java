package momo.dev.lollygag.datagen.client;

import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.registry.LParticleTypes;
import net.minecraft.data.PackOutput;
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
    }
}
