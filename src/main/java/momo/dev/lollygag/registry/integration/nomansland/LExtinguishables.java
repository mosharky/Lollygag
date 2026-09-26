package momo.dev.lollygag.registry.integration.nomansland;

import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.block.torches.ExtinguishableBlockPairing;
import com.farcr.nomansland.common.registry.NMLRegistries;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LExtinguishables {
    private static boolean registered = false;

    public static final DeferredRegister<ExtinguishableBlockPairing> EXTINGUISHABLES = DeferredRegister.create(NMLRegistries.EXTINGUISHABLE_BLOCKS_KEY, NoMansLand.MODID);
    public static final Map<String, Supplier<ExtinguishableBlockPairing>> EXTINGUISHABLE_BLOCKS = new HashMap<>();

    public static <B extends Block> void register(String name, Holder<B> from, Holder<B> to) {
        EXTINGUISHABLE_BLOCKS.put(name, () -> new ExtinguishableBlockPairing(from.value(), to.value()));
    }

    public static void register() {
        if (!registered) {
            registered = true;

            for (var extinguishable : EXTINGUISHABLE_BLOCKS.entrySet()) {
                EXTINGUISHABLES.register(extinguishable.getKey(), extinguishable.getValue());
            }
        } else {
            throw new IllegalStateException("Unable to register Extinguishables; Already registered!");
        }
    }
}
