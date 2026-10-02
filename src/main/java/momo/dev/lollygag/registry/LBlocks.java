package momo.dev.lollygag.registry;

import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.block.*;
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

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class LBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lollygag.MODID);
    public static List<DeferredBlock<?>> BLOCKS_REGISTERED = new ArrayList<>();


    public static final DeferredBlock<Block> OAK_BRANCH = register("oak_branch", () -> new BranchBlock(ofFullCopy(Blocks.OAK_LOG).noOcclusion()));
    public static final DeferredBlock<Block> STRIPPED_OAK_BRANCH = register("stripped_oak_branch", () -> new BranchBlock(ofFullCopy(Blocks.STRIPPED_OAK_LOG).noOcclusion()));


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

