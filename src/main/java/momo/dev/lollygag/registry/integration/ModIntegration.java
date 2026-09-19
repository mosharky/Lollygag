package momo.dev.lollygag.registry.integration;

public class ModIntegration {
    private static Boolean loaded = false;

    public static Boolean isLoaded() {
        return loaded;
    }

    public static void register() {
        loaded = true;
    }
}
