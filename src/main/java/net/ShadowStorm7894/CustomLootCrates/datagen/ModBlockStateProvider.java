package net.ShadowStorm7894.CustomLootCrates.datagen;

import net.ShadowStorm7894.CustomLootCrates.CustomLootCrates;
import net.ShadowStorm7894.CustomLootCrates.block.ModCrates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CustomLootCrates.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for(RegistryObject<Block> blocks : ModCrates.CRATES.getEntries()){
            cubeWithItem(blocks);
        }
    }


    private void cubeWithItem(RegistryObject<Block> blockRegistryObject){
        //Make blocks have item models that are 3D and the block models have the same texture on all 6 faces (cubeAll)
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
