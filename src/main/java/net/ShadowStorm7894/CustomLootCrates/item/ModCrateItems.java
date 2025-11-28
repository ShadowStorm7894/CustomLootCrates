package net.ShadowStorm7894.CustomLootCrates.item;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.ShadowStorm7894.CustomLootCrates.block.ModCrates;
import net.ShadowStorm7894.CustomLootCrates.item.custom.LootCrateKeys;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCrateItems {
    public static final DeferredRegister<Item> CRATE_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CustomLootCrates.MOD_ID);
    
    public static final RegistryObject<Item> EXAMPLE_CRATE = CRATE_ITEMS.register("example_crate",
            () -> new BlockItem(ModCrates.EXAMPLE_CRATE.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        CRATE_ITEMS.register(eventBus);
    }
}
