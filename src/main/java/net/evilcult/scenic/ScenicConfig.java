package net.evilcult.scenic;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Scenic Config
 * Scenic-Mod - net.evilcult.scenic.ScenicConfig
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-1.0.3
 * @since 2020-07-16
 */
public class ScenicConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> dimensionWhitelist;
	public static final ForgeConfigSpec.ConfigValue<Integer> grassShortMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> grassTuftMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> rockyDirtMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> stalactiteMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> stalagmiteMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> mossyGravelMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> MOSSY_ROCKY_DIRT_MAXIMUM;
	public static final ForgeConfigSpec.ConfigValue<Integer> treasurePotMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> fishBonesMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> rootsMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> rootsLongMaximum;
	public static final ForgeConfigSpec.ConfigValue<Integer> rootsGroundMaximum;

	static {
		BUILDER.push("Scenic Config");
		dimensionWhitelist = BUILDER.comment("A comma-separated list of strings of dimensions in which Scenic's features should generate (strings from BiomeDictionary.Type, see below for the format)").defineList("dimensionWhitelist", Lists.newArrayList("OVERWORLD","TWILIGHT"), itemRaw -> itemRaw instanceof String);
		// TODO: Figure out a way to blacklist features from certain dimensions.

		grassShortMaximum = BUILDER.comment("Maximum Short Grass Blocks Per Chunk (in blocks) - set to zero to disable").define("maximumGrassShorts", 5);
		grassTuftMaximum = BUILDER.comment("Maximum Grass Tufts Per Chunk (in blocks) - set to zero to disable").define("maximumGrassTufts", 5);
		rockyDirtMaximum = BUILDER.comment("Maximum Rocky Dirt Blocks Per Chunk (in blocks) - set to zero to disable").define("maximumRockyDirt", 8);
		stalactiteMaximum = BUILDER.comment("Maximum Stalactites Per Chunk (in blocks) - set to zero to disable").define("maximumStalactites", 10);
		stalagmiteMaximum = BUILDER.comment("Maximum Stalagmites Per Chunk (in blocks) - set to zero to disable").define("maximumStalagmites", 10);
		mossyGravelMaximum = BUILDER.comment("Maximum Mossy Gravel Blocks Per Chunk (in blocks) - set to zero to disable").define("maximumMossyGravel", 1);
		MOSSY_ROCKY_DIRT_MAXIMUM = BUILDER.comment("Maximum Mossy Rocky Dirt Blocks Per Chunk (in blocks) - set to zero to disable").define("maximumMossyRockyDirt", 1);
		treasurePotMaximum = BUILDER.comment("Maximum Treasure Pots Per Chunk (in blocks) - set to zero to disable").define("maximumTreasurePots",20);
		fishBonesMaximum = BUILDER.comment("Maximum Fish Bones Per Chunk (in blocks) - set to zero to disable").define("maximumFishBones", 1);
		rootsMaximum = BUILDER.comment("Maximum Roots Per Chunk (in blocks) - set to zero to disable").define("maximumRoots", 12);
		rootsLongMaximum = BUILDER.comment("Maximum Roots Per Chunk (in blocks) - set to zero to disable").define("maximumRootsLong", 15);
		rootsGroundMaximum = BUILDER.comment("Maximum Roots Per Chunk (in blocks) - set to zero to disable").define("maximumRootsGround", 2);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}