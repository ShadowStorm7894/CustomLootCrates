package net.ShadowStorm7894.CustomLootCrates.block;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.ShadowStorm7894.CustomLootCrates.item.ModCrateItems;
import net.ShadowStorm7894.CustomLootCrates.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModCrates {
    public static final DeferredRegister<Block> CRATES =
        DeferredRegister.create(ForgeRegistries.BLOCKS, CustomLootCrates.MOD_ID);


        public static final RegistryObject<Block> EXAMPLE_CRATE = CRATES.register("example_crate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));

        /*
        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
            //adds the block to BLOCKS
            RegistryObject<T> toReturn = CRATES.register(name, block);
            //calls function
            registerBlockItem(name, toReturn);
            return toReturn;
        }

        private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
            //registers block to ITEMS
            RegistryObject<Item> BlockItem = ModCrateItems.CRATE_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }
        */
        public static void register(IEventBus eventBus) {
        CRATES.register(eventBus);
    }
}
