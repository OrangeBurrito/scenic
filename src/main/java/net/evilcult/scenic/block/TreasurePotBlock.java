package net.evilcult.scenic.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Treasure Pot Block
 * Scenic-Mod - net.evilcult.scenic.block.TreasurePotBlock
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-0.1.3
 * @since 2020-06-23
 */
public class TreasurePotBlock extends Block implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4d, 0d, 4d, 12d, 12d, 12d);

    public TreasurePotBlock(Properties properties) {
        super(properties);
        this.setDefaultState((this.stateContainer.getBaseState()).with(WATERLOGGED, false));
    }

    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos position) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean allowsMovement(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos position, @Nonnull PathType type) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos position, @Nonnull ISelectionContext context) {
        Vector3d offset = state.getOffset(world, position);
        return SHAPE.withOffset(offset.x, offset.y, offset.z);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos position, @Nonnull ISelectionContext context) {
        Vector3d offset = state.getOffset(world, position);
        return SHAPE.withOffset(offset.x, offset.y, offset.z);
    }

    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos position) {
        return !state.getCollisionShape(world, position).project(Direction.UP).isEmpty();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos position) {
        BlockPos blockPos = position.down();
        return this.isValidGround(world.getBlockState(blockPos), world, blockPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidState = context.getWorld().getFluidState(context.getPos());
        boolean isWaterLogged = fluidState.isTagged(FluidTags.WATER) && fluidState.getLevel() == 8;
        return Objects.requireNonNull(super.getStateForPlacement(context)).with(WATERLOGGED, isWaterLogged);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public FluidState getFluidState(@Nonnull BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED);
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState facingState, IWorld world, BlockPos position, BlockPos facing) {
        if (!state.isValidPosition(world, position)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (state.get(WATERLOGGED)) {
                world.getPendingFluidTicks().scheduleTick(position, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            return super.updatePostPlacement(state, direction, facingState, world, position, facing);
        }
    }
}