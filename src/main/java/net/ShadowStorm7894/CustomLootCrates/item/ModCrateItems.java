package net.ShadowStorm7894.CustomLootCrates.item;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCrateItems {
    public static final DeferredRegister<Item> CRATE_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CustomLootCrates.MOD_ID);

    public static void register(IEventBus eventBus) {
        CRATE_ITEMS.register(eventBus);
    }
}
