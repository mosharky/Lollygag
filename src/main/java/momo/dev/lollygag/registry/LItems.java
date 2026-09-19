package momo.dev.lollygag.registry;

import com.farcr.nomansland.common.definitions.ItemDefinition;
import com.google.common.collect.Sets;
import momo.dev.lollygag.Lollygag;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Supplier;

public class LItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Lollygag.MODID);
    public static List<DeferredItem<?>> ITEMS_REGISTERED = new ArrayList<>();
    public static LinkedHashSet<DeferredItem<?>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();



    // Helpers (from NMLItems)
    public static <T extends Item> DeferredItem<T> register(String name, Supplier<T> item) {
        DeferredItem<T> deferred = ITEMS.register(name, item);
        ITEMS_REGISTERED.add(deferred);
        return deferred;
    }
}
