package net.evilcult.scenic.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;

/**
 * Plant Block
 * Scenic-Mod - net.evilcult.scenic.block.PlantBlock
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-1.0.3
 * @since 2020-04-25
 */
public class PlantBlock extends BushBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1D, 0D, 1D, 13D, 7D, 13D);

    public PlantBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos position, ISelectionContext context) {
        Vector3d offset = state.getOffset(world, position);
        return SHAPE.withOffset(offset.x, offset.y, offset.z);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos position) {
        return Tags.Blocks.DIRT.contains(state.getBlock());
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.PLAINS;
    }
}
