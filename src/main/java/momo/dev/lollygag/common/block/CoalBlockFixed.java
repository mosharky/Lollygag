package momo.dev.lollygag.common.block;

import com.teamabnormals.caverns_and_chasms.common.block.CoalBlock;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class CoalBlockFixed extends CoalBlock {
    private final Supplier<Item> ITEM;

    public CoalBlockFixed(Supplier<Item> item, Properties properties) {
        super(properties);
        ITEM = item;
    }

    @Override
    public Item asItem() {
        return ITEM.get();
    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this.asItem()));
    }
}
