package net.evilcult.scenic;

import net.evilcult.scenic.registry.ScenicItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * Scenic Item Group
 * Scenic-Mod - net.evilcult.scenic.AestheticsItemGroup
 *
 * @author Patrick "Vaelzan" Beasley (vaelzan@evilcult.net)
 * @version 1.16.5-0.1.3
 * @since 2020-04-25
 */
public class ScenicItemGroups {
    public static final ItemGroup MAIN = new ItemGroup(Scenic.MODID + "_main") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ScenicItems.TREASURE_POT.get());
        }
    };
}
