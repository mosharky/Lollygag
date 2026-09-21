package momo.dev.lollygag.registry.integration.aether;

import com.aetherteam.aether.block.AetherBlocks;
import momo.dev.lollygag.common.block.CaelicFireBlock;
import momo.dev.lollygag.common.block.LDwarfSpruceHeadBlock;
import momo.dev.lollygag.common.block.LDwarfSprucePlantBlock;
import momo.dev.lollygag.registry.LBlocks;
import momo.dev.lollygag.registry.LItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class AetherBase {
    public static final DeferredBlock<Block> COARSE_AETHER_DIRT = LBlocks.register("coarse_aether_dirt", () -> new Block(ofFullCopy(Blocks.COARSE_DIRT)));

    public static final DeferredBlock<Block> TALL_SKYGRASS = LBlocks.register("tall_skygrass", () -> new DoublePlantBlock(ofFullCopy(Blocks.TALL_GRASS)));
    public static final DeferredBlock<Block> SHORT_SKYGRASS = LBlocks.register("short_skygrass", () -> new TallGrassBlock(ofFullCopy(Blocks.SHORT_GRASS)));
    public static final DeferredBlock<Block> SPIRING_FERN = LBlocks.register("spiring_fern", () -> new LDwarfSpruceHeadBlock(of().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> SPIRING_FERN_PLANT = LBlocks.registerNoItem("spiring_fern_plant", () -> new LDwarfSprucePlantBlock(of().noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY), (LDwarfSpruceHeadBlock) SPIRING_FERN.get()));

    public static final DeferredBlock<Block> CAELIC_FIRE = LBlocks.registerNoItem("caelic_fire", () -> new CaelicFireBlock(ofFullCopy(Blocks.SOUL_FIRE).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> CAELIC_CAMPFIRE = LBlocks.register("caelic_campfire", () -> new CampfireBlock(false, 1, ofFullCopy(Blocks.SOUL_CAMPFIRE)));
    public static final DeferredBlock<Block> CAELIC_LANTERN = LBlocks.register("caelic_lantern", () -> new LanternBlock(ofFullCopy(Blocks.LANTERN)));

    public static final DeferredBlock<Block> HOLYSTONE_QUARTZ_ORE = LBlocks.register("holystone_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));

    public static final DeferredItem<Item> AMBROSIUM_ITEM = LItems.register("ambrosium", () -> new Item(new Item.Properties()));

    public static void register() {}
}
