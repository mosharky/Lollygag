package momo.dev.lollygag;

import momo.dev.lollygag.registry.integration.Mods;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.data.loading.DatagenModLoader;

// Registered as a STARTUP config so it's loaded before registration, letting it toggle registry content
public final class LConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue BLOVIATOR = BUILDER
            .comment("Adds the Bloviator from Cloud Storage. Only has an effect when Citadel is installed.")
            .define("Bloviator", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    // Always enabled during datagen so its assets get generated
    public static boolean bloviatorEnabled() {
        return Mods.CITADEL.isLoaded() && (BLOVIATOR.get() || DatagenModLoader.isRunningDataGen());
    }
}
