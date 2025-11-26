package net.ShadowStorm7894.CustomLootCrates.item.custom;

import net.ShadowStorm7894.CustomLootCrates.block.ModCrates;
import net.ShadowStorm7894.CustomLootCrates.item.ModCrateItems;
import net.ShadowStorm7894.CustomLootCrates.item.ModItems;
import net.ShadowStorm7894.CustomLootCrates.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LootCrateKeys extends Item {
    public LootCrateKeys(Properties pProperties) {
        super(pProperties);
    }
    public final int cooldown = 20;

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        ItemStack itemstack = pContext.getItemInHand();
        List<TagKey<Item>> itemTags = itemstack.getTags().toList();
        boolean correctcrate = false;
        assert player != null;
        player.getCooldowns().addCooldown(this, cooldown);

        //are we on serverside
        if (!pContext.getLevel().isClientSide()) {
            BlockPos blockPos = pContext.getClickedPos();
            BlockState state = pContext.getLevel().getBlockState(blockPos);
            ResourceLocation id = ForgeRegistries.BLOCKS.getKey(state.getBlock());
            for(RegistryObject<Item> crateItem : ModCrateItems.CRATE_ITEMS.getEntries()){
                if(id == ForgeRegistries.ITEMS.getKey(crateItem.get())){
                    List<TagKey<Item>> crateTags = getCrateItemTags(pContext.getLevel(), crateItem.get());
                    for(TagKey<Item> tag : crateTags) {
                        if(itemTags.contains(tag)){
                            //gamble away??
                        }
                    }
                }
            }
        }
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
    public static List<TagKey<Item>> getCrateItemTags(Level level, Item item) {
        // Dynamic registry lookup (non-deprecated)
        HolderLookup<Item> lookup = level.registryAccess().lookupOrThrow(Registries.ITEM);

        // Item ID (ResourceLocation)
        ResourceLocation key = ForgeRegistries.ITEMS.getKey(item);

        // MUST wrap it as a ResourceKey<Item>
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, key);

        // Correct: get the dynamic holder from the lookup
        Holder<Item> holder = lookup.getOrThrow(itemKey);

        // Return tag keys
        return holder.tags().toList();
    }

}


