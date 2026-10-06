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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class LBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Lollygag.MODID);
    public static List<DeferredBlock<?>> BLOCKS_REGISTERED = new ArrayList<>();
    public static List<Branch> BRANCHES = new ArrayList<>();


    // Leaves
    public static final DeferredBlock<Block> OAK_LEAVES = register("oak_leaves", () -> new DroopyLeavesBlock(ofFullCopy(Blocks.OAK_LEAVES)));

    // Branches
    public static final Branch OAK_BRANCH = registerBranch("oak", () -> Blocks.OAK_LOG, () -> Blocks.STRIPPED_OAK_LOG);
    public static final Branch SPRUCE_BRANCH = registerBranch("spruce", () -> Blocks.SPRUCE_LOG, () -> Blocks.STRIPPED_SPRUCE_LOG);
    public static final Branch BIRCH_BRANCH = registerBranch("birch", () -> Blocks.BIRCH_LOG, () -> Blocks.STRIPPED_BIRCH_LOG);
    public static final Branch JUNGLE_BRANCH = registerBranch("jungle", () -> Blocks.JUNGLE_LOG, () -> Blocks.STRIPPED_JUNGLE_LOG);
    public static final Branch ACACIA_BRANCH = registerBranch("acacia", () -> Blocks.ACACIA_LOG, () -> Blocks.STRIPPED_ACACIA_LOG);
    public static final Branch DARK_OAK_BRANCH = registerBranch("dark_oak", () -> Blocks.DARK_OAK_LOG, () -> Blocks.STRIPPED_DARK_OAK_LOG);
    public static final Branch MANGROVE_BRANCH = registerBranch("mangrove", () -> Blocks.MANGROVE_LOG, () -> Blocks.STRIPPED_MANGROVE_LOG);
    public static final Branch CHERRY_BRANCH = registerBranch("cherry", () -> Blocks.CHERRY_LOG, () -> Blocks.STRIPPED_CHERRY_LOG);
    public static final Branch CRIMSON_BRANCH = registerBranch("crimson", () -> Blocks.CRIMSON_STEM, () -> Blocks.STRIPPED_CRIMSON_STEM);
    public static final Branch WARPED_BRANCH = registerBranch("warped", () -> Blocks.WARPED_STEM, () -> Blocks.STRIPPED_WARPED_STEM);
    // non wood
    public static final Branch MUSHROOM_BRANCH = registerBranch("mushroom", () -> Blocks.MUSHROOM_STEM);

    /**
     * A wood type's branch blocks and the logs they're derived from.
     * strippedBranch and strippedLog are null for logs without a stripped variant of their own.
     */
    public record Branch(String wood, DeferredBlock<Block> branch, Supplier<Block> log,
                         @Nullable DeferredBlock<Block> strippedBranch, @Nullable Supplier<Block> strippedLog) {
        public boolean hasStripped() {
            return strippedBranch != null;
        }

        // The branch blocks themselves, stripped one included if present
        public List<DeferredBlock<Block>> blocks() {
            return hasStripped() ? List.of(strippedBranch, branch) : List.of(branch);
        }
    }

    // Registers "<wood>_branch" and "stripped_<wood>_branch"
    public static Branch registerBranch(String wood, Supplier<Block> log, Supplier<Block> strippedLog) {
        DeferredBlock<Block> strippedBranch = registerBranchBlock("stripped_" + wood + "_branch", strippedLog);
        DeferredBlock<Block> branch = registerBranchBlock(wood + "_branch", log);
        Branch set = new Branch(wood, branch, log, strippedBranch, strippedLog);
        BRANCHES.add(set);
        return set;
    }

    // For logs without a stripped variant of their own
    public static Branch registerBranch(String wood, Supplier<Block> log) {
        Branch set = new Branch(wood, registerBranchBlock(wood + "_branch", log), log, null, null);
        BRANCHES.add(set);
        return set;
    }

    private static DeferredBlock<Block> registerBranchBlock(String name, Supplier<Block> log) {
        return register(name, () -> new BranchBlock(ofFullCopy(log.get()).noOcclusion()));
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

