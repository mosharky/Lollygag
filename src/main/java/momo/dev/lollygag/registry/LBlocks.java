package momo.dev.lollygag.registry;

import com.mojang.datafixers.util.Pair;
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
    // Each branch paired with the log it's derived from
    public static List<Pair<DeferredBlock<Block>, Supplier<Block>>> BRANCHES = new ArrayList<>();


    // Branches
    public static final DeferredBlock<Block> OAK_BRANCH = registerBranch("oak", () -> Blocks.OAK_LOG, () -> Blocks.STRIPPED_OAK_LOG);
    public static final DeferredBlock<Block> SPRUCE_BRANCH = registerBranch("spruce", () -> Blocks.SPRUCE_LOG, () -> Blocks.STRIPPED_SPRUCE_LOG);
    public static final DeferredBlock<Block> BIRCH_BRANCH = registerBranch("birch", () -> Blocks.BIRCH_LOG, () -> Blocks.STRIPPED_BIRCH_LOG);
    public static final DeferredBlock<Block> JUNGLE_BRANCH = registerBranch("jungle", () -> Blocks.JUNGLE_LOG, () -> Blocks.STRIPPED_JUNGLE_LOG);
    public static final DeferredBlock<Block> ACACIA_BRANCH = registerBranch("acacia", () -> Blocks.ACACIA_LOG, () -> Blocks.STRIPPED_ACACIA_LOG);
    public static final DeferredBlock<Block> DARK_OAK_BRANCH = registerBranch("dark_oak", () -> Blocks.DARK_OAK_LOG, () -> Blocks.STRIPPED_DARK_OAK_LOG);
    public static final DeferredBlock<Block> MANGROVE_BRANCH = registerBranch("mangrove", () -> Blocks.MANGROVE_LOG, () -> Blocks.STRIPPED_MANGROVE_LOG);
    public static final DeferredBlock<Block> CHERRY_BRANCH = registerBranch("cherry", () -> Blocks.CHERRY_LOG, () -> Blocks.STRIPPED_CHERRY_LOG);
    public static final DeferredBlock<Block> CRIMSON_BRANCH = registerBranch("crimson", () -> Blocks.CRIMSON_STEM, () -> Blocks.STRIPPED_CRIMSON_STEM);
    public static final DeferredBlock<Block> WARPED_BRANCH = registerBranch("warped", () -> Blocks.WARPED_STEM, () -> Blocks.STRIPPED_WARPED_STEM);
    // non wood
    public static final DeferredBlock<Block> MUSHROOM_BRANCH = registerBranch("mushroom", () -> Blocks.MUSHROOM_STEM);

    // Registers "<wood>_branch" and "stripped_<wood>_branch", returning the unstripped branch
    public static DeferredBlock<Block> registerBranch(String wood, Supplier<Block> log, Supplier<Block> strippedLog) {
        registerBranch("stripped_" + wood, strippedLog);
        return registerBranch(wood, log);
    }

    // For logs without a stripped variant of their own
    public static DeferredBlock<Block> registerBranch(String wood, Supplier<Block> log) {
        DeferredBlock<Block> branch = register(wood + "_branch", () -> new BranchBlock(ofFullCopy(log.get()).noOcclusion()));
        BRANCHES.add(Pair.of(branch, log));
        return branch;
    }

    // The wood type a branch was registered with, e.g. "oak" for both oak_branch and stripped_oak_branch
    public static String branchWood(DeferredBlock<?> branch) {
        return branch.getId().getPath().replaceFirst("^stripped_", "").replaceFirst("_branch$", "");
    }


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

