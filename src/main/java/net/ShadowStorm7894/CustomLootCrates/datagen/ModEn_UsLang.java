package net.ShadowStorm7894.CustomLootCrates.datagen;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.ShadowStorm7894.CustomLootCrates.item.ModCrateItems;
import net.ShadowStorm7894.CustomLootCrates.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

public class ModEn_UsLang extends LanguageProvider {
    public ModEn_UsLang(PackOutput output, String locale) {
        super(output, CustomLootCrates.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("creativetab.loot_crates_tab", "Loot Crates Tab");
        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            // Get the full ID (e.g., "customlootcrates:oak_crate_key")
            String id = item.getId().toString();

            // Remove the namespace and replace underscores with spaces (e.g., "oak_crate_key" -> "oak crate key")
            String cleanedName = id.substring(id.indexOf(":") + 1).replace("_", " ");

            // Convert to Title Case (e.g., "oak crate key" -> "Oak Crate Key")
            String finalName = capitalizeWords(cleanedName);

            // Add the translation: key will be 'item.customlootcrates.oak_crate_key', value will be 'Oak Crate Key'
            addItem(item, finalName);
        }

        // --- Translation Logic for ModCrateItems ---
        for(RegistryObject<Item> item : ModCrateItems.CRATE_ITEMS.getEntries()){
            String id = item.getId().toString();
            String cleanedName = id.substring(id.indexOf(":") + 1).replace("_", " ");
            String finalName = capitalizeWords(cleanedName);

            addItem(item, finalName);
        }
    }

    private String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                // Capitalize the first letter and append the rest of the word
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase(Locale.ROOT))
                        .append(" ");
            }
        }

        // Trim trailing space and return
        return capitalized.toString().trim();
    }
}
