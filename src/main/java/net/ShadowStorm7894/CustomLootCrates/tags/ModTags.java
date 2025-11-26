package net.ShadowStorm7894.CustomLootCrates.tags;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.swing.text.html.HTML;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> LOOT_CRATE = tag("loot_crate");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CustomLootCrates.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> EXAMPLE_CRATE_LINK = tag("example_crate_link");


        private static TagKey<Item> tag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CustomLootCrates.MOD_ID, name));
        }
    }
}
