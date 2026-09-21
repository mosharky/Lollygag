package momo.dev.lollygag.registry.integration.aether;

import com.aetherteam.aether.block.AetherBlocks;
import com.teamabnormals.caverns_and_chasms.common.block.BrazierBlock;
import com.teamabnormals.caverns_and_chasms.core.registry.CCBlocks;
import com.teamabnormals.caverns_and_chasms.core.registry.CCSoundEvents;
import momo.dev.lollygag.common.block.CoalBlockFixed;
import momo.dev.lollygag.common.block.FragileHolystone;
import momo.dev.lollygag.common.block.FragileUndershale;
import momo.dev.lollygag.registry.LBlocks;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class AetherCnC {
    public static final DeferredBlock<Block> FRAGILE_HOLYSTONE = LBlocks.register("fragile_holystone", () -> new FragileHolystone(ofFullCopy(AetherBlocks.HOLYSTONE.get()).sound(CCSoundEvents.CCSoundTypes.FRAGILE_STONE)));
    public static final DeferredBlock<Block> FRAGILE_UNDERSHALE = LBlocks.register("fragile_undershale", () -> new FragileUndershale(ofFullCopy(AetherBlocks.HOLYSTONE.get()).sound(CCSoundEvents.CCSoundTypes.FRAGILE_STONE)));
    public static final DeferredBlock<Block> ROCKY_AETHER_DIRT = LBlocks.register("rocky_aether_dirt", () -> new Block(ofFullCopy(AetherBlocks.AETHER_DIRT.get()).sound(CCSoundEvents.CCSoundTypes.ROCKY_DIRT).requiresCorrectToolForDrops().strength(1.5F)));
    public static final DeferredBlock<Block> CAELIC_BRAZIER = LBlocks.register("caelic_brazier", () -> new BrazierBlock(1.0F, CCBlocks.CCProperties.BRAZIER));
    public static final DeferredBlock<Block> HOLYSTONE_SILVER_ORE = LBlocks.register("holystone_silver_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));
    public static final DeferredBlock<Block> HOLYSTONE_ZIRCONIA_ORE = LBlocks.register("holystone_zirconia_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), ofFullCopy(AetherBlocks.AMBROSIUM_ORE.get())));

    public static final DeferredBlock<Block> AMBROSIUM_PLACED = LBlocks.registerPlacedItem("ambrosium", () -> new CoalBlockFixed(() -> AetherBase.AMBROSIUM_ITEM.get(), LBlocks.LProperties.placedCoal(6)));

    public static void register() {}
}
