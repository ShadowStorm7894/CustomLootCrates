package net.ShadowStorm7894.CustomLootCrates.item;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.Callable;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODS_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CustomLootCrates.MOD_ID);

    //adds items to a custom creative mod tab
    public static final RegistryObject<CreativeModeTab> LOOT_CRATES_TAB = CREATIVE_MODS_TABS.register("loot_crates_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.loot_crates_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //for every RegistryObject of type Item, inside ModItems.ITEMS, place in the creative mod tab
                        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                            output.accept(item.get());
                        }
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODS_TABS.register(eventBus);
    }
}
