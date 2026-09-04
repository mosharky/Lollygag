package momo.dev.lollygag.mixin.aether;

import com.aetherteam.aether.entity.passive.Moa;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Moa.class)
public interface MoaAccessor {
    @Accessor("eggTime")
    int lollygag$getEggTime();

    @Accessor("eggTime")
    void lollygag$setEggTime(int eggTime);
}
