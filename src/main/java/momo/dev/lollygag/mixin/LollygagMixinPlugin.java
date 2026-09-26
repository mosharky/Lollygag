package momo.dev.lollygag.mixin;

import momo.dev.lollygag.registry.integration.Mods;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class LollygagMixinPlugin implements IMixinConfigPlugin {
    private Set<String> presentMods;

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.startsWith("momo.dev.lollygag.mixin.aether.")) {
            return presentMods.contains(Mods.AETHER.name());
        }
        if (mixinClassName.startsWith("momo.dev.lollygag.mixin.bountifulfares.")) {
            return presentMods.contains(Mods.BOUNTIFULFARES.name());
        }
        return true;
    }

    @Override
    public void onLoad(String mixinPackage) {
        this.presentMods = FMLLoader.getLoadingModList().getMods().stream()
                .map(ModInfo::getModId)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
