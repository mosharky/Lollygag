package momo.dev.lollygag.registry;

import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.hecco.bountifulfares.definition.block.custom.StrippedFruitLogBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.LeavesBlock;

public class LFlammables {
    public static void register() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;

        LBlocks.BLOCK_DEFINITIONS.forEach(block -> {
            if (block.get() instanceof LeavesBlock) {
                fireBlock.setFlammable(block.get(), 30, 60);
            }
            else if (block.get() instanceof FruitLogBlock || block.get() instanceof StrippedFruitLogBlock) {
                fireBlock.setFlammable(block.get(), 10, 5);
            }
        });

    }
}
