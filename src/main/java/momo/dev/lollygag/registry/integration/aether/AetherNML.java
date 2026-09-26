package momo.dev.lollygag.registry.integration.aether;

import com.aetherteam.aether.block.AetherBlocks;
import com.farcr.nomansland.common.block.GrassSproutsBlock;
import com.farcr.nomansland.common.block.torches.*;
import com.farcr.nomansland.common.registry.NMLSounds;
import com.farcr.nomansland.common.registry.blocks.NMLBlocks;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.LItems;
import momo.dev.lollygag.registry.integration.nomansland.LExtinguishables;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

@SuppressWarnings("unused")
public class AetherNML {
    // TODO: sconce torches need a new class to hold non-vanilla particles
    public static final DeferredBlock<Block> SKYGRASS_SPROUTS = LBlocks.register("skygrass_sprouts", () -> new GrassSproutsBlock(ofFullCopy(Blocks.FERN).offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final DeferredBlock<Block> SCONCE_CAELIC_TORCH = LBlocks.registerNoItem("sconce_caelic_torch", () -> new SconceTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(Blocks.SOUL_TORCH).sound(NMLSounds.SCONCE_TORCH)));
    public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_CAELIC_TORCH = LBlocks.registerNoItem("extinguished_sconce_caelic_torch", () -> new ExtinguishedSconceTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(NMLBlocks.EXTINGUISHED_SCONCE_TORCH.get())));
    public static final DeferredBlock<Block> SCONCE_CAELIC_WALL_TORCH = LBlocks.registerNoItem("sconce_caelic_wall_torch", () -> new SconceWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(NMLBlocks.SCONCE_WALL_TORCH.get()).lootFrom(SCONCE_CAELIC_TORCH)));
    public static final DeferredBlock<Block> EXTINGUISHED_SCONCE_CAELIC_WALL_TORCH = LBlocks.registerNoItem("extinguished_sconce_caelic_wall_torch", () -> new ExtinguishedSconceWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(NMLBlocks.EXTINGUISHED_SCONCE_WALL_TORCH.get())));
    public static final DeferredBlock<Block> EXTINGUISHED_CAELIC_TORCH = LBlocks.registerNoItem("extinguished_caelic_torch", () -> new ExtinguishedTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(NMLBlocks.EXTINGUISHED_TORCH.get())));
    public static final DeferredBlock<Block> EXTINGUISHED_CAELIC_WALL_TORCH = LBlocks.registerNoItem("extinguished_caelic_wall_torch", () -> new ExtinguishedWallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, ofFullCopy(NMLBlocks.EXTINGUISHED_WALL_TORCH.get())));

    public static final DeferredItem<Item> SCONCE_CAELIC_TORCH_ITEM = LItems.register("sconce_caelic_torch", () -> new StandingAndWallBlockItem(SCONCE_CAELIC_TORCH.get(), SCONCE_CAELIC_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));

    public static void register() {
        LExtinguishables.register("caelic_torch", AetherBlocks.AMBROSIUM_TORCH.getDelegate(), EXTINGUISHED_CAELIC_TORCH.getDelegate());
        LExtinguishables.register("caelic_torch_wall", AetherBlocks.AMBROSIUM_WALL_TORCH.getDelegate(), EXTINGUISHED_CAELIC_WALL_TORCH.getDelegate());
        LExtinguishables.register("sconce_caelic_torch", SCONCE_CAELIC_TORCH.getDelegate(), EXTINGUISHED_SCONCE_CAELIC_TORCH.getDelegate());
        LExtinguishables.register("sconce_caelic_wall_torch", SCONCE_CAELIC_WALL_TORCH.getDelegate(), EXTINGUISHED_SCONCE_CAELIC_WALL_TORCH.getDelegate());
    }
}
