package net.evilcult.scenic.world.gen;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.evilcult.scenic.Scenic;
import net.evilcult.scenic.registry.ScenicBlocks;
import net.evilcult.scenic.registry.ScenicFeatures;
import net.evilcult.scenic.world.gen.feature.RockPileConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

/**
 * Scenic Biome Features
 * Scenic-Mod - net.evilcult.scenic.world.gen.ScenicBiomeFeatures
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-1.0.3
 * @since 2020-06-14
 */

@Mod.EventBusSubscriber(modid = Scenic.MODID)
public class ScenicBiomeFeatures {
//
    public static BlockClusterFeatureConfig GRASS_SHORT_CONFIG = null;
    public static BlockClusterFeatureConfig GRASS_TUFT_CONFIG = null;
    public static BlockClusterFeatureConfig ROOTS_CONFIG = null;
    public static BlockClusterFeatureConfig ROOTS_LONG_CONFIG = null;
    public static BlockClusterFeatureConfig ROOTS_GROUND_CONFIG = null;
    public static BlockClusterFeatureConfig FISH_BONES_CONFIG = null;
    public static OreFeatureConfig ROCKY_DIRT_CONFIG = null;
    public static SphereReplaceConfig MOSSY_GRAVEL_CONFIG = null;
    public static SphereReplaceConfig MOSSY_ROCKY_DIRT_CONFIG = null;
    public static RockPileConfig ROCK_PILE_CONFIG = null;
    public static RockPileConfig SANDSTONE_ROCK_PILE_CONFIG = null;
    public static BlockClusterFeatureConfig STALAGMITE_CONFIG = null;
    public static BlockClusterFeatureConfig STALACTITE_CONFIG = null;
    public static BlockClusterFeatureConfig TREASURE_POT_CONFIG = null;

    public static ConfiguredFeature<?, ?> GRASS_SHORT_FEATURE = null;
    public static ConfiguredFeature<?, ?> GRASS_TUFT_FEATURE = null;
    public static ConfiguredFeature<?, ?> ROCKY_DIRT_FEATURE = null;
    public static ConfiguredFeature<?, ?> STALAGMITE_FEATURE = null;
    public static ConfiguredFeature<?, ?> STALACTITE_FEATURE = null;
    public static ConfiguredFeature<?, ?> TREASURE_POT_FEATURE = null;
    public static ConfiguredFeature<?, ?> MOSSY_GRAVEL_FEATURE = null;
    public static ConfiguredFeature<?, ?> MOSSY_ROCKY_DIRT_FEATURE = null;
    public static ConfiguredFeature<?, ?> FISH_BONES_FEATURE = null;
    public static ConfiguredFeature<?, ?> ROOTS_FEATURE = null;
    public static ConfiguredFeature<?, ?> ROOTS_LONG_FEATURE = null;
    public static ConfiguredFeature<?, ?> ROOTS_GROUND_FEATURE = null;

    public static void subscribe(IEventBus bus) {
        bus.addListener(ScenicBiomeFeatures::setup);
    }

    public static void setup(final FMLCommonSetupEvent event) {
        GRASS_SHORT_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.GRASS_SHORT.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).build();
        GRASS_TUFT_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.GRASS_TUFT.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).build();

        ROOTS_LONG_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.ROOTS_LONG.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(16).build();
        ROOTS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.ROOTS.get().getDefaultState()), new SimpleBlockPlacer())).tries(16).build();
        ROOTS_GROUND_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.ROOTS_GROUND.get().getDefaultState()), new SimpleBlockPlacer())).tries(8).build();

        FISH_BONES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.FISH_BONES.get().getDefaultState()), new SimpleBlockPlacer())).tries(4).build();

        ROCKY_DIRT_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ScenicBlocks.ROCKY_DIRT.get().getDefaultState(), 20);
        MOSSY_GRAVEL_CONFIG = new SphereReplaceConfig(ScenicBlocks.MOSSY_GRAVEL.get().getDefaultState(), FeatureSpread.func_242253_a(1, 2), 2, Lists.newArrayList(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState()));
        MOSSY_ROCKY_DIRT_CONFIG = new SphereReplaceConfig(ScenicBlocks.MOSSY_ROCKY_DIRT.get().getDefaultState(), FeatureSpread.func_242253_a(2, 3), 1, Lists.newArrayList(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState()));

        ROCK_PILE_CONFIG = (new RockPileConfig(ScenicBlocks.ROCK_PILE.get().getDefaultState(), 8, 1, 85, 32)); // was 60
        SANDSTONE_ROCK_PILE_CONFIG = (new RockPileConfig(ScenicBlocks.SANDSTONE_ROCK_PILE.get().getDefaultState(), 8, 61, 75, 2));

        STALAGMITE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.STALAGMITE.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).xSpread(20).zSpread(20).whitelist(ImmutableSet.of(Blocks.STONE.getDefaultState().getBlock())).func_227317_b_().build();
        STALACTITE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.STALACTITE.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).xSpread(20).zSpread(20).func_227317_b_().build();  //.whitelist(ImmutableSet.of(STONE.getBlock())) // (doesn't work with this placer, the whitelist checks below not above)

        TREASURE_POT_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ScenicBlocks.TREASURE_POT.get().getDefaultState()), new SimpleBlockPlacer())).tries(32).whitelist(ImmutableSet.of(Blocks.COBBLESTONE.getDefaultState().getBlock(), Blocks.MOSSY_COBBLESTONE.getDefaultState().getBlock())).func_227317_b_().build();

        GRASS_SHORT_FEATURE = Feature.RANDOM_PATCH.withConfiguration(GRASS_SHORT_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5);
        GRASS_TUFT_FEATURE = Feature.RANDOM_PATCH.withConfiguration(GRASS_TUFT_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5);

        ROCKY_DIRT_FEATURE = Feature.ORE.withConfiguration(ROCKY_DIRT_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 128)).func_242731_b(8));

        STALAGMITE_FEATURE = Feature.RANDOM_PATCH.withConfiguration(STALAGMITE_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 60)).func_242731_b(10));
        STALACTITE_FEATURE = Feature.RANDOM_PATCH.withConfiguration(STALACTITE_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 60)).func_242731_b(10));

        MOSSY_GRAVEL_FEATURE = Feature.DISK.withConfiguration(MOSSY_GRAVEL_CONFIG).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(NoPlacementConfig.INSTANCE).func_242731_b(1));
        MOSSY_ROCKY_DIRT_FEATURE = Feature.DISK.withConfiguration(MOSSY_ROCKY_DIRT_CONFIG).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(NoPlacementConfig.INSTANCE).func_242731_b(1));

        TREASURE_POT_FEATURE = Feature.RANDOM_PATCH.withConfiguration(TREASURE_POT_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 60))).func_242731_b(20);
        FISH_BONES_FEATURE = Feature.RANDOM_PATCH.withConfiguration(FISH_BONES_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 3))).func_242731_b(1);

        ROOTS_FEATURE = Feature.RANDOM_PATCH.withConfiguration(ROOTS_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 64))).func_242731_b(12); //
        ROOTS_LONG_FEATURE = Feature.RANDOM_PATCH.withConfiguration(ROOTS_LONG_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 64))).func_242731_b(15);
        ROOTS_GROUND_FEATURE = Feature.RANDOM_PATCH.withConfiguration(ROOTS_GROUND_CONFIG).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(NoPlacementConfig.INSTANCE).func_242731_b(2));
    }

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        RegistryKey<Biome> biome = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(event.getName(),
                        "Who registered null name biome, naming criticism!"));

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD)) {

            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ROCKY_DIRT_FEATURE);

            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, TREASURE_POT_FEATURE);

            if (event.getCategory() != Biome.Category.DESERT) {
                event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ScenicFeatures.ROCK_PILE_FEATURE.get().withConfiguration(ROCK_PILE_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(40, 0, 60))).func_242731_b(10));
            }

            if (event.getCategory() == Biome.Category.DESERT) {
                event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ScenicFeatures.SANDSTONE_ROCK_PILE_FEATURE.get().withConfiguration(SANDSTONE_ROCK_PILE_CONFIG).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 20))).func_242731_b(5));
            }

            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, STALACTITE_FEATURE);
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, STALAGMITE_FEATURE);

            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ROOTS_FEATURE);
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ROOTS_LONG_FEATURE);


            if (event.getCategory() == Biome.Category.RIVER || event.getCategory() == Biome.Category.SWAMP) {
                event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, MOSSY_GRAVEL_FEATURE);
                event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, MOSSY_ROCKY_DIRT_FEATURE);
            }

            if (event.getCategory() == Biome.Category.BEACH) {
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FISH_BONES_FEATURE);
            }

            if (event.getCategory() == Biome.Category.PLAINS
                    || event.getCategory() == Biome.Category.FOREST
                    || event.getCategory() == Biome.Category.EXTREME_HILLS
                    || event.getCategory() == Biome.Category.TAIGA
                    || event.getCategory() == Biome.Category.SAVANNA
                    || event.getCategory() == Biome.Category.JUNGLE) {

                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GRASS_SHORT_FEATURE);
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GRASS_TUFT_FEATURE);
            }

            if (event.getCategory() == Biome.Category.FOREST
                    || event.getCategory() == Biome.Category.TAIGA) {
//                     Ground roots look best if only in forests - they can appear a bit weird in wide open areas without many trees.
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ROOTS_GROUND_FEATURE);

                // TODO: Figure out a way to specifically generate ground roots at the base of trees.
            }
        }
    }

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

        Registry.register(registry, new ResourceLocation(Scenic.MODID, "grass_short"), GRASS_SHORT_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "grass_tuft"), GRASS_TUFT_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "rocky_dirt"), ROCKY_DIRT_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "stalagmite"), STALAGMITE_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "stalactite"), STALACTITE_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "treasure_pot"), TREASURE_POT_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "fish_bones"), FISH_BONES_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "mossy_gravel"), MOSSY_GRAVEL_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "mossy_rocky_dirt"), MOSSY_ROCKY_DIRT_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "roots"), ROOTS_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "long_roots"), ROOTS_LONG_FEATURE);
        Registry.register(registry, new ResourceLocation(Scenic.MODID, "ground_roots"), ROOTS_GROUND_FEATURE);
    }
}