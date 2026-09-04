package momo.dev.lollygag.registry;

import com.aetherteam.aether.block.AetherBlocks;
import com.farcr.nomansland.NoMansLand;
import com.farcr.nomansland.common.block.torches.ExtinguishableBlockPairing;
import com.farcr.nomansland.common.registry.NMLRegistries;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class LExtinguishables {
    public static final DeferredRegister<ExtinguishableBlockPairing> EXTINGUISHABLES = DeferredRegister.create(NMLRegistries.EXTINGUISHABLE_BLOCKS_KEY, NoMansLand.MODID);
    private static boolean registered = false;

    public static <B extends Block> DeferredHolder<ExtinguishableBlockPairing, ExtinguishableBlockPairing> register(String name, Holder<B> from, Holder<B> to) {
        return EXTINGUISHABLES.register(name, () -> new ExtinguishableBlockPairing(from.value(), to.value()));
    }

    static {
        register();
    }

    public static void register() {
        if (!registered) {
            registered = true;

            register("caelic_torch", AetherBlocks.AMBROSIUM_TORCH, LBlocks.EXTINGUISHED_CAELIC_TORCH.getDelegate());
            register("caelic_torch_wall", AetherBlocks.AMBROSIUM_WALL_TORCH, LBlocks.EXTINGUISHED_CAELIC_WALL_TORCH.getDelegate());

            register("sconce_caelic_torch", LBlocks.SCONCE_CAELIC_TORCH.getDelegate(), LBlocks.EXTINGUISHED_SCONCE_CAELIC_TORCH.getDelegate());
            register("sconce_caelic_wall_torch", LBlocks.SCONCE_CAELIC_WALL_TORCH.getDelegate(), LBlocks.EXTINGUISHED_SCONCE_CAELIC_WALL_TORCH.getDelegate());
        } else {
            throw new IllegalStateException("Unable to register Extinguishables; Already registered!");
        }
    }
}
