package net.ShadowStorm7894.CustomLootCrates.datagen;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.ShadowStorm7894.CustomLootCrates.item.ModCrateItems;
import net.ShadowStorm7894.CustomLootCrates.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CustomLootCrates.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            simpleItem(item);
        }
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
            ResourceLocation.parse("item/generated")).texture("layer0",
            ResourceLocation.fromNamespaceAndPath(CustomLootCrates.MOD_ID, "item/" + item.getId().getPath()));
    }
}
