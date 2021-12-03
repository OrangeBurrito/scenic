package net.evilcult.scenic.world.gen.feature;

import com.mojang.serialization.Codec;
import net.evilcult.scenic.block.RockPileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

/**
 * Rock Pile Feature
 * Scenic-Mod - net.evilcult.scenic.world.gen.feature.RockPileFeature
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-1.0.3
 * @since 2020-06-24
 */
public class RockPileFeature extends Feature<RockPileConfig> {

    public RockPileFeature(Codec<RockPileConfig> codec) {
        super(codec);
    }

    protected static boolean isLeavesAt(IWorldGenerationReader reader, BlockPos pos) {
        return reader.hasBlockState(pos, state -> (state.isIn(BlockTags.LEAVES)));
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos chunkCorner, RockPileConfig config) {

        BlockState floor = reader.getBlockState(chunkCorner.down());
        if (floor.getMaterial() != Material.EARTH && floor.getMaterial() != Material.ORGANIC && floor.getMaterial() != Material.ROCK) {
            return false;
        }

        int placed = 0;

        for(int i = 0; i < config.count; ++i) {
            int xRand = random.nextInt(config.radius) - random.nextInt(config.radius);
            int zRand = random.nextInt(config.radius) - random.nextInt(config.radius);

            int y = config.minHeight + random.nextInt(config.maxHeight - config.minHeight);
            BlockPos position = new BlockPos(chunkCorner.getX() + xRand, y, chunkCorner.getZ() + zRand);
            BlockState state = config.state.with(RockPileBlock.ROCKS, random.nextInt(7) + 1);

            if (isLeavesAt(reader, position.down())) {
                return false;
            }

            if ( reader.isAirBlock(position) && state.isValidPosition(reader, position)) {
                reader.setBlockState(position, state, 2);
                placed++;
            } else if (reader.getBlockState(position).getBlock() == Blocks.WATER && state.isValidPosition(reader, position))  {
                reader.setBlockState(position, state.with(RockPileBlock.WATERLOGGED, true), 2);
                placed++;
            }
        }

        return placed > 0;
    }
}
