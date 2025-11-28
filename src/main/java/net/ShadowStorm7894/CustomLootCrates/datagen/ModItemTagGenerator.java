package net.ShadowStorm7894.CustomLootCrates.datagen;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.ShadowStorm7894.CustomLootCrates.block.ModCrates;
import net.ShadowStorm7894.CustomLootCrates.item.ModCrateItems;
import net.ShadowStorm7894.CustomLootCrates.item.ModItems;
import net.ShadowStorm7894.CustomLootCrates.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {


    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, CustomLootCrates.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        taghelper("example");
    }

    private void taghelper(String cratename){
        // 1. Convert the string input to the required field name case (e.g., "wood" -> "WOOD")
        String upperCrateName = cratename.toUpperCase(Locale.ROOT);

        // 2. Construct the full static field names based on your naming convention
        String tagFieldName = upperCrateName + "_CRATE_LINK";
        String keyFieldName = upperCrateName + "_CRATE_KEY";
        String crateFieldName = upperCrateName + "_CRATE";
        try {
            // --- 1. Get the TagKey ---
            // Get the Field object from the ModTags.Items class
            Field tagField = ModTags.Items.class.getDeclaredField(tagFieldName);
            // Get the value of that static field (passing null for a static field)
            @SuppressWarnings("unchecked")
            TagKey<Item> crateTag = (TagKey<Item>) tagField.get(null);

            // --- 2. Get the RegistryObject for the Key ---
            Field keyField = ModItems.class.getDeclaredField(keyFieldName);
            @SuppressWarnings("unchecked")
            RegistryObject<Item> keyItem = (RegistryObject<Item>) keyField.get(null);

            // --- 3. Get the RegistryObject for the Crate ---
            Field crateField = ModCrateItems.class.getDeclaredField(crateFieldName);
            @SuppressWarnings("unchecked")
            RegistryObject<Item> crateItem = (RegistryObject<Item>) crateField.get(null);

            // --- 4. Use the retrieved objects (only if all succeeded) ---
            this.tag(crateTag)
                    .add(keyItem.get())
                    .add(crateItem.get());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle the error: the string didn't match a defined field
            System.err.println("Reflection Error: Could not find fields for crate name: " + cratename);
            e.printStackTrace();
        }
    }
}
