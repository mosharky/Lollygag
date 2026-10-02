package momo.dev.lollygag.mixin;

import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public final class LollygagMixinPlugin implements IMixinConfigPlugin {
    // Use raw mod IDs here rather than the Mods enum: mixin plugins run very early,
    // and touching classes that reference Minecraft types can load them before mixins apply.
    private static final String MIXIN_PACKAGE = "momo.dev.lollygag.mixin.";
    private static final Logger LOGGER = LoggerFactory.getLogger("lollygag");

    // Toggleable mixins, read from config/lollygag.mixins.properties before any mixins apply.
    private static final List<Option> OPTIONS = List.of(
            new Option("NMLCavesInBlueprintSlices", "blueprint.MultiNoiseModdedBiomeProviderMixin",
                    "Lets No Man's Land's generic cave biomes generate underneath biomes placed by Blueprint's modded biome slices.\n"
                            + "Only has an effect when Blueprint, No Man's Land, and a mod that uses Blueprint to place its biomes are installed.")
    );

    private Set<String> presentMods;
    private Map<String, Boolean> enabledMixins;

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!enabledMixins.getOrDefault(mixinClassName, true)) {
            return false;
        }
        if (mixinClassName.startsWith(MIXIN_PACKAGE + "aether.")) {
            return presentMods.contains("aether");
        }
        if (mixinClassName.startsWith(MIXIN_PACKAGE + "bountifulfares.")) {
            return presentMods.contains("bountifulfares");
        }
        if (mixinClassName.equals(MIXIN_PACKAGE + "blueprint.MultiNoiseModdedBiomeProviderMixin")) {
            return presentMods.containsAll(Set.of("blueprint", "nomansland", "biolith"));
        }
        return true;
    }

    @Override
    public void onLoad(String mixinPackage) {
        this.presentMods = FMLLoader.getLoadingModList().getMods().stream()
                .map(ModInfo::getModId)
                .collect(Collectors.toUnmodifiableSet());
        this.enabledMixins = loadOptions(FMLPaths.CONFIGDIR.get().resolve("lollygag.mixins.properties"));
    }

    private static Map<String, Boolean> loadOptions(Path path) {
        Properties properties = new Properties();
        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path)) {
                properties.load(reader);
            } catch (IOException e) {
                LOGGER.error("Failed to read {}, using defaults", path, e);
            }
        }

        // Rewrite the file if any options are missing so new options show up for users.
        if (OPTIONS.stream().anyMatch(option -> !properties.containsKey(option.key()))) {
            try (Writer writer = Files.newBufferedWriter(path)) {
                writer.write("# Lollygag mixin toggles. Changes take effect after restarting the game.\n");
                for (Option option : OPTIONS) {
                    writer.write("\n# " + option.comment().replace("\n", "\n# ") + "\n");
                    writer.write(option.key() + "=" + properties.getProperty(option.key(), "true") + "\n");
                }
            } catch (IOException e) {
                LOGGER.error("Failed to write {}", path, e);
            }
        }

        return OPTIONS.stream().collect(Collectors.toUnmodifiableMap(
                option -> MIXIN_PACKAGE + option.mixin(),
                option -> Boolean.parseBoolean(properties.getProperty(option.key(), "true").trim())));
    }

    private record Option(String key, String mixin, String comment) {
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
