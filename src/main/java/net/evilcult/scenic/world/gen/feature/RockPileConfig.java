package net.evilcult.scenic.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

/**
 * Rock Pile Config
 * Scenic-Mod - net.evilcult.scenic.world.gen.feature.RockPileConfig
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-1.0.3
 * @since 2020-08-08
 */
public class RockPileConfig implements IFeatureConfig {

    public final BlockState state;
    public final int radius;
    public final int minHeight;
    public final int maxHeight;
    public final int count;

    public static final Codec<RockPileConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                    BlockState.CODEC.fieldOf("state").forGetter((config) -> config.state),
                    Codec.INT.fieldOf("minHeight").forGetter((c) -> c.minHeight),
                    Codec.INT.fieldOf("maxHeight").forGetter((c) -> c.maxHeight),
                    Codec.INT.fieldOf("radius").forGetter((c) -> c.radius),
                Codec.INT.fieldOf("count").forGetter((c) -> c.count)).apply(instance, RockPileConfig::new));


    public RockPileConfig(BlockState state, int radius, int minHeight, int maxHeight, int count) {
        this.state = state;
        this.radius = radius;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.count = count;
    }
}
