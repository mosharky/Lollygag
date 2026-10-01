package momo.dev.lollygag.mixin;

import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class LollygagMixinPlugin implements IMixinConfigPlugin {
    // Use raw mod IDs here rather than the Mods enum: mixin plugins run very early,
    // and touching classes that reference Minecraft types can load them before mixins apply.
    private static final String MIXIN_PACKAGE = "momo.dev.lollygag.mixin.";

    private Set<String> presentMods;

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.startsWith(MIXIN_PACKAGE + "aether.")) {
            return presentMods.contains("aether");
        }
        if (mixinClassName.startsWith(MIXIN_PACKAGE + "bountifulfares.")) {
            return presentMods.contains("bountifulfares");
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
