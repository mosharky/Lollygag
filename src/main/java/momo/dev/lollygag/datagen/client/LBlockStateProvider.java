package momo.dev.lollygag.datagen.client;

import com.farcr.nomansland.NoMansLand;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import com.teamabnormals.autumnity.common.block.AbstractLargePumpkinSliceBlock;
import com.teamabnormals.autumnity.common.block.LargeJackOLanternSliceBlock;
import com.teamabnormals.autumnity.common.block.properties.CarvedSide;
import com.teamabnormals.autumnity.core.Autumnity;
import com.teamabnormals.caverns_and_chasms.common.block.BrazierBlock;
import com.teamabnormals.caverns_and_chasms.common.block.CoalBlock;
import com.teamabnormals.caverns_and_chasms.common.block.IngotBlock;
import com.teamabnormals.caverns_and_chasms.common.block.IngotLayer;
import com.teamabnormals.caverns_and_chasms.core.CavernsAndChasms;
import com.teamabnormals.incubation.common.block.BirdNestBlock;
import com.teamabnormals.incubation.core.registry.IncubationBlocks;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.block.LDwarfSpruceHeadBlock;
import momo.dev.lollygag.common.block.LDwarfSprucePlantBlock;
import net.hecco.bountifulfares.BountifulFares;
import net.hecco.bountifulfares.definition.block.custom.FruitLogBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.client.model.generators.ModelFile.UncheckedModelFile;

import java.util.function.Function;

import static momo.dev.lollygag.registry.LBlocks.*;
import static momo.dev.lollygag.registry.integration.aether.AetherBase.*;
import static momo.dev.lollygag.registry.integration.aether.AetherCnC.*;
import static momo.dev.lollygag.registry.integration.aether.AetherAutumnity.*;
import static momo.dev.lollygag.registry.integration.aether.AetherBB.*;
import static momo.dev.lollygag.registry.integration.aether.AetherNML.*;
import static momo.dev.lollygag.registry.integration.aether.AetherIncubation.*;
import static momo.dev.lollygag.registry.integration.BFIntegration.*;
import static momo.dev.lollygag.registry.integration.NMLIntegration.*;
import static momo.dev.lollygag.registry.integration.OreganizedIntegration.*;

public class LBlockStateProvider extends BlueprintBlockStateProvider {
    public LBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Lollygag.MODID, existingFileHelper);
    }

    @Override protected void registerStatesAndModels() {
        basics(COARSE_AETHER_DIRT, ROCKY_AETHER_DIRT, FRAGILE_HOLYSTONE, FRAGILE_UNDERSHALE, HOLYSTONE_QUARTZ_ORE,
                HOLYSTONE_SILVER_ORE, HOLYSTONE_ZIRCONIA_ORE);

        pebbles(FLINT_PEBBLES.get());
        pebbles(HOLYSTONE_PEBBLES.get());
        pebbles(BLACKSTONE_PEBBLES.get());

        coalBlock(AMBROSIUM_PLACED.get());
        ingotBlock(LEAD_INGOT_PLACED.get());
        ingotBlock(ELECTRUM_INGOT_PLACED.get());

        fire(CAELIC_FIRE.get());
        campfire(CAELIC_CAMPFIRE.get());
        brazier(CAELIC_BRAZIER.get());
        lantern(CAELIC_LANTERN.get());
        jackOLantern(CAELIC_JACK_O_LANTERN.get());
        largeJackOLanternSlice(LARGE_CAELIC_JACK_O_LANTERN.get());
        candle(CAELIC_CANDLE.get());
        candleCake(CAELIC_CANDLE_CAKE.get(), CAELIC_CANDLE.get());
        torch(EXTINGUISHED_CAELIC_TORCH.get(), EXTINGUISHED_CAELIC_WALL_TORCH.get());
        sconceTorch(SCONCE_CAELIC_TORCH.get(), SCONCE_CAELIC_WALL_TORCH.get());
        sconceTorch(EXTINGUISHED_SCONCE_CAELIC_TORCH.get(),  EXTINGUISHED_SCONCE_CAELIC_WALL_TORCH.get());

        mirroredSprouts(SKYGRASS_SPROUTS.get());
        dwarfSpruce(SPIRING_FERN.get(), SPIRING_FERN_PLANT.get());
        tallTintedGrass(TALL_SKYGRASS.get());
        simpleBlock(SHORT_SKYGRASS.get(), tintedCross(name(SHORT_SKYGRASS)));
        flatBlockItem(SHORT_SKYGRASS.get());

        leaves(DEAD_LEAVES.get());
        leaves(ASPEN_LEAVES.get());
        leaves(BIRCH_LEAVES.get());
        fruitLeaves(PEAR_LEAVES.get(), FLOWERING_PEAR_LEAVES.get());

        fruitLogModels(ASPEN_LOG.get(), ASPEN_WOOD.get(), ASPEN_LEAVES.get());
        fruitLogModels(STRIPPED_ASPEN_LOG.get(), STRIPPED_ASPEN_WOOD.get());
        fruitLogModels(BIRCH_LOG.get(), BIRCH_WOOD.get(), BIRCH_LEAVES.get());
        fruitLogModels(STRIPPED_BIRCH_LOG.get(), STRIPPED_BIRCH_WOOD.get());
        fruitLogModels(PEAR_LOG.get(), PEAR_WOOD.get(), PEAR_LEAVES.get());
        fruitLogModels(STRIPPED_PEAR_LOG.get(), STRIPPED_PEAR_WOOD.get());

        sapling(ASPEN_SAPLING.get(), POTTED_ASPEN_SAPLING.get());
        sapling(BIRCH_SAPLING.get(), POTTED_BIRCH_SAPLING.get());
        sapling(PEAR_SAPLING.get(), POTTED_PEAR_SAPLING.get());

        nestBlocks("black_moa", TWIG_BLACK_MOA_NEST, HAY_BLACK_MOA_NEST);
        nestBlocks("blue_moa", TWIG_BLUE_MOA_NEST, HAY_BLUE_MOA_NEST);
        nestBlocks("white_moa", TWIG_WHITE_MOA_NEST, HAY_WHITE_MOA_NEST);
    }

    public void nestBlocks(String eggType, DeferredBlock<Block> twigNest, DeferredBlock<Block> hayNest) {
        this.eggNest(eggType, IncubationBlocks.TWIG_NEST, twigNest);
        this.eggNest(eggType, IncubationBlocks.HAY_NEST, hayNest);
    }

    public void eggNest(String eggType, DeferredBlock<Block> base, DeferredBlock<Block> nest) {
        if (eggType != null) {
            MultiPartBlockStateBuilder builder = this.getMultipartBuilder(nest.get()).part().modelFile(new UncheckedModelFile(blockTexture(base.get()))).addModel().end();
            String[] names = new String[]{"one", "two", "three", "four", "five", "six"};
            for (int i = 0; i < 6; i++) {
                builder.part().modelFile(new UncheckedModelFile(modLoc("block/" + names[i] + "_" + eggType + "_egg" + (i > 0 ? "s" : "")))).addModel().condition(BirdNestBlock.EGGS, i + 1).end();
            }
        } else {
            this.simpleBlock(base.get(), new UncheckedModelFile(blockTexture(base.get())));
            this.generatedItem(nest.get(), "item");
        }
    }

    public void fire(Block block) {
        String name = name(block);
        ModelFile floor0 = fireModel(name + "_floor0", "template_fire_floor", name + "_0");
        ModelFile floor1 = fireModel(name + "_floor1", "template_fire_floor", name + "_1");
        ModelFile side0 = fireModel(name + "_side0", "template_fire_side", name + "_0");
        ModelFile side1 = fireModel(name + "_side1", "template_fire_side", name + "_1");
        ModelFile sideAlt0 = fireModel(name + "_side_alt0", "template_fire_side_alt", name + "_0");
        ModelFile sideAlt1 = fireModel(name + "_side_alt1", "template_fire_side_alt", name + "_1");

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part()
                .modelFile(floor0).nextModel()
                .modelFile(floor1).addModel();
        builder.part()
                .modelFile(side0).nextModel()
                .modelFile(side1).nextModel()
                .modelFile(sideAlt0).nextModel()
                .modelFile(sideAlt1).addModel();
        builder.part()
                .modelFile(side0).rotationY(90).nextModel()
                .modelFile(side1).rotationY(90).nextModel()
                .modelFile(sideAlt0).rotationY(90).nextModel()
                .modelFile(sideAlt1).rotationY(90).addModel();
        builder.part()
                .modelFile(side0).rotationY(180).nextModel()
                .modelFile(side1).rotationY(180).nextModel()
                .modelFile(sideAlt0).rotationY(180).nextModel()
                .modelFile(sideAlt1).rotationY(180).addModel();
        builder.part()
                .modelFile(side0).rotationY(270).nextModel()
                .modelFile(side1).rotationY(270).nextModel()
                .modelFile(sideAlt0).rotationY(270).nextModel()
                .modelFile(sideAlt1).rotationY(270).addModel();
    }

    private ModelFile fireModel(String modelName, String parentName, String textureName) {
        return models().withExistingParent(modelName, mcLoc("block/" + parentName))
                .texture("fire", modLoc("block/" + textureName))
                .renderType("cutout");
    }

    public void lantern(Block block) {
        String name = name(block);
        ModelFile lantern = models().withExistingParent(name, mcLoc("block/template_lantern"))
                .texture("lantern", modLoc("block/" + name));
        ModelFile hangingLantern = models().withExistingParent(name + "_hanging", mcLoc("block/template_hanging_lantern"))
                .texture("lantern", modLoc("block/" + name));

        getVariantBuilder(block)
                .partialState().with(LanternBlock.HANGING, false)
                    .modelForState().modelFile(lantern).addModel()
                .partialState().with(LanternBlock.HANGING, true)
                    .modelForState().modelFile(hangingLantern).addModel();

        itemModels().basicItem(modLoc(name));
    }

    public void campfire(Block block) {
        String name = name(block);
        ModelFile off = models().getExistingFile(mcLoc("block/campfire_off"));
        ModelFile lit = models().withExistingParent(name, mcLoc("block/template_campfire"))
                .texture("fire", modLoc("block/" + name + "_fire"))
                .texture("lit_log", modLoc("block/" + name + "_log_lit"))
                .renderType("cutout");

        getVariantBuilder(block).forAllStatesExcept(state -> {
            Direction facing = state.getValue(CampfireBlock.FACING);
            ModelFile model = state.getValue(CampfireBlock.LIT) ? lit : off;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY((int) facing.toYRot())
                    .build();
        }, CampfireBlock.SIGNAL_FIRE, CampfireBlock.WATERLOGGED);

        itemModels().basicItem(modLoc(name));
    }

    public void brazier(Block block) {
        String name = name(block);
        ModelFile off = models().getExistingFile(CavernsAndChasms.location("block/brazier_off"));
        ModelFile offHanging = models().getExistingFile(CavernsAndChasms.location("block/brazier_off_hanging"));
        ModelFile lit = models().withExistingParent(name, CavernsAndChasms.location("block/template_brazier"))
                .texture("fire", modLoc("block/" + name + "_fire"))
                .texture("lit", modLoc("block/" + name + "_top_lit"))
                .renderType("cutout");
        ModelFile litHanging = models().withExistingParent(name + "_hanging", CavernsAndChasms.location("block/template_brazier_hanging"))
                .texture("fire", modLoc("block/" + name + "_fire"))
                .texture("lit", modLoc("block/" + name + "_top_lit"))
                .renderType("cutout");

        getVariantBuilder(block)
                .partialState().with(BrazierBlock.LIT, false).with(BrazierBlock.HANGING, false)
                    .modelForState().modelFile(off).addModel()
                .partialState().with(BrazierBlock.LIT, false).with(BrazierBlock.HANGING, true)
                    .modelForState().modelFile(offHanging).addModel()
                .partialState().with(BrazierBlock.LIT, true).with(BrazierBlock.HANGING, false)
                    .modelForState().modelFile(lit).addModel()
                .partialState().with(BrazierBlock.LIT, true).with(BrazierBlock.HANGING, true)
                    .modelForState().modelFile(litHanging).addModel();

        itemModels().basicItem(modLoc(name));
    }

    public void jackOLantern(Block block) {
        ModelFile model = models().orientable(name(block), mcLoc("block/pumpkin_side"), Autumnity.location("block/cupric_jack_o_lantern"), mcLoc("block/pumpkin_top"));

        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(model)
                .rotationY((int) (state.getValue(HorizontalDirectionalBlock.FACING).toYRot() + 180) % 360)
                .build());
        itemModels().simpleBlockItem(block);
    }

    public void largeJackOLanternSlice(Block block) {
        String name = name(block);
        getVariantBuilder(block).forAllStatesExcept(state -> {
            Half half = state.getValue(AbstractLargePumpkinSliceBlock.HALF);
            Direction facing = state.getValue(AbstractLargePumpkinSliceBlock.FACING);
            CarvedSide carvedSide = state.getValue(LargeJackOLanternSliceBlock.CARVED_SIDE);
            String halfName = half.getSerializedName();
            String carvedSideName = carvedSide.getSerializedName();
            String corner = switch (facing) {
                case EAST -> "northeast";
                case NORTH -> "northwest";
                case WEST -> "southwest";
                default -> "southeast";
            };
            String parentName = "large_cupric_jack_o_lantern_slice_" + halfName + "_" + corner + "_" + carvedSideName;
            String modelName = name + "_slice_" + halfName + "_" + corner + "_" + carvedSideName;
            boolean leftTexture = usesLeftJackOLanternSliceTexture(corner, carvedSideName);
            ModelFile model = models().withExistingParent(modelName, Autumnity.location("block/" + parentName))
                    .texture("particle", modLoc("block/" + name + "_slice_top_left"))
                    .texture(leftTexture ? "left" : "right", modLoc("block/" + name + "_slice_" + halfName + "_" + (leftTexture ? "left" : "right")));
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        }, AbstractLargePumpkinSliceBlock.POWERED);
        itemModels().withExistingParent(name(block), modLoc("block/" + name + "_slice_bottom_northeast_z"));
    }

    private boolean usesLeftJackOLanternSliceTexture(String corner, String carvedSideName) {
        boolean diagonalCorner = corner.equals("northwest") || corner.equals("southeast");
        return carvedSideName.equals("x") == diagonalCorner;
    }

    public void candle(Block block) {
        String name = name(block);
        ModelFile[] unlit = candleModels(name, false);
        ModelFile[] lit = candleModels(name, true);

        getVariantBuilder(block).forAllStatesExcept(state -> {
            int candles = state.getValue(CandleBlock.CANDLES);
            ModelFile model = state.getValue(CandleBlock.LIT) ? lit[candles - 1] : unlit[candles - 1];
            return ConfiguredModel.builder().modelFile(model).build();
        }, CandleBlock.WATERLOGGED);
        itemModels().basicItem(modLoc(name));
    }

    private ModelFile[] candleModels(String name, boolean lit) {
        String suffix = lit ? "_lit" : "";
        String texture = lit ? "block/" + name + "_lit" : "block/" + name;
        return new ModelFile[] {
                candleModel(name + "_one_candle" + suffix, "template_candle", texture),
                candleModel(name + "_two_candles" + suffix, "template_two_candles", texture),
                candleModel(name + "_three_candles" + suffix, "template_three_candles", texture),
                candleModel(name + "_four_candles" + suffix, "template_four_candles", texture)
        };
    }

    private ModelFile candleModel(String modelName, String parentName, String textureName) {
        ResourceLocation texture = modLoc(textureName);
        return models().withExistingParent(modelName, mcLoc("block/" + parentName))
                .texture("all", texture)
                .texture("particle", texture);
    }

    public void candleCake(Block candleCake, Block candle) {
        ModelFile unlit = candleCakeModel(name(candleCake), name(candle));
        ModelFile lit = candleCakeModel(name(candleCake) + "_lit", name(candle) + "_lit");

        getVariantBuilder(candleCake)
                .partialState().with(CandleCakeBlock.LIT, false)
                    .modelForState().modelFile(unlit).addModel()
                .partialState().with(CandleCakeBlock.LIT, true)
                    .modelForState().modelFile(lit).addModel();
    }

    private ModelFile candleCakeModel(String cakeName, String candleName) {
        return models().withExistingParent(cakeName, mcLoc("block/template_cake_with_candle"))
                .texture("candle", modLoc("block/" + candleName))
                .texture("bottom", mcLoc("block/cake_bottom"))
                .texture("side", mcLoc("block/cake_side"))
                .texture("top", mcLoc("block/cake_top"))
                .texture("particle", mcLoc("block/cake_side"));
    }

    public void sconceTorch(Block standingBlock, Block wallBlock) {
        ModelFile floorModel = models().withExistingParent("block/" + name(standingBlock), NoMansLand.location("block/sconce_torch_template"))
                .texture("texture", modLoc("block/" + name(standingBlock)));
        simpleBlock(standingBlock, floorModel);
        itemModels().withExistingParent(name(standingBlock), floorModel.getLocation());
        // Wall torch
        ModelFile wallModel = models().withExistingParent("block/" + name(wallBlock), NoMansLand.location("block/sconce_wall_torch_template"))
                .texture("texture", modLoc("block/" + name(standingBlock)));
        wallTorchBlock(wallBlock, wallModel, false);
    }

    public void torch(Block standingBlock, Block wallBlock) {
        ModelFile model = models().withExistingParent("block/" + name(standingBlock), mcLoc("block/template_torch"))
                .texture("torch", modLoc("block/" + name(standingBlock)))
                .renderType("cutout");
        simpleBlock(standingBlock, model);
        itemModels().withExistingParent(name(standingBlock), model.getLocation());
        // Wall torch
        ModelFile wallModel = models().withExistingParent("block/" + name(wallBlock), mcLoc("block/template_torch_wall"))
                .texture("torch", modLoc("block/" + name(standingBlock)))
                .renderType("cutout");
        wallTorchBlock(wallBlock, wallModel, true);
    }

    private void wallTorchBlock(Block block, ModelFile model, boolean torchRotation) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(WallTorchBlock.FACING);
            int rotation = torchRotation ? wallTorchRotation(facing) : sconceWallTorchRotation(facing);
            return ConfiguredModel.builder().modelFile(model).rotationY(rotation).build();
        });
    }

    private int sconceWallTorchRotation(Direction facing) {
        return switch (facing) {
            case EAST -> 90;
            case SOUTH -> 180;
            case WEST -> 270;
            default -> 0;
        };
    }

    private int wallTorchRotation(Direction facing) {
        return switch (facing) {
            case NORTH -> 270;
            case SOUTH -> 90;
            case WEST -> 180;
            default -> 0;
        };
    }

    public void coalBlock(Block block) {
        this.getVariantBuilder(block).forAllStatesExcept(state -> {
            String count = switch (state.getValue(CoalBlock.COAL)) {
                case 1 -> "_one";
                case 2 -> "_two";
                case 3 -> "_three";
                default -> "_four";
            };

            boolean hasFlame = state.getValue(CoalBlock.LIT);
            boolean isHot = hasFlame || state.getValue(CoalBlock.WARM);
            String lit = isHot ? "_lit" : "";
            String flame = hasFlame ? "_flame" : "";
            String name = name(block) + count;
            BlockModelBuilder model = models().withExistingParent(name + lit + flame, CavernsAndChasms.location("block/template_coal" + count + (hasFlame ? "_lit" : "")))
                    .texture("coal", blockTexture(block).withSuffix(lit));
            if (hasFlame) {
                model.texture("fire", blockTexture(block).withSuffix("_fire"));
            }
            return ConfiguredModel.builder()
                    .modelFile(model).nextModel()
                    .modelFile(model).rotationY(90).nextModel()
                    .modelFile(model).rotationY(180).nextModel()
                    .modelFile(model).rotationY(270)
                    .build();
        }, CoalBlock.WATERLOGGED);

        this.placedItemModel(block);
    }

    public void ingotBlock(Block block) {

        MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block);
        this.addIngotLayer(builder, block, 1, 1, 2, 3);
        this.addIngotLayer(builder, block, 2, 2, 3);
        this.addIngotLayer(builder, block, 3, 3);
        this.addIngotLayer(builder, block, 4);

        this.placedItemModel(block);
    }

    public void placedItemModel(Block block) {
        this.itemModels().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).withSuffix("_placed").getPath(), "item/generated").texture("layer0", BuiltInRegistries.ITEM.getKey(Items.BARRIER).withPrefix("item/"));
    }

    public void addIngotLayer(MultiPartBlockStateBuilder builder, Block block, int i, Integer... nums) {
        this.addIngotModel(builder, block, IngotLayer.LEFT, Direction.Axis.X, i, nums);
        this.addIngotModel(builder, block, IngotLayer.RIGHT, Direction.Axis.X, i, nums);
        this.addIngotModel(builder, block, IngotLayer.LEFT, Direction.Axis.Z, i, nums);
        this.addIngotModel(builder, block, IngotLayer.RIGHT, Direction.Axis.Z, i, nums);
    }

    public void addIngotModel(MultiPartBlockStateBuilder builder, Block block, IngotLayer ingotLayer, Direction.Axis axis, int layer, Integer... nums) {
        Direction.Axis visualAxis = IngotBlock.getAxisForLayer(layer, axis);
        String name = "_" + ingotLayer.getSerializedName() + "_" + visualAxis.getSerializedName() + "_layer" + layer;
        BlockModelBuilder model = models().withExistingParent(name(block) + name, CavernsAndChasms.location("block/template_ingot" + name)).texture("ingot", blockTexture(block).toString().replace("waxed_", ""));

        if (nums.length > 0) {
            builder.part().modelFile(model).addModel().useOr()
                    .nestedGroup().condition(IngotBlock.AXIS, axis).condition(IngotBlock.LAYERS, layer - 1).condition(IngotBlock.TOP_INGOT, ingotLayer, IngotLayer.BOTH).end()
                    .nestedGroup().condition(IngotBlock.AXIS, axis).condition(IngotBlock.LAYERS, nums).end();
        } else {
            builder.part().modelFile(model).addModel()
                    .condition(IngotBlock.AXIS, axis).condition(IngotBlock.LAYERS, layer - 1).condition(IngotBlock.TOP_INGOT, ingotLayer, IngotLayer.BOTH);
        }
    }

    public ModelFile tintedCross(String blockName) {
        return models()
                .withExistingParent(blockName, mcLoc("block/tinted_cross"))
                .texture("cross", modLoc("block/" + blockName))
                .renderType("cutout");
    }

    @SafeVarargs
    public final void basics(DeferredBlock<? extends Block>... defs) {
        for (DeferredBlock<? extends Block> def : defs) {
            simpleBlockWithItem(def.get(), cubeAll(def.get()));
        }
    }

    public void leaves(Block block) {
        simpleBlockWithItem(block, models().leaves(name(block), modLoc("block/" + name(block))));
    }

    public void mirroredSprouts(Block block) {
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(block);
        VariantBlockStateBuilder.PartialBlockstate partialState = variantBuilder.partialState();
        variantBuilder.addModels(partialState,
                partialState.modelForState()
                        .modelFile(tintedCross(name(block))).nextModel()
                        .modelFile(tintedCross(name(block))).rotationY(270)  // mirror
                        .build()
        );
        this.flatBlockItem(block, this.modLoc("item/" + name(block)));
    }

    public void tallTintedGrass(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            String suffix = state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER
                    ? "_bottom"
                    : "_top";
            return ConfiguredModel.builder().modelFile(tintedCross(name(block) + suffix)).build();
        });
        this.flatBlockItem(block, this.modLoc("block/" + name(block) + "_top"));
    }

    public void dwarfSpruce(Block headBlock, Block plantBlock) {
        String name = name(headBlock);
        getVariantBuilder(headBlock).forAllStates(state -> {
            String suffix = state.getValue(LDwarfSpruceHeadBlock.TOP) ? "_top" : "";
            return ConfiguredModel.builder()
                    .modelFile(models().cross(name + suffix, modLoc("block/" + name + suffix)).renderType("cutout"))
                    .build();
        });
        getVariantBuilder(plantBlock).forAllStates(state -> {
            String suffix = state.getValue(LDwarfSprucePlantBlock.BOTTOM) ? "_bottom" : "_middle";
            return ConfiguredModel.builder()
                    .modelFile(models().cross(name + suffix, modLoc("block/" + name + suffix)).renderType("cutout"))
                    .build();
        });
        flatBlockItem(headBlock);
    }

    public void pebbles(Block block) {
        this.simpleBlockWithVariation(block,
                (i) -> {
                    int index = i + 1;
                    String name = name(block);
                    return this.models().getBuilder(name + "_" + index)
                            .parent(new ModelFile.UncheckedModelFile(NoMansLand.location("block/pebble/pebble_" + index)))
                            .texture("0", modLoc(ModelProvider.BLOCK_FOLDER + "/" + name));
                },11);
        this.flatBlockItem(block, modLoc("item/flint_pebbles"));
    }

    public void sapling(Block sapling, Block pottedSapling) {
        simpleBlock(sapling, models().cross(name(sapling), modLoc("block/" + name(sapling)))
                .renderType(mcLoc("cutout"))
        );
        flatBlockItem(sapling);
        simpleBlock(pottedSapling, models().withExistingParent(name(pottedSapling), mcLoc("block/flower_pot_cross"))
                .texture("plant", modLoc("block/" + name(sapling)))
                .renderType(mcLoc("cutout"))
        );
    }

    public void fruitLeaves(Block leaves, Block floweringLeaves) {
        simpleBlockWithItem(leaves, models().leaves(name(leaves), modLoc("block/" + name(leaves))));
        simpleBlockWithItem(floweringLeaves, models()
                .withExistingParent(name(floweringLeaves), BountifulFares.id("block/leaves_with_overlay"))
                .texture("all", modLoc("block/" +  name(leaves)))
                .texture("overlay", modLoc("block/" +  name(floweringLeaves)))
        );
    }

    public void fruitLogModels(Block log, Block wood) {
        String logName = name(log);
        String woodName = name(wood);
        // templates
        ResourceLocation template_fruit_log = BountifulFares.id("block/template_fruit_log");
        ResourceLocation template_fruit_log_noside = BountifulFares.id("block/template_fruit_log_noside");
        ResourceLocation template_fruit_log_otherside = BountifulFares.id("block/template_fruit_log_otherside");
        ResourceLocation template_fruit_log_side = BountifulFares.id("block/template_fruit_log_side");
        ResourceLocation template_fruit_wood_otherside = BountifulFares.id("block/template_fruit_wood_otherside");
        ResourceLocation template_fruit_wood_side = BountifulFares.id("block/template_fruit_wood_side");
        // template-applied model files
        ModelFile fruit_log = models().withExistingParent(logName, template_fruit_log).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_log_noside = models().withExistingParent(logName + "_noside", template_fruit_log_noside).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_log_otherside = models().withExistingParent(logName + "_otherside", template_fruit_log_otherside).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_log_side = models().withExistingParent(logName + "_side", template_fruit_log_side).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_wood_otherside = models().withExistingParent(woodName + "_otherside", template_fruit_wood_otherside).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_wood_side = models().withExistingParent(woodName + "_side", template_fruit_wood_side).texture("texture", modLoc("block/" + logName));

        // item models
        itemModels().withExistingParent(logName, BountifulFares.id("item/template_fruit_log")).texture("texture", modLoc("block/" + logName));
        itemModels().withExistingParent(woodName, BountifulFares.id("item/template_fruit_wood")).texture("texture", modLoc("block/" + logName));

        // block models
        getMultipartBuilder(log)
            .part()
                .modelFile(fruit_log_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.NORTH, true).end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.EAST, true).end()
            .part()
                .modelFile(fruit_log_side).rotationX(270).addModel()
                .condition(FruitLogBlock.SOUTH, true).end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.WEST, true).end()
            .part()
                .modelFile(fruit_log_side).addModel()
                .condition(FruitLogBlock.UP, true).end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.DOWN, true).end()
            .part()
                .modelFile(fruit_log).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y).end()
            .part()
                .modelFile(fruit_log_noside).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, true)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, true)
                .end()
            .part()
                .modelFile(fruit_log_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end();

        // wood blockstate
    getMultipartBuilder(wood)
            .part()
                .modelFile(fruit_wood_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.NORTH, true).end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.EAST, true).end()
            .part()
                .modelFile(fruit_wood_side).rotationX(270).addModel()
                .condition(FruitLogBlock.SOUTH, true).end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.WEST, true).end()
            .part()
                .modelFile(fruit_wood_side).addModel()
                .condition(FruitLogBlock.UP, true).end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.DOWN, true).end()
            .part()
                .modelFile(fruit_log).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y).end()
            .part()
                .modelFile(fruit_log_noside).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, true)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, true)
                .end()
            .part()
                .modelFile(fruit_wood_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end();
    }

    public void fruitLogModels(Block log, Block wood, Block leaves) {
        String logName = name(log);
        String woodName = name(wood);
        String leavesName = name(leaves);
        // templates
        ResourceLocation template_fruit_log = BountifulFares.id("block/template_fruit_log");
        ResourceLocation template_fruit_log_noside = BountifulFares.id("block/template_fruit_log_noside");
        ResourceLocation template_fruit_log_otherside = BountifulFares.id("block/template_fruit_log_otherside");
        ResourceLocation template_fruit_log_side = BountifulFares.id("block/template_fruit_log_side");
        ResourceLocation template_fruit_wood_otherside = BountifulFares.id("block/template_fruit_wood_otherside");
        ResourceLocation template_fruit_wood_side = BountifulFares.id("block/template_fruit_wood_side");
        // template-applied model files
        ModelFile fruit_log = models().withExistingParent(logName, template_fruit_log).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_log_noside = models().withExistingParent(logName + "_noside", template_fruit_log_noside).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_log_otherside = models().withExistingParent(logName + "_otherside", template_fruit_log_otherside).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_log_side = models().withExistingParent(logName + "_side", template_fruit_log_side).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_wood_otherside = models().withExistingParent(woodName + "_otherside", template_fruit_wood_otherside).texture("texture", modLoc("block/" + logName));
        ModelFile fruit_wood_side = models().withExistingParent(woodName + "_side", template_fruit_wood_side).texture("texture", modLoc("block/" + logName));

        // item models
        itemModels().withExistingParent(logName, BountifulFares.id("item/template_fruit_log")).texture("texture", modLoc("block/" + logName));
        itemModels().withExistingParent(woodName, BountifulFares.id("item/template_fruit_wood")).texture("texture", modLoc("block/" + logName));

        // block models
        getMultipartBuilder(log)
            .part()
                .modelFile(fruit_log_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.NORTH, true).end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.EAST, true).end()
            .part()
                .modelFile(fruit_log_side).rotationX(270).addModel()
                .condition(FruitLogBlock.SOUTH, true).end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.WEST, true).end()
            .part()
                .modelFile(fruit_log_side).addModel()
                .condition(FruitLogBlock.UP, true).end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.DOWN, true).end()
            .part()
                .modelFile(fruit_log).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y).end()
            .part()
                .modelFile(fruit_log_noside).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, true)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, true)
                .end()
            .part()
                .modelFile(fruit_log_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_log_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_log_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("block/" + leavesName))).uvLock(true).addModel()
                .condition(FruitLogBlock.LEAFY, true)
                .end();

        // wood blockstate
        getMultipartBuilder(wood)
            .part()
                .modelFile(fruit_wood_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.NORTH, true).end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.EAST, true).end()
            .part()
                .modelFile(fruit_wood_side).rotationX(270).addModel()
                .condition(FruitLogBlock.SOUTH, true).end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.WEST, true).end()
            .part()
                .modelFile(fruit_wood_side).addModel()
                .condition(FruitLogBlock.UP, true).end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.DOWN, true).end()
            .part()
                .modelFile(fruit_log).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y).end()
            .part()
                .modelFile(fruit_log_noside).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, true)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, true)
                .end()
            .part()
                .modelFile(fruit_wood_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Y)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(90).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(270).rotationY(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.X)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_log).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).rotationY(180).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .end()
            .part()
                .modelFile(fruit_log_noside).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_wood_otherside).rotationX(90).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.NORTH, false)
                .condition(FruitLogBlock.SOUTH, false)
                .end()
            .part()
                .modelFile(fruit_wood_side).rotationX(270).addModel()
                .condition(FruitLogBlock.AXIS, Direction.Axis.Z)
                .condition(FruitLogBlock.DOWN, false)
                .condition(FruitLogBlock.EAST, false)
                .condition(FruitLogBlock.SOUTH, false)
                .condition(FruitLogBlock.UP, false)
                .condition(FruitLogBlock.WEST, false)
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("block/" + leavesName))).uvLock(true).addModel()
                .condition(FruitLogBlock.LEAFY, true)
                .end();
    }


    // Helpers from NML
    public void flatBlockItem(Block block) {
        this.flatBlockItem(block, this.modLoc("block/" + name(block)));
    }

    public void flatBlockItem(Block block, ResourceLocation texture) {
        this.itemModels().getBuilder(key(block).getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture);
    }

    private void simpleBlockWithVariation(Block block, int variations) {
        String blockName = name(block);
        this.simpleBlockWithVariation(block, (i) -> {
            String name = blockName + "_" + i;
            return this.models().cubeAll(name, this.modLoc(ModelProvider.BLOCK_FOLDER + "/" + name));
        }, variations);
    }

    private void simpleBlockWithVariation(Block block, Function<Integer, ModelBuilder> modelFactory, int variations) {
        for (int i =0; i < variations; i++) {
            this.getVariantBuilder(block).partialState().addModels(ConfiguredModel.builder()
                    .modelFile(modelFactory.apply(i))
                    .buildLast()
            );
        }
    }

    private void simpleBlockWithVariationAndTransformation(Block block, int variations, boolean rotateX, boolean rotateY) {
        String blockName = name(block);
        this.simpleBlockWithVariationAndTransformation(
                block, (i) -> {
                    String suffix = "_" + i;
                    return this.models().cubeAll(
                            blockName + suffix,
                            this.modLoc(ModelProvider.BLOCK_FOLDER + "/" + blockName + suffix)
                    );
                }, (i) -> {
                    String suffix = "_" + i;
                    return this.models().singleTexture(
                            blockName + suffix + "_mirrored",
                            this.mcLoc(ModelProvider.BLOCK_FOLDER + "/cube_mirrored_all"),
                            "all",
                            this.modLoc(ModelProvider.BLOCK_FOLDER + "/" + blockName + suffix)
                    );
                },
                variations, rotateX, rotateY );
    }

    private void simpleBlockWithVariationAndTransformation(Block block, Function<Integer, ModelBuilder> modelFactory, Function<Integer, ModelBuilder> mirroredModelFactory, int variations, boolean rotateX, boolean rotateY) {
        ModelBuilder[] models = new ModelBuilder[variations *2];
        for (int i =0; i < variations; i++) {
            models[i *2] = modelFactory.apply(i);
            models[i *2 +1] = mirroredModelFactory.apply(i);
        }

        for (int i =0; i < variations; i++) {
            this.getVariantBuilder(block).partialState().addModels(
                    ConfiguredModel.builder().modelFile(models[i *2]).buildLast(),
                    ConfiguredModel.builder().modelFile(models[i *2 +1]).buildLast()
            );

            if (rotateX && rotateY) {
                for (int rotX =1; rotX <4; rotX++) {
                    for (int rotY =0; rotY <4; rotY++) {
                        this.getVariantBuilder(block).partialState().addModels(
                                ConfiguredModel.builder().modelFile(models[i *2]).rotationX(rotX *90).rotationY(rotY *90).buildLast(),
                                ConfiguredModel.builder().modelFile(models[i *2 +1]).rotationX(rotX *90).rotationY(rotY *90).buildLast()
                        );
                    }
                }
            } else if (rotateX) {
                for (int rot =1; rot <4; rot++) {
                    this.getVariantBuilder(block).partialState().addModels(
                            ConfiguredModel.builder().modelFile(models[i *2]).rotationX(rot *90).buildLast(),
                            ConfiguredModel.builder().modelFile(models[i *2 +1]).rotationX(rot *90).buildLast()
                    );
                }
            } else if (rotateY) {
                for (int rot =1; rot <4; rot++) {
                    this.getVariantBuilder(block).partialState().addModels(
                            ConfiguredModel.builder().modelFile(models[i *2]).rotationY(rot *90).buildLast(),
                            ConfiguredModel.builder().modelFile(models[i *2 +1]).rotationY(rot *90).buildLast()
                    );
                }
            }
        }
    }

    private void slabBlockWithVariation(Block block, Function<Integer, ResourceLocation> doubleSlabFactory, Function<Integer, ResourceLocation> texFactory, int variations) {
        this.slabBlockWithVariation(block, doubleSlabFactory, texFactory, texFactory, texFactory, variations);
    }

    private void slabBlockWithVariation(Block block,
                                        Function<Integer, ResourceLocation> doubleSlabFactory,
                                        Function<Integer, ResourceLocation> sideTexFactory,
                                        Function<Integer, ResourceLocation> bottomTexFactory,
                                        Function<Integer, ResourceLocation> topTexFactory,
                                        int variations) {
        String blockName = name(block);
        for (int i =0; i < variations; i++) {
            String suffix = "_" + i;
            ResourceLocation sideTex = sideTexFactory.apply(i);
            ResourceLocation bottomTex = bottomTexFactory.apply(i);
            ResourceLocation topTex = topTexFactory.apply(i);
            getVariantBuilder(block)
                    .partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(this.models().getExistingFile(doubleSlabFactory.apply(i))))
                    .partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(models().slab(blockName + suffix, sideTex, bottomTex, topTex)))
                    .partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel(models().slabTop(blockName + "_top" + suffix, sideTex, bottomTex, topTex)));
        }
    }

    private void stairsBlockWithVariation(Block block, Function<Integer, ResourceLocation> texFactory, int variations) {
        this.stairsBlockWithVariation(block, texFactory, texFactory, texFactory, variations);
    }

    private void stairsBlockWithVariation(Block block,
                                          Function<Integer, ResourceLocation> sideTexFactory,
                                          Function<Integer, ResourceLocation> bottomTexFactory,
                                          Function<Integer, ResourceLocation> topTexFactory,
                                          int variations) {
        ModelFile[] stairs = new ModelFile[variations];
        ModelFile[] stairsInner = new ModelFile[variations];
        ModelFile[] stairsOuter = new ModelFile[variations];

        String blockName = name(block);
        for (int i =0; i < variations; i++) {
            String suffix = "_" + i;
            ResourceLocation sideTex = sideTexFactory.apply(i);
            ResourceLocation bottomTex = bottomTexFactory.apply(i);
            ResourceLocation topTex = topTexFactory.apply(i);
            stairs[i] = models().stairs(blockName + suffix, sideTex, bottomTex, topTex);
            stairsInner[i] = models().stairsInner(blockName + "_inner" + suffix, sideTex, bottomTex, topTex);
            stairsOuter[i] = models().stairsOuter(blockName + "_outer" + suffix, sideTex, bottomTex, topTex);
        }

        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(StairBlock.FACING);
                    Half half = state.getValue(StairBlock.HALF);
                    StairsShape shape = state.getValue(StairBlock.SHAPE);
                    int yRot = (int) facing.getClockWise().toYRot();
                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) yRot +=270;
                    if (shape != StairsShape.STRAIGHT && half == Half.TOP) yRot +=90;
                    yRot %=360;
                    boolean uvlock = yRot !=0 || half == Half.TOP;

                    ConfiguredModel.Builder builder = ConfiguredModel.builder();
                    for (int i =0; i < variations; i++) {
                        if (i >0) builder = builder.nextModel();
                        builder = builder .modelFile(shape == StairsShape.STRAIGHT ? stairs[i] : shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner[i] : stairsOuter[i])
                                .rotationX(half == Half.BOTTOM ?0 :180)
                                .rotationY(yRot)
                                .uvLock(uvlock);
                    }

                    return builder.build();
                }, StairBlock.WATERLOGGED);
    }

    public ModelBuilder cubeBottomTopMirrored(String name, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return this.cubeMirrored(name, bottom, top, side, side, side, side);
    }

    public ModelBuilder cubeMirrored(String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return this.models().withExistingParent(name, "cube_mirrored")
                .texture("down", down)
                .texture("up", up)
                .texture("north", north)
                .texture("south", south)
                .texture("east", east)
                .texture("west", west);
    }

    public ModelBuilder cross(Block block) {
        return this.models().cross(name(block), this.modLoc(ModelProvider.BLOCK_FOLDER + "/" + name(block)));
    }

    private static ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public static String name(Block block) {
        return key(block).getPath();
    }

    private String name(DeferredBlock block) {
        return key((Block) block.get()).getPath();
    }
}
