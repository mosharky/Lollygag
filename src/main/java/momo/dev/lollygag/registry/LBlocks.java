package momo.dev.lollygag.registry;

import com.farcr.nomansland.common.block.GroundPickupBlock;
import com.farcr.nomansland.common.registry.blocks.NMLBlocks;
import com.teamabnormals.caverns_and_chasms.common.block.CoalBlock;
import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.block.*;
import momo.dev.lollygag.common.block.CoalBlockFixed;
import momo.dev.lollygag.registry.integration.aether.AetherBase;
import momo.dev.lollygag.registry.worldgen.LTreeGrowers;
import net.hecco.bountifulfares.definition.block.custom.FruitLeavesBlock;
import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.hecco.bountifulfares.definition.block.custom.StrippedFruitLogBlock;
import net.hecco.bountifulfares.registry.content.BFBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class LBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lollygag.MODID);
    public static List<DeferredBlock<?>> BLOCKS_REGISTERED = new ArrayList<>();

    public static class LProperties {
        public static BlockBehaviour.Properties placedCoal(int baseLight) {
            return of().mapColor(MapColor.COLOR_BLACK).strength(2.0F, 6.0F).requiresCorrectToolForDrops().lightLevel(placedCoalLight(baseLight)).noOcclusion().pushReaction(PushReaction.DESTROY);
        }

        public static ToIntFunction<BlockState> placedCoalLight(int base) {
            return state -> {
                boolean warm = state.getValue(CoalBlock.WARM);
                boolean lit = state.getValue(CoalBlock.LIT);
                return (warm || lit) ? base + (lit ? 4 : 2) + state.getValue(CoalBlock.COAL) : 0;
            };
        }
    }

    // register stuff here

    // Helpers (from NMLBlocks)
    public static <T extends Block> DeferredBlock<T> registerNoItem(String name, Supplier<T> block) {
        DeferredBlock<T> deferred = BLOCKS.register(name, block);
        BLOCKS_REGISTERED.add(deferred);
        return deferred;
    }

    public static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> block) {
        DeferredBlock<T> deferred = registerNoItem(name, block);
        LItems.register(name, () -> new BlockItem(deferred.get(), new Item.Properties()));
        return deferred;
    }

    public static <T extends Block> DeferredBlock<T> registerPlacedItem(String name, Supplier<T> block) {
        DeferredBlock<T> deferred = registerNoItem(name, block);
        LItems.register(name + "_placed", () -> new BlockItem(deferred.get(), new Item.Properties()));
        return deferred;
    }
}

