package com.orangeburrito.scenic;

import com.mojang.logging.LogUtils;
import com.orangeburrito.scenic.common.registry.ModBlocks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Scenic.MODID)
public class Scenic {
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final String MODID = "scenic";

	public Scenic() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(this::setup);
		ModBlocks.BLOCKS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}
}
