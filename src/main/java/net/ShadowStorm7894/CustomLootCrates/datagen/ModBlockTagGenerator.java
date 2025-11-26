package net.ShadowStorm7894.CustomLootCrates.datagen;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.ShadowStorm7894.CustomLootCrates.block.ModBlocks;
import net.ShadowStorm7894.CustomLootCrates.block.ModCubes;
import net.ShadowStorm7894.CustomLootCrates.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CustomLootCrates.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
            .add(ModCubes.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModCubes.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);

    }
}
