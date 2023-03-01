package com.orangeburrito.scenic.common.registry;

import com.orangeburrito.scenic.Scenic;
import com.orangeburrito.scenic.common.block.FoliageBlock;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Scenic.MODID);

	public static final RegistryObject<Block> GRASS_TUFT = BLOCKS.register("grass_tuft", () -> new FoliageBlock(null));
	public static final RegistryObject<Block> GRASS_SHORT = BLOCKS.register("grass_short", () -> new FoliageBlock(null));
}