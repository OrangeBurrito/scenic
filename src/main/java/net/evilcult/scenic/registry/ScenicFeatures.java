package net.evilcult.scenic.registry;

import net.evilcult.scenic.Scenic;
import net.evilcult.scenic.world.gen.feature.ClusterReplaceConfig;
import net.evilcult.scenic.world.gen.feature.ClusterReplaceFeature;
import net.evilcult.scenic.world.gen.feature.RockPileConfig;
import net.evilcult.scenic.world.gen.feature.RockPileFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * Scenic Features
 * Scenic-Mod - net.evilcult.scenic.registry.ScenicFeatures
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-1.0.3
 * @since 2020-06-20
 */
public class ScenicFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Scenic.MODID);

    public static final RegistryObject<Feature<ClusterReplaceConfig>> CLUSTER_REPLACE_FEATURE = register("scattered_disk", () -> new ClusterReplaceFeature(ClusterReplaceConfig.CODEC));
    public static final RegistryObject<Feature<RockPileConfig>> ROCK_PILE_FEATURE = register("rock_pile", () -> new RockPileFeature(RockPileConfig.CODEC));
    public static final RegistryObject<Feature<RockPileConfig>> SANDSTONE_ROCK_PILE_FEATURE = register("sandstone_rock_pile", () -> new RockPileFeature(RockPileConfig.CODEC));

    private static <F extends Feature<?>> RegistryObject<F> register(String name, Supplier<F> supplier) {
        return FEATURES.register(name, supplier);
    }
}
